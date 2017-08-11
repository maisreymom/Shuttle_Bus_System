$(document).ready(function(){
    $('ul.tabs').tabs();
    $('.modal').modal();
    $(".button-collapse").sideNav();
    $('#modal2').modal({
    	dismissible: false
    });
    $('select').material_select();
    $('#modal3').modal();
    $('#view_schedule').modal();
    $('#edit_bus_list').modal();
    $('#delete_bus_list').modal();

    $('#detail_c_pass').modal();

    $('#leaveDate').flatpickr({
		mode: "single",
		minDate: "today",
		dateFormat: "Y-m-d"
    });
    $('#returnDate').flatpickr({
		mode: "single",
		minDate: "today",
		dateFormat: "Y-m-d"
    });
    $('#deadlineBooking').flatpickr({
		mode: "single",
		minDate: "today",
		dateFormat: "Y-m-d h:i K",
		enableTime: true
    });



	
		var data_schedule =[];
		 $.ajax({
			 	async:false,
			 	cache:false,
				type : "GET",
				contentType : "application/json",
				url : "show_schedule",
				
				
				timeout : 100000,
				success : function(data) {
					data_schedule = data;
					var tr='';
					var td='';
					var view='';
					var set='';
					for(var i=0;i<data.length;i++){
						if(data[i]["student"]==undefined){
							data[i]["student"]=0;
						}
						if(data[i]["staff"]==undefined){
							data[i]["staff"]=0;
						}
						if(data[i]["customer"]==undefined){
							data[i]["customer"]=0;
						}
						if(data[i]["status"]){
							set = '<a class="modal-trigger view_pass_booked" value="'+data[i]["date"]+"->"+data[i]["destination_id"]+'" href="#view_schedule">View</a>/<a class="modal-trigger" href="#modal2">Edit</a>';
						}
						else {
							set = '<a class="modal-trigger" href="#modal2">Set</a>';
						}
					    td = '<td>' + data[i]["date"] +'</td>'
						+ '<td>' + data[i]["destination"] + '</td>'
						+ '<td>' + data[i]["student"] + '</td>'
						+ '<td>' + data[i]["staff"] + '</td>'
						+ '<td>' + data[i]["customer"] + '</td>'
						+ '<td class="detail" value="'+i+'"> <a class="modal-trigger" href="#modal1">Detail</a></td>'
						+ '<td class="set_schedule" value="'+i+'">'+set+'</td>';

						tr = tr + '<tr>' + td + '</tr>';


					}
					document.getElementById('getschedule').innerHTML = tr;
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		 $(".detail").click(function() {
		
			var tr ='<tr>';
			var td ='';
			
		

			var arr = $(this).attr('value');
			
			var i = parseInt(arr);
			
		
						for(var j=0;j<data_schedule[i]["list"].length;j++){
							td ='<td>' + data_schedule[i]["list"][j]["name"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["batch"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["role"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["email"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["phone"] + '</td>'
							
							tr = tr + td + '</tr>';
							
						}
						for(var j=0;j<data_schedule[i]["list_cus"].length;j++){
							td ='<td>' + data_schedule[i]["list_cus"][j]["name"] + '</td>'
							+ '<td>' + data_schedule[i]["list_cus"][j]["batch"] + '</td>'
							+ '<td>' + data_schedule[i]["list_cus"][j]["role"] + '</td>'
							+ '<td>' + data_schedule[i]["list_cus"][j]["email"] + '</td>'
							+ '<td>' + data_schedule[i]["list_cus"][j]["phone"] + '</td>';
							tr = tr + td + '</tr>';
						}
	
			
			document.getElementById('detail_title').innerHTML = 'User Detail on '+arr[0];
			document.getElementById('detail_pass').innerHTML = tr;
			
		 });

		 $("a.view_pass_booked").click(function(){
		 	var param = $(this).attr('value').split('->');
			var date_dest = {"date_of_travel":param[0],"destination_id":param[1]};
			
			$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(date_dest),
    					url : "detail_pass_schedule",
    					timeout : 100000,
    					success : function(data) {
    						var tr='';
    						var td='';
    						for(var i=0;i<data.length;i++){
    							td ='<td>'+ data[i]["driver"] + '</td>'
    								+ '<td>' + data[i]["bus"] + '</td>'
    								+ '<td>' + data[i]["remaining"] + '</td>'
    								+ '<td>' + data[i]["total_seats"] + '</td>'
    								+ '<td>' + data[i]["departure"] + '</td>'
    								+ '<td>' + data[i]["arrivel"] + '</td>'
    								+ '<td>' + data[i]["customer_only"] + '</td>'
    								+ '<td id="detail_book" value="'+i+'">' + '<a class="modal-trigger" href="#detail_c_pass">view</a>' + '</td>';
    							tr = tr + '<tr>' + td + '</tr>';
    						}
    						
    						document.getElementById('view_pass').innerHTML = tr;
    						$("#detail_book").click(function(){
    							var index = parseInt($(this).attr('value'));
    							console.log(index);
    							var td='';
    							var tr='';
    							for(var i=0;i<data[index]["detail"].length;i++){
    							td ='<td>'+ data[index]["detail"][i]["name"] + '</td>'
    								+ '<td>' + data[index]["detail"][i]["role"] + '</td>'
    								+ '<td>' + data[index]["detail"][i]["batch"] + '</td>'
    								+ '<td>' + data[index]["detail"][i]["email"] + '</td>'
    								+ '<td>' + data[index]["detail"][i]["phone"] + '</td>';
    							tr = tr + '<tr>' + td + '</tr>';
    						}
    						document.getElementById('view_c_pass').innerHTML = tr;
    						});
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});
		 });
		
      
		 $.ajax({
			 	async:false,
			 	cache:false,
				type : "GET",
				contentType : "application/json",
				url : "user",
				timeout : 100000,
				success : function(data) {
				
					var tr ='<tr>';
					var td="";
					
					for(var i=0;i<data.length;i++){
						
						  td = '<td>' + i + '</td>'
							 + '<td>' + data[i]["full_name"] + '</td>'
							 + '<td>' + data[i]["password"] + '</td>'
							 + '<td>' + data[i]["batch"] + '</td>'
							 + '<td>' + data[i]["email"] + '</td>'
							 + '<td>' + data[i]["gender"] + '</td>'
							 + '<td>' + data[i]["role"] + '</td>'
							 + '<td>' + data[i]["phone"] + '</td>';
							
							 tr = tr + td + '</tr>';
					                      		 
					}
					document.getElementById('user').innerHTML = tr;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		 var bus = [];
		 $.ajax({
			 	async:false,
			 	cache:false,
				type : "GET",
				contentType : "application/json",
				url : "schedule_bus",
				timeout : 100000,
				success : function(data) {
					bus = data;
					var tr ='<tr>';
					var td="";
					
					for(var i=0;i<data.length;i++){
						
						  td = '<td>' + (i+1) + '</td>'
							 + '<td>' + data[i]["no_seats"] + '</td>'
							 + '<td>' + data[i]["bus_model"] + '</td>'
							 + '<td>' + data[i]["plate_number"] + '</td>'
							 + '<td>' + '<a href="#edit_bus_list" value="'+data[i]["bus_id"]
							 +'" class="edit_bus">Edit</a> / <a class="delete_bus" href="#delete_bus_list" value="'+ data[i]["bus_id"] +'">Delete</a>' + '</td>';
							
							
							 tr = tr + td + '</tr>';
					                      		 
					}
					document.getElementById('schedule_bus').innerHTML = tr;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			 var confirm_validation;
			 var edit_id;
			 var delete_id;
    		 $(".edit_bus").click(function() {
    	 	   		edit_id = $(this).attr('value');
    	 	   		
    	 	   		for(var i = 0 ; i <bus.length;i++){
    	 	   			if(bus[i]["bus_id"]==edit_id){
    	 	   				confirm_validation = i;
    	 	   				$('#edit_num_seats').val(bus[i]["no_seats"]);
    	 	   				$('#edit_bus_model').val(bus[i]["bus_model"]);
    	 	   				$('#edit_plate_number').val(bus[i]["plate_number"]);
    	 	   				break;
    	 	   			}

    	 	   		}
    	 	   		
    	 	   				
    	 	   });

    		  $(".edit_bus_btn").click(function() {
    		  	var no = $('#edit_num_seats').val();
   				var model = $('#edit_bus_model').val();
   				var plate = $('#edit_plate_number').val();
   				var edit={"total_seats":0,'bus_model':'not_update','plate_number':'not_update','bus_id':edit_id};
   				var status = false;
   				if(no != bus[confirm_validation]["no_seats"]){
   					edit["total_seats"]= parseInt(no);
   					status =true;
   				}
   				if(model != bus[confirm_validation]["bus_model"]){
   					edit["bus_model"] = model;
   					status =true;

   				}
   				if(plate != bus[confirm_validation]["plate_number"]){
   					edit["plate_number"] = plate;
   					status =true;
   				}

   				if(status){
   					$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(edit),
    					url : "edit_bus",
    					timeout : 100000,
    					success : function(data) {
    						
    						                      		 
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

    		  $(".delete_bus").click(function() {
    	 	   		delete_id = $(this).attr('value');
    	 	   		
    	 	   				
    	 	   });
    		  $(".delete_bus_btn").click(function() {
    	 	   		$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : delete_id,
    					url : "delete_bus",
    					timeout : 100000,
    					success : function(data) {
    						
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});
    	 	   				
    	 	   });

    		  
    		  
		 var count_schedule=0;
		 var count_schedule_element=[];
		 var display_set=[];
		 $(".add_s").click(function() {
		 		
				var driver_list=[];
				var bus_list = [];
		 		$.ajax({
			 	async:false,
			 	cache:false,
				type : "GET",
				contentType : "application/json",
				url : "driver_list",
				timeout : 100000,
				success : function(data) {
					driver_list = data;
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});

		 		$.ajax({
			 	async:false,
			 	cache:false,
				type : "GET",
				contentType : "application/json",
				url : "schedule_bus",
				timeout : 100000,
				success : function(data) {
					bus_list =data;
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		 		
		 		var bus_model ='';
		 		var driver_name = '';
		 		var total_seats = '';
		 		var departure = '';
		 		var arrival = '';
		 		var customer ='';
		 		var delete_schedule = '';
		 		var customer_only='';
	            for(var i=0;i<bus_list.length;i++){
	            	bus_model = bus_model + '<option value="'+bus_list[i]["bus_id"]+'">' + bus_list[i]["bus_model"] + '</option>';
	            }
	            for(var i=0;i<driver_list.length;i++){
	            	driver_name = driver_name + '<option value="'+driver_list[i]["user_id"]+'">' + driver_list[i]["driver_name"] + '</option>';
	            }
	            for(var i=0;i<data_schedule[index_of]["list_cus"].length;i++){
	            		customer = customer + '<option value="'+data_schedule[index_of]["list_cus"][i]["user_id"] +'">'+ data_schedule[index_of]["list_cus"][i]["name"] + '</option>';
	            }
				bus_model = '<td id="bus_model'+count_schedule+'"><select>'+ bus_model +'</select></td>';
				driver_name = '<td id="driver_name'+count_schedule+'"><select>'+driver_name +'</select></td>';
				total_seats = '<td id="total_seats'+count_schedule+'"><input type="text" placeholder="total seats"></td>';
				departure = '<td><div class="flatpickr departure  data_departure'+count_schedule+'">'
              				+'<input type="text" placeholder="Select Time" data-input  class=" input flatpickr-input active"></div></td>';
              	arrival = '<td><div class="flatpickr arrival data_arrival'+count_schedule+'">'
                +'<input type="text" placeholder="Select Time" data-input  class="input flatpickr-input active"></div></td>';
                customer = '<td ><select multiple class="customer'+count_schedule+'"><option disabled>customer1</option>'
                + customer+'</select></td>';
                delete_schedule = '<td class="delete_icon"><i class="material-icons" value="'+count_schedule+'">delete</i></td>'
				customer_only = '<td><input type="checkbox" id="check'+count_schedule+'" /><label for="check'+count_schedule+'"></label></td>'
				tr = '<tr class="add_input" id="tr_'+count_schedule+'">' + bus_model + driver_name + customer+total_seats + departure + arrival+ customer_only+ delete_schedule+ '</tr>';
				
					
				
				$("#add_more_schedule" ).append( tr );
				$('select').material_select();
				
				
				count_schedule_element[count_schedule_element.length]= count_schedule;
				count_schedule++;
				
				$(".departure input").flatpickr({
		    	 		enableTime: true,
					    noCalendar: true,
					    enableSeconds: false, 
					    time_24hr: false,
					    dateFormat: "H:i", 
					    defaultHour: 12,
					    defaultMinute: 0
		   			 });
				  $(".arrival input").flatpickr({
		    	 		enableTime: true,
					    noCalendar: true,

					    enableSeconds: false, // disabled by default

					    time_24hr: false, // AM/PM time picker is used by default

					    // default format
					    dateFormat: "H:i", 

					    // initial values for time. don't use these to preload a date
					    defaultHour: 12,
					    defaultMinute: 0
		   			 });
			$(".delete_icon i").click(function() {
    		var data = $(this).attr("value");
    		
    		document.getElementById('tr_'+data).innerHTML = '';
    		
    		
    		
    		//console.log(count_schedule_element);
    		
    		for(var i = 0 ;i<count_schedule_element.length;i++){
    			if(count_schedule_element[i]==parseInt(data)){
    				count_schedule_element.splice(i, 1);
    				break;
    			}
    		}
    			
    		
    		
    		return data;
		  
    	});

		 });
    	


    	$("#set_schedule").click(function() {
    		var schedule_data=[];
    		var data_list = [];
    		data_list = data_schedule[index_of]["list"];
    		var assign_id=[];
    		var remaining_seats=0;
    		var count_staff=0;
    		var count_user=0;
    		var count_pass=0;
    		var validate_schedule=[];
    		var tts =0;
    		var vbs='';
    		var not_set_cus=0;
    		var count_cus=0;
    		var conflict_driver=false;
    		var conflict_bus=false;
    		var vtotal_seats='';
    		var vdeparture='';
    		for(var i=0;i<count_schedule_element.length;i++){
				var id_customer = $(".customer"+count_schedule_element[i]+" select").val();
				var check = $('input:checkbox:checked#check'+i).val();
				if(check){
					for(var j=0;j<parseInt($("#total_seats"+count_schedule_element[i]+" input" ).val());j++){
						if(id_customer[j]){
							assign_id[j] = id_customer[j];
							
						}
						else break;
					
				}
				}
				else{
					for(var j=0;j<parseInt($("#total_seats"+count_schedule_element[i]+" input" ).val());j++){
					if(id_customer[j]){
						assign_id[j] = id_customer[j];
						
					}
					else if(data_list[count_user]){
							if(data_list[count_user]["role"]=='staff'){
								count_staff++;
							}
						assign_id[j] = data_list[count_user]["user_id"];
						
						count_user++;	
					}

					else break;
					
				}
				}
				
				console.log(id_customer);
				remaining_seats = parseInt($("#total_seats"+count_schedule_element[i]+" input" ).val()) -assign_id.length;
				
				schedule_data[i] = {"bus_id":$( "#bus_model"+count_schedule_element[i]+" select" ).val(),
									"driver_name":$( "#driver_name"+count_schedule_element[i]+" select" ).val(),
									"total_seats":parseInt($( "#total_seats"+count_schedule_element[i]+" input" ).val()),
									"passenger_id":assign_id,
									"customer":id_customer.length,
									"student":assign_id.length - id_customer.length - count_staff,
									"staff":count_staff,
									"remaining_seats":remaining_seats,
									"date_of_travel":data_schedule[index_of]["date"],
									"destination_id":data_schedule[index_of]["destination_id"],
									"est_arrival":$('.data_arrival'+count_schedule_element[i]+' input').val(),
									"est_departure":$('.data_departure'+count_schedule_element[i]+' input').val()};

				count_cus = count_cus + id_customer.length;
				count_pass = count_pass + assign_id.length;
				count_staff=0;
				tts = tts + parseInt($( "#total_seats"+count_schedule_element[i]+" input" ).val());
				if(schedule_data[i]['passenger_id'].length<1&&schedule_data[i]['total_seats']>0){
					vbs+= "Bus"+(1+i) +",";
				}

				if(!schedule_data[i]["total_seats"]){
					vtotal_seats+= 'Bus'+(1+i)+", ";
				}
				if(!schedule_data[i]["est_departure"]){
					vdeparture+= 'Bus'+(1+i)+", ";
				}
				not_set_cus = not_set_cus + id_customer.length;



				assign_id = [];

    		}
    		
    		for(var i=0;i<schedule_data.length;i++){
    			for(var j=i+1;j<schedule_data.length;j++){
    				if(schedule_data[i]["driver_name"]==schedule_data[j]["driver_name"]){
    					conflict_driver = true;
    				}
    				if(schedule_data[i]["bus_id"]==schedule_data[j]["bus_id"]){
    					conflict_bus = true;
    				}
    			}
    		}
    		console.log(schedule_data);
    		if(count_cus>data_schedule[index_of]['list_cus'].length){
    			//console.log(1+validate_schedule.length+". Set Conflict customer")
    			validate_schedule[validate_schedule.length] = "Set Conflict customer";
    		}
    		if(tts<data_schedule[index_of]["list"].length+data_schedule[index_of]["list_cus"].length){
    			console.log(1+validate_schedule.length+". Total Seats Not Enough For Passnger")
    			validate_schedule[validate_schedule.length] = "Total Seats Not Enough For Passnger";
    		}
    		if(vbs){
    			
    			
    			
    		
    			validate_schedule[validate_schedule.length]= vbs+ " Does Not Have Passnger";
    			
    		}
    		if(not_set_cus<data_schedule[index_of]["list_cus"].length){
    			validate_schedule[validate_schedule.length] ="You Not Set Customer!";
    		}
    		if(conflict_bus){
    			validate_schedule[validate_schedule.length] = "Conflict Bus";
    		}
    		if(conflict_driver){
    			validate_schedule[validate_schedule.length] = "Conflict Driver";
    		}
    		if(vtotal_seats){
    			validate_schedule[validate_schedule.length] = vtotal_seats + "Not Set Available Seate for Passnger";
    		}
    		if(vdeparture){
    			validate_schedule[validate_schedule.length] = vdeparture + " Not Set Departure Time";
    		}

    		if(validate_schedule.length>0){
    			alertValidate(validate_schedule,schedule_data);
    		}
    		else{
    			$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(schedule_data),
    					url : "set_schedule_passenger",
    					timeout : 100000,
    					success : function(data) {
    						if(data["status"]){
    							$('#modal2').modal('close');
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
    		
    		/*$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(schedule_data),
    					url : "set_schedule_passenger",
    					timeout : 100000,
    					success : function(data) {
    						
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});

    		*/
			
    	});
    	function alertValidate(problem,data_submit) {
    		var content='';
    		console.log(problem);
    		for(var i=0;i<problem.length;i++){
    			
				  content = content+'<li>'+problem[i]+'</li>'
				  
				
    		}
    		content = '<ol>' + content + '</ol>';
    		console.log(data_submit);
    		$.confirm({
			    title: 'ERROR',
			    content: content,
			    buttons: {
			        confirm: function () {
			        	$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(data_submit),
    					url : "set_schedule_passenger",
    					timeout : 100000,
    					success : function(data) {
    						if(data["status"]){
    							$('#modal2').modal('close');
    						}
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});

			            return true;
			        },
			        cancel: function () {
			           return true;
			        },
			        
			    }
			});
    	}


    	var index_of=undefined;
    	$(".set_schedule").click(function() {

			var arr = $(this).attr('value');
			if(index_of!=arr){
				document.getElementById('add_more_schedule').innerHTML = '';
			}
			index_of = arr;
		
			document.getElementById('date_title').innerHTML = data_schedule[index_of]['date'];
			document.getElementById('destination_title').innerHTML = data_schedule[index_of]['destination'];
			document.getElementById('remaining_seats').innerHTML = data_schedule[index_of]["list"].length
			+data_schedule[index_of]["list_cus"].length;
    	});


		 $(".departure input").flatpickr({
    	 		enableTime: true,
			    noCalendar: true,
			    enableSeconds: false, 
			    time_24hr: false,
			    dateFormat: "H:i", 
			    defaultHour: 12,
			    defaultMinute: 0
   			 });
		  $(".arrival input").flatpickr({
    	 		enableTime: true,
			    noCalendar: true,

			    enableSeconds: false, // disabled by default

			    time_24hr: false, // AM/PM time picker is used by default

			    // default format
			    dateFormat: "H:i", 

			    // initial values for time. don't use these to preload a date
			    defaultHour: 12,
			    defaultMinute: 0
   			 });

		   $(".date_from").flatpickr({
    	 		maxDate: "today",
    	 		altInput: true,
   			 });
		$(".date_from").change(function() {
			var date_from = $(this).val();
			
			$(".date_to").flatpickr({
				altInput: true,
				minDate:date_from,
				maxDate: "today",

    });
		});
    	

			$(".report_btn").click(function() {
    	 	  	var from = $('.from input').val();
    	 	  	var to = $('.to input').val();
    	 	  	var date = {"date_from":from,"date_to":to};
    	 	  	console.log(date);
			    	 $.ajax({
						 	async:false,
						 	cache:false,
							type : "POST",
							contentType : "application/json",
							data: JSON.stringify(date),
							url : "schedule_report",
							timeout : 100000,
							success : function(data) {
								console.log(data);
								var tr ='<tr>';
								var td="";
								var tr_add='<tr>';
								var td_add= '';
								var rowspan=1;
								var tr_all='';
								var header = '';
								var th='';
								var bus_model = '';
								var driver_name= '';
									
									for(var i=0;i<data.length;i++){
									rowspan = data[i]["bus"].length;
									if(rowspan==0){
										rowspan =1;
									}
										
										if(data[i]["bus"].length>0){
									  		
									  		bus_model = '<td>' + data[i]["bus"][0]["bus_model"] + '</td>';

									  		
									  	}
									  	else{
									  		bus_model = '<td>Bus Has Deleted</td>';
									  	}
									  	if(data[i]["driver"].length>0){
									  	
									  		driver_name = '<td>' + data[i]["driver"][0]["driver_name"] + '</td>';
									  	}
									  	else{
									  		driver_name = '<td>Driver Has Deleted</td>';
									  	}
									  td = '<td rowspan="'+rowspan+'">' + data[i]["date"] + '</td>'

									  	 
									     + bus_model
									  	 + driver_name
									  	
										 + '<td rowspan="'+rowspan+'">' + data[i]["destination"] + '</td>'
										 + '<td rowspan="'+rowspan+'">' + data[i]["total_seats"] + '</td>'
										 + '<td rowspan="'+rowspan+'">' + data[i]["customer"] + '</td>'
										 + '<td rowspan="'+rowspan+'">' + data[i]["staff"] + '</td>'
										 + '<td rowspan="'+rowspan+'">' + data[i]["student"] + '</td>'
										
										 
										 tr = tr + td + '</tr>';
										 if(rowspan>1){
										 	 for(var j=1;j<rowspan;j++){
										 	 	if(data[i]["bus"].length>j){
										 	 		td_add = td_add + '<td>' + data[i]["bus"][j]["bus_model"] + '</td>';
										 	 	}
										 	 	else{
										 	 		td_add = td_add + '<td> Bus Has Deleted</td>';
										 	 	}
										 	 	if(data[i]["driver"].length>j){
										 	 		td_add = td_add + '<td>' + data[i]["driver"][j]["driver_name"] + '</td>';
										 	 	}
										 	 	else{
										 	 		td_add = td_add + '<td> Driver Has Deleted</td>';
										 	 	}

										 	 	
										 	 	tr_add = tr_add + td_add + '</tr>';
										 	 	td_add ='';
			 							 	 }
										 	
										 }
										 
										 tr_all = tr_all + tr + tr_add;

										 tr_add ='<tr>';
										 tr = '<tr>';
								                      		 
								}
								
								document.getElementById('report_table').innerHTML = tr_all;
							
								
							},
							error : function(e) {
								console.log("ERROR: ", e);
								
							},
							done : function(e) {
								console.log("DONE");
							}
						});
    	});
	
    	 	 $(".agree_add").click(function() {
    	 	 	var bus_model = $('.add_list_bus #bus_model').val();
    	 	 	var plate_number = $('.add_list_bus #plate_number').val();
    	 	 	var total_seats = $('.add_list_bus #total_seats').val();
    	 	 	var add = {"bus_model":bus_model,"plate_number":plate_number,"total_seats":parseInt(total_seats)};
    	 	 	
    	 	 	
    	 	 	 $.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(add),
    					url : "add_bus",
    					timeout : 100000,
    					success : function(data) {
    						console.log(data);
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});
    	 	 	
    	 	 	
    	 	 });



    	 	  $("#pop_up_user").click(function() {
    	 	  	var role = [];
    	 	  	var batch = [];
    	 	  	$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "GET",
    					
    					url : "role",
    					timeout : 100000,
    					success : function(data) {
    						role = data;
    						                   		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});
    	 	  	$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "GET",
    					
    					url : "batch",
    					timeout : 100000,
    					success : function(data) {
    						batch = data;
    						console.log(batch);
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});
    	 	  		var selectbatch= '<select>';
    	 	  		var optionbatch = '';
    	 	  		var selectrole = '<select>';
    	 	  		var optionrole = '';
    	 	  		for (var i = 0 ; i < batch.length; i++) {
    	 	  			optionbatch = optionbatch +'<option value="'+ batch[i]["batch_id"] + '">' + batch[i]["batch_number"] + '</option>' 
    	 	  		}
    	 	  		for (var i = 0 ; i < role.length; i++) {
    	 	  			optionrole = optionrole +'<option value="'+ role[i]["role_id"] + '">' + role[i]["role_name"] + '</option>' 
    	 	  		}
    	 	  		selectbatch = selectbatch + optionbatch + '</select>';
    	 	  		selectrole = selectrole + optionrole + '</select>';
    	 	  		
    	 	  		document.getElementById('batch').innerHTML = selectbatch;
    	 	  		document.getElementById('role').innerHTML = selectrole;
    	 	  		 $('select').material_select();



    	 	  });


    	 	  $(".add_user").click(function() {
    	 	  	console.log("add");
    	 	  	var name = $('#name').val();
    	 	  	var batch = $('#batch select').val();
    	 	  	var gender = $('#gender select').val();
    	 	  	var role = $('#role select').val();
    	 	  	var password =$('#password').val();
    	 	  	var email = $('#email').val();
    	 	  	var phone = $('#phone').val();
    	 	  	var image = $('#image').val();
    	 	  	var user = {"username":name,"batch":batch,"gender":gender,"role":role,
    	 	  	"password":password,"email":email,"image":image,"phone_number":phone};
    	 	  
    	 	  	$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(user),
    					url : "add_user",
    					timeout : 100000,
    					success : function(data) {
    						console.log(data);
    						                      		 
    					},
    					error : function(e) {
    						console.log("ERROR: ", e);
    						
    					},
    					done : function(e) {
    						console.log("DONE");
    					}
    				});


    	 	  });
    	 	  
    	 	  
    	 	var batch=[];
    	 	var new_batch;
    	 	//Get Batch
     	     $.ajax({
     	 	 	async:false,
     	 	 	cache:false,
     	 	 	contentType : 'application/json',
     	 		type : "GET",
     	 		url : "get_batch",
     	 		timeout : 100000,
     	 		success : function(data) {
					Console.log("Get Batch "+data);
     	 			batch=data;
     	 			new_batch=data[data.length-1].batch_number+1;
     	 			var batch_form="";
     	 			for(i=0;i<data.length;i++){
     	 				batch_form+='<tr><td>'+(i+1)
     	 						+'</td><td> Batch &nbsp'+data[i].batch_number
     	 						+'</td><td>'+data[i].date_of_leaving
     	 						+'</td><td>'+data[i].date_of_returning
     	 						+'</td><td>'+data[i].deadline_of_booking
     	 						+'</td><td><a class="editBatch" value="'+i
     	 						+'" href="#batchEdit">Edit</a> / <a class="deleteBatch" value="'+i
     	 						+'" href="#batchDelete">Delete</a></td></tr>';
     	 			}
     	 			batch_form+='<tr><td></td><td></td><td></td><td></td><td></td><td><a id="clk_add_batch" href="#addBatch">Add</a></td></tr>';
     	 			document.getElementById("get_batch_info").innerHTML = batch_form;

     	 		},
     	 		error : function(e) {
     	 			console.log("ERROR: ", e);
     	 			
     	 		},
     	 		done : function(e) {
     	 			console.log("DONE");
     	 		}
     	 	});
     	     
     	    //Edit Batch Button
     	     $('.editBatch').click(function(){
     	     	var index = $(this).attr('value');
     	     	var e_batch='<tr><td>Batch Number<span class="right">:</span></td><td>Batch&nbsp'+batch[index].batch_number
     	     				+'</td></tr><tr><td>Leave Date<span class="right">:</span></td><td><div class="input-field s6 flatpickr">'
				   	        +'<input type="text" placeholder="'+batch[index].date_of_leaving
				   	        +'" id="leaveDate" data-input class="input flatpickr-input active">' 
				   	        +'</div></td></tr><tr><td>Return Date<span class="right">:</span></td><td>'
				   	        +'<div class="input-field s6 flatpickr">'
				   	        +'<input type="text" placeholder="'+batch[index].date_of_returning
				   	        +'" id="returnDate" data-input class="input flatpickr-input active">'
				   	        +'</div></td></tr><tr><td>Deadline Booking<span class="right">:</span></td>'
				   	        +'<td><div class="input-field s6 flatpickr">'
				   	        +'<input type="text" placeholder="'+batch[index].deadline_of_booking
				   	        +'" id="deadlineBooking" data-input  class="input flatpickr-input active">'
				   	        +'</div></td></tr>'
				var button_update='<a value="'+batch[index].batch_id+'" class="modal-action modal-close waves-effect waves-green btn-flat bat_update">Update</a> '
     	     	document.getElementById('table_edit_batch').innerHTML=e_batch;
     	     	document.getElementById('bat_update').innerHTML=button_update;
     	     	$('#leaveDate').flatpickr({
     	    		mode: "single",
     	    		minDate: "today",
     	    		dateFormat: "Y-m-d"
     	        });
     	        $('#returnDate').flatpickr({
     	    		mode: "single",
     	    		minDate: "today",
     	    		dateFormat: "Y-m-d"
     	        });
     	        $('#deadlineBooking').flatpickr({
     	    		mode: "single",
     	    		minDate: "today",
     	    		dateFormat: "Y-m-d H:i:S",
     	    		enableTime: true
     	        });
     	     	$('.bat_update').click(function(){
     	     		var batch_id=batch[index].batch_id;
     	     		var leaveDate=$('#leaveDate').val();
     	     		var returnDate=$('#returnDate').val();
     	     		var deadlineBooking=$('#deadlineBooking').val();
     	     		console.log(leaveDate+" "+returnDate+" "+deadlineBooking);
         	     	updateBatch(batch_id, leaveDate, returnDate, deadlineBooking);
         	     	
         	     })
     	     })
     	     //Batch Update Button des_update
     	     function updateBatch(batch_id, leaveDate, returnDate, deadlineBooking){
     	    	 var submit={"batch_id":batch_id, "date_of_leaving":leaveDate,"date_of_returning":returnDate,"deadline_of_booking":deadlineBooking};
     	    	 $.ajax({
     	    	 	 	contentType : 'application/json',
     	    	 		type : "POST",
     	    	 		data:JSON.stringify(submit),
     	    	 		url : "update_batch",
     	    	 		timeout : 100000,
     	    	 		success : function(data) {
     	    	 			console.log(data);

     	    	 		},
     	    	 		error : function(e) {
     	    	 			console.log("ERROR: ", e);
     	    	 			
     	    	 		},
     	    	 		done : function(e) {
     	    	 			console.log("DONE");
     	    	 		}
     	    	 	});
     	     } 	
     	     
	     	  //Delete Batch Button
	     	     $('.deleteBatch').click(function(){
	     	     	var index=$(this).attr('value');
	     	     	var warning='<span>Are you sure, you want to delete <b class="red-text"> Batch '+batch[index].batch_number+'</b>?</span>';
	     	     	var delete_form='<a value="'+batch[index].batch_id+'" class="modal-action modal-close waves-effect waves-green btn-flat delete_batch">Yes</a>';
	     	     	document.getElementById('delete_batch_id').innerHTML=delete_form;
	     	     	document.getElementById('delete_batch_warning').innerHTML=warning;
	     	     	$('.delete_batch').click(function(){
	     	     	  deleteBatch(batch[index].batch_id);
	         	    })
	     	     })
	     	  //Batch Delete Button delete_batch
	     	     function deleteBatch(bat_id){
	     	    	 $.ajax({
	     	    	 	 	contentType : 'application/json',
	     	    	 		type : "POST",
	     	    	 		data:bat_id,
	     	    	 		url : "delete_batch",
	     	    	 		timeout : 100000,
	     	    	 		success : function(data) {
	     	    	 			console.log(data);
	
	     	    	 		},
	     	    	 		error : function(e) {
	     	    	 			console.log("ERROR: ", e);
	     	    	 			
	     	    	 		},
	     	    	 		done : function(e) {
	     	    	 			console.log("DONE");
	     	    	 		}
	     	    	 	});
	     	     }
	     	     
	     	     
	     	  $('#clk_add_batch').click(function(){
	     		  var new_batch_form='<span>Batch  '+new_batch+'</span>';
	     		  document.getElementById('new_batch').innerHTML=new_batch_form;
	     		  $('#new_leaveDate').flatpickr({
	     	    		mode: "single",
	     	    		minDate: "today",
	     	    		dateFormat: "Y-m-d"
	     	        });
	     	        $('#new_returnDate').flatpickr({
	     	    		mode: "single",
	     	    		minDate: "today",
	     	    		dateFormat: "Y-m-d"
	     	        });
	     	        $('#new_deadline').flatpickr({
	     	    		mode: "single",
	     	    		minDate: "today",
	     	    		dateFormat: "Y-m-d H:i:S",
	     	    		enableTime: true
	     	        });
	     	  })
     	     $('.add_new_batch').click(function(){
	     	       var new_ldate = $('#new_leaveDate').val();
	     	       var new_rdate = $('#new_returnDate').val();
	     	       var new_ddate = $('#new_deadline').val();
	     	       console.log(new_batch);
	     	       var submit={"batch_number":new_batch, "date_of_leaving":new_ldate,"date_of_returning":new_rdate,"deadline_of_booking":new_ddate};
	     	    	 $.ajax({
	     	    	 	 	contentType : 'application/json',
	     	    	 		type : "POST",
	     	    	 		data:JSON.stringify(submit),
	     	    	 		url : "add_batch",
	     	    	 		timeout : 100000,
	     	    	 		success : function(data) {
	     	    	 			new_batch++;
	     	    	 			console.log(new_batch);
	     	    	 			console.log(data);

	     	    	 		},
	     	    	 		error : function(e) {
	     	    	 			console.log("ERROR: ", e);
	     	    	 			
	     	    	 		},
	     	    	 		done : function(e) {
	     	    	 			console.log("DONE");
	     	    	 		}
	     	    	 	});
     	     })
     	     
     	     
     	     
     	     //Get Destination
     	     $.ajax({
     	 	 	async:false,
     	 	 	cache:false,
     	 	 	contentType : 'application/json',
     	 		type : "GET",
     	 		url : "get_destionation",
     	 		timeout : 100000,
     	 		success : function(data) {
     	 			var des_form="";
     	 			for(i=0;i<data.length;i++){
     	 				des_form+='<tr><td>'+(i+1)
     	 						+'</td><td>'+data[i].destination_name
     	 						+'</td><td><a class="editDest" id="'+data[i].destination_name+'" value="'+data[i].destination_id
     	 						+'" href="#destinationEdit">Edit</a> / <a class="deleteDes" id="'+data[i].destination_name+'" value="'+data[i].destination_id
     	 						+'" href="#destinationDelete">Delete</a></td></tr>';
     	 			}
     	 			des_form+='<tr><td></td><td></td><td><a href="#addDestination">Add</a></td></tr>';
     	 			document.getElementById("getDes").innerHTML = des_form;

     	 		},
     	 		error : function(e) {
     	 			console.log("ERROR: ", e);
     	 			
     	 		},
     	 		done : function(e) {
     	 			console.log("DONE");
     	 		}
     	 	});
     	   //Delete Destination Button
     	     $('.deleteDes').click(function(){
     	     	var des_name = this.id;
     	     	var des_id=$(this).attr('value');
     	     	var delete_des_form='<a class="modal-action modal-close waves-effect waves-green btn-flat delete_des">Yes</a>';
     	     	document.getElementById('getDesDelete').innerHTML=delete_des_form;
     	     	$('.delete_des').click(function(){
         	     	deleteDes(des_id);
         	     })
     	     })
     	   //Destination Delete Button delete_des
     	     function deleteDes(des_id){
     	    	 console.log(des_id);
     	    	 $.ajax({
     	    	 	 	contentType : 'application/json',
     	    	 		type : "POST",
     	    	 		data:des_id,
     	    	 		url : "delete_destination",
     	    	 		timeout : 100000,
     	    	 		success : function(data) {
     	    	 			console.log(data);

     	    	 		},
     	    	 		error : function(e) {
     	    	 			console.log("ERROR: ", e);
     	    	 			
     	    	 		},
     	    	 		done : function(e) {
     	    	 			console.log("DONE");
     	    	 		}
     	    	 	});
     	     }
     	     
     	     //Edit Destination Button
     	     $('.editDest').click(function(){
     	     	var des_name = this.id;
     	     	var des_id=$(this).attr('value');
     	     	var desText='<input id="des_name_update" type="text" class="validate" value="'+ des_name +'">';
     	     	var editFooter='<a class="modal-action modal-close btn-flat des_update">Update</a>'
     	     	document.getElementById('des_edit_footer').innerHTML=editFooter;
     	     	document.getElementById('getDesText').innerHTML=desText;
     	     	$('.des_update').click(function(){
     	     		var des_update=$('#des_name_update').val();
         	     	updateDes(des_id, des_update); 	
         	     })
     	     })
     	     
     	     //Add Destination Link
     	     $('.add_new_des').click(function(){
     	     	var new_des=$('#new_des_name').val();
     	     	$.ajax({
 	    	 	 	contentType : 'application/json',
 	    	 		type : "POST",
 	    	 		data:new_des,
 	    	 		url : "add_destination",
 	    	 		timeout : 100000,
 	    	 		success : function(data) {
 	    	 			console.log(data);

 	    	 		},
 	    	 		error : function(e) {
 	    	 			console.log("ERROR: ", e);
 	    	 			
 	    	 		},
 	    	 		done : function(e) {
 	    	 			console.log("DONE");
 	    	 		}
 	    	 	});
     	     })
     	     
     	     //Destination Update Button des_update
     	     function updateDes(des_id,des_update){
     	    	 var submit={"des_id":des_id, "des_update":des_update};
     	    	 $.ajax({
     	    	 	 	contentType : 'application/json',
     	    	 		type : "POST",
     	    	 		data:JSON.stringify(submit),
     	    	 		url : "update_destination",
     	    	 		timeout : 100000,
     	    	 		success : function(data) {
     	    	 			console.log(data);

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
