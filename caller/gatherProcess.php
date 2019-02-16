<?php
// Create a route that will handle Twilio webhook requests, sent as an
// HTTP POST to /voice in our application

file_put_contents("debug2.log",print_r($_REQUEST,TRUE));

