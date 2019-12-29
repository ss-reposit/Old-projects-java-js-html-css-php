<!DOCTYPE html>
<html>
	<head>
		<title>
		login
		</title>
		<link rel="stylesheet" type="text/css" href="test.css">
	</head>
	<body>
	<?php
		session_start();
		if(isset($_SESSION['loggedIn'])){
			echo 'You are logged in as ' . $_SESSION['userName'] '. Logout to continue';
		}
		$doc = new DOMDocument; //IDK
		if(isset($_POST['submit'])){
			$dbhost = 'localhost';
			$dbuser = 'guest';
			$dbpass = 'pass';
			$$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
			if(!$conn){
				die('could not connect to server ' . mysqli_connect_error($conn));
			}
			$username = mysqli_real_escape_string($conn, $_POST['enterd_username']);
			$password =  $_POST['enterd_password'];
			
			mysqli_select_db($conn, 'user_data');
			$sql_username_check = "SELECT user_pass FROM user_log_info WHERE user_name= '$username'";
			$check_valid_username = mysqli_query($conn, $sql_username_check);
			if(!mysqli_num_rows($check_valid_rows)){
				echo 'No such username and pasword combination found.';
			}
			else{
				if(password_verify($password, $sql_username_check)){
					echo 'valid login';
					$_SESSION['loggedIn'] = true;
					$_SESSION['userName'] = $username;
				}
				else{
					echo 'invalid login';
				}
			}
		}
	?>
		<script src="test.js"></script>
		<div id="title">
		 WEBHOF 
		</div>
		
			<div id="form">
			  <form action="<?php $_SERVER['PHP_SELF']?>" method="post">
				<label for="uName">
					Username
				</label>
					<input id="uName" type="text" name="enterd_username" placeholder="Username">
				<label for="pass">
					Password
				</label>
					<input id="pass" type="text" name="enterd_password" placeholder="Password">

					<input type="submit" name="submit" value="login">
					
					<p id="login_status" > </p>
			  </form>
		  </div>
	</body>
</html>
