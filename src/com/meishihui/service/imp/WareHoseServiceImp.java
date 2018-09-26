package com.meishihui.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meishihui.dao.AddWareHoseDetailDao;
import com.meishihui.dao.ExpenseDao;
import com.meishihui.dao.WareHoseDao;
import com.meishihui.enties.AddWareHoseDetail;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.WareHose;
import com.meishihui.service.WareHoseService;
import com.meishihui.util.CacheUtil;

@Service
public class WareHoseServiceImp implements WareHoseService {
	@Autowired
	private WareHoseDao hoseDao;
	@Autowired
	private AddWareHoseDetailDao hoseDetailDao;
	@Autowired
	private WareHoseService hoseService;
	@Autowired
	private ExpenseDao expenseDao;
		
	@Override
	public List<WareHose> getAllWareHose() {
		return hoseDao.getAllEntity();
	}

	@Override
	public void saveWareHoseAndDetail(AddWareHoseDetail hoseDetail) {

		WareHose wareHose = hoseDetailToHose(hoseDetail);
		hoseDao.addEntity(wareHose);
		hoseDetailDao.addEntity(hoseDetail);
	}
	@Override
	public void saveHoseDetailAndUpdateWareHose(AddWareHoseDetail hoseDetail) {
		String wareHoseId = hoseDetail.getWareHoseId();
		WareHose wareHose = hoseService.getWareHoseById(wareHoseId);
		
		Long his_sumstock = wareHose.getHis_sumCount();
		int stock = wareHose.getStock();
		stock = stock + hoseDetail.getAddStockCount();
		his_sumstock = his_sumstock + hoseDetail.getAddStockCount();
		wareHose.setStock(stock);
		wareHose.setHis_sumCount(his_sumstock);
		wareHose.setPurchasePrice(hoseDetail.getPurchasePrice());
		wareHose.setSellPrice(wareHose.getSellPrice());
		
		hoseDao.updateEntity(wareHose);
		hoseDetailDao.addEntity(hoseDetail);
	}
	@Override
	public void update(WareHose wareHose) {
		hoseDao.updateEntity(wareHose);
		
	}

	@Override
	public void delete(WareHose wareHose) {
		hoseDao.deleteEntity(wareHose);
		
	}

	@Override
	public List<WareHose> getWareHose(String hql, Object[] object) {
		

		return hoseDao.getEntityByHQL(hql, object);
	}	
	public List<WareHose> filtNoShowFoodMenu(List<WareHose> list){
		Iterator<WareHose> it = list.iterator();
		while(it.hasNext()){
			if(it.next().getIsShow() ==0){
				it.remove();
			}
		}
		
		return list;
	}
	@Override
	public Map<String, List<WareHose>> getWareHoseClassify() {
		List<WareHose> list = hoseDao.getAllEntity();
		list = filtNoShowFoodMenu(list);
		Map<String,List<WareHose>> map = new HashMap<String,List<WareHose>>();
		
		List<WareHose> listH = new ArrayList<WareHose>();
		List<WareHose> listS = new ArrayList<WareHose>();
		List<WareHose> listDrink = new ArrayList<WareHose>();
		List<WareHose> listL = new ArrayList<WareHose>();
			
		for(WareHose bean:list){
			String type = bean.getWareHoseType();
			switch(type){
				case "1":listH.add(bean);break;
				case "2":listS.add(bean);break;
				case "3":listL.add(bean);break;
				case "4":listDrink.add(bean);break;
			}
		}
		map.put("listH", listH);
		map.put("listS", listS);
		map.put("listDrink", listDrink);
		map.put("listL", listL);
		return map;
	}

	@Override
	public WareHose getWareHoseById(String id) {
		String hql = "FROM WareHose WHERE id = ?";
		Object []object = new Object[]{id};
		return (WareHose) hoseDao.uniqueResult(hql, object);
	}

