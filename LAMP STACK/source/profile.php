<!DOCTYPE html>
<html>
	<head>
		<title>
		Profile
		</title>
		<link rel="stylesheet" type="text/css" href="test.css">
	</head>
	<body>
	<?php
	session_start();
		if(isset($_SESSION['loggedIn'])){
			echo  $_SESSION['userName'];
			$dbhost = 'localhost';
			$dbuser = 'guest';
			$dbpass = 'pass';
			$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
			if(!$conn){
				die('could not connect to server ' . mysqli_connect_error($conn));
			}
			mysqli_select_db($conn, 'user_data');
			$sql="SELECT reputation profileImage description FROM user_profile_info WHERE username= '$_SESSION[userName]'";
			$result = mysqli_query($conn, $sql);
			
			while($row = mysqli_fetch_assoc($result)){
			}
			echo $row['reputation'] . 'dd';
		}
		else{
			echo 'You must login first.'
		}
	?>
		<script src="test.js"></script>
		<div d="title">
		 WEBHOF 
		</div>
		
	</body>
</html>
