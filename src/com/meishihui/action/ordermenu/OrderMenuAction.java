package com.meishihui.action.ordermenu;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.Order;
import com.meishihui.enties.OrderFoodMenu;
import com.meishihui.enties.TableNumber;
import com.meishihui.enties.WareHose;
import com.meishihui.service.OrderMenuService;
import com.meishihui.service.WareHoseService;
import com.meishihui.util.CacheUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope(value="prototype")
@Namespace(value="/ordermenu")
@ParentPackage(value="myInterceptor")
@Results({@Result(name="newAddSuccess",location="/jsp/orderMenu/orderMenuMain.jsp")})
public class OrderMenuAction  extends ActionSupport{

	@Autowired
	private OrderMenuService orderService;
	@Autowired
	private WareHoseService wareHoseService;
	
	@Override
	public String execute() throws Exception {
		System.out.println("do execute().....menu");
		return "success";
	}
	
	public String openOrderMenuPage() throws Exception {
		System.out.println("openOrderMenuPage().....menu");
		return "success";
	}
	@Action(value="OrderMenuAction_addMenu",results={@Result(name="newAddMenu",location="/jsp/orderMenu/addMenu_popup.jsp")})
	public String addMenu() throws Exception {
		return "newAddMenu";
	}
	
	@Action(value="OrderMenuAction_lookMenu",results={@Result(name="lookMenu",location="/jsp/orderMenu/lookMenu.jsp")})
	public String lookMenu() throws Exception{
	
		Map<String,List<WareHose>> map = wareHoseService.getWareHoseClassify();	
		List<TableNumber> listTab = orderService.getTableNumberForIsUse("0");
		
		ActionContext.getContext().put("listTab", listTab);		
		ActionContext.getContext().put("listL", map.get("listL"));
		ActionContext.getContext().put("listH", map.get("listH"));
		ActionContext.getContext().put("listDrink", map.get("listDrink"));
		ActionContext.getContext().put("listS", map.get("listS"));
		ActionContext.getContext().put("menuType", type);
		
		return "lookMenu";
	}
	@Action(value="OrderMenuAction_saveFoodMenu")
	public String saveFoodMenu() throws Exception {
		
		WareHose wareHose = new WareHose();
		wareHose.setCreateDate(new Date());
		wareHose.setName(foodName);
		wareHose.setSellPrice(sellPrice);
		wareHose.setStock(addWareHoseCount);
		wareHose.setPurchasePrice(buyPrice);
		wareHoseService.saveWareHose(wareHose);
		return "newAddSuccess";
	}
	@Action(value="OrderMenuAction_deleteFoodMenu")
	public String deleteFoodMenu()throws Exception{
		WareHose food = wareHoseService.getWareHoseById(foodMenuId);
		food.setIsShow(0);
		wareHoseService.update(food);
		/*return "deleteFoodMenu";*/
		return null;
	}
	@Action(value="OrderMenuAction_updateFoodMenu",results={@Result(name="updateFoodMenu",type="redirectAction",location="/ordermenu/OrderMenuAction_lookMenu")})
	public String updateFoodMenu()throws Exception{
		
		WareHose food = wareHoseService.getWareHoseById(foodMenuId);
		food.setSellPrice(sellPrice);
		wareHoseService.update(food);
		return "updateFoodMenu";
	}
	/**餐桌**/
	@Action(value="OrderMenuAction_addTable",results={@Result(name="addTable",location="/jsp/orderMenu/addTable.jsp")})
	public String addTable()throws Exception{
		return "addTable";
	}
	
