<?php

date_default_timezone_set('Europe/Prague');
ini_set('display_errors', 1);
error_reporting(E_ALL);
$filterPoint = '';
if (isset($_GET['filterPoint'])) {
  $filterPoint = $_GET['filterPoint'];
}
$page=1;
if(isset($_GET['page'])){
  $page=(int) $_GET['page'];
}
?>
<html>
<head>
  <title>Airtracker  - data log (Sigfox)</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="index.js" charset="utf-8"></script>
  <link rel='shortcut icon' type='image/x-icon' href='favicon.ico'/>
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
  <span class="high">VOC value &gt; 300</span>, <span class="ok">VOC value &gt; 250</span>, <span class="low">VOC value &gt; 0 and &lt; 250</span>
</div>
<div class="buttons">
<?php 
if($page!=1){
  printf("<button onclick=backPage()>Back</button>");
}else{
  printf("<button onclick=backPage() disabled>Back</button>");
}
?>
<?php
$conn = pg_connect("host=backup.blackies.net dbname=ubt user=ubt password=ubt")
or die ('Could not connect: '. pg_last_error());
$res = pg_query("select * from master_log where date > CURRENT_DATE - INTERVAL '1 month' and point_id like '" . $filterPoint . "%' order by date desc limit " . (($page+1)*1000) . " offset " . ($page)*1000);
if(pg_num_rows($res)>0){
  printf("<button onclick=nextPage()>Next</button>");
}else{
  printf("<button onclick=nextPage() disabled>Next</button>");
}
pg_free_result($res);
?>
</div>
<div class="datagrid" id="datagrid">
  <table>
      <tr>
        <th>Date and time</th><th>Device ID</th><th>Point ID</th><th>Decoded message</th>
      </tr>
    <?php
        $res = pg_query("select * from master_log where date > CURRENT_DATE - INTERVAL '1 month' and point_id like '" . $filterPoint . "%' order by date desc limit " . ($page*1000) . " offset " . ($page-1)*1000);
       while ($line = pg_fetch_row($res, null, PGSQL_ASSOC)) {;
      $voc = floatval($line['voc']);
      if ($voc == 11342 |$voc == 0 |$voc == 62744) {
        $class="";
      } else if ($voc > 300) {
        $class="high";
      } else if ($voc > 250) {  
        $class="ok";
      } else {
       $class="low";
      } 
    
      $voc = "";
      if ($line['voc'] != "0") $voc = sprintf("; VOC: %s ppb", $line['voc']);
        printf("<tr class=\"%s\"><td>%s</td><td>%s (Master %s)</td><td>%s</td><td>Noise AVG: %s db; Noise MAX: %s dB; Dust 1: %s &micro;g/m3; Dust 2,5: %s &micro;g/m3; Dust 10: %s &micro;g/m3%s</td></tr>\n", 
         $class, date("d.m.Y H:i:s", strtotime($line['date'])),  $line['sigfox_id'], str_pad($line['sensor_id'], 2, "0", STR_PAD_LEFT), $line['point_id'], $line['noise_avg'], $line['noise_max'], $line['dust_1'], $line['dust_2_5'], $line['dust_10'], $voc);
    }
    pg_free_result($res);
    pg_close($conn);
    ?>
  </table>
 </div>
</div>
</body>
</html>