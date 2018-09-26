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
	 * ���Ӳ�Ʒ���ͺͿ��
	 * @param hoseDetail
	 * @param isSaveWarehose �Ƿ񱣴�wareHose
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
	 * ��ȡ�ֿ���ϸ
	 * @param hql
	 * @param object
	 * @return
	 */
	List<AddWareHoseDetail> getWareHoseDetail(String hql,Object []object);
	List<AddWareHoseDetail> getAllWareHoseDetail();
	AddWareHoseDetail getWareHoseDetailById(String id);
	WareHose getWareHoseById(String id);
	/**
	 * ��hoseDetail  �õ�wareHose ����
	 * @param hoseDetail
	 * @return
	 */
	WareHose hoseDetailToHose(AddWareHoseDetail hoseDetail);
	Map<String,List<WareHose>> getWareHoseClassify();
	/**
	 * ���ӿ��
	 * @param id ��Ʒid
	 * @param count ������Ʒ����
	 */
	void addInvertory(AddWareHoseDetail hoseDetail);
}
