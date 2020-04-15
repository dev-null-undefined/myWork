"use strict";
process.title = "node-chat";
var webSocketsServerPort = 5455;
var webSocketServer = require("websocket").server;
var http = require("http");
var server = http.createServer(function (request, response) {});
server.listen(webSocketsServerPort, function () {
  console.log(new Date() + " Server is listening on port " + webSocketsServerPort);
});
var wsServer = new webSocketServer({
  httpServer: server,
});
wsServer.on("request", function (request) {
  console.log(new Date() + " Connection from origin " + request.origin + ".");
  var connection = request.accept(null, request.origin);
  // user sent some message
  connection.on("message", function (message) {
    console.log(message);
    connection.send("NO");
  });
  // user disconnected
  connection.on("close", function (connection) {});
});
