<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="popup_main calChildMaxWidth">
	<div class="mt_10">
		<span class="calBrotherMaxWidth titleInf">旧密码</span>
		<input type="text" id="oldPsd">
	</div>
	<div class="mt_10">
		<span class="calBrotherMaxWidth titleInf">新密码</span>
		<input type="text" id="newPsd">
	</div>
	<div class="mt_10">
		<span class="calBrotherMaxWidth titleInf">确认新密码</span>
		<input type="text" id="sureNewPsd">
	</div>
</div>
<div class="popup_bottom_div line_H30">
	<input type="button" class="commonBtn" id="modifyPsdBtn" value="确定" onclick="User.Drop.modifyPassword()">
</div>