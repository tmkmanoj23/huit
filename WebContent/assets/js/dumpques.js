var signOutAdmin=function(){
	localStorage.clear();
	window.location.replace("/HUIT/homepage.html");
}




$(document).ready(function(){
	$("#downloadTemplate").click(function(){
		 var base64 = "cXVlc3Rpb24sb3B0aW9uMSxvcHRpb24yLG9wdGlvbjMsb3B0aW9uNCxjb3JyZWN0LGNhdGVnb3J5LGRpZmZpY3VsdHkNCg==";
	     var fileName = "Template.csv";
	     var type = "data:application/vnd.ms-excel;base64 ";    
	     var link = document.createElement("a");
	     var uri = type + "," + escape(base64);
	     link.href = uri;
	     link.style = "visibility:hidden";
	     link.download = fileName;
	     document.body.appendChild(link);
	     link.click();
	     document.body.removeChild(link);
	});
	var json;
	$("#saveQuestions").click(function(){	
		var fU= document.getElementById("uploadFile");

		// Create a File Reader object
		var reader = new FileReader();
		var t = this;

		reader.onload = function(e) {
		   var strCSV = e.target.result;
		   var arrCSV = strCSV.split( "\n" );
		   // To ignore the first row which is header
		   var hdrRow = arrCSV.splice(0, 1);

		   var questionsDto = [];
		   for(var i = 0 ; i < arrCSV.length ;i++ ){
		   	var tempValue = arrCSV[i].split(',');
		   	if(tempValue[0]!= undefined && tempValue[1]!= undefined && tempValue[2]!= undefined && tempValue[3]!= undefined && tempValue[4]!= undefined && tempValue[5]!= undefined && tempValue[6]!= undefined && tempValue[7]!= undefined)
		   		questionsDto.push({"question" : tempValue[0],"option1" : tempValue[1],"option2" : tempValue[2],"option3" : tempValue[3],"option4" : tempValue[4],"correct" : tempValue[5],"category" : tempValue[6],"difficulty" : tempValue[7]});
		   		
		   }
		   
		   json={"questionsDto":questionsDto};
		   console.log(json);
		   $.ajax({
				"url" : "/HUIT/questions/createMultipleQuestion",
				"method" : "POST",
				"contentType" : "application/json",
				"data" : JSON.stringify(json),
				"processData" : false,
				"dataType" : "text",

				success : function(data) {
					console.log(data);
					if (data == "Question List Submitted Successfully") {
						var modal = document.getElementById('myModal1');
						var span = document.getElementsByClassName("close1")[0];
						modal.style.display = "block";
						span.onclick = function() {
						    modal.style.display = "none";}
					} else {
						alert("network error");
					}

				},
				error : function(d) {
					console.log(d)
				}
			});
		}
		reader.readAsText(fU.files[0]);
		  
	});
	
	$("#okBtn").click(function(e){
		var modal = document.getElementById('myModal1');
		modal.style.display = "none";
		window.location.replace("/HUIT/dumpques.html");
	});
	$("#createQues").click(function(){
		var category=$("input[name='a']:checked").val();
		var difficulty=$("input[name='a1']:checked").val();
		var ques=document.getElementById("question").value;
		var option1=document.getElementById("optionA").value;
		var option2=document.getElementById("optionB").value;
		var option3=document.getElementById("optionC").value;
		var option4=document.getElementById("optionD").value;
		var correct=$("input[name='c[1][]']:checked").val();
		var jsona={
				  "questionsDto": 
				    {
				      "option1": option1,
				      "option2": option2,
				      "option3": option3,
				      "option4": option4,
				      "question": ques,
				      "correct":correct,
				      "category":category,
				      "difficulty":difficulty
				    }
				};
		$.ajax({
			"url" : "/HUIT/questions/createQuestion",
			"method" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify(jsona),
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log(data);
				if (data == "Question Submitted Successfully") {
					var modal = document.getElementById('myModal1');
					var span = document.getElementsByClassName("close1")[0];
					modal.style.display = "block";
					span.onclick = function() {
					    modal.style.display = "none";}

				} else {
					alert("network error");
				}

			},
			error : function(d) {
				console.log(d)
			}
		});
	});
});


