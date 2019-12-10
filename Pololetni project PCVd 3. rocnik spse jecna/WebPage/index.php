<?php
$Fields = array();
$conn =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin_skola");
$select = "select * from Report join Senzor on Senzor.id=Report.Senzor_id;";
$selectResoult = mysqli_query($conn, $select);
$fieldNumber = mysqli_num_fields($selectResoult);
$FilterFields = array();
$get_info = mysqli_fetch_assoc($selectResoult);
foreach (array_keys($get_info) as $columnName) {
  array_push($Fields, $columnName);
}
$selectResoult = mysqli_query($conn, $select);
while ($fieldNumber > 0) {
  $fieldNumber--;
  if (isset($_GET[sprintf("input%d", $fieldNumber)])) {
    array_push($FilterFields, $_GET[sprintf("input%d", $fieldNumber)]);
  }
}
?>
<html>

<head>
  <title>Data log</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="script.js" charset="utf-8"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/0.7.3/p5.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/0.7.3/addons/p5.dom.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/0.7.3/addons/p5.sound.min.js"></script>
</head>

<body>
  <?php
  echo '<div class="dropdown">
  <button onclick="toggleDropDown()" class="dropbtn">Filter</button>
  <div id="myDropdown" class="dropdown-content">
    <form action="index.php">';
  foreach ($Fields as $fieldName) {
    if (in_array($fieldName, $FilterFields)) {
      printf('<label class="myInput"><input type="checkbox" name="input[]" value="%s" checked/>%s</label><br/>', $fieldName, $fieldName);
    } else {
      printf('<label class="myInput"><input type="checkbox" name="input[]" value="%s" />%s</label><br/>', $fieldName, $fieldName);
    }
  }
  echo ' <input type="submit" value="Submit" class="submit"/>
</form>
</div>
</div>
<div class="datagrid" id="datagrid">
  <table>
      <tr>';
  if (count($FilterFields) == 0) {
    foreach ($Fields as $field) {
      printf("<th>%s</th>", $field);
    }
    echo '</tr>';
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      foreach ($Fields as $collom) {
        printf("<th>%s</th>", $row[$collom]);
      }
      echo '</tr>';
    }
  } else {
    for ($i = 0; $i < count($FilterFields); $i++) {
      printf("<th>%s</th>", $FilterFields[$i]);
    }
    echo '</tr>';
    while ($row = mysqli_fetch_array($selectResoult)) {
      echo '<tr>';
      for ($i = 0; $i < count($FilterFields); $i++) {
        printf("<th>%s</th>", $row[$FilterFields[$i]]);
      }
      echo '</tr>';
    }

    echo '</table></div>';
  }
  echo '<button onclick="togleGraphView()" class="dropbtn">Togle Graph</button>';
  ?>
</body>

</html>