var red;
var green;
var blue;
var yellow;
var attempted=[];
var notattempted=[];
var reviewArray=[];
var greenColor="#43b743";
var yellowColor="#ffff00";
var blueColor="42b1f3";
var qIDArray=[];
var j;


function submitLr(){
//	
	var ansIDList=[];
	var ans;
	console.log(qIDArray.length)
	var score=0;;
	var noOfQuesE=qIDArray.length;
	for(j=0;j<qIDArray.length;j++){
		console.log(valueAns+"  "+response[j].correct);
		var valueAns= $("input[name="+qIDArray[j]+"]:checked").val();
		if(valueAns==response[j].correct.trim()){
			score++;
		}
		
		
		}
	localStorage.setItem("marksLr",score);
	localStorage.setItem("quesLr",noOfQuesE);
	localStorage.setItem("demotest","lr");
	window.location.replace("/HUIT/Result.html");
	}
	
function startTimer(duration, display) {
    var start = Date.now(),
        diff,
        minutes,
        seconds;
    function timer() {
        // get the number of seconds that have elapsed since 
        // startTimer() was called
        diff = duration - (((Date.now() - start) / 1000) | 0);

        // does the same job as parseInt truncates the float
        minutes = (diff / 60) | 0;
        seconds = (diff % 60) | 0;

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        if(minutes==1)
        {
          document.getElementById('hurryup').style.display='block';
        }
        if(minutes==0 && seconds==0)
        {
          alert("Time is up");
          setTimeout(submitEng(), 1000);
        }

        display.textContent = minutes + " min: " + seconds + "  sec"; 

        if (diff <= 0) {
            // add one second so that the count down starts at the full duration
            // example 05:00 not 04:59
            start = Date.now() + 1000;
        }
    };
    // we don't want to wait a full second before the timer starts
    timer();
    setInterval(timer, 1000);
}


window.onload = function () {
	var phone=localStorage.getItem("ph");
	if(phone==null){
	window.location.replace("/HUIT/homepage.html");}
	var testTime= localStorage.getItem("timeEng");
	console.log(testTime);
    var totalMinutes = 60 * 10;
        display = document.querySelector('#time');
    startTimer(totalMinutes, display);
    
};



var getcurrent=function(data){
	var prim = "#primary" + data;
	var el=document.getElementById(prim);
	 $('html, body').animate({
         scrollTop: $(prim).offset().top - 85
   }, 300); 
//	$(prim)[0].scrollIntoView(true);
	
}
var showAttempted=function(){
	showAll();
	console.log(qIDArray);
	for(j=0;j<notattempted.length;j++){
		var dataT=notattempted[j];
		var prim = 'primary' + dataT;
		var el=document.getElementById(prim);
		el.style.display = 'none';
		// 
		
	}
}
var showAll=function(){
	for(j=0;j<qIDArray.length;j++){
		var dataT=qIDArray[j];
		var prim = 'primary' + dataT;
		var el=document.getElementById(prim);
		el.style.display = 'block';
		// 
		
	}
}
var showNotAttempted=function(){
	showAll();
	for(j=0;j<qIDArray.length;j++){
		
		if(notattempted.indexOf(qIDArray[j]) == -1){
			console.log(qIDArray[j]);
			var dataT=qIDArray[j];
			var prim = 'primary' + dataT;
			var el=document.getElementById(prim);
			el.style.display = 'none';
		}
		// 
		
	}
}
var showmarkReview=function(){
	showAll();
for(j=0;j<qIDArray.length;j++){
		if(reviewArray.indexOf(qIDArray[j]) == -1){
			console.log(qIDArray[j]);
			var dataT=qIDArray[j];
			var prim = 'primary' + dataT;
			var el=document.getElementById(prim);
			el.style.display = 'none';
		}
	}
}
var review=function(data){
	
	var checkbox='review'+data;
	var checkstatus=document.getElementById(checkbox).checked;
	if(checkstatus){
		yellow++;
		var elem = document.getElementById(data);
		elem.style.backgroundColor = yellowColor;
		elem.style.color = '#000000';
		var tileChange = 'queslist' + data;
		var elemTile = document.getElementById(tileChange);
		elemTile.style.backgroundColor = yellowColor;
		elemTile.style.color = '#000000';
		if(reviewArray.indexOf(data) == -1){
			reviewArray.push(data);
			
		}
	}
	else{
		yellow--;
		reviewArray.splice(reviewArray.indexOf(data),1);
		if(attempted.indexOf(data) == -1){
		var elem = document.getElementById(data);
		elem.style.backgroundColor = blueColor;
		var tileChange = 'queslist' + data;
		var elemTile = document.getElementById(tileChange);
		elemTile.style.backgroundColor = blueColor;
		elemTile.style.color = '#000000';
		}
		else{
			var elem = document.getElementById(data);
			elem.style.backgroundColor = greenColor;
			var tileChange = 'queslist' + data;
			var elemTile = document.getElementById(tileChange);
			elemTile.style.backgroundColor = greenColor;
			elemTile.style.color = '#000000';
		}
	}
	
	

	document.getElementById("markreview").innerHTML = yellow;
	document.getElementById("attempted").innerHTML = green;
	document.getElementById("notattempted").innerHTML = blue;
	console.log(reviewArray);
}


