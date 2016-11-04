

var toggle;

window.onload = function(){
	var phone=localStorage.getItem("ph");
	if(phone!=null){
	window.location.replace("/HUIT/profile1.html");
	}
	
};

function checktoggle(){   //check admin or student
	if(toggle=="true"){
		toggle="false";
		$("#status").hide();
		$("#createAccount").hide();
	}
	else if(toggle=="false"){
		$("#status").show();
		$("#createAccount").show();
		toggle="true";
	}
	console.log(toggle);
}
function emailvalidation() {

	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	var uname = document.getElementById("userid").value;

	console.log(emailPattern.test(uname));

	if (!emailPattern.test(uname)) {
		document.getElementById("emailerror").style.visibility = "visible"
		document.getElementById("userid").style.borderColor = "red";
	}

}

function changespanemail() {
	if (document.getElementById("emailerror").style.visibility == "visible") {
		document.getElementById("emailerror").style.visibility = "hidden"
		document.getElementById("userid").style.borderColor = "gray";
	}

}

function changespanpass() {
	if (document.getElementById("passworderror").style.visibility == "visible") {
		document.getElementById("passworderror").style.visibility = "hidden"
		document.getElementById("password").style.borderColor = "gray";
	}

}

function passwordcheck() {
	var pass = null;
	var uname = document.getElementById("password").value;
	if (uname == null || uname == "") {
		document.getElementById("passworderror").style.visibility = "visible"
		document.getElementById("password").style.borderColor = "red";
	}

}

