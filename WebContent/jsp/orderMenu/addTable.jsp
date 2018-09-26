<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="ml_10">
	<div id="table" class="two_line_right">
		<div class="mt_10">
			<span class="alignRight">餐桌编号</span><s:textfield name="tabNumber" class="ml_10"></s:textfield> <br>
		</div>
		
	</div>
	<div class="mt_10">
		<input type='button' value="保存" onclick="OrderSys.TableManage.saveTable('tabNumber')">
		<input type='button' value="取消" onclick="cancle()">
	</div>
</div>

</body>
<script>
	JsUtil.PageSize.twoLineAlignRight();
</script>
</html>




