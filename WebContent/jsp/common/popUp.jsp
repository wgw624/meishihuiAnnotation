<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator begin="1" end="3" id="number">
	<div class="popup_bg" id="popup_bg${number }">
		<div class="popup_win" id="popup_win${number }">
			<div class="popup_head" id="popup_head${number }">
				<span class="popup_head_text line_H36 ml_10" id="popup_head_text${number }"></span>
				<i class="popup_head_close" id="popup_head_close${number }"></i>
			</div>
			<div class="popup_content" id="popup_content${number }"></div>
		</div>
	</div>
</s:iterator>

<ul id="userDropDiv" class="userDropDiv">
	<li id="li_modifyPwd" onclick="User.Drop.openModifyPasswordWin();"><i class="userDItem_i1"></i>修改密码</li>
	<li id="li_logout" onclick="User.Drop.cancel();"><i class="userDItem_i2"></i>注销</li>
	<li id="li_exit" onclick="User.Drop.signOut();"><i class="userDItem_i3"></i>退出</li>
</ul>