const Discord = require("discord.js");
const client = new Discord.Client();
const fs = require("fs");

const run = new RegExp("^!run ");

const reset = new RegExp("^!linux reset");
const stop = new RegExp("^!linux stop");
const shutdown = new RegExp("^!linux shutdown");
const start = new RegExp("^!linux start");
const restore = new RegExp("^!linux restore");
const status = new RegExp("^!linux status");

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

const { exec } = require("child_process");
let currentMsg;

let msgText;

function appendToFile(fileName, text) {
  fs.appendFile(fileName, text, err => {
    if (err) throw err;
  });
}
function writeToFile(fileName, text) {
  fs.writeFile(fileName, text, err => {
    if (err) throw err;
  });
}

client.on("message", msg => {
  if (run.test(msg.content)) {
    var currentdate = new Date();
    var datetime =
      "Last Sync: " +
      currentdate.getDate() +
      "/" +
      (currentdate.getMonth() + 1) +
      "/" +
      currentdate.getFullYear() +
      " @ " +
      currentdate.getHours() +
      ":" +
      currentdate.getMinutes() +
      ":" +
      currentdate.getSeconds();
    appendToFile("run_log", datetime + "  " + msg.author.tag + "  " + msg.content + "\n");
  }
  msgText = "";
  //HELP
  if (msg.content == "!linux help") {
    msg.reply("```!linux reset/stop/start/shutdown/restore/status```");
    return;
  }
  if (
    reset.test(msg.content) ||
    stop.test(msg.content) ||
    start.test(msg.content) ||
    restore.test(msg.content) ||
    shutdown.test(msg.content) ||
    status.test(msg.content)
  ) {
    currentMsg = msg;
    if (msg.member != null && msg.member.roles.find(r => r.name === "Admin" || r.name === "Owner")) {
      var currentdate = new Date();
      var datetime =
        "Last Sync: " +
        currentdate.getDate() +
        "/" +
        (currentdate.getMonth() + 1) +
        "/" +
        currentdate.getFullYear() +
        " @ " +
        currentdate.getHours() +
        ":" +
        currentdate.getMinutes() +
        ":" +
        currentdate.getSeconds();
      appendToFile("linux_log", datetime + "  " + msg.author.tag + "  " + msg.content + "\n");
      switch (true) {
        case reset.test(msg.content):
          exec("virsh destroy debian10 && virsh start debian10", myMethod);
          break;
        case start.test(msg.content):
          exec("virsh start debian10", myMethod);
          break;
        case stop.test(msg.content):
          exec("virsh destroy debian10", myMethod);
          break;
        case restore.test(msg.content):
          msg.reply("This can take up to 10 minutes please wait.");
          exec(
            "virsh destroy debian10; virsh undefine debian10 && rm -f /home/martin/debian10 &&  virt-clone --original debian10-clone --name debian10 --auto-clone && virsh start debian10",
            myMethodRestore
          );
          break;
        case shutdown.test(msg.content):
          exec("virsh shutdown debian10", myMethod);
          break;
        case status.test(msg.content):
          exec('virsh list --all | grep "debian10 "', myMethod);
          break;
      }
    } else {
      msg.reply("You dont have permition to do this. Ask admin for perm.");
    }
  }
});
function replyThis(msgText) {
  if (msgText.length > 2000) {
    writeToFile("result.txt", msgText);
    currentMsg.reply("Result is too BIG here is it in file.", {
      files: ["./result.txt"]
    });
  } else {
    currentMsg.reply(msgText);
  }
}
function myMethod(err, stdout, stderr) {
  if (err) {
    msgText += "Error:```" + err + "``` ";
  }
  if (stdout) {
    msgText += "Response:```" + stdout + "``` ";
  }
  if (stderr) {
    msgText += "Response:```" + stderr + "``` ";
  }
  replyThis(msgText);
}
function myMethodRestore(err, stdout, stderr) {
  replyThis("DONE");
}

fs.readFile("token", (err, data) => {
  if (err) throw err;
  let token = data.toString();
  token = token.replace(/(\r\n|\n|\r)/gm, "");
  console.log(token);
  client.login(token);
});
