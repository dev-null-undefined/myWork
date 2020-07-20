function addName(e) {
  console.log(e);
  e.preventDefault();
  let list = document.getElementById("list");
  let li = document.createElement("li");
  li.innerText = e.target.name.value.toUpperCase();
  list.appendChild(li);
  e.target.name.value = "";
  return false;
}
let players = [];
let playersSave = [];
let randomIndex = 0;
function shuffle(array) {
  array.sort(() => Math.random() - 0.5);
}
function startGame() {
  document.getElementById("preGame").style.display = "none";
  document.getElementById("game").style.display = "block";
  let list = document.getElementById("list");
  list.childNodes.forEach((element) => {
    players.push(element.innerText);
  });
  shuffle(players);
  shuffle(players);
  let finishedPlayers = [];
  players.forEach((element, index) => {
    let toKillIndex = index;
    toKillIndex++;
    if (toKillIndex == players.length) {
      toKillIndex = 0;
    }
    finishedPlayers.push({ name: element, toKill: players[toKillIndex] });
  });
  players = finishedPlayers;
  playersSave = players;
  randomIndex = Math.round(Math.random() * (players.length - 1));
  document.getElementById("name").innerText = players[randomIndex].name;
}
let showStatus = true;
function show() {
  showStatus = !showStatus;
  if (showStatus) {
    document.getElementById("toKill").innerText = "";
    document.getElementById("buttonShow").value = "show";
  } else {
    document.getElementById("toKill").innerText = players[randomIndex].toKill;
    document.getElementById("buttonShow").value = "hide";
  }
}
function nextPlayer() {
  if (!showStatus) {
    show();
  }
  players.splice(randomIndex, 1);

  if (players.length == 0) {
    document.getElementById("game").style.display = "none";
    document.getElementById("start").style.display = "block";
  } else {
    randomIndex = Math.round(Math.random() * (players.length - 1));
    document.getElementById("name").innerText = players[randomIndex].name;
  }
}
