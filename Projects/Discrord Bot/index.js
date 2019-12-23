const Discord = require("discord.js");
const client = new Discord.Client();
const fs = require("fs");

const bash = new RegExp("^!run bash ");
const python = new RegExp("^!run python ");
const php = new RegExp("^!run php ");

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

const { exec } = require("child_process");
let currentMsg;

function writeToFile(params) {
  fs.writeFile("execute", params, err => {
    if (err) throw err;
  });
}
client.on("message", msg => {
  currentMsg = msg;
  msgText = "";
  if (msg.content == "!run help") {
    msg.reply("!run bash || !run python || !run php");
    return;
  }
  if (bash.test(msg.content)) {
    let text = msg.content;
    if (text.endsWith("`")) {
      text = text.substring(13, text.length - 3);
    } else {
      text = text.substring(9, text.length);
    }
    msgText += "Running :``` " + text + " ``` ";
    writeToFile(text);
    exec("/bin/bash execute" + text, myMethod);
  } else {
    if (python.test(msg.content)) {
      let text = msg.content;
      if (text.endsWith("`")) {
        text = text.substring(15, text.length - 3);
      } else {
        text = text.substring(12, text.length);
      }
      msgText += "Running with python :``` " + text + " ``` ";
      writeToFile(text);
      exec("python execute", myMethod);
    } else {
      if (php.test(msg.content)) {
        let text = msg.content;
        if (text.endsWith("`")) {
          text = text.substring(12, text.length - 3);
        } else {
          text = text.substring(8, text.length);
        }
        msgText += "Running with php :``` " + text + " ``` ";
        writeToFile("<?php"+text+"?>");
        exec("php -f execute", myMethod);
      }
    }
  }
});
function replyThis(msgText) {
  if (msgText.length > 2000) {
    exec("echo '" + msgText + "' > result.txt");
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
  } else {
    if (stdout) {
      msgText += "Response:```" + stdout + "``` ";
    }
    if (stderr) {
      msgText += "Response:```" + stderr + "``` ";
    }
  }
  replyThis(msgText);
}

fs.readFile('token', (err, data) => { 
  if (err) throw err; 
  let token=data.toString();
  token=token.replace(/(\r\n|\n|\r)/gm, "");
  console.log(token); 
  client.login(token);
});