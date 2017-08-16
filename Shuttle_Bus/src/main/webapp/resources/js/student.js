$(document).ready(function() {
		var date_of_travel=[];
		var selectRound=null;
		var back=null;
		var from=null;
		var itera_date_travel=2;
		var user_id="t4jrtfh385";
		var see_more_notification=0;
		var mySchedule_data; // use for my schedule form
		
		var date="2017-08-26";
		$('.dropdown-icon, .dropdown-user-info').dropdown({
		      inDuration: 300,
		      outDuration: 225,
		      constrainWidth: false, // Does not change width of dropdown to that of the activator
		      hover: true, // Activate on hover
		      gutter: 0, // Spacing from edge
		      belowOrigin: true, // Displays dropdown below the button
		      alignment: 'right',  // Displays dropdown with edge aligned to the left of button
		      stopPropagation: false // Stops event propagation
		    }
		  );
		 $(".button-collapse").sideNav();
   		 $('select').material_select();   
   		 $('.modal').modal();
   		 var defaul_leave_date;
   		 var defaul_return_date;
   		 var defaul_deadline;
	    
   		 //user_info
   		$.ajax({
   			async:false,
			cache:false,
			type : "POST",
			url : "user_info",
			contentType : "application/json",
			data:user_id,
			timeout : 100000,
			success : function(data) {
					console.log("User: "+data[0].date_of_leaving);
					defaul_leave_date=data[0].date_of_leaving;
					defaul_return_date=data[0].date_of_returning;
		   		  	defaul_deadline=data[0].deadline_of_booking;
					var userInfoForm="";
					userInfoForm+='<li><a href="#!">Name: '+data[0].fullname
								+'</a></li><li class="divider"></li><li><a href="#!">Username: '+
								data[0].username+'</a></li><li class="divider"></li><li><a href="#!">No.of Ticket:&nbsp &nbsp'+
								data[0].no_of_ticket+'</a></li><li class="divider"><li><a href="logout">Log Out</a></li><li class="divider"></li>'
					document.getElementById("user_info").innerHTML = userInfoForm;
			},
			error : function(e) {
				console.log("ERROR: ", e);
					
			},
			done : function(e) {
				console.log("DONE");
			}
		});
   		//to enable date for booking
   		$(".flatpickr input").flatpickr({
			mode: "multiple",
		    minDate: "today",
		    dateFormat: "Y-m-d",
		    enable: [defaul_leave_date,defaul_return_date]
		}); 
		//for student notification
		student_notification();
   		function student_notification(){
			var notification_form='';
			var count=0; //to count new notification
   			var notification=[];
   			var book_notif=booking_noti();
   			var donate_notif=donate_noti();
   			var exchange_seat_nitif=exchange_seat_noti();
   			var permission=permission_noti();			
			var emergency=emergency_request_noti();
			
			for(i=0;i<exchange_seat_nitif.length;i++){
				notification.push(exchange_seat_nitif[i]);
			}
			for(i=0;i<permission.length;i++){
				notification.push(permission[i]);
			}
			for(i=0;i<book_notif.length;i++){
				notification.push(book_notif[i]);
			}
			for(i=0;i<donate_notif.length;i++){
				notification.push(donate_notif[i]);
			}
			for(i=0;i<emergency.length;i++){
				notification.push(emergency[i]);
			}
			notification.sort(function compare(a, b) {
			  var dateA = new Date(a.updated_at);
			  var dateB = new Date(b.updated_at);
			  return dateA - dateB;
			});
			
			//8 record include see more
			for(i=notification.length-1;i>notification.length-8&&i>=0;i--){
				if(notification[i].notification=='new'){
					count++;
				}
				switch(notification[i].status) {
				case 'booking_approved':
					notification_form+='<li><a href="#">Your booking at '+notification[i].date_of_travel+' have approved !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'booking_waiting':
					notification_form+='<li><a href="#">Your booking at '+notification[i].date_of_travel+' are padding !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'booking_ignore':
					notification_form+='<li><a href="#">Your booking at <b class="text-red">'+notification[i].date_of_travel+'</b> have ignored<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'donated':
					notification_form+='<li><a href="#">You haved received '+notification[i].no_of_ticket+' tictkets from '+notification[i].receive_from+' !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'exchange_seat':
					notification_form+='<li><a href="#">You haved received a seat at '+notification[i].date_of_travel+' !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'permission_waiting':
					notification_form+='<li><a href="#">Your Permission are padding !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'permission_approved':
					notification_form+='<li><a href="#">Your Permission have approved !<span class="'+notification[i].status +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'permission_ignored':
					notification_form+='<li><a href="#">Your Permission have ingored !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;	
				case 'emergency_approved':
					notification_form+='<li><a href="#">Your Emergency Request at '+notification[i].date_of_travel+' have approved !<span class=" badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'emergency_waiting':
					notification_form+='<li><a href="#">Your Emergency Request at '+notification[i].date_of_travel+' are padding !<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				case 'emergency_ignored':
					notification_form+='<li><a href="#">Your Emergency Request at <b class="text-red">'+notification[i].date_of_travel+'</b> have ignored<span class="'+notification[i].notification +' badge right"></span></a></li><li class="divider"></li>';
					break;
				}
			}
			if(count>0){
				document.getElementById('notifications_icon').innerHTML='<i class="material-icons red-text">notifications</i>';
			}
			else{
				document.getElementById('notifications_icon').innerHTML='<i class="material-icons">notifications</i>';
			}
			if(notification.length-1>7){
				notification_form+='<li><a id="clk_seemore" class="center" href="#seemore">see more</a></li><li class="divider"></li>';
			}
			document.getElementById('notification').innerHTML=notification_form;
			$('#clk_seemore').click(function(){
				see_more_notification=1;
				see_more();
			})
   		}
   	    //to see more notification
   		function see_more(){
			var notification=[];
			var notification_form='';
   			var book_notif=booking_noti();
   			console.log("Book "+book_notif.length);
			console.log("Book "+book_notif[0].updated_at);
   			var donate_notif=donate_noti();
   			console.log("donate "+donate_notif.length);
			console.log("Book "+book_notif[0].notification);
   			var exchange_seat_nitif=exchange_seat_noti();
   			console.log("exchangeSeat "+exchange_seat_nitif.length);
			console.log("Book "+exchange_seat_nitif[0].notification);
   			var permission=permission_noti();
			console.log("permission: "+permission.length);
			console.log("Book "+permission[0].notification);
			var emergency=emergency_request_noti();
			console.log("permission: "+emergency.length);
			console.log("Book "+permission[0].notification);
   			for(i=0;i<exchange_seat_nitif.length;i++){
				notification.push(exchange_seat_nitif[i]);
			}
			for(i=0;i<permission.length;i++){
				notification.push(permission[i]);
			}
			for(i=0;i<book_notif.length;i++){
				notification.push(book_notif[i]);
			}
			for(i=0;i<donate_notif.length;i++){
				notification.push(donate_notif[i]);
			}
			for(i=0;i<emergency.length;i++){
				notification.push(emergency[i]);
			}
			console.log("see more"+notification.length);
			console.log("see more"+notification[0].status);
			console.log("see more"+notification[1].status);
			notification.sort(function compare(a, b){
			  var dateA = new Date(a.updated_at);
			  var dateB = new Date(b.updated_at);
			  return dateA - dateB;
			});
			//consoel.log("Noti_length: "+notification.length)
			for(i=notification.length-1;i>notification.length-((see_more_notification*10)+1)&&i>=0;i--){
				console.log(i);
				switch(notification[i].status) {
				case 'booking_approved':
					notification_form+='<li class="collection-item"><p>Your booking at '+notification[i].date_of_travel+' from '+notification[i].destination_name+' have approved !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'booking_waiting':
					notification_form+='<li class="collection-item"><p>Your booking at '+notification[i].date_of_travel+' from '+notification[i].destination_name+' are padding !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'booking_ignore':
					notification_form+='<li class="collection-item"><p>Your booking at <b class="text-red">'+notification[i].date_of_travel+' from '+notification[i].destination_name+'</b> have ignored !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'donated':
					notification_form+='<li class="collection-item"><p>You have received '+notification[i].no_of_ticket+' tictkets from '+notification[i].receive_from+' at '+notification[i].updated_at+' !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'exchange_seat':
					notification_form+='<li class="collection-item"><p>You have received a seat from '+notification[i].destination+' at '+notification[i].date_of_travel+' by '+notification[i].exchange_from+' !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'permission_waiting':
					notification_form+='<li class="collection-item"><p>Your permission date on '+notification[i].date_of_request+' by this reason " '+notification[i].reason+' " are padding !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'permission_approved':
					notification_form+='<li class="collection-item"><p>Your permission date on '+notification[i].date_of_request+' by this reason " '+notification[i].reason+' " have approved !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;	
				case 'permission_ignore':
					notification_form+='<li class="collection-item"><p>Your permission date on '+notification[i].date_of_request+' by this reason " '+notification[i].reason+' " have ingored !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'emergency_approved':
					notification_form+='<li class="collection-item"><p>Your Emergency Request at '+notification[i].date_of_travel+' from '+notification[i].destination_name+' have approved !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'emergency_waiting':
					notification_form+='<li class="collection-item"><p>Your Emergency Request at '+notification[i].date_of_travel+' from '+notification[i].destination_name+' are padding !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;
				case 'emergency_ignored':
					notification_form+='<li class="collection-item"><p>Your Emergency Request at <b class="text-red">'+notification[i].date_of_travel+' from '+notification[i].destination_name+'</b> have ignored !<span class="'+notification[i].notification +' badge right"></span></p></li><li class="divider"></li>';
					break;	
				}
				
			}
			if(notification.length>=(see_more_notification*10)){
				notification_form+='<li class="collection-item center"><a id="clk_seemore1" href="#">see more</a></li>';
			}
			document.getElementById('list_noti').innerHTML=notification_form;
			$('#clk_seemore1').click(function(){
				console.log("see more 1");
				see_more_notification++;
				see_more();
			})
		 };

		 
		function emergency_request_noti(){
   			var emergency=[];
   			var submit={"user_id":user_id,"value":see_more_notification};
   			$.ajax({
   				async:false,
 				cache:false,
   				type : "POST",
   				url : "emergency_notification",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						emergency=data;
   						
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
   			return emergency;
   		}
		 
		function booking_noti(){
   			var submit={"user_id":user_id,"value":see_more_notification};
   			$.ajax({
   				async:false,
 				cache:false,
   				type : "POST",
   				url : "student_book_notification",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						mySchedule_data=data
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
   			return mySchedule_data;
   		}
   				 
   		
   		function permission_noti(){
   			var permission=[];
			var submit={"user_id":user_id,"value":see_more_notification};
   			$.ajax({
   				async:false,
   				cache:false,
   				type : "POST",
   				url : "student_permission",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						permission=data;
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
   			return permission;
   		}
   		function exchange_seat_noti(){
   			var exchange=[];
			var submit={"user_id":user_id,"value":see_more_notification};
   			$.ajax({
   				async:false,
 				cache:false,
   				type : "POST",
   				url : "exchange_seat_notification",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						exchange=data;
   						
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
   			return exchange;
   		}
   		
   		function donate_noti(){
   			var donate=[];
			var submit={"user_id":user_id,"value":see_more_notification};
   			$.ajax({
   				async:false,
 				cache:false,
   				type : "POST",
   				url : "donate_notification",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						donate=data;
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
   			return donate;
   		}
   		
   		
   		//show my schedule form
		mySchedule();
		function mySchedule(){
			var booking_form=null;
			var booking_waiting='<h5 class="center sch light-blue-text">Booking Request Sent</h5><br><table  class="centered bordered highlight"><thead><tr><th>Date of Travel</th><th>Destination</th><th>Option Status</th></tr></thead><tbody>';
			var booking_approved='<h5 class="center sch light-blue-text">My Schedule</h5><br><table  class="centered bordered highlight"><thead><tr><th>Date of Travel</th><th>Destination</th><th>View Ticket</th></tr></thead><tbody>';
			var b_wait=0,b_approved=0;
			var emergency_waiting='';
			var emergency_approved='';
			var e_wait=0,e_approved=0;
			for(i=0;i<mySchedule_data.length;i++){
				console.log(mySchedule_data[i].date_of_travel);
				switch(mySchedule_data[i].status) {
					case 'booking_approved':
						b_approved++;						
						booking_approved+='<tr><td>'+mySchedule_data[i].date_of_travel
		   								+'</td><td>'+mySchedule_data[i].destination_name
		   								+'</td><td><a href="#rqcodeForm" class="view_qrcode" value="'+i
		   						        +'" style="color:#ff9800;cursor:pointer">View</a></td></tr>';
						break;
					case 'booking_waiting':
						b_wait++;
						console.log("KK: "+mySchedule_data[i].date_of_travel);
						booking_waiting+='<tr><td>'+mySchedule_data[i].date_of_travel
									+'</td><td>'+mySchedule_data[i].destination_name
									+'</td><td><a href="#cancel_booking" class="book_cancel" value="'+i
							        +'" style="color:#ff9800;cursor:pointer">Cancel</a></td></tr>';	
						break;
				}
				
			}
			booking_approved+='</tbody></table><br><br>';
			booking_waiting+='</tbody></table><br><br>';
			console.log('My Schedule: '+b_wait);
			console.log('My Schedule: '+b_approved);
			if(b_wait!=0&&b_approved!=0){
				booking_form=booking_approved+booking_waiting;
			}else if(b_wait!=0){
				booking_form=booking_waiting;
			}else if(b_approved!=0){
				booking_form=booking_approved;
				console.log(booking_approved);
			}else{
				booking_form='';
			}
			document.getElementById('my_schedule').innerHTML=booking_form;
			$('.view_qrcode').click(function(){
				var qrcode_index=$(this).attr('value');
				console.log(qrcode_index);
				var qrcode_form='<img class="img_rqcode center" src="data:image/png;base64,'+mySchedule_data[qrcode_index].qrcode+'">';
				document.getElementById('rqcode').innerHTML=qrcode_form;
			})
			$('.book_cancel').click(function(){
				var cancel_index=$(this).attr('value') ;
				var camcel_book_form='<p>Are you sure that you want to cancel ticket from <b>'+mySchedule_data[cancel_index].destination_name+'</b> at <b class="red-text">'+mySchedule_data[cancel_index].date_of_travel+'</b></p>';
				var btn_Cancel ='<a class="modal-action modal-close waves-effect waves-green btn-flat booking_cancel_clk" value="'+i+'">Yes</a>';//clk_cancel_booking
				document.getElementById('getCancelInfo').innerHTML=camcel_book_form;
				document.getElementById('clk_cancel_booking').innerHTML=btn_Cancel;
				$('.booking_cancel_clk').click(function(){
				    cancel_ticket(mySchedule_data[cancel_index].destination_id,mySchedule_data[cancel_index].date_of_travel);
				})
			})
			
		}
   		// to coancel booking ticket
		function cancel_ticket(des_id, date_travel){
			var submit={"user_id":user_id,"destination_id":des_id,"date_of_travel":date_travel};
   			$.ajax({
   				async:false,
 				cache:false,
   				type : "POST",
   				url : "cancel_ticket",
   				contentType : "application/json",
   				data : JSON.stringify(submit),
   				timeout : 100000,
   				success : function(data) {
   						alert(data);
   				},
   				error : function(e) {
   					console.log("ERROR: ", e);
   						
   				},
   				done : function(e) {
   					console.log("DONE");
   				}
   			}); 
		}
   		 
  		//select form
 		var destination="";  //user in select
 		$.ajax({
 				type : "GET",
 				async:false,
 				cache:false,
 				contentType : "application/json",
 				url : "selectService",
 				timeout : 100000,
 				success : function(data) {
 					destination=data;
 					console.log(data);
 					var select='<select id="fromDes" class="validate"><option disabled selected>From</option>'
 					for(i=0;i<destination.length;i++){
 						select +='<option value="'+destination[i].destination_id+'">'+destination[i].destination_name+'</option>';								
 					};
 					select+='</select>';
 					document.getElementById("getSelectFrom").innerHTML = select;						
 					$('#fromDes').material_select();
 					},
 					error : function(e) {
 						console.log("ERROR: ", e);
 					},
 					done : function(e) {
 						console.log("DONE");
 					}
 		});
	    
  		 
  		//handle date select
		$('#roundWay').change(function(){
		 	selectRound=true;
		 	itera_date_travel=2;
		 	optionSelect(itera_date_travel);
		 	date_of_travel=[];
			$(".flatpickr input").flatpickr({
				mode: "multiple",
			    minDate: "today",
			    dateFormat: "Y-m-d",
			    enable: ["2017-08-20", "2017-08-25"]
			});
		 })
		$('#oneWay').change(function(){
		 	itera_date_travel=1;
		 	optionSelect(itera_date_travel);
		 	date_of_travel=[];
		    $(".flatpickr input").flatpickr({
				mode: "single",
				minDate: "today",
				dateFormat: "Y-m-d",
				enable: ["2017-08-20", "2017-08-25"]
			});
		 })

		
		
   		function optionSelect(itera_date_travel){
			if(itera_date_travel==2){
				var backForm='<select class="validate r-s"><option disabled selected>Return</option></select>';
				document.getElementById("getSelectTo").innerHTML = backForm;
				$('#getSelectTo .r-s').material_select();
				
			}else{
				var backForm='<label style="cursor:not-allowed">Return</label>';
				document.getElementById("getSelectTo").innerHTML = backForm;						
			}	
   		 }
   		//handle event select destinaton
   		$('#fromDes').change(function(){
			from = $("#fromDes").val();
			console.log("from: "+from);
			if(itera_date_travel==2){
				var toDesForm='<select id="toDes" class="validate">';
				for(i=0;i<destination.length;i++){
					if(destination[i].destination_id!=from){
						toDesForm +='<option value="'+destination[i].destination_id+'" selected>'+destination[i].destination_name+'</option>';
						back = destination[i].destination_id;
					}
				}
				toDesForm+='</select>';
				document.getElementById("getSelectTo").innerHTML = toDesForm;
				$('#toDes').material_select();
			}
		 });
  			 
   	    //schedule data
   		$.ajax({
   			type : "POST",
   			async:false,
			cache:false,
   			contentType : "application/json",
   			url : "scheduleData",
   			timeout : 100000,
   			success : function(data) {
   				    var scheduleForm="";
	   				for(i=0;i<data.length;i++){	
		   				scheduleForm+='<tr><td>'+data[i].date_of_travel
		   								+'</td><td>'+data[i].destination_name
		   								+'</td><td>'+data[i].total_available_seats
		   								+'</td><td>'+data[i].customer_seats
		   								+'</td><td>'+data[i].staff_seats
		   								+'</td><td>'+data[i].student_seats
		   								+'</td><td>'+data[i].remaining_seats
		   								+'</td><td><span class="userDetail" id="'+data[i].schedule_id
		   						        +'" style="color:#ff9800;cursor:pointer">detail</span></td></tr>';	
	   					}
	   					document.getElementById('getSchedule').innerHTML=scheduleForm;
   			},
   			error : function(e) {
   				console.log("ERROR: ", e);
   			},
   			done : function(e) {
   				console.log("DONE");
   			}
   		});
   		
   	    
   		//Passenger Detail
   		$('.userDetail').click(function() {
   		    var sch_id=this.id ;
   		    Passenger_detail(sch_id)
   	    });
		function Passenger_detail(sch_id){
			console.log("fun: "+sch_id); 
			$.ajax({
	   	   				type : "POST",
	   	   				contentType : "application/json",
		   	   			async:false,
		   				cache:false,
	   	   				data :sch_id,
	   	   				url : "passengerDetail",
	   	   				timeout : 100000,
	   	   				success : function(data) {
	   	   					var passen_modal="";
	   	   					for(i=0;i<data.length;i++){
	   	   						console.log(data[i].passenger.length);
		   	   					passen_modal+='<div class="row"><div class="col s6">&nbsp&nbsp Bus Model:&nbsp&nbsp<b>'
		   	   									+data[i].bus_model+'</b></div><div class="col s6 right">Departure Time:&nbsp&nbsp<b>'
		   	   									+data[i].departure_time+'</b></div></div>'
		   	   									+'<div class="row"><div class="col s6">&nbsp&nbsp Bus Driver:&nbsp&nbsp<b>'
		   	   									+data[i].bus_driver+'</b></div><div class="col s6 right">Arrival Time:&nbsp&nbsp<b>'
		   	   									+data[i].arrival_time+'</b></div></div>'
		   	   			      						+'<table class="bordered centered highlight">'
		   	 		        						+'<thead><tr> <th>No.</th> <th>Name</th>'
		   	 		        						+'<th>Batch</th> <th>Role</th> <th>Seat Number</th></tr> </thead>';
		   	  					for(j=0;j<data[i].passenger.length;j++){
		   			   					passen_modal+='<tbody><tr><td>'+(j+1)
		   			   								+'</td><td>'+data[i].passenger[j].passenger_name
		   			   								+'</td><td>'+data[i].passenger[j].batch
		   			   								+'</td><td>'+data[i].passenger[j].role
		   			   								+'</td><td>'+data[i].passenger[j].seat_number
		   			   								+'</td></tr>';	
		   	  						}
		   	  					passen_modal+='</tbody></table><br>';
	   	   					}
	   	  					document.getElementById('modal_body').innerHTML=passen_modal;
							$('#user_detail').modal('open');
	   	   					          
	   	   					},
	   	   					error : function(e) {
	   	   						console.log("ERROR: ", e);
	   	   					},
	   	   					done : function(e) {
	   	   						console.log("DONE");
	   	   					}
	   	   				});
		}
		
		
		 
   		$('#check_in_date').change(function(){      
			if(itera_date_travel==2){
				date_of_travel = $('#check_in_date').val().split(';');
	   		 	console.log(date_of_travel);	
	   		} 		
	   		else{
	   		 	date_of_travel[0] = $('#check_in_date').val();
	   		 	console.log(date_of_travel)	
	   		}  
			}); 
			
		//Booking Select
		$('#book_now').click(function(){
			 console.log(back+" "+from+" "+date_of_travel.length);
			 if((back==null&&itera_date_travel==2)||from==null||date_of_travel.length==0){
				 alert("Unvalid Data!"); 
			 }
			 else{
				 var destination_id=[from,back];
				 var submit = [];
		      	 for(var i=0;i<itera_date_travel;i++){	 
		      		submit[i]={"destination_id":destination_id[i],
		      				"date_of_travel":date_of_travel[i],
		      				"user_id":user_id
		      			};
		      		}
		      	    console.log(submit);
		      	  $.ajax({
						type : "POST",
						url : "booking",
						contentType : "application/json",
						data : JSON.stringify(submit),
						timeout : 100000,
						success : function(data) {
							console.log(data);	
							alert("Booking Done");
						},
						error : function(e) {
							console.log("ERROR: ", e);
							
						},
						done : function(e) {
							console.log("DONE");
						}
					});
			 }
	      			 
		 });
		function request_emer_booking(sch_id,reason){
		 var submit={'user_id':user_id,'schedule_id':sch_id,'reason':reason}
		 $.ajax({
					type : "POST",
					url : "emergency_booking",
					contentType : "application/json",
					data : JSON.stringify(submit),
					timeout : 100000,
					success : function(data) {
						console.log(data);	
						alert("Booking Done");
					},
					error : function(e) {
						console.log("ERROR: ", e);
						
					},
					done : function(e) {
						console.log("DONE");
					}
				});
	 	}
		$('#permission_date').flatpickr({
			mode: "single",
		    minDate: "today",
		    dateFormat: "Y-m-d"
		}); 
		$('.permission_request').click(function(){
			console.log("Hello");
			var date_of_request=$('#permission_date').val();
			var reason=$('#permission_reason').val();
			var submit={'user_id':user_id,'date_of_request':date_of_request,'reason':reason}
			console.log(submit);
			$.ajax({
				type : "POST",
				url : "permission_request",
				contentType : "application/json",
				data : JSON.stringify(submit),
				timeout : 100000,
				success : function(data) {
					console.log(data);	
					alert("Booking Done");
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		});
		 //Show Batch When Donate 
		$('#req_donate').click(function(){
			 $.ajax({
					type : "GET",
					url : "req_donate",
					contentType : "application/json",
					timeout : 100000,
					success : function(data) {
						console.log(data[0].batch_number);	
						var batch_donate='<option value="" disabled selected>Choose Batch</option>'
							for(i=0;i<data.length;i++){
								batch_donate+='<option value="'+ data[i].batch_id +'"> Batch '+data[i].batch_number+'</option>'
							}
						document.getElementById('donate_batch').innerHTML=batch_donate;
						$('#donate_batch').material_select();

					},
					error : function(e) {
						console.log("ERROR: ", e);
						
					},
					done : function(e) {
						console.log("DONE");
					}
				}); 
		 })
		 
		 
		//Show User When Donate 
		$('#donate_batch').change(function(){
			 var bat_id=$('#donate_batch').val();
			 var submit={"batch_id":bat_id,"user_id":user_id};
			 console.log(submit)
			 $.ajax({
					type : "POST",
					url : "req_donate_username",
					contentType : "application/json",
					data : JSON.stringify(submit),
					timeout : 100000,
					success : function(data) {
						console.log(data);	
						var batch_donate='<option value="" disabled selected>Donate to</option>'
							for(i=0;i<data.length;i++){
								batch_donate+='<option value="'+ data[i].user_id +'"> '+data[i].username+'</option>'
							}
						document.getElementById('danate_to_username').innerHTML=batch_donate;
						$('#danate_to_username').material_select();

					},
					error : function(e) {
						console.log("ERROR: ", e);
						
					},
					done : function(e) {
						console.log("DONE");
					}
				}); 
		 })
		 
		 
		 //When Donate btn click
		$('#donate_btn').click(function(){
			 var donate_to=$('#danate_to_username').val();
			 var no_ticket=$('#no_ticket').val();
			 if(donate_to!=""||donate_to!=null||no_ticket!=""||no_ticket!=null){
				 var submit={"receive_from":user_id,"donate_to":donate_to,"no_ticket":no_ticket};
				 console.log(submit)
				 $.ajax({
						type : "POST",
						url : "start_donate",
						contentType : "application/json",
						data : JSON.stringify(submit),
						timeout : 100000,
						success : function(data) {
							console.log(data);
							if(data==true){
								alert('Donating Success!')
							}else if(data==null||data==''){
								alert('Not Enough Ticket to Donate');
								
							}else{
								alert('Error, Please Try again');
							}
						},
						error : function(e) {
							console.log("ERROR: ", e);
							
						},
						done : function(e) {
							console.log("DONE");
						}
					}); 
			 }
		 })
		 
		 
		 //To check whether can exchange or not
		check_exchange();
		function check_exchange(){
			$.ajax({
				type : "POST",
				url : "check_exchange",
				contentType : "application/json",
				data:user_id,
				timeout : 100000,
				success : function(data) {
						console.log("Hello"+data);
						if(data==null||data==""){
							console.log("done");
						}
						else{
							var exch='<li><a id="req_exchange" href="#exchangeSeat"><i class="Tiny material-icons left">cached</i>Exchange Seat</a></li><li class="divider"></li>';
							document.getElementById('li_exchange').innerHTML=exch;
							
						}	
						$('#req_exchange').click(function(){
							console.log("Check")
							req_exchange();
						})
				},
				error : function(e) {
					console.log("ERROR: ", e);
						
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}
		
		 
		//Show Batch When Exchange 
		function req_exchange(){
				 $.ajax({
						type : "GET",
						url : "req_donate",
						contentType : "application/json",
						timeout : 100000,
						success : function(data) {
							//console.log(data[0].batch_number);	
							var batch_ex='<option value="" disabled selected>Choose Batch</option>'
								for(i=0;i<data.length;i++){
									batch_ex+='<option value="'+ data[i].batch_id +'"> Batch '+data[i].batch_number+'</option>'
								}
							document.getElementById('exchange_batch').innerHTML=batch_ex;
							$('#exchange_batch').material_select();

						},
						error : function(e) {
							console.log("ERROR: ", e);
							
						},
						done : function(e) {
							console.log("DONE");
						}
					}); 
			 }
			 
		//Show User When Exchange 
		$('#exchange_batch').change(function(){
				 var bat_id=$('#exchange_batch').val();
				 var submit={"batch_id":bat_id,"user_id":user_id};
				 $.ajax({
						type : "POST",
						url : "req_donate_username",
						contentType : "application/json",
						data : JSON.stringify(submit),
						timeout : 100000,
						success : function(data) {
							console.log(data);	
							var batch_ex='<option value="" disabled selected>select username</option>'
								for(i=0;i<data.length;i++){
									batch_ex+='<option value="'+ data[i].user_id +'"> '+data[i].username+'</option>'
								}
							document.getElementById('exchange_to_username').innerHTML=batch_ex;
							$('#exchange_to_username').material_select();

						},
						error : function(e) {
							console.log("ERROR: ", e);
							
						},
						done : function(e) {
							console.log("DONE");
						}
					}); 
			 })
			 
	     //When Exchange btn click
		$('#exchange_seat').click(function(){		 
			 var receive_from=user_id;
			 var exchange_to=$('#exchange_to_username').val();
			 if(receive_from!=""||receive_from!=null||exchange_to!=""||exchange_to!=null){
				 var submit={"receive_from":receive_from,"exchange_to":exchange_to};
				 console.log(submit)
				 $.ajax({
					    async:false,
						cache:false,
						type : "POST",
						url : "start_exchange_seat",
						contentType : "application/json",
						data : JSON.stringify(submit),
						timeout : 100000,
						success : function(data) {
							console.log(data);
							if(data==true){
								alert('Exchange Success!')
							}else{
								alert('Error, Please Try again');
							}
						},
						error : function(e) {
							console.log("ERROR: ", e);
							
						},
						done : function(e) {
							console.log("DONE");
						}
					}); 
			 }
		 })
		 
		 
		//schedule for emergency
		 $('#emer_booking').click(function(){
			 $.ajax({
					type : "POST",
					async:false,
					cache:false,
					contentType : "application/json",
					url : "checkEmergency",
					data:user_id,
					timeout : 100000,
					success : function(data) {
						    var scheduleForm="";
			   				for(i=0;i<data.length;i++){	
				   				scheduleForm+='<tr><td>'+data[i].date_of_travel
				   								+'</td><td>'+data[i].destination_name
				   								+'</td><td>'+data[i].total_available_seats
				   								+'</td><td>'+data[i].customer_seats
				   								+'</td><td>'+data[i].staff_seats
				   								+'</td><td>'+data[i].student_seats
				   								+'</td><td>'+data[i].remaining_seats
				   								+'</td><td><span class="userDetail light-blue-text" value="'+data[i].schedule_id
				   						        +'" style="cursor:pointer">detail</span></td>'
				   						        +'</td><td><a class="red lighten-1 btn text-while emer_booking_btn" value="'+data[i].schedule_id
				   						        +'" style="cursor:pointer" href="#emer_reasonForm">Booking</a></td></tr>';
			   					}
			   					document.getElementById('getEmergencyForm').innerHTML=scheduleForm;
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				$('.userDetail').click(function() {
					var sch_id=$(this).attr('value') ;
					Passenger_detail(sch_id)
				});
				$('.emer_booking_btn').click(function() {
					var sch_id=$(this).attr('value') ;
					var reason_form='<a class="modal-action waves-effect waves-green btn-flat clk_emergency" value="'+sch_id+'">Request</a>'
					console.log(reason_form)
					document.getElementById('getBtnEnergency').innerHTML=reason_form;
					$('.clk_emergency').click(function() {
						var sch_id=$(this).attr('value') ;
						var reason=$(emer_reason).val();
						console.log(sch_id+"  "+reason);
						request_emer_booking(sch_id,reason);
					});
				});
				
		 })
		



//Mobile Part	 
	$('.collapsible').collapsible();
	
	var m_date_of_travel=[];
	var m_selectRound=null;
	var m_back=null;
	var m_from=null;
	var m_itera_date_travel=2;
		 
		//handle date select
	$('#m_roundWay').change(function(){
	 	m_selectRound=true;
	 	m_itera_date_travel=2;
	 	m_date_of_travel=null;
	 	mOptionSelect(m_itera_date_travel);
	 	m_date_of_travel=[];
		$(".flatpickr input").flatpickr({
			mode: "range",
			minDate: "today",
			dateFormat: "Y-m-d",
		});
	 })
	 $('#m_oneWay').change(function(){
	 	m_itera_date_travel=1;
	 	m_date_of_travel=null;
	 	mOptionSelect(m_itera_date_travel);
	 	m_date_of_travel=[];
	    $(".flatpickr input").flatpickr({
			mode: "single",
			minDate: "today",
			dateFormat: "Y-m-d",
			disableMobile: "true",
		});
	 })

	//select form
	var m_destination="";  //user in select
	$.ajax({
			type : "GET",
			async:false,
			cache:false,
			contentType : "application/json",
			url : "selectService",
			timeout : 100000,
			success : function(data) {
				console.log("select");
				m_destination=data;
				var select='<select id="m_fromDes" class="validate"><option disabled selected>From</option>'
				for(i=0;i<m_destination.length;i++){
					select +='<option value="'+m_destination[i].destination_id+'">'+m_destination[i].destination_name+'</option>';								
				};
				select+='</select>';
				document.getElementById("m_getSelectFrom").innerHTML = select;						
				$('#m_fromDes').material_select();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
	});
	
	function mOptionSelect(m_itera_date_travel){
		if(m_itera_date_travel==2){
			var backForm='<div class="col s12"><div class="input-field s6 "><select class="validate m_to"><option disabled selected>Return</option></select></div><br></div>';
			document.getElementById("m_getSelectTo").innerHTML = backForm;
			$('.m_to').material_select();
		}else{
			var backForm='';
			document.getElementById("m_getSelectTo").innerHTML = backForm;						
		}	
		 }
		 //handle event select destinaton
	$('#m_fromDes').change(function(){
		m_from = $("#m_fromDes").val();
		console.log("from: "+m_from);
		if(m_itera_date_travel==2){
			var toDesForm='<div class="col s12"><div id="m_toDes" class="input-field s6 "><select class="validate sl">';
			for(i=0;i<m_destination.length;i++){
				if(m_destination[i].destination_id!=m_from){
					toDesForm +='<option value="'+m_destination[i].destination_id+'" selected>'+m_destination[i].destination_name+'</option>';
					//Chnage this after finish
					m_back = m_destination[i].destination_id;
					console.log("back: "+m_back);
				}
			}
			toDesForm+='</select></div><br></div>';
			document.getElementById("m_getSelectTo").innerHTML = toDesForm;
			$('.sl').material_select();
		}
	 });
	    

	$('#m_check_in_date').change(function(){      
		if(m_itera_date_travel==2){
			m_date_of_travel = $('#m_check_in_date').val().split('to');
   		 	console.log(m_date_of_travel);	
   		} 		
   		else{
   		 	m_date_of_travel[0] = $('#m_check_in_date').val();
   		 	console.log(m_date_of_travel)	
   		}  
		}); 
		
	 //Booking Select
	$('#m_book_now').click(function(){
		 if((m_back==null&&m_itera_date_travel==2)||m_from==null||m_date_of_travel.length==0){
			 alert("Unvalid Data!"); 
		 }
		 else{
			 console.log("book");
			 var m_date_of_booking =new Date($.now());
			 var m_destination_id=[m_back,m_from];
			 var submit = [];
	      	 for(var i=0;i<m_itera_date_travel;i++){	 
	      		submit[i]={"destination_id":m_destination_id[i],
	      				"date_of_travel":m_date_of_travel[i],
	      				"date_of_booking":""+m_date_of_booking};
	      		}
	      	    console.log(submit);
	      	  $.ajax({
					type : "POST",
					url : "booking",
					contentType : "application/json",
					data : JSON.stringify(submit),
					timeout : 100000,
					success : function(data) {
						console.log(data);	
						alert("Booking Done");
					},
					error : function(e) {
						console.log("ERROR: ", e);
						
					},
					done : function(e) {
						console.log("DONE");
					}
				});
		 }
		
      			 
	 });
	 
	 
	
	 
	 

		 

});
