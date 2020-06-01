  <!DOCTYPE html>
 <html lang="en">

 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Document</title>
     <style>
         input[type=file]{
            width: 200px;
            height: 300px;
         }
         input[type=file]::-webkit-input-placeholder{
            width: 200px;
            padding-top:200px;
            height: 100px;
         }
         input[type=file]::-webkit-file-upload-button{
            height: 200px;
            width:  200px;
            background-color: #517;
            border-radius: 50%;
            display: inline-block;
         }
     </style>
 </head>

 <body>
     <?php
$target_dir = "/fileManager/katka";
$target_file = $target_dir . "file.csv";
if (isset($_POST["submit"])) {
    if (file_exists($target_file)) {
        unlink($target_file);
    }
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
        echo "The file " . basename($_FILES["fileToUpload"]["name"]) . " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}
?>
     <form action="" method="post" enctype="multipart/form-data">
         <input type="file" name="fileToUpload" id="fileToUpload">
         <input type="submit" value="Upload Image" name="submit">
     </form>
     <?php
$the_big_array = array_map('str_getcsv', file('file.csv'));
$months = array(
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
    array(),
);
for ($i = 0; $i < sizeof($the_big_array); $i++) {
    $dt1 = DateTime::createFromFormat("d/M/y H:i A", $the_big_array[$i][3]);
    $dt2 = DateTime::createFromFormat("d/M/y H:i A", $the_big_array[$i][6]);
    if ($dt1 && $dt2) {
        $nd1 = $dt1->format('Y-m-d');
        $nd2 = $dt2->format('Y-m-d');
        if (true) { // !!!!!!!!!!! ROUND !!!!!!!!!!!!!
            $num = round(((strtotime($nd1) - strtotime($nd2)) / 86400));
        } else {
            $num = ((strtotime($nd1) - strtotime($nd2)) / 86400);
        }
        // ?start or end?
        // $month=date("m",(strtotime($nd2)));
        $month = date("m", (strtotime($nd1)));
        // ?NEGATIVE???
        // echo $num . "<br>";
        // if ($num < 0) {
        //     echo $dt1->format('d/m/Y') . "|" . $dt2->format('d/m/Y') . "<br>";
        // }

        array_push($months[$month - 1], $num);
    }
}
echo "<pre>";
// $disp = '<table class="table"><tbody>';
for ($i = 0; $i < 12; $i++) {
    sort($months[$i]);
    if (count($months[$i]) > 0) {

        // $disp .= '<tr>';
        // $disp .= '<td>' . ($i + 1) . '</td>';
        // $disp .= '<td>' . array_sum($months[$i]) / count($months[$i]) . '</td>';
        // $disp .= '<td>' . $months[$i][round(count($months[$i]) / 2)] . '</td>';
        // $disp .= '</tr>';
        echo "month," . ($i + 1) . ",avg," . array_sum($months[$i]) / count($months[$i]) . ",median," . $months[$i][round(count($months[$i]) / 2)] . "<br>";
    } else {
        echo "month," . ($i + 1) . ",avg,NO DATA,median,NODATA<br>";
    }
}
// $disp .= '</tbody></table>';
// echo $disp;
echo "</pre>";
?>
 </body>

 </html>