let canvas;
let filter = false;
let auto = false;
let graphView = false;
let values = new Array();
let colomns = new Array();
let colors = ["rgb(51, 204, 51)", "rgb(0, 51, 204)", "rgb(255, 0, 255)", "rgb(255, 0, 0)", "rgb(204, 153, 0)", "rgb(0, 153, 153)", "rgb(255, 255, 102)"];
let intervalValue;
function togleRefrash(e) {
  // e.preventDefault();
  auto = !auto;
  if (!auto) {
    document.getElementsByClassName("red")[0].style.background = "rgb(136, 28, 28)";
    clearInterval(intervalValue);
  } else {
    document.getElementsByClassName("red")[0].style.background = "#3e8e41";
    intervalValue = setInterval(getData, 4000);
  }
}
function toggleDropDown() {
  document.getElementById("myDropdown").classList.toggle("show");
  filter = !filter;
}
function togleGraphView() {
  if (filter) {
    toggleDropDown();
  }
  if (!graphView) {
    document.getElementsByClassName("datagrid")[0].style.display = "none";
    document.getElementsByClassName("p5Canvas")[0].style.display = "inline-block";
    document.getElementsByClassName("red")[0].style.display = "inline-block";
    document.cookie = "Graph=true";
  } else {
    document.getElementsByClassName("datagrid")[0].style.display = "inherit";
    document.getElementsByClassName("p5Canvas")[0].style.display = "none";
    document.getElementsByClassName("red")[0].style.display = "none";
    document.cookie = "Graph=false";
  }
  graphView = !graphView;
}
function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(";");
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function initGraph() {
  let rowsNames = document.getElementsByTagName("tr")[0].getElementsByTagName("th");
  let id = 0;
  colomns = new Array();
  Array.from(rowsNames).forEach(element => {
    values[element.innerText] = new Array();
    colomns.push(element.innerText);
  });
  let rows = document.getElementsByTagName("tr");
  for (id = 1; id < rows.length; id++) {
    let currentRow = document.getElementsByTagName("tr")[id].getElementsByTagName("th");
    for (let x = 0; x < currentRow.length; x++) {
      values[colomns[x]].push(currentRow[x].innerText);
    }
  }
}
function maxim(param) {
  let o = -Infinity;
  for (let x = 0; x < colomns.length; x++) {
    o = o > max(values[colomns[x]]) ? o : max(values[colomns[x]]);
  }
  return o;
}
function minim(param) {
  let o = Infinity;
  for (let x = 0; x < colomns.length; x++) {
    o = o < min(values[colomns[x]]) ? o : min(values[colomns[x]]);
  }
  return o;
}

