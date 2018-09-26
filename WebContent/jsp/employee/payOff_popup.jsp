<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="popup_main">
	<div>
		<table class="commonTabNoBorder">
			<tr>
				<td>姓名</td>
				<td>
					<input type="hidden" id="empId_popup" value='<s:property value="#employee.id"/>'>
					<input type="text" id="empName_popup" value='<s:property value="#employee.name"/>' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>工资日期</td>
				<td>
					<input type="text" id="salaryYear" style="width:60px;" value='<s:property value="#salaryYear"/>' readonly="readonly"> 年
					<input type="text" id="salaryMonth" style="width:60px;" value='<s:property value="#salaryMonth"/>' readonly="readonly" > 月
				</td>
			</tr>
			<tr>
				<td>基本工资</td>
				<td>
					<input type="text" id="baseSalary" value='<s:property value="#employee.baseSalary"/>' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>请假天数</td>
				<td>
					<input type="text" id="leaveDays" value='<s:property value="#leaveDays"/>' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>加班费</td>
				<td>
					<input type="text" id="overtiemPay" value='<s:property value="#employee.overtimePay"/>' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>全勤奖</td>
				<td>
					<input type="text" id="perfectAward" value='<s:property value="#employee.perfectAttendanceAward" />' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>实发工资</td>
				<td>
					<input type="text" id="salarySum" value='<s:property value="#employee.shoulePaySalary" />'>
				</td>
			</tr>
		</table>
	</div>
	<div class="popup_bottom_div mt_10">
		<input type="button" class="commonBtn" onclick="Employee.manage.surePayOff()" id="payOffBtn" value="确定">
	</div>
</div>


