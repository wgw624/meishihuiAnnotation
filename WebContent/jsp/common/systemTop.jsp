<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div id="topHead" class="topHead">
 <div class="userInf" onclick="user.drop.open(this)">
 	<i class="userTag"></i>
 	<span id="user_showName" class="userName">${ sessionScope.user.showName}</span>
 	<i class="userDropIcon"></i>
 </div>
 </div>