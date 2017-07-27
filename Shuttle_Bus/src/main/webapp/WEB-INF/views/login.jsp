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
	          
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">
	
	<spring:url value="/resources/css/login.css" var="login" />
	
	<link href="${login}" rel="stylesheet" />
	<spring:url value="/resources/js/login.js" var="loginjs" />
	
	<script src ="${loginjs}" type="text/javascript"></script>
</head>
<body>
 
	<div>
	  	<div class="row">
     	 <div class="col s10 m6 l4 offset-s1 offset-m3 offset-l4">
        <div class="card-panel z-depth-3">
       
        <div class="col s12 center ">
        	 <span class="title">LOGIN</span>
        </div>
        <div class="row">
        <div class="input-field col s12">
          <input id="username" type="text" class="validate">
          <label for="username">Email</label>
        </div>
      
        <div class="input-field col s12">
          <input id="password" type="password" class="validate">
          <label for="password">Password</label>
        </div>
     
      	<div class="col s12">
      		<div class="submit-button">
      		<button class="btn waves-effect waves-light">Submit</button></div>
      	
      </div>
      	<div class="row">
      	<div class="col s12">
      		<table>
      			<tr>
      				<td>
      					 <input type="checkbox" class="filled-in" id="filled-in-box" checked="checked" />
			      		 <label for="filled-in-box">Remember</label><br>
			      		 
      				</td>

      				<td class="forgot"><a href="/#">forgot password?</a></td>
      			</tr>
      		</table>
      	</div>
      		
      		
        </div>
      </div>
    </div>
	  </div>
	  </div>
	</div>	 
	
	
</body>
</html>