  <!DOCTYPE html>
  <html lang="en">

  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
      <style>
          input[type=file] {
              width: 200px;
              height: 300px;
          }

          input[type=file]::-webkit-input-placeholder {
              width: 200px;
              padding-top: 200px;
              height: 100px;
          }

          input[type=file]::-webkit-file-upload-button {
              height: 200px;
              width: 200px;
              background-color: #517;
              border-radius: 50%;
              display: inline-block;
          }
      </style>
  </head>

  <body>
      <?php
        error_reporting(0);
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
        $num1;
        $num2;
        if (isset($_POST["num1"]) && isset($_POST["num2"])) {
            $myfile = fopen("settings.txt", "w");
            $txt = $_POST["num1"] . "\n";
            $num1 = intval($_POST["num1"]);
            fwrite($myfile, $txt);
            $txt = $_POST["num2"] . "\n";
            $num2 = intval($_POST["num2"]);
            fwrite($myfile, $txt);
            fclose($myfile);
        } else {
            $myfile = fopen("settings.txt", "r");
            $l = 0;
            while (!feof($myfile)) {
                $something = fgets($myfile);
                if ($l == 0) {
                    $num1 = intval($something);
                } elseif ($l == 1) {
                    $num2 = intval($something);
                }
                $l++;
            }
            fclose($myfile);
        }

        ?>
      <form action="" method="post" enctype="multipart/form-data">
          <input type="file" name="fileToUpload" id="fileToUpload">
          <label for="num1">Delivery Date</label>
          <input type="number" name="num1" id="num1" placeholder="DELIVERY DATE" value="<?php echo $num1 ?>">
          <label for="num2">CREATED</label>
          <input type="number" name="num2" id="num2" placeholder="CREATED" value="<?php echo $num2 ?>">
          <input type="submit" value="Submit    " name="submit">
      </form>
      <?php
        $the_big_array = array_map('str_getcsv', file('file.csv'));
        $years = array();
        for ($i = 0; $i < sizeof($the_big_array); $i++) {
            $dt1 = DateTime::createFromFormat("d/M/y H:i A", $the_big_array[$i][$num1]);
            $dt2 = DateTime::createFromFormat("d/M/y H:i A", $the_big_array[$i][$num2]);
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
                $year = date("y", (strtotime($nd1)));
                // ?NEGATIVE???
                // echo $num . "<br>";
                // if ($num < 0) {
                //     echo $dt1->format('d/m/Y') . "|" . $dt2->format('d/m/Y') . "<br>";
                // }
                if (!isset($years[$year])) {
                    $years[$year] = array();
                    for ($i = 0; $i < 12; $i++) {
                        $years[$year][$i] = array();
                    }
                    $years[$year]['year'] = $year;
                }
                array_push($years[$year][$month - 1], $num);
            }
        }
        echo "<pre>";
        // $disp = '<table class="table"><tbody>';
        // var_dump($years);
        foreach ($years as $year) {
            echo $year['year'] . "<br>";
            for ($i = 0; $i < 12; $i++) {
                sort($year[$i]);
                if (count($year[$i]) > 0) {

                    // $disp .= '<tr>';
                    // $disp .= '<td>' . ($i + 1) . '</td>';
                    // $disp .= '<td>' . array_sum($year[$i]) / count($year[$i]) . '</td>';
                    // $disp .= '<td>' . $year[$i][round(count($year[$i]) / 2)] . '</td>';
                    // $disp .= '</tr>';
                    echo "month," . ($i + 1) . ",avg," . array_sum($year[$i]) / count($year[$i]) . ",median," . $year[$i][ceil(count($year[$i]) / 2)] . "<br>";
                } else {
                    echo "month," . ($i + 1) . ",avg,NO DATA,median,NODATA<br>";
                }
            }
            echo "<hr>";
        }

        // $disp .= '</tbody></table>';
        // echo $disp;
        echo "</pre>";
        ?>
  </body>

  </html>