<?php
// ini_set('display_errors', 1);
//var_dump($_GET['input']);
$Fields = array();
$conn =  new mysqli("localhost", "Pi", "hesloProPiDoDatabaze", "Pi", 5456);
$select;
function isNullorNotSet($var){
  return isset($var)&&$var!="";
}
if (isNullorNotSet($_GET['from'])) {
  if (isNullorNotSet($_GET['to'])) {
    $select = 'select * from Zaznam where Time>"' . str_replace("T", " ", $_GET['from']) . '" AND Time<"' . str_replace("T", " ", $_GET['to']).'"';
  } else {
    $select = 'select * from Zaznam where Time>"' . str_replace("T", " ", $_GET['from']).'"';
  }
} else {
  if (isNullorNotSet($_GET['to'])) {
    $select = 'select * from Zaznam where Time>"' . str_replace("T", " ", $_GET['to']).'"';
  } else {
    $select = "select * from Zaznam";
  }
}
// var_dump($select);
$selectResoult = mysqli_query($conn, $select);
$get_info = mysqli_fetch_assoc($selectResoult);

//var_dump($selectResoult);
foreach (array_keys($get_info) as $columnName) {
  array_push($Fields, $columnName);
}
$FilterSetted = true;
$selectResoult = mysqli_query($conn, $select);
if (isset($_GET['input']) && is_array($_GET['input'])) {
  $FilterFields = $_GET['input'];
} else {
  $FilterFields = array();
  $FilterSetted = false;
}
if (isset($_GET['onlyData']) && $_GET['onlyData'] == "true") {
  echo '<div class="datagrid" id="datagrid">
    <table>';
  if (!$FilterSetted) {
    printf('<thead class="tableHeader">
    <tr>');
    foreach ($Fields as $field) {
      printf("<td>%s</td>", $field);
    }
    echo '</tr>';
    printf('</thead>
    <tbody>');
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      foreach ($Fields as $collom) {
        printf("<th>%s</th>", $row[$collom]);
      }
      echo '</tr>';
    }
    echo '</tbody></table></div>';
  } else {
    printf('<thead class="tableHeader">
    <tr>');
    for ($i = 0; $i < count($FilterFields); $i++) {
      printf("<th>%s</th>", $FilterFields[$i]);
    }
    echo '</tr>';
    printf('</thead>
    <tbody>');
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      for ($i = 0; $i < count($FilterFields); $i++) {
        printf("<td>%s</td>", $row[$FilterFields[$i]]);
      }
      echo '</tr>';
    }
    echo '</tbody></table></div>';
  }
  exit();
}
?>
<html>

<head>
  <title>Data log</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="./p5.min.js"></script>
  <script src="script.js" charset="utf-8"></script>
</head>

<body>
  <?php
  echo '<form action="index.php">
  <div class="dropdown">
  <button onclick="toggleDropDown()" type="button" class="dropbtn">Filter</button>
  <div id="myDropdown" class="dropdown-content">';
  foreach ($Fields as $fieldName) {
    if (in_array($fieldName, $FilterFields)) {
      printf('<label class="myInput"><input type="checkbox" name="input[]" value="%s" checked/>%s</label><br/>', $fieldName, $fieldName);
    } else {
      printf('<label class="myInput"><input type="checkbox" name="input[]" value="%s" />%s</label><br/>', $fieldName, $fieldName);
    }
  }
  echo ' <input type="submit" value="Submit" class="submit"/>
<button type="button" onclick="resetInputs()" class="submit">Reset Input</button> 
</div>
</div>';
echo '<button type="button" onclick="togleGraphView()" class="dropbtn">Togle Graph</button>';
echo '<button type="button" onclick="togleRefrash()" class="dropbtn red">Auto refrash</button>';
if (isNullorNotSet($_GET['from'])) {
  echo '<input type="datetime-local" name="from" value="' . $_GET['from'] . '">
        <input type="datetime-local" name="to"  value="' . $_GET['to'] . '">
        <input type="submit" value="Submit" class="submit"/>
      </form>';
} else {
  echo '<input type="datetime-local" name="from">
        <input type="datetime-local" name="to">
        <input type="submit" value="Submit" class="submit"/>
      </form>';
}
echo '<div class="datagrid" id="datagrid">
  <table>';
  if (!$FilterSetted) {
    printf('<thead class="tableHeader">
    <tr>');
    foreach ($Fields as $field) {
      printf("<th>%s</th>", $field);
    } 
    echo '</tr>';
    printf('</thead>
    <tbody>');
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      foreach ($Fields as $collom) {
        printf("<td>%s</td>", $row[$collom]);
      }
      echo '</tr>';
    }
    echo '</tbody></table></div>';
  } else {
    printf('<thead class="tableHeader">
    <tr>');
    for ($i = 0; $i < count($FilterFields); $i++) {
      printf("<th>%s</th>", $FilterFields[$i]);
    }
    echo '</tr>';
    printf('</thead>
    <tbody>');
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      for ($i = 0; $i < count($FilterFields); $i++) {
        printf("<td>%s</td>", $row[$FilterFields[$i]]);
      }
      echo '</tr>';
    }
    echo '</tbody></table></div>';
  }
 
  ?>
</body>

</html>