<!DOCTYPE html>
<html>
	<head>
		<title>
		category
		</title>
		<link rel="stylesheet" type="text/css" href="test.css">
	</head>
	<body>
	<?php
	session_start();
		if(isset($_SESSION['loggedIn'])){
			echo  $_SESSION['userName'];
		}
		else{
			echo 'You must login first.'
		}
	?>
		<script src="test.js"></script>
		<div id="title">
		 TOP...
		</div>
		
		<nav>			
			<div class="navbar_element" >
				Menu 
			</div>
				
				<div class="navbar_element">
					Top Users 
				</div>
					
					<a href="" class="navbar_element">
						My Profile
					</a>
				
				<a href="" class="navbar_element">
					Login
				</a>
			
			<a href="" class="navbar_element">
				Create Account
			</a>
		</nav>
		
		<div id="current_location_bar">
		</div>
		<!-- Allow users to create nomine divs or seperate pages for nominees which are then displayed in each nominee page in order of most votes -->
	</body>
</html>
