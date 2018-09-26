package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="AddWareHoseDetail")
public class AddWareHoseDetail {
	
	@Id
	@GeneratedValue(generator="assign")
	@GenericGenerator(name="assign",strategy="assigned")
	private String  id;
	
	@Column
	private String  wareHoseId;
	
	@Column
	private String  wareHoseType;
	
	@Column
	private String  name;
	
	@Column
	private Double  purchasePrice;
	
	@Column
	private Double  sellPrice;
	
	@Column
	private Integer addStockCount;
	
	@Column
	private Integer giveawayCount=0;
	
	@Column
	private Double  shouldPaySumMoney;
	
	@Column
	private Double  reallyPaySumMoney;
	
	@Column
	private Double  remainMoney;
	
	@Column
	private Integer  isSettle = 0;
	
	@Column
	private String  comments;
	
	@Column
	private Date    date;
	
	@Column
	private String  orderOperator;//下单者
	
	@Column
	private String  payOperator;//付款者
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWareHoseId() {
		return wareHoseId;
	}
	public void setWareHoseId(String wareHoseId) {
		this.wareHoseId = wareHoseId;
	}
	public String getWareHoseType() {
		return wareHoseType;
	}
	public void setWareHoseType(String wareHoseType) {
		this.wareHoseType = wareHoseType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Integer getAddStockCount() {
		return addStockCount;
	}
	public void setAddStockCount(Integer addStockCount) {
		this.addStockCount = addStockCount;
	}
	public Integer getGiveawayCount() {
		return giveawayCount;
	}
	public void setGiveawayCount(Integer giveawayCount) {
		this.giveawayCount = giveawayCount;
	}
	public Double getShouldPaySumMoney() {
		return shouldPaySumMoney;
	}
	public void setShouldPaySumMoney(Double shouldPaySumMoney) {
		this.shouldPaySumMoney = shouldPaySumMoney;
	}
	
	public Double getReallyPaySumMoney() {
		return reallyPaySumMoney;
	}
	public void setReallyPaySumMoney(Double reallyPaySumMoney) {
		this.reallyPaySumMoney = reallyPaySumMoney;
	}
	public Double getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}
	public Integer getIsSettle() {
		return isSettle;
	}
	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getOrderOperator() {
		return orderOperator;
	}
	public void setOrderOperator(String orderOperator) {
		this.orderOperator = orderOperator;
	}
	public String getPayOperator() {
		return payOperator;
	}
	public void setPayOperator(String payOperator) {
		this.payOperator = payOperator;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
