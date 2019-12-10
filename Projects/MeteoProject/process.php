<?php

date_default_timezone_set('Europe/Prague');
ini_set('display_errors', 1);
error_reporting(E_ALL);

$SERVER_URL = $_SERVER['SERVER_NAME'];
$SNOG_REQUEST_URL = sprintf("https://%s/sn/gauge", $SERVER_URL);

$NOISE_MAX_ID = '0012'; // SNOG sensor ID (hex)
$DUST1_ID = '0013'; // SNOG sensor ID (hex)
$DUST2_5_ID = '0014'; // SNOG sensor ID (hex)
$DUST10_ID = '0015'; // SNOG sensor ID (hex)
$VOC_ID = '0005'; // SNOG sensor ID (hex)

function hextobin($hexstr) {
  $n = strlen($hexstr);
  $sbin="";
  $i=0;
  while($i<$n) {
    $a =substr($hexstr,$i,2);
    $c = pack("H*",$a);
    if ($i==0){
        $sbin=$c;
    } else {
      $sbin.=$c;
    }
    $i+=2;
  }
  return $sbin;
}

function floatToBin($value) {
  $bin = '';
  $packed = pack('f', $value); //'f' for 32 bit
  foreach(str_split(strrev($packed)) as $char) {
    $bin .= str_pad(decbin(ord($char)), 8, 0, STR_PAD_LEFT);
  }
  return $bin;
}

function getSensorId($data) {
  if ($data == "19DFE9") return 1; // 01
  if ($data == "19DFEA") return 2; // 02
  if ($data == "19DFEB") return 3; // 03
  if ($data == "19DFDD") return 4; // 04
  if ($data == "19DF0B") return 5; // 05
  if ($data == "19DEDC") return 6; // 06
  if ($data == "19DFEF") return 7; // 07
  if ($data == "19DFF0") return 8; // 08
  if ($data == "19DFF1") return 9; // 09
  if ($data == "19DFF2") return 10; // 10
  if ($data == "19DF0A") return 11; // 11
  if ($data == "19DFE7") return 12; // 12
  if ($data == "19DFE1") return 13; // 13
  if ($data == "19DFE3") return 14; // 14
  if ($data == "19DFE8") return 15; // 15
  if ($data == "19DFDF") return 16; // 16
  if ($data == "19DFE0") return 17; // 17
  if ($data == "19DFE4") return 18; // 18
  if ($data == "19DFE6") return 19; // 19
  if ($data == "19DFDC") return 20; // 20
  if ($data == "19DFDA") return 21; // 21
  if ($data == "19DF04") return 22; // 22
  if ($data == "19DFD8") return 23; // 23
  if ($data == "19DFD7") return 24; // 24
  if ($data == "19DFD6") return 25; // 25
  if ($data == "19DEDD") return 26; // 26
  if ($data == "19DFD4") return 27; // 27
  if ($data == "19DFD3") return 28; // 28
  if ($data == "19DFD2") return 29; // 29
  if ($data == "19DF02") return 31; // 31 
  if ($data == "19DF03") return 32; // 32
  if ($data == "19DF01") return 33; // 33
  if ($data == "19DF00") return 34; // 34
  if ($data == "2F96D3") return 101; // 101
  if ($data == "2F97B0") return 102; // 102
  if ($data == "2F97F0") return 103; // 103
  if ($data == "2F96D4") return 104; // 104
  if ($data == "2F96D9") return 105; // 105
  if ($data == "2F97BD") return 106; // 106
  if ($data == "2F9831") return 107; // 107
  if ($data == "2F97DF") return 108; // 108
  if ($data == "2F9773") return 109; // 109
  if ($data == "2F982E") return 110; // 110
  if ($data == "2F974A") return 111; // 111
  if ($data == "2F975A") return 112; // 112
  if ($data == "2F97B1") return 113; // 113
  if ($data == "2F9759") return 114; // 114
  if ($data == "2F9778") return 115; // 115
  if ($data == "2F97EF") return 116; // 116
  if ($data == "2F97F1") return 118; // 118
  if ($data == "2F97C0") return 119; // 119
  if ($data == "2F97F2") return 120; // 120
  if ($data == "2F9748") return 121; // 121
  if ($data == "2F9851") return 122; // 122
  if ($data == "2F970B") return 123; // 123
  if ($data == "2F985D") return 124; // 124
  if ($data == "2F97B3") return 125; // 125
  if ($data == "2F9852") return 126; // 126
  if ($data == "2F9852") return 127; // 127
  if ($data == "2F97D0") return 128; // 128
  if ($data == "2F9708") return 129; // 129
  if ($data == "2F97E0") return 130; // 130
  if ($data == "2F9754") return 131; // 131
  if ($data == "2F9764") return 132; // 132
  if ($data == "2F97B2") return 133; // 133
  if ($data == "2F97C3") return 134; // 134
  if ($data == "2F9858") return 135; // 135
  if ($data == "2F97D3") return 136; // 136
  if ($data == "2F97D6") return 137; // 137
  if ($data == "2F9830") return 138; // 138
  if ($data == "2F985E") return 139; // 139
  if ($data == "2F97C2") return 140; // 140
  if ($data == "2F9749") return 141; // 141
  if ($data == "2F984C") return 142; // 142
  if ($data == "2F970A") return 143; // 143
  if ($data == "2F984F") return 144; // 144
  if ($data == "2F9769") return 145; // 145
  if ($data == "2F970A") return 146; // 146
  if ($data == "2F97E1") return 147; // 147
  if ($data == "2F97E6") return 148; // 148
  if ($data == "2F9779") return 149; // 149
  if ($data == "2F976A") return 150; // 150
  if ($data == "2F982F") return 999; // 999
  return 0; // others
}

