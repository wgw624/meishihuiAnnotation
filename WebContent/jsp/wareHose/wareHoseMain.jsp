<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<div class="ml_10">
	<div class="H_36">
		<input type='button' onclick="WareHoseManage.warHose.addProductType(4)"  value="新增产品类型">
		<input type='button' value="添加仓库产品">
	</div>

	<s:iterator id="wareHose" value="wareHoseList">
		<div class="float_left bor w_160 mr_10">
			<s:hidden name="wareHose_id" value="%{#wareHose.id}"></s:hidden>
			<div class="line_H30 text_center" name="warehose_name"><s:property value="#wareHose.name" /></div>
			<div class="line_H30">
				<span class="iblock ml_10 line_H30">库存</span>
				<span class="iblock ml_10 line_H30"><s:property value="#wareHose.stock" /></span>
			</div>
			<div class="line_H30">
				<span class="iblock ml_10 line_H30">
					售价
				</span>
				<span class="iblock ml_10 line_H30">
					<s:property value="#wareHose.sellPrice" />
				</span>
			</div>
			<div class="line_H30">
				<input type='button' class="commonBtn float_right mr_20" value="新增" onclick="WareHoseManage.warHose.addInventory(this)">
			</div>
		</div>
	</s:iterator>

</div>






