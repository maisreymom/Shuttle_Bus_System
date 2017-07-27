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
    <div class="nav-wrapper container"><a id="logo-container" href="mobileTeacher" class="brand-logo">Logo</a>
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
<div class="hide-on-large-only">
	<div class="row">
		<br>
		<div><h6 class="header center orange-text"><b>Schedule Shuttle Bus Up to Date</b></h6></div>
	</div>
	<div class="row">
		<ul id="sch" class="collapsible" data-collapsible="accordion">
	  	</ul>
	</div>
      
</div>


  <!--  Scripts  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://unpkg.com/flatpickr"></script>

<script type="text/javascript">	

	$(document).ready(function() {
		$(".button-collapse").sideNav();
		$('.collapsible').collapsible();
		$.ajax({
			type : "GET",
			async:false,
			cache:false,
			contentType : "application/json",
			url : "mobileSchedule_Service",
			timeout : 100000,
			success : function(data) {
				    var scheduleForm="";
					for(i=0;i<data.length;i++){	
						scheduleForm+='<li><div class="collapsible-header';
						if(i==0){
							scheduleForm+=' active';
						}
						scheduleForm+='"><span><i class="material-icons">event</i>'+data[i].date_of_travel
									+'</span><span class="right"><i class="material-icons">place</i>'+data[i].destination_name
									+'</span></div><div class="collapsible-body">'
									+'<table class="centered">'
							        +'<tr><th>Bus Model : </th><td>'
							        +data[i].bus_model+'</td></tr>'
							        +'<tr><th>Driver Name : </th><td>'
							        +data[i].driver_name+'</td></tr>'  
							        +'<tr><th>Total Seats :</th><td>'
							        +data[i].total_available_seats+'</td></tr>'
							        +'<tr><th>Customer Seats :</th><td>'
							        +data[i].customer_seats+'</td></tr>'
							        +'<tr><th>Staff Seats :</th><td>'
							        +data[i].staff_seats+'</td></tr>'
							        +'<tr><th>Student Seat :</th><td>'
							        +data[i].student_seats+'</td></tr>'
							        +'<tr><th>Travel Time :</th><td>'
							        +data[i].est_departure_time+" to "+data[i].est_arrival_time+'</td></tr>'
							      	+'<tr><th class="center" colspan="2">Passenger List</th></tr></table><table class="centered bordered"><thead><tr><th>No.</th><th>Name</th><th>Role</th></tr></thead>';
						var pass=passenDetail(data[i].bus_per_schedule_id);
						for(j=0;j<pass.length;j++){
							scheduleForm+='<tr><td>'+(j+1)
		   								+'</td><td>'+pass[j].passenger_name
		   								+'</td><td>'+pass[j].role
		   								+'</td></tr>';	
  						}
						scheduleForm+='</table></div></li>';    	
					}
					document.getElementById('sch').innerHTML=scheduleForm;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		function passenDetail(bus_id){
			console.log(bus_id);
			var passen;
			$.ajax({
	   				type : "POST",
   	   			async:false,
   				cache:false,
	   				contentType : "application/json",
	   				data :bus_id,
	   				url : "driverGetDetail",
	   				timeout : 100000,
	   				success : function(data) {
	   					passen=data;
	   					},
	   					error : function(e) {
	   						console.log("ERROR: ", e);
	   					},
	   					done : function(e) {
	   						console.log("DONE");
	   					}
	   				});
			return passen;
		}

	});

 </script>
  </body>
</html>