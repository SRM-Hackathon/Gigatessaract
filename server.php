<?php
	

	session_start();

	// require('connection.php');
	

	
	$nomatch = 0;
	$email_1="";
	$userName_1="";


	$emailComment="";
	$firstComment ="";
	$lastComment="";
	$pass1Comment ="";
	
	
	$emailClass='form-control';
	$firstClass='form-control';
	$lastClass='form-control';
	$pass2Class='form-control';
	$userClass='form-control';
	$pass1Class='form-control';
	$passClass='form-control';

	$titleClass='form-control';
	$contentClass='form-control';

	$cardClass='card text-white';

	$taskClass='form-control';
	$nameClass='form-control';
	$nameComment ="";

	$timeClass='form-control';
	$numberClass='form-control';
	$timeComment ="";
	$numberComment ="";
	$passComment = "";
	$comComment = "";
	$comClass='form-control';
		
	// if(isset($_GET['resname'])){
	// 	$resname = $_GET['resname'];
	// }
	



	//logout
	if(isset($_GET['exit'])){
		session_destroy();
		//echo $_SESSION['userName'];
		mysqli_close($conn);
		header('location: main.php');
		}



	

	$conn = mysqli_connect('localhost', 'root', '123456', 'hotel');
	
	//Check connection
	if(mysqli_connect_errno()){
		//Connection failed
		echo "Failed to connect to mysql".mysqli_connect_errno();
	}
	else{
		//connection made

		//if(filter_has_var(INPUT_POST, "submit")){
		if(isset($_POST['submit'])){
						//Get values if data exists
						$userComment ="";
						$pass2Comment ="";
						$count = 0;
						$userName = mysqli_real_escape_string($conn, $_POST['userName']);
						$userName = filter_var($userName, FILTER_SANITIZE_SPECIAL_CHARS);
						$email = mysqli_real_escape_string($conn, $_POST['email']);
						$password = mysqli_real_escape_string($conn, $_POST['password']);
						$password_2 = mysqli_real_escape_string($conn, $_POST['password_2']);
						$firstName = mysqli_real_escape_string($conn, $_POST['firstName']);
						$firstName = filter_var($firstName, FILTER_SANITIZE_SPECIAL_CHARS);
						$lastName = mysqli_real_escape_string($conn, $_POST['lastName']);
						$lastName = filter_var($lastName, FILTER_SANITIZE_SPECIAL_CHARS);
						$name = $firstName." ".$lastName;
						//$field =[$firstName, $lastName, $email, $userName, $password_2];

						if(empty($firstName)){
							$firstComment="First Name is Required!";
							$firstClass=$firstClass." "."is-invalid";

							}
						else{
							//if(!filter_var($firstName, FILTER_VALIDATE_REGEXP,array("options"=>array("regexp"=>"/^M(.*)/")))){
							//$firstName_1 = filter_var($firstName, FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
								if(ctype_alpha($firstName)){
									$firstClass=$firstClass." "."is-valid";
									$count++;
								}
								else{
									$firstComment="Invalid First Name";
									$firstClass=$firstClass." "."is-invalid";

								}
							}
								


						if(empty($lastName)){
							$lastComment="Last Name is Required!";
							$lastClass=$lastClass." "."is-invalid";

							}
						else{
								if(ctype_alpha($lastName)){
									$lastClass=$lastClass." "."is-valid";
									$count++;

								}
								else{
									$lastComment="Invalid Last Name";
									$lastClass=$lastClass." "."is-invalid";
								}
							}
						


						if(empty($email)){
							$emailComment="E-mail is Required!";
							$emailClass=$emailClass." "."is-invalid";

							}
						else{
							if(filter_var($email,FILTER_VALIDATE_EMAIL)){
								$emailClass=$emailClass." "."is-valid";
								$count++;
								}
								else{
									$email_1=filter_var($email, FILTER_SANITIZE_EMAIL);
									$emailComment="Invalid E-mail ID, try using ".$email_1;
									$emailClass=$emailClass." "."is-invalid";
								}
							}



						if(empty($userName)){
							$userComment="User Name is Required!";
							$userClass=$userClass." "."is-invalid";

							}
							else{
								$userName_1 = filter_var($userName, FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
								if(!strcmp($userName,$userName_1)){
									$query_1= "SELECT * FROM users WHERE username ='$userName'";
									$result_1 =mysqli_query($conn, $query_1);
									if(mysqli_num_rows($result_1)!=0)
									{	
										echo mysqli_num_rows($result_1);
										$userClass=$userClass." "."is-invalid";
										$userComment="User Name already Exists!";
									}else{
										$userClass=$userClass." "."is-valid";
										$count++;
									}
									mysqli_free_result($result_1);

								}
								else{
									$userComment="Invalid Username";
									$userClass=$userClass." "."is-invalid";
								}
							}


						if(empty($password)){
							$pass1Comment="Password is Required!";
							$pass1Class=$pass1Class." "."is-invalid";

							}
							else{
								$count++;
							}
							



						if(empty($password_2)){
							$pass2Comment="Re-enter your Password";
							$pass2Class=$pass2Class." "."is-invalid";

							}
							else{
								$count++;
							}
						if($password != $password_2){
							$pass2Comment="Passwords don't match !";
							$pass2Class=$pass2Class." "."is-invalid";

						}else{
							if($password != "")
								$count++;
						}
							
						


						if($count == 7){
							$password = md5($password_2);//Encrypt password
							$query = "INSERT INTO users(username,email,password,name,firstName,lastName) VALUES ('$userName','$email','$password','$name','$firstName','$lastName');";
							$result = mysqli_query($conn, $query);
							$_SESSION['userName'] = $userName;
							$_SESSION['login'] = 1;
							mysqli_free_result($result);
							header('location: main.php');
						}
						/*
									for($i=0;$i<5;$i++){
										if(empty($field[$i])){
										$comment[$i]=$fname[$i]." is Required!";
										$class[$i]=$class[$i]." "."is-invalid";
										

										}
										else{
										$class[$i]=$class[$i]." "."is-valid";
										}
									}
									print_r($comment);

						*/
		}

		else if(isset($_POST['signIn'])){


							$userComment ="";
							$pass2Comment ="";
							$nomatch = 0;
							$count = 0;
							$userName = mysqli_real_escape_string($conn, $_POST['userName']);
							$userName = filter_var($userName, FILTER_SANITIZE_SPECIAL_CHARS);
							$password = mysqli_real_escape_string($conn, $_POST['password']);
					

							

							if(empty($userName)){
								$userComment="User Name is Required!";
								$userClass=$userClass." "."is-invalid";

								}
							else{
								$userClass=$userClass." "."is-valid";
								$count++;
								}

								


							if(empty($password)){
								$pass1Comment="Password is Required!";
								$pass1Class=$pass1Class." "."is-invalid";

								}
							else{
								$count++;
								}
							

							

							if($count == 2){
								$password = md5($password);
								$query_1= "SELECT * FROM users WHERE username ='$userName' AND password ='$password';";
								$result_1 =mysqli_query($conn, $query_1);

								if(mysqli_num_rows($result_1) == 1){
									$_SESSION['userName'] = $userName;
									$_SESSION['login'] = 1;
									mysqli_free_result($result_1);
									header('location: main.php');

								}else{
									$nomatch = 1;
									
								}

							}
		

		}
		
		

	}


	




?>