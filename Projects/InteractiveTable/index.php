<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php

    $conn = mysqli_connect("127.0.0.1", "UnityUser", "BKunjm3uCqjdBQpL", "NeuralNetwork", "5456");

    if (isset($_GET["Table"])) {
        $filterString = "";
        if (isset($_GET["filter"]) && is_array($_GET["filter"])) {
            $filterArray = $_GET["filter"];
            if (count($filterArray) > 0) {
                $filterString = " where ";
            }
            foreach (array_keys($filterArray) as $key) {
                if (substr($filterArray[$key], 0, 1) === ">" || substr($filterArray[$key], 0, 1) === "<" || substr($filterArray[$key], 0, 2) === "!=" || substr($filterArray[$key], 0, 1) === "=") {
                    $filterString .= $key . $filterArray[$key] . " AND ";
                } else {
                    $filterString .= $key . "=" . $filterArray[$key] . " AND ";
                }
            }
            $filterString = substr($filterString, 0, -4);
        }
        $orderBy = "";
        if (isset($_GET["orderBy"]) && is_array($_GET["orderBy"])) {
            if (strlen($orderBy) == 0) {
                $orderBy = " order by ";
            }
            foreach ($_GET["orderBy"] as $orderByVal) {
                $orderBy .= $orderByVal . " asc,";
            }
        }
        if (isset($_GET["orderByDesc"]) && is_array($_GET["orderByDesc"])) {
            if (strlen($orderBy) == 0) {
                $orderBy = " order by ";
            }
            foreach ($_GET["orderByDesc"] as $orderByVal) {
                $orderBy .= $orderByVal . " desc,";
            }
        }
        if (strlen($orderBy) != 0) {
            $orderBy = substr($orderBy, 0, -1) . " ";
        }
        $startFrom = 0;
        $pageSize = 10;
        if (isset($_GET["pageSize"]) && isset($_GET["pageNumber"])) {
            $pageSize = intval($_GET["pageSize"]);
            $pageNumber = intval($_GET["pageNumber"]);
            $startFrom = $pageSize * $pageNumber;
        }
        $sql = "select * from " . $_GET["Table"] . " " . $filterString . $orderBy . "limit " . $startFrom . "," . $pageSize . ";";
        echo $sql;
    } else {
        exit();
    }
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

    $sql = "select count(*) as ADEF from " . $_GET["Table"] . " " . $filterString . $orderBy . ";";
    $sqlresult = $conn->query($sql);
    $row = $sqlresult->fetch_assoc();
    echo '<div id="info" style="display: none;">{"count":' . $row["ADEF"] . '}</div>';
    $conn->close();
    ?>
</body>

</html>