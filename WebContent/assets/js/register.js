$(document)
		.ready(
				function() {

					$('#registerBtn')
							.click(
									function(e) {
										

										console.log("bttn")
										var name = document
												.getElementById("name").value;
										var email = document
												.getElementById("email").value;
										var phone = document
												.getElementById("contact-no").value;

										var JSONObject = {
											"registerDto" : {
												"name" : name,
												"email" : email,
												"phone" : phone
											}
										};
										console.log(JSONObject)
										openModal();
										$
												.ajax({
													"url" : "/HUIT/home/registerCandidate",
													"method" : "POST",
													"contentType" : "application/json",
													"data" : JSON
															.stringify(JSONObject),
													"processData" : false,
													"dataType" : "text",

													success : function(data) {
														console.log(data);
														// document.getElementById("registerResponse").text(data);
														closeModal();
														$('#myModal').modal(
																'show');
														if (data=="Registered") {
															$(
																	"#registerSuccessfull")
																	.text(
																			"Your password has been sent to your registered email ID. Please click OK to continue");
														} else if (data=="Already Registered") {
															$(
																	"#registerSuccessfull")
																	.text(
																			"An account has already been registered with your credentials. Please Login to continue");
														}

														else {
															$("#registerSuccessfull").text("Invalid Credentials");
														}
														
													},
													error : function(d) {
														console.log(d)
													}
												});
										e.preventDefault();
									});
				});
