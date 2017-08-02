$(document).ready(function() {
		var date_of_travel=[];
		var selectRound=null;
		var back=null;
		var from=null;
		var itera_date_travel=2;
		
		

		 $('.dropdown-icon, .dropdown-user-info').dropdown({
		      inDuration: 300,
		      outDuration: 225,
		      constrainWidth: false, // Does not change width of dropdown to that of the activator
		      hover: true, // Activate on hover
		      gutter: 0, // Spacing from edge
		      belowOrigin: true, // Displays dropdown below the button
		      alignment: 'left', // Displays dropdown with edge aligned to the left of button
		      stopPropagation: false // Stops event propagation
		    }
		  );
		 $(".button-collapse").sideNav();
   		 $('select').material_select();   
   		 $('.modal').modal();
   		 $(".flatpickr input").flatpickr({
				mode: "range",
			    minDate: "today",
			    dateFormat: "Y-m-d"
			});   		 
	    
	    
   		 /*
	    //user_info//not yet
  		 $.ajax({
				type : "GET",
				contentType : "application/json",
				url : "userInfo",
				timeout : 100000,
				success : function(data) {
					//console.log(data);
					var userInfoForm="";
					userInfoForm+='<li><a href="#!">'+data.username
								+'</a></li><li class="divider"></li><li><a href="#!">'+
								data.user_id+'</a></li><li class="divider"></li><li><a href="#!">No.Ticket:&nbsp &nbsp'+
								data.number_of_ticket+'</a></li><li class="divider"><li><a href="logout">Log Out</a></li><li class="divider"></li>'
					document.getElementById("user_info").innerHTML = select;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
	    */
	    
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
				mode: "range",
				minDate: "today",
				dateFormat: "Y-m-d"
			});
		 })
		 $('#oneWay').change(function(){
		 	itera_date_travel=1;
		 	optionSelect(itera_date_travel);
		 	date_of_travel=[];
		    $(".flatpickr input").flatpickr({
				mode: "single",
				minDate: "today",
				dateFormat: "Y-m-d"
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
   		    console.log(sch_id);
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
	   	   		 	

   	    });
		 
   		 $('#check_in_date').change(function(){      
			if(itera_date_travel==2){
				date_of_travel = $('#check_in_date').val().split('to');
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
				 console.log("book");
				 var date_of_booking =new Date($.now());
				 var destination_id=[back,from];
				 submit = [];
		      	 for(var i=0;i<itera_date_travel;i++){	 
		      		submit[i]={"destination_id":destination_id[i],
		      				"date_of_travel":date_of_travel[i],
		      				"date_of_booking":""+date_of_booking};
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
			 submit = [];
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
		//test
		var user_id="t4jrtfh385";
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
//Define user_id		 
		 var receive_from='t4jrtfh385';
		 var donate_to=$('#danate_to_username').val();
		 var no_ticket=$('#no_ticket').val();
		 if(donate_to!=""||donate_to!=null||no_ticket!=""||no_ticket!=null){
			 var submit={"receive_from":receive_from,"donate_to":donate_to,"no_ticket":no_ticket};
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
});
