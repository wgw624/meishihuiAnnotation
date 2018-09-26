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
public class AskForLeave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	@Column
	private int employeeId;
	
	@Column
	private int askForleaveDays;
	
	@Column
	private int askForLeaveYear;
	
	@Column
	private int askForLeaveMonth;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private String comments; //记录请假的日期 和备注，多次记录中间 用  ； 分开
	
	
	public int getAskForLeaveMonth() {
		return askForLeaveMonth;
	}
	public void setAskForLeaveMonth(int askForLeaveMonth) {
		this.askForLeaveMonth = askForLeaveMonth;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
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
	public int getAskForleaveDays() {
		return askForleaveDays;
	}
	public void setAskForleaveDays(int askForleaveDays) {
		this.askForleaveDays = askForleaveDays;
	}
	public int getAskForLeaveYear() {
		return askForLeaveYear;
	}
	public void setAskForLeaveYear(int askForLeaveYear) {
		this.askForLeaveYear = askForLeaveYear;
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
