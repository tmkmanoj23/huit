var response;
var send=function(){
	var phList=[];
	var phvar;
	for(var j=0;j<response.length;j++){
		phvar={
				"phone":response.phone
		}
		phList.push(phvar);
	}
	
	var jsonsend={
		    "confirmationDto" : 
	        {
	        	"message": document.getElementById("mailMessage").value,
	            "phlist":phList
	        }
	};
	$.ajax({
		"url" : "/HUIT/select/students",
		"method" : "POST",
		"contentType" : "application/json",
		"data" : JSON.stringify(jsonsend),
		"processData" : false,
		"dataType" : "text",

		success : function(data) {
			console.log(data);
			if(data="mail has been sent to selected student"){
				alert("success");
			}
			else{
				alert(data);
			}
		},
		error : function(d) {
			console.log(d)
		}
	});
	
}
$(document).ready(function(){
	$("#sendConfirmation").click(function(){
		var modal = document.getElementById('confirmSelection');
		var span = document.getElementsByClassName("close1")[0];
		modal.style.display = "block";
	});
	$("#sendconfirmMail").click(function(){
		openModal();
		console.log(response);
		var phList=[];
		var phvar;
		for(var j=0;j<response.length;j++){
			phvar={
					"phone":response[j].phone
			}
			phList.push(phvar);
		}
		
		var jsonsend={
			    "confirmationDto" : 
		        {
		        	"message": document.getElementById("mailMessage").value,
		            "phlist":phList
		        }
		};
		console.log(jsonsend);
		$.ajax({
			"url" : "/HUIT/select/students",
			"method" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify(jsonsend),
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log(data);
				if(data="mail has been sent to selected student"){
					closeModal();
					var modal1 = document.getElementById('confirmSelection');
					modal1.style.display = "none";
					var modal = document.getElementById('myModal1');
					var span = document.getElementsByClassName("close1")[0];
					modal.style.display = "block";
					span.onclick = function() {
					    modal.style.display = "none";}
				}
				else{
					alert(data);
				}
			},
			error : function(d) {
				console.log(d)
			}
		});
	});
	$("#okBtn").click(function(e){
		var modal = document.getElementById('myModal1');
		modal.style.display = "none";
		window.location.replace("/HUIT/viewtest.html");
	});
	$("#filterBtn").click(function(){
		var total;
		var studentcutof;
		if(document.getElementById("totalcutoff").value=="" ||document.getElementById("totalcutoff").value==null ||document.getElementById("totalcutoff").value==undefined){
			total=0;
		}
		else{
			total=document.getElementById("totalcutoff").value;
		}
		if(document.getElementById("studentcutoff").value=="" ||document.getElementById("studentcutoff").value==null ||document.getElementById("studentcutoff").value==undefined){
			studentcutof=0;
		}
		else{
			studentcutof=document.getElementById("studentcutoff").value;
		}
			
		
		var JSONObject = {
				 "filterDto": 
				   {
				     "passkey":localStorage.getItem("passkey"),
				     "cutoff_eng":document.getElementById("verbalcutoff").value,
				     "cutoff_lr":document.getElementById("logicalcutoff").value,
				     "cutoff_apti":document.getElementById("quantcutoff").value,
				     "cutoff_total":total,
				     "studenttotal":studentcutof
				   }

				}
		console.log(JSONObject);
		$.ajax({
			"url" : "/HUIT/filter/filterstudent",
			"method" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify(JSONObject),
			"processData" : false,
			"dataType" : "JSON",

			success : function(data) {
				console.log(data);
				response=data.studentExamDetailsDto;
				$("#studentTbl> tbody").html("");
				showstudentlist();
			},
			error : function(d) {
				console.log(d)
			}
		});

	});
	
	var updateList = function(){
	$.ajax({
		"url" : "/HUIT/list/studentlist/"+localStorage.getItem("passkey"),
		"method" : "GET",
		"contentType" : "application/json",
		"processData" : false,
		"dataType" : "JSON",

		success : function(data) {
			console.log(data);
			response = data.studentExamDetailsDto;
			showstudentlist();

		},
		error : function(d) {
			console.log(d)
		}
	});}
	var showstudentlist = function() {
		var len = response.length;
		var i;
		var passkey;
		for (i = 0; i < len; i++) {
			var name=response[i].name;
			var score_apti=response[i].score_apti;
			var score_eng=response[i].score_eng;
			var score_lr=response[i].score_lr;
			var total_score=response[i].total_score;
			
			$("#studentListtbl")
					.append('<tr><td style="text-align:center">'+(i+1)+'</td><td style="text-align:center">'+name+'</td><td style="text-align:center">'+score_eng+'</td><td style="text-align:center">'+score_apti+'</td><td style="text-align:center">'+score_lr+'</td><td style="text-align:center">'+total_score+'</td><td></td></tr>');
			}
		}
	
	updateList();
	
	
});