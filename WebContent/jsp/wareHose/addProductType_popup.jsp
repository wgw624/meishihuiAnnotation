<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="popup_main calChildMaxWidth">
	<div class="line_H30 mt_10">
		<s:hidden name="foodType_popup" value="#foodType"></s:hidden>
		<span class="calBrotherMaxWidth titleInf">产品名称</span>
		<span>
			<s:textfield name="p_name" class="w_120"></s:textfield>
		</span>
	</div>
	<div class="line_H30 mt_10">
		<span class="calBrotherMaxWidth titleInf">产品类型</span>
		<span>
			<s:if test="#foodType == 4">
				<s:select list="#{'4':'酒水饮料'}" name="p_TypeId" listKey="key" listValue="value" value="4"></s:select>
			</s:if>
			<s:if test="#foodType == 3">
				<s:select list="#{'3':'凉菜拉面'}" name="p_TypeId" listKey="key" listValue="value" value="3"></s:select>
			</s:if>
			<s:if test="#foodType == 2">
				<s:select list="#{'2':'素菜'}" name="p_TypeId" listKey="key" listValue="value" value="2"></s:select>
			</s:if>
			
			<s:if test="#foodType == 1">
				<s:select list="#{'1':'荤菜'}" name="p_TypeId" listKey="key" listValue="value" value="1"></s:select>
			</s:if>
		</span>
	</div>
	<s:if test="#foodType == 4">
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">产品进价</span>
			<span>
				<s:textfield name="p_purchasePrice" class="w_120" onblur="JsUtil.input.onblurValidateNum('p_purchasePrice','addProductBtn')"></s:textfield>
			</span>
			<span id="p_purchasePrice_span" class="errorInf"></span>
		</div>
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">产品售价</span>
			<span>
				<s:textfield name="p_sellPrice" class="w_120" onblur="JsUtil.input.onblurValidateNum('p_sellPrice','addProductBtn')"></s:textfield>
			</span>
			<span id="p_sellPrice_span" class="errorInf"></span>
		</div>
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">产品数量</span>
			<span>
				<s:textfield name="p_Count" class="w_120" onblur="JsUtil.input.onblurValidateInteger('p_Count','addProductBtn')"></s:textfield>
			</span>
			<span id="p_Count_span"  class="errorInf"></span>
		</div>
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">赠送</span>
			<span>
				<s:textfield name="p_giveawayCount" class="w_120" value="0" onblur="JsUtil.input.onblurValidateInteger('p_giveawayCount','addProductBtn')"></s:textfield>
			</span>
			<span id="p_giveawayCount_span"  class="errorInf"></span>
		</div>
	</s:if>
	<s:else>
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">产品售价</span>
			<span>
				<s:textfield name="p_sellPrice" class="w_120" onblur="JsUtil.input.onblurValidateNum('p_sellPrice','addProductBtn')"></s:textfield>
			</span>
			<span id="p_sellPrice_span" class="errorInf"></span>
		</div>
	</s:else>
	<s:if test="#foodType == 4">
		<div class="line_H30 mt_10">
			<span class="calBrotherMaxWidth titleInf">备注</span>
			<span>
				<s:textarea name="p_description" class="w_120" value="无"></s:textarea>
			</span>
		</div>
	</s:if>
</div>
<div class="popup_bottom_div line_H30">
	<input type="button" class="commonBtn" id="addProductBtn" value="确定" onclick="WareHoseManage.warHose.sureAddProductType()">
</div>
