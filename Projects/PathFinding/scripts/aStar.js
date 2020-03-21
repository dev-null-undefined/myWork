/* eslint-disable space-before-function-paren */
class aStar extends PathFind {
  constructor(board) {
    super(board)
    this.board.cells.forEach(element => {
      element.dist = Infinity
      element.distToFinish = Infinity
    })
    this.done = false
    this.openSet = [board.cells.filter(c => (c !== null && c.type === Cell.cellTypes.Start ? c : null))[0]]
    this.currentCell = null
    this.finish = board.cells.filter(c => (c !== null && c.type === Cell.cellTypes.Finish ? c : null))[0]
    this.openSet[0].dist = 0
    this.openSet[0].distToFinish = this.openSet[0].distance(this.finish)
  }

  update() {
    if (this.done) {
      return true
    }
    if (this.openSet.length === 0) return false
    this.currentCell = this.openSet.reduce((a, b) => (b.distToFinish < a.distToFinish ? b : a))
    this.openSet = this.openSet.filter(element => element !== this.currentCell)
    const x = this.currentCell.x
    const y = this.currentCell.y
    const dist = this.currentCell.dist + 1

    this.board.getNeighbor(x, y, true).forEach(element => {
      if (!this.openSet.includes(element) && element.type !== Cell.cellTypes.Wall && element.dist >= dist) {
        element.dist = dist
        element.distToFinish = dist + this.finish.distance(element)
        element.prev = this.currentCell
        this.openSet.push(element)
      }
      if (element.type === Cell.cellTypes.Finish) {
        this.done = true
      }
    })
    return true
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
      console.log(this)
      return
    }
    const farest = this.board.cells.reduce((a, b) =>
      b.dist > a.dist ? (b.dist !== Infinity ? b : a) : a.dist !== Infinity ? a : b
    )
    this.board.cells.forEach(element => {
      if (element === this.currentCell) {
        cnt.fillStyle = "#0F0"
      } else if (element.dist !== Infinity) {
        cnt.fillStyle =
          "rgb(" + 0 + ", " + (element.dist / farest.dist) * 255 + ", " + (element.dist / farest.dist) * 255 + ")"
      } else {
        return
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
    if (thas.update()) {
      thas.draw(cnt)
    } else {
      thas.intervalStop()
    }
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
