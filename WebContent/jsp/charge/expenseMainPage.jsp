<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<div class ="mt_10 mb_10">
		<span>时间</span>
		<input class="Wdate" id="startDate" type="text" value="" onclick="WdatePicker()">-
		<input class="Wdate" id="endDate" type="text" value="" onclick="WdatePicker()">
		<span>支出类型</span>
		<s:select list="#{'-1':'全部','0':'买菜','1':'水费','2':'电费','3':'燃气费','4':'酒水饮料','5':'其他','6':'工资'}" id="expenseType" ></s:select>
		<input type="button" value="查询" onclick="ChargeManage.queryExpenseRecord()">
		<input type="button" value="新增支出" onclick="ChargeManage.addExpenseRecord()">
		<span>支出总金额：</span>
		<span id="expenseTotalMoney"><s:property value="#expenseTotalMoney"/> </span>
	</div>
	<div id="expenseRecordTabel">
		<s:include value="expenseTable.jsp"></s:include>
	</div>
</div>