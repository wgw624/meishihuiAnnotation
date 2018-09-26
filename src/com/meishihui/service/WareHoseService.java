package com.meishihui.service;

import java.util.List;
import java.util.Map;

import com.meishihui.enties.AddWareHoseDetail;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.OrderFoodMenu;
import com.meishihui.enties.WareHose;

public interface WareHoseService{
	List<WareHose> getAllWareHose();
	/**
	 * 增加产品类型和库存
	 * @param hoseDetail
	 * @param isSaveWarehose 是否保存wareHose
	 */
	void saveWareHoseAndDetail(AddWareHoseDetail hoseDetail);
	void saveHoseDetailAndUpdateWareHose(AddWareHoseDetail hoseDatail);
	void updateHoseDetail(AddWareHoseDetail hoseDetail);
	void updateWareHoseAndDetail(WareHose wareHose,AddWareHoseDetail hoseDetail,Expensed expensed);
	void update(WareHose wareHose);
	void delete(WareHose wareHose);
	void saveWareHose(WareHose wareHose);
	List<WareHose> getWareHose(String hql,Object []object);
	/**
	 * 获取仓库明细
	 * @param hql
	 * @param object
	 * @return
	 */
	List<AddWareHoseDetail> getWareHoseDetail(String hql,Object []object);
	List<AddWareHoseDetail> getAllWareHoseDetail();
	AddWareHoseDetail getWareHoseDetailById(String id);
	WareHose getWareHoseById(String id);
	/**
	 * 由hoseDetail  得到wareHose 对象
	 * @param hoseDetail
	 * @return
	 */
	WareHose hoseDetailToHose(AddWareHoseDetail hoseDetail);
	Map<String,List<WareHose>> getWareHoseClassify();
	/**
	 * 增加库存
	 * @param id 商品id
	 * @param count 新增商品数量
	 */
	void addInvertory(AddWareHoseDetail hoseDetail);
}
