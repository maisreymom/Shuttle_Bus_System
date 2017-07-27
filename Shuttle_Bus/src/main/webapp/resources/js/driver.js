
 $(document).ready(function(){
    // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
    
    //user info
	$.ajax({
		url:'user_info',
		type:'POST',
		success: function(response){
				user = response.data;
				document.getElementById("getPassenger").innerHTML ='<img src="'+user.image+'" alt="" class="circle profile">' ;		
		}				
	});  
    
    
   //modal schedule template
	$.ajax({
		url:'schedule_info',
		type:'POST',
		success: function(response){
				schedule = response.data;
				var template = $('#Schedule').html();
				// compile it with Template7
				var compiledTemplate = Template7.compile(template);
				// Now we may render our compiled template by passing required context
				var html = compiledTemplate(schedule);
				document.getElementById("getSchedule").innerHTML = html;		
		}				
	});
	
	//modal passenger detail template
	$.ajax({
		url:'passenger_info',
		type:'POST',
		success: function(response){
				passenger = response.data;
				var template = $('#modalPassenger').html();
				// compile it with Template7
				var compiledTemplate = Template7.compile(template);
				// Now we may render our compiled template by passing required context
				var html = compiledTemplate(passenger);
				document.getElementById("getPassenger").innerHTML = html;		
		}				
	});
  });