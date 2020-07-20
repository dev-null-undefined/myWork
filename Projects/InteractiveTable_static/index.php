<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php
    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);

    //* Parametrs
    $dsHost = "127.0.0.1";
    $dsUser = "UnityUser";
    $dsPasswd = "BKunjm3uCqjdBQpL";
    $dsDatabase = "NeuralNetwork";
    $dsPort = "5456";
    $table = "PlayerData";
    $filter = [];
    $orderBy = [];
    $orderByDesc = [];
    $startFrom = 0;
    $pageSize = 10;
    $pageNumber = 0;
    $dsJoins = "";

    if (isset($_REQUEST["dsHost"])) {
        $dsHost = $_REQUEST["dsHost"];
    }
    if (isset($_REQUEST["dsUser"])) {
        $dsUser = $_REQUEST["dsUser"];
    }
    if (isset($_REQUEST["dsDatabase"])) {
        $dsDatabase = $_REQUEST["dsDatabase"];
    }
    if (isset($_REQUEST["dsPort"])) {
        $dsPort = $_REQUEST["dsPort"];
    }
    if (isset($_REQUEST["dsPasswd"])) {
        $dsPasswd = $_REQUEST["dsPasswd"];
    }
    if (isset($_REQUEST["table"])) {
        $table = $_REQUEST["table"];
    }
    if (isset($_REQUEST["filter"]) && is_array($_REQUEST["filter"])) {
        $filter = $_REQUEST["filter"];
    }
    if (isset($_REQUEST["orderBy"]) && is_array($_REQUEST["orderBy"])) {
        $orderBy = $_REQUEST["orderBy"];
    }
    if (isset($_REQUEST["orderByDesc"]) && is_array($_REQUEST["orderByDesc"])) {
        $orderByDesc = $_REQUEST["orderByDesc"];
    }
    if (isset($_REQUEST["pageSize"])) {
        $pageSize = intval($_REQUEST["pageSize"]);
    }
    if (isset($_REQUEST["pageNumber"])) {
        $pageNumber = intval($_REQUEST["pageNumber"]);
    }
    $startFrom = $pageSize * $pageNumber;
    if (isset($_REQUEST["dsJoins"])) {
        $dsJoins = $_REQUEST["dsJoins"];
    }

    $conn = mysqli_connect($dsHost, $dsUser, $dsPasswd, $dsDatabase, $dsPort);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $filterString = "";
    if (count($filter) > 0) {
        $filterString = " where ";
    }
    foreach (array_keys($filter) as $key) {
        if (substr($filter[$key], 0, 1) === ">" || substr($filter[$key], 0, 1) === "<" || substr($filter[$key], 0, 2) === "!=" || substr($filter[$key], 0, 1) === "=") {
            $filterString .= $key . $filter[$key] . " AND ";
        } else {
            $filterString .= $key . ' like "' . $filter[$key] . '" AND ';
        }
    }
    $filterString = substr($filterString, 0, -4);
    $orderByString = "";
    if (count($orderBy) > 0) {
        $orderByString = " order by ";
    }
    foreach ($orderBy as $orderByVal) {
        $orderByString .= $orderByVal . " asc,";
    }
    if (strlen($orderByString) == 0 && count($orderByDesc) > 0) {
        $orderByString = " order by ";
    }
    foreach ($orderByDesc as $orderByVal) {
        $orderByString .= $orderByVal . " desc,";
    }
    if (strlen($orderByString) != 0) {
        $orderByString = substr($orderByString, 0, -1) . " ";
    }
    $sql = "select * from " . $table . " " . $dsJoins . " " . $filterString . $orderByString . "limit " . $startFrom . "," . $pageSize . ";";
    echo $sql;
    $sqlresult = $conn->query($sql);
    $endLine = "\n";
    if ($sqlresult->num_rows > 0) {
        $htmltable =  "<table id=\"myTable\">" . $endLine;
        $firstLine   = true;
        while ($row = $sqlresult->fetch_assoc()) {
            if ($firstLine) {
                $htmltable .=   "<thead>" . $endLine;
                $htmltable .=   "<tr>"  . $endLine;
                foreach ($row as $key => $value) {
                    $htmltable .=   "<th>" . $key . "</th>"  . $endLine;
                }
                $htmltable .=   "</tr>"  . $endLine;
                $htmltable .=   "</thead>" . $endLine;
                $htmltable .=   "<tbody>" . $endLine;
                $firstLine = false;
            }
            $htmltable .=   "<tr>"  . $endLine;
            foreach ($row as $key => $value) {
                $htmltable .=   "<td>" . $value . "</td>"  . $endLine;
            }
            $htmltable .=   "</tr>"   . $endLine;
        }
        $htmltable .=   "</tbody>" . $endLine;
        $htmltable .=   "</table>"   . $endLine;
        echo $htmltable;
    } else {
        echo "0 rows";
    }

    $sql = "select count(*) as DQQVOJNTKANZNFKAF from " . $table . " " . $filterString . $orderByString . ";";
    $sqlresult = $conn->query($sql);
    $row = $sqlresult->fetch_assoc();
    echo '<div id="info" style="display: none;">{"count":' . $row["DQQVOJNTKANZNFKAF"] . '}</div>';
    $conn->close();
    ?>
</body>

</html>