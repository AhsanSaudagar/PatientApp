<?php
require __DIR__ . '/config.php';

header('Content-Type: text/xml');
if(isset($_GET['tracker_id']) && !empty($_GET['tracker_id'])){
    if(isset($_GET['name']) && !empty($_GET['name']))
    {
        $name=$_GET['name'];
    }
    else {
    	$name="";
    }


    if(isset($_GET['time']) && !empty($_GET['time']))
    {
        $time="at ".$_GET['time'];
    }
    else {
    	$time="now";
    }
    header('Content-Type: text/xml');
    echo '<Response>
    <Gather input="speech dtmf" timeout="5" numDigits="1" action="'.$base_url.'/webhook.php?tracker_id='.$_GET["tracker_id"].'" finishOnKey="#">
    <Say>Hi '.$name.', You have scheduled medicine to be taken '.$time.'. Please press 9 if you need help. Press 1 if you are taking the medicine. Press 2 if you are not taking it. Thank you! </Say>
    </Gather>
    </Response>';
}
else{
    echo '';
}
?>
