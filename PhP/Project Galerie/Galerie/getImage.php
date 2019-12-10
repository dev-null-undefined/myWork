<?php
if (isset($_GET['session_id'])) {
    session_id($_GET['session_id']);
}
session_start([
    'cookie_lifetime' => 86400,
]);
if (!isset($_SESSION['Username'])) {
    //header($_SERVER["SERVER_PROTOCOL"] . " 404 Not Found");
    exit();
}

$connection =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin");
if (isset($_SESSION['Folder']) && isset($_GET['Image']) && isset($_GET['Small']) && !is_array($_SESSION['Folder'])) {
    $folder = $_SESSION['Folder'];
    if ($_GET['Small'] == "true") {
        $file_out = '/websiteFiles/casheFiles/' . ($folder == "" ? "" : "/" . $folder) . $_GET['Image'] . '.small';
    } elseif ($_GET['Small'] == "false") {
        $file_out = '/websiteFiles' . ($folder == "" ? "" : "/" . $folder) . '/' . $_GET['Image'];
    } else {
        exit();
    }
    $fp = fopen($file_out, 'rb');
    if (file_exists($file_out)) {
        $image_info = getimagesize($file_out);
        // echo exec('whoami');
        // echo 'Current script owner: ' . get_current_user();
        switch ($image_info[2]) {
            case IMAGETYPE_JPEG:
                header("Content-Type: image/jpeg");
                // echo "jpg" . $file_out;
                break;
            case IMAGETYPE_GIF:
                header("Content-Type: image/gif");
                // echo "gif" . $file_out;
                break;
            case IMAGETYPE_PNG:
                header("Content-Type: image/png");
                // echo "png" . $file_out;
                break;
            default:
                header($_SERVER["SERVER_PROTOCOL"] . " 500 Internal Server Error");
                break;
        }
        header('Content-Length: ' . filesize($file_out));
        fpassthru($fp);
    }
} else {
    //header($_SERVER["SERVER_PROTOCOL"] . " 404 Not Found");
}
exit();
