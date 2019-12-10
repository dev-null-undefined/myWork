<?php
$folder = "/websiteFiles";
$conn =  new mysqli("jestrab.kolej.mff.cuni.cz", "martin", "Kos", "martin");
addSubFolders($folder,$conn,1);
function addSubFolders($Folder, $connection)
{
    $fileUnder = scandir($Folder);
    foreach ($fileUnder as $file) {
        if (is_dir($Folder . "/" . $file)&&$file!="."&&$file!="..") {
            $select = "select Id from Folder where Path='".$Folder."';";
            $selectResoult = mysqli_query($connection, $select);
            $row = mysqli_fetch_assoc($selectResoult);
            $ParrentFolderId = $row['Id'];

            $sql = "INSERT INTO Folder(Nazev,Path,ParentId)VALUES('" . $file . "','" . $Folder . "/" . $file . "'," . $ParrentFolderId . ");";
            if ($connection->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                exit;
            }
            

            $select = "select Id from Folder where Path='".$Folder.'/'.$file."';";
            $selectResoult = mysqli_query($connection, $select);
            $row = mysqli_fetch_assoc($selectResoult);
            $FolderId = $row['Id'];

            var_dump($select);
            $sql = "INSERT INTO Permition(`Read`,`Write`,`Modify`,`Folder_id`)VALUES(true,true,false,".$FolderId.");";
            if ($connection->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                exit;
            }

            $select = "select Id from Permition where Folder_id=".$FolderId.";";
            $selectResoult = mysqli_query($connection, $select);
            $row = mysqli_fetch_assoc($selectResoult);
            $PermitionId = $row['Id'];


            $sql = "INSERT INTO RealationTable(Permition_id,User_id)VALUES(".$PermitionId.",1);";
            if ($connection->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                exit;
            }
            $sql = "INSERT INTO RealationTable(Permition_id,User_id)VALUES(".$PermitionId.",2);";
            if ($connection->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                exit;
            }
            $sql = "INSERT INTO RealationTable(Permition_id,User_id)VALUES(".$PermitionId.",3);";
            if ($connection->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                exit;
            }
            addSubFolders($Folder . "/" . $file , $connection, $ParrentFolderId);
        }
    }
}
/*
INSERT INTO Folder(Nazev,Path,ParentId)VALUES('$file','$dir."/".$file',$ParrentFolderId);
$FolderId = select Id from Folder where Path='$dir."/".$file';
INSERT INTO Permition(`Read`,`Write`,`Modify`,`Folder_id`)VALUES(true,true,false,$FolderId);
$PermitionId = select Id from Permition where Folder_id=$FolderId;
INSERT INTO RealationTable(Permition_id,User_id)VALUES($PermitionId,1);
INSERT INTO RealationTable(Permition_id,User_id)VALUES($PermitionId,2);
INSERT INTO RealationTable(Permition_id,User_id)VALUES($PermitionId,3);
*/