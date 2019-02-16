<?php
// Create a route that will handle Twilio webhook requests, sent as an
$base_url = "http://52.55.222.177/patient_app";

// Your Account SID and Auth Token from twilio.com/console
$account_sid = 'ACadaa835e58844d68a424f3027d05de2e';
$auth_token = 'ff710da829e1bc986fb5dade41cbb64a';

// A Twilio number you own with Voice capabilities
$twilio_number = "+12026183196";

$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "patient_app";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    file_put_contents("debug.log","Connection failed". PHP_EOL,FILE_APPEND);
    die("Connection failed: " . $conn->connect_error);
}