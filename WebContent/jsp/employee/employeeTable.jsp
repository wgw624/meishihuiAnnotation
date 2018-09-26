<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="commonTab">
		<tr>
			<th>姓名</th>
			<th>本月假期</th>
			<th>请假天数</th>
			<th>上月工资</th>
			<th>操作</th>
		</tr>
		<s:set name='empMap' value="#askForLeave"></s:set>
		<s:iterator value="listEmployee" id="emp">
			<tr>
				<td>
					<input type="hidden" value='<s:property value="#emp.id"/>' id="empId">  
					<input type="hidden" value='<s:property value="#emp.name"/>' id="empName">
					<s:property value="#emp.name"/>
				</td>
				<td><s:property value="#emp.holidays"/> </td>
				
				<td>
					<s:if test="#askForLeave[#emp.id] == null">
						0
					</s:if>
					<s:else>
						<s:property value="#askForLeave[#emp.id]"/>
					</s:else>
				</td>
				<td>
					<s:if test="#isPayOffMap[#emp.id] == null">
						未发
					</s:if>
					<s:else>
						<s:property value="#isPayOffMap[#emp.id]"/>
					</s:else>
				</td>
				<td>
					<input type="button" value="请假" onclick='Employee.manage.askForLeave(<s:property value= "#emp.id" />,"<s:property value="#emp.name"/>")'>
					<input type="button" value="发工资" onclick="Employee.manage.payOff(<s:property value= "#emp.id" />)">
					<input type="button" value="编辑" onclick="Employee.manage.editEmployee(<s:property value= "#emp.id" />)">  
				</td>
			</tr>
		</s:iterator>
	</table>