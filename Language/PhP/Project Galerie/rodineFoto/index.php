<?php
//error_reporting(E_ALL);
//ini_set('display_errors', 1);
if (isset($_GET['session_id'])) {
    session_id($_GET['session_id']);
}
session_start([
    'cookie_lifetime' => 86400,
]);
if (!isset($_SESSION['Username'])) {
    header('Location:LoginToRodineFoto.php');
    exit();
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="script.js"></script>
    <link rel="stylesheet" href="style.css" />
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="./WebPageStufTopSecret/Praveen-Minimal-Outline-Gallery.ico" type="image/x-icon">
    <title>Rodina Galerie</title>
</head>

<body>
    <div id="menu" class="contextMenu">
        <ul class="menu-options">
            <li class="menu-option"><a href="">Open Image</a></li>
            <li class="menu-option"><a href="" target="_blank">Open Image in new tab</a></li>
            <li class="menu-option"><a href="" download>Download</a></li>
        </ul>
    </div>
    <div class="header">
        <nav>
            <h2 class="logo">Galerie</h2>
            <input type="checkbox" id="neco-label">
            <label for="neco-label" class="show-menu">
                <i class="fas fa-bars"></i>
            </label>
            <form action="#">
                <div class="input_t">
                    <?php
                    if (isset($_GET['DIR'])) {
                        $dirSeted = true;
                        $dir = $_GET['DIR'];
                        if ($dir == '' || $dir == '.') {
                            $dirSeted = false;
                            $dir = '';
                        }
                        if (preg_match('/^\.\./', $dir)) {
                            $dir = '';
                            $dirSeted = false;
                        }
                    } else {
                        $dirSeted = false;
                        $dir = '';
                    }
                    if ($dirSeted) {
                        echo '<input type="text" name="DIR" value="' . $dir . '/' . '"></input>';
                    } else {
                        echo '<input type="text" name="DIR" id=""></input>';
                    }
                    $_SESSION['Folder']=$dir;
                    ?>
                    <input type="submit" value="Submit" class="submit" />
                </div>
            </form>
            <ul class="menu">
                <?php
                echo '<a href="../Galerie/index.php?session_id=' . session_id() . '">Public</a>';
                ?>
                <a href="#" class="selected">Rodina</a>
                <label for="neco-label" class="hide-menu">
                    <i class="fas fa-times"></i>
                </label>
            </ul>
        </nav>
    </div>
    <div class="container">
        <div class="gallery">
            <?php
            // ini_set('display_errors', 1);
            function processimg($filename, $newFile)
            {
                list($width, $height) = getimagesize($filename);
                $newheight = (400 * $height) / $width;
                $imagetruecolor = imagecreatetruecolor(400, $newheight);
                $a = getimagesize($filename);
                $image_type = $a[2];
                if ($image_type == IMAGETYPE_JPEG) {
                    $newimage = imagecreatefromjpeg($filename);
                } elseif ($image_type == IMAGETYPE_PNG) {
                    $newimage = imagecreatefrompng($filename);
                } elseif ($image_type == IMAGETYPE_BMP) {
                    $newimage = imagecreatefrombmp($filename);
                } else {
                    return;
                }
                imagecopyresampled($imagetruecolor, $newimage, 0, 0, 0, 0, 400, $newheight, $width, $height);
                imagejpeg($imagetruecolor, $newFile, 80);
            };
            function is_image($path)
            {
                // var_dump($path);
                $a = getimagesize($path);
                $image_type = $a[2];
                if (in_array($image_type, array(IMAGETYPE_GIF, IMAGETYPE_JPEG, IMAGETYPE_PNG, IMAGETYPE_BMP))) {
                    return true;
                }
                return false;
            }

            $connection =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin");
            if ($dirSeted) {
                $select = "SELECT Id FROM Folder WHERE Path = '/websiteFiles/" . $dir . "';";
            } else {
                $select = "SELECT Id FROM Folder WHERE Path = '/websiteFiles';";
            }
            $selectResoult = mysqli_query($connection, $select);
            $row = mysqli_fetch_assoc($selectResoult);
            $FolderId = $row['Id'];

            $select = "SELECT * FROM User INNER JOIN RealationTable ON RealationTable.User_id = User.Id INNER JOIN Permition ON RealationTable.Permition_id = Permition.Id INNER JOIN Folder ON Permition.Folder_id = Folder.Id WHERE Jmeno = '" . $_SESSION['Username'] . "' AND Permition.Read = 1 AND ParentId = " . $FolderId . ";";
            $selectResoult = mysqli_query($connection, $select);
            if ($dirSeted) {
                $dirUp = explode('/', $dir . '/');
                //var_dump($dirUp);
                array_splice($dirUp, sizeof($dirUp) - 2);
                $upDir = implode('/', $dirUp);
                //var_dump($upDir);
                //var_dump($dir);
                //var_dump($dirUp);
                echo '<a class="imgContainer" href="index.php?DIR=' . $upDir .
                    '"><img class="folder" src="./WebPageStufTopSecret/folder.png">' .
                    '<div class="text-block">NAHORU</div>' . '</a>';
            }
            while ($row = mysqli_fetch_array($selectResoult)) {
                if ($dirSeted) {
                    echo '<a class="imgContainer" href="index.php?DIR=' . $dir . '/' . $row['Nazev'] .
                        '"><img class="folder" src="./WebPageStufTopSecret/folder.png">' .
                        '<div class="text-block">' . $row['Nazev'] . '</div>' . '</a>';
                } else {
                    echo '<a class="imgContainer" href="index.php?DIR=' . $row['Nazev'] .
                        '"><img class="folder" src="./WebPageStufTopSecret/folder.png">' .
                        '<div class="text-block">' . $row['Nazev'] . '</div>' . '</a>';
                }
            }
            
            if ($dirSeted) {
                $filesUnder = scandir("/websiteFiles/" . $dir);
            } else {
                $filesUnder = scandir("/websiteFiles");
            }
            if (isset($_GET['i'])) {
                $i = $_GET['i'];
            } else {
                $i = 1;
            }
            if (!is_numeric($i)) {
                $i = 1;
            }
            $saveI = $i;
            //echo is_int($i);
            $alreadyIn = 0;

            $numberOfImages = 0;
            $currentImage = 0;
            foreach ($filesUnder as $file) {
                if ($file != '.' && $file != '..') {
                    if (is_image('/websiteFiles' . ($dirSeted?"/".$dir:"") . '/' . $file)) {
                        $numberOfImages++;
                    }
                }
            }
            $buffer = '';
            echo '<hr style="margin-bottom: 1%;">';
            foreach ($filesUnder as $file) {
                if ($file != '.' && $file != '..') {
                    if ($alreadyIn < 16) {
                        if (--$i <= 0) {
                            $alreadyIn++;
                            if (is_image('/websiteFiles' . ($dirSeted?"/".$dir:"") . '/' . $file)) {
                                $currentImage++;
                                $path = '/websiteFiles' . ($dirSeted?"/".$dir:"") . '/' . $file;
                                if (!file_exists('/websiteFiles/casheFiles/' . $dir)) {
                                    mkdir('/websiteFiles/casheFiles/' . ($dirSeted?"/".$dir:""), 0777, true);
                                }
                                $newFile = '/websiteFiles/casheFiles/' . ($dirSeted?"/".$dir:"") . $file . '.small';
                                if (!file_exists($newFile)) {
                                    processimg($path, $newFile);
                                }
                                echo '<a oncontextmenu="imageContextMenu()" onclick=load("img-' . $currentImage . '")><img class="image" src="./getImage.php?Image='.$file.'&Small=true"></a>';
                                echo '<noscript><a href="./getImage.php?Image='.$file.'&Small=false" target="_blank"><img class="image" src="' . $newFile . '" alt="' . $file . '"></a></noscript>';

                                $buffer .= '<div id="pic-' . $currentImage . '" class="overlay">
                                    <img id="img-' . $currentImage . '" class="big-img" data-src="./getImage.php?Image='.$file.'&Small=false">
                                    <a onclick="' . ($currentImage > 1 ? 'load("img-' . ($currentImage - 1) . '")' : 'closeX()') . '" class="prev" title="Prev">►►</a> 
                                    <a onclick="' . ($currentImage < $numberOfImages - 1 ? 'load("img-' . ($currentImage + 1) . '")' : 'closeX()') . '" class="next" title="Next">►►</a> 
                                    <a onclick="closeX()" class="close" title="Close">×</a> 
                                    </div>';
                            }
                        }
                    } else {
                        if (is_image('/websiteFiles' . ($dirSeted?"/".$dir:"") . '/' . $file)) {
                            $currentImage++;
                            $buffer .= '<div id="pic-' . $currentImage . '" class="overlay">
                                <img id="img-' . $currentImage . '" class="big-img" data-src="' . '.' . '/' . $dir . '/' . $file . '" alt="' . $file . '">
                                <a onclick="' . ($currentImage > 1 ? 'load("img-' . ($currentImage - 1) . '")' : 'closeX()') . '" class="prev" title="Prev">►►</a> 
                                <a onclick="' . ($currentImage < $numberOfImages - 1 ? 'load("img-' . ($currentImage + 1) . '")' : 'closeX()') . '" class="next" title="Next">►►</a> 
                                <a onclick="closeX()" class="close" title="Close">×</a> 
                                </div>';
                        }
                    }
                    //echo $i . "=i,alreadyin=".$alreadyIn;

                }
            }
            echo '<div id="gallery">' . $buffer . '</div>';
            ?>
        </div>
    </div>
    <div class="but">
        <?php
        //var_dump($saveI);
        if ($saveI == 1 || $saveI == 0 || $saveI == "") {
            echo '<button onclick=backPage() disabled>Back</button>';
        } else {
            echo '<button onclick=backPage()>Back</button>';
        }
        $saveI += 16;
        //var_dump(sizeof($filesUnder));
        //var_dump($saveI);
        if ($saveI > $numberOfImages) {
            echo '<button onclick=nextPage() disabled>Next</button>';
        } else {
            echo '<button onclick=nextPage()>Next</button>';
        }
        ?>

    </div>

    <form class="butout" action="process.php" method="post">
        <?php
        echo '<input style="display:none;" type="text" name="session_id" value="' . session_id() . '">';
        ?>
        <input type="submit" value="Odhlasit" />
    </form>
</body>
<footer>
    <p>
        ® HTML & CSS by Jiří Petroušek ®
    </p>
    <p>
        ® PHP, SERVER & DATABASE by Martin Kos ®
    </p>
</footer>
<script>
    const menu = document.getElementById("menu");
</script>
</body>

</html>