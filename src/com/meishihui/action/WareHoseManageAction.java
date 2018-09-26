package com.meishihui.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.AddWareHoseDetail;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.TableNumber;
import com.meishihui.enties.WareHose;
import com.meishihui.service.OrderMenuService;
import com.meishihui.service.WareHoseService;
import com.meishihui.util.CacheUtil;
import com.meishihui.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("wareHoseAction")
@Scope("prototype")
@ParentPackage("myInterceptor")
@Namespace("/wareHose")
@Results({
		@Result(name="openInvertoryPopup",location="/jsp/wareHose/addProductType_popup.jsp"),
		@Result(name="listHoseDetail",location="/jsp/wareHose/hoseDetailMain.jsp"),
		@Result(name="listHoseDetailPart",location="/jsp/wareHose/hoseDetailTable.jsp"),
		@Result(name="hoseDetailPaymentHtml",location="/jsp/wareHose/paymentPage_popup.jsp"),
		@Result(name="hoseDetailDebt",type="redirectAction",location="/wareHoseManageAction_getNoSettleHoseDetail"),
		@Result(name="successAddProductType",type="redirectAction",location="/wareHoseManageAction_openMain"),
		@Result(name="error",location="/jsp/error.jsp")
		
		})

public class WareHoseManageAction extends ActionSupport implements ModelDriven<AddWareHoseDetail>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private WareHoseService hoseService;
	@Autowired
	private OrderMenuService orderService;

	private AddWareHoseDetail hoseDetail = new AddWareHoseDetail();

	@Override
	public AddWareHoseDetail getModel() {
		return hoseDetail;
	}
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	@Action(value="wareHoseManageAction_openMain",results={@Result(name="openWareHose",location="/jsp/wareHose/wareHoseMain.jsp")})
	public String openMain() throws Exception{
		String hql = "FROM WareHose WHERE wareHoseType = ?";
		Object []object = new Object[]{"4"};
		hoseService.getWareHose(hql, object);
		List<WareHose> wareHoseList = hoseService.getWareHose(hql, object);
		ActionContext.getContext().put("wareHoseList", wareHoseList);
		return "openWareHose";
	}
	/**
	 * 新增产品
	 * @return
	 * @throws Exception
	 */
	@Action(value="wareHoseManageAction_addProductType",results={@Result(name="openOrderMenu",type="chain",location="wareHoseManageAction_openOrderMenu")})
	public String addProductType() throws Exception{
		
		String returnStr = "openOrderMenu";
		
		if("4".equals(hoseDetail.getWareHoseType())){
			returnStr = "successAddProductType";
			hoseService.saveWareHoseAndDetail(hoseDetail);
		}else{
			WareHose wareHose = hoseService.hoseDetailToHose(hoseDetail);
			hoseService.saveWareHose(wareHose);
		}
		
		return returnStr;
	}
	@Action(value="wareHoseManageAction_openOrderMenu",results={@Result(name="lookMenu",location="/jsp/orderMenu/lookMenu.jsp")})
	public String openOrderMenu() throws Exception{
		Map<String,List<WareHose>> map = hoseService.getWareHoseClassify();	
		List<TableNumber> listTab = orderService.getTableNumberForIsUse("0");
		
		ActionContext.getContext().put("listTab", listTab);		
		ActionContext.getContext().put("listL", map.get("listL"));
		ActionContext.getContext().put("listH", map.get("listH"));
		ActionContext.getContext().put("listDrink", map.get("listDrink"));
		ActionContext.getContext().put("listS", map.get("listS"));
		ActionContext.getContext().put("menuType", hoseDetail.getWareHoseType());
		
		return "lookMenu";
	}
	@Action(value="wareHoseManageAction_openInvertoryPopup",results={})
	public String openInvertoryPopup() throws Exception{
		ActionContext.getContext().put("foodType", hoseDetail.getWareHoseType());
		return "openInvertoryPopup";
	}
	@Action(value="wareHoseManageAction_addInvertory")
	public String addInvertory() throws Exception{
		hoseService.addInvertory(hoseDetail);
		return "successAddProductType";
	}
	@Action(value="wareHoseManageAction_getAllHoseDetail")
	public String  getAllHoseDetail() throws Exception{
		List<AddWareHoseDetail> listHoseDetail = hoseService.getAllWareHoseDetail();
		ActionContext.getContext().put("listHoseDetail", listHoseDetail);
		return "listHoseDetail";
	}
	
	@Action(value="wareHoseManageAction_getHostDetailBysql",results={})
	public String getHostDetailBysql() throws Exception{
		String resultStr = "listHoseDetailPart";
		System.out.println(CacheUtil.getUser().getLoginName()+"rrrr");
		List<AddWareHoseDetail> listHoseDetail = null;
		String hql = "FROM AddWareHoseDetail WHERE isSettle = ?";
		Object []object = new Object[]{hoseDetail.getIsSettle()};
		listHoseDetail = hoseService.getWareHoseDetail(hql, object);
	
		ActionContext.getContext().put("listHoseDetail", listHoseDetail);
		if("debt".equals(hoseDetail.getComments())){
			ActionContext.getContext().put("queryType", "debt");
			resultStr = "listHoseDetail";
			
			if(CommonUtil.isNotNullList(listHoseDetail)){
				double debtSumMoney = 0;
				for(AddWareHoseDetail hoseDetail:listHoseDetail){
					debtSumMoney +=hoseDetail.getRemainMoney();
				}
				ActionContext.getContext().put("debtSumMoney", debtSumMoney);
			}
		}
		return resultStr;
	}
	
	@Action(value="wareHoseManageAction_getNoSettleHoseDetail")
	public String getNoSettleHoseDetail() throws Exception{
		String hql = "FROM AddWareHoseDetail WHERE isSettle = ?";
		Object []object = new Object[]{0};
		List<AddWareHoseDetail> listHoseDetail = hoseService.getWareHoseDetail(hql, object);
		ActionContext.getContext().put("listHoseDetail", listHoseDetail);
		ActionContext.getContext().put("queryType", "debt");
		return "listHoseDetail";
	}
	
	@Action(value="wareHoseManageAction_OpenPaymentPage")
	public String OpenPaymentPage() throws Exception{
		String hoseDetailId = hoseDetail.getId();
		AddWareHoseDetail hoseDetailEnty = hoseService.getWareHoseDetailById(hoseDetailId);
		ActionContext.getContext().put("hoseDetail", hoseDetailEnty);
		return "hoseDetailPaymentHtml";
	}
	
	@Action(value="surePayment")
	public String surePayment() throws Exception{
		String hoseDetailId = hoseDetail.getId();
		double sfje = hoseDetail.getReallyPaySumMoney();//这里是实付金额
		AddWareHoseDetail hoseDetailEnty = hoseService.getWareHoseDetailById(hoseDetailId);
		hoseDetailEnty.setIsSettle(1);
		
		String wareHoseId = hoseDetailEnty.getWareHoseId();
		WareHose wareHose = hoseService.getWareHoseById(wareHoseId);
		
		double sumMoney = wareHose.getPaySumMoney() + sfje;
		wareHose.setPaySumMoney(sumMoney);
		
		Expensed expen = new Expensed();
		expen.setDate(new Date());
		expen.setWareHoseDetailId(hoseDetailId);
		expen.setExpensedType("4");
		expen.setPayMoney(sfje);
		expen.setComments(hoseDetail.getComments());
		expen.setOperator(CacheUtil.getUser().getShowName());
		
		hoseService.updateWareHoseAndDetail(wareHose, hoseDetailEnty,expen);
		
		return "hoseDetailDebt";
	}
}
