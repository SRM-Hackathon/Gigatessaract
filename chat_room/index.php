<?php include("config.php");include("login.php");?>
<!DOCTYPE html>
<html>
 <head>
  <script src="//code.jquery.com/jquery-latest.js"></script>
  <script src="chat.js"></script>
   <style type="text/css">

   body{
      background-image: url("bg.png");
      background-repeat: no-repeat;
    background-size: cover;
   }
  .chat .users, .chat .chatbox{
 display:inline-block;
 vertical-align:top;
 height:352px;
 padding:0px 15px;
 position:relative;
}
.chat .users{
 background:white;
 color:black;
 width:198px;
 overflow-y:auto;
 margin-top: 20px;
 margin-left: 470px;
}
.chat .chatbox{
 background:#fff;
 color:black;
 margin-left:40px;
 margin-top: 20px;
 width:370px;
}
.chat .chatbox .msgs{
 border-top:1px solid black;
 border-bottom:1px solid black;
 overflow-y:auto;
 height:270px;
}
.chat .chatbox #msg_form{
 padding-top:1.5px;
}

.chat .error{color:red;}
.chat .success{color:green;}
.chat .msgs .msg, .chat .users .user{border-bottom:1px solid black;padding:4px 0px;white-space:pre-line;word-break:break-word;}
</style>  <title>PHP Group Chat</title>
 </head>
 <body>
  <div id="content" style="margin-top:10px;height:100%;">
   <center><h1>Start Group Chat</h1></center>
   <div class="chat">
    <div class="users">
     <?php include("users.php");?>
    </div>
    <div class="chatbox">
     <?php
     if(isset($_SESSION['user'])){
      include("chatbox.php");
     }else{
      $display_case=true;
      include("login.php");
     }
     ?>
    </div>
   </div>
  </div>
 </body>
</html>
