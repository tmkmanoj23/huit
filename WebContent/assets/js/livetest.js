var response1;

$(document).ready(function() {
	$("#okBtn").click(function(e){
		var modal = document.getElementById('myModal1');
		modal.style.display = "none";
	});
	
	$('#liveTestSubmit').click(function(e) {
		var ph = document.getElementById("phone").value;
		var pass = document.getElementById("passkey").value;
		console.log(ph);
		if(ph==localStorage.getItem("ph")){
	$.ajax({
		"url" : "/HUIT/test/testValidation/"+pass+"/"+ph,
		"method" : "GET",
		"contentType" : "application/json",
//		"data" : JSON.stringify(JSONObject),
		"processData" : false,
		"dataType" : "text",

		success : function(data) {
			if(data=="Success"){
				localStorage.setItem("testvalid","valid");
				localStorage.setItem("ph", ph);
				localStorage.setItem("passkey", pass);
				$.ajax({
					"url" : "/HUIT/test/getRcordOnPassKey/"+pass,
					"method" : "GET",
					"contentType" : "application/json",
//					"data" : JSON.stringify(JSONObject),
					"processData" : false,
					"dataType" : "JSON",

					success : function(data) {
						response1=data.testDto;
						console.log(data);
						setTime();
						window.location.replace("/HUIT/aptitude.html");
					},
					error : function(d) {
						console.log(d)
					}
				});
				
			}
			else if(data=="Attempt Exceded"){
				var modal = document.getElementById('myModal1');
				var span = document.getElementsByClassName("close1")[0];
				modal.style.display = "block";
				$("#modalData").text("You have already given the test, for further assitance contact the admin");
				span.onclick = function() {
				    modal.style.display = "none";}
			}
			else{
				var modal = document.getElementById('myModal1');
				var span = document.getElementsByClassName("close1")[0];
				modal.style.display = "block";
				$("#modalData").text("Invalid Credentials");
				span.onclick = function() {
				    modal.style.display = "none";}
			}
			console.log(data);

		},
		error : function(d) {
			console.log(d)
		}
	});}
	else{
		var modal = document.getElementById('myModal1');
		var span = document.getElementsByClassName("close1")[0];
		modal.style.display = "block";
		$("#modalData").text("Invalid Phone Number");
		span.onclick = function() {
		    modal.style.display = "none";}
	}
	});
	
});
var setTime=function(){
	localStorage.setItem("timeApti",response1.time_apti);
	localStorage.setItem("timeEng",response1.time_eng);
	localStorage.setItem("timeLr",response1.time_lr);
}