<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Teacher</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
  
  
  <style type="text/css">
  
	.container {
	    width: 80%;
	}
  	.pickmeup .pmu-instance nav {
  		height: 25px!important;
  		background-color: black;
  	}
  	input[type=text]:not(.browser-default){
  		    border-bottom: 0px solid #9e9e9e!important;
  	}
  	.input-field{
  		height: 39px;
  		margin-top: -3px;
  		margin-bottom: -3px;
  	}
  	.bookingBtn{
  		width: 100%;
  		height: 100%;
  	}
  	.custom_input{
  		border-right: 1px solid #f8f4f4;
  	}
  	.row_book{
  		background: white;
  		padding: 0;
  		border-radius: 2px;
  	}
  	.row_mobileBook {
	    margin-left: 5px;
	    margin-right: 5px;
	}
  	.custom_book{
  		margin: 0;
  		border-radius: 0;
  		background-color: blue; 
  	}
  	.sch_table{
  		border: 1 solid black!important;
  	} 
  	.mobile_schedule{
  		width: 100%;
  	}
  	.profile{
  		width: 60px;
  		height: 60px;
  	}
  	.dropdown-user-info{
  		height: 64px;
  	}
  	[type="radio"]:not(:checked) + label::before, [type="radio"]:not(:checked) + label::after {
  		  border: 2px solid #fffdfc;
		}
		[type="radio"]:checked + label::after, .with-gap[type="radio"]:checked + label::after {
		    background-color: White;
		}
		[type="radio"]:checked + label::after, .with-gap[type="radio"]:checked + label::before, .with-gap[type="radio"]:checked + label::after {
		    border: 2px solid #FFFFFF;
		}
	.option_label{
		color: white;
	}
	.modal_header{
		color:blue;
	}
	.bordered th, .bordered td {
   		 border: 1px solid gray;
	}
	.r_book{
		padding-top: 10px;
    	padding-bottom: 1px;
	}
	.r_book input[type=text]:not(.browser-default){
		border-bottom: 1px solid #9e9e9e!important;
	}
	.r_book .option_label {
    	color: black;
	}
	.r_book [type="radio"]:not(:checked) + label::before, [type="radio"]:not(:checked) + label::after {
  		  border: 2px solid #BDBDBD;
		}
	.r_book [type="radio"]:checked + label::after, .with-gap[type="radio"]:checked + label::after {
		    background-color: #BDBDBD;
		}
	.r_book [type="radio"]:checked + label::after, .with-gap[type="radio"]:checked + label::before, .with-gap[type="radio"]:checked + label::after {
		    border: 2px solid #BDBDBD;
		}
	.modal{
		width: 80% !important;
	}
	.m_header{
		    margin-bottom: -6px !important;
			padding-bottom: 20px;
		    box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14), 0 1px 5px 0 rgba(0,0,0,0.12), 0 3px 1px -2px rgba(0,0,0,0.2);
	}
  </style>
  
</head>
<body>
 <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
      <ul class="right hide-on-med-and-down">
      <!-- 
      	  <li>
        	<a class="dropdown-icon" href="#" data-activates='request'><i class="large material-icons">dialpad</i></a>
        </li>	
       -->
        <li>
        	<a href="#" class="dropdown-user-info" href="#" data-activates='user_info'><img src="https://s-media-cache-ak0.pinimg.com/736x/64/fb/c9/64fbc98e98bebd0c06dc5f9345724658.jpg" alt="" class="responsive-img circle profile"></a>
        </li>
      </ul>

      <!-- Dropdown .dropdown-icon 
      	<ul id='request' class='dropdown-content'>
	    <li><a href="#!">Emergency Booking</a></li><li class="divider"></li>
	    <li><a href="#!">Donate Ticket</a></li><li class="divider"></li>
	  </ul>
      -->
	  

	  <ul id='user_info' class='dropdown-content'>
		  <!-- 
		  	<li><a href="#!">username</a></li><li class="divider"></li>
		    <li><a href="#!">No.Ticket:&nbsp &nbsp<span>10</span></a></li>
		    <li><a href="#!">Log Out</a></li><li class="divider"></li>
		   --> 
	  </ul>
	  <ul id="nav-mobile" class="side-nav">
	  		<li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">account_circle</i>Name: Mai Mom</a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">loyalty</i>No. of Ticket:&nbsp &nbsp<span>10</span></a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="mobileSchedule"><i class="material-icons">event</i>Schedule Up to Date</a></li>
		    <li><div class="divider"></div></li>
		    <!-- 
		    	<li><a href="#!"><i class="material-icons">assignment_late</i>Emergency Booking</a></li>
			    <li><div class="divider"></div></li>
			    <li><a href="#!"><i class="material-icons">cached</i>Donate Ticket</a></li>
			    <li><div class="divider"></div></li>
			    <li><a href="#!"><i class="material-icons">settings</i>Setting</a></li>
		     -->
	  </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
<div class="container hide-on-med-and-down">
  	<br>
      <h5 class="header center orange-text"><b>vKirirom Shuttle Bus System</b></h5>
      <p class="header col s12 light center">A modern responsive front-end framework based on Material Design</p>
