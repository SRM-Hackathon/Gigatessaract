<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>


<?php
include("config.php");
if(isset($_SESSION['user'])){
?>

    <h1>Chat Room</h1>      

 <a style="right: 20px;top: 30px;position: absolute;cursor: pointer; " href="logout.php">Log Out</a>
 <div class='msgs'>
  <?php include("msgs.php");?>
 </div>
 <br>
 <form id="msg_form">
  <input name="msg" size="30" type="text"/>
  <button style="background-color:#008CBA;">Send</button>
 </form>
<?php 
}
?>