let players = [];
let walls = [];
let speed = 10;
let ray;
let particle;
let xoff = 0;
let yoff = 10000;
let xPloff = 5000;
let yPloff = 20000;
let clicked = false;
let drawingWall;
let clickVector;
let control = false;
let PlayerControl = true;
let light=true;
function changeLighth(bool){
  light=bool;  
}
function changePlayerControl(bool){
  PlayerControl = bool;
}
function generateRandomLines(count) {
  for (let i = 0; i < count; i++) {
    let x1 = random(width);
    let x2 = random(width);
    let y1 = random(height);
    let y2 = random(height);
    walls.push(new Boundary(x1, y1, x2, y2));
  }
}
function generateWallsAround() {
  walls.push(new Boundary(0, 0, width, 0));
  walls.push(new Boundary(width, 0, width, height));
  walls.push(new Boundary(width, height, 0, height));
  walls.push(new Boundary(0, height, 0, 0));
}
function changeLightControl(bool) {
  control = bool;
}
function setup() {
  createCanvas(800, 800);
  /*for (let i = 0; i < 5; i++) {
    let x1 = random(width);
    let x2 = random(width);
    let y1 = random(height);
    let y2 = random(height);
    walls[i] = new Boundary(x1, y1, x2, y2);
  }*/
  walls.push(new Boundary(0, 0, width, 0));
  walls.push(new Boundary(width, 0, width, height));
  walls.push(new Boundary(width, height, 0, height));
  walls.push(new Boundary(0, height, 0, 0));
  players.push(new Player(width / 2, height / 2));
  //generateRandomLines(2);
  frameRate(60);
  particle = new Particle(180);
}
document.addEventListener("click", function myOnClick(evt) {
  if (clicked) {
    walls.push(new Boundary(clickVector.x, clickVector.y, mouseX, mouseY));
    clicked = false;
  } else {
    clickVector = createVector(evt.x, evt.y);
    clicked = true;
  }
});
function draw() {
  background(0);
  if (clicked) {
    drawingWall = new Boundary(clickVector.x, clickVector.y, mouseX, mouseY);
    let saveWalls = [];
    for (let wall of walls) {
      saveWalls.push(wall);
    }
    saveWalls.push(drawingWall);
    for (let wall of saveWalls) {
      wall.show();
    }
    if (control) {
      particle.update(mouseX, mouseY);
    } else {
      particle.update(noise(xoff) * width, noise(yoff) * height);
    }
    if(light){
    particle.show();
    particle.look(saveWalls, players);
    }
  } else {
    if (control) {
      particle.update(mouseX, mouseY);
    } else {
      particle.update(noise(xoff) * width, noise(yoff) * height);
    }
    if(light){
      particle.show();
      particle.look(walls, players);
      }
    for (let wall of walls) {
      wall.show();
    }
  }
  for (let pl of players) {
    pl.show();
  }
  players[0].update(
    PlayerControl ? mouseX : noise(xPloff),
    PlayerControl ? mouseY : noise(yPloff),
    walls
  );

  xoff += speed / 1000;
  yoff += speed / 1000;
  xPloff += speed / 1000;
  yPloff += speed / 1000;
}
