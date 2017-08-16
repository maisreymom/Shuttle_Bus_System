<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <!-- Compiled and minified CSS -->

      <!-- Compiled and minified JavaScript -->

      <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>

      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
      <script type="text/javascript" src="https://cdn.rawgit.com/JDMcKinstry/JavaScriptDateFormat/master/dateFormat.min.js"></script>
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/additional-methods.js"></script>
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">
            <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
              <script src="https://unpkg.com/flatpickr"></script>

              <spring:url value="/resources/css/admin.css" var="admincss"/>
              <spring:url value="/resources/js/admin.js" var="adminjs"/>
              <link href="${admincss}" rel="stylesheet"/>
              <script src="${adminjs}" type="text/javascript"></script>
              <script type="text/javascript"></script>
            </head>
            <body>
              <nav class="nav-extended">
                <div class="container">
                  <div class="nav-wrapper">
                    <div class="row nav_row">
                      <div class="col s12 m5">
                        <a href="#" class="brand-logo" id='click'>SHUTTLE BUS</a>

                      </div>

                      <div class="col s0 l7">
                        <ul id="nav-mobile" class="right hide-on-med-and-down">

                           <li><a id="view_emr" class="modal-trigger" href="#modal_notif"><i class="material-icons">public</i></a></li>

                          <li >
                            <a class='dropdown-button' href='#' data-activates='profile'><i class="material-icons">account_circle</i></a>

                            </li>

                          </ul>
                        </div>
                      </div>
                    </div>

                    <div class="nav-content">
                      <ul class="tabs tabs-transparent tabs-fixed-width">
                        <li class="tab">
                          <a href="#test1" class="active">Schedule</a>
                        </li>
                        <li class="tab">
                          <a href="#test2">Report</a>
                        </li>
                        <li class="tab">
                          <a href="#test3">Shuttle Bus</a>
                        </li>
                        <li class="tab">
                          <a href="#test4">User</a>
                        </li>
                        <li class="tab">
                          <a href="#test5">Setting</a>
                        </li>

                      </ul>
                    </div>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Compiled and minified CSS -->


	<!-- Compiled and minified JavaScript -->

	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">

    <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
    <script src="https://unpkg.com/flatpickr"></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">
  <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
  <script src="https://unpkg.com/flatpickr"></script>


	<spring:url value="/resources/css/admin.css" var="admincss" />
	<spring:url value="/resources/js/admin.js" var="adminjs" />
	<link href="${admincss}" rel="stylesheet" />
	<script src ="${adminjs}" type="text/javascript"></script>

