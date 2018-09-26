package com.meishihui.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**订单**/
@Entity
@Table(name="OrderFoodMenu")
public class OrderFoodMenu {
	
	@Column(name="ofid")
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String ofid;
	
	@Column(name="fId")
	private String fid;
	
	@Column(name="foodType")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="countMenu")
	private int countMenu;
	
	@Column(name="createDate")
	private Date createDate;
	
	@Column(name="editDate")
	private Date editDate; 
	
	@Column(name="isShow")
	private String isShow;//是否显示菜，1显示； 0 不显示，默认1'
	
	@Column(name="isNewAdd")
	private String isNewAdd;//comment '是否事新增菜'
	
	@Column(name="oldCount")
	private int oldCount;//
	
	@Column(name="isUpdate")
	private String isUpdate;
	
	
	public int getOldCount() {
		return oldCount;
	}
	public void setOldCount(int oldCount) {
		this.oldCount = oldCount;
	}
	public String getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsNewAdd() {
		return isNewAdd;
	}
	public void setIsNewAdd(String isNewAdd) {
		this.isNewAdd = isNewAdd;
	}
	public String getOfid() {
		return ofid;
	}
	public void setOfid(String ofid) {
		this.ofid = ofid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getCountMenu() {
		return countMenu;
	}
	public void setCountMenu(int countMenu) {
		this.countMenu = countMenu;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
