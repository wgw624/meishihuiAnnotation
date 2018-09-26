<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table class="commonTab">
		<tr>
			<th>支出类型</th>
			<th>金额</th>
			<th>日期</th>
			<th>备注</th>
			<th>操作人</th>
		</tr>
		<s:iterator value="expenseList" var="expense">
			<tr>
				<td>
					<s:if test ='#expense.expensedType==0'>买菜 </s:if>
					<s:elseif test="#expense.expensedType==1">水费</s:elseif>
					<s:elseif test="#expense.expensedType==2">电费</s:elseif>
					<s:elseif test="#expense.expensedType==3">燃气费</s:elseif>
					<s:elseif test="#expense.expensedType==4">酒水饮料</s:elseif>
					<s:elseif test="#expense.expensedType==5">其他</s:elseif>
					<s:else>工资</s:else>
				</td>
				<td><s:property value="#expense.payMoney"/></td>
				<td><s:property value="#expense.date"/></td>
				<td><s:property value="#expense.comments"/></td>
				<td><s:property value="#expense.operator"/></td>
			</tr>
		</s:iterator>
</table>