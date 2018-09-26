package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.opensymphony.xwork2.interceptor.annotations.Allowed;

@Entity
@Table(name="tableNumber")
public class TableNumber {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	
	@Column(name="tabNumber")
	private String number;
	
	@Column
	private String isUse;
	
	@Column
	private Date createDate;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIsUse() {
		return isUse;
	}
	
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
