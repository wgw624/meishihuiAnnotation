<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/toggle.css">
<script src="../js/jquery.js"></script>
<title>Insert title here</title>
</head>
<body>
<div style="position: relative;display: inline-block">
    <input type="checkbox" id="radio" class="radio" name="switch">
    <label class="container" for="radio">
        <i class="bg_hui"></i>
        <i class="bg_lv"></i>
        <span class="circle"></span>
    </label>
</div>
<div>

    <ex:tog name="check" id="123"/>
    <ex:tog id="456"/>
    <input type="button" onclick = "test()" value="点击">
    <input type="checkbox" checked="checked"/>
</div>
</body>
<script>
	function test(){
		console.log($("#456").is(':checked'))
		alert($("#456").attr('checked'));
	}
</script>
</html>