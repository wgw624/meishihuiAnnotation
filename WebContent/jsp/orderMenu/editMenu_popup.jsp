<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="popup_main">
	<div class="line_H30">
		<input type="hidden" id="editFoodId">
		<span>菜名</span>
		<span><input type="text" readonly="readonly" id="editFoodName"></span>
	</div>
	<div class="line_H30">
		<span>价格</span>
		<span><input type="text" id="editFoodPrice" onblur="JsUtil.input.onblurValidateNum('editFoodPrice','updateBtn')"></span>
		<span id="editFoodPrice_span" class="errorInf"></span>
	</div>
	<div class="popup_bottom_div line_H30">
		<input type="button" onclick="OrderSys.MenuManage.updateFoodMenu()" id="updateBtn" value="确定">
	</div>
</div>
