<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<div>
		<table class="commonTabNoBorder">
			<tr>
				
				<td>姓名</td>
				<td>
					<input type="hidden" id="askForLeaveEmpId">
					<input type="text" id="askForLeaveEmpName" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>请假天数</td>
				<td>
					<input type="text" id="askForLeaveDays" onblur="JsUtil.input.onblurValidateInteger('askForLeaveDays','sureAskForLeaveBtn')" />
					<span id="askForLeaveDays_span" class="errorInf"></span>
				</td>
			</tr>
			<tr>
				<td>开始日期</td>
				<td><input class="Wdate" id="startDate" type="text" value="" onclick="WdatePicker()"></td>
			</tr>
			<tr>
				<td>结束日期</td>
				<td><input class="Wdate" id="endDate" type="text" value="" onclick="WdatePicker()"></td>
			</tr>
			<tr>
				<td><s:textarea name="comments" value="无" label="备注"></s:textarea> </td>
			</tr>
		</table>
	</div>
	<div class="popup_bottom_div line_H30 mt_10">
		<input type="button" value="确定" id="sureAskForLeaveBtn" class="commonBtn" onclick="Employee.manage.sureAskForLeave()">
	</div>
</div>
