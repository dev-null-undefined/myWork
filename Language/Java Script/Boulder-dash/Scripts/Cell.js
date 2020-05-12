class Cell {
  constructor(x, y, type) {
    this.x = x;
    this.y = y;
    this.type = type;
    this.falling = false;
    this.updated = false;
  }

  static Explosion() {
    return [document.getElementById("Explosion-1"), document.getElementById("Explosion-2")];
  }
  static cellTypes() {
    return {
      Dirt: { id: 1, color: "#FFF", rounded: false, img: document.getElementById("Dirt"), gravity: false },
      Air: { id: 2, color: "#000", rounded: false, img: null, gravity: false },
      Point: { id: 3, color: "#0F0", rounded: true, img: document.getElementById("Point"), gravity: true },
      Finish: { id: 4, color: "#066", rounded: false, img: null, gravity: false },
      Border: { id: 5, color: "#F00", rounded: false, img: document.getElementById("Border"), gravity: false },
      Wall: { id: 6, color: "#FD0", rounded: false, img: document.getElementById("Wall"), gravity: false },
      Stone: { id: 7, color: "#CA6", rounded: true, img: document.getElementById("Stone"), gravity: true },
      Player: {
        id: 8,
        color: "#00F",
        rounded: false,
        img: { normal: document.getElementById("Player"), L: document.getElementById("PlayerL"), R: document.getElementById("PlayerR") },
        gravity: false,
      },
    };
  }
  draw(cnt, sizeX, sizeY) {
    cnt.fillStyle = this.type.color;
    if (this.type.img) {
      cnt.drawImage(this.type.img, this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    } else {
      cnt.fillRect(this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    }
  }
  drawExplosion(cnt, sizeX, sizeY, type) {
    cnt.drawImage(Cell.Explosion()[type], this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
  }
  update(b) {
    if (this.updated) {
      return;
    }
    this.updated = true;
    if (this.type.gravity) {
      if (this.falling && b.getCell(this.x, this.y + 1).type.id == Cell.cellTypes().Player.id) {
        b.gameover();
      }
      if (b.getCell(this.x, this.y + 1).type.id == Cell.cellTypes().Air.id) {
        b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
        b.setCell(this.x, this.y + 1, this);
        this.falling = true;
        this.y++;
      } else if (b.getCell(this.x, this.y + 1).type.rounded) {
        if (b.getCell(this.x - 1, this.y).type.id == Cell.cellTypes().Air.id && b.getCell(this.x - 1, this.y + 1).type.id == Cell.cellTypes().Air.id) {
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
          b.setCell(this.x - 1, this.y, this);
          this.x--;
        } else if (b.getCell(this.x + 1, this.y).type.id == Cell.cellTypes().Air.id && b.getCell(this.x + 1, this.y + 1).type.id == Cell.cellTypes().Air.id) {
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes().Air));
          b.setCell(this.x + 1, this.y, this);
          this.x++;
        } else {
          this.falling = false;
        }
      } else {
        this.falling = false;
      }
    }
  }
}
