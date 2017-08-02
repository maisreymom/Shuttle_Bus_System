$(document).ready(function(){
    $('ul.tabs').tabs();
    $('.modal').modal();
    $(".button-collapse").sideNav();
    $('#modal2').modal();
    $('select').material_select();
    $('#modal3').modal();
    $('#edit_bus_list').modal();
    $('#delete_bus_list').modal();
    

var row={"phnom_penh":[],"Kirirom":[]};
	
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
					tr='';
					td='';
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

					    td = '<td>' + data[i]["date"] +'</td>'
						+ '<td>' + data[i]["destination"] + '</td>'
						+ '<td>' + data[i]["student"] + '</td>'
						+ '<td>' + data[i]["staff"] + '</td>'
						+ '<td>' + data[i]["customer"] + '</td>'
						+ '<td class="detail" value="'+i+'"> <a class="modal-trigger" href="#modal1">Detail</a></td>'
						+ '<td class="set_schedule" value="'+i+'"><a class="modal-trigger" href="#modal2">Set</a></td>';

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
			
		
						console.log(i);
						console.log(data_schedule[i]);
						for(var j=0;j<data_schedule[i]["list"].length;j++){
							td ='<td>' + data_schedule[i]["list"][j]["name"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["batch"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["role"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["emain"] + '</td>'
							+ '<td>' + data_schedule[i]["list"][j]["phone"] + '</td>'
							
							tr = tr + td + '</tr>';
							
						}
	
			
			document.getElementById('detail_title').innerHTML = 'User Detail on '+arr[0];
			document.getElementById('detail_pass').innerHTML = tr;
			
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

    		  $(".delete_bus").click(function() {
    	 	   		delete_id = $(this).attr('value');
    	 	   		console.log(delete_id);
    	 	   				
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

    		  
    		  
		 var count_schedule=0;
		 var count_schedule_element=[];
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
	            for(var i=0;i<bus_list.length;i++){
	            	bus_model = bus_model + '<option value="'+bus_list[i]["bus_id"]+'">' + bus_list[i]["bus_model"] + '</option>';
	            }
	            for(var i=0;i<driver_list.length;i++){
	            	driver_name = driver_name + '<option value="'+driver_list[i]["user_id"]+'">' + driver_list[i]["driver_name"] + '</option>';
	            }
				bus_model = '<td id="bus_model'+count_schedule+'"><select>'+ bus_model +'</select></td>';
				driver_name = '<td id="driver_name'+count_schedule+'"><select>'+driver_name +'</select></td>';
				total_seats = '<td id="total_seats'+count_schedule+'"><input type="text" placeholder="total seats"></td>';
				departure = '<td><div class="flatpickr departure  data_departure'+count_schedule+'">'
              				+'<input type="text" placeholder="Select Time" data-input  class=" input flatpickr-input active"></div></td>';
              	arrival = '<td><div class="flatpickr arrival data_arrival'+count_schedule+'">'
                +'<input type="text" placeholder="Select Time" data-input  class="input flatpickr-input active"></div></td>';
                customer = '<td ><select multiple class="customer'+count_schedule+'"><option>customer1</option>'
                +'<option>customer2</option><option>customer3</option></select></td>';
                delete_schedule = '<td class="delete_icon"><i class="material-icons" value="'+count_schedule+'">delete</i></td>'
				tr = '<tr class="add_input" id="tr_'+count_schedule+'">' + bus_model + driver_name + customer+total_seats + departure + arrival+delete_schedule+'</tr>';
				
					
				
				$("#add_more_schedule" ).append( tr );
				$('select').material_select();
				count_schedule_element[count_schedule_element.length]= count_schedule;
				count_schedule++;
				console.log(count_schedule_element);
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
    			
    		
    		console.log(count_schedule_element);
    		return data;
		  
    	});

		 });
    	


    	$("#set_schedule").click(function() {
    		var schedule_data=[];
    		var data_list = data_schedule[index_of]["list"];
    		var assign_id=[];
    		var remaining_seats=0;
    		
    		console.log(data_list);
    		for(var i=0;i<count_schedule_element.length;i++){
				var id_customer = $(".customer"+count_schedule_element[i]+" select").val();
				console.log(id_customer);
				for(var j=0;j<parseInt($("#total_seats"+count_schedule_element[i]+" input" ).val());j++){
					if(id_customer[0]){
						assign_id[j] = id_customer[0];
						id_customer.shift();
					}
					else if(data_list[0]){
						
						assign_id[j] = data_list[0]["name"];
						data_list.shift();
						
					}
					else break;
					
				}
				console.log($(".customer"+count_schedule_element[i]+" select").val().length);
				remaining_seats = parseInt($("#total_seats"+count_schedule_element[i]+" input" ).val()) -assign_id.length;
				
				schedule_data[i] = {"bus_id":$( "#bus_model"+count_schedule_element[i]+" select" ).val(),
									"driver_name":$( "#driver_name"+count_schedule_element[i]+" select" ).val(),
									"total_seats":parseInt($( "#total_seats"+count_schedule_element[i]+" input" ).val()),
									"passenger_id":assign_id,
									"customer":$(".customer"+count_schedule_element[i]+" select").val().length,
									"student":assign_id.length,
									"staff":0,
									"remaining_seats":remaining_seats,
									"date_of_travel":data_schedule[index_of]["date"],
									"destination_id":data_schedule[index_of]["destination"],
									"est_arrival":$('.data_arrival'+count_schedule_element[i]+' input').val(),
									"est_departure":$('.data_departure'+count_schedule_element[i]+' input').val()};

				assign_id = [];

    		}

    		console.log(schedule_data);
    		

    		$.ajax({
    				 	async:false,
    				 	cache:false,
    				 	contentType : 'application/json; charset=utf-8',
    					type : "POST",
    					data : JSON.stringify(schedule_data),
    					url : "set_schedule_passenger",
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
    	var index_of=0;
    	$(".set_schedule").click(function() {

			var arr = $(this).attr('value');
			index_of = arr;
			
			document.getElementById('remaining_seats').innerHTML = data_schedule[index_of]["list"].length;
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
    	 	 	
    	 	 	console.log(add);
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
    	 	//Get Destination
     	     $.ajax({
     	 	 	async:false,
     	 	 	cache:false,
     	 	 	contentType : 'application/json',
     	 		type : "GET",
     	 		url : "get_batch",
     	 		timeout : 100000,
     	 		success : function(data) {
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
