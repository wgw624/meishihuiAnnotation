package com.meishihui.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meishihui.dao.TableNumberDao;
import com.meishihui.enties.TableNumber;
import com.meishihui.service.TableNumService;

@Service
public class TableNumberServiceImp implements TableNumService{

	@Autowired
	private TableNumberDao tableNumDao;

	@Override
	public Map<String, String> getMapTableNumName() {
		List<TableNumber> listTab = tableNumDao.getAllEntity();
		Map<String,String> mapTable = new HashMap<String,String>();
		if(listTab !=null && listTab.size()>0){
			for(TableNumber tab:listTab){
				mapTable.put(tab.getId(), tab.getNumber());
			}
		}
		return mapTable;
	}

}
