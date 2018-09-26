WareHoseManage = {
	warHose:{
		openMain:function(){
			var url= rootPath+"/wareHose/wareHoseManageAction_openMain";
			var param = null;
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
		},
		addProductType:function(foodType){//新增产品类型
			var url = rootPath+"/wareHose/wareHoseManageAction_openInvertoryPopup";
			var param ={
					wareHoseType:foodType
			}
			$.post(url,param,function(data){
				var option = {
						"width":"260",
						"height":"340",
						"title":"新增"
				}
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				win.open();
				JsUtil.PageSize.calOneMaxWidth('calChildMaxWidth','calBrotherMaxWidth');
			});
		},

		sureAddProductType:function(){
			var url = rootPath+"/wareHose/wareHoseManageAction_addProductType";
			var param = null;
			var typeId = $("#p_TypeId").val();
			var comments = $("#p_description").val();
			if("4" == typeId){
				var purchasePrice = $("#p_purchasePrice").val();
				var count = $("#p_Count").val();
				var giveawayCount = $("#p_giveawayCount").val();
			}
			var pName = $("#p_name").val();
			var sellPrice = $("#p_sellPrice").val();
			
		/*	var obj = null;*/
			if("4"== typeId){
				param = {'wareHoseType':typeId,'name':pName,'purchasePrice':purchasePrice,'sellPrice':sellPrice,'addStockCount':count,'comments':comments,'giveawayCount':giveawayCount};
			/*	obj = new Array({'id':'p_name','name':'产品名称'},{'id':'p_TypeId','name':'产品类型'}
				 ,{'id':'p_purchasePrice','name':'产品进价'},{'id':'p_sellPrice','name':'产品售价'}
				 ,{'id':'p_Count','name':'产品数量'},{'id':'p_sellPrice','name':'产品售价'});*/
			}else{
				param = {'wareHoseType':typeId,'name':pName,'sellPrice':sellPrice,'comments':comments};
			/*	obj = new Array({'id':'p_name','name':'产品名称'},{'id':'p_TypeId','name':'产品类型'},{'id':'p_sellPrice','name':'产品售价'});*/
			}
		/*	if(JsUtil.input.isNull(obj)){
				return;
			}*/
			$.post(url,param,function(data){
				PopWin.PopUp.close();
				$("#bodyContent").html(data);
			});
		},
		addInventory:function(dom){//增加库存
			var url = rootPath+"/jsp/wareHose/addInventory_popup.jsp";
			$.post(url,null,function(data){
				var option={
						"width":"230",
						"height":"200",
						"title":"新增"
				}
				console.log(wareHoseId);
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				win.open();
				var par = $(dom).parent("div").parent("div");
				var wareHoseId = par.find("input[name='wareHose_id']").val();
				var wareHosename = par.find("div[name='warehose_name']").text();
				$("#wareHoseId").val(wareHoseId);
				$("#wareHosename").text(wareHosename);
			});
		},
		sureAddInvertory:function(){
			var url = rootPath+"/wareHose/wareHoseManageAction_addInvertory";
			var wareHoseId=$("#wareHoseId").val()
			var count = $("#pCount").val();
			var giveAwayCount = $("#giveAwayCount").val();
			var comments = $("#comments").val();
			var param={
					'wareHoseId':wareHoseId,
					'addStockCount':count,
					'giveawayCount':giveAwayCount,
					'comments':comments
			}
			var valiParam = new Array({'id':'pCount','name':'增加数量'});
			if(JsUtil.input.isNull(valiParam)){
				return;
			}
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
				PopWin.PopUp.close();
			});
		}
	},
	wareHoseDetail:{
		purchaseQuery:function(isSettleId){

			if(isSettleId){ //0 未结账，1 已结账，2未结清
				var isSettle = $("#"+isSettleId).val();
				var param = {
						'isSettle':isSettle,
				}
				var url = rootPath+"/wareHose/wareHoseManageAction_getHostDetailBysql";
				$.post(url,param,function(data){
					$("#hoseDetailTable").html(data);
				})
				
			}else{
				var url = rootPath+"/wareHose/wareHoseManageAction_getAllHoseDetail";
				$.post(url,param,function(data){
					$("#bodyContent").html(data);
				})
				
			}
		},
		NoDebtQuery:function(){
			var param = {
				'isSettle':'0',
				'comments':'debt',
			}
			var url = rootPath+"/wareHose/wareHoseManageAction_getHostDetailBysql";
			$.post(url,param,function(data){
				$("#bodyContent").html(data);
			})
			
			
		},
		openPaymentHtml:function(hoseDetailId){
			var param = {
					'id':hoseDetailId
			}
			var url = rootPath+"/wareHose/wareHoseManageAction_OpenPaymentPage";
			$.post(url,param,function(data){
				var option = {
						'width':'300',
						'height':'400',
						'title':'还款'
				}
				var win = new PopWin.PopUp.init(option);
				win.setContent(data);
				win.open();
				JsUtil.PageSize.calOneMaxWidth('calChildMaxWidth','calBrotherMaxWidth');
			})
		},
		surePayment:function(hoseDetailId){
			var sfje = $("#hoseDetailRelPayMoney").val();//实付金额
			var comments = $("#comments").val();
			var isSettle = $("#isNoSettle:checked").val();
			console.log(isSettle)
			var param = {
					'id':hoseDetailId,
					'reallyPaySumMoney':sfje,
					'comments':comments,
					'isSettle':isSettle
			}
			var url = rootPath+"/wareHose/surePayment";
			$.post(url,param,function(data){
				PopWin.PopUp.close();
				$("#bodyContent").html(data);
			});
		}
	}
}
