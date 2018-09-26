<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/commonPath.jspf" %>
<script src="${rootPath }/js/ChargeManage.js"></script>
<script src="${rootPath }/js/employee.js""></script>

<div class="orderMenuMain" id = "orderMenuMain">
	<div class="pt_10">
		<div class="float_left ml_20 text_center">
			<a onclick="ChargeManage.openTurnOverPage()" id="orderFood" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon5.png" width="52px" height="52px" />
				</span>
				<span class="block">营业额查询</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="ChargeManage.openExpensePage()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">支出查询</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="WareHoseManage.wareHoseDetail.purchaseQuery()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">进货查询</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="WareHoseManage.wareHoseDetail.NoDebtQuery()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">欠款查询</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="Employee.manage.employeeSalary()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">员工管理</span>
			</a>
		</div>
	</div>
</div>

	
