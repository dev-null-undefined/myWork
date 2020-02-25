"use strict";
let canvas;
let cells = new Array();
// let colms = 192;
// let rows = 107;
let colms = 190;
let rows = 107;
let sizeOfCell = 10;

let justBuild = true;
let stack = new Array();
let currentCell;
let numberToSolve;
class Cell {
  static numberInLine;
  constructor(x, y) {
    this.visited = false;
    this.x = x;
    this.y = y;
    //TOP,RIGHT,BOTTOM,LEFT
    this.walls = [true, true, true, true];
  }
  getCoords() {
    return "X:" + this.x + ",Y:" + this.y;
  }
  getNeighbor(allCells, sizeX, sizeY) {
    let neighbors = new Array();
    if (this.x > 0) {
      neighbors.push(allCells[Cell.index(this.x - 1, this.y)]);
    }
    if (this.y > 0) {
      neighbors.push(allCells[Cell.index(this.x, this.y - 1)]);
    }
    if (this.x < sizeX - 1) {
      neighbors.push(allCells[Cell.index(this.x + 1, this.y)]);
    }
    if (this.y < sizeY - 1) {
      neighbors.push(allCells[Cell.index(this.x, this.y + 1)]);
    }
    neighbors = neighbors.filter(x => !x.visited);
    if (neighbors.length > 0) {
      return neighbors[Math.floor(Math.random() * neighbors.length)];
    } else {
      return null;
    }
  }
  static index(x, y) {
    return x * Cell.numberInLine + y;
  }
  draw(helpX, helpY, current = false) {
    noStroke();
    if (this.visited) {
      fill(106, 13, 173);
    } else {
      fill(0);
    }
    if (current) {
      fill(10, 40, 180);
    }
    rect(helpX * this.x, helpY * this.y, helpX, helpY);
    stroke(150);
    fill(0);
    if (this.walls[0]) {
      line(helpX * this.x, helpY * this.y, helpX * (this.x + 1), helpY * this.y);
    }
    if (this.walls[1]) {
      line(helpX * (this.x + 1), helpY * this.y, helpX * (this.x + 1), helpY * (this.y + 1));
    }
    if (this.walls[2]) {
      line(helpX * this.x, helpY * (this.y + 1), helpX * (this.x + 1), helpY * (this.y + 1));
    }
    if (this.walls[3]) {
      line(helpX * this.x, helpY * this.y, helpX * this.x, helpY * (this.y + 1));
    }
  }
  static removeWalls(a, b) {
    let x = a.x - b.x;
    let y = a.y - b.y;
    if (x === 1) {
      b.walls[1] = false;
      a.walls[3] = false;
    } else if (x === -1) {
      a.walls[1] = false;
      b.walls[3] = false;
    }
    if (y === 1) {
      a.walls[0] = false;
      b.walls[2] = false;
    } else if (y === -1) {
      b.walls[0] = false;
      a.walls[2] = false;
    }
  }
}
function setup() {
  canvas = createCanvas(colms * sizeOfCell, rows * sizeOfCell);
  for (let i = 0; i < colms; i++) {
    for (let j = 0; j < rows; j++) {
      cells.push(new Cell(i, j));
    }
  }
  Cell.numberInLine = rows;
  numberToSolve = colms * rows - 1;
  //frameRate(60);
  currentCell = cells[0];
  currentCell.visited = true;
}
function restart() {
  cells=new Array();
  stack=new Array();
  for (let i = 0; i < colms; i++) {
    for (let j = 0; j < rows; j++) {
      cells.push(new Cell(i, j));
    }
  }
  Cell.numberInLine = rows;
  numberToSolve = colms * rows - 1;
  frameRate(1);
  currentCell = cells[0];
  currentCell.visited = true;
  numberToSolve = colms * rows - 1;
  loop();
}
function draw() {
  background(0);
  if (justBuild) {
    build();
    cells.forEach(x => x.draw(width / colms, height / rows));
    //stack.forEach(x => x.draw(width / colms, height / rows, true));
    //currentCell.draw(width / colms, height / rows, true);
    saveCanvas(canvas, "Bludistak", "jpg");
    noLoop();
  } else {
    cells.forEach(x => x.draw(width / colms, height / rows));
    if (numberToSolve > 0) {
      let next = currentCell.getNeighbor(cells, colms, rows);
      if (next != null) {
        Cell.removeWalls(currentCell, next);
        currentCell = next;
        stack.push(currentCell);
        currentCell.visited = true;
        numberToSolve--;
      } else {
        currentCell = stack.pop();
      }
      currentCell.draw(width / colms, height / rows, true);
    } else {
      console.log("DONE");
      saveCanvas(canvas, "Bludistak", "jpg");
      noLoop();
    }
  }
}
function build() {
  while (numberToSolve > 0) {
    let next = currentCell.getNeighbor(cells, colms, rows);
    if (next != null) {
      Cell.removeWalls(currentCell, next);
      currentCell = next;
      stack.push(currentCell);
      currentCell.visited = true;
      numberToSolve--;
    } else {
      currentCell = stack.pop();
    }
  }
}
