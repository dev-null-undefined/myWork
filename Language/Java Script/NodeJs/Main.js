const myInterface = require("readline").createInterface({
  input: process.stdin,
});
myInterface.on("line", function (line) {
  console.log("User input: " + line);
});
