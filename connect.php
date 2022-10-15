<?php

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','mybase');

$con =mysqli_connect(HOST,USER,PASS,DB);
$query = "SELECT * FROM `orders` ORDER BY order_id ASC";

$result = mysqli_query($con,$query);
$number_of_rows = mysqli_num_rows($result);

$response = array();

if($number_of_rows >0){
	while($row = mysqli_fetch_assoc($result)){
		$response[] = $row;
	}
}
header('Content-Type: application/json');
echo json_encode(array("result"=>$response));

mysqli_close($con);

?>