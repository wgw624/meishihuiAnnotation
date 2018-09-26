Employee={
	manage:{
		askForLeave:function(empId,empName){
			var url=rootPath +"/jsp/employee/askForLeave_popup.jsp";
			
			$.post(url,null,function(data){
				var option = {
						'title':'员工请假'
				}
				var win = new PopWin.PopUp.init(option)
				win.setContent(data);
				$("#askForLeaveEmpId").val(empId);
				$("#askForLeaveEmpName").val(empName);
				win.open();
			})
		},
		sureAskForLeave:function(){
			var askForLeaveDays = $("#askForLeaveDays").val();
			if(!JsUtil.validate.isInteger(askForLeaveDays)){
				JsUtil.input.onblurValidateInteger('askForLeaveDays','sureAskForLeaveBtn')
				return;
			}
			url = rootPath+"/employee/sureAskForLeave"; 
			var empId = $("#askForLeaveEmpId").val();
			var startDate = $("#startDate").val() == "" ? JsUtil.Date.getCurrentDate() : $("#startDate").val();
			var endDate = $("#endDate").val() == "" ? JsUtil.Date.getCurrentDate() : $("#startDate").val();
		
			var comments = $("#comments").val();
			var param={
					startDate:startDate,
					'endDate':endDate,
					'holidays':askForLeaveDays,
					'id':empId,
					'comments':comments,
					
			}
			
			$.post(url,param,function(data){
				PopWin.PopUp.close();
				$("#bodyContent").html(data);
			});
		},
		employeeSalary:function(){
			var url = rootPath+"/employee/employee_queryAllEmployeeInf";
			$.post(url,null,function(data){
				$("#bodyContent").html(data);
			})
		},
		queryEmployee:function(){
			var url = rootPath+"/employee/employee_queryAllEmployeeInf";
			var year = $("#year").val();
			var month = $("#month").val();
			var param = {
					'year':year,
					'month':month,
			}
			$.post(url,param,function(data){
				$("#employeeDiv").html(data);
			})
		},
		addEmployee:function(){
			var url = rootPath+"/jsp/employee/newAddEmployee_popup.jsp";
			$.post(url,null,function(data){
				var option={
					'title':'新增员工'
				}
				var win = new PopWin.PopUp.init(option)
				win.setContent(data);
				win.open();
			})
		},
		editEmployee:function(empId){
			var url = rootPath+"/employee/queryEditEmployee";
			var param={
					"id":empId
				}
			$.post(url,param,function(data){
				var option={
						'title':'修改员工信息'
					}
					var win = new PopWin.PopUp.init(option)
					win.setContent(data);
					win.open();
			});
		},
		isShowOvertime:function(){
			var val = $("input[name='isFixOvertimePay']:checked").val()
			if("0" == val){
				$("#overpay").css({'display':''});
			}else{
				$("#overpay").css({'display':'none'});
				$("#overtimePay").val(0);
			}
		},
		payOff:function(empId){
			var url = rootPath+"/employee/payOff";
			var param = {
					'id':empId,
			}
			$.post(url,param,function(data){
				var option={
						'title':'发工资'
					}
					var win = new PopWin.PopUp.init(option)
					win.setContent(data);
					win.open();
			});
		},
		surePayOff:function(){
			var year = $("#salaryYear").val();
			var month = $("#salaryMonth").val();
			var empId = $("#empId_popup").val();
			var sumSalary = $("#salarySum").val();
			
			var param = {
				'year':year,
				'month':month,
				'id':empId,
				'name':$("#empName_popup").val(),
				'shoulePaySalary':sumSalary,
			}
			var url =  rootPath+"/employee/surePayOff";
			$.post(url,param,function(data){
				PopWin.PopUp.close();
			});
			
		},
		saveNewEmployee:function(addOrEdit){

			var flag = 1;
			var id = 0;
			if(addOrEdit =="edit"){//
				flag = 0;
				id = $("#employeeId_popup").val()
			}
			var name = $("#employeeName").val();
			var baseSalary = $("#baseSalary").val();
			var holidays = $("#holidays").val();
			var perfectAttendanceAward = $("#perfectAttendanceAward").val();
			var overtimePay = $("#overtimePay").val();
			var isFixOverTimePay = $("input[name='isFixOvertimePay']:checked").val()
			var param = {
					id: id,
					name:name,
					'holidays':holidays,
					'baseSalary':baseSalary,
					'perfectAttendanceAward':perfectAttendanceAward,
					'overtimePay':overtimePay,
					'isFixOverTimePay':isFixOverTimePay,
					'isWork':flag,// 此时 isWork 表示 是新增还是编辑
			}
		
			var url = rootPath+"/employee/addOrEditEmployee";
			$.post(url,param,function(data){
				PopWin.PopUp.close();
				$("#bodyContent").html(data);
			})
		},
		deleteEmployee:function(){
			
		},
	}
}