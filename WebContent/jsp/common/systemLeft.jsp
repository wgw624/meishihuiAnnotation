 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div id= "leftMenu" class="leftMenu">
     <div class="logo">欢迎来到我的系统</div>
        <ul>
            <li id="orderSys" onclick="CommonMange.leftMenu.orderManage()">点餐系统</li>
            <li onclick="WareHoseManage.warHose.openMain()">仓库管理</li>
            <li onclick="CommonMange.leftMenu.expendMange()">会员管理</li>
            <li onclick="CommonMange.leftMenu.chargeMange()">费用管理</li>
            <li onclick="CommonMange.leftMenu.userManage()">用户管理</li>
       </ul>
 </div>