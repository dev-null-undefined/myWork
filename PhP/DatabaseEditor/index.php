<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <link rel="stylesheet" href="style.css" />
  <title>Document</title>
  <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
  <?php

  //INSERT INTO Users(userName,hashPasswd,admin)VALUES('Admin','$2y$10$aWV7oAVJd6HCZCylhPa5M.BdMkjsm26A5y9VAA8tsbLrPuyBnmm6W', true);
  $conn =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin_skola");
  $select;
  if(isset($_POST['insert'])){
    $sql = "INSERT INTO Messege(text)VALUES('".$_POST['insert']."');";
    if ($conn->query($sql) === TRUE) {
      echo "New record created successfully";
  } else {
      echo "Error: " . $sql . "<br>" . $conn->error;
  }
  exit();
  }
  if (isset($_POST['Username']) && isset($_POST['Password']) && is_array($_POST['Password']) == false && is_array($_POST['Username']) == false) {
    $usr = $_POST['Username'];
    $pswd = $_POST['Password'];
    //$hashPswd=password_hash($password, PASSWORD_DEFAULT);
    //var_dump($hashPswd);

    //echo $usr . $pswd; //. $hashPswd;
    $select = "select * from Users where userName='" . $usr . "';";
    $selectResoult = mysqli_query($conn, $select);
    if (mysqli_num_rows($selectResoult) == 1) {
      //echo "find user with name as u input";
    }
    $row = mysqli_fetch_array($selectResoult);
    //echo $row['hashPasswd'];
    //echo 'whadup';
    $passwdVerify = password_verify($pswd, $row['hashPasswd']);
    if ($passwdVerify == true) {
      //  echo "loged in";
      echo '<form method="post" action="#" >
          <hr />
            <input type="text" name="insert" class="field" />
            <input type="submit" value="Submit" class="submit" />
        </form>';
    } else {
      echo "NOT loged in";
    }
  }
  ?>
  <form method="post" action="#" class="container">
    <div class="title center">Registration</div>
    <hr />
    <label class="input-container">
      <i class="fas fa-user-alt icon"></i>
      <input type="text" name="Username" class="field" />
    </label>
    <label class="input-container">
      <i class="fas fa-key icon"></i>
      <input type="password" id="pwd" name="Password" class="field" />
    </label>
    <br />
    <div class="div-submit">
      <input type="submit" value="Submit" class="submit" />
    </div>
  </form>
  <i class="divnavec"></i>
</body>

</html>