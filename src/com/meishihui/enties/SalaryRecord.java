package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SalaryRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int employeeId;
	
	@Column
	private double salarySum;
	
	@Column
	private int salaryYear;
	
	@Column
	private int salaryMonth;
	
	@Column
	private Date salaryDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getSalarySum() {
		return salarySum;
	}
	public void setSalarySum(double salarySum) {
		this.salarySum = salarySum;
	}
	public int getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(int salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public Date getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	public int getSalaryYear() {
		return salaryYear;
	}
	public void setSalaryYear(int salaryYear) {
		this.salaryYear = salaryYear;
	}
	
	
}
