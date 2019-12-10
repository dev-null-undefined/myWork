<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <link rel="stylesheet" href="style_login.css" />
  <title>Document</title>
  <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
  <?php
  error_reporting(E_ALL);
ini_set('display_errors', 1);
  session_start();
  $conn =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin");
  $select;
  if (isset($_POST['Username']) && isset($_POST['Password']) && is_array($_POST['Password']) == false && is_array($_POST['Username']) == false) {
    $usr = $_POST['Username'];
    $pswd = $_POST['Password'];
    $stmt = $conn->prepare('SELECT * FROM User WHERE Jmeno = ?');
    $stmt->bind_param('s', $usr);
    $stmt->execute();
    $result = $stmt->get_result();
    $row = $result->fetch_assoc();
    $passwdVerify = password_verify($pswd, $row['HashPassword']);
    if ($passwdVerify == true) {
      $_SESSION["Username"] = $usr;
      header("Location:index.php?session_id=".session_id());
      exit();
    } else {
      header("Location:LoginToRodineFoto.php#wrongpass");
    exit();
    }
  }else{
    echo '<div class="LoginPanel">
    <h1>Login</h1>
    
    <form method="post" action="#">
        <div class="loginC">
             <div class="input_t">
                    <i class="fas fa-user"></i>
                 <input type="text" name="Username" placeholder="Username"> <br></br>
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
  }
  ?>

</body>

</html>