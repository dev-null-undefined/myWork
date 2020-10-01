// const { Client, MessageEmbed } = require("discord.js");

// const bot = new Client();

// const token = "NjU4NDY3MjYxNTMwNTA1MjE3.XgALbA.7I_udn-BHvcXRBiLUvtXJ51Sgcg";

// const PREFIX = "p!";

// bot.on("ready", () => {
//   console.log("the poll bot is online");
//   let channel = null;
//   console.log(
//     bot.channels
//       .fetch("759084374363865099")
//       .then((x) => (channel = x))
//       .catch(console.error)
//   );
//   setInterval(() => channel.send("*images"), 1000000);
// });
// bot.login(token);
// request(options, function (error, response, body) {
//   if (error) throw new Error(error);

//   console.log(body);
// });

const request = require("request");

module.exports = {
  name: "image",
  // description: "this is a ping command!", *image dog
  execute(message, args) {
    let searchFor = "dog";
    if (args[0]) {
      searchFor = args.join("+");
    }
    var options = {
      url:
        "https://customsearch.googleapis.com/customsearch/v1?cx=30b44659deafe545f&key=AIzaSyARhm6ryz9n-wxIyKadnZboyHJhGqOjxQE&searchType=image&q=" +
        searchFor +
        "&start=" +
        Math.floor(Math.random() * 50),
      method: "GET",
      headers: {
        Accept: "text/html",
        "User-Agent": "Chrome",
      },
    };

    request(options, function (error, response, responseBody) {
      if (error) {
        return;
      }
      let items = JSON.parse(responseBody).items;
      let randomItem = items[Math.floor(Math.random() * items.length)];
      message.channel.send(randomItem.link);
    });
  },
};
