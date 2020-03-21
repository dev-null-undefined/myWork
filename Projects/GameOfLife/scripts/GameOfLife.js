class GameOfLife {
  constructor(board) {
    this.board = board
    this.interval = null
  }

  update() {
    this.nextState = this.board.copy()
    this.nextState.setAll(Cell.cellTypes.Dead)
    // console.log(this.nextState)
    this.board.cells.forEach((element, index) => {
      const x = element.x
      const y = element.y
      const lifeNeibr = this.board.getNeighbor(x, y, true).filter(cell => cell.type === Cell.cellTypes.Life)
      if (element.type === Cell.cellTypes.Life) {
        if (lifeNeibr.length > 3) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Dead)
        } else if (lifeNeibr.length < 2) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Dead)
        } else {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Life)
        }
      } else {
        if (lifeNeibr.length == 3) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Life)
        }
      }
    })
    this.board = this.nextState
  }

  draw(cnt) {
    this.board.draw(cnt)
  }

  updateAndDraw(cnt, thas) {
    thas.update()
    thas.draw(cnt)
  }

  startInterval(cnt, speed) {
    if (this.interval === null) {
      this.interval = setInterval(this.updateAndDraw, speed, cnt, this)
    }
  }

  stopInterval() {
    if (this.interval !== null) {
      clearInterval(this.interval)
      this.interval = null
      return true
    }
    return false
  }
}