function getPointId($sensorId) {
  if ($sensorId == 1) return 301;
  if ($sensorId == 2) return 0;
  if ($sensorId == 3) return 0;
  if ($sensorId == 4) return 0;
  if ($sensorId == 5) return 0;
  if ($sensorId == 6) return 0;
  if ($sensorId == 7) return 307;
  if ($sensorId == 8) return 308;
  if ($sensorId == 9) return 309;
  if ($sensorId == 10) return 310;
  if ($sensorId == 11) return 0;
  if ($sensorId == 12) return 312;
  if ($sensorId == 13) return 0;
  if ($sensorId == 14) return 0;
  if ($sensorId == 15) return 303;
  if ($sensorId == 16) return 311;
  if ($sensorId == 17) return 304;
  if ($sensorId == 18) return 306;
  if ($sensorId == 19) return 302;
  if ($sensorId == 20) return 305;
  if ($sensorId == 28) return 401;
  if ($sensorId == 29) return 402;
  if ($sensorId == 32) return 0;
  if ($sensorId == 33) return 0;
  if ($sensorId == 34) return 0;

  if ($sensorId == 124) return 601;
  if ($sensorId == 102) return 602;
  if ($sensorId == 103) return 603;
  if ($sensorId == 125) return 604;
  if ($sensorId == 105) return 605;
  if ($sensorId == 106) return 606;
  if ($sensorId == 107) return 607;
  if ($sensorId == 108) return 608;
  if ($sensorId == 109) return 609;
  if ($sensorId == 110) return 610;
  if ($sensorId == 111) return 611;
  if ($sensorId == 112) return 612;
  if ($sensorId == 113) return 613;
  if ($sensorId == 114) return 614;
  if ($sensorId == 115) return 615;
  if ($sensorId == 116) return 616;
  if ($sensorId == 123) return 617;
  if ($sensorId == 118) return 618;
  if ($sensorId == 122) return 619;
  if ($sensorId == 120) return 620;
  if ($sensorId == 121) return 621;

  return 0;
}

function useVOC($pointId) {
  if ($pointId == 0) return true; // testovaci desky
  if ($pointId == 401 || $pointId == 402) return true; // testovaci desky
  if ($pointId == 555 || $pointId == 556) return true; // testovaci desky (vystava)
  if ($pointId == 303 || $pointId == 305 || $pointId == 307 || $pointId == 310) return true; // Litomerice s VOC
  if ($pointId >= 600 && $pointId < 699) return true;
  return false; // Litomerice bez VOC
}

function mustCorrectVOC($pointId) {
  if ($pointId == 0) return false;
  if ($pointId == 303 || $pointId == 305 || $pointId == 307 || $pointId == 310) return true; // Litomerice s VOC
  return false; // nelogujeme do SNOGu
}

if(isset($_GET['time'])) {
  $time = date("d.m.Y H:i:s");
} else {
  $time = date("d.m.Y H:i:s", $_GET['time']);
}

$dt = new DateTime($time, new DateTimeZone('Europe/Berlin'));
$dt->setTimezone(new DateTimeZone('UTC'));

$noiseAvg = (hexdec(substr($_GET['data'], 0, 2))) / 4 + 50;
if ($noiseAvg == 102.25 || $noiseAvg == 50) $noiseAvg = 0;
$arr = unpack('H*',pack('f', $noiseAvg));
$noiseAvgHex = array_shift($arr);
//$noiseAvgHex = floatToBin($noiseAvg);

$noiseMax = (hexdec(substr($_GET['data'], 2, 2))) / 4 + 50;
if ($noiseMax == 102.25 || $noiseMax == 50) $noiseMax = 0;
$arr = unpack('H*',pack('f', $noiseMax));
$noiseMaxHex = array_shift($arr);
//$noiseMaxHex = floatToBin($noiseMax);

$dust1 = hexdec(substr($_GET['data'], 4, 4));
$arr = unpack('H*',pack('f', $dust1));
$dust1Hex = array_shift($arr);

$dust2_5 = hexdec(substr($_GET['data'], 8, 4));
$arr = unpack('H*',pack('f', $dust2_5));
$dust2_5Hex = array_shift($arr);

$dust10 = hexdec(substr($_GET['data'], 12, 4));
$arr = unpack('H*',pack('f', $dust10));
$dust10Hex = array_shift($arr);

$voc = hexdec(substr($_GET['data'], 16, 4));
$arr = unpack('H*',pack('f', $voc));
$vocHex = array_shift($arr);

