<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Dr.care - Free Bootstrap 4 Template by Colorlib</title>
      <script src="https://www.gstatic.com/firebasejs/5.8.3/firebase.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-app.js"></script>
      <script src="https://cdn.firebase.com/libs/firebaseui/3.5.2/firebaseui.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-auth.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-database.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-firestore.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-messaging.js"></script>
      <script src="https://www.gstatic.com/firebasejs/5.8.2/firebase-functions.js"></script>
      <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,200,300,400,500,600,700,800,900" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/support/css/open-iconic-bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/animate.css'/>">
    
    <link rel="stylesheet" href="<c:url value='/resources/support/css/owl.carousel.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/owl.theme.default.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/magnific-popup.css'/>">

    <link rel="stylesheet" href="<c:url value='/resources/support/css/aos.css'/>">

    <link rel="stylesheet" href="<c:url value='/resources/support/css/ionicons.min.css'/>">

    <link rel="stylesheet" href="<c:url value='/resources/support/css/bootstrap-datepicker.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/jquery.timepicker.css'/>">

    
    <link rel="stylesheet" href="<c:url value='/resources/support/css/flaticon.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/icomoon.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/support/css/style.css'/>">
      <script type="text/javascript">
          var config = {
              apiKey: "AIzaSyAZKcu9XjoiEk1RGJW0fK2n3yeJFfwlErg",
              authDomain: "patientapp-4c69f.firebaseapp.com",
              databaseURL: "https://patientapp-4c69f.firebaseio.com",
              projectId: "patientapp-4c69f",
              storageBucket: "patientapp-4c69f.appspot.com",
              messagingSenderId: "33836603016"
          };
          firebase.initializeApp(config);
          function registerUser() {
              var email = document.getElementById('emailAddress').value;
              var password = document.getElementById('password').value;
              if (email.length < 4) {
                  alert('Please enter an email address.');
                  return;
              }
              if (password.length < 4) {
                  alert('Please enter a password.');
                  return;
              }
              // Sign in with email and pass.
              // [START createwithemail]
              firebase.auth().createUserWithEmailAndPassword(email, password).then(function (value) {
                  var contextPath = $("#contextPath").val();
                  $.ajax({
                      type: "POST",
                      headers:{
                          "Content-Type" : 'application/json'
                      },
                      dataType : 'json',
                      url: contextPath+"/patient/signUpPatient",
                      data: JSON.stringify({
                          'name' : document.getElementById('name').value,
                          'emailAddress' : document.getElementById('emailAddress').value,
                          'mobileNo' : document.getElementById('mobileNo').value,
                          'gender' : document.getElementById('gender').value,
                          'age': document.getElementById('age').value,
                          'guardianName' : document.getElementById('guardianName').value,
                          'guardianMobileNumber' : document.getElementById('guardianMobileNumber').value,
                          'pid' : value.user.uid
                      }),
                      success: function(data) {
                          alert("Registered Successfully" );
                      }
                  });
                  // $.post(contextPath+"/patient/signUpPatient",
                  //     {
                  //         'name' : document.getElementById('name').value,
                  //         'emailAddress' : document.getElementById('emailAddress').value,
                  //         'mobileNo' : document.getElementById('mobileNo').value,
                  //         'gender' : document.getElementById('gender').value,
                  //         'age': document.getElementById('age').value,
                  //         'guardianName' : document.getElementById('guardianName').value,
                  //         'guardianMobileNumber' : document.getElementById('guardianMobileNumber').value
                  //     },
                  //     function(data, status){
                  //         alert("Data: " + data + "\nStatus: " + status);
                  //     });
              }).catch(function(error) {
                  // Handle Errors here.
                  var errorCode = error.code;
                  var errorMessage = error.message;
                  // [START_EXCLUDE]
                  if (errorCode == 'auth/weak-password') {
                      alert('The password is too weak.');
                  } else {
                      alert(errorMessage);
                  }
                  console.log(error);
                  // [END_EXCLUDE]
              });
              // [END createwithemail]
          }
          function initApp() {
              // Listening for auth state changes.
              // [START authstatelistener]
              firebase.auth().onAuthStateChanged(function(user) {
                  // [START_EXCLUDE silent]
                  // document.getElementById('quickstart-verify-email').disabled = true;
                  // [END_EXCLUDE]
                  if (user) {
                      var contextPath = $("#contextPath").val();
                      window.location = contextPath + '/patientApp/dashboard';
                      //alert("User is signed In");
                      // User is signed in.
                      var displayName = user.displayName;
                      var email = user.email;
                      var emailVerified = user.emailVerified;
                      var photoURL = user.photoURL;
                      var isAnonymous = user.isAnonymous;
                      var uid = user.uid;
                      var providerData = user.providerData;
                      // [START_EXCLUDE]
                      // if (!emailVerified) {
                      //     document.getElementById('quickstart-verify-email').disabled = false;
                      // }
                      // [END_EXCLUDE]
                  } else {
                      // User is signed out.
                      // [START_EXCLUDE]
                      //alert("User is signed out")
                      // [END_EXCLUDE]
                  }
                  // [START_EXCLUDE silent]
                  // document.getElementById('quickstart-sign-in').disabled = false;
                  // [END_EXCLUDE]
              });
              // [END authstatelistener]
              $('#emailAddress').val("");
              document.getElementById('register').addEventListener('click', registerUser, false);
          }
          window.onload = function() {
              initApp();
          };
      </script>

  </head>
  <body>
                                                <nav class="navbar py-4 navbar-expand-lg ftco_navbar navbar-light bg-light flex-row">
        <div class="container">
            <div class="row no-gutters d-flex align-items-start align-items-center px-3 px-md-0">
              <div class="col-lg-2 pr-4 align-items-center">
                <a class="navbar-brand" href="#">V<span>care</span></a>
                  <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
              </div>
              <div class="col-lg-10 d-none d-md-block">
                <div class="row d-flex">
                  <div class="col-md-4 pr-4 d-flex topper align-items-center">
                    <div class="icon bg-white mr-2 d-flex justify-content-center align-items-center"><span class="icon-map"></span></div>
                    <span class="text">Address: Mahavir Nagar, Satara Road, Pune 411037</span>
                  </div>
                  <div class="col-md pr-4 d-flex topper align-items-center">
                    <div class="icon bg-white mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                    <span class="text">Email: support@perennialsys.com</span>
                  </div>
                  <div class="col-md pr-4 d-flex topper align-items-center">
                    <div class="icon bg-white mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                    <span class="text">Phone: 020 2421 1286</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </nav>
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container d-flex align-items-center">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      <!-- <p class="button-custom order-lg-last mb-0"><a href="appointment.html" class="btn btn-secondary py-2 px-3">Patient Registration</a></p> -->
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav mr-auto">
	        	<li class="nav-item"><a href="#" class="nav-link pl-0">Home</a></li>
	        	<li class="nav-item"><a href="#" class="nav-link">About</a></li>
	        
	          <li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->
    
  
		
		<section class="ftco-section ftco-no-pt ftco-no-pb ftco-counter img" id="section-counter" style="background-image: url(<c:url value='/resources/support/images/bg_3.jpg'/>);" data-stellar-background-ratio="0.5">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-6 py-5 pr-md-5">
	          <div class="heading-section heading-section-white ftco-animate mb-5">
	          	<!-- <span class="subheading">Registration</span> -->
	            <h2 class="mb-4">Free Registration</h2>
	            <p>People who want to avail this service can register themselves with this form</p>
	          </div>
	          <form action="#" class="appointment-form ftco-animate">
	    				<div class="d-md-flex">
		    				<div class="form-group">
		    					<input type="text" class="form-control" pattern="^[\\p{L} .'-]+$" placeholder="Full Name" id="name" required>
		    				</div>
              </div>
              <div class="d-md-flex">
                  <div class="form-group">
                    <input type="number" class="form-control" placeholder="Age" id="age"required>
                  </div>
                  <div class="form-group ml-md-4">
                    <input type="text" class="form-control" maxlength="1" pattern="[(?:m|f|M|F)]" placeholder="Gender" id="gender" required>
                  </div>
                </div>
              <div class="d-md-flex">
		    				<div class="form-group">
		    					<input type="tel" class="form-control" maxlength="10" placeholder="Mobile Number" id="mobileNo" required>
		    				</div>
		    				<div class="form-group ml-md-4">
		    					<input type="email" class="form-control" placeholder="Email Id" id="emailAddress" required>
		    				</div>
              </div>
              <div class="d-md-flex">
		    				<div class="form-group">
		    					<input type="password" class="form-control" placeholder="Password" id="password" required>
		    				</div>
              </div>
              <div class="d-md-flex">
		    				<div class="form-group">
		    					<input type="text" class="form-control" pattern="^[\\p{L} .'-]+$" placeholder="Guardian Name" id="guardianName" required>
		    				</div>
		    				<div class="form-group ml-md-4">
		    					<input type="tel" class="form-control" maxlength="10" placeholder="Guardian Number"  id="guardianMobileNumber" required>
		    				</div>
              </div>
	    			
	    				<div class="d-md-flex">
		            <div class="form-group ml-md-4">
                        <button class="btn btn-secondary py-3 px-4" id="register" name="register">Register</button>
		              <%--<input type="submit" value="Register" class="btn btn-secondary py-3 px-4">--%>
		            </div>
	    				</div>
	    			</form>
    			</div>
    			<div class="col-lg-6 p-5 bg-counter aside-stretch">
						<h3 class="vr">About Dr.Care Facts</h3>
    				<div class="row pt-4 mt-1">
		          <div class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 p-5 bg-light">
		              <div class="text">
		                <strong class="number" data-number="30">0</strong>
		                <span>Years of Experienced</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 p-5 bg-light">
		              <div class="text">
		                <strong class="number" data-number="4500">0</strong>
		                <span>Happy Patients</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 p-5 bg-light">
		              <div class="text">
		                <strong class="number" data-number="84">0</strong>
		                <span>Number of Doctors</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 p-5 bg-light">
		              <div class="text">
		                <strong class="number" data-number="300">0</strong>
		                <span>Number of Staffs</span>
		              </div>
		            </div>
		          </div>
	          </div>
          </div>
        </div>
    	</div>
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-5">
              <h2 class="ftco-heading-2 logo">V<span>care</span></h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
            </div>
            <div class="ftco-footer-widget mb-5">
            	<h2 class="ftco-heading-2">Have a Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">Mahavir Nagar, Satara Road, Pune 411037</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">020 2421 1286</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">support@perennialsys.com</span></a></li>
	              </ul>
	            </div>

	            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-3">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-5 ml-md-4">
              <h2 class="ftco-heading-2">Links</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Home</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>About</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Services</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Deparments</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Contact</a></li>
              </ul>
            </div>
            <div class="ftco-footer-widget mb-5 ml-md-4">
              <h2 class="ftco-heading-2">Services</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Neurolgy</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Dentist</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Ophthalmology</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Cardiology</a></li>
                <li><a href="#"><span class="ion-ios-arrow-round-forward mr-2"></span>Surgery</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-5">
              <h2 class="ftco-heading-2">Recent Blog</h2>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(<c:url value='/resources/support/images/image_1.jpg'/>);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> Dec 25, 2018</a></div>
                    <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                    <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-5 d-flex">
                <a class="blog-img mr-4" style="background-image: url(<c:url value='/resources/support/images/image_2.jpg'/>);"></a>
                <div class="text">
                  <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> Dec 25, 2018</a></div>
                    <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                    <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md">
          	<div class="ftco-footer-widget mb-5">
            	<h2 class="ftco-heading-2">Opening Hours</h2>
            	<h3 class="open-hours pl-4"><span class="ion-ios-time mr-3"></span>We are open 24/7</h3>
            </div>
            <div class="ftco-footer-widget mb-5">
            	<h2 class="ftco-heading-2">Subscribe Us!</h2>
              <form action="#" class="subscribe-form">
                <div class="form-group">
                  <input type="text" class="form-control mb-2 text-center" placeholder="Enter email address">
                  <input type="submit" value="Subscribe" class="form-control submit px-3">
                </div>
              </form>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="<c:url value='/resources/support/js/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery-migrate-3.0.1.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/popper.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/bootstrap.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.easing.1.3.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.waypoints.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.stellar.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/owl.carousel.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.magnific-popup.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/aos.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.animateNumber.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/bootstrap-datepicker.js'/>"></script>
  <script src="<c:url value='/resources/support/js/jquery.timepicker.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/scrollax.min.js'/>"></script>
  <script src="<c:url value='/resources/support/js/main.js'/>"></script>
    
  </body>
</html>