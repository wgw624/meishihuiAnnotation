<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="mainContent">
	<div class="topBtnDiv">
		<span>开始日期</span>
		<input class="Wdate" id="startDate" type="text" value="" onclick="WdatePicker()">
		<span>结束时间</span>
		<input class="Wdate" type="text" id="endDate" onclick="WdatePicker()">
		<input type="button" onclick="ChargeManage.queryConsumOrder()" id="consumOrderQuery" value="查询">
		应收金额：<span><s:property value="#sumYSJE"/></span>
	</div>
	<div class="bodyData" id="consumOrder">
	
	</div>
</div>
<script>
console.log(JsUtil.Date.dateFormatStr(new Date()))
$("#startDate").val(JsUtil.Date.dateFormatStr(new Date()))
$("#endDate").val(JsUtil.Date.dateFormatStr(new Date()))
$("#consumOrderQuery").click();
</script>