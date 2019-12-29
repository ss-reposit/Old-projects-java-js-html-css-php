<html>
   <head>
      <title>Create Account</title>
	  <link rel="stylesheet" type="text/css" href="test.css">
   </head>
   <body>

      <?php
			session_start();
			if(isset($_SESSION['loggedIn'])){
			echo 'You are logged in as ' . $_SESSION['userName'] '. Logout to continue';
			}
           if(isset($_POST['submit'])){
			$dbhost = 'localhost';
           $dbuser = 'root';
           $dbpass = 'pass';
           $conn = mysqli_connect($dbhost, $dbuser, $dbpass);

           if(! $conn ) {
                  die('Could not connect: ' . mysqli_connect_error($conn));
           }
           $username = mysqli_real_escape_string($conn, $_POST['create_username']);
           $pass = password_hash($_POST['create_password'], PASSWORD_DEFAULT);
           mysqli_select_db($conn,'user_data');
           $sql = "INSERT INTO user_log_info(user_name, user_pass, submission_date) VALUES ('$username','$pass',NOW())"; //'date' is a keyword change to submission_date
           mysqli_query($conn, $sql);
           echo "Account Sucessfully Created!\n";
		   $Profile_Data_Sql="INSERT INTO user_profile_info(username, reputation, description, image) VALUES ('$username', '0', '', '~/Documents/default_profile')";
           mysqli_close($conn);
           }
        ?>
          		<div id="title">
		 WEBHOF 
		</div>
			  <form action="<?php $_SERVER['PHP_SELF']?>" method="post">
				<label for="uName">
					Username
				</label>
					<input id="uName" type="text" name="create_username" placeholder="Username">
				<label for="pass">
					Password
				</label>
					<input id="pass" type="text" name="create_password" placeholder="Password">
				<label for="date">
					Submission Date
				</label>
					<input id="date" type="text" name="submission_date" disabled>

					<a href="" ><input type="submit" name="submit" value="Create Account"></a>
			  </form>
		  	<!-- could create a hidden input filed and check if it gets filled to know if spam bot or real user -->
			
		<script type="text/javaScript"> 
				var now = new Date();
				var dd = now.getDate();
				var mm = now.getMonth() + 1;
				var yyyy = now.getFullYear();
				if(dd<10){
					dd = '0' + dd;
				}
				if(mm<10){
					mm = '0' + mm;
				}
				now = yyyy+'-'+mm+'-'+dd;
			document.getElementById('date').value = now;
		</script>
		
  </body>
</html> 