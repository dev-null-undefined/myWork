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
          this.cells.push(new Cell(i, j, Cell.cellTypes.Border));
        } else if ((j % 7 == 0 && i < sizeX - 5 && j % 14 != 0) || (j % 14 == 0 && i > 5)) {
          this.cells.push(new Cell(i, j, Cell.cellTypes.Wall));
        } else {
          this.cells.push(new Cell(i, j, Cell.cellTypes.Dirt));
        }
      }
    }
    for (let i = 0, count = Math.floor((Math.random() * this.cells.length) / this.level) + this.cells.length / this.level; i < count; i++) {
      this.getRandom([Cell.cellTypes.Border, Cell.cellTypes.Wall]).type = Cell.cellTypes.Stone;
    }
    for (let i = 0; i < this.numberOfPoints; i++) {
      this.getRandom([Cell.cellTypes.Border, Cell.cellTypes.Wall, Cell.cellTypes.Stone]).type = Cell.cellTypes.Point;
    }
  }

  getRandom() {
    let x = Math.floor(Math.random() * (this.sizeX - 2)) + 1;
    let y = Math.floor(Math.random() * (this.sizeY - 2)) + 1;
    return this.getCell(x, y);
  }

  getRandom(notType) {
    let from = this.cells.filter((x) => !notType.some((b) => x.type == b));
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
        let player = this.cells.filter((x) => x.type == Cell.cellTypes.Player)[0];
        for (let i = 0; i < 3; i++) {
          for (let j = 0; j < 3; j++) {
            let explCell = this.getCell(player.x + i - 1, player.y + j - 1);
            explCell.drawExplosion(cnt, cnt.canvas.width / this.sizeX, cnt.canvas.height / this.sizeY, this.lostAnimation % 2);
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
      for (let i = 0; i < this.sizeY; i++) {
        for (let j = 0; j < this.sizeX; j++) {
          if (!this.lost) {
            this.getCell(j, i).update(this);
          }
        }
      }
      this.cells.forEach((x) => (x.falling = false));
    }
  }
  gameover() {
    this.lostAnimation = 60;
  }
}
class Cell {
  constructor(x, y, type) {
    this.x = x;
    this.y = y;
    this.type = type;
    this.falling = false;
  }

