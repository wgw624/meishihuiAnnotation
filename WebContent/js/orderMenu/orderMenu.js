OrderSys={
	MenuManage:{//菜单管理
		openOrderMenuPage:function(type){
			var url = rootPath+"/ordermenu/OrderMenuAction_lookMenu";
			var param = {type:type};
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
				if(type == "1"){
					$("#orderBtn").parent("div").addClass("none");
				}
				
			});
		},
		changeFoodType:function(sel){
			var type = $(sel).val()
			if("4" == type){
				$("#addWareHoseCountDiv").css("display","block");
			}else{
				$("#addWareHoseCount").val(0);
				$("#addWareHoseCountDiv").css("display","none");
			}
			JsUtil.PageSize.twoLineAlignRight(); 
		},
		delFoodMenu:function(domEle,id){//删除菜
			var url = rootPath+"/ordermenu/OrderMenuAction_deleteFoodMenu"
			var param = {
				foodMenuId:id
			}
			$.post(url,param,function(data){
				$(domEle).parent().parent("div").remove();
			});
		},
		editFoodMenu:function(domEle,id,menuId){//编辑
			var url = rootPath +"/jsp/orderMenu/editMenu_popup.jsp";
			var foodName = $("#name_"+menuId).text();
			var foodPrice = $("#price_"+menuId).text();
			$.post(url,null,function(data){
				var option={
						'width':'260px',
						'height':'320px',
						'title':'编辑'
				}
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				$("#editFoodId").val(id);
				$("#editFoodName").val(foodName);
				$("#editFoodPrice").val(foodPrice);
				win.open();
			})
		},
		updateFoodMenu:function(){
			//非空验证
			var validateObj = new Array({'id':'editFoodName','name':'名称'},
										{'id':'editFoodPrice','name':'价格'});
			JsUtil.input.isNull(validateObj);
			var url = rootPath+"/ordermenu/OrderMenuAction_updateFoodMenu";
			var id = $("#editFoodId").val();
			var price = $("#editFoodPrice").val();
			var param = {
				'foodMenuId':id,
				'sellPrice':price
			};
			$.post(url,param,function(data){
				PopWin.PopUp.close();
				$("#bodyContent").html(data);
			})
		}
		
	},
	TableManage:{//餐桌管理
		newAddTable:function(){
			var url = rootPath +"/ordermenu/OrderMenuAction_addTable"
			var param = null;
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
		},
		saveTable:function(tableId){
			var url = rootPath+"/ordermenu/OrderMenuAction_saveTable";
			var tabNumber = $("#"+tableId).val();
			var param={'tabNumber':tabNumber}
			
			var isNotNull = new Array({'id':tableId,'name':'编号'});
			if(JsUtil.input.isNull(isNotNull)){
				return;
			}
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
		}
	},
	OrderManage:{//订单管理
		addFood:function(dom){
			var parDiv = $(dom).parent().parent("div");
			if(parDiv.find("input[type=checkbox]:checked").length>0){
				var textDom = $(dom).parent().find(".foodCount")
				var num = textDom.val();
				num = parseInt(num);
				$(textDom).val(num+1);
				OrderSys.OrderManage.check(null);
			}
		},
		subtractFood:function(dom){
			var parDiv = $(dom).parent().parent("div");
			if(parDiv.find("input[type=checkbox]:checked").length>0){
				var textDom = $(dom).parent().find(".foodCount")
				var num = textDom.val();
				num = parseInt(num);
				if(num>1){
					$(textDom).val(num-1);
					OrderSys.OrderManage.check(null);
				}	
			}
			
		},
		addCJ:function(dom){//增加餐具
			var text = $("#cjCount").val();
			text = parseInt(text);
			$("#cjCount").val(text+1);
			OrderSys.OrderManage.check(null);
		},
		subtractCJ:function(dom){
			var text = $("#cjCount").val();
			text = parseInt(text);
			if(text>1){
				$("#cjCount").val(text-1);
				OrderSys.OrderManage.check(null);
			}
		},
		check:function(dom){
//			alert($(dom).is(":checked"))
			var idArr = new Array("#huncaiDiv","#sucaiDiv","#liangcaiDiv","#jiushuiDiv");
			var moneyArr = new Array();
			var idCountArr = new Array();		
			for(var i=0;i<idArr.length;i++){
				var checked = $(idArr[i]).find("input[type=checkbox]:checked");
				var count = checked.length
				var money = 0;	
				checked.each(function(index){
					var parDiv = $(this).parent().parent("div");
					var countFood = parseInt(parDiv.find(".foodCount").val());
					var price = $(this).val();
					money = money + parseInt(price)*countFood;
				});
				idCountArr.push(count);
				moneyArr.push(money);
				
				var allchecked = $(idArr[i]).find(":checkbox");
				allchecked.each(function(index){
					console.log($(this).not(true))
					if($(this).not(":checked").length>0){
						var parNode = $(this).parent().parent("div");
						var textObj = parNode.find(".foodCount");
						$(textObj).val(1);
					}
				});
			}
			$("#hcId").val(idCountArr[0])
			$("#scId").val(idCountArr[1])
			$("#lcId").val(idCountArr[2])
			$("#jsId").val(idCountArr[3])
			$("#dcjeId").val(moneyArr[0]+moneyArr[1]+moneyArr[2]);
			$("#jsjeId").val(moneyArr[3]);
			var money = 0;
			for(var i=0;i<moneyArr.length;i++){
				money = money+moneyArr[i];
			}
			var cjPrice = $("#cjCount").attr("je");
			var cjCount = $("#cjCount").val();
			cjCount = parseInt(cjCount);
			cjPrice = parseInt(cjPrice)
			$("#jeId").val(money+cjPrice*cjCount)//应收金额
		},
		getCheckMenuStr:function(){//获取所点的全部菜
			var menuStr = "";
			var allCheck = $("#foodMenu").find("input[type='checkbox']:checked")
			allCheck.each(function(index){
				var foodCount =  $(this).parent().parent().find(".foodCount").val();
				if(index == 0){
					menuStr = menuStr+$(this).attr("id")+"_"+foodCount;
				}else{
					menuStr = menuStr+ "@" +$(this).attr("id")+"_"+foodCount;
				}
			});
			return menuStr;
		},
		submitOrder:function(){
			debugger;
			var tableId=$("#tableId").val();
			var menuStr = OrderSys.OrderManage.getCheckMenuStr();
			
			var ysje = $("#jeId").val();
			var cjCount = $("#cjCount").val();
			if(tableId ==undefined || tableId =="" || tableId == null){
				alert("请选择桌号");
				return;
			}
			var param = {'tableId':tableId,'menuStr':menuStr,'ysje':ysje,'cjCount':cjCount,}
			var url = rootPath+"/ordermenu/OrderMenuAction_submitOrder";
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
			
		},
		
		sureEditOrder:function(){
			debugger;
			var orderId = $("#orderId").val();
			var tableId=$("#tableId").val();
			var menuStr = OrderSys.OrderManage.getCheckMenuStr();
			var ysje = $("#jeId").val();
			var cjCount = $("#cjCount").val();
			var param = {'tableId':tableId,'orderId':orderId,'menuStr':menuStr,'ysje':ysje,'cjCount':cjCount,}
			var url = rootPath+"/ordermenu/OrderMenuAction_sureEditOrder";
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
		},
		waitSettle:function(){
			var url = rootPath+"/ordermenu/OrderMenuAction_waitSettle";
			var param = null;
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			});
			
		},
	
		openOrderDetail:function(orderId){
			var url = rootPath+"/ordermenu/OrderMenuAction_openOrderDetail";
			var param = {"orderId":orderId};
			$.post(url,param,function(data){
				var option={
						"width":"300",
						"height":"400"
				}
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				win.open();
			})
		},
		editOrderDetail:function(orderId){
			var url = rootPath+"/ordermenu/OrderMenuAction_lookMenu";
			var param = {type:'0'};
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
				var url2 = rootPath+"/ordermenu/OrderMenuAction_editOrderDetail";
				$.post(url2,{"orderId":orderId},function(data){
					debugger;
					var dataMap = data.orderData;
					$.each(dataMap,function(key,value){
						if("tableId" == key){
							var table = value.split("_");
							$("#tableId").append("<option value='"+table[0]+"'>"+table[1]+"</option>");					
							$("#tableId").find("option[value='"+table[0]+"']").attr("selected",true);
						}else if("dinnerwareCount" == key){
							var dcount = parseInt(value);
							if(dcount>0){
								for(var i=0;i<dcount;i++){
									$("#addCJ").click();
								}
							}
						}else{

							$("#"+key).click();
							var par = $("#"+key).parent().parent();
							var inp = par.find(".foodCount");
							var count = parseInt(value);
							if(count>1){
								var addBtn = par.find(".add_Icon ");
								for(var i=0;i<count-1;i++){
									addBtn.click();
								}
							}
						}
						
					});
				})
				$("#orderBtn").parent().addClass("none")
				$("#orderSureBtn").parent().removeClass("none")
				$("#orderId").val(orderId);
			});
		},
		payOrder:function(orderId){
			var ssje = $("#ssje").val();
			var tableId = $("#tableId").val();
			debugger;
			var payType = $(".payType").find(":checked").next().text();
			
			var url = rootPath+"/ordermenu/OrderMenuAction_payOrder";
			var param = {"orderId":orderId,"ssje":ssje,"tableId":tableId,"payType":payType};
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			});
		
			PopWin.PopUp.close();
		}
	}
}

