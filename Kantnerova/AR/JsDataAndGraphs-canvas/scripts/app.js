const lines = Array.from(document.getElementsByTagName("tbody")[0].getElementsByTagName("tr"));
const canvas = document.getElementsByTagName("canvas")[0];
const ctx = canvas.getContext("2d");
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
  createGraph(ctx, line, "°C", true);
}
function createGraph(content, values, afterText = "°C", colorFull = false) {
  let w = content.canvas.width;
  let h = content.canvas.height;
  content.clearRect(0, 0, w, h);
  let barWidth = w / (values.length + 1);
  let maxHeight = h - 35;

  ctx.lineWidth = 1;
  content.strokeRect(0, 0, w, maxHeight);

  let maxValue = values.reduce((a, b) => (b > a ? b : a), 0);
  let minValue = values.reduce((a, b) => (b < a ? b : a), 0);
  let mid = ((maxHeight * 0.8) / (maxValue + Math.abs(minValue))) * maxValue + maxHeight * 0.1;
  console.log(maxHeight, mid, maxValue, minValue);

  values.forEach((element, index) => {
    // Rectangle
    if (colorFull) {
      if (element <= 0) {
        content.fillStyle = "#C0C0C0";
      } else if (element <= 10) {
        content.fillStyle = "#3BB9FF";
      } else if (element <= 20) {
        content.fillStyle = "#C6DEFF";
      } else if (element <= 30) {
        content.fillStyle = "#FBB117";
      } else {
        content.fillStyle = "#F87431";
      }
    } else {
      content.fillStyle = "rgb(0,102,255)";
    }
    content.fillRect(
      barWidth * index + 0.5 * barWidth,
      element < 0 ? mid : mid - (element / maxValue) * (mid - maxHeight * 0.1),
      barWidth * 0.777,
      element < 0 ? (element / minValue) * (maxHeight * 0.9 - mid) : (element / maxValue) * (mid - maxHeight * 0.1)
    );
    content.fillStyle = "#000000";
    content.textAlign = "center";
    content.font = "20px serif";
    // Text at the top of the bar
    content.fillText(element + afterText, barWidth * index + 0.8885 * barWidth, element < 0 ? mid - 2 : mid - (element / maxValue) * (mid - maxHeight * 0.1) - 1);
    // Text under the table
    content.font = "25px serif";
    content.fillText(index, barWidth * index + 0.8885 * barWidth, h);
  });
}
changeGraph("Praha");
