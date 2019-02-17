<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Vcare</title>
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
          function SignOut() {
              if (firebase.auth().currentUser) {
                  // [START signout]
                  firebase.auth().signOut();
                  var contextPath = $("#contextPath").val();
                  window.location = contextPath + '/patientApp/logout';
                  // [END signout]
              }
          }
          function initApp() {
              // Listening for auth state changes.
              // [START authstatelistener]
              firebase.auth().onAuthStateChanged(function(user) {
                  // [START_EXCLUDE silent]
                  // document.getElementById('quickstart-verify-email').disabled = true;
                  // [END_EXCLUDE]
                  if (!user) {
                      // User is signed out.
                      // [START_EXCLUDE]
                      var contextPath = $("#contextPath").val();
                      window.location = contextPath + '/patientApp/';
                      // [END_EXCLUDE]
                  }else{
                      sessionStorage.pid = user.uid;
                  }
                  // [START_EXCLUDE silent]
                  // document.getElementById('quickstart-sign-in').disabled = false;
                  // [END_EXCLUDE]
              });
              // [END authstatelistener]
              document.getElementById('logout').addEventListener('click', SignOut, false);
          }

          window.onload = function() {
              initApp();
              getMedicineList()
          };
          $(document).on("click", "#save", function() {
              var jsonObject ={};
              var medicine = $("#medicine_name1").children("option:selected"). val();
              var qty = $("#qty1").children("option:selected"). val();
              var time = $("#time1").children("option:selected"). val();
              var patientId;
              if(medicine=='Select a medicine'){
                  alert("please select medicine");
                  return;
              }
              jsonObject["medicineId"] = medicine;
              jsonObject["scheduledQuantity"] = qty;
              jsonObject["scheduledTime"] = time;

              var contextPath = $("#contextPath").val();
              $.ajax({
                  type: "POST",
                  headers:{
                      "Content-Type" : 'application/json',
                      "patientId" : sessionStorage.pid,
                  },
                  dataType : 'json',
                  url: contextPath+"/patient/MedicineSchedule",
                  data: JSON.stringify(jsonObject),
                  success: function(response) {
                      $("#removeSchedule").attr('scheduleId',response.ScheduleId);
                      alert(response.Result);
                  }
              });

          });

          $(document).on("click", "#removeSchedule", function() {
              var jsonObject ={};
              var scheduleId = $(this).attr('scheduleId');
              var contextPath = $("#contextPath").val();
              if(scheduleId ==undefined || scheduleId==null || scheduleId==0){
                  alert('schedule not exist');
                  return;
              }
              $.ajax({
                  type: "DELETE",
                  headers:{
                      "Content-Type" : 'application/json',
                      "patientId" : sessionStorage.pid,
                      "scheduleId" : scheduleId,
                  },
                  dataType : 'json',
                  url: contextPath+"/patient/MedicineSchedule",
                  success: function(response) {
                      alert(response.Result);
                  }
              });

          });


          function getMedicineList() {
              var contextPath = $("#contextPath").val();
              $.ajax({
                  type: "GET",
                  url: contextPath+"/patient/getAllMedicines",
                  success: function(response) {
                      populateProgramNameList(response.data ,"medicine_name1")
                  }
              });
          }

          function populateProgramNameList(data, id) {
              $("#" + id).empty();
              $("#" + id).append('<option >' + "Select a medicine" + '</option>');
              $.each(data, function (index, value) {
                  $("#" + id).append('<option value="' + value.id + '">' + value.name + '</option>');
              });
          }


      </script>
  </head>
  <body>
    <nav class="navbar py-4 navbar-expand-lg ftco_navbar navbar-light bg-light flex-row">
    	<div class="container">
    		<div class="row no-gutters d-flex align-items-start align-items-center px-3 px-md-0">
    			<div class="col-lg-2 pr-4 align-items-center">
		    		<a class="navbar-brand" href="index.html">V<span>care</span></a>
	    		</div>
                <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
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
	       <p  id="logout" class="button-custom order-lg-last mb-0 btn btn-secondary py-2 px-3">Logout</p>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav mr-auto">
	        	<li class="nav-item"><a href="index.html" class="nav-link pl-0">Home</a></li>
	        	<li class="nav-item"><a href="about.html" class="nav-link">About</a></li>

	        	   <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->
    
    
		
		<section class="ftco-section">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-2">
          <div class="col-md-8 text-center heading-section ftco-animate">
          	<!-- <span class="subheading">Facility</span> -->
            <h2 class="mb-4">Medicine Schedule</h2>
            <p>Fill out the entries below to get reminder of your medicine schedule</p>
          </div>
        </div>
        <form name="medicine_schedule" action="#" method="POST">
        <div class="col-md-8 text-center parent-container">
                
                    
                 <div id="clonedInput1" class="clonedInput">
                        <div>
                            <label for="txtCategory" class="">Medicine Name<span class="requiredField">*</span></label>
                            <select class="" name="medicine_name[]" id="medicine_name1">
                              <option value="disprine">disprine</option>
                              <option value="crocin">crocin</option>
                              <option value="pari 10 mg">pari 10 mg</option>
                              <option value="mg 500">mg 500</option>
                            </select>
                        </div>
                        <div>
                            <label for="txtSubCategory" class="">Quantity <span class="requiredField">*</span></label>
                            <select class="" name="qty[]" id="qty1">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <div>
                            <label for="txtSubSubCategory">Time <span class="requiredField">*</span></label>
                            <select name="time[]" id="time1">
                              <option value="2019-01-01 00:00:00">00:00</option>
                              <option value="2019-01-01 00:30:00">00:30</option>
                              <option value="2019-01-01 01:00:00">01:00</option>
                              <option value="2019-01-01 01:30:00">01:30</option>
                              <option value="2019-01-01 02:00:00">02:00</option>
                              <option value="2019-01-01 02:30:00">02:30</option>
                              <option value="2019-01-01 03:00:00">03:00</option>
                                <option value="2019-01-01 03:30:00">03:30</option>
                                <option value="2019-01-01 04:00:00">04:00</option>
                              <option value="2019-01-01 04:30:00">04:30</option>
                              <option value="2019-01-01 05:00:00">05:00</option>
                                <option value="2019-01-01 05:30:00">05:30</option>
                                <option value="2019-01-01 06:00:00">06:00</option>
                              <option value="2019-01-01 06:30:00">06:30</option>
                              <option value="2019-01-01 07:00:00">07:00</option>
                                <option value="2019-01-01 07:30:00">07:30</option>
                                <option value="2019-01-01 08:00:00">08:00</option>
                              <option value="2019-01-01 08:30:00">08:30</option>
                              <option value="2019-01-01 09:00:00">09:00</option>
                                <option value="2019-01-01 09:30:00">09:30</option>
                                <option value="2019-01-01 10:00:00">10:00</option>
                              <option value="2019-01-01 10:30:00">10:30</option>
                              <option value="2019-01-01 11:00:00">11:00</option>
                              <option value="2019-01-01 11:30:00">11:30</option>
                              <option value="2019-01-01 12:00:00">12:00</option>
                              <option value="2019-01-01 12:30:00">12:30</option>
                              <option value="2019-01-01 13:00:00">13:00</option>
                              <option value="2019-01-01 13:30:00">13:30</option>
                              <option value="2019-01-01 14:00:00">14:00</option>
                              <option value="2019-01-01 14:30:00">14:30</option>
                              <option value="2019-01-01 15:00:00">15:00</option>
                              <option value="2019-01-01 15:30:00">15:30</option>
                              <option value="2019-01-01 16:00:00">16:00</option>
                              <option value="2019-01-01 16:30:00">16:30</option>
                              <option value="2019-01-01 17:00:00">17:00</option>
                              <option value="2019-01-01 17:30:00">17:30</option>
                              <option value="2019-01-01 18:00:00">18:00</option>
                              <option value="2019-01-01 18:30:00">18:30</option>
                              <option value="2019-01-01 19:00:00">19:00</option>
                              <option value="2019-01-01 19:30:00">19:30</option>
                              <option value="2019-01-01 20:00:00">20:00</option>
                              <option value="2019-01-01 20:30:00">20:30</option>
                              <option value="2019-01-01 21:00:00">21:00</option>
                              <option value="2019-01-01 21:30:00">21:30</option>
                              <option value="2019-01-01 22:00:00">22:00</option>
                              <option value="2019-01-01 22:30:00">22:30</option>
                              <option value="2019-01-01 23:00:00">23:00</option>
                              <option value="2019-01-01 23:30:00">23:30</option>


                            </select>
                        </div>
                        <div class="actions">
                            <input type="button" class="clone" value="Add More"></input> 
                            <input type="button" id="removeSchedule" class="remove" value="Remove"></input>
                        </div>
                        
                    </div>
         
        </div>
        <%--<input type="Submit" class="clone" value="Save"></input>--%>
        <button class="btn btn-secondary py-3 px-4" id="save" name="save">Save</button>
    </form>
    	
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
	                <li><span class="icon icon-map-marker"></span><span class="text">Address: Mahavir Nagar, Satara Road, Pune 411037</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">020 2421 1286</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">Email: support@perennialsys.com</span></a></li>
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
  <!--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>-->
  <script src="<c:url value='/resources/support/js/main.js'/>"></script>
    <script>
    

//Create and append the options

        
    var regex = /^(.+?)(\d+)$/i;
var cloneIndex = $(".clonedInput").length;

function clone(){
    $(this).parents(".clonedInput").clone()
        .appendTo(".parent-container")
        .attr("id", "clonedInput" +  cloneIndex)
        .find("*")
        .each(function() {
            var id = this.id || "";
            var match = id.match(regex) || [];
            if (match.length == 3) {
                this.id = match[1] + (cloneIndex);
            }
        })
        .on('click', 'input.clone', clone)
        .on('click', 'input.remove', remove);
    cloneIndex++;
}
function remove(){
    $(this).parents(".clonedInput").remove();
}
$("input.clone").on("click", clone);

$("input.remove").on("click", remove);
    </script>
  </body>
</html>