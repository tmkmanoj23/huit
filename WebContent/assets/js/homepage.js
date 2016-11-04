$(document).ready(function() {

	$('#submit').click(function(e) {

		console.log("bttn")
		var name = document.getElementById("name").value;
		var email = document.getElementById("email").value;
		var message= document.getElementById("message").value;
		var subject= document.getElementById("subject").value;;
		
		var JSONObject = {
			"messageHomePageDto" : {
				"name" : name,
				"email" : email,
				"subject" : subject,
				"msg" : message
			}
		};
		
		console.log(JSONObject);
		$.ajax({
			"url" : "/HUIT/message/sendmessage",
			"method" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify(JSONObject),
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log(data);
				 document.getElementById("name").value="";
				 document.getElementById("email").value="";
				 document.getElementById("message").value="";
				 document.getElementById("subject").value="";
			},
			error : function(d) {
				console.log(d)
			}
		});

		e.preventDefault();
	});
});