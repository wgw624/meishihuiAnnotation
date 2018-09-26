<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="popup_main">

	<div>
		<table class="commonTabNoBorder">
			<tr>
				<td>姓名</td>
				<td>
					<input type="hidden"  id="employeeId_popup" value='<s:property value="#emp.id"/>' />
					<input type="text"  id="employeeName" value='<s:property value="#emp.name"/>' />
				</td>
			</tr>
			<tr>
				<td>基本工资</td>
				<td>
					<input type="text"  id="baseSalary" value='<s:property value="#emp.baseSalary"/>'  onblur="JsUtil.input.onblurValidateNum('baseSalary','newAddEmployeeBtn')">
					<span id="baseSalary_span" class="errorInf"></span>
				</td>
			</tr>
			<tr>
				<td>假期</td>
				<td>
				<input type="text"  id="holidays" value='<s:property value="#emp.holidays"/>'  onblur="JsUtil.input.onblurValidateNum('holidays','newAddEmployeeBtn')">
				<span id="holidays_span" class="errorInf"></span>
				</td>
			</tr>
			<tr>
				<td>全勤奖</td>
				<td>
				<input type="text" id="perfectAttendanceAward" value='<s:property value="#emp.perfectAttendanceAward"/>'   onblur="JsUtil.input.onblurValidateNum('perfectAttendanceAward','newAddEmployeeBtn')">
				<span id="perfectAttendanceAward_span" class="errorInf"></span>
				</td>
			</tr>
			<s:if test="#emp.isFixOverTimePay ==0 ">
				<s:radio list="#{0:'是',1:'否' }" value='0'  onclick="Employee.manage.isShowOvertime()" name="isFixOvertimePay" label="固定加班费"></s:radio> 
			</s:if>
			<s:else>
				<s:radio list="#{0:'是',1:'否' }" value='1'  onclick="Employee.manage.isShowOvertime()" name="isFixOvertimePay" label="固定加班费"></s:radio> 
			</s:else>
			<tr id='overpay' <s:if test="#emp.isFixOverTimePay ==1"> style="display:none"</s:if> >
				<td>加班费<s:property value="#emp.isFixOverTimePay"/></td>
				<td>
					<input type="text" id="overtimePay" value='<s:property value="#emp.overtimePay"/>' onblur="JsUtil.input.onblurValidateNum('overtimePay','newAddEmployeeBtn')">
					<span id="overtimePay_span" class="errorInf"></span>
				</td>
			</tr>
			
		</table>
	</div>

	<div class="popup_bottom_div mt_10">
		<input type="button" class="commonBtn" onclick="Employee.manage.saveNewEmployee('edit')" id="newAddEmployeeBtn" value="确定">
	</div>
</div>
