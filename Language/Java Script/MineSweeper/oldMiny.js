"use strict";
var mine = [];
var drawArr = [];
var saveDrawArr = [];
var help = 0;
var canvas;
var seedSeeds;
var velikost;
var minesCount;
var reset = false;
var images=[]

function saveValue(x, y, value) {
  this.xCoord = x;
  this.yCoord = y;
  this.value = value;
}
function preload(){
  images[0]=loadImage("images\\UnOpened.png");
  images[1]=loadImage("images\\Mine.jpg");
  images[2]=loadImage("images\\MineFound.jpg");
}
function setup() {
  canvas = createCanvas(500, 500);
  start(1, 15);
  frameRate(5);
}

function start(seed, size) {
  minesCount = 0;
  seedSeeds = seed;
  velikost = size;
  var done = false;
  mine = [];ssss
  drawArr = [];
  let zeroLine = [];
  for (var x = 0; x < size; x++) {
    let line = [];
    zeroLine = [];
    for (var y = 0; y < size; y++) {
      zeroLine.push(-1);
      if (Math.floor(Math.random() * seed) === 1) {
        line.push(1);
        minesCount++;
      } else {
        line.push(0);
      }
    }
    done = true;
    mine.push(line);
    drawArr.push(zeroLine);
  }
  help = width / size;
}

document.addEventListener(
  "contextmenu",
  function(ev) {
    var x = ev.pageX - $("#Hra").offset().left;
    var y = ev.pageY - $("#Hra").offset().top;
    if (x > 0 && y > 0 && x < canvas.width && y < canvas.height) {
      ev.preventDefault();
      x = Math.floor(x / help);
      y = Math.floor(y / help);
      //console.log(x + "," + y);
      if (reset) {
        start(seedSeeds, velikost);
        reset = false;
      } else {
        console.log(
          "right click x=" +
            x +
            ", y=" +
            y +
            ", drawArr value= " +
            drawArr[x][y] +
            ", mine valuer= " +
            mine[x][y]
        );
        mineFound(x, y);
        if (win()) {
          reset = true;
          drawArr = mine;
          console.log("WIN");
        }
      }
    }
  },
  false
);
function win() {
  let saveMineCount = minesCount;
  saveDrawArr.forEach(p => {
    if (p != undefined) {
      if (mine[p.xCoord][p.yCoord] === 1) {
        saveMineCount--;
      }
    }
  });
  return saveMineCount === 0;
}

function mineFound(x, y) {
  if (drawArr[x][y] === -3) {
    saveDrawArr = saveDrawArr.map(o => forEachFromSaveDrawArr(o, x, y));
  } else {
    saveDrawArr.push(new saveValue(x, y, drawArr[x][y]));
    drawArr[x][y] = -3;
  }
}

function forEachFromSaveDrawArr(o, x, y) {
  if (o != undefined) {
    if (o.yCoord === y && o.xCoord === x) {
      drawArr[x][y] = o.value;
      return undefined;
    } else {
      return o;
    }
  }
  return undefined;
}

document.addEventListener("click", function(evt) {
  var x = evt.pageX - $("#Hra").offset().left;
  var y = evt.pageY - $("#Hra").offset().top;
  if (x > 0 && y > 0 && x < canvas.width && y < canvas.height) {
    if (reset) {
      start(seedSeeds, velikost);
      reset = false;
    } else {
      x = Math.floor(x / help);
      y = Math.floor(y / help);
      console.log(
        "Left click x=" +
          x +
          ", y=" +
          y +
          ", drawArr value= " +
          drawArr[x][y] +
          ", mine valuer= " +
          mine[x][y]
      );
      //drawArr[x][y] = mine[x][y];
      if (mine[x][y] === 1) {
        //start(seedSeeds, velikost);
        console.log("prohra");
        drawArr = mine;
        reset = true;
      }
      drawGrey(x, y);
    }
  }
});

function getMinesNextTo(x, y) {
  var amunt = 0;
  if (x < velikost - 1) {
    if (y < velikost - 1) {
      if (mine[x + 1][y + 1] === 1) amunt++;
    }
    if (mine[x + 1][y] === 1) amunt++;
    if (y > 0) {
      if (mine[x + 1][y - 1] === 1) amunt++;
    }
  }
  if (y > 0) {
    if (mine[x][y - 1] === 1) amunt++;
  }
  if (y < velikost - 1) {
    if (mine[x][y + 1] === 1) amunt++;
  }
  if (x > 0) {
    if (y < velikost - 1) {
      if (mine[x - 1][y + 1] === 1) amunt++;
    }
    if (mine[x - 1][y] === 1) amunt++;
    if (y > 0) {
      if (mine[x - 1][y - 1] === 1) amunt++;
    }
  }
  return amunt;
}

