<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<div class="calChildMaxWidth">
		<input type="hidden" id="hoseDetailId" value="${hoseDetail.id}">
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">名称</span>
			<input type="text" id="hoseDetailName" readonly="readonly" value="${hoseDetail.name}">
		</div>
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">单价</span>
			<input type="text" id="hoseDetailPrice"  value="${hoseDetail.purchasePrice}" readonly="readonly">
		</div>
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">数量</span>
			<input type="text" id="hoseDetailNum" readonly="readonly"  value="${hoseDetail.addStockCount}">
		</div>
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">应付金额</span>
			<input type="text" id="hoseDetailSholdPayMoney" readonly="readonly"  value="${hoseDetail.shouldPaySumMoney}">
		</div>
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">实付金额</span>
			<input type="text" id="hoseDetailRelPayMoney"  value="${hoseDetail.shouldPaySumMoney}" onblur="JsUtil.input.onblurValidateNum('hoseDetailRelPayMoney','surePayMentBtn')" >
			<span id="hoseDetailRelPayMoney_span" class="errorInf"></span>
		</div>
		<div class="mt_10">
			<span class="calBrotherMaxWidth titleInf">备注</span>
			<s:textarea value="无" id="comments"></s:textarea>
		</div>
		<div class="mt_10">
			<s:radio name="isNoSettle" list="#{'1':'已结清','2':'未结清' }" value="1"></s:radio>
		</div>
	</div>
	
	<div class="popup_bottom_div">
		<input type="button" class="commonBtn" id="surePayMentBtn" value="确定" onclick="WareHoseManage.wareHoseDetail.surePayment('<s:property value='#hoseDetail.id'/>')">
	</div>
</div>