  static Explosion = [document.getElementById("Explosion-1"), document.getElementById("Explosion-2")];
  static cellTypes = {
    Dirt: { color: "#FFF", rounded: false, img: document.getElementById("Dirt"), gravity: false },
    Air: { color: "#000", rounded: false, img: null, gravity: false },
    Point: { color: "#0F0", rounded: true, img: document.getElementById("Point"), gravity: true },
    Finish: { color: "#066", rounded: false, img: null, gravity: false },
    Border: { color: "#F00", rounded: false, img: document.getElementById("Border"), gravity: false },
    Wall: { color: "#FD0", rounded: false, img: document.getElementById("Wall"), gravity: false },
    Stone: { color: "#CA6", rounded: true, img: document.getElementById("Stone"), gravity: true },
    Player: {
      color: "#00F",
      rounded: false,
      img: { normal: document.getElementById("Player"), L: document.getElementById("PlayerL"), R: document.getElementById("PlayerR") },
      gravity: false,
    },
  };
  draw(cnt, sizeX, sizeY) {
    cnt.fillStyle = this.type.color;
    if (this.type.img) {
      cnt.drawImage(this.type.img, this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    } else {
      cnt.fillRect(this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
    }
  }
  drawExplosion(cnt, sizeX, sizeY, type) {
    cnt.drawImage(Cell.Explosion[type], this.x * sizeX, this.y * sizeY, sizeX * 0.9, sizeY * 0.9);
  }
  update(b) {
    if (this.type.gravity) {
      if (b.getCell(this.x, this.y + 1).type == Cell.cellTypes.Air) {
        b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
        b.setCell(this.x, this.y + 1, this);
        this.falling = true;
        this.y++;
        // this.update(b);
      } else if (b.getCell(this.x, this.y + 1).type.rounded) {
        if (b.getCell(this.x - 1, this.y).type == Cell.cellTypes.Air && b.getCell(this.x - 1, this.y + 1).type == Cell.cellTypes.Air) {
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
          b.setCell(this.x - 1, this.y, this);
          this.x--;
          // this.update(b);
        } else if (b.getCell(this.x + 1, this.y).type == Cell.cellTypes.Air && b.getCell(this.x + 1, this.y + 1).type == Cell.cellTypes.Air) {
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
          b.setCell(this.x + 1, this.y, this);
          this.x++;
          // this.update(b);
        }
      }
      if (this.type == Cell.cellTypes.Stone && this.falling && b.getCell(this.x, this.y + 1).type == Cell.cellTypes.Player) {
        b.gameover();
      }
    }
  }
}
class Game {
  constructor(x, y, control) {
    this.level = getCookie("level") == "" ? 15 : parseInt(getCookie("level"));
    this.points = getCookie("points") == "" ? 0 : parseInt(getCookie("points"));
    this.deathCounter = getCookie("deaths") == "" ? 0 : parseInt(getCookie("deaths"));
    this.player = new Player(4, 4, control, this);
    this.board = new Board(x, y, 20, 15);
    this.board.setCell(this.player.x, this.player.y, this.player);
    this.board.setCell(4, y - 4, new Cell(4, y - 4, Cell.cellTypes.Finish));
    this.divPoints = document.getElementById("Points");
    this.divLevel = document.getElementById("Level");
    this.divDeath = document.getElementById("Deaths");
    this.divDeath.innerText = this.deathCounter.toString().padStart(3);
    this.divPoints.innerText = this.points.toString().padStart(3) + " ";
    this.divLevel.innerText = (15 - this.level + 1).toString().padStart(3) + " ";
  }

  draw(cnt) {
    this.board.draw(cnt);
  }
  update(b) {
    if (this.player.finished) {
      this.nextLevel(this.player.points + Math.min(150 - this.player.mined, 0));
    } else if (this.board.lost) {
      this.deathCounter++;
      this.divDeath.innerText = this.deathCounter.toString().padStart(3);
      this.startLevel(this.level, 15);
    }
    this.board.update();
    this.player.updated = false;
  }
  startLevel(level, points) {
    this.player.moveVect.x = 0;
    this.player.moveVect.y = 0;
    this.level = level;
    this.player = new Player(4, 4, this.player.moveVect, this);
    this.board = new Board(this.board.sizeX, this.board.sizeY, level, points);
    this.board.setCell(this.player.x, this.player.y, this.player);
    this.board.setCell(4, this.board.sizeY - 4, new Cell(4, this.board.sizeY - 4, Cell.cellTypes.Finish));
  }
  nextLevel(points) {
    this.level--;
    this.points += points;
    this.divPoints.innerText = this.points.toString().padStart(3) + " ";
    this.divLevel.innerText = (15 - this.level + 1).toString().padStart(3) + " ";
    this.startLevel(this.level, 15);
    setCookie("level", this.level, 365);
    setCookie("points", this.points, 365);
    setCookie("deaths", this.deathCounter, 365);
  }
}
class Player extends Cell {
  constructor(x, y, moveVect, game) {
    super(x, y, Cell.cellTypes.Player);
    this.updated = false;
    this.points = 0;
    this.mined = 0;
    this.moveVect = moveVect;
    this.updateNumber = 0;
    this.finished = false;
  }
  update(b) {
    if (this.updated) {
      return;
    }
    this.updated = true;
    this.updateNumber = 0;
    if (this.moveVect.x != 0 || this.moveVect.y != 0) {
      let cellToMove = b.getCell(this.x + this.moveVect.x, this.y + this.moveVect.y);
      switch (cellToMove.type) {
        case Cell.cellTypes.Dirt:
          this.mined++;
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes.Air:
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes.Point:
          this.points++;
          b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
          b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
          if (b.cells.filter((x) => x.type == Cell.cellTypes.Point).length == 0) {
            Cell.cellTypes.Finish.color = "#0FF";
          }
          this.x = this.x + this.moveVect.x;
          this.y = this.y + this.moveVect.y;
          break;
        case Cell.cellTypes.Finish:
          if (b.cells.filter((x) => x.type == Cell.cellTypes.Point).length == 0) {
            this.finished = true;
            b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
            b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
            this.x = this.x + this.moveVect.x;
            this.y = this.y + this.moveVect.y;
          }
          break;
        case Cell.cellTypes.Stone:
          if (b.getCell(this.x + this.moveVect.x * 2, this.y + this.moveVect.y * 2).type == Cell.cellTypes.Air) {
            b.setCell(
              this.x + this.moveVect.x * 2,
              this.y + this.moveVect.y * 2,
              new Cell(this.x + this.moveVect.x * 2, this.y + this.moveVect.y * 2, Cell.cellTypes.Stone)
            );
            b.setCell(this.x, this.y, new Cell(this.x, this.y, Cell.cellTypes.Air));
            b.setCell(this.x + this.moveVect.x, this.y + this.moveVect.y, this);
            this.x = this.x + this.moveVect.x;
            this.y = this.y + this.moveVect.y;
          }
          break;
      }
    }
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

const canvas = document.getElementById("canvas");
const content = canvas.getContext("2d");

let moveVect = {
  x: 0,
  y: 0,
};

let game = new Game(35, 22, moveVect);
window.onresize = () => setSizes(canvas, window.innerWidth, window.innerHeight);
window.onkeydown = (e) => keyDown(e);
window.onkeyup = (e) => keyUp(e);

function keyDown(event) {
  switch (event.which) {
    case 38:
      moveVect.x = 0;
      moveVect.y = -1;
      break;
    case 40:
      moveVect.x = 0;
      moveVect.y = 1;
      break;
    case 39:
      moveVect.y = 0;
      moveVect.x = 1;
      break;
    case 37:
      moveVect.y = 0;
      moveVect.x = -1;
      break;
    case 82:
      game.startLevel(game.level, 15);
  }
}
function keyUp(event) {
  switch (event.which) {
    case 37:
    case 38:
    case 39:
    case 40:
      moveVect.y = 0;
      moveVect.x = 0;
      break;
  }
}
function setSizes(c, x, y) {
  let yMax = (x / game.board.sizeX) * game.board.sizeY;
  yMax = Math.min(y, yMax);
  c.width = (yMax / game.board.sizeY) * game.board.sizeX * 0.7;
  c.height = yMax * 0.7;
}
let frame = 0;
function updateAndDraw() {
  if (frame > 20) {
    frame = 0;
    game.update();
  } else {
    frame++;
  }
  game.draw(content);
  requestAnimationFrame(updateAndDraw);
}
setSizes(canvas, window.innerWidth, window.innerHeight);
updateAndDraw();

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(";");
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
