package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int holidays;
	
	@Column
	private Double baseSalary;
	
	@Column()
	private Double perfectAttendanceAward;
	
	@Column(columnDefinition="Double default 0 comment '加班费'")
	private Double overtimePay;
	
	@Column
	private int isFixOverTimePay = 0;// 是否固定加班费，0 固定，1 计算
	
	@Column(columnDefinition="Int default 1 comment '0表示离职，1表示在职'")
	private Integer isWork;
	
	@Column
	private Date startDate;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	private double shoulePaySalary;
	
	
	public int getIsFixOverTimePay() {
		return isFixOverTimePay;
	}
	public void setIsFixOverTimePay(int isFixOverTimePay) {
		this.isFixOverTimePay = isFixOverTimePay;
	}
	public double getShoulePaySalary() {
		return shoulePaySalary;
	}
	public void setShoulePaySalary(double shoulePaySalary) {
		this.shoulePaySalary = shoulePaySalary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHolidays() {
		return holidays;
	}
	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}
	public Double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public Double getPerfectAttendanceAward() {
		return perfectAttendanceAward;
	}
	public void setPerfectAttendanceAward(Double perfectAttendanceAward) {
		this.perfectAttendanceAward = perfectAttendanceAward;
	}
	public Double getOvertimePay() {
		return overtimePay;
	}
	public void setOvertimePay(Double overtimePay) {
		this.overtimePay = overtimePay;
	}
	public Integer getIsWork() {
		return isWork;
	}
	public void setIsWork(Integer isWork) {
		this.isWork = isWork;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
