<?php
header('Content-Type: text/xml');
if(isset($_GET['schedule_id']) && !empty($_GET['schedule_id'])){
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
    <Gather input="speech dtmf" timeout="3" numDigits="1" action="http://52.55.222.177/patient_app/webhook.php?schedule_id='.$_GET["schedule_id"].'" finishOnKey="#">
    <Say>Hi '.$name.', You have scheduled medicine to be taken '.$time.'. Please press 0 if you need help. Press 1 if you are taking the medicine. Press 2 if you are not taking it. </Say>
    </Gather>
    </Response>';
}
else{
    echo '';
}
?>
