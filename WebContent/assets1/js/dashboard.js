function checkph(){
	var phone=localStorage.getItem("phadmin");
	if(phone==null){
	window.location.replace("/HUIT/homepage.html");
}}
window.onload = checkph;

var testDetails;
var showDetails=function(passkey){
//	var message="<table style=" height: '78px';" width='90'><tbody><tr><td>hello</td><td>hi&nbsp;</td><td>bye</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>bye</td><td>&nbsp;</td><td>bye</td></tr></tbody></table>";
	console.log(passkey);
	$.ajax({
		"url" : "/HUIT/test/getRcordOnPassKey/"+passkey,
		"method" : "GET",
		"contentType" : "application/json",
		// "data" : JSON.stringify(JSONObject),
		"processData" : false,
		"dataType" : "JSON",

		success : function(data) {
			console.log(data);
			testDetails = data.testDto;
			getNotification();

		},
		error : function(d) {
			console.log(d)
		}
	});
	console.log("hey"+testDetails);
	
}
var getNotification=function(){
	var diff="Medium";
	var date=testDetails.dot.split("T");
	if(testDetails.difficulty=="1"){
		diff="Easy";
	}
	if(testDetails.difficulty=="2"){
		diff="Medium";
	}
	if(testDetails.difficulty=="3"){
		diff="Hard";
	}
	
	var message="<a><H3 >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Test Details</H3></a><br /><a><b>College</b></a>&nbsp&nbsp&nbsp&nbsp "+testDetails.college+" <br /><a><b>Date</b></a>&nbsp&nbsp&nbsp&nbsp "+date[0]+" <a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>Difficulty</b></a>&nbsp&nbsp&nbsp&nbsp "+diff+" <br /><a><b>No of Quantitative</b></a>&nbsp&nbsp&nbsp&nbsp "+testDetails.noOfApti+"<a><b> &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbspTime</b></a> &nbsp&nbsp&nbsp&nbsp "+testDetails.time_apti+" min<br /><a><b>No of Verbal</b></a>&nbsp&nbsp&nbsp&nbsp "+testDetails.noOfEng+"<a><b> &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbspTime</b></a> &nbsp&nbsp&nbsp&nbsp "+testDetails.time_eng+" min<br /><a><b>No of Logical</b></a>&nbsp&nbsp&nbsp&nbsp "+testDetails.noOfEng+"<a><b> &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp   Time</b></a> &nbsp&nbsp&nbsp&nbsp "+testDetails.time_lr+" min";
	demo.showNotification('top','center',message);
};
var signOutAdmin=function(){
	localStorage.clear();
	window.location.replace("/HUIT/homepage.html");
}
$(document)
		.ready(
				function() {
					
				
					var response;
					$.ajax({
						"url" : "/HUIT/filter/getCollageList",
						"method" : "GET",
						"contentType" : "application/json",
						// "data" : JSON.stringify(JSONObject),
						"processData" : false,
						"dataType" : "JSON",

						success : function(data) {
							console.log(data);
							response = data.testDto;
							shownotification();
							;

						},
						error : function(d) {
							console.log(d)
						}
					});
					var shownotification = function() {
						var len = response.length;
						noOftest = len;
						var date
						var i;
						for (i = 0; i < len; i++) {
							date = response[i].dot.split("T");
							var col=Math.floor((Math.random() * 4) + 1)
							var color;
							
								color = "alert alert-success";
							

							$('#notificatonSection')
									.append(
											$('<div class="'
													+ color
													+ '"><button type="button" style="font-size:15px" aria-hidden="true" onclick="showDetails(\''+response[i].passKey+'\')"class="close"><b>i</b></button> <span><b> '
													+ response[i].college
													+ ' </b><i>(' + date[0]
													+ ')</i></span> </div>'));
						}
					};
					$.ajax({
						"url" : "/HUIT/count/getstudentcount",
						"method" : "GET",
						"contentType" : "application/json",
						// "data" : JSON.stringify(JSONObject),
						"processData" : false,
						"dataType" : "JSON",

						success : function(data) {
							console.log(data);
							response = data.studentCoutDto;
							$("#selected").text(response.selected_student);
							$("#rejected").text(response.unselected_student);
							;

						},
						error : function(d) {
							console.log(d)
						}
					});
					
					var countres;
					$.ajax({
						"url" : "/HUIT/testcount/getcount",
						"method" : "GET",
						"contentType" : "application/json",
						// "data" : JSON.stringify(JSONObject),
						"processData" : false,
						"dataType" : "JSON",

						success : function(data) {
							console.log(data);
							countres = data.testCountDto;
							updateCounts();
							;

						},
						error : function(d) {
							console.log(d)
						}
					});
					var updateCounts=function(){
						
						$("#no-this-month").text(countres.count_current_month);
						$("#no-next-month").text(countres.count_next_month);
						$("#todaytest").text(countres.count_today);
					}
					

				});
