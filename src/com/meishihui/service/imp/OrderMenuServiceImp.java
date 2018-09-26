package com.meishihui.service.imp;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meishihui.dao.OrderDao;
import com.meishihui.dao.TableNumberDao;
import com.meishihui.dao.WareHoseDao;
import com.meishihui.enties.Order;
import com.meishihui.enties.OrderFoodMenu;
import com.meishihui.enties.TableNumber;
import com.meishihui.enties.WareHose;
import com.meishihui.service.OrderMenuService;
import com.meishihui.util.CommonUtil;

@Service
public class OrderMenuServiceImp implements OrderMenuService{

	@Autowired
	private TableNumberDao tableNumberDao;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private WareHoseDao wareHoseDao;
	

	/**
	 * isUse 0 未使用，1 使用中，all 查出全部桌号
	 */
	@Override
	public List<TableNumber> getTableNumberForIsUse(String isUse) {
		if("all".equals(isUse)){
			return tableNumberDao.getAllEntity();
		}else{
			String hql = "FROM TableNumber where isUse = ?";
			Object []obj = new Object[]{isUse};
			return tableNumberDao.getEntityByHQL(hql, obj);
		}
	}

	@Override
	public void saveTable(TableNumber tableNumber) {
		tableNumberDao.addEntity(tableNumber);
	}

	@Override
	public void submitOrder(Order order) {
		orderDao.addEntity(order);
	}
	@Override
	public Order modifyOrder(Order order, String menuStr) {
		
		Set<OrderFoodMenu> setFood = order.getSetFoodMenu();
		Map<String,OrderFoodMenu> mapOid = new HashMap<String,OrderFoodMenu>();
		if(CommonUtil.isNotNullSet(setFood)){
			for(OrderFoodMenu ofm :setFood){
				mapOid.put(ofm.getFid(), ofm);
			}
		}
		
		Map<String,String> mapNewOid = new HashMap<String,String>();
		if(CommonUtil.isNotNullStr(menuStr)){
			String []menuStrArr = menuStr.split("@");
			for(int i=0;i<menuStrArr.length;i++){
				String menu = menuStrArr[i];
				String []oidNum = menu.split("_");
				mapNewOid.put(oidNum[0], oidNum[0]);
			}
		}
		
		//验证新增的菜
		if(CommonUtil.isNotNullMap(mapOid)){
			String []menuStrArr = menuStr.split("@");
			for(int i=0;i<menuStrArr.length;i++){
				String menu = menuStrArr[i];
				String []foodArr = menu.split("_");
				String newOid = foodArr[0];
				if(!mapOid.containsKey(newOid)){
					OrderFoodMenu bean = new OrderFoodMenu();
					bean.setCreateDate(new Date());
					Object []obj = new Object[]{foodArr[0]};
					
					WareHose food = (WareHose) wareHoseDao.uniqueResult("FROM WareHose WHERE id = ?", obj);
					bean.setFid(foodArr[0]);
					bean.setName(food.getName());
					bean.setPrice(food.getSellPrice());
					bean.setType(food.getWareHoseType());
					
					bean.setCountMenu(Integer.valueOf(foodArr[1]));
					order.getSetFoodMenu().add(bean);
				}else{//验证是否为不显示的菜
					OrderFoodMenu ofm = mapOid.get(newOid);
					ofm.setCountMenu(Integer.valueOf(foodArr[1]));
					if("0".equals(ofm.getIsShow())){
						ofm.setIsShow("1");
						ofm.setCountMenu(Integer.valueOf(foodArr[1]));
					}
				}
			}
		}else{
			return order;
		}
		//验证删除的菜
		if(mapNewOid!=null){
			for(Entry<String,OrderFoodMenu> enty:mapOid.entrySet()){
				String fid = enty.getKey();
				if(!mapNewOid.containsKey(fid)){
					enty.getValue().setIsShow("0");
				}
			}
		}
		return order;
	}
	@Override
	public Order getOrder(String menuStr, String tableNumber, int ysje, int cjCount, String user) {
		String []menuStrArr = menuStr.split("@");
		Order order = new Order();
		Set<OrderFoodMenu> setOrderFood = new HashSet<OrderFoodMenu>();
		for(int i=0;i<menuStrArr.length;i++){
			OrderFoodMenu bean = new OrderFoodMenu();
			String menu = menuStrArr[i];
			String []foodArr = menu.split("_");
			
			bean.setCreateDate(new Date());
			Object []obj = new Object[]{foodArr[0]};
			
			WareHose food = (WareHose) wareHoseDao.uniqueResult("FROM WareHose WHERE id = ?", obj);
			bean.setFid(foodArr[0]);
			bean.setName(food.getName());
			bean.setPrice(food.getSellPrice());
			bean.setType(food.getWareHoseType());
			
			bean.setCountMenu(Integer.valueOf(foodArr[1]));
			setOrderFood.add(bean);
		}
		order.setTableId(tableNumber);
		order.setDinnerwareCount(Integer.valueOf(cjCount));
		order.setIsSettleAcounts(0+"");
		order.setOperator(user);
		order.setSetFoodMenu(setOrderFood);
		order.setCreateDate(new Date());
		order.setYSJE(Integer.valueOf(ysje));
		order.setSSJE(Integer.valueOf(ysje));
		
		return order;
	}