</div>
<div id="session" class="section hide-on-med-and-down">    
      <div class="light-blue lighten-1">
      		<br><br>
      		<div class="container">
      		   <form id="SelectRadio">
				    <span>
				    	<input class="with-gap" name="option_way" type="radio" id="roundWay" value="RoundWay" checked="checked"/>
				        <label class="option_label" for="roundWay">Round Ways</label>
				    </span>
				    <span>
				    	<input class="with-gap" name="option_way" type="radio" id="oneWay" value="OneWay"/>
				        <label class="option_label" for="oneWay">One Way</label>
				    </span>	  
				</form>
				<br>
		      <div class="row row_book">
		        <div class="col s12 l3 custom_input">
			   		<div id="getSelectFrom" class="input-field s6"></div>
			   	</div>
		        <div class="col s12 l3 custom_input">
			   		<div id="getSelectTo" class="input-field s6">      
				      <select class="validate">
				         <option value="" disabled selected>Return</option>
				      </select>
				    </div>
			   	</div>
			   	<div class="col s12 l3">
			   		<div class="input-field s6 flatpickr">
				   		<input type="text" placeholder="Select Date" id="check_in_date" data-input class="input flatpickr-input active"> 
					</div>
			   	</div>
			   	<div class="col s12 l3 custom_book light-blue lighten-1">
			   		<a class="btn orange lighten-1 bookingBtn" id="book_now">Book Now</a>   			
			   	</div>
		      </div>
		    </div>
		    <br><br>
      </div>
</div>
<div class=" container hide-on-large-only">
      <div class="light-white lighten-1 hide-on-large-only r_book">
      		<div class="row container">
      			<br>
      			<form id="SelectRadio">
				    <span>
				    	<input class="with-gap" name="mobile-way" type="radio" id="m_roundWay" value="RoundWay" checked="checked"/>
				        <label class="option_label" for="m_roundWay">Round Ways &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
				    </span>
				    <span>
				    	<input class="with-gap" name="mobile-way" type="radio" id="m_oneWay" value="OneWay"/>
				        <label class="option_label" for="m_oneWay">One Way</label>
				    </span>	  
				</form>
				<br>
			</div>
      		<div class="row row_mobileBook">
		        <div class="col s12">
			   		<div id="m_getSelectFrom" class="input-field s6 light-white lighten-1"></div>
			   		<br>
			   	</div>
			</div>
			<div id="m_getSelectTo" class="row row_mobileBook">
		        <div class="col s12">
			   		<div class="input-field s6 ">      
				      <select class="validate light-white lighten-1">
				        <option value="" disabled selected>Return</option>
				      </select>
				    </div>
				    <br>
			   	</div>
			</div>
			<div class="row row_mobileBook">
		        <div class="col s12 l3">
			   		<div class="input-field s6 flatpickr">
				   		<input id="m_check_in_date" type="text" placeholder="Select Date" data-input class="input flatpickr-input active"> 
					</div>
					<br>
			   	</div>
			</div>
      </div>
      <div class="row r_book">
		   <div class="col s12 l3">
			   	<a class="btn orange lighten-1 bookingBtn" id="m_book_now">Book Now</a>
			   	<br><br>   			
			</div>
	  </div>   	
  </div>
<div class="container hide-on-med-and-down" >
   	 <h5 class="center sch light-blue-text">Schedule Up to Date</h5><br>
      <table class="centered highlight bordered">
        <thead>
          <tr>
              <th>Date</th>
              <th>Destination</th>         
              <th>Total Seats</th>
              <th>Customer</th>
              <th>Staff</th>
              <th>Student</th>
              <th>Remaining</th>
              <th>Passenger</th>
          </tr>
        </thead>
        <tbody id="getSchedule"></tbody>
      </table>
      <br><br>
    </div>
<div class="container">
    <!-- Modal Structure -->
	  <div id="user_detail" class="modal modal-fixed-footer">
		  <div id="modal_body" class="modal-content"> 
		  </div>
		  <div class="modal-footer">
		   <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Back</a>
		</div>
	</div>
</div>


<footer class="page-footer light-blue lighten-1 hide-on-med-and-down">
    <div class="container">
      <div class="row">
        <div class="col l6">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>
        </div>
        <div class="col l3 ">
        </div>
        <div class="col l3">
          <h5 class="white-text ">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">vKirirom Pine View Resort</a>
      </div>
    </div>
  </footer>


  <!--  Scripts  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://unpkg.com/flatpickr"></script>
  <script type="text/javascript">		
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
					document.getElementById("user_info").innerHTML = userInfoForm;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
	    
	    
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
			console.log(from);
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
			 console.log(from);
			 if((back==null&&itera_date_travel==2)||from==null||date_of_travel.length==0){
				 alert("Unvalid Data!"); 
			 }
			 else{
				 var destination_id=[from,back];
				 var submit = [];
		      	 for(var i=0;i<itera_date_travel;i++){	 
		      		submit[i]={"destination_id":destination_id[i],
		      				"date_of_travel":date_of_travel[i]};
		      		}
		      	  $.ajax({
						type : "POST",
						url : "booking",
						contentType : "application/json",
						data : JSON.stringify(submit),
						timeout : 100000,
						success : function(data) {
							console.log(data);	
							if(data){
								alert("Booking Done");
							}
							else{
								alert("Booking Error");
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
	      			 
		 });
	});

 </script>


<script type="text/javascript">	
	
	$(document).ready(function() {
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
	});

 </script>
  </body>
</html>