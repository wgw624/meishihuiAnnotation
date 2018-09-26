package com.meishihui.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meishihui.dao.AskForLeaveDao;
import com.meishihui.dao.EmployeeDao;
import com.meishihui.dao.ExpenseDao;
import com.meishihui.dao.SalaryRecordDao;
import com.meishihui.enties.AskForLeave;
import com.meishihui.enties.Employee;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.SalaryRecord;
import com.meishihui.service.EmployeeService;
import com.meishihui.util.CommonUtil;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private AskForLeaveDao askForLeaveDao;
	
	@Autowired
	private SalaryRecordDao srd;
	
	@Autowired
	private ExpenseDao expenseDao;
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeDao.addEntity(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.updateEntity(employee);
	}
	@Override
	public List<Employee> queryAllEmployee() {
		return employeeDao.getAllEntity();
	}

	@Override
	public void delEmployee(Employee employee) {
		employeeDao.deleteEntity(employee);
		
	}

	@Override
	public Map<Integer, Integer> getEmployeeAskForleaveInf(int year, int month) {
		
		int tempYear = CommonUtil.getCurrentDateYear();
		int tempMonth = CommonUtil.getCurrentDateMonth();
		
		if(year != 0){
			tempYear = year;
		}
		if(month !=0){
			tempMonth = month;
		}
		
		String hql = "from AskForLeave where askForLeaveYear = ? and askForLeaveMonth = ? ";
		List<AskForLeave> askForLeaveList = askForLeaveDao.getEntityByHQL(hql, new Object[]{tempYear,tempMonth});
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		if(CommonUtil.isNotNullList(askForLeaveList)){
			for(AskForLeave enty:askForLeaveList){
				if(map.containsKey(enty.getEmployeeId())){
					int num = map.get(enty.getEmployeeId());
					num = num + enty.getAskForleaveDays();
					map.put(enty.getEmployeeId(),num);
				}else{
					map.put(enty.getEmployeeId(),enty.getAskForleaveDays());
				}
			}
		}
		return map;
	}

	@Override
	public void sureAskForLeave(AskForLeave askForLeave) {
		askForLeaveDao.addEntity(askForLeave);
		
	}

	@Override
	public Employee getEmployeeById(int id) {
		String hql = "From Employee where id = ?";
		Employee employee = (Employee) employeeDao.uniqueResult(hql, new Object[]{id});
	
		return employee;
	}

	@Override
	public int getCurEmployeeAskForLeve(int empId) {
		int year = CommonUtil.getCurrentDateYear();
		int month = CommonUtil.getCurrentDateMonth();
		if(month == 1){
			year = year -1;
			month = 12;
		}else{
			month = month -1;
		}
		String hql = "from AskForLeave where employeeId = ? and askForLeaveYear = ? and askForLeaveMonth = ? ";
		List<AskForLeave> askForLeaveList = askForLeaveDao.getEntityByHQL(hql, new Object[]{empId,year,month});
		int askForLeaveDays = 0;
		if(CommonUtil.isNotNullList(askForLeaveList)){
			for(AskForLeave afl:askForLeaveList){
				askForLeaveDays = askForLeaveDays + afl.getAskForleaveDays();
			}
		}
		
		return askForLeaveDays;
	}
	
	@Override
	@Transactional
	public void surePayOff(SalaryRecord sr, Expensed expense) {
		srd.addEntity(sr);
		expenseDao.addEntity(expense);
	}

	@Override
	public Map<Integer, String> isPayOff(int year, int month) {
		
		if(year == 0 || month ==0){
			year = CommonUtil.getCurrentDateYear();
			month = CommonUtil.getCurrentDateMonth();
		}
		
		if(month == 1){
			month = 12;
			year = year-1;
		}else{
			month = month -1;
		}
		
		Map<Integer,String> retMap = new HashMap<Integer,String>();
		
		String hql = "From SalaryRecord where salaryYear = ? and salaryMonth = ?";
		List<SalaryRecord> srList = srd.getEntityByHQL(hql, new Object[]{year,month});
		if(CommonUtil.isNotNullList(srList)){
			for(SalaryRecord sr:srList){
				retMap.put(sr.getEmployeeId(),"ÒÑ·¢");
			}
		}
		return retMap;
	}

}
