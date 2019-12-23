const Discord = require("discord.js");
const client = new Discord.Client();

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

const { exec } = require("child_process");
let msgText;
client.on("message", msg => {
  let regex = new RegExp("^!run ");
  if (regex.test(msg.content)) {
    let text = msg.content;
    if (text.endsWith("`")) {
      text = text.substring(8, text.length - 3);
    }else{
      text = text.substring(5, text.length);
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
  }
});

client.login("NjU4NDY3MjYxNTMwNTA1MjE3.XgAT5g.Xd2grtkmZMEwyeJASL0rysNW2xk");
