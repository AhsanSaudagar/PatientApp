<?php
// Create a route that will handle Twilio webhook requests, sent as an
// HTTP POST to /voice in our application
require __DIR__ . '/vendor/autoload.php';
use Twilio\TwiML\VoiceResponse;

$response = new VoiceResponse();

$gather = $response->gather(['action' => '/gatherProcess.php',
    'method' => 'GET']);
file_put_contents("debug.log",print_r($response,TRUE));

if(isset($_POST['Digits']) && !empty($_POST['Digits'])){
	$gather->say("You have pressed ".$_POST['Digits']);
}
file_put_contents("debug1.log",print_r($_REQUEST,TRUE));
echo $response;
