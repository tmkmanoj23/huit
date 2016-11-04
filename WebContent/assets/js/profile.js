var cityUI;
var stateUI;
function checkph(){
	var phone=localStorage.getItem("ph");
	if(phone==null){
	window.location.replace("/HUIT/homepage.html");
}}
window.onload = checkph;

//window.onload = function(e){ 
//	var phone=localStorage.getItem("ph");
//	if(phone==null){
//	window.location.replace("/HUIT/aptitude.html"); }
//}
function codeAddress() {
            geocoder = new google.maps.Geocoder();
            var address = document.getElementById('pin').value;
            geocoder.geocode({ 'address': address }, function (results, status) {
            	console.log(status);
                if (status == google.maps.GeocoderStatus.OK) {                       
                	
                    for (var component in results[0]['address_components']) {
                        for (var i in results[0]['address_components'][component]['types']) {
                            if (results[0]['address_components'][component]['types'][i] == "administrative_area_level_1") {
                                state = results[0]['address_components'][component]['long_name'];
                                cityUI=results[0]['address_components'][1]['long_name'];
                                stateUI=state;	
//                                alert(results[0]['address_components'][1]['long_name'] + ' , '  + state);
                                document.getElementById('state').value=stateUI;
                                document.getElementById('city').value=cityUI;
                                document.getElementById('country').value="India";
                            }
                        }
                    }                                           
                } else {
                	var modal = document.getElementById('myModal1');
    				var span = document.getElementsByClassName("close1")[0];
    				modal.style.display = "block";
    				
    				span.onclick = function() {
    				    modal.style.display = "none";
    				}
    				$("#modalData").text("Invalid Pincode");
    				document.getElementById('pin').value='';
                	document.getElementById('state').value='';
                    document.getElementById('city').value='';
                    document.getElementById('country').value='';
                }
            });
        }     





function signOut() {
	if(localStorage.getItem("loginType")=="facebook"){
	FB.login(function(response) {
		if (response.status === 'connected') {
			console.log("5");
			
			FB.logout(function(response) {
				window.location.href="login.html";
				console.log("logout1");
			});

		}
	});
//	localStorage.removeItem("loginType");
//	localStorage.removeItem("ph");
	localStorage.clear();

	}
	else if(localStorage.getItem("loginType")=="google"){
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
      });
