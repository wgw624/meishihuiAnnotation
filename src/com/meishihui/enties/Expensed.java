package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 支出明细
 * @author lenovo_wgw
 *
 */
@Entity
@Table(name="expensed")
public class Expensed {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	
	@Column(name="expensedType")
	private String expensedType;//0 买菜  1水费  2 电费  3：燃气费     4 酒水饮料  5： 其他 
	
	@Column
	private String wareHoseDetailId;
	
	@Column
	private Double payMoney;
	
	@Column(name="_comments")
	private String comments;
	
	@Column
	private Date date;
	
	@Column
	private String operator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpensedType() {
		return expensedType;
	}
	public void setExpensedType(String expensedType) {
		this.expensedType = expensedType;
	}
	public String getWareHoseDetailId() {
		return wareHoseDetailId;
	}
	public void setWareHoseDetailId(String wareHoseDetailId) {
		this.wareHoseDetailId = wareHoseDetailId;
	}
	
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
	
}
