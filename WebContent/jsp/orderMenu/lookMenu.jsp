<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input type="hidden" id="hid_menu" value='<s:property value="type"/>' />

<div id="rr" class="line_H30 ml_5">
	<div class ="float_left mr_10">
	 	<span>桌号</span>
	 	<s:select list="listTab" id="tableId" listKey = "id" listValue="number" class="w_80"></s:select>
	</div>
	<div class="float_left mr_10">
		<span>餐具数</span>
		<i class="subtract_Icon float_l mt_4" id="subCJ" onclick="OrderSys.OrderManage.subtractCJ(this)"></i>
		<s:textfield class="w_30 p_0 text_center" id="cjCount" je="1" value="0" onblur="OrderSys.OrderManage.check()"></s:textfield>
		<i class="add_Icon float_l mt_4" id="addCJ" onclick="OrderSys.OrderManage.addCJ(this)"></i>
	</div>
	<div class="float_left mr_10">
		<span>凉数</span>
		<s:textfield class="w_30 p_0 text_center" id="lcId" readonly="true"></s:textfield>
		<span>荤菜</span>
		<s:textfield class="w_30 p_0 text_center" id="hcId" readonly="true"></s:textfield>
		<span>素菜</span>
		<s:textfield class="w_30 p_0 text_center" id="scId" readonly="true"></s:textfield>
	</div>
	<div class="float_left mr_10">
		<span>金额</span>
		<s:textfield class="w_50 p_0 text_center" id="jeId" readonly="true"></s:textfield>
		<span>点菜金额</span>
		<s:textfield class="w_50 p_0 text_center" id="dcjeId" readonly="true"></s:textfield>
		<span>酒水金额</span>
		<s:textfield class="w_50 p_0 text_center" id="jsjeId" readonly="true"></s:textfield>
	</div>
	<div class="float_right mr_10">
		<input type='button' id="orderBtn" onclick="OrderSys.OrderManage.submitOrder()" value="下单">
	</div>
	<div class="float_right mr_10 none">
		<s:hidden id="orderId"></s:hidden>
		<input type='button' id="orderSureBtn" onclick="OrderSys.OrderManage.sureEditOrder()" value="确认">
	</div>
