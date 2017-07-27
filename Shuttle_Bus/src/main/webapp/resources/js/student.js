$(document).ready(function(){	
  				var optionWay='round';
  				var iteration=2;
  				
  				
				//BookingSession
				$("#Round").show();
				$("#One").hide();
				$('select').material_select();
				$("input:radio[id=roundWay]").click(function() {
					optionWay='round';
					iteration=2;
				 	$("#Round").show();
					$("#One").hide();
				});
				$("input:radio[id=oneWay]").click(function() {
					optionWay='one';
					iteration=1;
				 	$("#One").show();
					$("#Round").hide();
					autoSelect(optionWay);
				});
				
					
					//date picker
				  $('.datepicker').pickadate({
				    selectMonths: true, // Creates a dropdown to control month
				    selectYears: 15, // Creates a dropdown of 15 years to control year	
				    dateFormat: "Y-m-d",
				    onSet: function( arg ){
				        if ( 'select' in arg ){ //prevent closing on selecting month/year
				            this.close();
				        }
				    }
				  });
				  
				  //select date
				  $('#goDate_one').change(function(){      //Date in full format alert(new Date(this.value));
					  goDate_one = new Date(this.value);
				    });
				  $('#goDate_round').change(function(){      //Date in full format alert(new Date(this.value));
					  goDate_round = new Date(this.value);
				    });
				  $('#backDate_round').change(function(){      //Date in full format alert(new Date(this.value));
					  backDate_round = new Date(this.value);
				    });
				  
				  
				  	// booking option
				  	autoSelect(optionWay);
					function autoSelect(id){
						$('#fromDes_'+id).on('change', function() {
							  var name = this.value ;
							  if(name=="Kirirom"){		  
								  var select='<select id="toDes_'+id+'"><option value="Phnom Penh">Phnom Penh</option></select>';
								  document.getElementById("to_"+id).innerHTML = select;
								  
							  }
							  if(name=="Phnom Penh"){
								  var select1='<select id="toDes_'+id+'"><option value="Kirirom">Kirirom</option></select>';
								  document.getElementById("to_"+id).innerHTML = select1;
								  
							  }
							})
					}
				  
					$('.modal').modal();
					
					
									
				    //submit booking
					$(".bookNow").click(function(){
					      var destinationId1 = $('#fromDes_'+optionWay).find(":selected").text()+" to "+$('#toDes_'+optionWay).find(":selected").text() ;
					      var destinationId2 = $('#toDes_'+optionWay).find(":selected").text()+" to "+$('#fromDes_'+optionWay).find(":selected").text() ;
					    
					      var date_of_booking =new Date($.now());
					      	if(true){
					      		submit = [];
					      		for(i=0;i<iteration;i++){
					      			submit[i]["destinationId"]= destinationId+i;
						      		submit[i]["date_of_traavel"]= date_of_traavel+i;
						      		submit[i]["date_of_booking"]= new Date();
					      		}
					      		$.ajax({
									type : "POST",
									contentType : "application/json",
									url : "booking_service",
									data : JSON.stringify(test),
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
					
					//modal schedule template
					$.ajax({
						url:'list',
						type:'POST',
						success: function(response){
								schedule = response.data;
								var template = $('#template').html();
								// compile it with Template7
								var compiledTemplate = Template7.compile(template);
								// Now we may render our compiled template by passing required context
								var html = compiledTemplate(schedule);
								document.getElementById("getSchedule").innerHTML = html;		
						}				
					});
					
					//modal passenger detail template
					$.ajax({
						url:'list',
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
					//model emergency request template
					$.ajax({
						url:'list',
						type:'POST',
						success: function(response){
								emer_schedule = response.data;
								var template = $('#modale_emergency').html();
								// compile it with Template7
								var compiledTemplate = Template7.compile(template);
								// Now we may render our compiled template by passing required context
								var html = compiledTemplate(emer_schedule);
								document.getElementById("getEmergency").innerHTML = html;		
						}				
					});
					
				});