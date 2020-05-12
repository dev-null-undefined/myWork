class Player extends Cell {
  constructor(x, y, moveVect, points, minesLimit) {
    super(x, y, Cell.cellTypes().Player);
    this.minesLimit = minesLimit;
    this.divRemaining = document.getElementById("Remaining");
    this.divRemaining.innerText = this.minesLimit.toString().padStart(3);
    this.divRemaining.style.color = "#0F0";
    this.updated = false;
    this.points = points;
    this.mined = 0;
    this.moveVect = moveVect;
    this.finished = false;
    this.stonesCanBeMined = 1;
  }
  update(b) {
    if (this.updated) {
      return;
    }
    this.updated = true;

    if (this.moveVect.delX != 0 || this.moveVect.delY != 0) {
      let cellToMove = b.getCell(this.x + this.moveVect.delX, this.y + this.moveVect.delY);
      if (cellToMove.type.id == Cell.cellTypes().Dirt.id) {
        this.minedDirt();
        b.setCell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, new Cell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, Cell.cellTypes().Air));
      } else if (cellToMove.type.id == Cell.cellTypes().Point.id && !cellToMove.falling) {
        b.setCell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, new Cell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, Cell.cellTypes().Air));
        this.minedPoint(b);
      } else if (this.stonesCanBeMined > 0) {
        if (cellToMove.type.id == Cell.cellTypes().Stone.id && !cellToMove.falling) {
          this.stonesCanBeMined--;
          b.setCell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, new Cell(this.x + this.moveVect.delX, this.y + this.moveVect.delY, Cell.cellTypes().Air));
        }
      }
    }
    if (this.moveVect.x != 0 || this.moveVect.y != 0) {
      let cellToMove = b.getCell(this.x + this.moveVect.x, this.y + this.moveVect.y);
      switch (cellToMove.type.id) {
        case Cell.cellTypes().Dirt.id:
          this.minedDirt();
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes().Air.id:
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes().Point.id:
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          this.minedPoint(b);
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes().Finish.id:
          if (b.cells.filter((x) => x.type.id == Cell.cellTypes().Point.id).length == 0) {
            this.finished = true;
            b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
            b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
            this.x = this.x + this.moveVect.x;
            this.y = this.y + this.moveVect.y;
          }
          break;
        case Cell.cellTypes().Stone.id:
          if (b.getCell(this.x + this.moveVect.x * 2, this.y + this.moveVect.y).type.id == Cell.cellTypes().Air.id) {
            b.setCell(
              this.x + this.moveVect.x * 2,
              this.y + this.moveVect.y * 2,
              new Cell(this.x + this.moveVect.x * 2, this.y + this.moveVect.y * 2, Cell.cellTypes().Stone)
            );
            b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
            b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
            this.x = this.x + this.moveVect.x;
            this.y = this.y + this.moveVect.y;
          }
          break;
      }
    }
  }
  minedPoint(b) {
    this.points++;
    if (b.cells.filter((x) => x.type.id == Cell.cellTypes().Point.id).length == 0) {
      b.cells.filter((x) => x.type.id == Cell.cellTypes().Finish.id)[0].type.color = "#0FF";
    }
  }
  minedDirt() {
    this.mined++;
    if (this.minesLimit - this.mined < 50) {
      this.divRemaining.style.color = "#fc9403";
    }
    if (this.minesLimit - this.mined < 25) {
      this.divRemaining.style.color = "#F00";
    }
    this.divRemaining.innerText = (this.minesLimit - this.mined).toString().padStart(3);
  }
  draw(cnt, sizeX, sizeY) {
    if (this.moveVect.x == 0) {
      cnt.drawImage(this.type.img.normal, this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    } else if (this.moveVect.x == 1) {
      cnt.drawImage(this.type.img.R, this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    } else if (this.moveVect.x == -1) {
      cnt.drawImage(this.type.img.L, this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    }
  }
}
