<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="waitSettlePage">
		<s:iterator id="waitSettle" value="waitSettleList">
			<div class="waitSettleTable">
				
				<div class="tableNumber">
					<s:property value="#tableMap[#waitSettle.tableId]"/>
				</div>
				
				<div class="waitSettleContent">
					<div>
						<span>消费金额</span>
						<span><s:property value="#waitSettle.YSJE"/> </span>
					</div>
					<div>
						<span>餐具数</span>
						<span><s:property value="#waitSettle.dinnerwareCount"/></span>
					</div>
					<div>
						<span>下单时间</span>
						<span><s:property value="#waitSettle.createDate"/></span>
					</div>
				</div>
				<div class="waitSettleBtn_div">
					<input type="button" value="编辑" onclick="OrderSys.OrderManage.editOrderDetail('${waitSettle.oid}');">
					<input type="button" value="结账" onclick="OrderSys.OrderManage.openOrderDetail('${waitSettle.oid}');">
				</div>
			</div>
		
		</s:iterator>
	</div>
</body>
</html>