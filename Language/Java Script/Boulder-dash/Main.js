const canvas = document.getElementById("canvas");
const content = canvas.getContext("2d");
const clearId = "1";
let moveVect = {
  x: 0,
  y: 0,
  lastX: 0,
  lastY: 0,
  currX: 0,
  currY: 0,
  delX: 0,
  delY: 0,
};
let delteOnly = false;
let paused = false;
if (getCookie("clearId") != clearId) {
  let allCookies = getCookies();
  for (var key in allCookies) {
    document.cookie = key + "= ; expires = Thu, 01 Jan 1970 00:00:00 GMT;path=/";
  }
  setCookie("clearId", clearId, 365);
}
let game = new Game(35, 22, moveVect);

function keyDown(event) {
  switch (event.which) {
    case 27:
      paused = !paused;
      if (!paused) {
        updateAndDraw();
      } else {
        content.font = "30px Arial";
        content.fillText("Paused", 0, 30);
      }
      break;
    case 38:
      if (delteOnly) {
        moveVect.delX = 0;
        moveVect.delY = -1;
      } else {
        if (moveVect.currY != -1) {
          moveVect.currX = 0;
          moveVect.currY = -1;
          moveVect.lastX = 0;
          moveVect.lastY = -1;
        }
      }
      break;
    case 40:
      if (delteOnly) {
        moveVect.delX = 0;
        moveVect.delY = 1;
      } else {
        if (moveVect.currY != 1) {
          moveVect.currX = 0;
          moveVect.currY = 1;
          moveVect.lastX = 0;
          moveVect.lastY = 1;
        }
      }
      break;
    case 39:
      if (delteOnly) {
        moveVect.delX = 1;
        moveVect.delY = 0;
      } else {
        if (moveVect.currX != 1) {
          moveVect.currX = 1;
          moveVect.currY = 0;
          moveVect.lastX = 1;
          moveVect.lastY = 0;
        }
      }
      break;
    case 37:
      if (delteOnly) {
        moveVect.delX = -1;
        moveVect.delY = 0;
      } else {
        if (moveVect.currX != -1) {
          moveVect.currX = -1;
          moveVect.currY = 0;
          moveVect.lastX = -1;
          moveVect.lastY = 0;
        }
      }
      break;
    case 82:
      game.startLevel(game.level, 15);
      break;
    case 32:
      delteOnly = true;
      break;
  }
}
function keyUp(event) {
  switch (event.which) {
    case 32:
      moveVect.delX = 0;
      moveVect.delY = 0;
      delteOnly = false;
      break;
    case 37:
      moveVect.currX = 0;
      break;
    case 38:
      moveVect.currY = 0;
      break;
    case 39:
      moveVect.currX = 0;
      break;
    case 40:
      moveVect.currY = 0;
      break;
  }
}
function setSizes(c, x, y) {
  let yMax = (x / game.board.sizeX) * game.board.sizeY;
  yMax = Math.min(y, yMax);
  c.width = (yMax / game.board.sizeY) * game.board.sizeX * 0.9;
  c.height = yMax * 0.9;
}
function updateAndDraw() {
  if (!paused) {
    game.update();
    game.draw(content);
    setTimeout(updateAndDraw, 100);
  }
}
setSizes(canvas, window.innerWidth, window.innerHeight);
updateAndDraw();
window.onresize = () => setSizes(canvas, window.innerWidth, window.innerHeight);
window.onkeydown = (e) => keyDown(e);
window.onkeyup = (e) => keyUp(e);
// Nuzky
// level creator?
