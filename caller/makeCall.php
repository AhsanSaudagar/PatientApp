<?php
require __DIR__ . '/vendor/autoload.php';
require __DIR__ . '/config.php';
use Twilio\Rest\Client;

// Your Account SID and Auth Token from twilio.com/console
$account_sid = 'ACadaa835e58844d68a424f3027d05de2e';
$auth_token = 'ff710da829e1bc986fb5dade41cbb64a';
// In production, these should be environment variables. E.g.:
// $auth_token = $_ENV["TWILIO_ACCOUNT_SID"]

// A Twilio number you own with Voice capabilities
$twilio_number = "+12026183196";

// Where to make a voice call (your cell phone?)
$to_number = "+919049392312";

$client = new Client($account_sid, $auth_token);
$client->account->calls->create(  
    $to_number,
    $twilio_number,
    array(
        "url" => $base_url."/twimlScript.php?name='Rakesh'&time='8:30PM&schedule_id=231"
    )
);
?>