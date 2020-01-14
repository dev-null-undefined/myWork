<?php
if (isset($_GET['jazyk'])) {
    $conn =  new mysqli("localHost", "prekladac", "1234", "prekladac");
    $sql = 'insert into jazyk (Jazyk) values ("' . $_GET['jazyk'] . '");';
    $conn->query($sql);
    // echo $sql;
}
header("Location: http://84.242.120.206:5554/Pridavani_slovicek_webpage/"); 