function getData() {
  let myUrl = location.href;
  if (location.search == "") {
    myUrl += "?onlyData=true";
  } else {
    myUrl += "&onlyData=true";
  }
  $.ajax({
    url: myUrl,
    async: false,
    success: function(result) {
      console.log("DataLoadet");
      let datagrid = document.getElementById("datagrid");
      datagrid.innerHTML = result;
      initGraph();
    }
  });
}
function colomnsDevide() {
  return colomns.length / 2 - Math.floor(colomns.length / 2) >= 0.5 ? Math.floor(colomns.length / 2) + 1 : Math.floor(colomns.length / 2);
}
//P5 Graf
function linedash(x1, y1, x2, y2, delta, style = "-") {
  // delta is both the length of a dash, the distance between 2 dots/dashes, and the diameter of a round
  let distance = dist(x1, y1, x2, y2);
  let dashNumber = distance / delta;
  let xDelta = (x2 - x1) / dashNumber;
  let yDelta = (y2 - y1) / dashNumber;

  for (let i = 0; i < dashNumber; i += 2) {
    let xi1 = i * xDelta + x1;
    let yi1 = i * yDelta + y1;
    let xi2 = (i + 1) * xDelta + x1;
    let yi2 = (i + 1) * yDelta + y1;

    if (style == "-") {
      line(xi1, yi1, xi2, yi2);
    } else if (style == ".") {
      point(xi1, yi1);
    } else if (style == "o") {
      ellipse(xi1, yi1, delta / 2);
    }
  }
}
function setup() {
  if (getCookie("Graph") == "true") {
    togleGraphView();
  }
  initGraph();
  canvas = createCanvas(window.windowWidth * 0.98, window.windowHeight * 0.9);
}
function draw() {
  let vyska = canvas.height - colomnsDevide() * 18;
  background(0);
  let minimum = minim(values);
  let maximum = maxim(values);
  if (minimum === null || maximum === null) {
    return;
  }
  let gap = canvas.width / (values[colomns[0]].length - 1);
  let h;
  if (minimum > 0) {
    h = vyska / maximum;
  } else {
    h = vyska / (abs(minimum) + abs(maximum) + 10);
  }
  let center;
  stroke(126);
  if (minimum > 0) {
    center = 0;
  } else {
    strokeWeight(5);
    center = (vyska / (abs(minimum) + abs(maximum))) * abs(minimum);
    line(0, vyska - center, canvas.width, vyska - center);
  }
  strokeWeight(2);
  // console.log(center);
  for (let x = 0; x < colomns.length; x++) {
    stroke(colors[x % colors.length]);
    for (let i = 0; i < values[colomns[x]].length; i++) {
      switch (Math.floor(x / colors.length)) {
        case 0:
          line(i * gap, vyska - (center + values[colomns[x]][i] * h), (i + 1) * gap, vyska - (center + values[colomns[x]][i + 1] * h));
          break;
        case 1:
          linedash(i * gap, vyska - (center + values[colomns[x]][i] * h), (i + 1) * gap, vyska - (center + values[colomns[x]][i + 1] * h), gap / 10, "-");
          break;
        case 2:
          linedash(i * gap, vyska - (center + values[colomns[x]][i] * h), (i + 1) * gap, vyska - (center + values[colomns[x]][i + 1] * h), gap / 10, ".");
          break;
        case 3:
          linedash(i * gap, vyska - (center + values[colomns[x]][i] * h), (i + 1) * gap, vyska - (center + values[colomns[x]][i + 1] * h), gap / 10, "o");
          break;
      }
    }
  }
  stroke(255);
  fill(255);
  rect(0, vyska + 1, canvas.width, canvas.height);
  let textHelp = 18;
  for (let x = 0; x < colomns.length; x++) {
    stroke(120);
    if (x < colomns.length / 2) {
      fill(colors[x % colors.length]);
      textAlign(LEFT, TOP);
      textSize(18);
      textStyle(BOLD);
      text(colomns[x], 0, vyska + 1 + textHelp * x);
      stroke(colors[x % colors.length]);
      switch (Math.floor(x / colors.length)) {
        case 0:
          line(textWidth(colomns[x]) + 10, vyska + 1 + textHelp * x + textHelp / 2, textWidth(colomns[x]) * 2, vyska + 1 + textHelp * x + textHelp / 2);
          break;
        case 1:
          linedash(textWidth(colomns[x]) + 10, vyska + 1 + textHelp * x + textHelp / 2, textWidth(colomns[x]) * 2, vyska + 1 + textHelp * x + textHelp / 2, 5, "-");
          break;
        case 2:
          linedash(textWidth(colomns[x]) + 10, vyska + 1 + textHelp * x + textHelp / 2, textWidth(colomns[x]) * 2, vyska + 1 + textHelp * x + textHelp / 2, 5, ".");
          break;
        case 3:
          linedash(textWidth(colomns[x]) + 10, vyska + 1 + textHelp * x + textHelp / 2, textWidth(colomns[x]) * 2, vyska + 1 + textHelp * x + textHelp / 2, 5, "o");
          break;
      }
    } else {
      fill(colors[x % colors.length]);
      textAlign(LEFT, TOP);
      textSize(18);
      textStyle(BOLD);
      text(colomns[x], canvas.width / 2, vyska + 1 + textHelp * (x - colomns.length / 2));
      stroke(colors[x % colors.length]);
      switch (Math.floor(x / colors.length)) {
        case 0:
          line(
            canvas.width / 2 + textWidth(colomns[x]) + 10,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            canvas.width / 2 + textWidth(colomns[x]) * 2,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2
          );
          break;
        case 1:
          linedash(
            canvas.width / 2 + textWidth(colomns[x]) + 10,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            canvas.width / 2 + textWidth(colomns[x]) * 2,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            5,
            "-"
          );
          break;
        case 2:
          linedash(
            canvas.width / 2 + textWidth(colomns[x]) + 10,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            canvas.width / 2 + textWidth(colomns[x]) * 2,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            5,
            "."
          );
          break;
        case 3:
          linedash(
            canvas.width / 2 + textWidth(colomns[x]) + 10,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            canvas.width / 2 + textWidth(colomns[x]) * 2,
            vyska + 1 + textHelp * (x - colomns.length / 2) + textHelp / 2,
            5,
            "o"
          );
          break;
      }
    }
  }
  let maxAndMin = abs(minimum) + abs(maximum);
  fill(255);
  textAlign(LEFT, TOP);
  textSize(17);
  if (minimum < 0) {
    for (let i = 0; i < 10; i++) {
      text((maxAndMin - (maxAndMin / 10) * i + minimum).toFixed(2), 0, (canvas.height / 10) * i);
    }
  } else {
    let maxAndMin = maximum;
    for (let i = 0; i < 10; i++) {
      text((maxAndMin - (maxAndMin / 10) * i).toFixed(2), 0, (canvas.height / 10) * i);
    }
  }
}
function windowResized() {
  resizeCanvas(window.windowWidth * 0.98, window.windowHeight * 0.9);
}
function resetInputs() {
  let dropDown = document.getElementById("myDropdown");
  let inputs = Array.from(dropDown.getElementsByTagName("input"));
  inputs.filter(element => element.type == "checkbox");
  inputs.forEach(element => (element.checked = false));
}
