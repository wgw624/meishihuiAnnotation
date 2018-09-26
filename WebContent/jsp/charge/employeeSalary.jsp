<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<span>年份</span><s:select name="year" list="#year"  value="#currentDateYear" ></s:select>
	<span>月份</span><s:select name="month" list="#{1:'一月',2:'二月',3:'三月',4:'四月',5:'五月',6:'六月',7:'七月',8:'八月',9:'九月',10:'十月',11:'十一月',12:'十二月'}"  value="#currentDateMonth" listKey="key" listValue="value"></s:select>
	<input type='button' value="查询" onclick="Employee.manage.queryEmployee()">
	<input type='button' value="新增员工" onclick="Employee.manage.addEmployee()">
</div>
<div id="employeeDiv">
	<s:include value="/jsp/employee/employeeTable.jsp"></s:include>
</div>