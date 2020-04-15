"use strict";
// for better performance - to avoid searching in DOM
var content = document.getElementById("content");
var input = document.getElementById("input");
var status = document.getElementById("status");
window.WebSocket = window.WebSocket || window.MozWebSocket;
if (!window.WebSocket) {
  alert("Browser doesnt support WebSocket");
}
var connection = new WebSocket("ws://studio.nvl.cz:5455");
connection.onopen = function () {
  alert("Connected");
};
connection.onerror = function (error) {
  alert("error");
};
connection.onmessage = function (message) {
  alert("messege" + message);
};
