package com.meishihui.service;

import java.util.List;
import java.util.Set;

import com.meishihui.enties.Order;
import com.meishihui.enties.OrderFoodMenu;
import com.meishihui.enties.TableNumber;

public interface OrderMenuService {
	List<TableNumber> getTableNumberForIsUse(String isUse);
	void saveTable(TableNumber tableNumber);
	void submitOrder(Order order);
	Order getOrder(String menuStr,String tableNumber,int ysje,int cjCount,String user);
	Order modifyOrder(Order order,String menuStr);
	Order getOrderById(String orderId);
	void updateTable(String tableNumber,String isUse);
	/**
	 * 支付订单
	 * @param order
	 * @param tableId
	 * @param isUse
	 */
	void updateOrder(Order order,String tableId,String isUse);
	/**
	 * 获取订单列表
	 * @return
	 */
	List<Order> getOrderList(String hql,Object[]objects);
	/**
	 * 获取一个带结账账单
	 */
	Order getOneWaitSettle(String hql,Object[]objects);
	Order getOneWaitSettleLazyLoad(String id);
	Set<OrderFoodMenu> getFoodMenu(Order oldOrder,Order newOrder);
	
	void saveOrUpdate(Order order);
	/**
	 * 过滤不显示的订单食物列表
	 * @param order
	 * @return
	 */
	Order filtNoShowFood(Order order);
	/**
	 * 结账付款
	 * @param order 要付款的订单
	 * @param updateList 要更新的仓库列表
	 */
	void payOrder(Order order,Set<OrderFoodMenu> updateList);
}
