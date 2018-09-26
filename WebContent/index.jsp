<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="jsp/common/commonPath.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${rootPath }/js/jquery.js"></script>
<link rel="stylesheet" href="${rootPath }/css/login.css" style="text/css">
</head>
<body>

<div class="loginBody" id="loginMain">
	<div class="loginMain loginMain_size">
		<div class="loginLogo">
			<img src="${rootPath }/image/logo/msh.png" id="logoTitle" title="美食汇" />
                <span class="loginLogoTitle">
					美食汇餐饮系统               
				</span>
		</div>         
		<div class="loginCenter loginCenter_size" id="loginForm">
		  		<div class="loginImg loginImg_size">
                    <img src="${rootPath }/image/logo/login.png" id="loginImg_" style="width:100%">
                </div>
                <div class="loginInf loginInf_size">
				
					<form action="${rootPath }/login/loginAction_loginSystem" method="post">
						<div class="loginInfTitle loginInfTitle_size">
	                        <span class="loginInfTitleSpan loginInfTitleSpan_size">
	                        	 美食汇餐饮管理系统
	                        </span>
	                    </div>
	                    <div class="loginInpDiv loginInpDiv_size">
							<input  name="loginName" id="loginName" />
						</div>
						 <div class="loginInpDiv loginInpDiv_size">
							<input type="password" name="password" id="password">
						</div>
						<div class="loginErrorInf">
	                   		<c:choose>
								<c:when test="${empty msg }">
								</c:when>
								<c:otherwise>
									${msg}
								</c:otherwise>
							</c:choose>
                    	</div>
						<div class="loginInpDiv loginInpDiv_size">
							<input type="submit" value="登陆">
						</div>	
					</form> 
					
				</div>
		</div>
		<div class="loginBottom loginBottom_size">
                Copyright ©${datetime} meishihui Technology All Rights Reserved
        </div>
	</div>
</div>
</body>
<script type="text/javascript">
	var w = $(window).width();
	var h = $(window).height();
	$("#loginMain").css("width",w);
	$("#loginMain").css("height",h);
	function loginSystem(){
		rootPath = "<%=request.getContextPath() %>"
		var url = rootPath+"/login/loginAction_loginSystem";
		var param = {'userName':"123","password":"123321"}
		$.post(url,param,function(data){
			var rst = eval('('+data.result+')');
			if(data.dataMap.isLogin == "true"){
				window.location.href = "jsp/systemFirstPage.jsp"
			}else{
				window.location.href = "index.jsp"
			}
		});
	}
</script>
</html>