<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <link rel="stylesheet" href="style.css" />
  <title>Document</title>
</head>
<?php
$Jazyky = array();
$conn =  new mysqli("localHost", "prekladac", "1234", "prekladac");
$select = "select Jazyk from jazyk;";
$selectResoult = mysqli_query($conn, $select);
while ($row = mysqli_fetch_array($selectResoult)) {
  array_push($Jazyky, $row['Jazyk']);
}
if (isset($_GET['jazyk1'])) {
  $select = 'SELECT Id FROM jazyk where jazyk.Jazyk="' . $_GET['jazyk1'] . '";';
  $selectResoult = mysqli_query($conn, $select);
  $row = mysqli_fetch_array($selectResoult);
  $jazyk1ID = $row['Id'];
  $select = 'SELECT Id FROM jazyk where jazyk.Jazyk="' . $_GET['jazyk2'] . '";';
  $selectResoult = mysqli_query($conn, $select);
  $row = mysqli_fetch_array($selectResoult);
  $jazyk2ID = $row['Id'];
  // echo $select;

  $slovo1Id;
  $select = 'SELECT  slovo.Id as Id FROM  slovo
  INNER join jazyk on slovo.IdJazyk=jazyk.Id
  where slovo.Slovo="' . $_GET['slovo1'] . '" and jazyk.Jazyk="' . $_GET['jazyk1'] . '";';
  $selectResoult = mysqli_query($conn, $select);
  if (mysqli_num_rows($selectResoult) > 0) {
    $row = mysqli_fetch_array($selectResoult);
    $slovo1Id = $row['Id'];
  } else {
    $conn->query("insert into slovo (IdJazyk,Slovo) values (" . $jazyk1ID . ",'" . $_GET['slovo1'] . "');");
    $selectResoult = mysqli_query($conn, $select);
    $row = mysqli_fetch_array($selectResoult);
    $slovo1Id = $row['Id'];
  }


  $slovo2Id;
  $select = 'SELECT  slovo.Id as Id  FROM  slovo
  INNER join jazyk on slovo.IdJazyk=jazyk.Id
  where slovo.Slovo="' . $_GET['slovo2'] . '" and jazyk.Jazyk="' . $_GET['jazyk2'] . '";';
  $selectResoult = mysqli_query($conn, $select);
  // echo $select;
  if (mysqli_num_rows($selectResoult) > 0) {
    $row = mysqli_fetch_array($selectResoult);
    $slovo2Id = $row['Id'];
  } else {
    $conn->query("insert into slovo (IdJazyk,Slovo) values (" . $jazyk2ID . ",'" . $_GET['slovo2'] . "');");
    $selectResoult = mysqli_query($conn, $select);
    $row = mysqli_fetch_array($selectResoult);
    $slovo2Id = $row['Id'];
  }


  // echo "insert into Preklad (IdSlovo1,IdSlovo2) VALUES (".$slovo1Id.",".$slovo2Id.");";
  $select = 'SELECT * FROM  Preklad
  where Preklad.IdSlovo1="' . $slovo1Id . '" and Preklad.IdSlovo2="' . $slovo2Id . '";';
  $selectResoult = mysqli_query($conn, $select);
  // echo $select;
  if (mysqli_num_rows($selectResoult) > 0) {
    echo "<h1> TO UZ TU JE MORE</h1>";
  } else {
    $conn->query("insert into Preklad (IdSlovo1,IdSlovo2) VALUES (" . $slovo1Id . "," . $slovo2Id . ");");
  }
  echo '<body>
  <form action="index.php">
    <div class="levaStrana Strana">
      <textarea name="slovo1" rows="10" cols="30">'
    . $_GET['slovo1'] . '</textarea>
      <select name="jazyk1">';
  foreach ($Jazyky as $Jazyk) {
    if ($Jazyk == $_GET['jazyk1']) {
      echo '<option value="' . $Jazyk . '" selected>' . $Jazyk . '</option>';
    } else {
      echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
    }
  }
  echo '</select>
    </div>
    <div class="pravaStrana Strana">
      <textarea name="slovo2" rows="10" cols="30">' . $_GET['slovo2'] . '</textarea>
      <select name="jazyk2">
      ';
  foreach ($Jazyky as $Jazyk) {
    if ($Jazyk == $_GET['jazyk2']) {
      echo '<option value="' . $Jazyk . '" selected>' . $Jazyk . '</option>';
    } else {
      echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
    }
  }
  echo '
      </select>
    </div>
    <input type="submit" value="Submit" />
  </form>
  <form action="post.php">
      <input type="text" name="jazyk">
      <input type="submit" value="Pridat jazyk">
    </form>
    <a href="http://84.242.120.206:5554/Prekladac_webpage/">Slovnicek</a>
</body>';
} else {
  echo '<body>
  <form action="index.php">
    <div class="levaStrana Strana">
      <textarea name="slovo1" rows="10" cols="30"></textarea>
      <select name="jazyk1">';
  foreach ($Jazyky as $Jazyk) {
    echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
  }
  echo '</select>
    </div>
    <div class="pravaStrana Strana">
      <textarea name="slovo2" rows="10" cols="30"></textarea>
      <select name="jazyk2">
      ';
  foreach ($Jazyky as $Jazyk) {
    echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
  }
  echo '
      </select>
    </div>
    <input type="submit" value="Submit" />
  </form>
  <form action="post.php">
      <input type="text" name="jazyk">
      <input type="submit" value="Pridat jazyk">
    </form>
    
  <a href="http://84.242.120.206:5554/Prekladac_webpage/">Slovnicek</a>
</body>';
}
?>

</html>