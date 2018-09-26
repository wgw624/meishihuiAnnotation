User={
	Drop:{
		cancel:function(){
			var url = rootPath+"/login/loginAction_cancel";
			$.post(url,null,function(data){
				window.location.href = "index.jsp"
			});
		},
		signOut:function(){
			User.Drop.cancel();
		},
	
		openModifyPasswordWin:function(){
			var url = rootPath+"/jsp/modifypassword_popup.jsp";
			$.post(url,null,function(data){
				var option = {
						'title':'修改密码'
				}
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				win.open();
			})
		},
		modifyPassword:function(){
			var oldPsd     = $("#oldPsd").val();
			var newPsd     = $("#newPsd").val();
			var sureNewPsd = $("#sureNewPsd").val();
			if(newPsd != sureNewPsd){
				alert("新密码输入不一致")
				return;
			}
			var url = rootPath+"/login/modifyPassword";
			var param = {
					'newPsd':newPsd,
					'password':oldPsd,
			}
			$.post(url,param,function(){
				PopWin.PopUp.close();
			});
		},
	},
	newAddUser:function(){
		var url = rootPath+"/jsp/user/addUser_popup.jsp";
		$.post(url,null,function(data){
			var option = {
					'title':'新增用户'
			}
			var win = new PopWin.PopUp.init(option);
			win.setContent(data);
			win.open();
		});
	}
}