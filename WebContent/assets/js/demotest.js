function checkph(){
	var phone=localStorage.getItem("ph");
	if(phone==null){
	window.location.replace("/HUIT/homepage.html");
}}
window.onload = checkph;


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
	localStorage.clear();
	localStorage.removeItem("nameuser");
	}
	else if(localStorage.getItem("loginType")=="google"){
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
      });
      localStorage.clear();
      window.location.replace("/HUIT/login.html");
	}
	else{
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



$(document).ready(function() {
	var name=localStorage.getItem("nameuser");
	$("#username").html(name);
	
	
	
});