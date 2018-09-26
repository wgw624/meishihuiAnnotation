<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="popup_main" >
	<div>
		<table class="commonTabNoBorder">
			<tr>
				<td>消费类型</td>
				<td>
					<s:select list="#{'0':'买菜','1':'水费','2':'电费','3':'燃气费','4':'其他'}" name="expenseType" theme="simple"  ></s:select>
				</td>
			</tr>
			
			<tr>
				<td>消非金额</td>
				<td class="errorInfTdPar">
					<input type="text" id = "expenseMoney" onblur="JsUtil.input.onblurValidateNum('expenseMoney','sureExpenseBtn')">
					<span id="expenseMoney_span" class="errorInfTd"></span>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<s:textarea theme="simple" id="comments" value="无"></s:textarea>
				</td>
			</tr>
		</table>
	</div>
	<div class="popup_bottom_div line_H30">
		<input type="button" class="commonBtn" id='sureExpenseBtn' value="确定" onclick="ChargeManage.sureExpense()">
	</div>
</div>