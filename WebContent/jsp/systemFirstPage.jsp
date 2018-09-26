<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="common/commonPath.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${rootPath }/css/base.css" type="text/css">
	<link rel="stylesheet" href="${rootPath }/css/main.css" type="text/css">
	<link rel="stylesheet" href="${rootPath }/css/page.css" type="text/css">
	<link rel="stylesheet" href="${rootPath }/css/popup.css" type="text/css">
	<script src="${rootPath }/js/plugin/jquery.js"></script>
	<script src="${rootPath }/js/plugin/My97DatePicker/WdatePicker.js"></script>
	<script src="${rootPath }/js/user.js"></script>
	<script src="${rootPath }/js/CalculatePage.js"></script>
	<script src="${rootPath }/js/common/commonMenu.js"></script>
	<script src="${rootPath }/js/common/JsUtil.js"></script>
	<script src="${rootPath }/js/common/popup.js"></script>
	<script src="${rootPath }/js/orderMenu/orderMenu.js"></script>
	<script src="${rootPath }/js/wareHoseManage.js"></script>
	<title>美食汇</title>
	
</head>
<body>
<s:include value="/jsp/common/popUp.jsp"></s:include>
<div class="clearfix">
	<s:include value="/jsp/common/systemLeft.jsp"></s:include>
	<div id="rightMenu" class="rightMenu">
		
	   	<s:include value="/jsp/common/systemTop.jsp"></s:include>
	    <div id="bodyContent" class="bodyContent">
	    </div>
	
	</div>
</div>

</body>
<script>
    JsUtil.PageSize.ChangeWh();
    $("#orderSys").click();
</script>

</body>
</html>