var testrecord;
var ReconfigurePass;

var cancelBtn=function(){
	var modal = document.getElementById('myModalReconfigure');
	modal.style.display = "none";
};
var saveTest=function(){
	
	var noEng=document.getElementById("noEng").value;
	var noQuant=document.getElementById("noQuant").value;
	var noLogical=document.getElementById("noLogical").value;
	var timeEng=document.getElementById("timeEng").value;
	var timeQuant=document.getElementById("timeQuant").value;
	var timeLogical=document.getElementById("timeLogical").value;
	var dateTest=document.getElementById("date").value;
	var diff=$('#difficulty').val();
	
	var jsonUpdate={
			  "testDto": 
			    {
			      "passKey":ReconfigurePass,
			      "noOfApti":noQuant,
			      "noOfEng":noEng,
			      "noOfLR":noLogical,
			      "time_apti":timeQuant,
			      "time_eng":timeEng,
			      "time_lr":timeLogical,
			      "difficulty":diff,
			    	  "dot":dateTest
			    	  
			    }
			  
			}
	console.log(jsonUpdate);
	$.ajax({
		"url" : "/HUIT/test/updateTest",
		"method" : "POST",
		"contentType" : "application/json",
		"data" : JSON.stringify(jsonUpdate),
		"processData" : false,
		"dataType" : "text",

		success : function(data) {
			console.log(data);
			var modal = document.getElementById('myModalReconfigure');
			modal.style.display = "none";
		},
		error : function(d) {
			console.log(d)
			alert(data);
		}
	});

}
var fillModal=function(){
	ReconfigurePass=testrecord.passKey;
	var modal = document.getElementById('myModalReconfigure');
	var span = document.getElementsByClassName("close1")[0];
	modal.style.display = "block";
	
	document.getElementById("noEng").value=testrecord.noOfEng;
	document.getElementById("noQuant").value=testrecord.noOfApti;
	document.getElementById("noLogical").value=testrecord.noOfLR;
	document.getElementById("timeEng").value=testrecord.time_eng;
	document.getElementById("timeQuant").value=testrecord.time_apti;
	document.getElementById("timeLogical").value=testrecord.time_lr;
	var dateTemp=testrecord.dot.split('T');
	
	document.getElementById("date").value=dateTemp[0];
	$('#difficulty').val(testrecord.difficulty);
	span.onclick = function() {
	    modal.style.display = "none";}
}
var setConfig=function(pass){
	console.log(pass);
	
	
	$.ajax({
		"url" : "/HUIT/test/getRcordOnPassKey/"+pass,
		"method" : "GET",
		"contentType" : "application/json",
		"processData" : false,
		"dataType" : "JSON",

		success : function(data) {
			testrecord = data.testDto;
			fillModal();
		},
		error : function(d) {
			console.log(d)
		}
	});
	
};
var shortlistresponse;
var showstudentlist=function(passkey){
	console.log(passkey);
	$.ajax({
		"url" : "/HUIT/list/getshortlisted/"+passkey,
		"method" : "GET",
		"contentType" : "application/json",
		"processData" : false,
		"dataType" : "JSON",

		success : function(data) {
			console.log(data);
			shortlistresponse = data.studentExamDetailsDto;
			showlist();

		},
		error : function(d) {
			console.log(d)
		}
	});
	
}
var showlist=function(){
	var len = shortlistresponse.length;
	var i;
	var passkey;
	var modal = document.getElementById('myModalShorlisted');
	var span = document.getElementsByClassName("close1")[0];
	modal.style.display = "block";
	$("#studentListTbl").html('');
	for (i = 0; i < len; i++) {
		var name=shortlistresponse[i].name;
		var score_apti=shortlistresponse[i].score_apti;
		var score_eng=shortlistresponse[i].score_eng;
		var score_lr=shortlistresponse[i].score_lr;
		var total_score=shortlistresponse[i].total_score;
		
		$("#studentListTbl")
				.append('<tr><td style="text-align:center">'+(i+1)+'</td><td style="text-align:center">'+name+'</td><td style="text-align:center">'+score_eng+'</td><td style="text-align:center">'+score_apti+'</td><td style="text-align:center">'+score_lr+'</td><td style="text-align:center">'+total_score+'</td><td></td></tr>');
		}
	}