</div>
<div id="foodMenu" class="ml_5">
	<div class="clearfix">
		<div class="float_left H_50B mr_10 cal_w">
			<div class="text_center head_bg line_H24 text_white">
				<span>荤菜类</span>
				<i class="addIcon_big float_right mr_10" onclick="WareHoseManage.warHose.addProductType(1)"></i>
			</div>
			<div class="over calChildMaxWidth bor mt_10 " id="huncaiDiv">
				<s:iterator id="li" value="listH" status="status">
					<div class="table_row float_left icon_span line_H30">
						<%-- <s:hidden name="%{id}" value="%{id}"></s:hidden> --%>
						<s:if test='#menuType == "0"'> 
							<span class="table_cell"><s:checkbox name="%{name}" id="%{id}" fieldValue="%{sellPrice}" onclick="OrderSys.OrderManage.check(this)"></s:checkbox></span>
						</s:if>
						<span class="table_cell calBrotherMaxWidth" id="name_hc_${status.count }"><s:property value="#li.name"/></span>
						<span class="table_cell" id="price_hc_${status.count }"><s:property value="#li.sellPrice"/></span>
						<s:if test='#menuType == "1"'> 
							<span class="table_cell W_40 mr_10" style="width:34px;">
								<i class="del_icon" title="删除" onclick="OrderSys.MenuManage.delFoodMenu(this,'<s:property value="#li.id"/>')"></i>
								<i class="edit_icon" title="编辑" onclick="OrderSys.MenuManage.editFoodMenu(this,'<s:property value="#li.id"/>','hc_${status.count }')"></i>
							</span>
						</s:if>
						<s:else>
							<span class="table_cell W_40 mr_10 span_hover" style="width:54px;">
								<i class="subtract_Icon float_l mt_4" onclick="OrderSys.OrderManage.subtractFood(this)"></i>
								<s:textfield class="w_20 p_0 text_center float_l foodCount" style="height:16px;" readonly="true" value="1"></s:textfield>
								<i class="add_Icon float_l mt_4"  onclick="OrderSys.OrderManage.addFood(this)" ></i>
							</span>
						</s:else>
					</div>
				</s:iterator>
			</div>			
		</div>
		
		<div class="float_left H_50B cal_w">
			<div class="text_center head_bg line_H24 text_white">
				<span>
					素菜类
				</span>
				<i class="addIcon_big float_right mr_10" onclick="WareHoseManage.warHose.addProductType(2)"></i>
			</div>
			<div class="cal_H over bor calChildMaxWidth mt_10" id="sucaiDiv">
				<s:iterator id="li" value="listS" status="status">
					<div class="table_row float_left icon_span line_H30">
						<s:if test='#menuType == "0"'> 
								<span class="table_cell"><s:checkbox name="%{name}" id="%{id}" fieldValue="%{sellPrice}" onclick="OrderSys.OrderManage.check(this)"></s:checkbox></span>
						</s:if>
						<span class="table_cell calBrotherMaxWidth" id="name_sc_${status.count }"><s:property value="#li.name"/></span>
						<span class="table_cell mr_20"id="price_sc_${status.count }"><s:property value="#li.sellPrice"/></span>
						
						<s:if test='#menuType == "1"'> 
							<span class="table_cell">
								<i class="del_icon" title="删除" onclick="OrderSys.MenuManage.delFoodMenu(this,'<s:property value="#li.id"/>')"></i>
								<i class="edit_icon" title="编辑" onclick="OrderSys.MenuManage.editFoodMenu(this,'<s:property value="#li.id"/>','sc_${status.count }')"></i>
							</span>
						</s:if>
						<s:else>
							<span class="table_cell W_40 mr_10 span_hover" style="width:54px;">
								<i class="subtract_Icon float_l mt_4"onclick="OrderSys.OrderManage.subtractFood(this)"></i>
								<s:textfield class="w_20 p_0 text_center float_l foodCount" style="height:16px;" readonly="true" value="1"></s:textfield>
								<i class="add_Icon float_l mt_4"  onclick="OrderSys.OrderManage.addFood(this)" ></i>
							</span>
						</s:else>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>
		
	<div class="clearfix">
		<div class="float_left H_50B cal_w mr_10">
			<div class="text_center head_bg line_H24 text_white">
				<span>凉菜类</span>
				<i class="addIcon_big float_right mr_10" onclick="WareHoseManage.warHose.addProductType(3)"></i>
			</div>
			<div class="cal_H_1 over mt_10 over bor calChildMaxWidth" id="liangcaiDiv">
					<s:iterator id="li" value="listL" status="status">
						<div class="table_row float_left icon_span line_H30">
							<s:if test='#menuType == "0"'> 
								<span class="table_cell"><s:checkbox name="%{name}" id="%{id}" fieldValue="%{sellPrice}" onclick="OrderSys.OrderManage.check(this)"></s:checkbox></span>
							</s:if>
							<span class="table_cell calBrotherMaxWidth" id="name_lc_${status.count }"><s:property value="#li.name"/></span>
							<span class="table_cell mr_20"id="price_lc_${status.count }"><s:property value="#li.sellPrice"/></span>
							<s:if test='#menuType == "1"'> 
								<span class="table_cell">
									<i class="del_icon" title="删除" onclick="OrderSys.MenuManage.delFoodMenu(this,'<s:property value="#li.id"/>')"></i>
									<i class="edit_icon" title="编辑" onclick="OrderSys.MenuManage.editFoodMenu(this,'<s:property value="#li.id"/>','lc_${status.count }')"></i>
								</span>
							</s:if>
							<s:else>
								<span class="table_cell W_40 mr_10 span_hover" style="width:54px;">
									<i class="subtract_Icon float_l mt_4"onclick="OrderSys.OrderManage.subtractFood(this)"></i>
									<s:textfield class="w_20 p_0 text_center float_l foodCount" style="height:16px;" readonly="true" value="1"></s:textfield>
									<i class="add_Icon float_l mt_4"  onclick="OrderSys.OrderManage.addFood(this)" ></i>
								</span>
							</s:else>
						</div>
					</s:iterator>
				</div>
		</div>
		<div class="float_left H_50B cal_w">
			<div class="text_center head_bg line_H24 text_white">
				<span>酒水类</span>
				<i class="addIcon_big float_right mr_10" onclick="WareHoseManage.warHose.addProductType(4)"></i>
			</div>
			<div class="W_100B H_100H over bor mt_10 calChildMaxWidth" id = "jiushuiDiv">
				<s:iterator id="li" value="listDrink" status="status">
					<div class="table_row float_left icon_span line_H30">
							<s:if test='#menuType == "0"'> 
								<span class="table_cell"><s:checkbox name="%{name}" id="%{id}" fieldValue="%{sellPrice}" onclick="OrderSys.OrderManage.check(this)"></s:checkbox></span>
							</s:if>
						<span class="table_cell calBrotherMaxWidth" id="name_js_${status.count }"><s:property value="#li.name"/></span>
						<span class="table_cell mr_20" id="price_js_${status.count }"><s:property value="#li.sellPrice"/></span>
						<s:if test='#menuType == "1"'> 
							<span class="table_cell">
								<i class="del_icon" title="删除" onclick="OrderSys.MenuManage.delFoodMenu(this,'<s:property value="#li.id"/>')"></i>
								<i class="edit_icon" title="编辑" onclick="OrderSys.MenuManage.editFoodMenu(this,'<s:property value="#li.id"/>','js_${status.count }')"></i>
							</span>
						</s:if>
						<s:else>
							<span class="table_cell W_40 mr_10 span_hover" style="width:54px;">
								<i class="subtract_Icon float_l mt_4"onclick="OrderSys.OrderManage.subtractFood(this)"></i>
								<s:textfield class="w_20 p_0 text_center float_l foodCount" style="height:16px;" readonly="true" value="1"></s:textfield>
								<i class="add_Icon float_l mt_4"  onclick="OrderSys.OrderManage.addFood(this)" ></i>
							</span>
						</s:else>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>
</div>
<script>
	$(".cal_H").each(function(index,domEle){
		parEle = $(domEle).parent();
		parH = parEle.height();
	});
	var parW = $(".cal_w").parent("div").width()
	$(".cal_w").css("width",(parW - 10)/2);
	JsUtil.PageSize.calDivH("foodMenu");
 	JsUtil.PageSize.calDivH("huncaiDiv");
 	JsUtil.PageSize.calDivH("sucaiDiv"); 
 	JsUtil.PageSize.calDivH("liangcaiDiv"); 
 	JsUtil.PageSize.calDivH("jiushuiDiv"); 
	JsUtil.PageSize.calOneMaxWidth('calChildMaxWidth','calBrotherMaxWidth');
</script>