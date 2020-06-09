const canvas = document.getElementById("canvas");
const cnt = canvas.getContext("2d");

const points = [];
window.onresize = windowsResize;
window.onmousemove = mouseMove;
canvas.onclick = onCanvasClick;
canvas.onmousedown = mouseDown;
canvas.onmouseup = mouseUp;

let movePointIndex = -1;
function mouseDown(event) {
  let pointIndex = -1;
  points.forEach((element, index) => {
    if (Math.abs(element.x - event.clientX) < 10 && Math.abs(element.y - event.clientY) < 10) {
      pointIndex = index;
    }
  });
  if (pointIndex != -1) {
    movePointIndex = pointIndex;
    draw();
  }
}
function mouseUp(event) {
  if (movePointIndex != -1) {
    movePointIndex = -1;
    draw();
  }
}
function mouseMove(event) {
  if (movePointIndex != -1) {
    points[movePointIndex].x = event.clientX;
    points[movePointIndex].y = event.clientY;
    draw();
  }
}

function onCanvasClick(event) {
  let createPoint = true;
  points.forEach((element) => {
    if (Math.abs(element.x - event.clientX) < 10 && Math.abs(element.y - event.clientY) < 10) {
      createPoint = false;
    }
  });
  if (createPoint) {
    points.push({ x: event.clientX, y: event.clientY });
  }
  draw();
}
function windowsResize() {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  draw();
}
function draw() {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, canvas.width, canvas.height);

  cnt.strokeStyle = "#00F";
  cnt.beginPath();
  let lastPoint = points[0];
  for (let i = 1, lenght = points.length; i < lenght; i++) {
    cnt.moveTo(lastPoint.x, lastPoint.y);
    lastPoint = points[i];
    cnt.lineTo(lastPoint.x, lastPoint.y);
  }
  cnt.stroke();

  cnt.fillStyle = "#FFF";
  points.forEach((element) => {
    cnt.beginPath();
    cnt.arc(element.x, element.y, 5, 0, Math.PI * 2, true);
    cnt.fill();
  });

  let pointsToDraw = chaikinsAlgo(points, 6);
  cnt.strokeStyle = "#FFF";
  cnt.beginPath();
  lastPoint = pointsToDraw[0];
  for (let i = 1, lenght = pointsToDraw.length; i < lenght; i++) {
    cnt.moveTo(lastPoint.x, lastPoint.y);
    lastPoint = pointsToDraw[i];
    cnt.lineTo(lastPoint.x, lastPoint.y);
  }
  cnt.stroke();
}
function chaikinsAlgo(points, quality) {
  const size = 0.25;
  if (quality > 0) {
    let newPoints = [];
    let lastPoint = points[0];
    for (let i = 1, lenght = points.length; i < lenght; i++) {
      newPoints.push({ x: (1 - size) * lastPoint.x + size * points[i].x, y: (1 - size) * lastPoint.y + size * points[i].y });
      newPoints.push({ x: size * lastPoint.x + (1 - size) * points[i].x, y: size * lastPoint.y + (1 - size) * points[i].y });
      lastPoint = points[i];
    }
    return chaikinsAlgo(newPoints, quality - 1);
  } else {
    return points;
  }
}
windowsResize();
draw();
