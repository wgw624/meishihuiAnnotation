package com.meishihui.service;

import java.util.List;
import java.util.Map;

import com.meishihui.enties.AskForLeave;
import com.meishihui.enties.Employee;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.SalaryRecord;

public interface EmployeeService {
	public void saveEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public List<Employee> queryAllEmployee();
	/**
	 * 根据员工ID 获取员工
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(int id);
	public void delEmployee(Employee employee);
	
	/**
	 * 确定员工请假申请
	 * @param empId
	 */
	public void sureAskForLeave(AskForLeave empId);
	
	/**
	 * 获取所有员工请假信息，根据月份年份
	 * @param year
	 * @param month
	 */
	public Map<Integer,Integer> getEmployeeAskForleaveInf(int year,int month);
	
	/**
	 * 根据获取员工Id 获取员工上个月得请假天数
	 * @param empId
	 * @return
	 */
	public int getCurEmployeeAskForLeve(int empId);
	
	/**
	 * 发放员工工资
	 * @param sr
	 * @param expense
	 */
	public void surePayOff(SalaryRecord sr,Expensed expense);
	
	/**
	 * 该日期上个月是否已发工资
	 * @param year
	 * @param month
	 * @return
	 */
	public Map<Integer,String> isPayOff(int year,int month);
}
