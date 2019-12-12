<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="style.css" />
    <title>Document</title>
    <script src="script.js" charset="utf-8"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body onload="loaded()">
    <?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);
    session_start();
    if (isset($_SESSION['Legit']) && $_SESSION['Legit']) {
        $out = shell_exec("../Wake_on_lan.sh " . $_SESSION["MAC"]);
        // echo $out;
        // echo "whad up boy";
        echo '<h1>
        Waking PC
      </h1>
      <form class="butout" action="./process.php" method="post">
        <input style="display:none;" type="text" name="destroye" value="true">
        <input type="submit" value="Odhlasit" />
    </form>';
    echo "../Wake_on_lan.sh " . $_SESSION["MAC"];
        exit();
    }
    if (isset($_POST['Password']) && is_array($_POST['Password']) == false) {
        if ($_POST['Password'] == "HesloJeJEdnaDVA34") {
            $_SESSION["Legit"] = true;
            $_SESSION["MAC"] = $_POST['mac-address'];
            $out = shell_exec("../Wake_on_lan.sh" . $_SESSION["MAC"]);
            // echo $out;
            echo '<h1>
        Waking PC
        </h1>
        <form class="butout" action="./process.php" method="post">
          <input style="display:none;" type="text" name="destroye" value="true">
          <input type="submit" value="Odhlasit" />
      </form>';
            exit();
        } else {
            header("Location:index.php#wrongpass");
        }
    }
    echo '<div class="LoginPanel">
    <h1>Login</h1>
    <form method="post" action="#">
        <div class="loginC">
        <div class="input_t">
                <i class="fas fa-desktop"></i>
        <input id="macAddress" type="text" name="mac-address" placeholder="00:00:00:00:00:00">
        </div>
        <div class="input_t">
                <i class="fas fa-unlock-alt"></i>
        <input type="password" name="Password" placeholder="Password">
        </div>
        <input type="submit" name="Login" value="Log In">
        </div>
</form>
</div>
<div id="wrongpass" class="LoginPanel wrong-password"><div class="botom">Wrong Password or Username</div></div>';
    ?>
</body>

</html>