const Discord = require('discord.js')
const client = new Discord.Client()

client.on('ready', () => {
  console.log(`Logged in as ${client.user.tag}!`)
})

client.on('message', msg => {
  if (msg.content === 'ping') {
    msg.reply('Pong!')
  }
})

client.login('NjU4NDY3MjYxNTMwNTA1MjE3.XgAMgQ.udDD_dcGt_zywi-O26QFB7b758k')
