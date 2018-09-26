JsUtil={
	PageSize:{
		ChangeWh:function(){
			 var h = $(document).height();
			    var w = $(document).width();
			    console.log("h:"+h+"  w:"+w);
			    $("#leftMenu").css("height",h);
			    $("#rightMenu").css("height",h-2);
			    
			    $("#topHead").css("width",w-200-18 -8);
			    $("#bodyContent").css("width",w-200-2-12-18);
			    $("#bodyContent").css("height",h-40-2-7);
		},
		twoLineAlignRight:function(){
			var maxWidth = 0;
			$(".two_line_right .alignRight").each(function(index,domEle){
				if($(domEle).width()>maxWidth){
					maxWidth = $(domEle).width();
				}
			});
			$(".two_line_right .alignRight").each(function(index,domEle){
				$(domEle).css("width",maxWidth);
			});
		},
		calOneMaxWidth:function(par,calObj){// 计算 calOneMax_W div 下所有cal_max_w 最大宽度
			$("."+par).each(function(index){
				var max_w = 0;
				$(this).find("."+calObj).each(function(index){
					var w = $(this).width();
					if(w > max_w){
						max_w = w;
					}
				});
				$(this).find("."+calObj).css("width",max_w);
			});
		},
		calDivH:function(divId){// 计算div 的高度，父div 有高度，该div 有多个兄弟节点 cal_div_h
			
			var parDiv = $("#"+divId).parent("div");
			var parH = parDiv.height();
			var slibs = $("#"+divId).siblings("div");
			var sumH = 0;
			slibs.each(function(index){
				var h = $(this).height();
				var mt = $(this).css("margin-top");
				var mb = $(this).css("margin-bottom");
				mt = parseInt(mt.substring(0,mt.length-2));
				mb = parseInt(mb.substring(0,mb.length-2));
				sumH = sumH + h + mt + mb;
			
				
			});
			var cur_div = $("#"+divId);
			var cur_mt = cur_div.css("margin-top");
			cur_mt = parseInt(cur_mt.substring(0,cur_mt.length-2));
			var cur_mb = cur_div.css("margin-bottom");
			cur_mb = parseInt(cur_mb.substring(0,cur_mb.length-2));
			console.log(parH+"-->"+cur_mt+"--->"+cur_mb);
			$("#"+divId).css("height",parH-sumH - cur_mt - cur_mb -4);
		}
	},
	Date:{
		dateFormatStr:function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			m = m<10 ? ('0'+m) : m;
			var d = date.getDate()
			d = d<10 ? ('0'+d) : d;
			return y+'-'+m+'-'+d
		},
		getCurrentMonth:function(){
			var today = new Date();
			var num = today.getMonth();
			return num+1;
		},
		getCurrentYear:function(){
			var today = new Date();
			var num = today.getYear();
			return num+1;
		},
		getCurrentDate:function(){
			var myDate = new Date();
			var curDate = myDate.toLocaleDateString();
			var re  = new RegExp("/","g");
			curDate = curDate.replace(re,"-");
			return curDate; 
		}
	},
	validate:{
		isNum:function(data){
			var reg = new RegExp("^[0-9]+(.[0-9]{1,12})?$");//小数和整数
			if(!reg.test(data)){
				return false;
			}else{
				return true;
			}
		},
		isInteger:function(data){
			var reg = new RegExp("^\\d+$");//正整数
			if(!reg.test(data)){
				return false;
			}else{
				return true;
			}
		},
		isNull:function(data){
			var reg = new RegExp("/^\s*$/g");
			if(data == ""){
				return true;
			}
			if(!reg.test(data)){
				return false;
			}else{
				return true;
			}
		}
	},
	input:{
		onblurValidateNum:function(id,btnId){
			var data = $("#"+id).val();
			if(!JsUtil.validate.isNum(data)){
				$("#"+id+"_span").text("请输入合法数字");
				$("#"+id).focus();
				$("#"+btnId).attr("disabled",true);
				$("#"+btnId).addClass("commonDisableBtn");
			}else{
				$("#"+id+"_span").text("");
				$("#"+btnId).removeAttr("disabled");
				$("#"+btnId).removeClass("commonDisableBtn");
			}
		},
		onblurValidateInteger:function(id,btnId){
			var data = $("#"+id).val();
			if(!JsUtil.validate.isInteger(data)){
				$("#"+id+"_span").text("请输入正整数");
				$("#"+id).focus();
				$("#"+btnId).attr("disabled",true);
				$("#"+btnId).addClass("commonDisableBtn");
			}else{
				$("#"+id+"_span").text("");
				$("#"+btnId).removeAttr("disabled");
				$("#"+btnId).removeClass("commonDisableBtn");
			}
		},
		isNull:function(validateObj){
			if(validateObj !=null && validateObj !=undefined){
				for(var i=0;i<validateObj.length;i++){
					var id = validateObj[i].id;
					var val = $("#"+id).val();
					if(JsUtil.validate.isNull(val)){
						alert(validateObj[i].name+"不能为空");
						return true;
					}
				}
				return false;
			}else{
				return false;
			}
		}
	},
	drag:{
		options:{
			moveWinId:'popup_win1',
			dragId:'popup_head1',
		},
		bind:function(dragObj){
			var opts = $.extend({},JsUtil.drag.options,dragObj)
			var moveWinId = opts.moveWinId;
			var dragId = opts.dragId;
			var moveWinObj = $("#"+moveWinId);
			$("#"+dragId).on("mousedown",opts,JsUtil.drag.startMove)
			$("body").on("mouseup",opts,JsUtil.drag.stopMove);
		},
		startMove:function(opts){
			var moveObj = $("#"+opts.data.moveWinId).offset()
			var moveWinX = moveObj.left;
			var moveWinY = moveObj.top;
			var moveOpt ={
					'moveWinId':opts.data.moveWinId,
					'dragId':opts.data.dragId,
					'spaceX':event.clientX - moveWinX,
					'spaceY':event.clientY - moveWinY,
			}
			
			$("#"+opts.data.dragId).on("mousemove",moveOpt,JsUtil.drag.move);
		},
		move:function(opts){
			var ex    = event.clientX
			var ey    = event.clientY;
			var left  = ex - opts.data.spaceX;
			var top   = ey - opts.data.spaceY
			var winW  = $(window).width();
			var winH  = $(window).height();
			var moveW = $("#"+opts.data.moveWinId).width();
			var moveH = $("#"+opts.data.moveWinId).height();
			if(left <= 0 || top <= 0 || top+moveH >= winH || left+moveW >= winW){
				return;
			}
			$("#"+opts.data.moveWinId).css({
				'left':left,
				'top':top
			})
		},
		stopMove:function(opts){
			$("#"+opts.data.dragId).off("mousemove");
		/*	console.log("stopMove..")
			$("body").off("mouseup",JsUtil.drag.stopMove)*/
		}
	},
	sys:{
	
	}
}