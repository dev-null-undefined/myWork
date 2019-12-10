class Particle {
  getHeigth(a, b) {
    if (a.y > b.y) {
      return a.y - b.y;
    }
    return b.y - a.y;
  }
  getWidth(a, b) {
    if (a.x > b.x) {
      return a.x - b.x;
    }
    return b.x - a.x;
  }
  constructor(amount) {
    amount = 360 / amount;
    this.Roff = 0;
    this.Goff = 10000;
    this.Boff = 10000000;
    this.pos = createVector(width / 2, height / 2);
    this.rays = [];
    for (let a = 0; a < 360; a += amount) {
      this.rays.push(new Ray(this.pos, radians(a)));
    }
  }

  update(x, y) {
    this.pos.set(x, y);
  }
  getD(a, b, c) {
    return Math.pow(b, 2) + -4 * a * c;
  }
  getOptions(b, D, a) {
    let options = [];
    options.push((-b + Math.sqrt(D)) / (2 * a));
    options.push((-b - Math.sqrt(D)) / (2 * a));
    // console.log(options);
    return options;
  }
  look(walls, players) {
    for (let i = 0; i < this.rays.length; i++) {
      const ray = this.rays[i];
      let secWalls = [];
      for (let pl of players) {
        secWalls.push(pl.toWall(ray));
      }
      let closest = null;
      let record = Infinity;
      let wallC;
      for (let wall of walls) {
        const pt = ray.cast(wall);
        if (pt) {
          const d = p5.Vector.dist(this.pos, pt);
          if (d < record) {
            record = d;
            closest = pt;
            wallC = wall;
          }
        }
      }
      for (let wall of secWalls) {
        const pt = ray.cast(wall);
        if (pt) {
          const d = p5.Vector.dist(this.pos, wall.minus.pos);
          if (d < record) {
            record = d;
            closest = pt;
            wallC = wall;
          }
        }
      }
      if (closest) {
        // colorMode(HSB);
        // stroke((i + frameCount * 2) % 360, 255, 255, 50);
        stroke(
          noise(this.Roff) * 255,
          noise(this.Goff) * 255,
          noise(this.Boff) * 255,
          200
        );
        if (wallC.minus) {
          wallC.minus.color = true;
          let r = wallC.minus.r;
          let b = p5.Vector.dist(wallC.minus.pos, this.pos);
          let a = p5.Vector.dist(wallC.minus.pos, closest);
          let alfa = Math.asin(a / record);
          let c = -Math.pow(r, 2) + Math.pow(b, 2);
          let y = 2 * b * Math.cos(alfa);
          let vector = record + this.getOptions(y, this.getD(1, y, c), 1)[0]; //use index 1 for funn
          //console.log(vector);
          let x = Math.cos(ray.angle) * vector;
          y = Math.sin(ray.angle) * vector;

          line(this.pos.x, this.pos.y, closest.x - x, closest.y - y);
          // wallC.show();
        } else {
          //console.log("IDK");
          line(this.pos.x, this.pos.y, closest.x, closest.y);
        }
        //this.mirrorLine(closest, wallC, record);
      }
    }
  }

  // mirrorLine(closest, wallC, distance) {
  //   let pata = createVector(this.pos.x, closest.y);
  //   //console.log(pata);
  //   //console.log(this.pos);
  //   let angle = Math.asin(this.getHeigth(this.pos, pata) / distance);
  //   //console.log(this.getHeigth(this.pos, pata));
  //   //console.log(angle);
  //   // let angle = Math.atan((wallC.a.y - wallC.b.y) / (wallC.a.x - wallC.b.x));
  //   let secoundAngle = Math.asin(
  //     (wallC.a.y > wallC.b.y ? wallC.a.y - wallC.b.y : wallC.b.y - wallC.a.y) /
  //       distance
  //   );
  //   let resoult = angle + (180 - (180 - secoundAngle) - angle);
  //   //console.log(radians(resoult));

  //   //   distance /
  //   //     (this.pos.y > closest.y
  //   //       ? this.pos.y - closest.y
  //   //       : closest.y - this.pos.y)
  //   // );
  //   let vector = p5.Vector.fromAngle(radians(resoult + secoundAngle));
  //   vector.normalize();
  //   console.log(vector);
  //   line(
  //     closest.x,
  //     closest.y,
  //     vector.x * width - closest.x,
  //     vector.y * height - closest.y
  //   );
  // }
  show() {
    stroke(
      noise(this.Roff) * 255,
      noise(this.Goff) * 255,
      noise(this.Boff) * 255,
      50
    );
    this.Roff += 0.01;
    this.Goff += 0.01;
    this.Boff += 0.01;
    ellipse(this.pos.x, this.pos.y, 1);
    for (let ray of this.rays) {
      ray.show();
    }
  }
}
