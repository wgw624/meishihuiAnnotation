<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<table class="commonTab">
		<tr>
			<th>名称</th>
			<th>数量</th>
			<th>进价</th>
			<th>售价</th>
			<th>进货时间</th>
			<th>所需金额</th>
			<th>是否结账</th>
			<th>
				<s:if test="#queryType == 'debt'">
					还款操作
				</s:if>
				<s:else>
					下单操作
				</s:else>
			</th>
		</tr>
		<s:iterator value="listHoseDetail" var="hoseDetail">
			<tr>
				<td><s:property value="#hoseDetail.name" /> </td>
				<td><s:property value="#hoseDetail.addStockCount" /> </td>
				<td><s:property value="#hoseDetail.purchasePrice" /> </td>
				<td><s:property value="#hoseDetail.sellPrice" /> </td>
				<td><s:property value="#hoseDetail.date" /> </td>
				<td><s:property value="#hoseDetail.shouldPaySumMoney" /> </td>
				<td>
					<s:if test="#hoseDetail.isSettle == 0">
						否
					</s:if>
					<s:elseif test="#hoseDetail.isSettle == 1">
						是
					</s:elseif>
					<s:else>
						未结清
					</s:else>
				</td>
				<td>
					<s:if test="#queryType == 'debt'">
						<input type="button" value="还款" onclick="WareHoseManage.wareHoseDetail.openPaymentHtml('<s:property value="#hoseDetail.id" />')">
					</s:if>
					<s:else>
						<s:property value="#hoseDetail.orderOperator" /> 
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>