function onSignIn(googleUser) {   //Sign in with google
	if(toggle=="true"){
	localStorage.setItem("loginType","google");
	var profile = googleUser.getBasicProfile();
	console.log('ID: ' + profile.getId()); // Do not send to your backend! Use
											// an ID token instead.
	console.log('Name: ' + profile.getName());
	console.log('Image URL: ' + profile.getImageUrl());
	console.log('Email: ' + profile.getEmail());
	document.getElementById('status').innerHTML = 'Thanks for logging in '
			+ profile.getEmail() + '!';
	
	$.ajax({
		"url" : "/HUIT/home/getPhone/"+profile.getEmail(),
		"method" : "GET",
		"contentType" : "application/json",
//		"data" : JSON.stringify(JSONObject),
		"processData" : false,
		"dataType" : "text",

		success : function(data) {
			console.log(data);
			if (data != "Not Found") {
				localStorage.setItem("ph", data);
				window.location.replace("/HUIT/profile1.html");

			} else {
				
		var name = profile.getName();
		var email = profile.getEmail();
		var phone = profile.getId();

		var JSONObject = {
			"registerDto" : {
				"name" : name,
				"email" : email,
				"phone" : phone
			}
		};
		console.log("hey"+JSONObject)
		$.ajax({
					"url" : "/HUIT/home/registerCandidate",
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

			}
			
		},
		error : function(d) {
			console.log(d)
		}
	});
	
	}
}

// ----- facebook login
function statusChangeCallback(response) {
	console.log('1');
	console.log('statusChangeCallback');
	console.log(response);
	if (response.status === 'connected') {
		testAPI();
	} else if (response.status === 'not_authorized') {
		
	} else {
		
	}
}

function checkLoginState() {
	FB.login(function(response) {
		if (response.status === 'connected') {
			window.location.reload();
			testAPI();

		} else if (response.status === 'not_authorized') {
			
		} else {
			
		}
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

	FB.getLoginStatus(function(response) {
		console.log('3');
		statusChangeCallback(response);
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

function testAPI() {
	localStorage.setItem("loginType","facebook");
	console.log('4');
	console.log('Welcome!  Fetching your information.... ');
	FB.api('/me?fields=id,name,email', function(response) {
		console.log('Successful login for: ' + response.name);
		console.log(response);

		document.getElementById('status').innerHTML = 'Thanks for logging in, '
				+ response.email + '!';
		
		
		$.ajax({
			"url" : "/HUIT/home/getPhone/"+response.email,
			"method" : "GET",
			"contentType" : "application/json",
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log();
				if (data != "Not Found") {
					localStorage.setItem("ph", data);
					window.location.replace("/HUIT/profile1.html");

				} else {
					
			var name = response.name;
			var email = response.email;
			var phone = response.id;
			console.log(name+" " +email +"" +phone);
			var JSONObject = {
				"registerDto" : {
					"name" : name,
					"email" : email,
					"phone" : phone
				}
			};
			console.log(JSONObject)
			$
					.ajax({
						"url" : "/HUIT/home/registerCandidate",
						"method" : "POST",
						"contentType" : "application/json",
						"data" : JSON.stringify(JSONObject),
						"processData" : false,
						"dataType" : "text",

						success : function(data) {
							console.log(data);
							localStorage.setItem("ph", phone);
							window.location.replace("/HUIT/profile1.html");
						},
						error : function(d) {
							console.log(d)
						}
					});

				}
				
			},
			error : function(d) {
				console.log(d)
			}
		});
	}, {
		scope : 'email'
	});
	$.ajax({
		"url" : "/HUIT/home/getPhone/"+response.email,
		"method" : "GET",
		"contentType" : "application/json",
		"processData" : false,
		"dataType" : "text",

		success : function(data) {
			console.log(data);
			if (data != "Not Found") {
				localStorage.setItem("ph", data);
				window.location.replace("/HUIT/profile1.html");

			} else {
				
		var name = response.name;
		var email = response.email;
		var phone = response.id;

		var JSONObject = {
			"registerDto" : {
				"name" : name,
				"email" : email,
				"phone" : phone
			}
		};
		console.log(JSONObject)
		$
				.ajax({
					"url" : "/HUIT/home/registerCandidate",
					"method" : "POST",
					"contentType" : "application/json",
					"data" : JSON.stringify(JSONObject),
					"processData" : false,
					"dataType" : "text",

					success : function(data) {
						console.log(data);
						// document.getElementById("registerResponse").text(data);
						localStorage.setItem("ph", data);
						window.location.replace("/HUIT/profile1.html");
					},
					error : function(d) {
						console.log(d)
					}
				});

			}
			
		},
		error : function(d) {
			console.log(d)
		}
	})
	localStorage.setItem("ph", data);
	window.location.replace("/HUIT/profile1.html");
}


// ---- normal user login
$(document).ready(function() {
	
	
	toggle="true";
	$('#dismiss').click(function() {
		window.location.replace("/HUIT/login.html");
	});
	
	
	//-- Forgot password
	$('#getpassword').click(function() {
		var emailpassword = document.getElementById("forgotEmail").value;
		$.ajax({
			"url" : "/HUIT/home/getPassword/"+emailpassword,
			"method" : "GET",
			"contentType" : "application/json",
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log(data);
				if (data != "default") {
					$('#myModal').modal('hide');
					$('#mymodal').modal('show');
						$("#title").text("Success");
						$("#message").text("Your password has been sent to your email, please check your mail to continue");
						$("#dismiss").text("Close");
						$("#forgotEmail").hide();
						$("#getpassword").hide();
					}
				 else {
					 $('#myModal').modal('hide');
						$('#myModal').modal('show');
							$("#title").text("Alert");
							$("#message").text("Invalid email or password, please check your chedentials and try again");
							$("#dismiss").text("Close");
							$("#forgotEmail").hide();
							$("#getpassword").hide();
				}
			},
			error : function(d) {
				console.log(d)
			}
		});
	});
	
	$('#loginbtn').click(function(e) {
		var uname = document.getElementById("userid").value;
		var passwrd = document.getElementById("password").value;
		
		//----------------Candidate Login ----------------------->
		if(toggle=="true"){
		var JSONObject = {
			"candidateGenDto" : {
				"email" : uname,
				"password" : passwrd
			}
		};
		$.ajax({
			"url" : "/HUIT/home/loginCandidate",
			"method" : "POST",
			"contentType" : "application/json",
			"data" : JSON.stringify(JSONObject),
			"processData" : false,
			"dataType" : "text",

			success : function(data) {
				console.log(data);
				if (data != "Not Found") {
					var ph="";
					ph=data;
					localStorage.setItem("ph", ph);
					window.location.replace("/HUIT/profile1.html");

				} else {
					$('#myModal').modal('show');
						$("#title").text("Alert");
						$("#message").text("Invalid credentials");
						$("#dismiss").text("Close");
						$("#forgotEmail").hide();
						$("#getpassword").hide();
				}

			},
			error : function(d) {
				console.log(d)
			}
		});
		}
		
		// -------  Admin Login --------------------------------------->
		else{

			var JSONObject = {
				"adminDto" : {
					"email" : uname,
					"password" : passwrd
				}
			};
			$.ajax({
				"url" : "/HUIT/homeadmin/loginadmin",
				"method" : "POST",
				"contentType" : "application/json",
				"data" : JSON.stringify(JSONObject),
				"processData" : false,
				"dataType" : "text",

				success : function(data) {
					console.log(data);
					if (data != "Not Found") {
						var ph="";
						ph=data;
						localStorage.setItem("phadmin", ph);
						window.location.replace("/HUIT/dashboard.html");

					} else {
						$('#myModal').modal('show');
						$("#title").text("Alert");
						$("#message").text("Invalid credentials");
						$("#dismiss").text("Close");
						$("#forgotEmail").hide();
						$("#getpassword").hide();
					}

				},
				error : function(d) {
					console.log(d)
				}
			});
		}
		e.preventDefault();
		
	});
});
