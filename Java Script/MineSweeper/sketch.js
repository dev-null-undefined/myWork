// function sumNestedArray(array) {
//   return array.map(o => o.reduce((x, y) => x + y));
// }
// let testerAr = [[2, 3, 4, 5], [23, 4], [32, 4]];
// console.log((sumNestedArray(testerAr));
// sumNestedArray(testerAr);

var help;

var i = 0;
var j = 0;
var l = 0;

var groupRed, groupGreen, groupBlue;
var groupSecoundRed, groupSecoundGreen, groupSecoundBlue;

var values = [];

function setup() {
  createCanvas(windowWidth, 200);
  var amount = 500;
  for (var p = 0; p < amount; p++) {
    values.push(
      new ownColor(
        Math.floor(Math.random() * 255),
        Math.floor(Math.random() * 255),
        Math.floor(Math.random() * 255)
      )
    );
  }
  background(0);
  help = width / values.length;
  console.log(help);
  for (var p = 0; p < values.length; p++) {
    fill(values[p].red, values[p].green, values[p].blue);
    stroke(values[p].red, values[p].green, values[p].blue);
    rect(p * help, height / 2.0, (p + 1) * help, height);
  }
}

function compareColor(one, two) {
  return one.red + one.blue + one.green > two.red + two.blue + two.green;
}

function swap(array, indexOne, indexTwo) {
  let temp = array[indexOne];
  array[indexOne] = array[indexTwo];
  array[indexTwo] = temp;
}

function setRGB() {
  for (let u = 0; u < values.length; u++) {
    var a = values[u];
    if (a.mainColor == 0) {
      groupRed = u;
    } else {
      if (a.mainColor == 1) {
        groupBlue = u;
      } else {
        groupGreen = u;
      }
    }
  }
}

function setSecoundRGB() {
  for (let u = 0; u < values.length; u++) {
    var a = values[u];
    if (a.mainColor == 0 && a.secoundaryColor == 1) {
      groupSecoundRed = u;
    } else {
      if (a.mainColor == 1 && a.secoundaryColor == 0) {
        groupSecoundBlue = u;
      } else {
        if (a.secoundaryColor == 0) {
          groupSecoundGreen = u;
        }
      }
    }
  }
}

function ownColor(Red, Green, Blue) {
  this.red = Red;
  this.blue = Blue;
  this.green = Green;
  Object.defineProperty(this, "mainColor", {
    get: function() {
      if (this.red > this.green && this.red > this.blue) {
        return 0;
      }
      if (this.blue > this.green && this.blue > this.red) {
        return 1;
      }
      if (this.green > this.blue && this.green > this.red) {
        return 2;
      }
      return -1;
    }
  });
  Object.defineProperty(this, "secoundaryColor", {
    get: function() {
      if (
        (this.red > this.green && this.red < this.blue) ||
        (this.red < this.green && this.red > this.blue)
      ) {
        return 0;
      }
      if (
        (this.blue > this.green && this.blue < this.red) ||
        (this.blue < this.green && this.blue > this.red)
      ) {
        return 1;
      }
      if (
        (this.green > this.blue && this.green < this.red) ||
        (this.green < this.blue && this.green > this.red)
      ) {
        return 2;
      }
      return -1;
    }
  });
}

function restart(amount) {
  i = 0;
  j = 0;
  l = 0;
  values = [];
  for (var p = 0; p < amount; p++) {
    values.push(
      new ownColor(
        Math.floor(Math.random() * 255),
        Math.floor(Math.random() * 255),
        Math.floor(Math.random() * 255)
      )
    );
  }
  background(0);
  help = width / values.length;
  console.log(help);
  for (var p = 0; p < values.length; p++) {
    fill(values[p].red, values[p].green, values[p].blue);
    stroke(values[p].red, values[p].green, values[p].blue);
    rect(p * help, height / 2.0, (p + 1) * help, height);
  }
}
function draw() {
  if (j < values.length) {
    for (let x = 0; x < values.length - 1 - i; x++) {
      let a = values[x];
      let b = values[x + 1];
      if (a.mainColor > b.mainColor) {
        swap(values, x, x + 1);
      }
    }
    j++;
    if (j == values.length) {
      setRGB();
      console.log(groupRed + "," + groupBlue + "," + groupGreen);
    }
  } else {
    if (i < groupRed) {
      for (let j = groupRed - 1; j > 0 + i; j--) {
        let a = values[j];
        let b = values[j + 1];
        if (a.secoundaryColor > b.secoundaryColor) {
          swap(values, j, j + 1);
        }
      }
      i++;
    } else {
      if (i < groupBlue) {
        for (let j = groupBlue - 1; j > 0 + i; j--) {
          let a = values[j];
          let b = values[j + 1];
          if (a.secoundaryColor > b.secoundaryColor) {
            swap(values, j, j + 1);
          }
        }
        i++;
      } else {
        if (i < groupGreen) {
          for (let j = groupGreen - 1; j > 0 + i; j--) {
            let a = values[j];
            let b = values[j + 1];
            if (a.secoundaryColor > b.secoundaryColor) {
              swap(values, j, j + 1);
            }
          }
          i++;
          if (i == groupGreen) {
            setSecoundRGB();
            console.log(
              groupSecoundRed + "," + groupSecoundBlue + "," + groupSecoundGreen
            );
          }
        } else {
          if (l < groupSecoundRed) {
            for (let j = groupSecoundRed - 1; j > 0 + l; j--) {
              let a = values[j];
              let b = values[j + 1];
              if (!compareColor(a, b)) {
                swap(values, j, j + 1);
              }
            }
            l++;
          } else {
            if (l < groupRed) {
              for (let j = groupRed - 1; j > 0 + l; j--) {
                let a = values[j];
                let b = values[j + 1];
                if (compareColor(a, b)) {
                  swap(values, j, j + 1);
                }
              }
              l++;
            } else {
              if (l < groupSecoundBlue) {
                for (let j = groupSecoundBlue - 1; j > 0 + l; j--) {
                  let a = values[j];
                  let b = values[j + 1];
                  if (!compareColor(a, b)) {
                    swap(values, j, j + 1);
                  }
                }
                l++;
              } else {
                if (l < groupBlue) {
                  for (let j = groupBlue - 1; j > 0 + l; j--) {
                    let a = values[j];
                    let b = values[j + 1];
                    if (compareColor(a, b)) {
                      swap(values, j, j + 1);
                    }
                  }
                  l++;
                } else {
                  if (l < groupSecoundGreen) {
                    for (let j = groupSecoundGreen - 1; j > 0 + l; j--) {
                      let a = values[j];
                      let b = values[j + 1];
                      if (!compareColor(a, b)) {
                        swap(values, j, j + 1);
                      }
                    }
                    l++;
                  } else {
                    if (l < groupGreen) {
                      for (let j = groupGreen - 1; j > 0 + l; j--) {
                        let a = values[j];
                        let b = values[j + 1];
                        if (compareColor(a, b)) {
                          swap(values, j, j + 1);
                        }
                      }
                      l++;
                    } else {
                      this.restart(Math.floor(Math.random() * 500) + 250);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  for (let i = 0; i < values.length; i++) {
    stroke(values[i].red, values[i].green, values[i].blue);
    fill(values[i].red, values[i].green, values[i].blue);
    rect(i * help, 0, (i + 1) * help, height / 2.0);
  }
}
