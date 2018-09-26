package com.meishihui.action;

import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.Expensed;
import com.meishihui.enties.Order;
import com.meishihui.service.ExpenseService;
import com.meishihui.service.OrderMenuService;
import com.meishihui.service.TableNumService;
import com.meishihui.util.CacheUtil;
import com.meishihui.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage(value="myInterceptor")
@Scope(value="prototype")
@Namespace(value="/chargeManage")
public class ChargeManger extends ActionSupport implements ModelDriven<Expensed>{

	private Date startDate;
	private Date endDate;
	private String expenseType;
	@Autowired
	private OrderMenuService orderService;
	
	@Autowired
	private TableNumService tableNumService;
	
	@Autowired
	private ExpenseService expenseService;
	
	private Expensed expensed = new Expensed();
	
	@Override
	public Expensed getModel() {
		return expensed;
	}

	
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	@Action(value="chargeManageAction_openConsumOrderPage")
	public String openConsumOrderPage() throws Exception{
		
		return "openTurnOverPage";
	}
	@Action(value="chargeManageAction_getConsumOrderList",results={@Result(name="consumOrderList",location="/jsp/charge/ConsumOrderList.jsp"),})
	public String getConsumOrderList() throws ParseException{
	
		String hql = "FROM Order where isSettleAcounts = ? and createDate between ? and ? ";
		Object []objects = new Object[]{"1",startDate,CommonUtil.getNextDate(endDate)};
		List<Order> listOrder = orderService.getOrderList(hql, objects);
		Map<String,String> tabMap = tableNumService.getMapTableNumName();
		
		int sumYSJE = 0;
		int sumSSJE = 0;
		int XJJE = 0;
		int ZFBJE = 0;
		int WXJE = 0;
		int SKJE = 0;
		for(Order ord:listOrder){
			sumYSJE += ord.getYSJE();
			sumSSJE += ord.getSSJE();
			switch(ord.getPayType()){
				case "现金":XJJE+=ord.getSSJE();break;
				case "支付宝":ZFBJE+=ord.getSSJE();break;
				case "微信":WXJE+=ord.getSSJE();break;
				case "刷卡":SKJE+=ord.getSSJE();break;
			}
			ord.setTableId(tabMap.get(ord.getTableId()));
		}
		ActionContext.getContext().get("request");			
		ActionContext.getContext().put("listOrder", listOrder);
		ActionContext.getContext().put("sumYSJE", sumYSJE);
		ActionContext.getContext().put("sumSSJE", sumSSJE);
		ActionContext.getContext().put("XJJE", XJJE);
		ActionContext.getContext().put("ZFBJE", ZFBJE);
		ActionContext.getContext().put("WXJE", WXJE);
		ActionContext.getContext().put("SKJE", SKJE);
		
		return "consumOrderList";
	}
	@Action(value="chargeManageAction_openExpensePage",results={@Result(name="expenseList",location="/jsp/charge/expenseMainPage.jsp"),})
	public String openExpensePage() throws Exception{
		List<Expensed> expenseList = expenseList(null, null,null);
		double expenseTotalMoney = 0;
		if(CommonUtil.isNotNullList(expenseList)){
			for(Expensed exp:expenseList){
				expenseTotalMoney += exp.getPayMoney();
			}
		}
		ActionContext.getContext().put("expenseTotalMoney", expenseTotalMoney);
		ActionContext.getContext().put("expenseList", expenseList);
		return "expenseList";
	}
	
	@Action(value="chargeManageAction_queryExpenseRecord",results={@Result(name="expenseListTable",location="/jsp/charge/expenseTable.jsp"),})
	public String queryExpenseRecord() throws Exception{
		List<Expensed> expenseList = expenseList(startDate,endDate,expenseType);
		double expenseTotalMoney = 0;
		if(CommonUtil.isNotNullList(expenseList)){
			for(Expensed exp:expenseList){
				expenseTotalMoney += exp.getPayMoney();
			}
		}
		ActionContext.getContext().put("expenseTotalMoney", expenseTotalMoney);
		ActionContext.getContext().put("expenseList", expenseList);
		return "expenseListTable";
	}

	@Action(value="chargeManageAction_sureExpense",results={@Result(name="addExpenseSuccess",type="redirectAction",location="/chargeManageAction_queryExpenseRecord")})
	public String sureExpense()throws Exception{
		Expensed exp = new Expensed();
		exp.setComments(expensed.getComments());
		exp.setPayMoney(expensed.getPayMoney());
		exp.setExpensedType(expensed.getExpensedType());
		
		exp.setWareHoseDetailId("0");
		exp.setDate(new Date());
		exp.setOperator(CacheUtil.getUser().getShowName());
		expenseService.saveExpense(exp);
		return "addExpenseSuccess";
	}
	
	private List<Expensed> expenseList(Date startDate,Date endDate,String expenseType){
		List<Expensed> expenseList = null;
		if(CommonUtil.isNotNullDate(startDate) && CommonUtil.isNotNullDate(endDate)){
			String hql = "";
			Object []objects = null;
			if("-1".equals(expenseType)){
				hql = "From Expensed where date between ? and ?";
				objects = new Object[]{startDate,CommonUtil.getNextDate(endDate)};
			}else{
				hql = "From Expensed where date between ? and ? and expensedType = ?";
				objects = new Object[]{startDate,CommonUtil.getNextDate(endDate),expenseType};
			}
				expenseList = expenseService.queryExpenseRecord(hql, objects);
		}else{
			if(CommonUtil.isNotNullStr(expenseType) && !"-1".equals(expenseType)){
				String hql = "From Expensed where expensedType = ?";
				 expenseList = expenseService.queryExpenseRecord(hql, new Object[]{expenseType});
			}else{
				expenseList = expenseService.queryAllExpenseRecord();
			}
				   
		}
		return expenseList;
	}
	

}
