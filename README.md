Control the time you spend on phone calls or the time your employees spend calling. 


File addnotif.php
```

$temps=$_GET['temps'];
$user=$_GET['user'];
$date=$_GET['date'];

// attempt insert query execution
$sql = "INSERT INTO notif (`user`,`temps`,`date`) VALUES ('$user','$temps','$date')";
if(mysqli_query($con, $sql)){
    echo "success";
} else{
    echo "erreur";
}
 
// close connection
mysqli_close($con);
?>
```


File addtache.php
```

$temps=$_GET['temps'];
$user=$_GET['user'];
$iddocument=$_GET['iddocument'];
// attempt insert query execution
$sql = "INSERT INTO taches (`user`,`temps`,`iddocument`) VALUES ('$user','$temps','$iddocument')";
if(mysqli_query($con, $sql)){
    echo "success";
} else{
    echo "erreur";
}
 
// close connection
mysqli_close($con);
?>
```


getnotifs.php
```

$s="<table border='0'><tr><th>UserID</th><th>Temps</th><th>Date</th></tr>";

$sql0 = "SELECT user,temps,date FROM notif ";
$result = mysqli_query($con,$sql0);
 
while($row = mysqli_fetch_row($result))
{
    $s=$s."<tr><td>".$row[0]."</td><td>".$row[1]."</td><td>".$row[2]."</td></tr>";
}
$s=$s."</table></center>";
echo $s;



?>
```

gettaches.php
```

$id=$_GET['id'];
$return_arr = array();


$sql0 = "SELECT count(*) FROM taches WHERE user='$id' ";
$result0 = mysqli_query($con,$sql0);
 
while ($row = mysqli_fetch_array($result0, MYSQL_ASSOC)) {
    echo $row['count(*)'];
}



?>
```


getuser.php
```

$user=$_GET['username'];
$return_arr = array();


$sql0 = "SELECT * FROM user ";
$result0 = mysqli_query($con,$sql0);
 
while ($row = mysqli_fetch_array($result0, MYSQL_ASSOC)) {
    $row_array['id'] = $row['id'];
    $row_array['username'] = $row['username'];
    $row_array['password'] = $row['password'];
    
    array_push($return_arr,$row_array);
}

echo json_encode($return_arr);


?>
```
