/* eslint-disable space-before-function-paren */
class Cell {
  constructor(x, y) {
    this.x = x
    this.y = y
    this.type = Cell.cellTypes.Air
  }

  draw(cnt, size) {
    cnt.fillStyle = this.getFill()
    cnt.fillRect(this.x * size, this.y * size, size * 0.9, size * 0.9)
  }

  getFill() {
    switch (this.type) {
      case Cell.cellTypes.Life:
        return "#FFF"
      case Cell.cellTypes.Dead:
        return "#000"
    }
  }

  copy() {
    const newCell = new Cell(this.x, this.y)
    newCell.type = this.type
    return newCell
  }
}
Cell.cellTypes = {
  Life: 0,
  Dead: 1
}
class Board {
  constructor(width, height, dens) {
    this.width = Math.floor(width / dens)
    this.height = Math.floor(height / dens)
    this.dens = dens
    this.cells = []
    for (let i = 0; i < this.height; i++) {
      for (let j = 0; j < this.width; j++) {
        this.cells.push(new Cell(j, i))
      }
    }
  }

  getNeighbor(x, y, diagonal) {
    const neibrs = []
    if (diagonal) {
      if (x < this.width - 1 && y > 0) {
        neibrs.push(this.getCell(x + 1, y - 1))
      }
      if (x < this.width - 1 && y < this.height - 1) {
        neibrs.push(this.getCell(x + 1, y + 1))
      }
      if (x > 0 && y > 0) {
        neibrs.push(this.getCell(x - 1, y - 1))
      }
      if (x > 0 && y < this.height - 1) {
        neibrs.push(this.getCell(x - 1, y + 1))
      }
    }
    if (x < this.width - 1) {
      neibrs.push(this.getCell(x + 1, y))
    }
    if (x > 0) {
      neibrs.push(this.getCell(x - 1, y))
    }
    if (y > 0) {
      neibrs.push(this.getCell(x, y - 1))
    }
    if (y < this.height - 1) {
      neibrs.push(this.getCell(x, y + 1))
    }
    return neibrs
  }

  getCell(x, y) {
    if (x >= this.width || y >= this.height || x < 0 || y < 0) {
      throw new Error("Out of board")
    }
    return this.cells[y * this.width + x]
  }

  editCell(x, y, cell) {
    if (x >= this.width || y >= this.height || x < 0 || y < 0) {
      throw new Error("Out of board")
    }
    this.cells[y * this.width + x] = cell
    return true
  }

  changeTypeCell(x, y, type) {
    if (x >= this.width || y >= this.height || x < 0 || y < 0) {
      throw new Error("Out of board")
    }
    let isType = false
    Object.keys(Cell.cellTypes).forEach(key => {
      isType = Cell.cellTypes[key] === type ? true : isType
    })
    if (!isType) throw new Error("Invalide type")
    this.cells[y * this.width + x].type = type
    return true
  }

  draw(cnt) {
    cnt.fillStyle = "#000"
    cnt.fillRect(0, 0, this.width * this.dens, this.height * this.dens)
    this.cells.forEach(cell => {
      cell.draw(cnt, this.dens)
    })
  }

  copy() {
    const newBoard = new Board(this.width * this.dens, this.height * this.dens, this.dens)
    this.cells.forEach((cell, index) => (newBoard.cells[index] = cell.copy()))
    return newBoard
  }

  setAll(type) {
    let isType = false
    Object.keys(Cell.cellTypes).forEach(key => {
      isType = Cell.cellTypes[key] === type ? true : isType
    })
    if (!isType) throw new Error("Invalide type")
    this.cells.forEach(cell => (cell.type = type))
  }
}
