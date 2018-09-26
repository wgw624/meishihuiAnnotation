package com.meishihui.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meishihui.dao.ExpenseDao;
import com.meishihui.enties.Expensed;
import com.meishihui.service.ExpenseService;

@Service
public class ExpenseServiceImp implements ExpenseService{
	
	@Autowired
	private ExpenseDao expenseDao;
	
	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	@Override
	public List<Expensed> queryExpenseRecord(String hql, Object[] object) {
		List<Expensed> expenseList = expenseDao.getEntityByHQL(hql, object);
		return expenseList;
	}

	@Override
	public Expensed queryExpenseByOrderId(String hoseDetailId) {
		String hql = "From Expense where wareHoseDetailId = ?";
		Object []objects = new Object[]{hoseDetailId};
		List<Expensed> expenseList = expenseDao.getEntityByHQL(hql, objects);
		if(expenseList != null && expenseList.size()>0){
			return expenseList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void saveExpense(Expensed expense) {
		expenseDao.saveOrUpdate(expense);
	}

	@Override
	public List<Expensed> queryAllExpenseRecord() {
		List<Expensed> expenseList = expenseDao.getAllEntity();
		return expenseList;
	}
	
}
