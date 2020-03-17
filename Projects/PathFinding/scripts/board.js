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
      case Cell.cellTypes.Wall:
        return "#FFF"
      case Cell.cellTypes.Air:
        return "#000"
      case Cell.cellTypes.Finish:
        return "#F00"
      case Cell.cellTypes.Start:
        return "#0FF"
    }
  }
}
Cell.cellTypes = {
  Wall: 0,
  Air: 1,
  Finish: 2,
  Start: 3
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

  drawBoxWithSize(x, y, size, cnt) {
    const partSize = Math.floor((size - 1) / 2)
    this.drawBox(x + partSize, y + partSize, x - partSize, y - partSize, cnt)
  }

  drawBox(x, y, x1, y1, cnt) {
    let save
    if (x > x1) {
      save = x
      x = x1
      x1 = save
    }
    if (y > y1) {
      save = y
      y = y1
      y1 = save
    }
    cnt.fillStyle = "#000"
    cnt.fillRect(
      x * this.dens,
      y * this.dens,
      Math.min((x1 - x) * this.dens, this.width * this.dens - x * this.dens),
      Math.min((y1 - y) * this.dens, this.height * this.dens - y * this.dens)
    )
    for (let i = x; i <= x1; i++) {
      for (let j = y; j <= y1; j++) {
        try {
          this.getCell(i, j).draw(cnt, this.dens)
        } catch (e) {
          //   console.log(e)
        }
      }
    }
  }
}