function drawGrey(o, p) {
  beenTo = [];
  for (var x = 0; x < velikost; x++) {
    let zeroLine = [];
    for (var y = 0; y < velikost; y++) {
      zeroLine.push(0);
    }
    beenTo.push(zeroLine);
  }
  drawGreyAgain(o, p, beenTo);
}

var beenTo;

function drawGreyAgain(x, y) {
  if (drawArr[x][y] === -2) {
  } else {
    drawArr[x][y] = mine[x][y];
  }
  beenTo[x][y] = 1;
  // console.log(beenTo + "," + x + "x" + "," + y + "y");
  if (x < velikost - 1) {
    if (x + 1 <= velikost && beenTo[x + 1][y] === 0) {
      if (
        mine[x + 1][y] === 0 &&
        (getMinesNextTo(x, y) < 2 || drawArr[x + 1][y] != -1)
      ) {
        drawGreyAgain(x + 1, y, beenTo);
      } else {
        drawArr[x][y] = -2;
      }
    }
  }
  if (x > 0) {
    if (x - 1 >= 0 && beenTo[x - 1][y] === 0) {
      if (
        mine[x - 1][y] === 0 &&
        (getMinesNextTo(x, y) < 2 || drawArr[x - 1][y] != -1)
      ) {
        drawGreyAgain(x - 1, y, beenTo);
      } else {
        drawArr[x][y] = -2;
      }
    }
  }
  if (y < velikost) {
    if (y + 1 <= velikost - 1 && beenTo[x][y + 1] === 0) {
      if (
        mine[x][y + 1] === 0 &&
        (getMinesNextTo(x, y) < 2 || drawArr[x][y + 1] != -1)
      ) {
        drawGreyAgain(x, y + 1, beenTo);
      } else {
        drawArr[x][y] = -2;
      }
    }
  }
  if (y > 0) {
    if (y - 1 >= 0 && beenTo[x][y - 1] === 0) {
      if (
        mine[x][y - 1] === 0 &&
        (getMinesNextTo(x, y) < 2 || drawArr[x][y - 1] != -1)
      ) {
        drawGreyAgain(x, y - 1, beenTo);
      } else {
        drawArr[x][y] = -2;
      }
    }
  }
}

function draw() {
  background(220);
  fill(0, 255, 0);
  stroke(255, 0, 0);
  var x = 0;
  var y = 0;
  for (x = 0; x < mine.length; x++) {
    for (y = 0; y < mine[0].length; y++) {
      switch (drawArr[x][y]) {
        case -3:
          fill(150, 0, 0);
          break;
        case -2:
          fill(150);
          break;
        case -1:
          fill(0, 255, 0);
          break;
        case 0:
          fill(150);
          break;
        case 1:
          fill(255);
          break;
      }
      rect(x * help, y * help, (x + 1) * help, (y + 1) * help);
      //stroke(255, 0, 0);
    }
  }

  stroke(255, 0, 0);
  fill(0);
  for (x = 0; x < mine.length; x++) {
    for (y = 0; y < mine[0].length; y++) {
      if (drawArr[x][y] === -2) {
        let amunt = getMinesNextTo(x, y);
        text(
          amunt,
          x * help + help / 3.5,
          y * help + help / 5,
          (x + 1) * help,
          (y + 1) * help
        );
        if (x < velikost - 1) {
          if (drawArr[x + 1][y] === 0) {
            x += 1;
            amunt = getMinesNextTo(x, y);
            if (amunt != 0)
              text(
                amunt,
                x * help + help / 3.5,
                y * help + help / 5,
                (x + 1) * help,
                (y + 1) * help
              );
            x -= 1;
          }
        }
        if (x > 0) {
          if (drawArr[x - 1][y] === 0) {
            x -= 1;
            amunt = getMinesNextTo(x, y);
            if (amunt != 0)
              text(
                amunt,
                x * help + help / 3.5,
                y * help + help / 5,
                (x + 1) * help,
                (y + 1) * help
              );
            x += 1;
          }
        }
        if (y < velikost - 1) {
          if (drawArr[x][y + 1] === 0) {
            y += 1;
            amunt = getMinesNextTo(x, y);
            if (amunt != 0)
              text(
                amunt,
                x * help + help / 3.5,
                y * help + help / 5,
                (x + 1) * help,
                (y + 1) * help
              );
            y -= 1;
          }
        }
        if (y > 0) {
          if (drawArr[x][y - 1] === 0) {
            y -= 1;
            amunt = getMinesNextTo(x, y);
            if (amunt != 0)
              text(
                amunt,
                x * help + help / 3.5,
                y * help + help / 5,
                (x + 1) * help,
                (y + 1) * help
              );
            y += 1;
          }
        }
      }
    }
  }
}
