<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="popup_main">
	<div class="popup_main_cont">
		<div class="payTableNumber">
			<s:hidden id="tableId" value="%{#order.tableId}"></s:hidden>
			<s:property  value="tableName"/>
		</div>
		<s:iterator id="food" value="#order.setFoodMenu">
			<div class="consumeFood">
				<span>
					<s:property value="#food.name"/>
				</span>
				<span>
					<s:property value="#food.price"/>
				</span>
			</div>
		</s:iterator>
		<div class="ysssdiv clearfix">
			<div style="float:left">
				<span>应收金额</span>
				<s:textfield id="ysje" readonly="true" value="%{#order.YSJE}"></s:textfield>
			</div>
			<div style="float:left">
				<span>实收金额</span>
				<s:textfield id="ssje" value="%{#order.SSJE}"></s:textfield>
			</div>
		</div>
		<div class="payType">
			<s:radio list="#{'1':'现金','2':'支付宝','3':'微信','4':'刷卡'}" value="1" name="paytype"></s:radio>
		</div>
	</div>
	<div class="popup_bottom_div">
		<input type="button" value="取消" onclick="">
		<input type="button" value="确认支付" onclick="OrderSys.OrderManage.payOrder('${order.oid}')">
		<input type="button" value="支付并打印" onclick="">
	</div>
</div>