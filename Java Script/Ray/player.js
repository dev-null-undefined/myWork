class Player {
  constructor(x, y) {
    this.x = x;
    this.y = y;
    this.r = 40;
    this.pos = createVector(x, y);
    this.color = false;
  }
  toWall(ray) {
    //console.log(degrees(ray.angle));
    let x1 = this.r * Math.sin(PI - ray.angle);
    let y1 = this.r * Math.cos(PI - ray.angle);
    // console.log(x1);
    // console.log(y1);
    return new Boundary(
      this.x - x1,
      this.y - y1,
      this.x + x1,
      this.y + y1,
      this
    );
  }
  show() {
    if (this.color) {
      // stroke(255);
      stroke(200, 50, 0);
      noFill();
      ellipse(this.x, this.y, this.r * 2, this.r * 2);
      fill(255);
      ellipse(this.x, this.y, this.r / 10, this.r / 10);
    } else {
      stroke(255);
      // fill(255);

      noFill();
      ellipse(this.x, this.y, this.r * 2, this.r * 2);
      fill(255);
      ellipse(this.x, this.y, this.r / 10, this.r / 10);
    }
    this.color = false;
  }
  moveBy(x,y){
    if(x>1){
      x--;
    }
    if(x<-1){
      x++;
    }
    if(y>1){
      y--;
    }
    if(y<-1){
      y++;
    }
    if(x<=1&&x>=-1){
      x=0;
    }
    if(y<=1&&y>=-1){
      y=0;
    }
    this.x+=x;
    this.y+=y;
    this.pos=createVector(this.x, this.y);
  } 
   update(x, y, walls) {
    let closest;
    let closestDist;
    for (let wall of walls) {
      const x1 = x;
      const y1 = y;
      const x2 = this.pos.x;
      const y2 = this.pos.y;

      const x3 = wall.b.x;
      const y3 = wall.b.y;
      const x4 = wall.a.x;
      const y4 = wall.a.y;

      const den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
      if (den == 0) {
      } else {
        const t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        const u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
        if (t > 0 && t < 1 && u > 0 && u < 1) {
          const pt = createVector();
          pt.x = x1 + t * (x2 - x1);
          pt.y = y1 + t * (y2 - y1);
          if(closest){
            if(closestDist==p5.Vector.dist(this.pos,pt)){
             return; 
            }
            if(closestDist>p5.Vector.dist(this.pos,pt)){
              closest=pt;
              closestDist=p5.Vector.dist(this.pos,pt);
            }
          }else{
            closest=pt;
            closestDist=p5.Vector.dist(this.pos,pt);
          }
        }
      }
    }
    if(closest){
      this.moveBy(closest.x-this.x,closest.y-this.y);
    }else{
    this.x = x;
    this.y = y;
    this.pos = createVector(x, y);
    }
  }
}
