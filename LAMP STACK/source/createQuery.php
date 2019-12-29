<html>
   <head>
      <title>Connecting MySQL Server</title>
   </head>
   <body>
      <?php
		if(isset($_POST["submit"])){ //since this file will be called again this first isset needs to used to make sure that SQL is not called with empty values
		$dbhost = "localhost";
		$dbuser = "root";
		$dbpass = "pass";
		$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
		if(!$conn){
			die("could not connect " . mysqli_connect_error());
		}
		echo "connected \n";
		mysqli_select_db($conn, "TUTORIALS");
		$tutorial_title = mysqli_real_escape_string($conn, $_POST["tutorial_title"]);
		$tutorial_author = mysqli_real_escape_string($conn, $_POST["tutorial_author"]);
		$submission_date = mysqli_real_escape_string($conn, $_POST["submission_date"]);
		$sql = "INSERT INTO tutorials_db(tutorial_title, tutorial_author, submission_date) ".
				"VALUES ('$tutorial_title', '$tutorial_author', '$submission_date')";
		
		$query = mysqli_query($conn, $sql);
		if(!$query){
			die("could not add data " . mysqli_error($conn));
		}
		echo "added data sucessfully";
		mysqli_close($conn);
		}
      ?>
	  
	  <form action="<?php $_SERVER['PHP_SELF']?>" method="post">
		<table width="600" border="0" >
			<tr>
				<td width="200">
					Tutorial Title
				</td>
				<td>
					<input type="text" name="tutorial_title">
				</td>
			</tr>
			<tr>
				<td width="200">
					Tutorial author
				</td>
				<td>
					<input type="text" name="tutorial_author">
				</td>
			</tr>
			<tr>
				<td width="200">
					Submission date [yy-mm-dd]
				</td>
				<td>
					<input type="text" name="submission_date">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submit" value="add data">
				</td>
			</tr>
		</table>
	  </form>
  </body>
</html> 