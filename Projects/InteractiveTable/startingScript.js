function Start() {
    createInteractiveTable(
        ajaxPage = "index.php",
        tableId = "myTable",
        slqTableInfoId = "info",
        sqlTable = document.getElementById("dsTable").value,
        sqlHost = document.getElementById("dsIp").value,
        sqlUser = document.getElementById("dsUser").value,
        sqlPassword = document.getElementById("dsPasswd").value,
        sqlDatabase = document.getElementById("dsDatabase").value,
        sqlPort = document.getElementById("sqlPort").value,
        sqlJoins = document.getElementById("dsJoins").value
      ) 
      document.body.removeChild(document.getElementById("main"));
}