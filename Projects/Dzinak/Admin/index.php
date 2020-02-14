<!DOCTYPE html>
<?php
$openTime = file("../../web/settings/oteviraci_doba.ini");
?>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <?php
    foreach($openTime as $line){
        echo '<div>'.$line.'</div><br>';
    }
    ?>
    <br>
    <form action="pridatFirmu.php" method="POST" enctype="multipart/form-data">
        <input type="file" id="icon" name="icon" accept="image/*">
        <input type="text" name="url" id="url" placeholder="Adressa nebo prazdne">
        <input type="submit" value="submit">
    </form>
    <br>
    <form action="upravitDobu.php" method="POST">
        <input type="text" name="pondeli" id="pondeli" placeholder="Pondeli">
        <input type="text" name="utery" id="utery" placeholder="utery">
        <input type="text" name="streda" id="streda" placeholder="streda">
        <input type="text" name="ctvrtek" id="ctvrtek" placeholder="ctvrtek">
        <input type="text" name="patek" id="patek" placeholder="patek">
        <input type="text" name="sobota" id="sobota" placeholder="sobota">
        <input type="text" name="nedele" id="nedele" placeholder="nedele">
        <input type="submit" value="submit">
    </form>
    <br>
</body>

</html>