//      localStorage.removeItem("loginType");
//      localStorage.removeItem("ph");
      localStorage.clear();

      window.location.replace("/HUIT/login.html");
	}
	else{
//		localStorage.removeItem("ph");
		localStorage.clear();

		window.location.replace("/HUIT/login.html");
	}
	
    
}

    function onLoad() {
      gapi.load('auth2', function(){
        gapi.auth2.init();
      });
    }
    
    
    window.fbAsyncInit = function() {
    	FB.init({
    		appId : '1144102812338857',
    		cookie : true, // enable cookies to allow the server to access
    		// the session
    		xfbml : true, // parse social plugins on this page
    		version : 'v2.5' // use graph api version 2.5
    	});

    };


    (function(d, s, id) {
    	var js, fjs = d.getElementsByTagName(s)[0];
    	if (d.getElementById(id))
    		return;
    	js = d.createElement(s);
    	js.id = id;
    	js.src = "//connect.facebook.net/en_US/sdk.js";
    	fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function statusChangeCallback(response) {
    	console.log('1');
    	console.log('statusChangeCallback');
    	console.log(response);
    	if (response.status === 'connected') {
    		console.log("connected");
    	}
    }
var checkDob=function(){
	var dateOfbirth= document.getElementById("dob").value.split('-');
	if(dateOfbirth[0]>1998){
		 document.getElementById("dob").value="";
		var modal = document.getElementById('myModal1');
		var span = document.getElementsByClassName("close1")[0];
		modal.style.display = "block";
		$("#modalData").text("Invalid Date of Birth");
		span.onclick = function() {
		    modal.style.display = "none";
	}
	}
}
 
var phonecheck=function(){
	var phonenum= document.getElementById("phone").value;
	if(phonenum.length!=10){
		 document.getElementById("phone").value="";
			var modal = document.getElementById('myModal1');
			var span = document.getElementsByClassName("close1")[0];
			modal.style.display = "block";
			$("#modalData").text("Invalid Phone number");
			span.onclick = function() {
			    modal.style.display = "none";
	}
}
}
    
$(document).ready(function() {
	$("#updategen").click(function(e){
		var button=document.getElementById('updategen').innerHTML;
		console.log(button);
		if(button=="Edit"){
			document.getElementById("phone").disabled = false;
			document.getElementById("name").disabled = false;
			document.getElementById("dob").disabled = false;
			document.getElementById("pin").disabled = false;
			document.getElementById("address").disabled = false;
			
			$("#updategen").html('Update');
		}
		if(button=="Update"){
			
			var name=document.getElementById("name").value;
			var phone=document.getElementById("phone").value;
			var dob=document.getElementById("dob").value;
			var pin=document.getElementById("pin").value;
			var address=document.getElementById("address").value;
			var city=document.getElementById("city").value;
			var state=document.getElementById("state").value;
			var gender=$("input[name='a']:checked").val();
			var country="India";
			var finalAdd=address+"+"+city+"+"+state+"+"+country+"+"+pin;
			console.log(finalAdd);
			if(address!="" && address!=undefined && pin!="" && pin!=undefined && name!="" && name!=undefined && phone!="" && phone!=undefined && dob!="" && dob!=undefined && gender!="" && gender!=undefined && finalAdd!="" && finalAdd!=undefined){
			document.getElementById("phone").disabled = true;
			document.getElementById("name").disabled = true;
			document.getElementById("dob").disabled = true;
			document.getElementById("pin").disabled = true;
			document.getElementById("address").disabled = true;
			var JSONObject = {
					  "profileDto": {
						  	
						    "name":name ,
						   "address":finalAdd,
						   "dob": dob,
						   "gender":gender,
						    "phone": localStorage.getItem("ph"),
						   "newphone":phone
						   
						  }
						};
				console.log(JSONObject)
				$.ajax({
							"url" : "/HUIT/home/updatecandidateGenInfo",
							"method" : "POST",
							"contentType" : "application/json",
							"data" : JSON.stringify(JSONObject),
							"processData" : false,
							"dataType" : "text",

							success : function(data) {
								console.log(data);
								// document.getElementById("registerResponse").text(data);
								localStorage.setItem("ph", phone);
								window.location.replace("/HUIT/profile1.html");
							},
							error : function(d) {
								console.log(d)
							}
						});
			
			$("#updategen").html('Edit');
			}
			else{
				var modal = document.getElementById('myModal1');
				var span = document.getElementsByClassName("close1")[0];
				modal.style.display = "block";
				$("#modalData").text("Please complete the form and submit");
				span.onclick = function() {
				    modal.style.display = "none";
					
				}
			}
			
		}
		
	});
	$("#okBtn").click(function(e){
		var modal = document.getElementById('myModal1');
		modal.style.display = "none";
	});
		
		$("#updateUG").click(function(e){
			var button=document.getElementById('updateUG').innerHTML;
			console.log(button);
			if(button=="Edit"){
				document.getElementById("collegename").disabled = false;
				document.getElementById("course").disabled = false;
				document.getElementById("specialization").disabled = false;
				document.getElementById("yearUG").disabled = false;
				document.getElementById("percentageUG").disabled = false;
				$("#updateUG").html('Update');
			}
			if(button=="Update"){
			
				var collegename=document.getElementById("collegename").value;
				var specializationUG=document.getElementById("specialization").value;
				var percentageUG=document.getElementById("percentageUG").value.split('.');
				var yearUG=document.getElementById("yearUG").value;
				var courseUG=document.getElementById("course").value;
				if(collegename!="" && collegename!=undefined && course!="" && course!=undefined && specialization!="" && specialization!=undefined && yearUG!="" && yearUG!=undefined && percentageUG[0]!="" && percentageUG[0]!=undefined){
					document.getElementById("collegename").disabled = true;
					document.getElementById("course").disabled = true;
					document.getElementById("specialization").disabled = true;
					document.getElementById("yearUG").disabled = true;
					document.getElementById("percentageUG").disabled = true;
					var JSONObject ={
						  "candidateEduDto": {
							    "collegeUG": document.getElementById("collegename").value,
							    "specializationUG": document.getElementById("specialization").value,
							   "percentageUG":percentageUG[0],
							   "yearUG":document.getElementById("yearUG").value,
							    "phone": localStorage.getItem("ph"),
							    "courseUG": document.getElementById("course").value
							   
							  }
							};
					console.log(JSONObject)
					$.ajax({
								"url" : "/HUIT/home/updtCndtGrdctnInfo",
								"method" : "POST",
								"contentType" : "application/json",
								"data" : JSON.stringify(JSONObject),
								"processData" : false,
								"dataType" : "text",

								success : function(data) {
									console.log(data);
									// document.getElementById("registerResponse").text(data);
									window.location.replace("/HUIT/profile1.html");
								},
								error : function(d) {
									console.log(d)
								}
							});
				
				$("#updateUG").html('Edit');
			}
				else{
					var modal = document.getElementById('myModal1');
					var span = document.getElementsByClassName("close1")[0];
					modal.style.display = "block";
					$("#modalData").text("Please complete the form and submit");
					span.onclick = function() {
					    modal.style.display = "none";}
				}
			}
			
			
		});
		
			$("#update10").click(function(e){
				var button=document.getElementById('update10').innerHTML;
				console.log(button);
				if(button=="Edit"){
					document.getElementById("board10").disabled = false;
					document.getElementById("percentage10").disabled = false;
					document.getElementById("year10").disabled = false;
					$("#update10").html('Update');
				}
				if(button=="Update"){
					
					var board10=document.getElementById("board10").value;
					var percentage10=document.getElementById("percentage10").value.split('.');
					var year10=document.getElementById("year10").value;
					if(board10!="" && board10!=undefined && percentage10[0]!="" && percentage10[0]!=undefined && year10!="" && year10!=undefined){
						document.getElementById("board10").disabled = true;
						document.getElementById("percentage10").disabled = true;
						document.getElementById("year10").disabled = true;
						var JSONObject ={
							  "candidateEduDto": {
								    "boardTenth": document.getElementById("board10").value,
								    "percentageTenth": percentage10[0],
								   "yearTenth":document.getElementById("year10").value,
								    "phone": localStorage.getItem("ph")
								  }
								};
						console.log(JSONObject)
						$.ajax({
									"url" : "/HUIT/home/updtCndtTenthInfo",
									"method" : "POST",
									"contentType" : "application/json",
									"data" : JSON.stringify(JSONObject),
									"processData" : false,
									"dataType" : "text",

									success : function(data) {
										console.log(data);
										// document.getElementById("registerResponse").text(data);
										window.location.replace("/HUIT/profile1.html");
									},
									error : function(d) {
										console.log(d)
									}
								});
					$("#update10").html('Edit');}
					else{
						var modal = document.getElementById('myModal1');
						var span = document.getElementsByClassName("close1")[0];
						modal.style.display = "block";
						$("#modalData").text("Please complete the form and submit");
						span.onclick = function() {
						    modal.style.display = "none";}
					}
				}
				
			});
			
				
				$("#update12").click(function(e){
					var button=document.getElementById('update12').innerHTML;
					console.log(button);
					if(button=="Edit"){
						document.getElementById("board12").disabled = false;
						document.getElementById("percentage12").disabled = false;
						document.getElementById("year12").disabled = false;
						$("#update12").html('Update');
					}
					if(button=="Update"){
						
						var board12=document.getElementById("board12").value;
						var percentage12=document.getElementById("percentage12").value.split('.');
						var year12=document.getElementById("year12").value;
						if(board12!="" && board12!=undefined && percentage12[0]!="" && percentage12[0]!=undefined && year12!="" && year12!=undefined){
							document.getElementById("board12").disabled = true;
							document.getElementById("percentage12").disabled = true;
							document.getElementById("year12").disabled = true;
							var JSONObject ={
								  "candidateEduDto": {
									    "boardTwelve":document.getElementById("board12").value,
									    "percentageTwelve":percentage12[0],
									   "yearTwelve":document.getElementById("year12").value,
									    "phone": localStorage.getItem("ph")
									  }
									};
							console.log(JSONObject)
							$.ajax({
										"url" : "/HUIT/home/updtCndtTwelveInfo",
										"method" : "POST",
										"contentType" : "application/json",
										"data" : JSON.stringify(JSONObject),
										"processData" : false,
										"dataType" : "text",

										success : function(data) {
											console.log(data);
											// document.getElementById("registerResponse").text(data);
											window.location.replace("/HUIT/profile1.html");
										},
										error : function(d) {
											console.log(d)
										}
									});
						$("#update12").html('Edit');}
						else{
							var modal = document.getElementById('myModal1');
							var span = document.getElementsByClassName("close1")[0];
							modal.style.display = "block";
							$("#modalData").text("Please complete the form and submit");
							span.onclick = function() {
							    modal.style.display = "none";}
						}
					}
					
				});
	


var collegeList = [
                   "Indian Institute Of Technology, Madras",
                   "Indian Institute Of Technology, Bombay",
                   "Indian Institute Of Technology, Kharagpur",
                   "Indian Institute Of Technology, Delhi",
                   "Indian Institute Of Technology, Kanpur",
                   "Indian Institute Of Technology, Roorkee",
                   "Indian Institute Of Technology, Hyderabad",
                   "Indian Institute Of Technology, Gandhinagar",
                   "Indian Institute Of Technology, Ropar-Rupnagar",
                   "Indian Institute Of Technology, Patna",
                   "Indian Institute Of Technology, North Guwahati",
                   "National Institute Of Technology, Tiruchirappalli",
                   "Vellore Institute Of Technology",
                   "Indian Institute Of Technology (Banaras Hindu University), Varanasi",
                   "Sardar Vallabhbhai National Institute Of Technology",
                   "Indian Institute Of Technology, Indore",
                   "Birla Institute Of Technology",
                   "Visvesvaraya National Institute Of Technology, Nagpur (Deemed University)-Nagpur",
                   "National Institute Of Technology, Rourkela-Rourkela",
                   "Indian Institute Of Technology, Mandi",
                   "College Of Engineering, Pune",
                   "National Institute Of Technology, Karnataka-Mangalore",
                   "Motilal Nehru National Institute Of Technology",
                   "Psg College Of Technology-Coimbatore",
                   "Indian Institute Of Technology, Jodhpur",
                   "Indian Institute Of Technology, Bhubaneswar",
                   "Thapar University-Patiala",
                   "National Institute Of Technology, Warangal",
                   "Thiagarajar College Of Engineering-Madurai",
                   "National Institute Of Technology, Durgapur",
                   "Amrita School Of Engineering",
                   "Kalinga Institue Of Industrial Technology",
                   "S. Ramaiah Institute Of Technology-Bangalore",
                   "Coimbatore Institute Of Technology-Coimbatore",
                   "V. College Of Engineering-Bengaluru",
                   "National Institute Of Technology, Calicut",
                   "Malaviya National Institute Of Technology, Jaipur",
                   "Pec University Of Technology-Chandigarh",
                   "Manipal Institute Of Technology",
                   "Shanmugha Arts Science Technology & Research Academy (Sastra)",
                   "Jamia Millia Islamia (A Central University)",
                   "B R Ambedkar National Institute Of Technology, Jalandhar",
                   "Indian Institute Of Science Education & Research, Mohali",
                   "Karunya Institute Of Technology And Sciences",
                   "Institute Of Technology, Nirma University",
                   "Kongu Engineering College",
                   "Sona College Of Technology-Salem",
                   "National Institute Of Technology, Kurukshetra",
                   "Pondicherry Engineering College",
                   "Amrita Viswa Vidyapeetham-Amrita Nagar (Po) ,Ettimadai",
                   "National Institute Of Technology, Hamirpur",
                   "National Institute Of Technology, Agartala",
                   "Kumaraguru College Of Technology-Coimbatore",
                   "S. Abdur Rahman Institute Of Science And Technology",
                   "Cochin University Of Science And Technology-Cochin",
                   "Sant Longowal Institute Of Engineering & Technology-Sangrur",
                   "National Institute Of Technology, Meghalaya",
                   "Itm University – Gwalior (School Of Engineering & Technology)-Gwalior",
                   "Koneru Lakshmaiah Education Foundation",
                   "Jaypee Institute Of Information Technology",
                   "Bharati Vidyapeeth Deemed University College Of Engineering-Pune",
                   "Bannari Amman Institute Of Technology-Sathyamangalam",
                   "National Institute Of Technology, Raipur",
                   "Vishwakarma Institute Of Technology-Pune",
                   "National Institute Of Technology, Silchar",
                   "Noorul Islam Centre For Higher Education",
                   "National Institute Of Technology, Srinagar",
                   "University Institute Of Chemical Technology, North Maharashtra University, Jalgaon",
                   "National Institute Of Science & Technology-Berhampur",
                   "Siddaganga Institute Of Technology-Tumkur",
                   "Chaitanya Bharathi Institute Of Technology-Hyderabad",
                   "Hindustan Institute Of Technology And Science (Hits)",
                   "Sagi Ramakrishnam Raju Engineering College-Bhimavaram",
                   "V.Raman College Of Engineering-Bhubaneswar",
                   "Kasegaon Education Societys Rajarambapu Institute Of Technology-Islampur",
                   "National Institute Of Technology, Goa",
                   "Pandit Dwarka Prasad Mishra Indian Institute Of Information Technology, Design And Manufacturing (IIITDM), Jabalpur",
                   "National Institute Of Technology, Jamshedpur",
                   "Institute Of Engineering & Management-Kolkata",
                   "Indian Institute Of Information Technology, Design & Manufacturing (IIITD&M) Kancheepuram-Chennai",
                   "Centurion Institute Of Technology",
                   "College Of Technology And Engineering-Udaipur",
                   "Indian Institute Of Engineering Science And Technology, Shibpur",
                   "Veermata Jijabai Technological Institute",
                   "K. Wagh Institute Of Engineering Education & Research-Nashik",
                   "Shri Ramdeobaba College Of Engineering And Management, Ramdeo Tekdi, Gittikhadan, Katol Road, Nagpur-Nagpur",
                   "National Institute Of Technology, Patna",
                   "Vignan’s Foundation For Science, Technology And Research",
                   "Shri Guru Gobind Singhji Institute Of Engineering And Technology-Nanded",
                   "Sri Ramakrishna Engineering College-Coimbatore",
                   "Bengal Institute Of Technology",
                   "National Institute Of Technology, Delhi",
                   "Yeshwantrao Chavan College Of Engineering-Nagpur",
                   "Adhiyamaan College Of Engineering (Engineering & Technology)",
                   "Maharashtra Academy Of Engineering And Educational Research, Mit College Of Engineering, Pune-Pune",
                   "The National Institute Of Engineering",
                   "Government College Of Engineering, Aurangabad (Academic Autonomous)",
                   "Anand Institute Of Higher Technology",
                   "Noida Institute Of Engineering & Technology",
                   "University Institute Of Chemical Engineering And Technology",
                   "National Institute of Technology, Agartala", 
                   "Motilal Nehru National Institute of Technology, Allahabad", 
                   "Maulana Azad National Institute of Technology, Bhopal", 
                   "National Institute of Technology, Calicut", 
                   "National Institute of Technology, Durgapur", 
                   "National Institute of Technology, Hamirpur", 
                   "Malaviya National Institute of Technology, Jaipur", 
                   "Dr. B. R. Ambedkar National Institute of Technology, Jalandhar", 
                   "National Institute of Technology, Jamshedpur", 
                   "National Institute of Technology, Kurukshetra", 
                   "Visvesvaraya National Institute of Technology, Nagpur", 
                   "National Institute of Technology, Patna", 
                   "National Institute of Technology, Raipur", 
                   "National Institute of Technology, Rourkela", 
                   "National Institute of Technology, Silchar", 
                   "National Institute of Technology, Srinagar", 
                   "S V National Institute of Technology, Surat", 
                   "National Institute of Technology Karnataka, Surathkal", 
                   "National Institute of Technology, Trichy", 
                   "National Institute of Technology, Tadepalligudem", 
                   "National Institute of Technology, Warangal", 
                   "National Institute of Technology, Arunachal Pradesh (Yupia)", 
                   "National Institute of Technology Sikkim", 
                   "National Institute of Technology, Goa", 
                   "National Institute of Technology, Meghalaya", 
                   "National Institute of Technology, Nagaland", 
                   "National Institute of Technology, Manipur", 
                   "National Institute of Technology Mizoram", 
                   "National Institute of Technology Uttarakhand", 
                   "National Institute of Technology, Delhi", 
                   "National Institute of Technology, Pondicherry", 
                   "Other",
                   ];
var courseTags = [
                     "Bachelor Hotel Management and Catering Technology(B.H.M.C.T)",
                     "Bachelor Library Science(B.L.Sc)",
                     "Bachelor of Applied Sciences(B.A.S)",
                     "Bachelor of Architecture(B.Arch)",
                     "Bachelor of Arts Bachelor of Education(B.A. B.Ed)",
                     "Bachelor of Arts Bachelor of Law(B.A.LLB)",
                     "Bachelor of Arts(B.A)",
                     "Bachelor of Audiology and Speech Language Pathology(B.A.S.L.P)",
                     "Bachelor of Ayurvedic Medicine and Surgery(B.A.M.S)",
                     "Bachelor of Business Administration Bachelor of Law(B.B.A LL.B)",
                     "Bachelor of Business Administration(B.B.A)",
                     "Bachelor of Business Management(B.B.M)",
                     "Bachelor of Business Studies(B.B.S)",
                     "Bachelor of Commerce(B.Com)",
                     "Bachelor of Communication Journalism(B.C.J)",
                     "Bachelor of Computer Applications(B.C.A)",
                     "Bachelor of Computer Science(B.C.S)",
                     "Bachelor of Dental Surgery(B.D.S)",
                     "Bachelor of Design(B.Des)",
                     "Bachelor of education in Artificial Intelligence(B.Ed AI)",
                     "Bachelor of Education(B.Ed)",
                     "Bachelor of Electronic Science(B.E.S)",
                     "Bachelor of Elementary Education(B.EL.Ed)",
                     "Bachelor of Engineering(B.E)",
                     "Bachelor of Fashion Technology(B.F.Tech)",
                     "Bachelor of Financial Investment and Analysis(B.F.I.A)",
                     "Bachelor of Fine Arts(B.F.A)",
                     "Bachelor of Fishery Sciences(B.F.S)",
                     "Bachelor of General Law(B.G.L)",
                     "Bachelor of Homeopathic Medicine & Surgery(B.H.M.S)",
                     "Bachelor of Hospitality and Tourism Management(B.H.T.M)",
                     "Bachelor of Hotel Management(B.H.M)",
                     "Bachelor of Information Systems Management(B.I.S.M)",
                     "Bachelor of Labour Management(B.L.M)",
                     "Bachelor of Law(LL.B)",
                     "Bachelor of Laws(B.L)",
                     "Bachelor of Library and Information Science(B.L.I.S)",
                     "Bachelor of Literature(B.Lit)",
                     "Bachelor of Medical Laboratory Technology(B.M.L.T)",
                     "Bachelor of Medical Record Science(B.M.R.Sc)",
                     "Bachelor of Medical Technology(B.M.T)",
                     "Bachelor of Medicine Bachelor of Surgery(M.B.B.S)",
                     "Bachelor of Mental Retardation(B.M.R)",
                     "Bachelor of Naturopathy and Yogic Sciences(B.N.Y.Sc)",
                     "Bachelor of Occupational Therapy(B.O.T)",
                     "Bachelor of Occupational Therapy(B.O.Th)",
                     "Bachelor of Optometry and Vision Science(B.Optom)",
                     "Bachelor of Pharmacy(B.Pharma)",
                     "Bachelor of Physical Education(B.P.E)",
                     "Bachelor Of Physical Education(B.P.Ed)",
                     "Bachelor of Physiotherapy(B.P.T)",
                     "Bachelor of Public Relations(B.P.R)",
                     "Bachelor of Science Bachelor of Education(B.Sc.B.Ed)",
                     "Bachelor of Science Education(B.S.E)",
                     "Bachelor of Science in Education(B.Sc.Ed)",
                     "Bachelor of Science(B.S)",
                     "Bachelor of Siddha Medical Sciences(B.S.M.S)",
                     "Bachelor Of Social Work(B.S.W)",
                     "Bachelor of Socio Legal Sciences Bachelor of Laws(B.S.L.LL.B)",
                     "Bachelor of Speech Language & Audiology(B.S.L.A)",
                     "Bachelor of Tourism Administration(B.T.A)",
                     "Bachelor of Unani Medicine & Surgery(B.U.M.S)",
                     "Bachelor of Unani Medicine & Surgery(Kamil e Tob o Jarahat)",
                     "Bachelor of Veterinary Science(B.V.Sc)",
                     "Bachelors of Technology(B.Tech)",
                     "Basic Training Certificate(B.T.C)",
                     "Behavioral Healthcare Education(B.H.Ed)",
                     "Under Graduate Basic Training(U.G.B.T)",
                     "Under Graduate Teacher Training(U.G.T.T)",
                     "Under Graduate Training(U.G.T)",
                                        ];
                   $( "#collegename" ).autocomplete({
                     source: collegeList
                   });
$("#collegename").autocomplete({
	source: collegeList
});


$("#course").autocomplete({
	source: courseTags
});

	var response;
	var  display1 =function(){
		openModal();
	$.ajax({
		"url" : "/HUIT/home/viewprofile/"+localStorage.getItem('ph'),
		"method" : "GET",
		"contentType" : "application/json",
//		"data" : JSON.stringify(JSONObject),
		"processData" : false,
		"dataType" : "JSON",

		success : function(data) {
			console.log(data);
			response=data;
			edit();

		},
		error : function(d) {
			console.log(d)
		}
	})};
	display1();
	var  edit =function(){
		localStorage.setItem("nameuser",response.profileDto.name);
		$("#username").html(response.profileDto.name);
		if(response.profileDto.name!=null && response.profileDto.name!="undefined" && response.profileDto.name!=0)
		document.getElementById("name").value=response.profileDto.name;
		
		if(response.profileDto.email!=null && response.profileDto.email!="undefined" && response.profileDto.email!=0)
		document.getElementById("email").value=response.profileDto.email;
		
		if(response.profileDto.dob!=null && response.profileDto.dob!=undefined && response.profileDto.dob!=0){
		var date=response.profileDto.dob.split("T");
		console.log(date[0]);
		document.getElementById("dob").value=date[0];
		}
		
		if(response.profileDto.address!=null && response.profileDto.address!="undefined" && response.profileDto.address!=0){
			var addressData=response.profileDto.address.split("+");
		document.getElementById("address").value=addressData[0];
		document.getElementById("city").value=addressData[1];
		document.getElementById("state").value=addressData[2];
		document.getElementById("country").value=addressData[3];
		document.getElementById("pin").value=addressData[4];
		}
		
		console.log(localStorage.getItem('ph'));
		if(localStorage.getItem('ph').length<11){
		document.getElementById("phone").value=localStorage.getItem('ph');
		}
		if(response.profileDto.gender=="male")
			{
			$('input:radio[name=a][value=male]').click();
			}
		else if(response.profileDto.gender=="female 	"){
			$('input:radio[name=a][value=female]').click();
		}
		else{
			document.getElementById("male").checked = false;
			document.getElementById("female").checked = false;

		}
		
		if(response.profileDto.collegeUG!=null && response.profileDto.collegeUG!="undefined" && response.profileDto.collegeUG!=0)
		document.getElementById("collegename").value=response.profileDto.collegeUG;
		
		if(response.profileDto.yearUG!=null && response.profileDto.yearUG!="undefined" && response.profileDto.yearUG!=0)
		document.getElementById("yearUG").value=response.profileDto.yearUG;
		
		if(response.profileDto.percentageUG!=null && response.profileDto.percentageUG!="undefined" && response.profileDto.percentageUG!=0)
		document.getElementById("percentageUG").value=response.profileDto.percentageUG;
		
		if(response.profileDto.yearTwelve!=null && response.profileDto.yearTwelve!="undefined" && response.profileDto.yearTwelve!=0)
		document.getElementById("year12").value=response.profileDto.yearTwelve;
		if(response.profileDto.percentageTwelve!=null && response.profileDto.percentageTwelve!="undefined" && response.profileDto.percentageTwelve!=0)
		document.getElementById("percentage12").value=response.profileDto.percentageTwelve;
		if(response.profileDto.yearTenth!=null && response.profileDto.yearTenth!="undefined" && response.profileDto.yearTenth!=0)
		document.getElementById("year10").value=response.profileDto.yearTenth;
		if(response.profileDto.percentageTenth!=null && response.profileDto.percentageTenth!="undefined" && response.profileDto.percentageTenth!=0)
		document.getElementById("percentage10").value=response.profileDto.percentageTenth;
		
		if(response.profileDto.boardTenth!=null && response.profileDto.boardTenth!="undefined" && response.profileDto.boardTenth!=0)
			document.getElementById("board10").value=response.profileDto.boardTenth;
		
		if(response.profileDto.boardTwelve!=null && response.profileDto.boardTwelve!="undefined" && response.profileDto.boardTwelve!=0)
			document.getElementById("board12").value=response.profileDto.boardTwelve;
		if(response.profileDto.specializationUG!=null && response.profileDto.specializationUG!="undefined" && response.profileDto.specializationUG!=0)
			document.getElementById("specialization").value=response.profileDto.specializationUG;
		if(response.profileDto.courseUG!=null && response.profileDto.courseUG!="undefined" && response.profileDto.courseUG!=0)
			document.getElementById("course").value=response.profileDto.courseUG;
		closeModal();
	};
	
});
