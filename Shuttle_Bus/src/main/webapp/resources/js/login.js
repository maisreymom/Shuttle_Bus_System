$(document).ready(function(){
     $(".submit-button .btn").click(function(){
      var email = $("#username").val();
      var password = $("#password").val();      
      
      	if(validateEmail(email) && validatePassword(password)){
      		 submit = {};
      		 submit["email"] = email;
      		 submit["password"] = password;
      		$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "login_service",
				data : JSON.stringify(submit),
				timeout : 100000,
				success : function(data) {
					document.location.href = '/ShuttleBusSystem/users/'+data;
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

function validateEmail(username) 
{
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(username);
}
function validatePassword(password)
{
	var valid = false;
	if(password.length>=8){
		valid = true;
	}
	return valid;
}