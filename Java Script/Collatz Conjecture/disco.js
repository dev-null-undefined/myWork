function setup() {
  createCanvas(800, 800);
  frameRate(10);
}
function draw() {
  background(0);
  translate(0, height/2);
  const move=20;
  let done=false;
  for(let startNumber=1;startNumber<1000;startNumber+=5){
  let number = startNumber;
  let numberBefore;
  let iBefore;
  let x=0;
  let xBefore;
  let omega=PI/2;
  for (let i = 0; number != 1;) {
    numberBefore = number;
    number = Collatz(number);
    if (number%2==0){
      omega+=PI/20;
    }else{
      omega-=PI/20;
    }
    // console.log(omega);
    xBefore=x;
    x+=cos(omega)*move;
    iBefore=i;
    i+=sin(omega)*move;
    stroke(255,255,255,map(number,0,startNumber*2,0,255));
    line(iBefore, xBefore, i, x);
  }
  }
  // done=true;
}

function Collatz(x) {
  return x % 2 == 0 ? x / 2 : 3 * x + 1;
}
