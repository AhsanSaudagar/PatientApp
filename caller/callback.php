<?php
// Create a route that will handle Twilio webhook requests, sent as an
// HTTP POST to /voice in our application
require __DIR__ . '/vendor/autoload.php';
require __DIR__ . '/config.php';

use Twilio\TwiML\VoiceResponse;

$response = new VoiceResponse();

$gather = $response->gather(['action' => '/gatherProcess.php',
    'method' => 'GET']);

    // file_put_contents("debug.log",print_r($response,TRUE));
$is_panic = "0";
$is_taken = "0";



// if(isset($_POST['Digits']) && !empty($_POST['Digits'])){
//     if($_POST['Digits'] == 1){
//         $is_taken = "1"; //Medicine Taken
//         // $is_panic = "0"; //No Panic
//     }
//     elseif($_POST['Digits'] == 9){
//         // $is_taken = "0"; //Medicine not Taken
//         $is_panic = "1"; // Panic pressed
//     }
// 	// $gather->say("You have pressed ".$_POST['Digits']);
// }


try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // prepare sql and bind parameters
    // $stmt = $conn->prepare("INSERT INTO tracker_log (schedule_id, is_taken, is_panic) 
    // VALUES (:schedule_id, :is_taken, :is_panic)");
    // $stmt->bindParam(':schedule_id', $schedule_id);
    // $stmt->bindParam(':is_taken', $is_taken);
    // $stmt->bindParam(':is_panic', $is_panic);
    // $stmt->bindParam(':created_date', $created_date);


    $sql = "UPDATE tracker_log SET is_taken=?, is_panic=? ".$where;
    $stmt= $conn->prepare($sql);
    $stmt->execute([$is_taken, $is_panic, $value]);

    if(isset($_GET['tracker_id']) && !empty($_GET['tracker_id'])){
        
        $where = "WHERE tracker_id=?";
        $value = $_GET['tracker_id'];

        $sql = "UPDATE tracker_log SET twilio_id=? is_taken=?, is_panic=? ".$where;
        $stmt= $conn->prepare($sql);
        $stmt->execute([$_POST['CallSid'],$is_taken, $is_panic, $value]);
    
    }
    // elseif(isset($_POST['CallSid']) && !empty($_POST['CallSid'])){

    //     $where = "WHERE twilio_id=?";
    //     $value = $_POST['CallSid'];

    //     $sql = "UPDATE tracker_log SET  is_taken=?, is_panic=? ".$where;
    //     $stmt= $conn->prepare($sql);
    //     $stmt->execute([$is_taken, $is_panic, $value]);

        
    // }
    
    // insert a row
    // $schedule_id = "22";
    // $is_taken = "1";
    // $is_panic = "0";
    // $created_date = "";
    $stmt->execute();
    }
catch(PDOException $e)
    {
        file_put_contents("debug1.log", "Error: " . $e->getMessage().PHP_EOL,FILE_APPEND);
    }
$conn = null;
file_put_contents("debug1.log",print_r($_POST,TRUE));
echo $response;
