package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class WareHose {
	@Id
	@GeneratedValue(generator="assign")
	@GenericGenerator(name="assign",strategy="assigned")
	@Column(name="id")
	private String  id;
	
	@Column
	private String  wareHoseType;
	
	@Column
	private String  name;
	
	@Column
	private Double  purchasePrice;
	
	@Column
	private Double  sellPrice;
	
	@Column
	private Integer stock;
	
	@Column
	private Long    his_sumCount;
	
	@Column
	private Double sellSumMoney = 0.0;
	
	@Column
	private Double  paySumMoney = 0.0;	
	
	@Column
	private String  operator;
	
	@Column
	private Date    createDate;
	
	@Column
	private Integer isShow;
	
	
	public Double getSellSumMoney() {
		return sellSumMoney;
	}
	public void setSellSumMoney(Double sellSumMoney) {
		this.sellSumMoney = sellSumMoney;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Long getHis_sumCount() {
		return his_sumCount;
	}
	public void setHis_sumCount(Long his_sumCount) {
		this.his_sumCount = his_sumCount;
	}
	public Double getPaySumMoney() {
		return paySumMoney;
	}
	public void setPaySumMoney(Double paySumMoney) {
		this.paySumMoney = paySumMoney;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	
	
}
