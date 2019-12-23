const Discord = require("discord.js");
const client = new Discord.Client();

const bash = new RegExp("^!run bash");
//const python = new RegExp("^!run python");

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

const { exec } = require("child_process");
let msgText;
client.on("message", msg => {
  if (bash.test(msg.content)) {
    let text = msg.content;
    if (text.endsWith("`")) {
      text = text.substring(12, text.length - 3);
    }else{
      text = text.substring(9, text.length);
    }
    msgText = "";
    msgText += "Running :``` " + text + " ``` ";
    exec("cd / && " + text, (err, stdout, stderr) => {
      if (err) {
        //some err occurred
        msgText += "Error:``` " + err + " ``` ";
      } else {
        // the *entire* stdout and stderr (buffered)
        if (stdout) {
          msgText += "Response:``` " + stdout + " ``` ";
        }
        if (stderr) {
          msgText += "Response:``` " + stderr + " ``` ";
        }
      }
      msg.reply(msgText);
    });
  }else{
  }
});

client.login("NjU4NDY3MjYxNTMwNTA1MjE3.XgAT5g.Xd2grtkmZMEwyeJASL0rysNW2xk");
