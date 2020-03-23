class GameOfLife {
  constructor(board) {
    this.board = board;
    this.interval = null;
  }

  update() {
    this.nextState = this.board.copy();
    this.nextState.setAll(Cell.cellTypes.Dead);
    // console.log(this.nextState)
    this.board.cells.forEach((element, index) => {
      const x = element.x;
      const y = element.y;
      const lifeNeibr = this.board.getNeighbor(x, y, true).filter(cell => cell.type === Cell.cellTypes.Life);
      if (element.type === Cell.cellTypes.Life) {
        if (lifeNeibr.length > 3) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Dead);
        } else if (lifeNeibr.length < 2) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Dead);
        } else {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Life);
        }
      } else {
        if (lifeNeibr.length == 3) {
          this.nextState.changeTypeCell(x, y, Cell.cellTypes.Life);
        }
      }
    });
    this.board = this.nextState;
  }

  draw(cnt) {
    this.board.draw(cnt);
  }

  updateAndDraw(cnt) {
    this.update();
    this.draw(cnt);
  }

  startInterval(cnt, speed) {
    if (this.interval === null) {
      this.interval = speed;
      this.repeatInterval(cnt, this);
    }
  }

  repeatInterval(cnt, thas) {
    thas.updateAndDraw(cnt);
    if (thas.interval !== null) {
      setTimeout(thas.repeatInterval, thas.interval.speed, cnt, thas);
    }
  }
  stopInterval() {
    if (this.interval !== null) {
      this.interval = null;
      return true;
    }
    return false;
  }
}