	@Override
	public void addInvertory(AddWareHoseDetail hoseDetail) {
		String wareHoseId = hoseDetail.getWareHoseId();
		WareHose wareHose = hoseService.getWareHoseById(wareHoseId);
		
		hoseDetail.setId(Calendar.getInstance().getTimeInMillis()+"-hoseDetail");
		hoseDetail.setDate(new Date());
		hoseDetail.setName(wareHose.getName());
		hoseDetail.setSellPrice(wareHose.getSellPrice());
		hoseDetail.setPurchasePrice(wareHose.getPurchasePrice());
		hoseDetail.setWareHoseType(wareHose.getWareHoseType());
		hoseDetail.setShouldPaySumMoney(wareHose.getPurchasePrice() * hoseDetail.getAddStockCount());
		hoseDetail.setRemainMoney(wareHose.getPurchasePrice() * hoseDetail.getAddStockCount());
		hoseDetail.setReallyPaySumMoney((double)(0));
		hoseDetail.setOrderOperator(CacheUtil.getUser().getShowName());
		
		int stock = wareHose.getStock();
		stock = stock + hoseDetail.getAddStockCount();
		Long his_sumStock = wareHose.getHis_sumCount();
		his_sumStock = his_sumStock + hoseDetail.getAddStockCount();
		
		wareHose.setHis_sumCount(his_sumStock);
		wareHose.setStock(stock);
		
		hoseService.update(wareHose);
		hoseDetailDao.addEntity(hoseDetail);
		
	}

	public WareHose hoseDetailToHose(AddWareHoseDetail hoseDetail){
		
		String timeMIllis = Calendar.getInstance().getTimeInMillis()+"";
		WareHose wareHose = new WareHose();
		if("4".equals(hoseDetail.getWareHoseType())){
			hoseDetail.setId(timeMIllis+"-hoseDetail");
			hoseDetail.setWareHoseId(timeMIllis+"-hose");
			hoseDetail.setDate(new Date());
			hoseDetail.setShouldPaySumMoney(hoseDetail.getAddStockCount() * hoseDetail.getPurchasePrice());
			hoseDetail.setRemainMoney(hoseDetail.getAddStockCount() * hoseDetail.getPurchasePrice());
			hoseDetail.setReallyPaySumMoney(0.0);
			hoseDetail.setOrderOperator(CacheUtil.getUser().getShowName());
			wareHose.setHis_sumCount(Long.valueOf(hoseDetail.getAddStockCount()));
			wareHose.setPurchasePrice(hoseDetail.getPurchasePrice());
			wareHose.setStock(hoseDetail.getAddStockCount()+hoseDetail.getGiveawayCount());
		}
		
		
		wareHose.setId(timeMIllis+"-hose");
		wareHose.setWareHoseType(hoseDetail.getWareHoseType());
		wareHose.setName(hoseDetail.getName());
		wareHose.setSellPrice(hoseDetail.getSellPrice());
		wareHose.setOperator(hoseDetail.getOrderOperator());
		wareHose.setCreateDate(new Date());
		wareHose.setIsShow(1);
		return wareHose;
		
	}

	@Override
	public void saveWareHose(WareHose wareHose) {
		hoseDao.addEntity(wareHose);
	}

	@Override
	public List<AddWareHoseDetail> getWareHoseDetail(String hql, Object[] objects) {
		List<AddWareHoseDetail> listHoseDetail = hoseDetailDao.getEntityByHQL(hql, objects);
		return listHoseDetail;
	}

	@Override
	public List<AddWareHoseDetail> getAllWareHoseDetail() {
		List<AddWareHoseDetail> listHoseDetail = hoseDetailDao.getAllEntity();
		return listHoseDetail;
	}

	@Override
	public AddWareHoseDetail getWareHoseDetailById(String id) {
		String hql = "FROM AddWareHoseDetail WHERE id = ?";
		Object []objects = new Object[]{id};
		return (AddWareHoseDetail) hoseDetailDao.uniqueResult(hql, objects);
	}

	@Override
	public void updateHoseDetail(AddWareHoseDetail hoseDetail) {
		hoseDetailDao.updateEntity(hoseDetail);
	}


	@Override
	@Transactional
	public void updateWareHoseAndDetail(WareHose wareHose, AddWareHoseDetail hoseDetail,Expensed expensed) {
		hoseDao.updateEntity(wareHose);
		hoseDetailDao.updateEntity(hoseDetail);
		expenseDao.addEntity(expensed);
		
	}


}
