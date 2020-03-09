window.onload = start

let game
let content
let width
let height
let ax = 0
let ay = 0
let px = 0
let py = 0
let bx = 25
let by = 25
const sizeX = 192
const sizeY = 108
const snake = []
let tailSize = 5
let speed = false

function draw (event) {
  content.fillStyle = '#000000'
  content.fillRect(0, 0, width, height)
  content.fillStyle = 'lime'
  content.fillRect(px * width / sizeX, py * height / sizeY, (width / sizeX) - 1, (height / sizeY) - 1)
  content.fillStyle = 'white'
  content.fillRect(bx * width / sizeX, by * height / sizeY, (width / sizeX) - 1, (height / sizeY) - 1)
  px += ax
  py -= ay
  px = px < 0 ? sizeX - 1 : px >= sizeX ? 0 : px
  py = py < 0 ? sizeY - 1 : py >= sizeY ? 0 : py
  if (px === bx && py === by) {
    tailSize++
    bx = Math.floor(Math.random() * sizeX)
    by = Math.floor(Math.random() * sizeY)
  }
  snake.forEach(element => {
    if (px === element.x && py === element.y) {
      tailSize = 1
      ax = 0
      ay = 0
    }
    content.fillStyle = 'lime'
    content.fillRect(element.x * width / sizeX, element.y * height / sizeY, (width / sizeX) - 1, (height / sizeY) - 1)
  })
  snake.push({ x: px, y: py })
  while (snake.length > tailSize) {
    snake.shift()
  }
}

window.addEventListener('keydown', (event) => {
  switch (event.which) {
    case 37:
      ax = -1
      ay = 0
      break
    case 38:
      ax = 0
      ay = 1
      break
    case 39:
      ax = 1
      ay = 0
      break
    case 40:
      ax = 0
      ay = -1
      break
    case 32:
      speed = true
      break
  }
})
window.addEventListener('keyup', env => {
  switch (env.which) {
    case 32:
      speed = false
      break
  }
})
function start () {
  game = document.getElementById('game')
  content = game.getContext('2d')
  width = game.width
  height = game.height
  content.fillStyle = '#000000'
  content.fillRect(0, 0, width, height)
  setTimeout(drawRefresh, 144)
}
function drawRefresh () {
  draw()
  speed ? setTimeout(drawRefresh, 20) : setTimeout(drawRefresh, 50)
}
