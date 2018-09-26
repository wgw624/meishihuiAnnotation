<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="showInfDiv">
	<div class="mr_10 float_left">
		<span>应收金额</span>
		<span><s:property value="#sumYSJE"/></span>
	</div>
	<div class="mr_10 float_left">	
		<span>实收金额：</span>
		<span><s:property value="#sumSSJE"/></span>
	</div>
	
	<div class="mr_10 float_left">	
		<span>现金金额：</span>
		<span><s:property value="#XJJE"/></span>
	</div>
	<div class="mr_10 float_left">	
		<span>支付宝金额：</span>
		<span><s:property value="#ZFBJE"/></span>
	</div>
	<div class="mr_10 float_left">	
		<span>微信金额：</span>
		<span><s:property value="#WXJE"/></span>
	</div>
	<div class="mr_10">	
		<span>刷卡金额：</span>
		<span><s:property value="#SKJE"/></span>
	</div>	
</div>
<div>
	<table class="commonTab">
		<tr>
			<th>座号</th>
			<th>应收金额</th>
			<th>实收金额</th>
			<th>支付方式</th>
			<th>下单时间</th>
			<th>结账时间</th>
			<th>操作人</th>
		</tr>

		<s:iterator id="data" value="listOrder">
			<tr>
				<td><s:property value="#data.tableId"/> </td>
				<td><s:property value="#data.YSJE "/></td>
				<td><s:property value="#data.SSJE "/></td>
				<td><s:property value="#data.payType"/></td>
				<td><s:date name="#data.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#data.settlAccountDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:property value="#data.operator"/></td>				
			</tr>
		</s:iterator>
	</table>

</div>