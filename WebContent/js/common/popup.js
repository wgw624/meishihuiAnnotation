var PopWin = {
	PopUp:{
		initId:{
			pop_bg:"popup_bg",
			popup_win:"popup_win",
			popup_head:"popup_head",
			popup_content:"popup_content",
			pop_head_text:"popup_head_text",
			pop_head_close:"popup_head_close",
			pop_number:0,
		},
		init:function(options){
			var _optDef={
				'width':'260px',
				'height':'320px',
				'title':'弹窗'
			};
			var popWinId = PopWin.PopUp.initId;
			popWinId.pop_number = popWinId.pop_number + 1;
			var popupWin = popWinId.popup_win +popWinId.pop_number;
			var popContent = popWinId.popup_content + popWinId.pop_number;
			var popBg = popWinId.pop_bg + popWinId.pop_number;
			var popHead = popWinId.popup_head + popWinId.pop_number;
			var popHeadTx = popWinId.pop_head_text + popWinId.pop_number;
			var popHeadClose = popWinId.pop_head_close + popWinId.pop_number;
			var opts = $.extend({},_optDef,options);
			
			var width = opts.width;
			var height = opts.height;
			var title = opts.title;
			
			this.setContent = function(html){
				$("#"+popContent).html(html);
			};
			this.open = function(){
				if(width == undefined){
					$("#"+popupWin).css("width","auto")
				}else{
					/*$("#"+popupWin).css("width",width)*/
				}
				if(title !=undefined && title !=null){
					$("#"+popHeadTx).text(title);
				}
				if(height == undefined){
					$("#"+popupWin).css("height","auto")
				}else{
					/*$("#"+popupWin).css("height",height)
					var headHeight = $("#"+popHead).height();
					var h = 56;//弹窗底部按钮 及间距
					$("#"+popContent).css("height",height-headHeight - h);*/
				}
				
				$("#"+popupWin).css({"left":"300px","top":"200px"});
				$("#"+popBg).css("display","block");
				$("#"+popHeadClose).unbind("click").bind("click",function(){
					PopWin.PopUp.close()
				});	
				JsUtil.drag.bind({'moveWinId':'popup_win'+popWinId.pop_number,'dragId':'popup_head'+popWinId.pop_number});
			};
			
		},
		
		close:function(){
			var popWinId = PopWin.PopUp.initId;
			var popContent = popWinId.popup_content + popWinId.pop_number;
			var popBg = popWinId.pop_bg + popWinId.pop_number;
			$("#"+popContent).html("")
			$("#"+popBg).css("display","none");	
			popWinId.pop_number = popWinId.pop_number-1;
		},
	}	

}
var user={
		drop:{
			open:function(clikele){
				var userLeft = $(".userInf").offset().left;
				var userTop = $(".userInf").offset().top;
				var dropLeft = userLeft;
				var dropTop = userTop+50+15;
				$("#userDropDiv").css(
					{"display":"block",
				});
				$("#userDropDiv").unbind("mouseleave ").bind("mouseleave ",function(){
					user.drop.close();
				});
			},
			close:function(){
				$("#userDropDiv").css("display","none");
			},
		}
}

