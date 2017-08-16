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
  	.input-field{
  		height: 39px;
  		margin-top: -3px;
  		margin-bottom: -3px;
  	}
  	.bookingBtn{
  		width: 100%;
  		height: 100%;
  	}
  	.row_mobileBook {
	    margin-left: 5px;
	    margin-right: 5px;
	}
  	.mobile_schedule{
  		width: 100%;
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
</style>
  
</head>
<body>
 <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
	  <ul id="nav-mobile" class="side-nav">
	  		<li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">account_circle</i>Name: Mai Mom</a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">loyalty</i>No. of Ticket:&nbsp &nbsp<span>10</span></a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="mobileSchedule"><i class="material-icons">event</i>Schedule Up to Date</a></li>
		    <li><div class="divider"></div></li>
	  </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>


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








  <!--  Scripts  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://unpkg.com/flatpickr"></script>
  


<script type="text/javascript">	
	
	$(document).ready(function() {
		

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
			    dateFormat: "d-m-Y"
			});   	
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
				dateFormat: "d-m-Y"
			});
		 });
		 $('#m_oneWay').change(function(){
		 	m_itera_date_travel=1;
		 	m_date_of_travel=null;
		 	mOptionSelect(m_itera_date_travel);
		 	m_date_of_travel=[];
		    $(".flatpickr input").flatpickr({
				mode: "single",
				minDate: "today",
				dateFormat: "d-m-Y",
				disableMobile: "true",
			});
		 });

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
					var select='<select id="m_fromDes" class="validate"><option disabled selected>From</option>';
					for(i=0;i<m_destination.length;i++){
						select +='<option value="'+m_destination[i].destination_id+'">'+m_destination[i].destination_name+'</option>';

}
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
   		
   	    
   		 //Passenger Detail//not yet
   		$('.userDetail').click(function() {
   		    var sch_id=this.id ;
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