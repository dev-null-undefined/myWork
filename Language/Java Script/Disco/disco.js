var boxes = [];
var canvas;
var xoff;
var yoff;
var boxx = 20;
var boxy = 20;
var speed=100;
function ownColor(Red, Green, Blue) {
  this.red = Red;
  this.blue = Blue;
  this.green = Green;
}
class box{
  constructor( posX, posY, offX, offY){
    this.posX=posX;
    this.posY=posY;
    this.offX=offX;
    this.offY=offY;
    this.color=new ownColor(
      Math.floor(Math.random() * 255),
      Math.floor(Math.random() * 255),
      Math.floor(Math.random() * 255)
      );
      this.timer=0;
  }
  update(){
    if(this.timer<=0){
      this.color.red+=Math.floor(Math.random() * speed)-Math.floor(Math.random() * speed);
      if(this.color.red>=255){
        this.color.red-=Math.floor(Math.random() * speed);
      }
      if(this.color.red<=0){
        this.color.red+=Math.floor(Math.random() * speed);
      }
      this.color.green+=Math.floor(Math.random() * speed)-Math.floor(Math.random() * speed);
      if(this.color.green>=255){
        this.color.green-=Math.floor(Math.random() * speed);
      }
      if(this.color.green<=0){
        this.color.green+=Math.floor(Math.random() * speed);
      }
      this.color.blue+=Math.floor(Math.random() * speed)-Math.floor(Math.random() * speed);
      if(this.color.blue>=255){
        this.color.blue-=Math.floor(Math.random() * speed);
      }
      if(this.color.blue<=0){
        this.color.blue+=Math.floor(Math.random() * speed);
      }
      this.timer=Math.floor(Math.random() * 30);
    }else{
      this.timer--;
    }
  }
  draw(){
    noStroke();
    fill(this.color.red, this.color.green, this.color.blue);
    rect((this.posX-1)*this.offX,(this.posY-1)*this.offY,this.posX*this.offX,this.offY*this.posY);
  }
}
function setup() {
  // slider;
  canvas = createCanvas(1000, 500);
  yoff = canvas.height / boxy;
  xoff = canvas.width / boxx;
  let rowOfBoxes;
  for (var x = 0; x < boxx; x++) {
    rowOfBoxes=[];
    for (var y = 0; y < boxy; y++) {
      rowOfBoxes.push(new box(x+1,y+1,xoff,yoff));
    }
    boxes.push(rowOfBoxes);
  }
}

function draw() {
  for (var x = 0; x < boxx; x++) {
    for (var y = 0; y < boxy; y++) {
      boxes[x][y].update();
      boxes[x][y].draw();
    }
  }
}
