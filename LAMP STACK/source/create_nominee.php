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
		if(isset($_POST['submit']) && isset($_POST['nominee_name']) && isset($_POST['nominee_category']) && isset($_FILES['nominee_image'])){
				
				$nominee_name = $_POST['nominee_name'];
				$nominee_category = $_POST['nominee_category'];
				$nominee_image = basename($_FILES['nominee_image']['name']);
				
				$upload = 1;
				$target_dir = '/'. $nominee_category . '/';
				$target_file = $target_dir . $nominee_image;
				$file_type = pathinfo($target_file, PATHINFO_EXTENSION);
				$check = getimagesize($_FILES['nominee_image']['tmp_name']);
				if($check !== false){
					echo "File is an image " . $check['mime'];
					$upload=1;
				}
				else{
					echo "file is not an image";
					$upload=0;
				}
				if(file_exists($target_file)){ 
					echo "file already exists"; 
					$upload=0;
				}
				if($_FILE['nominee_image']['size'] > 16000){ 
					echo "File is too large";
					$upload=0;
				}
				if($file_type != "jpg" && $file_type != "png" && $file_type != "gif" ){
					echo "wrong file type";
					$upload=0;
				}
				if($upload !== 0){
					if(move_uploaded_file($_FILES['nominee_image']['tmp_name'], $target_file)){
						echo "the file : " .$_FILES['nominee_image']['name'] . " sucessfully uploaded"; 
					}
					else{
						echo "There was a problem uploading";
					}
				}
				else{
					echo "upload=0 no upload sucessful";
				}
				if(file_exists($target_dir.$nominee_image.'php')){
					echo "Error, nominee already present";
				}
				else{
				$file_handle = fopen($target_dir . $nominee_name . '.php' , 'a') or die('cannot fopen()');
				$html = 
				'<html>
					<head>
						<title>'. $nominee_name .'</title>
					</head>
					<body> 
						<div id="title">'. $nominee_name .'</div>
						<img'. $target_file .'/>
					</body>
				</html>'
				;
				fwrite($file_handle,$html);
				fclose($file_handle);
				echo "nomine page created, and image created and linked succesfully";
				}				
			}
			else{
				echo 'error values not set';
				echo 'submit ' . ($_POST['submit']) . ' name ' . isset($_POST['nominee_name']) .' category '. isset($_POST['nominee_category']) .' image '. isset($_FILES['nominee_image']);
			}
		}
		else{
			echo 'You must login first.';
		}
	?>
		<div id="title">
		 CREATE NOMINEE
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
		 <form action="<?php $_SERVER['PHP_SELF']?>" method="post" enctype="multipart/form-data">
			<label for="nominee_name">NAME</label>
			<input id="nominee_name" type="text" name="nominee_name" placeholder="Enter your nominee">
			<label for="nominee_category">CATEGORY</label>
			<select id="nominee_category" name="nominee_category">
				<option value="Top Memer">Top CATEGORY_</option><option value="Top Youtubers">Top CATEGORY_</option>
				<option value="Top Gamers">Top CATEGORY_</option><option value="Top Entertainers">Top CATEGORY_</option>
				<option value="Best Musician">Best CATEGORY_</option><option value="Best Fighters">Best CATEGORY_</option>
			</select>
			<label for="nominee_image">IMAGE</label>
			<input id="nominee_image" type="file" name="nominee_image" accept="image/.gif, image/.jpg, image/.png">
			<input  id ="submit "type="submit" name="submit" value="Nominate">
			</form>
		<!--use form to write to a text file, no need for description? just image and name-->
		<!-- cannot create file with AJAX -->
		<script type="text/javaScript"> 
			/*function nominee_check_inputs() {
				var http_Tx_Rx = new XMLHttpRequest();
				
				var nominee_name = document.getElementById("nominee_name").innerHTML;
				var nominee_description = document.getElementById("nominee_description").innerHTML;
				var nominee_image = document.getElementById("nominee_image").innerHTML;//?
				 if(nominee_name && nominee_description && nominee_image){
					 if(true){
					 http_Tx_Rx.open("POST", "/var/www/html/nominees/" + nominee_name + ".txt", true);
					 return;
					 }
					 else{
						 console.log('nominee already exists');
					 }
				 }
				 else{
					 return false;
				 }
				 
			}*/
		</script>
	</body>
</html>
