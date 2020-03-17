/* eslint-disable space-before-function-paren */
const canvas = document.getElementById("canvas")
const content = canvas.getContext("2d")

let width
let height
let board
document.addEventListener("keydown", keyDown)
document.addEventListener("keyup", keyUp)
window.onresize = windowResize && draw
canvas.onmousedown = mouseDown
document.onmouseup = mouseUp
canvas.onmousemove = mouseMove

var cursorX
var cursorY
let lastBoardX = null
let lastBoardY = null
let typeToChange = Cell.cellTypes.Wall
let canvasMouseIsDown = false

function mouseMove(e) {
  cursorX = e.clientX
  cursorY = e.clientY
}
function keyUp(event) {
  switch (event.which) {
    case 17: // Controll key
      typeToChange = Cell.cellTypes.Wall
      break
  }
}
function keyDown(event) {
  switch (event.which) {
    case 17: // Controll key
      typeToChange = Cell.cellTypes.Air
      break
  }
}
function mouseDown(params) {
  canvasMouseIsDown = true
  const x = cursorX - canvas.offsetLeft
  const y = cursorY - canvas.offsetTop
  const cellUnderCursor = board.getCell(Math.floor(x / board.dens), Math.floor(y / board.dens))
  // console.log(cellUnderCursor)
  if (cellUnderCursor.type === Cell.cellTypes.Air || cellUnderCursor.type === Cell.cellTypes.Wall) {
    changing()
  } else {
    lastBoardX = null
    lastBoardY = null
    moving(cellUnderCursor)
  }
}
function moving(cell) {
  const x = cursorX - canvas.offsetLeft
  const y = cursorY - canvas.offsetTop
  const boardX = Math.floor(x / board.dens)
  const boardY = Math.floor(y / board.dens)
  const size = 2
  if (lastBoardX !== null) {
    if (boardX >= lastBoardX) {
      if (boardY >= lastBoardY) {
        board.drawBox(boardX + size, boardY + size, lastBoardX - size, lastBoardY - size, content)
      } else {
        board.drawBox(boardX + size, boardY - size, lastBoardX - size, lastBoardY + size, content)
      }
    } else if (boardY >= lastBoardY) {
      board.drawBox(boardX - size, boardY + size, lastBoardX + size, lastBoardY - size, content)
    } else {
      board.drawBox(boardX - size, boardY - size, lastBoardX + size, lastBoardY + size, content)
    }
  }
  content.fillStyle = cell.getFill()
  content.fillRect(
    x - board.dens / 2,
    y - board.dens / 2,
    Math.min(board.dens, board.width * board.dens - (x - board.dens / 2)),
    Math.min(board.dens, board.height * board.dens - (y - board.dens / 2))
  )
  if (canvasMouseIsDown) {
    setTimeout(moving, 10, cell)
  } else {
    try {
      const saveType = cell.type
      board.changeTypeCell(cell.x, cell.y, board.getCell(boardX, boardY).type)
      board.changeTypeCell(boardX, boardY, saveType)
      draw()
    } catch (error) {}
  }
  lastBoardX = boardX
  lastBoardY = boardY
}
function changing() {
  let x = cursorX - canvas.offsetLeft
  let y = cursorY - canvas.offsetTop
  x = Math.floor(x / board.dens)
  y = Math.floor(y / board.dens)
  try {
    const cellToChange = board.getCell(x, y)
    if (cellToChange.type === Cell.cellTypes.Air || cellToChange.type === Cell.cellTypes.Wall) {
      board.changeTypeCell(x, y, typeToChange)
      board.getCell(x, y).draw(content, board.dens)
    }
  } catch (e) {
    // console.log(e)
  }
  if (canvasMouseIsDown) setTimeout(changing, 10)
}
function mouseUp() {
  canvasMouseIsDown = false
}

function draw() {
  board.draw(content)
}

function windowResize() {
  width = window.innerWidth
  height = window.innerHeight
  canvas.width = width
  canvas.height = height
  return true
}

function start() {
  windowResize()
  board = new Board(width, height, 10)
  board.changeTypeCell(9, 4, Cell.cellTypes.Start)
  draw()
}

start()
