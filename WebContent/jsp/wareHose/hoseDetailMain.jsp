<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class ="mt_10 mb_10">
	<s:if test="#queryType == 'debt'">
		<span>总欠款</span>
		<span><s:property value="#debtSumMoney"/> </span>
	</s:if>
	<s:else>
		<s:select list="#{'0':'未结账','1':'已结账','2':'未结清'}" headerKey="2" headerValue="全部" label="是否结账" id="isSettleId"></s:select>
		<input type="button" value="查询" onclick = "WareHoseManage.wareHoseDetail.purchaseQuery('isSettleId')">
	</s:else>
</div>
<div id="hoseDetailTable">
	<s:include value="hoseDetailTable.jsp"></s:include>
</div>