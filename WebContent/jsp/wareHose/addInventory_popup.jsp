<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="popup_main">
	<div class="text_center line_H30" id="wareHosename"></div>
	<div class="line_H30 mt_10">
		<span>数量</span>
		<s:hidden name="wareHoseId"></s:hidden>
		<span>
			<s:textfield name="pCount" class="w_120" onblur="JsUtil.input.onblurValidateNum('pCount','sureAddInvetoryBtn')"></s:textfield>
		</span>
		<span id="pCount_span" class="errorInf"></span>
	</div>
	<div class="line_H30 mt_10">
		<span>赠送</span>
		<span>
			<s:textfield name="giveAwayCount" class="w_120" value="0"></s:textfield>
		</span>
	</div>
	<div class="line_H30 mt_10">
		<span>备注</span>
		<span>
			<s:textarea name="comments" class="w_120" value="无"></s:textarea>
		</span>
	</div>
	<div class="popup_bottom_div line_H30 mt_10">
		<input type="button" class="commonBtn" id='sureAddInvetoryBtn' value="确定" onclick="WareHoseManage.warHose.sureAddInvertory()">
	</div>
</div>