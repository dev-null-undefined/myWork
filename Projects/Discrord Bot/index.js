/* eslint-disable space-before-function-paren */
const Discord = require("discord.js");
const client = new Discord.Client();
const fs = require("fs");
const { prefix, token } = require("./config.json");
const ytdl = require("ytdl-core");

const run = new RegExp("^!run ");

const reset = new RegExp("^!linux reset");
const stop = new RegExp("^!linux stop");
const shutdown = new RegExp("^!linux shutdown");
const start = new RegExp("^!linux start");
const restore = new RegExp("^!linux restore");
const status = new RegExp("^!linux status");

const queue = new Map();

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

const { exec } = require("child_process");
let currentMsg;

let msgText;

function appendToFile(fileName, text) {
  fs.appendFile(fileName, text, (err) => {
    if (err) throw err;
  });
}
function writeToFile(fileName, text) {
  fs.writeFile(fileName, text, (err) => {
    if (err) throw err;
  });
}

client.on("message", (msg) => {
  if (run.test(msg.content)) {
    var currentdate = new Date();
    var datetime =
      "Date-time: " +
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
  // HELP
  if (msg.content === "!linux help") {
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
    let highestRole = msg.member.roles.highest;
    if ((msg.member != null && highestRole.name === "Admin") || highestRole.name === "Owner" || highestRole.name === "Administrator") {
      currentdate = new Date();
      datetime =
        "Date-time: " +
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
          client.user.setPresence({
            status: "idle",
            game: {
              name: "!linux help !run is REBOOTING",
              type: "LISTENING",
            },
          });
          exec("virsh destroy debian10; virsh start debian10", myMethod);

          break;
        case start.test(msg.content):
          exec("virsh start debian10", myMethod);
          break;
        case stop.test(msg.content):
          client.user.setPresence({
            status: "idle",
            game: {
              name: "!linux help !run is poweredOff",
              type: "LISTENING",
            },
          });
          exec("virsh destroy debian10", myMethod);
          break;
        case restore.test(msg.content):
          client.user.setPresence({
            status: "dnd",
            game: {
              name: "Restoring!",
              type: "LISTENING",
            },
          });
          msg.reply("This can take up to 10 minutes please wait.");
          exec(
            "virsh destroy debian10; virsh undefine debian10 && rm -f /home/martin/debian10 &&  virt-clone --original debian10-clone --name debian10 --auto-clone && virsh start debian10",
            myMethodRestore
          );
          break;
        case shutdown.test(msg.content):
          client.user.setPresence({
            status: "idle",
            game: {
              name: "!linux help !run is poweredOff",
              type: "LISTENING",
            },
          });
          exec("virsh shutdown debian10", myMethod);
          break;
        case status.test(msg.content):
          exec("virsh list --all | grep 'debian10 '", myMethod);
          break;
      }
    } else {
      msg.reply("You dont have permition to do this. Ask admin for perm.");
    }
  } else if (!run.test(msg.content)) {
    if (msg.author.bot) return;
    if (!msg.content.startsWith(prefix)) return;

    const serverQueue = queue.get(msg.guild.id);

    if (msg.content.startsWith(`${prefix}play`)) {
      execute(msg, serverQueue);
    } else if (msg.content.startsWith(`${prefix}skip`)) {
      skip(msg, serverQueue);
    } else if (msg.content.startsWith(`${prefix}stop`)) {
      stopPlaying(msg, serverQueue);
    } else if (msg.content.startsWith(`${prefix}volume`)) {
      volume(msg, serverQueue);
    } else if (msg.content.startsWith(`${prefix}pause`)) {
      pause(msg, serverQueue);
    } else if (msg.content.startsWith(`${prefix}resume`)) {
      resume(msg, serverQueue);
    } else {
      msg.channel.send("You need to enter a valid command!");
    }
  }
});
function replyThis(msgText) {
  if (msgText.length > 2000) {
    writeToFile("result.txt", msgText);
    currentMsg.reply("Result is too BIG here is it in file.", {
      files: ["./result.txt"],
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

function myMethodRestore(_err, stdout, stderr) {
  client.user.setPresence({
    status: "idle",
    game: {
      name: "!linux help !run is REBOOTING",
      type: "LISTENING",
    },
  });
  replyThis("DONE");
}

async function execute(message, serverQueue) {
  const args = message.content.split(" ");

  const voiceChannel = message.member.voice.channel;
  if (!voiceChannel) return message.channel.send("You need to be in a voice channel to play music!");
  const permissions = voiceChannel.permissionsFor(message.client.user);
  if (!permissions.has("CONNECT") || !permissions.has("SPEAK")) {
    return message.channel.send("I need the permissions to join and speak in your voice channel!");
  }

  const songInfo = await ytdl.getInfo(args[1]);
  const song = {
    title: songInfo.title,
    url: songInfo.video_url,
  };
  if (!serverQueue) {
    const queueContruct = {
      textChannel: message.channel,
      voiceChannel: voiceChannel,
      dispatcher: null,
      connection: null,
      songs: [],
      volume: 1,
      playing: true,
    };

    queue.set(message.guild.id, queueContruct);

    queueContruct.songs.push(song);

    try {
      var connection = await voiceChannel.join();
      queueContruct.connection = connection;
      play(message.guild, queueContruct.songs[0], parseFloat(args[2]));
    } catch (err) {
      console.log(err);
      queue.delete(message.guild.id);
      return message.channel.send(err);
    }
  } else {
    serverQueue.songs.push(song);
    return message.channel.send(`${song.title} has been added to the queue!`);
  }
}

function skip(message, serverQueue) {
  if (!message.member.voice.channel) return message.channel.send("You have to be in a voice channel to stop the music!");
  if (!serverQueue) return message.channel.send("There is no song that I could skip!");
  serverQueue.connection.dispatcher.end();
}

function stopPlaying(message, serverQueue) {
  if (!message.member.voice.channel) return message.channel.send("You have to be in a voice channel to stop the music!");
  serverQueue.songs = [];
  serverQueue.connection.dispatcher.end();
}

function play(guild, song, seekNum) {
  const serverQueue = queue.get(guild.id);
  if (!song) {
    serverQueue.voiceChannel.leave();
    queue.delete(guild.id);
    return;
  }

  const dispatcher = serverQueue.connection
    .play(ytdl(song.url), { seek: isNaN(seekNum) ? 0 : seekNum })
    .on("finish", () => {
      serverQueue.songs.shift();
      play(guild, serverQueue.songs[0]);
    })
    .on("error", (error) => console.error(error));
  serverQueue.dispatcher = dispatcher;
  dispatcher.setVolumeLogarithmic(serverQueue.volume / 5);
  serverQueue.textChannel.send(`Start playing: **${song.title}**`);
}

function volume(message, serverQueue) {
  if (serverQueue) {
    serverQueue.volume = parseFloat(message.content.split(" ")[1]);
    serverQueue.dispatcher.setVolumeLogarithmic(serverQueue.volume / 5);
  }
}

function pause(message, serverQueue) {
  if (serverQueue && !serverQueue.dispatcher.paused) {
    serverQueue.dispatcher.pause(true);
    message.reply("Paused");
  }
}
function resume(message, serverQueue) {
  if (serverQueue && serverQueue.dispatcher.paused) {
    serverQueue.dispatcher.resume();
    message.reply("Resumed");
  }
}

client.login(token);