</head>
<body>
<nav class="nav-extended">
	<div class="container">
		<div class="nav-wrapper">
      <div class="row nav_row">
			<div class="col s12 m6">
				 <a href="#" class="brand-logo" id='click'>Logo</a>

			</div>

			<div class="col s0 l6">
				<ul id="nav-mobile" class="right hide-on-med-and-down">
        <li class="avatar">
      		<img src="https://s-media-cache-ak0.pinimg.com/736x/64/fb/c9/64fbc98e98bebd0c06dc5f9345724658.jpg" alt="" class="circle profile">
   		 </li>
   		 <li><b>Rathana12</b></li>
   		 <li> <button class="btn waves-effect waves-light logout" type="submit" name="action">Logout</button></li>

      </ul>
			</div>
		</div>
    </div>
    <div class="nav-content">
      <ul class="tabs tabs-transparent tabs-fixed-width">
        <li class="tab"><a href="#test1" class="active">Schedule</a></li>
        <li class="tab"><a  href="#test2">Report</a></li>
        <li class="tab"><a href="#test3">Shuttle Bus</a></li>
        <li class="tab"><a href="#test4">User</a></li>
        <li class="tab"><a href="#test5">Setting</a></li>
        
      </ul>
    </div>
	</div>

  </nav>
  <div class="container">
  <div id="test1" class="col s12">
  	<div class="row">
  		<div class="col s12">
  			<h5 class="title">Shuttle Bus Information</h5>
  		</div>
  		<div class="col s12">
  			<table class="bordered centered">
        <thead>
          <tr>
              <th>Date</th>
              <th>Destination</th>
              <th>Student</th>
              <th>Staff</th>
              <th>Customer</th>
              <th>Detail</th>
              <th>Schedule</th>
          </tr>
        </thead>

        <tbody id="getschedule">

        </tbody>
      </table>
  		</div>
  		<div id="modal1" class="modal modal-fixed-footer">
    <div class="modal-content">
      <h5 class="modal_title" id="detail_title"></h5>
      <table>
        <thead>
          <tr>
              <th>Name</th>
              <th>Batch</th>
              <th>Role</th>
              <th>Email</th>
              <th>Phone</th>
          </tr>
        </thead>

        <tbody id="detail_pass">

        </tbody>
      </table>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
    </div>
  	</div>

  	<div id="modal2" class="modal modal-fixed-footer modal_schedule">
    <div class="modal-content wrap_modal">
 
    	<div class="col s6"> <h6><b>Date:</b> <span id="date_title">Fri, 12/june/2017</span></h6></div>
    	<div class="col s6"><h6><b>Destination: </b><span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span></h6></div>

    	
          <div class="col s3">Total Seats: <span id="remaining_seats"></span> </div>
       

      <table>
        <thead>
          <tr>
              <th>Bus Model</th>
              <th>Driver Name</th>
              <th>Customer</th>
              <th>Total Seats</th>
              <th>Departure</th>
              <th>Arrival</th>
              <th>Customer Only</th>
              <th></th>

          </tr>
        </thead>
        <tbody id="add_more_schedule">
        </tbody>
      </table>
      <span class="add_s"><i class="material-icons icon">add_circle</i> Add</span>

   

      
    





    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="cancel_schedule">Cancel</a>
      <a href="#!" class="modal-action waves-effect waves-green btn-flat" id="set_schedule">Set</a>
    </div>
  </div>

  <div id="view_schedule" class="modal modal-fixed-footer modal_schedule">
    <div class="modal-content">
      <div class="col s6"> <h6><b>Date:</b> <span id="date_title">Fri, 12/june/2017</span></h6></div>
      <div class="col s6"><h6><b>Destination: </b><span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span></h6></div>

      <table>
        <thead>
          <tr>
              <th>Bus</th>
              <th>Driver</th>
              <th>Remaining</th>
              <th>Total Seats</th>
              <th>Departure</th>
              <th>Arrival</th>
              <th>Customer Only</th>
              <th>Detail</th>
          </tr>
        </thead>

        <tbody id="view_pass">

        </tbody>
      </table>

    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
    </div>
    </div>


      <div id="detail_c_pass" class="modal modal-fixed-footer modal_schedule">
    <div class="modal-content wrap_modal">
 
      <div class="col s6"> <h6><b>Date:</b> <span id="date_title">Fri, 12/june/2017</span></h6></div>
      <div class="col s6"><h6><b>Destination: </b><span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span></h6></div>
      <table>
      <thead>
          <tr>
              <th>Name</th>
              <th>Batch</th>
              <th>Role</th>
              <th>Email</th>
              <th>Phone</th>
          </tr>
        </thead>

        <tbody id="view_c_pass">

        </tbody>
      </table>

    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="cancel_schedule">Cancel</a>
      
    </div>
  </div>



  </div>

  </div>
  <div id="test2" class="col s12">
  	<div class="row">
  	<div class="col s12">
  			<h5 class="title">Shuttle Bus and Passenger Report</h5>
  	</div>
  	<div class="col s12">
  		<div class=" row report_date">
  		<table>
  <tr>
    <td><div class="col s10"> FROM: </div></td>
  	<td>
  	
      <div class=" col s10 flatpickr from">
      <input type="text" placeholder="Select Date.." data-input  class=" date_from input flatpickr-input active"> 
      </div>
     
  	</td>
  	
  	<td>
  		<div class="col s1">TO: </div>
  	</td>
  	<td>
	  	
	  		<div class=" col s10 flatpickr to">
      <input type="text" placeholder="Select Date.." data-input  class="date_to input flatpickr-input active"> 
	  	</div>
  	</td>
  	<td>
  		<div class="col s4"><button class="btn waves-effect waves-light report_btn">Report</button></div>
  	</td>

  </tr>

  </table>

 </div>


  	</div>

  		<div class="col s12">
  			<table class="centered bordered">
        <thead>
          <tr>
              <th>Date</th>
              <th>Bus Model</th>
              <th>Bus Driver</th>
              <th>Destination</th>
              <th>Total Seats</th>
              <th>Customer</th>
              <th>Staff</th>
              <th>Student</th>
          </tr>
        </thead>

        <tbody id="report_table">
          
        </tbody>
      </table>
  		</div>
  	</div>
  </div>
  <div id="test3" class="col s12">
  	<div class="row">
  	<div class="col s12">
  			<h5 class="title">List of Shuttle Bus</h5>
  	</div>
  		<div class="col s12">
  			<table class="centered bordered">
        <thead>
          <tr>
              <th>No</th>
              <th>Number of Seats</th>
              <th>Bus Model</th>

              <th>Plate Number</th>
              <th>Option</th>
          </tr>
        </thead>

        <tbody id="schedule_bus">


        </tbody>
      </table>
  		</div>

      <div class="col s12">
        <div id="edit_bus_list" class="modal">
            <div class="modal-content">
             <h5>New Shuttle Bus</h5>
            </div>

              <div class="row">
                <div class="edit_bus_class">
                  
                   <div class="input-field col s4">
                    Number of Seats <input placeholder="Placeholder" id="edit_num_seats" type="text" class="validate">
                    

                  </div>
                    <ul id='profile' class='dropdown-content'>
                      <li><a href="#!"><i class="material-icons">person</i>Va Rathana</a></li>
                      <li><a href="#!"><i class="material-icons">mode_edit</i>Edit</a></li>
                      <li><a href="#!"><i class="material-icons">school</i>Student</a></li>
                      <li><a href="#!"><i class="material-icons">autorenew</i>Log Out</a></li>

                    </ul>
                </nav>
                <div class="container">
                  <div id="test1" class="col s12">
                    <div class="row">
                      <div class="col s12">
                        <h5 class="title">Shuttle Bus Information</h5>
                      </div>
                      <div class="col s12">
                        <table class="bordered centered">
                          <thead>
                            <tr>
                              <th>Date</th>
                              <th>Destination</th>
                              <th>Student</th>
                              <th>Staff</th>
                              <th>Customer</th>
                              <th>Detail</th>
                              <th>Schedule</th>
                            </tr>
                          </thead>

                          <tbody id="getschedule"></tbody>
                        </table>
                      </div>
                      <div id="modal1" class="modal modal-fixed-footer">
                        <div class="modal-content">
                          <h5 class="modal_title" id="detail_title"></h5>
                          <table>
                            <thead>
                              <tr>
                                <th>Name</th>
                                <th>Batch</th>
                                <th>Role</th>
                                <th>Email</th>
                                <th>Phone</th>
                              </tr>
                            </thead>

                            <tbody id="detail_pass"></tbody>
                          </table>
                        </div>
                        <div class="modal-footer">
                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
                        </div>
                      </div>


                      <div id="modal_reason" class="modal modal-fixed-footer">
                        <div class="modal-content">
                          <h5 class="modal_title" id="detail_title">Reason</h5>
                           <p id="detail_reason"></p>

                            <tbody id="detail_emr"></tbody>
                          </table>
                        </div>
                        <div class="modal-footer">
                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
                        </div>
                      </div>



                      <div id="modal2" class="modal modal-fixed-footer modal_schedule">
                        <div class="modal-content wrap_modal">

                          <div class="col s6">
                            <h6>
                              <b>Date:</b>
                              <span id="date_title">Fri, 12/june/2017</span>
                            </h6>
                          </div>
                          <div class="col s6">
                            <h6>
                              <b>Destination:
                              </b>
                              <span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span>
                            </h6>
                          </div>

                          <div class="col s3">Total Seats:
                            <span id="remaining_seats"></span>
                          </div>

                          <table>
                            <thead>
                              <tr>
                                <th>Bus Model</th>
                                <th>Driver Name</th>
                                <th>Customer</th>
                                <th>Total Seats</th>
                                <th>Departure</th>
                                <th>Arrival</th>
                                <th>Customer Only</th>
                                <th></th>

                              </tr>
                            </thead>

                            <tbody id="add_more_schedule"></tbody>
                          </table>

                          <span class="add_s">
                            <i class="material-icons icon">add_circle</i>
                            Add</span>

                        </div>
                        <div class="modal-footer">
                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="cancel_schedule">Cancel</a>
                          <a href="#!" class="modal-action waves-effect waves-green btn-flat" id="set_schedule">Set</a>
                        </div>
                      </div>

                      <div id="edit_schedule" class="modal modal-fixed-footer modal_schedule">
                        <div class="modal-content wrap_modal">

                          <div class="col s6">
                            <h6>
                              <b>Date:</b>
                              <span id="date_title">Fri, 12/june/2017</span>
                            </h6>
                          </div>
                          <div class="col s6">
                            <h6>
                              <b>Destination:
                              </b>
                              <span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span>
                            </h6>
                          </div>

                          <div class="col s3">Total Seats:
                            <span id="remaining_seats"></span>
                          </div>

                          <table>
                            <thead>
                              <tr>
                                <th>Bus Model</th>
                                <th>Driver Name</th>
                                <th>Customer</th>
                                <th>Total Seats</th>
                                <th>Departure</th>
                                <th>Arrival</th>
                                <th>Customer Only</th>
                                <th></th>

                              </tr>
                            </thead>

                            <tbody id="edit_more_schedule"></tbody>

                          </table>
                          <span class="add_edit">
                            <i class="material-icons icon">add_circle</i>
                            Add</span>

                        </div>
                        <div class="modal-footer">
                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="cancel_schedule">Cancel</a>
                          <a href="#!" class="modal-action waves-effect waves-green btn-flat" id="edite_schedule_btn">Edit</a>
                        </div>
                      </div>

                      <div id="view_schedule" class="modal modal-fixed-footer modal_schedule">
                        <div class="modal-content">
                          <div class="col s6">
                            <h6>
                              <b>Date:</b>
                              <span id="date_title">Fri, 12/june/2017</span>
                            </h6>
                          </div>
                          <div class="col s6">
                            <h6>
                              <b>Destination:
                              </b>
                              <span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span>
                            </h6>
                          </div>

                          <table>
                            <thead>
                              <tr>
                                <th>Bus</th>
                                <th>Driver</th>
                                <th>Remaining</th>
                                <th>Total Seats</th>
                                <th>Departure</th>
                                <th>Arrival</th>
                                <th>Customer Only</th>
                                <th>Detail</th>
                              </tr>
                            </thead>

                            <tbody id="view_pass"></tbody>
                          </table>
                        </div>
                        <div class="modal-footer">
                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
                        </div>
                      </div>

                      <div id="detail_c_pass" class="modal modal-fixed-footer modal_schedule">
                        <div class="modal-content wrap_modal">

                          <div class="col s6">
                            <h6>
                              <b>Date:</b>
                              <span id="date_title">Fri, 12/june/2017</span>
                            </h6>
                          </div>
                          <div class="col s6">
                            <h6>
                              <b>Destination:
                              </b>
                              <span class="modal_destination" id="destination_title">Kirirom to Phnom Penh</span>
                            </h6>
                          </div>
                          <table>
                            <tr>
                              <th>Name</th>
                              <th>Batch</th>
                              <th>Role</th>
                              <th>Email</th>
                              <th>Phone</th>
                            </tr>
                          </thead>
                        </thead>

                        <tbody id="view_c_pass"></tbody>
                      </table>

                    </div>
                    <div class="modal-footer">
                      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="cancel_schedule">Cancel</a>

                    </div>
                  </div>

                </div>


              </div>
              <div id="test2" class="col s12">
                <div class="row">
                  <div class="col s12">
                    <h5 class="title">Shuttle Bus and Passenger Report</h5>
                  </div>
                  <div class="col s12">
                    <div class=" row report_date">
                      <table>
                        <tr>
                          <td>
                            <div class="col s10">
                              FROM:
                            </div>
                          </td>
                          <td>

                            <div class=" col s10 flatpickr from">
                              <input type="text" placeholder="Select Date.." data-input class=" date_from input flatpickr-input active"></div>

                            </td>

                            <td>
                              <div class="col s1">TO:
                              </div>
                            </td>
                            <td>

                              <div class=" col s10 flatpickr to">
                                <input type="text" placeholder="Select Date.." data-input class="date_to input flatpickr-input active"></div>
                              </td>
                              <td>
                                <div class="col s4">
                                  <button class="btn waves-effect waves-light report_btn">Report</button>
                                </div>
                              </td>

                            </tr>

                          </table>

                        </div>

                      </div>

                      <div class="col s12">
                        <table class="centered bordered ">
                          <thead>
                            <tr>
                              <th>Date</th>
                              <th>Bus Model</th>
                              <th>Bus Driver</th>
                              <th>Destination</th>
                              <th>Total Seats</th>
                              <th>Customer</th>
                              <th>Staff</th>
                              <th>Student</th>
                            </tr>
                          </thead>

                          <tbody id="report_table"></tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                  <div id="test3" class="col s12">
                    <div class="row">
                      <div class="col s12">
                        <h5 class="title">List of Shuttle Bus</h5>
                      </div>
                      <div class="col s12">
                        <table class="centered bordered">
                          <thead>
                            <tr>
                              <th>No</th>
                              <th>Number of Seats</th>
                              <th>Bus Model</th>

                              <th>Plate Number</th>
                              <th>Option</th>
                            </tr>
                          </thead>

                          <tbody id="schedule_bus"></tbody>
                        </table>
                      </div>

                      <div class="col s12">
                        <div id="edit_bus_list" class="modal">
                          <div class="modal-content">
                            <h5>New Shuttle Bus</h5>
                          </div>

                          <div class="row">
                            <div class="edit_bus_class">

                              <div class="input-field col s4">
                                Number of Seats
                                <input placeholder="Placeholder" id="edit_num_seats" type="text" class="validate"></div>
                                <div class="input-field col s4">
                                  Bus Model
                                  <input id="edit_bus_model" type="text" class="validate"></div>
                                  <div class="input-field col s4">
                                    Plate Number
                                    <input id="edit_plate_number" type="text" class="validate"></div>
                                  </div>

                                </div>

                                <div class="modal-footer footer_bus_edit">
                                  <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat edit_bus_btn">EDIT</a>
                                </div>
                              </div>

                            </div>

                            <div class="col s12">
                              <div id="delete_bus_list" class="modal">
                                <div class="modal-content">
                                  <h5>New Shuttle Bus</h5>
                                </div>

                                <div class="row">
                                  <div class="edit_bus_class">
                                    <span style="color: red">Wearning!</span>
                                    : It will delete this bus forever.
                                  </div>

                                </div>

                                <div class="modal-footer footer_bus_edit">
                                  <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat delete_bus_btn">DELETE</a>
                                </div>
                              </div>
                            </div>

                            <div class="col s12">
                              <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                                <a class="btn-floating waves-effect waves-light btn-large" href="#modal3">
                                  <i class="material-icons">add</i>
                                </a>
                              </div>

                            </div>
                            <div class="col s12">

                              <div id="modal3" class="modal modal-fixed-footer modal_new_bus">
                                <div class="row">
                                  <div class="modal-content">

                                    <div class="col s12">
                                      <h5 class="title_new_bus">New Shuttle Bus</h5>
                                    </div>
                                    <div class="col s12 add_list_bus">
                                      <table>
                                        <tr>

                                          <td>Bus Model:
                                          </td>
                                          <td>
                                            <div class="input-field col s10">
                                              <input placeholder="Placeholder" id="bus_model" type="text" class="validate"></div>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td>Plate Number:
                                            </td>
                                            <td>

                                              <div class="input-field col s10">
                                                <input placeholder="Placeholder" id="plate_number" type="text" class="validate"></div>
                                              </td>
                                              <td>Bus Image:
                                              </td>
                                              <td>
                                                <a href="file:///C:\">&nbsp &nbsp &nbsp &nbsp<i class="material-icons">directions_car</i>
                                                </a>
                                              </td>
                                            </tr>
                                            <tr>
                                              <td>Total No of Seats:
                                              </td>
                                              <td>
                                                <div class="input-field col s10">
                                                  <input placeholder="Placeholder" id="total_seats" type="text" class="validate"></div>
                                                </td>

                                              </tr>
                                            </table>
                                          </div>

                                        </div>

                                      </div>
                                      <div class="modal-footer">
                                        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat agree_add">ADD</a>
                                      </div>
                                    </div>

                                  </div>
                                </div>
                              </div>
                              <div id="test4" class="col s12">
                                <div class="row">
                                  <div class="col s12">
                                    <h5 class="title">List of User</h5>
                                  </div>
                                  <div class="col s12">
                                    <table class="centered bordered">
                                      <thead>
                                        <tr>
                                          <th>No</th>
                                          <th>Name</th>
                                          <th>Password</th>
                                          <th>Batch</th>
                                          <th>Email</th>
                                          <th>Gender</th>
                                          <th>Role</th>
                                          <th>Phone</th>
                                        </tr>
                                      </thead>

                                      <tbody id="user"></tbody>
                                    </table>
                                  </div>
                                  <div class="col s12">
                                    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                                      <a id="pop_up_user" class="btn-floating waves-effect waves-light btn-large" href="#modal4">
                                        <i class="material-icons">add</i>
                                      </a>
                                    </div>

                                  </div>
                                  <div class="col s12">
                                    <form id="form_add_user">
                                    <!-- Modal Structure -->
                                    <div id="modal4" class="modal modal-fixed-footer ">
                                      <div class="row">
                                        <div class="modal-content">

                                          <div class="col s12">
                                            <h5 class="title_new_bus">Add New User</h5>
                                          </div>
                                          <div class="col s12">
                                          
                                            <table>

                                              <tr>
                                                <td>First Name:
                                                </td>
                                                <td>

                                                  <div class="input-field col s10">
                                                    <input placeholder="Name" id="fname" name="fname" type="text" class="validate"></div>
                                                  </td>
                                                  <td>Last Name:
                                                </td>
                                                <td>

                                                  <div class="input-field col s10">
                                                    <input placeholder="Name" id="lname" name="lname" type="text" class="validate"></div>
                                                  </td>
                                                  
                                                </tr>

                                                <tr>
                                                  <td>Email:
                                                    </td>
                                                    <td>

                                                      <div class="input-field col s10">
                                                        <input placeholder="Email" id="email" type="email" class="validate" name="email"  required="" aria-required="true" ></div>
                                                      </td>
                                                  <td>Password:
                                                  </td>
                                                  <td>
                                                    <div class="input-field col s10">
                                                      <input placeholder="Password" id="password" type="password" class="validate" name="password"></div>
                                                    </td>
                                                    
                                                  </tr>

                                                  <tr>
                                                    
                                                      <td>Gender:
                                                  </td>
                                                  <td>
                                                    <div class="input-field col s10" id="gender" name="gender">
                                                      <select>
                                                        <option value="" disabled selected>Gender</option>
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>

                                                      </select>
                                                    </div>
                                                  </td>
                                                      <td>phone Number:
                                                      </td>
                                                      <td>
                                                        <div class="input-field col s10">
                                                          <input placeholder="Phone Number" id="phone" type="text" class="validate" name="phone" ></div>
                                                        </td>
                                                      </tr>

                                                      <tr>
                                                        <td>Batch:
                                                        </td>
                                                        <td>
                                                          <div class="input-field col s10" id="batch" name="batch"></div>
                                                        </td>
                                                        <td>Role:
                                                    </td>
                                                    <td>
                                                      <div class="input-field col s10" id="role"></div>
                                                    </td>
                                                      
                                                        </tr>
                                                      </table>
                                                     
                                                    </div>

                                                  </div>

                                                </div>
                                                <div class="modal-footer">
                                              <button class="btn waves-effect waves-light" 
                                              type="submit" name="action">ADD</button>
                                                </div>
                                              </div>
                                               </form>
                                            </div>
                                          </div>

                                        </div>
                                        <div id="test5" class="col s12">
                                          <div class="row">
                                            <div class="col s12">
                                              <h5 class="title">Current Batch Information</h5>
                                            </div>
                                            <div class="col s12">
                                              <table class="centered bordered">
                                                <thead>
                                                  <tr>
                                                    <th>No.</th>
                                                    <th>Batch Number</th>
                                                    <th>Leave Date</th>
                                                    <th>Return Date</th>
                                                    <th>Deadline Booking</th>
                                                    <th>Reset</th>
                                                  </tr>
                                                </thead>
                                                <tbody id="">
                                                  <tr>
                                                    <td>1</td>
                                                    <td>Batch 1</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017: 5pm</td>
                                                    <td>
                                                      <a href="#batchEdit">Edit</a>
                                                      /
                                                      <a href="#batchDelete">Delete</a>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td>2</td>
                                                    <td>Batch 2</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017: 5pm</td>
                                                    <td>
                                                      <a href="#batchEdit">Edit</a>
                                                      /
                                                      <a href="#batchDelete">Delete</a>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td>3</td>
                                                    <td>Batch 3</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017</td>
                                                    <td>12/07/2017: 5pm</td>
                                                    <td>
                                                      <a href="#batchEdit">Edit</a>
                                                      /
                                                      <a href="#batchDelete">Delete</a>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>
                                                      <a href="#addBatch">Add</a>
                                                    </td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                            </div>
                                            <div class="col s12">
                                              <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                                                <a id="pop_up_user" class="btn-floating waves-effect waves-light btn-large" href="#modal4">
                                                  <i class="material-icons">add</i>
                                                </a>
                                              </div>
                                            </div>
                                          </div>
                                          <!-- Batch Edit -->
                                          <div id="batchEdit" class="modal modal-fixed-footer">
                                            <div class="modal-content">
                                              <h5 class="blue-text center">Edit Batch Information</h5>
                                              <table>
                                                <tr>
                                                  <td>Batch Number<span class="right">:</span>
                                                  </td>
                                                  <td>Batch 1</td>
                                                </tr>
                                                <tr>
                                                  <td>Leave Date<span class="right">:</span>
                                                  </td>
                                                  <td>
                                                    <div class="input-field s6 flatpickr">
                                                      <input type="text" placeholder="Select Date" id="leaveDate" data-input class="input flatpickr-input active"></div>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td>Return Date<span class="right">:</span>
                                                    </td>
                                                    <td>
                                                      <div class="input-field s6 flatpickr">
                                                        <input type="text" placeholder="Select Date" id="returnDate" data-input class="input flatpickr-input active"></div>
                                                      </td>
                                                    </tr>
                                                    <tr>
                                                      <td>Deadline Booking<span class="right">:</span>
                                                      </td>
                                                      <td>
                                                        <div class="input-field s6 flatpickr">
                                                          <input type="text" placeholder="Select Date" id="deadlineBooking" data-input class="input flatpickr-input active"></div>
                                                        </td>
                                                      </tr>
                                                    </table>
                                                  </div>
                                                  <div class="modal-footer">
                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Update</a>
                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                                  </div>
                                                </div>
                                                <!-- Batch Delete -->
                                                <div id="batchDelete" class="modal modal-fixed-footer delete_modal">
                                                  <div class="modal-content">
                                                    <h5 class="blue-text">Delete Confirm</h5>
                                                    <p>Are you sure, you want to delete this batch?</p>
                                                  </div>
                                                  <div class="modal-footer">
                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Yes</a>
                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">No</a>
                                                  </div>
                                                </div>
                                                <!-- Add Batch -->
                                                <div id="addBatch" class="modal modal-fixed-footer">
                                                  <div class="modal-content">
                                                    <h5 class="blue-text center">Add New Batch</h5>
                                                    <table>
                                                      <tr>
                                                        <td>Batch Number<span class="right">:</span>
                                                        </td>
                                                        <td>Batch 4</td>
                                                      </tr>
                                                      <tr>
                                                        <td>Leave Date<span class="right">:</span>
                                                        </td>
                                                        <td>
                                                          <div class="input-field s6 flatpickr">
                                                            <input type="text" placeholder="Select Date" id="leaveDate" data-input class="input flatpickr-input active"></div>
                                                          </td>
                                                        </tr>
                                                        <tr>
                                                          <td>Return Date<span class="right">:</span>
                                                          </td>
                                                          <td>
                                                            <div class="input-field s6 flatpickr">
                                                              <input type="text" placeholder="Select Date" id="returnDate" data-input class="input flatpickr-input active"></div>
                                                            </td>
                                                          </tr>
                                                          <tr>
                                                            <td>Deadline Booking<span class="right">:</span>
                                                            </td>
                                                            <td>
                                                              <div class="input-field s6 flatpickr">
                                                                <input type="text" placeholder="Select Date" id="deadlineBooking" data-input class="input flatpickr-input active"></div>
                                                              </td>
                                                            </tr>
                                                          </table>
                                                        </div>
                                                        <div class="modal-footer">
                                                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Add</a>
                                                          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                                        </div>
                                                      </div>
                                                      <div class="row">
                                                        <div class="col s12">
                                                          <h5 class="title">Destination Infomation</h5>
                                                        </div>
                                                        <div class="col s12">
                                                          <table class="centered bordered">
                                                            <thead>
                                                              <tr>
                                                                <th>No.</th>
                                                                <th>Destination name</th>
                                                                <th>Reset</th>
                                                              </tr>
                                                            </thead>
                                                            <tbody id="">
                                                              <tr>
                                                                <td>1</td>
                                                                <td>Kirirom to Phnom Penh</td>
                                                                <td>
                                                                  <a href="#destinationEdit">Edit</a>
                                                                  /
                                                                  <a href="#destinationDelete">Delete</a>
                                                                </td>
                                                              </tr>
                                                              <tr>
                                                                <td>2</td>
                                                                <td>Phnom Penh to Kirirom</td>
                                                                <td>
                                                                  <a href="#destinationEdit">Edit</a>
                                                                  /
                                                                  <a href="#destinationDelete">Delete</a>
                                                                </td>
                                                              </tr>
                                                              <tr>
                                                                <td></td>
                                                                <td></td>
                                                                <td>
                                                                  <a href="#addDestination">Add</a>
                                                                </td>
                                                              </tr>
                                                            </tbody>
                                                          </table>
                                                        </div>
                                                        <!-- Destination Edit -->
                                                        <div id="destinationEdit" class="modal modal-fixed-footer dest_">
                                                          <div class="modal-content">
                                                            <h5 class="blue-text center">Edit Batch Information</h5>
                                                            <br>
                                                              Destination Name:&nbsp
                                                              <div class="input-field inline">
                                                                <input id="des_name" type="text" class="validate">
                                                                  <label id="des_name_label" for="des_name">Destination Name</label>
                                                                </div>
                                                              </div>
                                                              <div class="modal-footer">
                                                                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Update</a>
                                                                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                                              </div>
                                                            </div>
                                                            <!-- Batch Delete -->
                                                            <div id="destinationDelete" class="modal modal-fixed-footer delete_modal">
                                                              <div class="modal-content">
                                                                <h5 class="blue-text">Delete Confirm</h5>
                                                                <p>Are you sure, you want to delete this destination?</p>
                                                              </div>
                                                              <div class="modal-footer">
                                                                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Yes</a>
                                                                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">No</a>
                                                              </div>
                                                            </div>
                                                            <!-- Add Destination -->
                                                            <div id="addDestination" class="modal modal-fixed-footer dest_">
                                                              <div class="modal-content">
                                                                <h5 class="blue-text center">Add New Batch</h5>
                                                                <br>
                                                                  Destination Name:&nbsp
                                                                  <div class="input-field inline">
                                                                    <input id="des_name" type="text" class="validate">
                                                                      <label id="des_name_label" for="des_name">Destination Name</label>
                                                                    </div>
                                                                  </div>
                                                                  <div class="modal-footer">
                                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Add</a>
                                                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                                                  </div>
                                                                </div>
                                                              </div>
                                                            </div>
                                                            <!-- tab 5 -->
                                                          </div>
                                                         <div id="modal_notif" class="modal modal-fixed-footer">
                                                          <div class="modal-content">
                                                            <h5 class="modal_title" id="detail_title"></h5>
                                                            <table>
                                                              <thead>
                                                                <tr>
                                                                  <th>Name</th>
                                                                  <th>Role</th>
                                                                  <th>Date Of Travel</th>
                                                                  <th>Destination</th>
                                                                  <th>Reason</th>
                                                                  <th>Confirm</th>
                                                                </tr>
                                                              </thead>

                                                              <tbody id="detail_emr"></tbody>
                                                            </table>
                                                          </div>
                                                          <div class="modal-footer">
                                                            <a href="#!" class="modal-action waves-effect waves-green btn-flat confirm_btn">Confirm</a>
                                                            <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                                          </div>
                                                        </div>

                                                      </html>

                  </div>
              </td>
            </tr>
    			</table>
    		</div>

    	</div>


    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat add_user">ADD</a>
    </div>
  </div>

  		</div>
  </div>
  
  </div>
  <div id="test5" class="col s12">
  	<div class="row">
	  	<div class="col s12">
	  			<h5 class="title">Current Batch Information</h5>
	  	</div>
	  		<div class="col s12">
	  			<table class="centered bordered">
			        <thead>
			          <tr>
			              <th>No.</th>
			              <th>Batch Number</th>
			              <th>Leave Date</th>
			              <th>Return Date</th>
			              <th>Deadline Booking</th>
			              <th>Reset</th>
			          </tr>
			        </thead>
			        <tbody id="get_batch_info">
			        	<!-- 
			        	<tr>
							<td>1</td>
							<td>Batch 1</td>
							<td>12/07/2017</td>
							<td>12/07/2017</td>
							<td>12/07/2017: 5pm</td>
							<td><a href="#batchEdit">Edit</a> / <a href="#batchDelete">Delete</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><a href="#addBatch">Add</a></td>
						</tr>
			        	 --> 
			        </tbody>
			      </table>
	  		</div>
  </div>
  <!-- Batch Edit -->
  <div id="batchEdit" class="modal modal-fixed-footer">
    <div class="modal-content">
      <h5 class="blue-text center">Edit Batch Information</h5>
      <table id="table_edit_batch">
      <!-- 
      	<tr>
			<td>Batch Number<span class="right">:</span></td>
			<td>Batch 1</td>
		</tr>
		<tr>
			 <td>Leave Date<span class="right">:</span></td>
			 <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="leaveDate" data-input class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
		<tr>
			  <td>Return Date<span class="right">:</span></td>
			  <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="returnDate" data-input class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
		<tr>
			 <td>Deadline Booking<span class="right">:</span></td>
			 <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="deadlineBooking" data-input  class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
       -->
	  </table>
    </div>
    <div class="modal-footer">
	    <span id="bat_update">
	    	<!-- <a class="modal-action modal-close waves-effect waves-green btn-flat">Update</a> -->
	    </span>
        <a class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
    </div>
  </div>
  <!-- Batch Delete -->
  <div id="batchDelete" class="modal modal-fixed-footer delete_modal">
    <div class="modal-content">
      <h5 class="blue-text">Delete Confirm</h5>
      <p id="delete_batch_warning"></p>
    </div>
    <div class="modal-footer">
      <span id="delete_batch_id">
      		<!-- <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Yes</a>  -->  
      </span>
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">No</a>
    </div>
  </div>
  <!-- Add Batch -->
  <div id="addBatch" class="modal modal-fixed-footer">
    <div class="modal-content">
      <h5 class="blue-text center">Add New Batch</h5>
      <table>
		 <tr>
			<td>Batch Number<span class="right">:</span></td>
			<td id="new_batch"></td>
		</tr>
		<tr>
			 <td>Leave Date<span class="right">:</span></td>
			 <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="new_leaveDate" data-input class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
		<tr>
			  <td>Return Date<span class="right">:</span></td>
			  <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="new_returnDate" data-input class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
		<tr>
			 <td>Deadline Booking<span class="right">:</span></td>
			 <td>
			 	<div class="input-field s6 flatpickr">
				   	<input type="text" placeholder="Select Date" id="new_deadline" data-input  class="input flatpickr-input active"> 
				</div>
			 </td>
		</tr>
	  </table>
    </div>
    <div class="modal-footer">
      <a class="modal-action modal-close waves-effect waves-green btn-flat add_new_batch">Add</a>
      <a class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
    </div>
  </div> 
  <div class="row">
  		<div class="col s12">
	  		<h5 class="title">Destination Infomation</h5>
	  	</div>
	  	<div class="col s12">
	  			<table class="centered bordered">
			        <thead>
			          <tr>
			              <th>No.</th>
			              <th>Destination name</th>
			              <th>Reset</th>
			          </tr>
			        </thead>
			        <tbody id="getDes">
			        </tbody>
			      </table>
	  	</div>
	  	<!-- Destination Edit -->
	  <div id="destinationEdit" class="modal modal-fixed-footer dest_form">
	    <div class="modal-content">
	      <h5 class="blue-text center">Edit Batch Information</h5>
	      <br>
	        Destination Name:&nbsp
			<div class="input-field inline" id="getDesText">
			     <input id="des_name" type="text" class="validate" value="">  
			</div>
	    </div>
	    <div class="modal-footer">
	      <span id="des_edit_footer"></span>
	      <a href="#!" class="modal-action modal-close btn-flat">Cancel</a>
	    </div>
	  </div>
	  <!-- Destination Delete -->
	  <div id="destinationDelete" class="modal modal-fixed-footer delete_modal">
	    <div class="modal-content">
	      <h5 class="blue-text">Delete Confirm</h5>
	      <p>Are you sure, you want to delete this destination?</p>
	    </div>
	    <div class="modal-footer">
		    <span id="getDesDelete">
		    </span>
	        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">No</a>
	    </div>
	  </div>
	  <!-- Add Destination -->
	  <div id="addDestination" class="modal modal-fixed-footer dest_form">
	    <div class="modal-content">
	      <h5 class="blue-text center">Add New Batch</h5>
	      <br>
	        Destination Name:&nbsp
			<div class="input-field inline">
			     <input id="new_des_name" type="text" class="validate">
			</div>
	    </div>
	    <div class="modal-footer">
	      <a class="modal-action modal-close waves-effect waves-green btn-flat add_new_des">Add</a>
	      <a class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
	    </div>
	  </div>
  </div>
</div><!-- tab 5 -->
</div><!-- hold tab -->
<footer class="page-footer">
          <div class="container">
            <div class="row">
              <div class="col s12">
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
              </div>
            </div>
          </div>
          <div class="footer-copyright">
            <div class="container">
            © 2014 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
          </div>
</footer>
</body>
</html>

