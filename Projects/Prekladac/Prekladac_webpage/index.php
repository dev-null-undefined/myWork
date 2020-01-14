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
  if ($_GET['jazyk1'] == "Rozpoznat jazyk") {
    $select = 'SELECT
              toSlovo.slovo as var1,fromSlovo.slovo as var2
              FROM
              Preklad
              INNER JOIN slovo AS fromSlovo ON Preklad.IdSlovo1 = fromSlovo.Id
              INNER JOIN slovo AS toSlovo ON Preklad.IdSlovo2 = toSlovo.Id
              INNER JOIN jazyk AS fromJazyk ON fromSlovo.IdJazyk = fromJazyk.Id
              INNER JOIN jazyk AS toJazyk ON toSlovo.IdJazyk = toJazyk.Id
              WHERE
              (fromSlovo.Slovo = "' . strtolower($_GET['slovo1']) . '"
              AND toJazyk.Jazyk="' . $_GET['jazyk2'] . '")
              OR (toSlovo.Slovo = "' . strtolower($_GET['slovo1']) . '"
              AND fromJazyk.Jazyk="' . $_GET['jazyk2'] . '");';
  } else {
    $select = 'SELECT
              toSlovo.slovo as var1,fromSlovo.slovo as var2
              FROM
              Preklad
              INNER JOIN slovo AS fromSlovo ON Preklad.IdSlovo1 = fromSlovo.Id
              INNER JOIN slovo AS toSlovo ON Preklad.IdSlovo2 = toSlovo.Id
              INNER JOIN jazyk AS fromJazyk ON fromSlovo.IdJazyk = fromJazyk.Id
              INNER JOIN jazyk AS toJazyk ON toSlovo.IdJazyk = toJazyk.Id
              WHERE
              (fromSlovo.Slovo = "' . strtolower($_GET['slovo1']) . '" 
              AND fromJazyk.Jazyk="' . $_GET['jazyk1'] . '" 
              AND toJazyk.Jazyk="' . $_GET['jazyk2'] . '")
              OR (toSlovo.Slovo = "' . strtolower($_GET['slovo1']) . '"
              AND toJazyk.Jazyk="' . $_GET['jazyk1'] . '"
              AND fromJazyk.Jazyk="' . $_GET['jazyk2'] . '");';
  }
  // echo $select;
  // echo $select;
  $preklad = '';
  $selectResoult = mysqli_query($conn, $select);
  while ($row = mysqli_fetch_array($selectResoult)) {
    if ($row['var1'] == $_GET['slovo1']) {
      $preklad .= $row['var2'] . ", ";
    } else {
      $preklad .= $row['var1'] . ", ";
    }
  }
  echo '<body>
        <form action="index.php">
          <div class="levaStrana Strana">
            <textarea name="slovo1" rows="10" cols="30">'
    . $_GET['slovo1'] . '</textarea>
              <select name="jazyk1">
              <option value="Rozpoznat jazyk">Rozpoznat jazyk</option>';
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
      <textarea name="slovo2" rows="10" cols="30" disabled>' . $preklad . '</textarea>
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
    <input type="image" src="imgs/arrow.png" class="submit" alt="Submit" />
  </form>
  <a href="http://84.242.120.206:5554/Pridavani_slovicek_webpage/">Pridat Slova</a>
</body>';
} else {
  echo '<body>
  <form action="index.php">
    <div class="levaStrana Strana">
      <textarea name="slovo1" rows="10" cols="30"></textarea>
      <select name="jazyk1">
      <option value="Rozpoznat jazyk">Rozpoznat jazyk</option>';
  foreach ($Jazyky as $Jazyk) {
    echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
  }
  echo '</select>
    </div>
    <div class="pravaStrana Strana">
      <textarea name="slovo2" rows="10" cols="30" disabled></textarea>
      <select name="jazyk2">
      ';
  foreach ($Jazyky as $Jazyk) {
    echo '<option value="' . $Jazyk . '">' . $Jazyk . '</option>';
  }
  echo '
      </select>
    </div>
    <input type="image" src="imgs/arrow.png" class="submit" alt="Submit" />
  </form>
  <a href="http://84.242.120.206:5554/Pridavani_slovicek_webpage/">Pridat Slova</a>
</body>';
}
?>

</html>