var signOutAdmin=function(){
	localStorage.clear();
	window.location.replace("/HUIT/homepage.html");
}

$(document).ready(function(){
	$("#okBtn").click(function(e){
		var modal = document.getElementById('myModal1');
		modal.style.display = "none";
		window.location.replace("/HUIT/viewtest.html");
	});
        
             $( "#generatepasskey" ).click(function() {
            	 
            	 var JSONObject ={
            			  "testDto": 
            			    {
            			      "noOfApti":document.getElementById("noofapti").value,
            			      "noOfEng":document.getElementById("noofeng").value,
            			      "noOfLR":document.getElementById("nooflr").value,
            			      "testTime":document.getElementById("time").value,
            			      "batch":document.getElementById("batchname").value,
            			      "college":document.getElementById("collegetest").value,
            			      "dot":document.getElementById("date").value,
            			      "difficulty":$('#difficulty').val(),
            			      "time_apti":document.getElementById("timeapti").value,
            			    	 "time_eng":document.getElementById("timeeng").value,
            			    		 "time_lr":document.getElementById("timelr").value
            			    }
            			  
            			};
					console.log(JSONObject)
					var passkey;
					$.ajax({
								"url" : "/HUIT/test/createTest",
								"method" : "POST",
								"contentType" : "application/json",
								"data" : JSON.stringify(JSONObject),
								"processData" : false,
								"dataType" : "text",

								success : function(data) {
									console.log(data);
									passkey=data;
									var modal = document.getElementById('myModal1');
									var span = document.getElementsByClassName("close1")[0];
									modal.style.display = "block";
									span.onclick = function() {
									    modal.style.display = "none";}
									$( "#passkey" ).css("visibility","visible");
					                $( "#passkey" ).html(passkey);
									
								},
								error : function(d) {
									console.log(d)
								}
							});
                

                    });
            
             });