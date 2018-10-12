
<!DOCTYPE html>

<html>

<head>

<title>My Friends</title>
  <style type="text/css">
    
* {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}
body {
  font-size: 120%;
    background-image: url("bg.png");
    background-repeat: no-repeat;
    background-size: cover;
}
.btn {
  padding: 10px;
  font-size: 15px;
  color: white;
  background: #5F9EA0;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
        </style>
</head>

<body>

<br>

<?php

session_start();

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "srmhack";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);


$sql="SELECT * FROM friends WHERE status='active'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {

echo "<b><center>Active Friends:</center></b> <br><br>";

echo "<center><table border='2' cellpadding='25'></center>";

echo "<tr>  <th>  Name  </th>  <th>  Profile  </th><th>  Status  </th><th></th> </tr>";
    // output data of each row
    while($row = $result->fetch_assoc()) {
        // echo out the contents of each row into a table

echo "<tr>";


echo '<td>' . $row['tos'] . '</td>';

echo '<td><a href="profile.php?id=' . $row['id'] . '"><img src="prof.jpg" alt="icon" /></a></td>';

echo '<td>' . $row['status'] . '</td>';



echo "</tr>";
    }}
     else {
    echo "<center>0 results</center>";
}

$conn->close();

// close table>

echo "</table>";
?>
</body>
</html>

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


 <?php

if(!isset($_SESSION)){ 
    session_start(); 
}

if(!isset($_SESSION["username"])){
	header('Location: login.php');
	exit();
}

include_once("connect.php");
$username = $_SESSION["username"];

$sql = "USE srmhack;";
$conn->query($sql);

$tableName = "friends";

$sql = "CREATE TABLE IF NOT EXISTS $tableName(
		id INT(11) NOT NULL AUTO_INCREMENT,
		froms VARCHAR(100) NOT NULL,
		tos VARCHAR(100),
		status VARCHAR(500),
		PRIMARY KEY (id)
		)";
$result = $conn->query($sql);

if (!$result) {
	trigger_error('Invalid query: ' . $conn->error);
}	

?>

