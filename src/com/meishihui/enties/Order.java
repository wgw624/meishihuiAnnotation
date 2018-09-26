package com.meishihui.enties;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="t_order")
public class Order {
	@Column(name="_oid")
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String oid;
	
	@Column(name="tableId")
	private String tableId;
	
	@Column(name="operator")
	private String operator;
	
	@Column(name="isSettleAcounts")
	private String isSettleAcounts; //0 表示未结账，1 表示已结账
	
	@Column(name="dinnerwareCount")
	private Integer dinnerwareCount;//餐具
	
	@Column(name="YSJE")
	private double YSJE;
	
	@Column(name="SSJE")
	private double SSJE;
	
	@Column(name="createDate")
	private Date createDate;
	
	@Column(name="settlAccountDate")
	private Date settlAccountDate;
	
	@Column(name="payType")
	private String payType;
	
/*	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=OrderFoodMenu.class)*/
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY )
	@JoinColumn(name="oid")
	private Set<OrderFoodMenu> setFoodMenu = new HashSet<OrderFoodMenu>();
	
	
	public Date getSettlAccountDate() {
		return settlAccountDate;
	}
	public void setSettlAccountDate(Date settlAccountDate) {
		this.settlAccountDate = settlAccountDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIsSettleAcounts() {
		return isSettleAcounts;
	}
	public void setIsSettleAcounts(String isSettleAcounts) {
		this.isSettleAcounts = isSettleAcounts;
	}
	public Integer getDinnerwareCount() {
		return dinnerwareCount;
	}
	public void setDinnerwareCount(Integer dinnerwareCount) {
		this.dinnerwareCount = dinnerwareCount;
	}
	public double getYSJE() {
		return YSJE;
	}
	public void setYSJE(double ySJE) {
		YSJE = ySJE;
	}
	public double getSSJE() {
		return SSJE;
	}
	public void setSSJE(double sSJE) {
		SSJE = sSJE;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<OrderFoodMenu> getSetFoodMenu() {
		return setFoodMenu;
	}
	public void setSetFoodMenu(Set<OrderFoodMenu> setFoodMenu) {
		this.setFoodMenu = setFoodMenu;
	}
	
	

}
