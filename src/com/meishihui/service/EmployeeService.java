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
	 * ����Ա��ID ��ȡԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(int id);
	public void delEmployee(Employee employee);
	
	/**
	 * ȷ��Ա���������
	 * @param empId
	 */
	public void sureAskForLeave(AskForLeave empId);
	
	/**
	 * ��ȡ����Ա�������Ϣ�������·����
	 * @param year
	 * @param month
	 */
	public Map<Integer,Integer> getEmployeeAskForleaveInf(int year,int month);
	
	/**
	 * ���ݻ�ȡԱ��Id ��ȡԱ���ϸ��µ��������
	 * @param empId
	 * @return
	 */
	public int getCurEmployeeAskForLeve(int empId);
	
	/**
	 * ����Ա������
	 * @param sr
	 * @param expense
	 */
	public void surePayOff(SalaryRecord sr,Expensed expense);
	
	/**
	 * �������ϸ����Ƿ��ѷ�����
	 * @param year
	 * @param month
	 * @return
	 */
	public Map<Integer,String> isPayOff(int year,int month);
}
