<?php

date_default_timezone_set('Europe/Prague');
ini_set('display_errors', 1);
error_reporting(E_ALL);
$filterPoint = '';
if (isset($_GET['filterPoint'])) {
  $filterPoint = $_GET['filterPoint'];
}
?>
<html>
<head>
  <title>Airtracker  - data log (Sigfox)</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <meta charset="UTF-8">
</head>
<body>
<div class="box">
<div class="logo">
  <img src="logo.png" width="233px" height="66px" />
</div>
<div class="header">
  <h1>AirTracker</h1>
  <h2>Data received from sigfox</h2>
</div>
<div>
<form action="index.php" method="GET">
  <span>Point ID:<span>
  <input type="text" name="filterPoint" value="<?php echo $filterPoint; ?>" />
  <input type="submit" value="Set filter" />
</form>
</div>
<div class="legend">
  
<span class="high">Poslední odpověď starší než 30 minut.</span>
  </div>
<div class="datagrid" id="datagrid">
  <table>
      <tr>
        <th>Last time</th><th>Device ID</th><th>Point ID</th>
      </tr>
    <?php
     $conn = pg_connect("host=backup.blackies.net dbname=ubt user=ubt password=ubt")
       or die ('Could not connect: '. pg_last_error());
    $res = pg_query("select distinct sigfox_id, sensor_id, point_id, max(date) from master_log ml1 where date > CURRENT_DATE - INTERVAL '1 month' group by sigfox_id, sensor_id, point_id order by point_id");
    while ($line = pg_fetch_row($res, null, PGSQL_ASSOC)) {
      //                                     Date       Id        Sensor Id     Point Id
      if(strtotime($line['max']) < strtotime("-30 minutes")) {
        $class="high";
       }else{
         $class="low";
       }
        printf("<tr class=\"%s\"><td>%s</td><td>%s (Master %s)</td><td>%s</td></tr>\n",
         $class,date("d.m.Y H:i:s", strtotime($line['max'])),  $line['sigfox_id'], str_pad($line['sensor_id'], 2, "0", STR_PAD_LEFT), $line['point_id']);
    }
    pg_free_result($res);
    pg_close($conn);
    ?>
  </table>
 </div>
</div>
</body>
</html>
