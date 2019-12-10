let time = 0;
let wave = [];
let path = [];
let multiplierSlider;

let timeSlider;
let plusSlider;
let slider;
let colorList;
function setup() {
  createCanvas(600, 400);
  slider = createSlider(1, 50, 5);
  colorList=[];
  for(let x=0;x<51;x++){
    colorList.push(color(Math.random()*255,Math.random()*255,Math.random()*255));
  }
  
  multiplierSlider = createSlider(0, 15, 1);
  plusSlider = createSlider(1, 50, 25);
  
  timeSlider = createSlider(1, 500, 100);
}

let multiplier;
function draw() {
  background(0);
  translate(150, 200);

  let x = 0;
  let y = 0;
  for (let i = 0; i < slider.value(); i++) {
    let prevx = x;
    let prevy = y;

    let n = i *i*i  * multiplierSlider.value() + 1;
    multiplier=map(plusSlider.value(), 1, 50, 0.1, TWO_PI);
    let radius = 75 * (4 / (n * multiplier));
    x += radius * cos(n * time);
    y += radius * sin(n * time);

    stroke(colorList[i]._getRed(),colorList[i]._getGreen(),colorList[i]._getBlue(), 75);
    // noFill();
    fill(colorList[i]._getRed(),colorList[i]._getGreen(),colorList[i]._getBlue(), 75);
    ellipse(prevx, prevy, radius * 2);

    //fill(255);
    stroke(255);
    line(prevx, prevy, x, y);
    //ellipse(x, y, 8);
  }
  wave.unshift(y);


  translate(200, 0);
  line(x - 200, y, 0, wave[0]);
  beginShape();
  noFill();
  for (let i = 0; i < wave.length; i++) {
    vertex(i, wave[i]);
  }
  endShape();

  time +=timeSlider.value()/1000.0;


  if (wave.length > 250) {
    wave.pop();
  }
}
