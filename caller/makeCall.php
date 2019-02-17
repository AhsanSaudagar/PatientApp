<?php
require __DIR__ . '/vendor/autoload.php';
require __DIR__ . '/config.php';
use Twilio\Rest\Client;

if(isset($_GET['to_number']) && !empty($_GET['to_number']) &&
   isset($_GET['name']) && !empty($_GET['name']) &&
   isset($_GET['time']) && !empty($_GET['time']) &&
   isset($_GET['tracker_id']) && !empty($_GET['tracker_id'])
   //&& isset($_GET['guardian_number']) && !empty($_GET['guardian_number'])
){

// Where to make a voice call (your cell phone?)
$to_number = $_GET['to_number'];
//$emergency = "+919049392312";

$client = new Client($account_sid, $auth_token);
$client->account->calls->create(  
    $to_number,
    $twilio_number,
    array(
        "statusCallback" => $base_url."/callback.php?tracker_id=".$_GET['tracker_id'],
        "statusCallbackEvent" => array("busy","answered","canceled","failed","no-answer","completed"),
        "statusCallbackMethod" => "POST",
        "url" => $base_url."/twimlScript.php?name=".$_GET['name']."&time=".$_GET['time']."&tracker_id=".$_GET['tracker_id']
    )
);
}
else{
    file_put_contents("debug.log", "Error: Inputs parameters incorrect".PHP_EOL,FILE_APPEND);
}
?>