$sensorId = getSensorId($_GET['id']);
$pointId = getPointId($sensorId);
$pointBinary = hextobin(str_pad(dechex($pointId), 4, '0', STR_PAD_LEFT));

if (useVOC($pointId, $voc)) {
  if (mustCorrectVOC($pointId)) {
    $voc = $voc / 1.6;
  }
  if ($voc > 2500) {
    $vocStr = '';
  } else {
    $vocStr = ';VOC: ' . $voc . 'ppb';
  }
} else {
  $vocStr = '';
}

$humanData = 'Noise AVG: '. $noiseAvg . ' dB; Noise MAX: '. $noiseMax . ' dB; Dust 1: ' . $dust1 . ' &micro;g/m3; Dust 2,5: ' . $dust2_5 . ' &micro;g/m3;Dust 10: ' . $dust10 .' &micro;g/m3'. $vocStr;
echo "Sensor: " . $sensorId . "<br/>";
echo $humanData . "<br/>";

$ret = "";
if ($pointId > 0 && $pointId != 401 && $pointId != 402) { // send just assigned units' data
  if (useVOC($pointId) && $voc < 2500) {
      $packet = hextobin("00004447") . $pointBinary . hextobin("00647049")
            .pack("CCCCCC", intval($dt->format("y")), intval($dt->format("m")), intval($dt->format("d")), intval($dt->format("H")), intval($dt->format("i")), intval($dt->format("s")))
                .hextobin('06'.'0011'. substr($noiseAvgHex,6,2). substr($noiseAvgHex,4,2). substr($noiseAvgHex,2,2). substr($noiseAvgHex,0,2)
              .$NOISE_MAX_ID.substr($noiseMaxHex,6,2).substr($noiseMaxHex,4,2).substr($noiseMaxHex,2,2).substr($noiseMaxHex,0,2)
              .$DUST1_ID.substr($dust1Hex,6,2).substr($dust1Hex,4,2).substr($dust1Hex,2,2).substr($dust1Hex,0,2)
              .$DUST2_5_ID.substr($dust2_5Hex,6,2).substr($dust2_5Hex,4,2).substr($dust2_5Hex,2,2).substr($dust2_5Hex,0,2)
              .$DUST10_ID.substr($dust10Hex,6,2).substr($dust10Hex,4,2).substr($dust10Hex,2,2).substr($dust10Hex,0,2)
              .$VOC_ID.substr($vocHex,6,2).substr($vocHex,4,2).substr($vocHex,2,2).substr($vocHex,0,2));
  } else {
     $packet = hextobin("00004447") . $pointBinary . hextobin("00647049")
            .pack("CCCCCC", intval($dt->format("y")), intval($dt->format("m")), intval($dt->format("d")), intval($dt->format("H")), intval($dt->format("i")), intval($dt->format("s")))
                .hextobin('05'.'0011'. substr($noiseAvgHex,6,2). substr($noiseAvgHex,4,2). substr($noiseAvgHex,2,2). substr($noiseAvgHex,0,2)
              .$NOISE_MAX_ID.substr($noiseMaxHex,6,2).substr($noiseMaxHex,4,2).substr($noiseMaxHex,2,2).substr($noiseMaxHex,0,2)
              .$DUST1_ID.substr($dust1Hex,6,2).substr($dust1Hex,4,2).substr($dust1Hex,2,2).substr($dust1Hex,0,2)
              .$DUST10_ID.substr($dust2_5Hex,6,2).substr($dust2_5Hex,4,2).substr($dust2_5Hex,2,2).substr($dust2_5Hex,0,2)
              .$VOC_ID.substr($dust10Hex,6,2).substr($dust10Hex,4,2).substr($dust10Hex,2,2).substr($dust10Hex,0,2));
  }

  echo bin2hex($packet)."</br>";
 #die;
  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, $SNOG_REQUEST_URL);
  curl_setopt($curl, CURLOPT_USERAGENT, 'Mozilla');
  curl_setopt($curl, CURLOPT_POST, 1);
  curl_setopt($curl, CURLOPT_POSTFIELDS, $packet);
  curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
  curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
  echo "Curl resp: ";
  $ret = curl_exec($curl);
  if (!$ret) {
    $ret = curl_error($curl);
  }
  curl_close($curl);  
}
$conn = pg_connect("host=localhost dbname=units user=units password=units")
    or die ('Could not connect: '. pg_last_error());
$query = sprintf("insert into master_log(sigfox_id, point_id, sensor_id, date, response, noise_avg, noise_max, dust_1, dust_2_5, dust_10, voc, lqi) values ('%s', '%s', '%s', '%s', '%s', %f, %f, %d, %d, %d, %f, '%s')",
         $_GET['id'], $pointId, $sensorId, date("Y-m-d H:i:s", $_GET['time']), $ret, $noiseAvg, $noiseMax, $dust1, $dust2_5, $dust10, $voc, $_GET['lqi']);
$result = pg_query($query) or die('Query failed: ' . pg_last_error());
pg_close($conn);

echo "</br>Data sent OK</br>";

?>

