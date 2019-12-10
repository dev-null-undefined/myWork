<?php
session_start();
if (isset($_SESSION['Username']) && isset($_GET['DIR'])) {
    $connection =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin");
    $select = "SELECT * FROM User 
        INNER JOIN RealationTable ON RealationTable.User_id = User.Id 
        INNER JOIN Permition ON RealationTable.Permition_id = Permition.Id 
        INNER JOIN Folder ON Permition.Folder_id = Folder.Id 
        WHERE Jmeno = '" . $_SESSION['Username'] . "' AND Permition.Modify = 1 AND Path = '" . "/websiteFiles/" . $_GET['DIR'] . "';";
    $selectResoult = mysqli_query($connection, $select);
    if ($selectResoult->num_rows >= 1) {
        echo '<ul class="menu-options">
        <li class="menu-option"><a href="">Delete folder and all subfolders.</a></li>
        <li class="menu-option"><a href="">Rename folder</a></li>
        <li class="menu-option"><a href="">Edit permitions</a></li>
    </ul>';
    } else {
        // var_dump($_SESSION['Folder']);
        // var_dump( $select );
        echo "NO permtion to edit this folder u noob";
    }
}
