/* eslint-disable space-before-function-paren */
const canvas = document.getElementById("canvas");
const content = canvas.getContext("2d");

let width;
let height;
document.addEventListener("keydown", keyDown);
document.addEventListener("keyup", keyUp);
window.onresize = restart;
canvas.onmousedown = mouseDown;
document.onmouseup = mouseUp;
canvas.onmousemove = mouseMove;

var cursorX;
var cursorY;
let typeToChange = Cell.cellTypes.Life;
let canvasMouseIsDown = false;
let game = null;

function mouseMove(e) {
  cursorX = e.clientX;
  cursorY = e.clientY;
}
function keyUp(event) {
  switch (event.which) {
    case 17: // Controll key
      typeToChange = Cell.cellTypes.Life;
      break;
  }
}
function keyDown(event) {
  switch (event.which) {
    case 17: // Controll key
      typeToChange = Cell.cellTypes.Dead;
      break;
    case 32: // Space
      if (game !== null) {
        if (!game.stopInterval()) {
          game.startInterval(content, 75);
        }
      }
      break;
    case 82: // R
      draw();
      break;
    case 67: // C
      restart();
  }
}
function mouseDown(event) {
  canvasMouseIsDown = true;
  changing();
}
function changing() {
  let x = cursorX - canvas.offsetLeft;
  let y = cursorY - canvas.offsetTop;
  x = Math.floor(x / game.board.dens);
  y = Math.floor(y / game.board.dens);
  try {
    game.board.changeTypeCell(x, y, typeToChange);
    game.board.getCell(x, y).draw(content, game.board.dens);
  } catch (e) {
    // console.log(e)
  }
  if (canvasMouseIsDown) setTimeout(changing, 10);
}
function mouseUp() {
  canvasMouseIsDown = false;
}

function draw() {
  game.draw(content);
}

function windowResize() {
  width = window.innerWidth;
  height = window.innerHeight;
  canvas.width = width;
  canvas.height = height;
  game = new GameOfLife(new Board(width, height, 50));
  return true;
}

function restart() {
  if (game != null) {
    game.stopInterval();
  }
  start();
}

function start() {
  windowResize();
  draw();
}

start();
