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
	 * ֧������
	 * @param order
	 * @param tableId
	 * @param isUse
	 */
	void updateOrder(Order order,String tableId,String isUse);
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	List<Order> getOrderList(String hql,Object[]objects);
	/**
	 * ��ȡһ���������˵�
	 */
	Order getOneWaitSettle(String hql,Object[]objects);
	Order getOneWaitSettleLazyLoad(String id);
	Set<OrderFoodMenu> getFoodMenu(Order oldOrder,Order newOrder);
	
	void saveOrUpdate(Order order);
	/**
	 * ���˲���ʾ�Ķ���ʳ���б�
	 * @param order
	 * @return
	 */
	Order filtNoShowFood(Order order);
	/**
	 * ���˸���
	 * @param order Ҫ����Ķ���
	 * @param updateList Ҫ���µĲֿ��б�
	 */
	void payOrder(Order order,Set<OrderFoodMenu> updateList);
}
