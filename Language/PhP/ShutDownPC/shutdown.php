<?php
if (isset($_GET[password])&&isset($_GET[username])) {
    array_push($FilterFields, $_GET[sprintf("input%d", $fieldNumber)]);
    if($_GET[password]=="heslo1234Atakyjsemprostespanejvprogramovanialebavimeto"&&$_GET[username]=="Martin"){
        echo "<h1>Shuting down PC</h1>";
        $output = shell_exec("sudo init 0");
        echo "<div>$output</div>";
    }else{
        echo "<h1>Wrong Password</h1>";
    }
}else{
    echo "<h1>Password is not sended.</h1>";
}
?>
<?php $output = shell_exec("touch /ahojky");
 echo $output;
 ?>