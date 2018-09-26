<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<div>
		<input type="button" value="新增用户" onclick="User.newAddUser()">
		<input type="button" value="编辑" >
		<input type="button" value="删除" >
	</div>
	<div>
		 <s:include value="/jsp/userTable.jsp"></s:include>
	</div>
</div>