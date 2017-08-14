$(document).ready(function(){
var user_id='jdfh4737rb';
$('.modal').modal();
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
			console.log("User: "+data[0].fullname);
			var userInfoForm="";
			userInfoForm+='<li><a href="#!">Name: '+data[0].fullname
						+'</a></li><li class="divider"></li><li><a href="#!">Username: '+
						data[0].username+'</a></li><li class="divider"><li><a href="logout">Log Out</a></li><li class="divider"></li>'
			document.getElementById("user_info").innerHTML = userInfoForm;
	},
	error : function(e) {
		console.log("ERROR: ", e);
			
	},
	done : function(e) {
		console.log("DONE");
	}
});
function formatDate(date) {
	  var monthNames = [
	    "Jan", "Feb", "Mar",
	    "Apr", "May", "Jun", "Jul",
	    "Aug", "Sep", "Oct",
	    "Nov", "Dec"
	  ];

	  var day = date.getDate();
	  var monthIndex = date.getMonth();
	  var year = date.getFullYear();

	  return  monthNames[monthIndex]+ ' ' + day + ', ' + year;
	}

	//show my schedule form
	mySchedule();
	function mySchedule(){
		$.ajax({
			type : "POST",
			async:false,
			cache:false,
			data:user_id,
			contentType : "application/json",
			url : "myschedule_driver",
			timeout : 100000,
			success : function(data) {
				console.log(data);
				if(data.length!=0){
					var scheduleForm='<h5 class="title">My Shuttle Bus Schedule</h5><br>'
	    				+'<table class="bordered centered highlight">'
				        +'<thead><tr><th>No.</th><th>Date</th><th>Bus ID</th>'
				        +'<th>Bus Driver</th><th>Destination</th><th>Travel Time</th>'
				        +'<th>Confirm</th></tr></thead>';
				        +'<tbody>';
	     		 	
					for(i=0;i<data.length;i++){	
							scheduleForm+='<tr><td>'+(i+1)
					   				    +'</td><td>'+data[i].date_of_travel
										+'</td><td>'+data[i].bus_model
					   				    +'</td><td>'+data[i].driver_name
										+'</td><td>'+data[i].destination_name
										+'</td><td>'+data[i].est_departure_time+" to "+data[i].est_arrival_time
										+'</td>';
							//Get today
							var currentTime = new Date()
						var month = currentTime.getMonth() + 1
						var day = currentTime.getDate()
						var year = currentTime.getFullYear()
						if(day<10) {
							day = '0'+day
						} 
						if(month<10) {
							month = '0'+month
						} 
						var today = day + "/" + month + "/" + year;
						console.log(data[i].date_of_travel+"="+formatDate(new Date()));
							if(data[i].date_of_travel==formatDate(new Date())){
								var check = checkConfirm(data[i].bus_per_schedule_id);
								console.log(check);
								if(check.actual_departure_time==""||check.actual_departure_time==null){
									scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 leaveConfirm" value="'+data[i].bus_per_schedule_id+'">Leave</a></td></tr>';
								}else if(check.actual_arrival_time==""||check.actual_arrival_time==null){
									scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 arriveConfirm" value="'+data[i].bus_per_schedule_id+'">Arrive</a></td></tr>';
								}else{//if already done both
									scheduleForm+='</td><td><a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a></td></tr>';
								}		
							}else{
								scheduleForm+='</td><td><a class="btn red lighten-4" disabled>Leave</a></td></tr>';
							}	        	    	
				}
				scheduleForm+='</tbody></table>'
				console.log(scheduleForm);
				document.getElementById('my_schedule').innerHTML=scheduleForm;
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


$.ajax({
		type : "GET",
		async:false,
		cache:false,
		contentType : "application/json",
		url : "driverSchedule",
		timeout : 100000,
		success : function(data) {
			    var scheduleForm="";
				for(i=0;i<data.length;i++){	
   					scheduleForm+='<tr><td>'+data[i].date_of_travel
				   				+'</td><td>'+data[i].bus_model
				   				+'</td><td>'+data[i].driver_name
   								+'</td><td>'+data[i].destination_name
   								+'</td><td>'+data[i].total_available_seats
   								+'</td><td>'+data[i].customer_seats
   								+'</td><td>'+data[i].staff_seats
   								+'</td><td>'+data[i].student_seats
   								+'</td><td>'+data[i].est_departure_time+" to "+data[i].est_arrival_time
   								+'</td><td><a href="#user_detail" class="userDetail" value="'+data[i].bus_per_schedule_id
   						        +'" style="color:#ff9800;cursor:pointer">detail</a></td>';
   					//Get today
   					var currentTime = new Date()
					var month = currentTime.getMonth() + 1
					var day = currentTime.getDate()
					var year = currentTime.getFullYear()
					if(day<10) {
						day = '0'+day
					} 
					if(month<10) {
						month = '0'+month
					} 
					var today = day + "/" + month + "/" + year;
					console.log(data[i].date_of_travel+"="+formatDate(new Date()));
   					if(data[i].date_of_travel==formatDate(new Date())){
   						var check = checkConfirm(data[i].bus_per_schedule_id);
   						console.log(check);
   						if(check.actual_departure_time==""||check.actual_departure_time==null){
   							scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 leaveConfirm" value="'+data[i].bus_per_schedule_id+'">Leave</a></td></tr>';
   						}else if(check.actual_arrival_time==""||check.actual_arrival_time==null){
   							scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 arriveConfirm" value="'+data[i].bus_per_schedule_id+'">Arrive</a></td></tr>';
   						}else{//if already done both
   							scheduleForm+='</td><td><a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a></td></tr>';
   						}		
   					}else{
   						scheduleForm+='</td><td><a class="btn red lighten-4" disabled>Leave</a></td></tr>';
   					}	        	    	
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
	
function checkConfirm(bus_id) {
	var confirm="";
	console.log("check confirm  "+bus_id);
	$.ajax({
			type : "POST",
  			async:false,
			cache:false,
				contentType :"application/json",
				data :bus_id,
				url : "checkConfirm",
				timeout : 100000,
				success : function(data) {
					confirm=data;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
    return confirm;
}
	
//Confirm Leave 
$('.leaveConfirm').click(function() {
		    var bus_id=$(this).attr("value") ;
		    console.log("leave "+bus_id);
   	   		$.ajax({
   	   				type : "POST",
	   	   			async:false,
	   				cache:false,
   	   				contentType : "application/json",
   	   				data :bus_id,
   	   				url : "leaveConfirm",
   	   				timeout : 100000,
   	   				success : function(bus_set) {
   	   					var arriveForm='<a class="btn light-blue lighten-1 arriveConfirm" value="'+bus_set+'">Arrive</a>'
   	   					document.getElementById(bus_set).innerHTML = arriveForm;
   	   				},
   	   				error : function(e) {
   	   					console.log("ERROR: ", e);
   	   				},
   	   				done : function(e) {
   	   					console.log("DONE");
   	   				}
   	   			});
   	   	$('.arriveConfirm').click(function() {
   		 var bus_id=$(this).attr("value") ;
   		    console.log("arrive "+bus_id);
   		 	arriveConfirm(bus_id);
   		    });
   	   		
});
// Arrived Confirm
$('.arriveConfirm').click(function() {
		 var bus_id=$(this).attr("value") ;
		  console.log("arrive "+bus_id);
		  arriveConfirm(bus_id);
	  		
});
function arriveConfirm(bus_per_id){
	$.ajax({
			type : "POST",
  			async:false,
			cache:false,
				contentType : "application/json",
				data :bus_per_id,
				url : "arriveConfirm",
				timeout : 100000,
				success : function(bus_set) {
					var arriveForm='<a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a>'
					document.getElementById(bus_set).innerHTML = arriveForm;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
	});
}

	    

//Passenger Detail
$('.userDetail').click(function() {
   		    var bus_id=$(this).attr("value") ;
	   	    console.log(bus_id);
	   	   		$.ajax({
	   	   				type : "POST",
		   	   			async:false,
		   				cache:false,
	   	   				contentType : "application/json",
	   	   				data :bus_id,
	   	   				url : "driverGetDetail",
	   	   				timeout : 100000,
	   	   				success : function(data) {
	   	   					console.log(data);
	   	   					var passenger_modal="";
	   	  					for(i=0;i<data.length;i++){
	   			   					passenger_modal+='<tr><td>'+(i+1)
	   			   								+'</td><td>'+data[i].passenger_username
	   			   								+'</td><td>'+data[i].passenger_fullname
	   			   								+'</td><td>'+data[i].batch
	   			   								+'</td><td>'+data[i].role
	   			   								+'</td><td>'+data[i].seat_number
	   			   								+'</td></tr>';	
	   	  						}
	   	  						document.getElementById('getDetail').innerHTML=passenger_modal;
								console.log("Done");
	   	   				},
	   	   				error : function(e) {
	   	   					console.log("ERROR: ", e);
	   	   				},
	   	   				done : function(e) {
	   	   					console.log("DONE");
	   	   				}
	   	   			});
	   	   		 	

   	    });
}); 	 
