/* eslint-disable space-before-function-paren */
const canvas = document.getElementById("canvas")
const content = canvas.getContext("2d")
const menu = document.getElementById("menu")

let width
let height
let board
document.addEventListener("keydown", keyDown)
document.addEventListener("keyup", keyUp)
window.onresize = start
canvas.onmousedown = mouseDown
document.onmouseup = mouseUp
canvas.onmousemove = mouseMove
canvas.oncontextmenu = onContext

var cursorX
var cursorY
let lastBoardX = null
let lastBoardY = null
let typeToChange = Cell.cellTypes.Wall
let canvasMouseIsDown = false
let pathFind = null
let speed = { speed: 15 }
let speedDif = null
let speedMin = 5
let speedMax = 250
let speedInterval = null

function mouseMove(e) {
  cursorX = e.clientX
  cursorY = e.clientY
}
function keyUp(event) {
  switch (event.which) {
    case 38: // Up
      if (speedInterval !== null) {
        clearInterval(speedInterval)
        speedInterval = null
      }
      break
    case 40: // Down
      if (speedInterval !== null) {
        clearInterval(speedInterval)
        speedInterval = null
      }
      break
  }
}
function keyDown(event) {
  switch (event.which) {
    case 32: // Space
      if (pathFind !== null) {
        pathFind.intervalStop()
        pathFind = null
        draw()
      } else {
        if (algorithm != null) {
          switch (algorithm.innerText) {
            case "dijkstra":
              pathFind = new dijkstra(board)
              break
            case "A*":
              pathFind = new aStar(board)
              break
          }
          pathFind.intervalStart(content, speed)
        }
      }
      break
    case 83: // S
      break
    case 82: // R
      draw()
      break
    case 67: // C
      if (pathFind != null) {
        pathFind.intervalStop()
      }
      start()
      break
    case 38: // Up
      speedDif = -1
      if (speedInterval === null) {
        speedInterval = setInterval(updateSpeed, 5)
      }
      break
    case 40: // Down
      speedDif = 1
      if (speedInterval === null) {
        speedInterval = setInterval(updateSpeed, 5)
      }
      break
  }
}
function updateSpeed() {
  speed.speed = Math.min(Math.max(speed.speed + speedDif, speedMin), speedMax)
}
function onContext(event) {}
function mouseDown(event) {
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
    draw()
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
    Math.min(x - board.dens / 2, board.width * board.dens),
    Math.min(y - board.dens / 2, board.height * board.dens),
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
function changing(frst = true) {
  let x = cursorX - canvas.offsetLeft
  let y = cursorY - canvas.offsetTop
  x = Math.floor(x / board.dens)
  y = Math.floor(y / board.dens)
  try {
    const cellToChange = board.getCell(x, y)
    if (frst) {
      if (cellToChange.type === Cell.cellTypes.Air) {
        typeToChange = Cell.cellTypes.Wall
      }
      if (cellToChange.type === Cell.cellTypes.Wall) {
        typeToChange = Cell.cellTypes.Air
      }
    }
    if (cellToChange.type === Cell.cellTypes.Air || cellToChange.type === Cell.cellTypes.Wall) {
      board.changeTypeCell(x, y, typeToChange)
      board.getCell(x, y).draw(content, board.dens)
    }
  } catch (e) {
    // console.log(e)
  }
  if (canvasMouseIsDown) setTimeout(changing, 10, false)
}
function mouseUp() {
  canvasMouseIsDown = false
}

function draw() {
  board.draw(content)
}

function windowResize() {
  width = window.innerWidth - 10
  height = window.innerHeight - 10
  canvas.width = width
  canvas.height = height
  if (pathFind !== null) {
    pathFind.intervalStop()
    pathFind = null
    draw()
  }
  return true
}

function start() {
  windowResize()
  board = new Board(width, height, 40)
  board.changeTypeCell(0, 0, Cell.cellTypes.Start)
  board.changeTypeCell(board.width - 1, board.height - 1, Cell.cellTypes.Finish)
  draw()
}
start()
