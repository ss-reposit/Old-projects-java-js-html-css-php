<!DOCTYPE html>
<html>
	<head>
		<title>
		home
		</title>
		<link rel="stylesheet" type="text/css" href="test.css">
	</head>
	<body>
	<?php 
		session_start();
		if(isset($_SESSION['loggedIn'])){
			echo 'You are logged in as ' . $_SESSION['userName'] . '.';
		}
		else{
			echo 'log in to vote';
		}
	?>
		<script src="test.js"></script>
		<div id="title">
		 WEBHOF 
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
		<div class="card_display">
			<div class="vote_card">
				<a href=""><div class="vote_card_content">
					<img src="/var/www/html/profileImages/default_profile.png"/>
					<p class="p_top_name">
						NAME
					</p>
					<p class="p_bottom_description">
						DESCRIPTION
					</p>
				</div></a>
			</div>
				<div class="vote_card">
					<a href=""><div class="vote_card_content">
						<img src="/var/www/html/profileImages/default_profile.png"/>
						<p class="p_top_name">
							NAME
						</p>
						<p class="p_bottom_description">
							DESCRIPTION
						</p>
					</div></a>
				</div>
					<div class="vote_card">
						<a href=""><div class="vote_card_content">
							<img src="/var/www/html/profileImages/default_profile.png"/>
							<p class="p_top_name">
								NAME
							</p>
							<p class="p_bottom_description">
								DESCRIPTION
							</p>
						</div></a>
					</div>
		</div>
			
		<div class="card_display">
					<a href=""><div class="vote_card">	
						<div class="vote_card_content">
							<img src="/var/www/html/profileImages/default_profile.png"/>
							<p class="p_top_name">
								NAME
							</p>
							<p class="p_bottom_description">
								DESCRIPTION
							</p>
						</div></a>
					</div>
				<div class="vote_card">	
					<a href=""><div class="vote_card_content">
						<img src="/var/www/html/profileImages/default_profile.png"/>
						<p class="p_top_name">
							NAME
						</p>
						<p class="p_bottom_description">
							DESCRIPTION
						</p>
					</div></a>
				</div>
			<div class="vote_card">			
				<a href=""><div class="vote_card_content">
					<img src="/var/www/html/profileImages/default_profile.png"/>
					<p class="p_top_name">
						NAME
					</p>
					<p class="p_bottom_description">
						DESCRIPTION
					</p>
				</div></a>
			</div>
		</div>
		<div id="footer">
		</div>
	</body>
</html>
