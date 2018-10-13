<?php

function spellcheck($string)   //Microsoft BING API
{

$host = 'https://api.cognitive.microsoft.com';
$path = '/bing/v7.0/spellcheck?';
$params = 'mkt=en-us&mode=proof';

$data = array (
    'text' => urlencode ($string)
);

$key = '3b9dff4665a240de901b9a6ff56adffa';



$headers = "Content-type: application/x-www-form-urlencoded\r\n" .
    "Ocp-Apim-Subscription-Key: $key\r\n";

$options = array (
    'http' => array (
        'header' => $headers,
        'method' => 'POST',
        'content' => http_build_query ($data)
    )
);
$context  = stream_context_create($options);
$result = file_get_contents($host . $path . $params, false, $context);

if($result === FALSE) {
    /* Handle error */
}
$response = array("", "");
$json = json_encode(json_decode($result), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
return $json;
// $response[1] =  $json;
// $obj = json_decode($json);
// $split = explode(" ", $string);

// foreach ($obj.flaggedTokens as $key => $value) {

// 	 $in = $obj.flaggedTokens[$key];
// 	 $split[$in] = $obj.flaggedTokens[$i]
// }
// $response[0] = 

}




?>