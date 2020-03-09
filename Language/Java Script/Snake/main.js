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
const size = 40
const snake = []
let tailSize = 5

function draw (event) {
  content.fillStyle = '#000000'
  content.fillRect(0, 0, width, height)
  content.fillStyle = 'lime'
  content.fillRect(px * width / size, py * height / size, (width / size) - 1, (width / size) - 1)
  content.fillStyle = 'white'
  content.fillRect(bx * width / size, by * height / size, (width / size) - 1, (width / size) - 1)
  px += ax
  py -= ay
  px = px < 0 ? size - 1 : px >= size ? 0 : px
  py = py < 0 ? size - 1 : py >= size ? 0 : py
  if (px === bx && py === by) {
    tailSize++
    bx = Math.floor(Math.random() * size)
    by = Math.floor(Math.random() * size)
  }
  snake.forEach(element => {
    if (px === element.x && py === element.y) {
      tailSize = 5
      ax = 0
      ay = 0
    }
    content.fillStyle = 'lime'
    content.fillRect(element.x * width / size, element.y * height / size, (width / size) - 1, (width / size) - 1)
  })
  snake.push({ x: px, y: py })
  while (snake.length >= tailSize) {
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
  }
})
function start () {
  game = document.getElementById('game')
  content = game.getContext('2d')
  width = game.width
  height = game.height
  content.fillStyle = '#000000'
  content.fillRect(0, 0, width, height)
  setInterval(draw, 144)
}