	@Override
	public void updateTable(String tableNumber, String isUse) {
		Object []objs = new Object[]{tableNumber};
		TableNumber table = (TableNumber) tableNumberDao.uniqueResult("FROM TableNumber where id=?", objs);
		table.setIsUse(isUse);
		tableNumberDao.updateEntity(table);
		
	}

	@Override
	public List<Order> getOrderList(String hql,Object[]objects) {
		
		List<Order> orderList = orderDao.getEntityByHQL(hql, objects);
		return orderList;
	}

	@Override
	public Order getOneWaitSettle(String hql, Object[] objects) {
		Order order = (Order) orderDao.uniqueResult(hql, objects);
		return order;
	}

	@Override
	public Order getOneWaitSettleLazyLoad(String id) {
		Order order = orderDao.layLoadUniqueResult(id);
		order = filtNoShowFood(order);
		return order;
	}

	@Override
	public void updateOrder(Order order,String tableId,String isUse) {
		String hql = "FROM TableNumber WHERE id = ?";
		Object []objects = new Object[]{tableId};
		TableNumber table = (TableNumber) tableNumberDao.uniqueResult(hql, objects);
		table.setIsUse(isUse);
		
		tableNumberDao.updateEntity(table);
		orderDao.updateEntity(order);
	}

	@Override
	public Order getOrderById(String orderId) {
		String hql = "FROM Order where oid = ?";
		Order order = (Order) orderDao.uniqueResult(hql, new Object[]{orderId});
		return order;
	}

	@Override
	public Set<OrderFoodMenu> getFoodMenu(Order oldOrder, Order newOrder) {
		Map<String,OrderFoodMenu> oldMap = new HashMap<String,OrderFoodMenu>();
		Map<String,OrderFoodMenu> newMap = new HashMap<String,OrderFoodMenu>();
		Set<OrderFoodMenu> set = new HashSet<OrderFoodMenu>();
		Map<String,String> oldFoodMap = new HashMap<String,String>();
		
		for(OrderFoodMenu ord:oldOrder.getSetFoodMenu()){
			oldMap.put(ord.getOfid(), ord);
			oldFoodMap.put(ord.getFid(), ord.getOfid());
		}
		Set<String> newOfid = new HashSet<String>();
		
		for(OrderFoodMenu ord:newOrder.getSetFoodMenu()){
			String fId = ord.getFid();
			if(oldFoodMap.containsKey(fId)){
				String id = oldFoodMap.get(fId);
				int oldCount = oldMap.get(id).getCountMenu();
				
				ord.setOfid(oldFoodMap.get(fId));
				if(ord.getCountMenu() != oldCount){
					ord.setIsUpdate("1");
					ord.setOldCount(oldCount);
				}
				set.add(ord);
				newOfid.add(oldFoodMap.get(fId));
			}else{
				ord.setIsNewAdd("1");
				ord.setEditDate(new Date());
				set.add(ord);
			}
		}

		for(Entry<String,OrderFoodMenu> enty:oldMap.entrySet()){
			String key = enty.getKey();
			if(!newOfid.contains(key)){
				OrderFoodMenu ord = enty.getValue();
				ord.setIsShow("0");
				set.add(ord);
			}
		}
		return set;
	}

	@Override
	public void saveOrUpdate(Order order) {
		orderDao.saveOrUpdate(order);
	}

	@Override
	public Order filtNoShowFood(Order order) {
		Order rOrder = order;
		Set<OrderFoodMenu> set = rOrder.getSetFoodMenu();
		Iterator<OrderFoodMenu> it = set.iterator();
		System.out.println(set.size());
		for(int i=0;i<set.size();i++){
			OrderFoodMenu ofm = it.next();
			if("0".equals(ofm.getIsShow())){
				it.remove();
				i--;
			}
		}
		return rOrder;
	}

	@Override
	@Transactional
	public void payOrder(Order order, Set<OrderFoodMenu> updateList) {
		orderDao.saveOrUpdate(order);
		String tableId = order.getTableId();
		TableNumber table = (TableNumber) tableNumberDao.uniqueResult("from TableNumber where id = ?", new Object[]{tableId});
		table.setIsUse("0");
		tableNumberDao.updateEntity(table);
		for(OrderFoodMenu ord:updateList){
			String id = ord.getFid();
			int count = ord.getCountMenu();
			double price = ord.getPrice();
			
			WareHose hose = (WareHose) wareHoseDao.uniqueResult("FROM WareHose WHERE id = ?", new Object[]{id});
			if("4".equals(ord.getType())){
				int oldStock = hose.getStock();
				int newStock = oldStock-count;
				hose.setStock(newStock);
			}
			double money = hose.getSellSumMoney();
			money = money + count*price;
			hose.setSellSumMoney(money);
			wareHoseDao.updateEntity(hose);
		}
		
	}


	
}
