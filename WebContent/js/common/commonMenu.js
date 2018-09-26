
/**
 * Created by weiguangwei on 11/27 0027.
 */
CommonMange={
	leftMenu:{
		chargeMange:function(){//费用管理
			var url = rootPath+"/jsp/charge/ChargeManageMain.jsp"
			$.post(url,null,function(data){
				$("#bodyContent").html(data);
			})
		},
		orderManage:function(){//订单管理
			var url=rootPath+"/sysMenu/SystemMenuAction_openOrderMenu";
			var param = null;
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			});
		},
		userManage:function(){
			var url=rootPath+"/sysMenu/queryUser";
			$.post(url,null,function(data){
				$("#bodyContent").html(data);
			});
		},
		expendMange:function(){
			alert("待开发")
		}
	}
}
function switchMenu(){
    if($("#left_menuDiv").css("display")=="none"){
        $("#left_menuDiv").css("display","block");
        $("#innerContentDiv").css("margin-left","200px");
    }else{
        $("#left_menuDiv").css("display","none");
        $("#innerContentDiv").css("margin-left","0");
    }

}


