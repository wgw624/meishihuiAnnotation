<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

   <div class="orderMenuMain" id = "orderMenuMain">
	<div class="pt_10">
		<div class="float_left ml_20 text_center">
			<a onclick="OrderSys.MenuManage.openOrderMenuPage('0')" id="orderFood" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon5.png" width="52px" height="52px" />
				</span>
				<span class="block">点餐</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="OrderSys.MenuManage.openOrderMenuPage('1')" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">菜单查看</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="OrderSys.TableManage.newAddTable()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">新增桌号</span>
			</a>
		</div>
		<div class="float_left ml_20 text_center">
			<a onclick="OrderSys.OrderManage.waitSettle()" class="common_a">
				<span class="orderMenuBack_span">
					<img src="../image/menu/icon1.png" width="52px" height="52px" />
				</span>
				<span class="block text_center">待结账</span>
			</a>
		</div>
	</div>
</div>

	
