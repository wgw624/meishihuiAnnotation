package com.meishihui.service;

import java.util.List;

import com.meishihui.enties.Expensed;

public interface ExpenseService {
	List<Expensed> queryExpenseRecord(String sql,Object[] object);
	List<Expensed> queryAllExpenseRecord();
	Expensed queryExpenseByOrderId(String hoseDetailId);
	void saveExpense(Expensed expense);
}