var setAns = function(data) {
//	console.log(data);
	var checkbox='review'+data;
	var checkstatus=document.getElementById(checkbox).checked;
	if(checkstatus){
		
	}
	else{
	var elem = document.getElementById(data);
	elem.style.backgroundColor = '#43b743';
	var tileChange = 'queslist' + data;
	var elemTile = document.getElementById(tileChange);
	elemTile.style.backgroundColor = '#43b743';
	elemTile.style.color = '#FFFFFF';
	}
	if(attempted.indexOf(data) == -1){
		notattempted.splice(notattempted.indexOf(data),1);
		attempted.push(data);
		green++;
		blue--;
	}
	
	document.getElementById("attempted").innerHTML = green;
	document.getElementById("notattempted").innerHTML = blue;

};

var noOfQues;

var send = {
	"answerKeyDto" : {
		"phone" : "9831579896",
		"ansId" : [ {
			"qID" : 1,
			"answer" : "A"
		}, {
			"qID" : 2,
			"answer" : "A"
		} ]
	}
};

function clearResponse(data) {
	var clearAr = 'clear' + data + 'A';
	var clearBr = 'clear' + data + 'B';
	var clearCr = 'clear' + data + 'C';
	var clearDr = 'clear' + data + 'D';
	var primary = 'primary' + data;
//	console.log(data);
	document.getElementById(clearAr).checked = false;
	document.getElementById(clearBr).checked = false;
	document.getElementById(clearCr).checked = false;
	document.getElementById(clearDr).checked = false;
	
	
	if(notattempted.indexOf(data) == -1){
		attempted.splice(attempted.indexOf(data),1);
		notattempted.push(data);
		green--;
		blue++;
	}
	if(reviewArray.indexOf(data)!= -1){
		var tileChange = 'queslist' + data;
		var elemTile = document.getElementById(tileChange);
		elemTile.style.backgroundColor = yellowColor;
		elemTile.style.color = '#000000';
		var elem = document.getElementById(data);
		elem.style.backgroundColor = yellowColor;
		
	}
	else{
		var tileChange = 'queslist' + data;
		var elemTile = document.getElementById(tileChange);
		elemTile.style.backgroundColor = '#FFFFFF';
		elemTile.style.color = '#000000';
		var elem = document.getElementById(data);
		elem.style.backgroundColor = '#42b1f3';
	}
	
	document.getElementById("attempted").innerHTML = green;
	document.getElementById("notattempted").innerHTML = blue;
	
	
}
var response;
$(document)
		.ready(
				function() {
//					window.onload=checkph;
//
//					function checkph(){
//						var phone=localStorage.getItem("ph");
//						if(phone==null){
//						window.location.replace("/HUIT/homepage.html");
//					}}

//					console.log(jsona);
					
					var passkey = localStorage.getItem('passkey');
					var ph = localStorage.getItem('ph');
//					console.log(passkey);
//					console.log(ph);
					$
							.ajax({
								"url" : "/HUIT/questions/getLrQuesDemo",
								"method" : "GET",
								"contentType" : "application/json",
								// "data" : JSON.stringify(JSONObject),
								"processData" : false,
								"dataType" : "JSON",

								success : function(data) {
//									console.log(data);
									response = data.questionsDto;
									showquestion();

								},
								error : function(d) {
//									console.log(d)
								}
							});
					var showquestion = function() {
//						console.log(response);
//						console.log(response[0].question);
						var len = response.length;
						document.getElementById("attempted").innerHTML = "00";
						red=0; 
						green=0;
						blue=len;
						yellow=0;
						document.getElementById("notattempted").innerHTML = len;
						document.getElementById("markreview").innerHTML = "00";
						document.getElementById("none").innerHTML = "00";
						noOfQues = len;
						var i;
						for (i = 0; i < len; i++) {
							qIDArray.push(response[i].qID);
							var clearA = 'clear' + response[i].qID + 'A';
							var clearB = 'clear' + response[i].qID + 'B';
							var clearC = 'clear' + response[i].qID + 'C';
							var clearD = 'clear' + response[i].qID + 'D';
							var primary = 'primary' + response[i].qID;
							var queslist1 = 'queslist' + response[i].qID;
							notattempted.push(response[i].qID);
							$('#ques')
									.append(
											$('<div id="'
													+ primary
													+ '" class="panel panel-primary"><div id="'
													+ response[i].qID
													+ '" class="panel-heading"><h3 class="panel-title">Question-'
													+ (i + 1)
													+ '</h3></div><div class="panel-body">'
													+ response[i].question
													+ '<div class="ans" onclick="setAns('
													+ response[i].qID
													+ ')"><input type="radio" name='
													+ response[i].qID
													+ ' id='
													+ clearA
													+ ' value="A">'
													+ response[i].option1
													+ '</input><br><input type="radio" name='
													+ response[i].qID
													+ ' id='
													+ clearB
													+ ' value="B">'
													+ response[i].option2
													+ '</input><br><input type="radio" name='
													+ response[i].qID
													+ ' id='
													+ clearC
													+ ' value="C">'
													+ response[i].option3
													+ '</input><br><input type="radio" name='
													+ response[i].qID
													+ ' id='
													+ clearD
													+ ' value="D">'
													+ response[i].option4
													+ '</input><br></div></div><div class="panel-footer" style="padding:10px"><ul><div style="width: 30%;float: right;margin-top: 1.5%;margin-right: 4"><input onclick="review('+response[i].qID+');" type="checkbox" id="review'
													+ response[i].qID
													+ '">Mark for review</div><button class="btn btn-warning" style="margin-left: 83%;margin-top: -3%;" onclick="return clearResponse('
													+ response[i].qID
													+ ')">Clear Response</button></ul></div></div>'));
							$('#queslist').append(
									'<a onclick="getcurrent('+response[i].qID+');" class="btn btn-default" id="'
											+ queslist1 + '">' + (i + 1)
											+ '</a>');
						}
						console.log(qIDArray);
					}
					
				});
