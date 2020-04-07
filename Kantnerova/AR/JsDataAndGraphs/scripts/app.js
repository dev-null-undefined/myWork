const lines = Array.from(document.getElementsByTagName("tbody")[0].getElementsByTagName("tr"));
const containerSvg = document.getElementsByTagName("svg")[0];
lines.forEach((element) => {
  pElements = Array.from(element.getElementsByTagName("p"));
  pElements.shift();
  pElements.forEach((element) => {
    let number = parseFloat(element.innerText);
    if (number <= 0) {
      element.parentElement.style.backgroundColor = "#C0C0C0";
    } else if (number <= 10) {
      element.parentElement.style.backgroundColor = "#3BB9FF";
    } else if (number <= 20) {
      element.parentElement.style.backgroundColor = "#C6DEFF";
    } else if (number <= 30) {
      element.parentElement.style.backgroundColor = "#FBB117";
    } else {
      element.parentElement.style.backgroundColor = "#F87431";
    }
  });
});
function findLine(startOfTheLine) {
  return lines.find((element) => element.getElementsByTagName("p")[0].innerText == startOfTheLine);
}
function changeGraph(startOfTheLine) {
  let line = Array.from(findLine(startOfTheLine).getElementsByTagName("p"));
  line.shift();
  line = line.map((element) => parseFloat(element.innerText));
  createGraph(containerSvg, line, "°C", true);
}
function createGraph(container, values, afterText = "°C", colorFull = false) {
  container.innerHTML = "";
  let barWidth = container.width.baseVal.value / (values.length + 1);
  let maxHeight = container.height.baseVal.value - 35;
  let rect = document.createElement("rect");
  rect.setAttribute("width", container.width.baseVal.value);
  rect.setAttribute("height", maxHeight);
  rect.style = "fill: none;stroke:black";
  container.appendChild(rect);
  let maxValue = values.reduce((a, b) => (b > a ? b : a), 0);
  let minValue = values.reduce((a, b) => (b < a ? b : a), 0);
  let mid = ((maxHeight * 0.8) / (maxValue + Math.abs(minValue))) * maxValue + maxHeight * 0.1;
  console.log(maxHeight, mid, maxValue, minValue);
  values.forEach((element, index) => {
    let group = document.createElement("g");
    group.setAttribute("id", index);
    // Rectangle
    let rect = document.createElement("rect");
    rect.setAttribute("x", barWidth * index + 0.5 * barWidth);
    if (element < 0) {
      rect.setAttribute("y", mid);
    } else {
      rect.setAttribute("y", mid - (element / maxValue) * (mid - maxHeight * 0.1));
    }
    rect.setAttribute("width", barWidth * 0.777);
    if (element < 0) {
      rect.setAttribute("height", (element / minValue) * (maxHeight * 0.9 - mid));
    } else {
      rect.setAttribute("height", (element / maxValue) * (mid - maxHeight * 0.1));
    }
    if (colorFull) {
      if (element <= 0) {
        rect.style = "fill: #C0C0C0";
      } else if (element <= 10) {
        rect.style = "fill: #3BB9FF";
      } else if (element <= 20) {
        rect.style = "fill: #C6DEFF";
      } else if (element <= 30) {
        rect.style = "fill: #FBB117";
      } else {
        rect.style = "fill: #F87431";
      }
    } else {
      rect.style = "fill: rgb(0,102,255);";
    }
    group.appendChild(rect);
    // Text at the top of the bar
    let text = document.createElement("text");
    text.setAttribute("x", barWidth * index + 0.8885 * barWidth);
    if (element < 0) {
      text.setAttribute("y", mid - 2);
    } else {
      text.setAttribute("y", mid - (element / maxValue) * (mid - maxHeight * 0.1) - 1);
    }
    text.setAttribute("text-anchor", "middle");
    text.innerText = element + afterText;
    text.style = "font-size: 1.4em;";
    group.appendChild(text);
    // Text under the table
    let indexText = document.createElement("text");
    indexText.setAttribute("x", barWidth * index + 0.8885 * barWidth);
    indexText.setAttribute("y", container.height.baseVal.value);
    indexText.setAttribute("text-anchor", "middle");
    indexText.innerText = index;
    indexText.style = "font-size: 1.4em;";
    group.appendChild(indexText);
    container.appendChild(group);
  });
  container.innerHTML += "";
}
changeGraph("Praha");
