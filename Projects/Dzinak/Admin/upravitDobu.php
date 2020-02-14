<?php
$fp = fopen("../../web/settings/oteviraci_doba.ini","wb");
fwrite($fp, $_POST['pondeli']."\n");
fwrite($fp, $_POST['utery']."\n");
fwrite($fp, $_POST['streda']."\n");
fwrite($fp, $_POST['ctvrtek']."\n");
fwrite($fp, $_POST['patek']."\n");
fwrite($fp, $_POST['sobota']."\n");
fwrite($fp, $_POST['nedele']."\n");
fclose($fp);
header("Location: https://admin.blacktailor.cz/done");
?>