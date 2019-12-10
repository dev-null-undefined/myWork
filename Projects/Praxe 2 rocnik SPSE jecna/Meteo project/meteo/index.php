<?php
  date_default_timezone_set('Europe/Prague');
  ini_set('display_errors', 1);
  error_reporting(E_ALL);
  
  $filterSensor = '';
  if (isset($_GET['filterSensor'])) {
    $filterSensor = $_GET['filterSensor'];	
  }
?>
<html>
<head>
  <title>Meteo  - data log (Sigfox)</title>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="box">
<div class="logo">
  <img src="logo.png" width="233px" height="66px" />
</div>
<div class="header">
  <h1>Meteo</h1>
  <h2>Data received from sigfox</h2>
</div>
<div>
<form action="index.php" method="GET">
  <span>Sensor ID:<span>
  <input type="text" name="filterSensor" value="<?php echo $filterSensor; ?>" />
  <input type="submit" value="Set filter" />
</form>
</div>
<div class="datagrid" id="datagrid">
  <table>
      <tr>
        <th>Date and time</th><th>Device ID</th><th>Decoded message</th>
      </tr>
    <?php
    $conn = pg_connect("host=backup.blackies.net dbname=ubt user=ubt password=ubt")
       or die ('Could not connect: '. pg_last_error());

    $res = pg_query("select * from meteo_log where date > CURRENT_DATE - INTERVAL '1 month' and sensor_id like '" . $filterSensor . "%' order by date desc");
    while ($line = pg_fetch_row($res, null, PGSQL_ASSOC)) {
     
      printf("<tr><td>%s</td><td>%s (Master %s)</td><td>Wind sped: <b>%.2fm/s</b>; Wind direction: <b>%.1f&deg;</b>; Pressure: <b>%.1fhPa</b>; Temperature: <b>%.2f&deg;C</b></td></tr>\n", 
	      date("d.m.Y H:i:s", strtotime($line['date'])),  $line['sigfox_id'], str_pad($line['sensor_id'], 2, "0", STR_PAD_LEFT),
	      $line['wind_speed'], $line['wind_direction'], $line['press'], $line['temperature']);

    }
    pg_free_result($res);
    pg_close($conn);
    ?>
  </table>
 </div>
</div>
</body>
</html>