	@Action(value="OrderMenuAction_saveTable")
	public String saveTable()throws Exception{

		TableNumber tab = new TableNumber();		
		tab.setNumber(tabNumber);
		tab.setIsUse("0");
		tab.setCreateDate(new Date());
		
		orderService.saveTable(tab);
		return "newAddSuccess";
	}
	/**订单**/
	@Action(value="OrderMenuAction_submitOrder",results={@Result(name="addOrderSuccess",location="/jsp/orderMenu/orderMenuMain.jsp")})
	public String submitOrder()throws Exception{
		
		Order order = orderService.getOrder(menuStr, tableId, Integer.valueOf(ysje), Integer.valueOf(cjCount), CacheUtil.getUser().getShowName());
		
		orderService.submitOrder(order);
		orderService.updateTable(tableId, "1");
		return "addOrderSuccess";
	}
	@Action(value="OrderMenuAction_sureEditOrder",results={@Result(name="editOrder",type="redirectAction",location="/OrderMenuAction_waitSettle")})
	public String sureEditOrder()throws Exception{
		
		Order oldOrder = orderService.getOrderById(orderId);
		oldOrder = orderService.modifyOrder(oldOrder, menuStr);
		
		oldOrder.setYSJE(Double.valueOf(ysje));
		oldOrder.setSSJE(Double.valueOf(ysje));
		oldOrder.setDinnerwareCount(Integer.valueOf(cjCount));
		oldOrder.setTableId(tableId);
		oldOrder.setOperator(CacheUtil.getUser().getShowName());
		
		orderService.saveOrUpdate(oldOrder);
		return "editOrder";
	}
	@Action(value="OrderMenuAction_waitSettle",results={@Result(name="waitSettle",location="/jsp/orderMenu/waitSettle.jsp")})
	public String waitSettle()throws Exception{
		
		String hql="From Order where isSettleAcounts = ?";
		Object []objects = new Object[]{"0"};
		List<Order> waitSettleList = orderService.getOrderList(hql,objects);
		
		ActionContext.getContext().put("waitSettleList", waitSettleList);

		List<TableNumber> listTable  = orderService.getTableNumberForIsUse("all");
		Map<String,String> tableMap = new HashMap<String,String>();
		for(TableNumber num:listTable){
			tableMap.put(num.getId(), num.getNumber());
		}
		
		ActionContext.getContext().put("tableMap", tableMap);
		return "waitSettle";
	}
	//TODO 性能待提高，懒加载
	@Action(value="OrderMenuAction_openOrderDetail",results={@Result(name="orderDetail",location="/jsp/orderMenu/orderDetail_popup.jsp")})
	public String openOrderDetail()throws Exception{
	/*	Order order = orderService.getOneWaitSettleLazyLoad(orderId);
	*/
		String hql = "From Order where oid = ?";
		Object []objects = new Object[]{orderId};
		Order order =orderService.getOneWaitSettle(hql, objects);
	
		ActionContext.getContext().put("order", order);
		System.out.println("after "+order.getSetFoodMenu().size());
		List<TableNumber> listTable  = orderService.getTableNumberForIsUse("1");
		Map<String,String> tableMap = new HashMap<String,String>();
		for(TableNumber num:listTable){
			tableMap.put(num.getId(), num.getNumber());
		}
		
		ActionContext.getContext().put("tableName", tableMap.get(order.getTableId()));
		
		return "orderDetail";
	}
	
	
	@Action(value="OrderMenuAction_editOrderDetail",results={@Result(name="orderData",type="json")})
	public String editOrderDetail()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String hql = "From Order where oid = ?";
		Object []objects = new Object[]{orderId};
		Order order =orderService.getOneWaitSettle(hql, objects);
		
		List<TableNumber> tableList = orderService.getTableNumberForIsUse("1");
		String tableNum="";
		for(TableNumber table:tableList){
			if(order.getTableId().equals(table.getId())){
				tableNum = table.getNumber();
				break;
			}
		}
		
		map.put("tableId", order.getTableId()+"_"+tableNum);
		map.put("dinnerwareCount", order.getDinnerwareCount()+"");
		order = orderService.filtNoShowFood(order);
		Set<OrderFoodMenu> set = order.getSetFoodMenu();
		for(OrderFoodMenu od :set){
			map.put(od.getFid(), od.getCountMenu()+"");
		}
		setOrderData(map);
//		return null;
		return "orderData";
	}
	
	@Action(value="OrderMenuAction_payOrder",results={@Result(name="payOrder",type="redirect",location="/ordermenu/OrderMenuAction_waitSettle")})
	public String payOrder()throws Exception{
		
		String hql = "From Order WHERE oid = ?";
		Object []objects = new Object[]{orderId};
		Order order =orderService.getOneWaitSettle(hql, objects);
		
		
		order.setSSJE(Double.valueOf(ssje));
		order.setIsSettleAcounts(1+"");
		order.setPayType(payType);
		order.setSettlAccountDate(new Date());
		
		Set<OrderFoodMenu> setFood = order.getSetFoodMenu();

		orderService.payOrder(order, setFood);
		return "payOrder";
	}
	
	
	String foodName;
	Double sellPrice;
	Double buyPrice;
	Double sumMoney;
	
	String foodType;
	String type;
	String foodMenuId;
	String cjCount;

	/*餐桌**/
	private String tabNumber;
	/***订单****/
	private String tableId;
	private String menuStr;
	private String ysje;
	private String ssje;
	private String orderId;
	private String payType;
	private int addWareHoseCount;
	private Map<String,String> orderData;
	
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public void setAddWareHoseCount(int addWareHoseCount) {
		this.addWareHoseCount = addWareHoseCount;
	}

	public Map<String, String> getOrderData() {
		return orderData;
	}

	public void setOrderData(Map<String, String> orderData) {
		this.orderData = orderData;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public void setSsje(String ssje) {
		this.ssje = ssje;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCjCount(String cjCount) {
		this.cjCount = cjCount;
	}


	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}


	public void setTabNumber(String number) {
		this.tabNumber = number;
	}


	public void setFoodMenuId(String foodMenuId) {
		this.foodMenuId = foodMenuId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	
}
