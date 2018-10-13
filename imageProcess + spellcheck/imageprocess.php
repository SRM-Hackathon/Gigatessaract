<?php
require_once 'spelling.php';

$string = 'VS-RAHUL-LINK.jpg';
$fbool = $_POST['flip'];
$ext = $_POST['extension'];
if($ext=='jpeg')
$im = imagecreatefromjpeg($string);
else if($ext=='png')
$im = imagecreatefrompng($string);
if($_GET['flip']==1)
imageflip($im, IMG_FLIP_HORIZONTAL);

if($im && imagefilter($im, IMG_FILTER_GRAYSCALE) && imagefilter($im, IMG_FILTER_CONTRAST))
{
    //ALl done. 
}
else
{
    echo 'Preprocessing failed';  //perhaps try again with better lighting?????
}
$str = '';
if($ext == 'jpeg')
{
	exec(tesseract imagejpeg($im) $str);
}
else if($ext == 'jpeg')
exec(tesseract imagepng($im) $str);
$ext = '';

$response = spellcheck($str);
//spellcheck string



?>
