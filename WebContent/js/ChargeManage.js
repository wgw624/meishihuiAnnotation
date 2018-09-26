/**
 * 费用管理
 */
ChargeManage={
	queryConsumOrder:function(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		
		var param = {
				'startDate':startDate,
				'endDate':endDate
		}
		var url = rootPath +"/chargeManage/chargeManageAction_getConsumOrderList"
		$.post(url,param,function(data){
			$("#consumOrder").html(data);
		})
	},
	openTurnOverPage:function(){
		var url= rootPath+"/jsp/charge/TurnoverQueryPage.jsp";
		$.post(url,null,function(data){
			$("#bodyContent").html(data);
		})
	},
	openExpensePage:function(){
		var url= rootPath+"/chargeManage/chargeManageAction_openExpensePage";
		$.post(url,null,function(data){
			$("#bodyContent").html(data);
		})
	},
	queryExpenseRecord:function(){
		var url= rootPath+"/chargeManage/chargeManageAction_queryExpenseRecord";
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var expenseType = $("#expenseType").val();
		if(startDate == null || startDate == undefined){
			alert("请选择开始日期");
			return;
		}
		if(endDate == null || endDate == undefined){
			alert("请选择结束日期");
			return;
		}
		var param = {
				'startDate':startDate,
				'endDate':endDate,
				'expenseType':expenseType
		}
		$.post(url,param,function(data){
			$("#expenseRecordTabel").html(data);
		})
	},
	addExpenseRecord:function(){		
		var url= rootPath+"/jsp/charge/addExpenseRecord_popup.jsp";	
		$.post(url,null,function(data){
			var option = {
					"width":"260",
					"height":"340",
					"title":"费用支出"
			}
			var win = new PopWin.PopUp.init(option);
			win.setContent(data);
			win.open();
		})
	},
	sureExpense:function(){
		var url = rootPath+"/chargeManage/chargeManageAction_sureExpense";
		var expensedType = $("#expenseType").val();
		var payMoney = $("#expenseMoney").val();
		var comments = $("#comments").val();
		var param = {
			expensedType:expensedType,
			payMoney:payMoney,
			comments:comments
		}
		var validateParam = new Array({'id':'expenseMoney','name':'发费金额'});
		if(JsUtil.input.isNull(validateParam)){
			return;
		}
		$.post(url,param,function(data){
			PopWin.PopUp.close();
			$("#expenseRecordTabel").html(data);
		})
	},

}
