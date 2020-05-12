class Board {
  constructor(sizeX, sizeY, level, nop) {
    this.lost = false;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.cells = [];
    this.level = level;
    this.numberOfPoints = nop;
    this.lostAnimation = -1;
    for (let i = 0; i < sizeX; i++) {
      for (let j = 0; j < sizeY; j++) {
        if (i == 0 || j == 0 || j == sizeY - 1 || i == sizeX - 1) {
          this.cells.push(new Cell(i, j, Cell.cellTypes().Border));
        } else if ((j % 7 == 0 && i < sizeX - 5 && j % 14 != 0) || (j % 14 == 0 && i > 5)) {
          this.cells.push(new Cell(i, j, Cell.cellTypes().Wall));
        } else {
          this.cells.push(new Cell(i, j, Cell.cellTypes().Dirt));
        }
      }
    }
    for (let i = 0, count = this.cells.length / this.level; i < count; i++) {
      this.getRandom([Cell.cellTypes().Border.id, Cell.cellTypes().Wall.id]).type = Cell.cellTypes().Stone;
    }
    for (let i = 0; i < this.numberOfPoints; i++) {
      this.getRandom([Cell.cellTypes().Border.id, Cell.cellTypes().Wall.id, Cell.cellTypes().Stone.id]).type = Cell.cellTypes().Point;
    }
  }

  getRandom() {
    let x = Math.floor(Math.random() * (this.sizeX - 2)) + 1;
    let y = Math.floor(Math.random() * (this.sizeY - 2)) + 1;
    return this.getCell(x, y);
  }

  getRandom(notType) {
    let from = this.cells.filter((x) => !notType.some((b) => x.type.id == b));
    return from[Math.floor(Math.random() * from.length)];
  }

  getCell(x, y) {
    if (x >= this.sizeX || y >= this.sizeY || x < 0 || y < 0) {
      throw new Error("Out of board");
    }
    return this.cells[x * this.sizeY + y];
  }
  setCell(x, y, cell) {
    this.cells[x * this.sizeY + y] = cell;
  }
  draw(cnt) {
    if (this.lostAnimation >= 0 && this.lostAnimation < 60) {
      if (this.lostAnimation == 0) {
        this.lost = true;
      } else {
        let player = this.cells.filter((x) => x.type.id == Cell.cellTypes().Player.id)[0];
        for (let i = 0; i < 3; i++) {
          for (let j = 0; j < 3; j++) {
            let explCell = this.getCell(player.x + i - 1, player.y + j - 1);
            if (explCell.type.id != Cell.cellTypes().Wall.id && explCell.type.id != Cell.cellTypes().Border.id) {
              explCell.drawExplosion(cnt, cnt.canvas.width / this.sizeX, cnt.canvas.height / this.sizeY, this.lostAnimation % 2);
            }
          }
        }
      }
    } else {
      cnt.fillStyle = "#000";
      cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
      this.cells.forEach((cell) => {
        cell.draw(cnt, cnt.canvas.width / this.sizeX, cnt.canvas.height / this.sizeY);
      });
    }
    if (this.lostAnimation > 0) {
      this.lostAnimation--;
    }
  }
  update() {
    if (this.lostAnimation < 0) {
      for (let i = 1; i < this.sizeY - 1; i++) {
        for (let j = 1; j < this.sizeX - 1; j++) {
          if (!this.lost) {
            this.getCell(j, i).update(this);
          }
        }
      }
      this.cells.forEach((x) => {
        x.updated = false;
      });
    }
  }
  gameover() {
    this.cells.forEach((x) => {
      x.updated = true;
    });
    this.lostAnimation = 15;
  }
}
