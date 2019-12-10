<?php
if (isset($_GET['folder'])) {
    $folder =$_GET['folder'];
}else{
    $folder = '';
}
?>
<html>
<head>
  <title>PHP Test</title>
  <link rel="stylesheet" type="text/css" href="style.css">
 </head>
 <body>
<h1>ahoj</h1>
<?php
$default_folder='/media/share';
if ($handle = opendir($default_folder.$folder)) {

    while (false !== ($entry = readdir($handle))) {

        if ($entry != "." && $entry != "..") {
            if(!is_dir($default_folder.$folder.'/'.$entry)){
                echo '<a class="file"href="'.$default_folder.$folder.'/'.$entry.'" download>'.$entry.'</a></br>';
            }else{
                echo '<div class="dropdown">
                <button class="dropbtn">'.$entry.'</button>
                <div class="dropdown-content">
                  <a href="index.php?folder='.$folder.'/'.$entry.'">Open Folder</a></br>
                  <a target="_blank" href="downloadfolder.php?folder='.$default_folder.$folder.'/'.$entry.'">Download folder</a>
                </div>
              </div></br>';
            }
       }
    }

    closedir($handle);
}
?>
 </body>
</html>
