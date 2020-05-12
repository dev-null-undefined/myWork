class Game {
  constructor(x, y, control) {
    this.startPosPlayer = { x: 4, y: 4 };
    this.finishPos = { x: 3, y: 4 };
    this.mineLimit = 250;
    this.divPoints = document.getElementById("Points");
    this.divLevel = document.getElementById("Level");
    this.divDeath = document.getElementById("Deaths");
    this.level = getCookie("level") == "" ? 15 : parseInt(getCookie("level"));
    this.points = getCookie("points") == "" ? 0 : parseInt(getCookie("points"));
    this.deathCounter = getCookie("deaths") == "" ? 0 : parseInt(getCookie("deaths"));
    this.player = new Player(this.startPosPlayer.x, this.startPosPlayer.y, control, this.points, this.mineLimit);
    this.board = new Board(x, y, this.level, 15);
    this.board.setCell(this.player.x, this.player.y, this.player);
    this.board.setCell(this.finishPos.x, this.finishPos.y, new Cell(this.finishPos.x, this.finishPos.y, Cell.cellTypes().Finish));
    this.divDeath.innerText = this.deathCounter.toString().padStart(3) + " ";
    this.divPoints.innerText = this.points.toString().padStart(3) + " ";
    this.divLevel.innerText = (15 - this.level + 1).toString().padStart(3) + " ";
  }

  draw(cnt) {
    this.board.draw(cnt);
  }
  update(b) {
    if (this.player.finished) {
      this.nextLevel(this.player.points + Math.floor(Math.min(this.mineLimit - this.player.mined, 0) / 10));
    } else if (this.board.lost) {
      this.deathCounter++;
      this.divDeath.innerText = this.deathCounter.toString().padStart(3) + " ";
      this.startLevel(this.level, 15);
    }
    if (this.player.moveVect.lastX != 0 || this.player.moveVect.lastY != 0) {
      this.player.moveVect.x = this.player.moveVect.lastX;
      this.player.moveVect.y = this.player.moveVect.lastY;
    } else {
      this.player.moveVect.x = this.player.moveVect.currX;
      this.player.moveVect.y = this.player.moveVect.currY;
    }
    this.player.moveVect.lastX = 0;
    this.player.moveVect.lastY = 0;
    let cellToDelete = this.board.getCell(this.player.x + this.player.moveVect.delX, this.player.y + this.player.moveVect.delY);
    if (
      (this.player.moveVect.delX != 0 || this.player.moveVect.delY != 0) &&
      !cellToDelete.falling &&
      (cellToDelete.type.id != Cell.cellTypes().Air.id || (cellToDelete.type.id != Cell.cellTypes().Stone.id && this.player.stonesCanBeMined > 0))
    ) {
      this.player.update(this.board);
    } else {
      this.board.update();
    }
    this.divPoints.innerText = (this.player.points + Math.floor(Math.min(this.mineLimit - this.player.mined, 0) / 10)).toString().padStart(3) + " ";
    this.player.updated = false;
  }
  startLevel(level, points) {
    this.player.moveVect.x = 0;
    this.player.moveVect.y = 0;
    this.level = level;
    this.player = new Player(this.startPosPlayer.x, this.startPosPlayer.y, this.player.moveVect, this.points, this.mineLimit);
    this.board = new Board(this.board.sizeX, this.board.sizeY, level, points);
    this.board.setCell(this.player.x, this.player.y, this.player);
    this.board.setCell(this.finishPos.x, this.finishPos.y, new Cell(this.finishPos.x, this.finishPos.y, Cell.cellTypes().Finish));
  }
  nextLevel(points) {
    this.level--;
    this.points = points;
    this.divLevel.innerText = (15 - this.level + 1).toString().padStart(3) + " ";
    this.startLevel(this.level, 15);
    setCookie("level", this.level, 365);
    setCookie("points", this.points, 365);
    setCookie("deaths", this.deathCounter, 365);
  }
}
