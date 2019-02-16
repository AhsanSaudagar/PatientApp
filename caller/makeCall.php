<?php
require __DIR__ . '/vendor/autoload.php';
require __DIR__ . '/config.php';
use Twilio\Rest\Client;

// Where to make a voice call (your cell phone?)
$to_number = "+919049392312";
//$emergency = "+919049392312";

$client = new Client($account_sid, $auth_token);
$client->account->calls->create(  
    $to_number,
    $twilio_number,
    array(
        "url" => $base_url."/twimlScript.php?name='Rakesh'&time='8:30PM&schedule_id=231"
    )
);
?>