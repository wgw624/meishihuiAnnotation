<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="popup_main calChildMaxWidth">

	<div>
		<table class="commonTabNoBorder">
			<tr>
				<td>姓名</td>
				<td><input type="text"  id="employeeName"></td>
			</tr>
			<tr>
				<td>基本工资</td>
				<td>
				<input type="text"  id="baseSalary" onblur="JsUtil.input.onblurValidateNum('baseSalary','newAddEmployeeBtn')">
				<span id="baseSalary_span" class="errorInf"></span>
				</td>
			</tr>
			<tr>
				<td>假期</td>
				<td>
					<input type="text"  id="holidays" onblur="JsUtil.input.onblurValidateNum('holidays','newAddEmployeeBtn')">
					<span id="holidays_span" class="errorInf"></span>
				</td>
			</tr>
			<tr>
				<td>全勤奖</td>
				<td>
					<input type="text" id="perfectAttendanceAward"  onblur="JsUtil.input.onblurValidateNum('perfectAttendanceAward','newAddEmployeeBtn')">
					<span id="perfectAttendanceAward_span" class="errorInf"></span>
				</td>
			</tr>
	
			<s:radio list="#{0:'是',1:'否' }" value="0" onclick="Employee.manage.isShowOvertime()" name="isFixOvertimePay" label="固定加班费"></s:radio> 
			
			<tr id='overpay'>
				<td>加班费</td>
				<td>
					<input type="text" id="overtimePay"  onblur="JsUtil.input.onblurValidateNum('overtimePay','newAddEmployeeBtn')">
					<span id="overtimePay_span" class="errorInf"></span>
				</td>
			</tr>
			
		</table>
	</div>

	<div class="popup_bottom_div mt_10">
		<input type="button" class="commonBtn" onclick="Employee.manage.saveNewEmployee('add')" id="newAddEmployeeBtn" value="确定">
	</div>
</div>