var closeList=function(){
	var modal = document.getElementById('myModalShorlisted');
	modal.style.display = "none";
}

var setAns = function(passs) {
	console.log(passs);
	localStorage.setItem("passkey", passs);
	var message="<b><i>hvdavghd jadagshdvagshd ghdvashgd agshd agshd ghasd gashdv agshd agshd agshdv agshdv agshd agsh"+passs+"</i></b>";
	demo.showNotification('top','center',message);
	window.location.replace("/HUIT/evaluate.html");
};
var signOutAdmin=function(){
	localStorage.clear();
	window.location.replace("/HUIT/homepage.html");
}


var response;
var responseUpcoming;
$(document)
		.ready(
				function() {
				
					$.ajax({
						"url" : "/HUIT/testlist/getcompletedtest",
						"method" : "GET",
						"contentType" : "application/json",
						"processData" : false,
						"dataType" : "JSON",

						success : function(data) {
							console.log(data);
							response = data.testDto;
							showcompletedlist();

						},
						error : function(d) {
							console.log(d)
						}
					});
					$.ajax({
						"url" : "/HUIT/testlist/getupcomingtest/",
						"method" : "GET",
						"contentType" : "application/json",
						"processData" : false,
						"dataType" : "JSON",

						success : function(data) {
							console.log(data);
							responseUpcoming = data.testDto;
							showupcominglist();

						},
						error : function(d) {
							console.log(d)
						}
					});


					var showcompletedlist = function() {
						var len = response.length;
						var i;
						var passkey;
						for (i = 0; i < len; i++) {
							var passkey = response[i].passKey;
							var college = response[i].college;
							var batch = response[i].batch;
							var date = response[i].dot.split("T");
							var temp = date[0].split("-");
							var dot = date[0].split("-").reverse().join("/");
							var testId = passkey + batch + temp[2];
							if(response[i].status=="1"){
							$("#evaluateTestTbl")
									.append(
											'<tr><td>'
													+ testId
													+ '</td><td>'
													+ college
													+ '</td><td>'
													+ passkey
													+ '</td><td>'
													+ batch
													+ '</td><td>'
													+ dot
													+ '</td><td><button onclick="setAns(\''
													+ passkey
													+ '\');" class="btn btn-danger btn-fill evaluate">&nbsp&nbspEvaluate Test&nbsp&nbsp</button></td></tr>');
							}
							else if(response[i].status=="2"){
								$("#evaluateTestTbl")
								.append(
										'<tr><td>'
												+ testId
												+ '</td><td>'
												+ college
												+ '</td><td>'
												+ passkey
												+ '</td><td>'
												+ batch
												+ '</td><td>'
												+ dot
												+ '</td><td><button onclick="showstudentlist(\''
												+ passkey
												+ '\');" class="btn btn-success btn-fill evaluate">&nbspView Shorlisted</button></td></tr>');
							}
						}
					};
					var showupcominglist = function() {
						var len1 = responseUpcoming.length;
						var j;
						var passkey1;
						for (j = 0; j < len1; j++) {
							var passkey = responseUpcoming[j].passKey;
							var college = responseUpcoming[j].college;
							var batch = responseUpcoming[j].batch;
							var date = responseUpcoming[j].dot.split("T");
							var temp = date[0].split("-");
							var dot = date[0].split("-").reverse().join("/");
							var testId = passkey + batch + temp[2];
							$("#upcomingTestTbl")
									.append(
											'<tr><td>'
													+ testId
													+ '</td><td>'
													+ college
													+ '</td><td>'
													+ passkey
													+ '</td><td>'
													+ batch
													+ '</td><td>'
													+ dot
													+ '</td><td><button onclick="setConfig(\''
													+ passkey
													+ '\');" class="btn btn-info btn-fill reconfigure">Reconfigure Test</button></td></tr>');
						
						}
					};

				});
