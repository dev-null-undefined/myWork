/* eslint-disable space-before-function-paren */
class dijkstra extends PathFind {
  constructor(board) {
    super(board)
    this.board.cells.forEach(element => (element.dist = Infinity))
    this.done = false
    this.visited = []
    this.currentCell = null
    const startCell = board.cells.filter(c => (c !== null && c.type === Cell.cellTypes.Start ? c : null))[0]
    this.finish = board.cells.filter(c => (c !== null && c.type === Cell.cellTypes.Finish ? c : null))[0]
    startCell.dist = 0
  }

  update() {
    if (this.done) {
      return true
    }
    this.currentCell = this.board.cells.reduce(
      (a, b) => (b.dist < a.dist ? (!this.visited.includes(b) ? b : a) : !this.visited.includes(a) ? a : b),
      new Cell(0, 0)
    )
    const wasIn = this.visited.includes(this.currentCell)
    this.visited.push(this.currentCell)
    const x = this.currentCell.x
    const y = this.currentCell.y
    const dist = this.currentCell.dist + 1
    this.board.getNeighbor(x, y, false).forEach(element => {
      if (!this.visited.includes(element) && element.type !== Cell.cellTypes.Wall) {
        element.dist = dist
        element.prev = this.currentCell
      }
      if (element.type === Cell.cellTypes.Finish) {
        this.done = true
      }
    })
    return !wasIn
  }

  draw(cnt) {
    if (this.done) {
      this.board.draw(cnt)
      let before = this.finish
      do {
        cnt.fillStyle = "#0F0"
        cnt.fillRect(
          before.x * this.board.dens,
          before.y * this.board.dens,
          this.board.dens * 0.9,
          this.board.dens * 0.9
        )
        before = before.prev
      } while (before.type !== Cell.cellTypes.Start)
      this.intervalStop()
      return
    }
    const farest = this.visited.reduce((a, b) => (b.dist > a.dist && b.dist !== Infinity ? b : a))
    this.visited.forEach(element => {
      if (element === this.currentCell) {
        cnt.fillStyle = "#0F0"
      } else {
        cnt.fillStyle =
          element.dist === Infinity
            ? "#000"
            : "rgb(" +
              (element.dist / farest.dist) * 255 +
              ", " +
              (element.dist / farest.dist) * 255 +
              ", " +
              (element.dist / farest.dist) * 255 +
              ")"
      }
      cnt.fillRect(
        element.x * this.board.dens,
        element.y * this.board.dens,
        this.board.dens * 0.9,
        this.board.dens * 0.9
      )
    })
  }

  updateAndDraw(cnt, thas) {
    thas.update()
    thas.draw(cnt)
  }

  intervalStart(cnt, speed) {
    if (this.interval === null) {
      this.interval = setInterval(this.updateAndDraw, speed, cnt, this)
    }
  }

  intervalStop() {
    if (this.interval !== null) {
      clearInterval(this.interval)
      this.interval = null
    }
  }
}
