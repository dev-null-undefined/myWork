<?php
$target_dir='../../web/';
$target_file = '';
$extension = strtolower(pathinfo("./" . $_FILES["icon"]['name'], PATHINFO_EXTENSION));
$i = 0;
for ($i = 0; file_exists('../../web/img/img-' . $i . '.' . $extension); $i -= -1) {
}
$target_file = 'img/img-' . ($i) . '.' . $extension;
$uploadOk = 1;
$check = getimagesize($_FILES["icon"]['tmp_name']);
if ($check !== false) {
    //echo "File is an image - " . $check["mime"] . ".";
    $uploadOk = 1;
} else {
    //echo "File is not an image.";
    $uploadOk = 0;
}

//var_dump($target_file);
//var_dump($extension);
if ($uploadOk = 1) {
    move_uploaded_file($_FILES["icon"]["tmp_name"], $target_dir.$target_file);
    //echo 'DONE';
    $fp = fopen("../../web/img/urls.ini", "a");
    fwrite($fp, $target_file . "\n".$_POST['url']."\n");
    fclose($fp);
    header('Location: https://admin.blacktailor.cz/index.php#done');
} else {
    header('Location: https://admin.blacktailor.cz/index.php#error');
}