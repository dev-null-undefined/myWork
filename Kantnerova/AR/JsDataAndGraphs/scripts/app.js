var lines = Array.from(document.getElementsByTagName("tbody")[0].getElementsByTagName("tr"));
const containerSvg = document.getElementsByTagName("svg")[0];
lines.forEach((element) => {
  pElements = Array.from(element.getElementsByTagName("p"));
  pElements.shift();
  pElements.forEach((element) => {
    let number = parseFloat(element.innerText);
    if (number <= 0) {
      element.parentElement.style.backgroundColor = "#C0C0C00";
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
  createGraph(containerSvg, line);
}
function createGraph(container, values) {
  container.innerHTML = "";
  let barWidth = container.width.baseVal.value / (values.length + 1);
  let maxHeight = container.height.baseVal.value - 35;
  let rect = document.createElement("rect");
  rect.setAttribute("width", container.width.baseVal.value);
  rect.setAttribute("height", maxHeight);
  rect.style = "fill: none;stroke:black";
  container.appendChild(rect);
  let maxValue = values.reduce((a, b) => (b > a ? b : a));
  let minValue = values.reduce((a, b) => (b < a ? b : a));
  if (minValue < 0 && maxValue > 0) {
    let mid = ((maxHeight * 0.8) / (maxValue + Math.abs(minValue))) * maxValue + maxHeight * 0.1;
    console.log(maxHeight, mid, maxValue, minValue);
    values.forEach((element, index) => {
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
      rect.style = "fill: rgb(0,102,255);";
      container.appendChild(rect);
      let text = document.createElement("text");
      text.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      if (element < 0) {
        text.setAttribute("y", mid - 2);
      } else {
        text.setAttribute("y", mid - (element / maxValue) * (mid - maxHeight * 0.1) - 1);
      }
      text.setAttribute("text-anchor", "middle");
      text.innerText = element + "°C";
      text.style = "font-size: 1.4em;";
      container.appendChild(text);

      let indexText = document.createElement("text");
      indexText.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      indexText.setAttribute("y", container.height.baseVal.value);
      indexText.setAttribute("text-anchor", "middle");
      indexText.innerText = index;
      indexText.style = "font-size: 1.4em;";
      container.appendChild(indexText);
    });
  } else if (minValue < 0 && maxValue < 0) {
    values.forEach((element, index) => {
      let rect = document.createElement("rect");
      rect.setAttribute("x", barWidth * index + 0.5 * barWidth);
      rect.setAttribute("y", 0);
      rect.setAttribute("width", barWidth * 0.777);
      rect.setAttribute("height", (element / minValue) * maxHeight * 0.9);
      rect.style = "fill: rgb(0,102,255);";
      container.appendChild(rect);
      let text = document.createElement("text");
      text.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      text.setAttribute("y", (element / minValue) * (maxHeight * 0.9) + 20);
      text.setAttribute("text-anchor", "middle");
      text.innerText = element + "°C";
      text.style = "font-size: 1.4em;";
      container.appendChild(text);

      let indexText = document.createElement("text");
      indexText.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      indexText.setAttribute("y", container.height.baseVal.value);
      indexText.setAttribute("text-anchor", "middle");
      indexText.innerText = index;
      indexText.style = "font-size: 1.4em;";
      container.appendChild(indexText);
    });
  } else {
    values.forEach((element, index) => {
      let rect = document.createElement("rect");
      rect.setAttribute("x", barWidth * index + 0.5 * barWidth);
      rect.setAttribute("y", maxHeight - (element / maxValue) * (maxHeight * 0.9));
      rect.setAttribute("width", barWidth * 0.777);
      rect.setAttribute("height", (element / maxValue) * maxHeight * 0.9);
      rect.style = "fill: rgb(0,102,255);";
      container.appendChild(rect);
      let text = document.createElement("text");
      text.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      text.setAttribute("y", maxHeight - (element / maxValue) * (maxHeight * 0.9) - 2);
      text.setAttribute("text-anchor", "middle");
      text.innerText = element + "°C";
      text.style = "font-size: 1.4em;";
      container.appendChild(text);

      let indexText = document.createElement("text");
      indexText.setAttribute("x", barWidth * index + 0.8885 * barWidth);
      indexText.setAttribute("y", container.height.baseVal.value);
      indexText.setAttribute("text-anchor", "middle");
      indexText.innerText = index;
      indexText.style = "font-size: 1.4em;";
      container.appendChild(indexText);
    });
  }
  container.innerHTML += "";
}
// changeGraph("Praha");
changeGraph("Stockholm");
