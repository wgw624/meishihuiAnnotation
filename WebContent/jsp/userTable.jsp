<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table>
	<tr>
		<th>显示名</th>
		<th>登陆名</th>
	</tr>
	<s:iterator value="listUser" id="listUser">
		<tr>
			<td>
				<s:property value="#listUser.showName"/>
			</td>
			<td>
				<s:property value="#listUser.loginName"/>
			</td>
		</tr>
	</s:iterator>
</table>