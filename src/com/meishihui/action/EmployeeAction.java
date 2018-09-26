package com.meishihui.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.AskForLeave;
import com.meishihui.enties.Employee;
import com.meishihui.enties.Expensed;
import com.meishihui.enties.SalaryRecord;
import com.meishihui.service.EmployeeService;
import com.meishihui.util.CacheUtil;
import com.meishihui.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@Namespace("/employee")
@ParentPackage("myInterceptor")
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{

	private Employee employee = new Employee();
	
	private int year;
	private int month;

	private String comments;
	
	
	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Autowired
	private EmployeeService employeeService;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Employee getModel() {
		return employee;
	}
	
	@Action(value="addOrEditEmployee",results={@Result(name="addSuccess",type="redirectAction",location="/employee_queryAllEmployeeInf")})
	public String addOrEditEmployee()throws Exception{
		if(employee.getIsWork() == 1){//1表示新增
			employee.setStartDate(new Date());
			employee.setIsWork(1);
			employeeService.saveEmployee(employee);
		}else{//修改
			int empId = employee.getId();
			Employee emp = employeeService.getEmployeeById(empId);
			emp.setBaseSalary(employee.getBaseSalary());
			emp.setHolidays(employee.getHolidays());
			emp.setIsFixOverTimePay(employee.getIsFixOverTimePay());
			emp.setOvertimePay(employee.getOvertimePay());
			emp.setPerfectAttendanceAward(employee.getPerfectAttendanceAward());
			employeeService.updateEmployee(emp);
		}
		
		return "addSuccess";
	}
	
	@Action(value="queryEditEmployee",results={@Result(name="queryEditEmployee",location="/jsp/employee/editEmployee_popup.jsp")})
	public String queryEditEmployee()throws Exception{
		Employee emp = employeeService.getEmployeeById(employee.getId());
		ActionContext.getContext().put("emp", emp);
		return "queryEditEmployee";
	}
	
	@Action(value="employee_queryAllEmployeeInf",results={@Result(name="queryAllEmployee",location="/jsp/charge/employeeSalary.jsp"),
														  @Result(name="retrunEmployeeTable",location="/jsp/employee/employeeTable.jsp")})
	public String queryAllEmployeeInf()throws Exception{

	    Map<Integer,Integer> mapYear = getMapYear(2016);
		List<Employee> listEmployee = employeeService.queryAllEmployee();
		Map<Integer,Integer> askMap = employeeService.getEmployeeAskForleaveInf(year, month);
		Map<Integer,String> isPayOffMap =employeeService.isPayOff(year, month);//上月工资是已发放
		
		ActionContext.getContext().put("listEmployee", listEmployee);
		ActionContext.getContext().put("askForLeave", askMap);
		ActionContext.getContext().put("year", mapYear);
		ActionContext.getContext().put("isPayOffMap", isPayOffMap);
		ActionContext.getContext().put("currentDateMonth", CommonUtil.getCurrentDateMonth());
		ActionContext.getContext().put("currentDateYear", CommonUtil.getCurrentDateYear());
		if(year !=0){
			return "retrunEmployeeTable";
		}
		return "queryAllEmployee";
	}
	
	@Action(value="sureAskForLeave", results={@Result(name="goEmployeePage",type="redirect",location="employee_queryAllEmployeeInf")})
	public String sureEmployeeAskForLeave()throws Exception{
		
		AskForLeave askForLeave = new AskForLeave();
		
		askForLeave.setEmployeeId(employee.getId());
		askForLeave.setAskForLeaveYear(CommonUtil.getCurrentDateYear(employee.getStartDate()));
		askForLeave.setAskForLeaveMonth(CommonUtil.getCurrentDateMonth(employee.getStartDate()));
		askForLeave.setStartDate(employee.getStartDate());
		askForLeave.setEndDate(employee.getEndDate());
		askForLeave.setAskForleaveDays(employee.getHolidays());
		askForLeave.setComments(comments);
		
		employeeService.sureAskForLeave(askForLeave);
		return "goEmployeePage";
	}
	
	@Action(value="payOff",results={@Result(name="payOff",location="/jsp/employee/payOff_popup.jsp")})
	public String payOff()throws Exception{
		int empId = employee.getId();
		Employee emp = employeeService.getEmployeeById(empId);
		int leaveDays = employeeService.getCurEmployeeAskForLeve(empId);
		double salsyr = getCurrentSalary(emp, leaveDays);
		emp.setShoulePaySalary(salsyr);
		int salaryYear = CommonUtil.getCurrentDateYear();
		int salaryMonth = CommonUtil.getCurrentDateMonth();
		if(salaryMonth == 1){
			salaryYear = salaryYear -1;
			salaryMonth = 12;
		}else{
			salaryMonth = salaryMonth-1;
		}
		ActionContext.getContext().put("salaryYear", salaryYear);
		ActionContext.getContext().put("salaryMonth", salaryMonth);
		ActionContext.getContext().put("employee", emp);
		ActionContext.getContext().put("leaveDays", leaveDays);
		
		return "payOff";
	}
	
	@Action(value="surePayOff")
	public String surePayOff()throws Exception{
		int empId = employee.getId();
		double money = employee.getShoulePaySalary();
		
		Expensed expense = new Expensed();
			expense.setExpensedType("6");
			expense.setComments(employee.getName());
			expense.setOperator(CacheUtil.getUser().getShowName());
			expense.setDate(Calendar.getInstance().getTime());
			expense.setPayMoney(money);
		
		SalaryRecord sr = new SalaryRecord();
			sr.setEmployeeId(empId);
			sr.setSalaryDate(Calendar.getInstance().getTime());
			sr.setSalaryYear(year);
			sr.setSalaryMonth(month);
			sr.setSalarySum(money);
			
		employeeService.surePayOff(sr, expense);
		
		return null;
	}
	
	private Map<Integer,Integer> getMapYear(int startYear){
		Map<Integer,Integer> retMap = new HashMap<Integer,Integer>();
		int curYear = CommonUtil.getCurrentDateYear();
		for(int i=startYear;i<curYear+3;i++){
			retMap.put(i, i);
		}
		return retMap;
	}
	
	private double getCurrentSalary(Employee emp,int leaveDays){
		double paySalary = 0;
		int y = CommonUtil.getCurrentDateYear();
		int m = CommonUtil.getCurrentDateMonth();
		if(m == 1){
			m =12;
			y = y-1;
		}
		int curSalaryMonthDays = CommonUtil.getDaysYearMonth(y, m);
		double basesalary = emp.getBaseSalary();
		double overTimePay = emp.getOvertimePay();
		double qqj = emp.getPerfectAttendanceAward();
		int flag = emp.getIsFixOverTimePay();
		int holidays = emp.getHolidays();
		if(flag == 0){
			if(leaveDays == 0){
				paySalary = basesalary + qqj + overTimePay*holidays;
			}else if(leaveDays<=holidays){
				paySalary = basesalary + qqj +overTimePay*(holidays-leaveDays);
			}else if(leaveDays > holidays){
				paySalary = basesalary - overTimePay*(leaveDays -holidays);
			}
		}else{
			
			if(leaveDays == 0){
				paySalary = basesalary + qqj + basesalary/(curSalaryMonthDays - holidays)*holidays;
			}else if(leaveDays<=holidays){
				paySalary = basesalary + qqj + basesalary/(curSalaryMonthDays - holidays)*(holidays-leaveDays);
			}else if(leaveDays > holidays){
				paySalary = basesalary - basesalary/(curSalaryMonthDays - holidays)*(leaveDays -holidays);
			}
		}
		
		return paySalary;
	}
}
