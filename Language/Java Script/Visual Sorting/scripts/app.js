/* eslint-disable space-before-function-paren */

class Part {
  constructor(a, b) {
    this.a = a;
    this.b = b;
  }

  contains(c) {
    return c >= this.a && c <= this.b;
  }

  size() {
    return this.b - this.a;
  }
}

let sortingInterval = null;
let arrayToSort = [];

const speedSlider = document.getElementById("speed");
const sizeSlider = document.getElementById("arraySize");
const generateButton = document.getElementById("generator");
const startButton = document.getElementById("startButton");
const sizeSliderInfo = document.getElementById("arraySizeText");
const speedSliderInfo = document.getElementById("speedText");
const canvas = document.getElementById("canvas");

let width = window.innerWidth * 0.8;
let height = window.innerHeight - 250;
canvas.width = width;
canvas.height = height;

const cnt = canvas.getContext("2d");
cnt.font = "30px Arial";
var timer = { start: null };
// Chack box from buttons
function generateArrayAndDraw(event) {
  arrayToSort = generateArray(sizeSlider.value);
  draw();
}

// #region Adding event Listener
generateButton.addEventListener("click", generateArrayAndDraw);
startButton.addEventListener("click", startSorting);
speedSlider.onchange = speedSliderOnChange;
sizeSlider.onchange = sizeSliderOnChange;
speedSlider.oninput = speedSliderOnChange;
sizeSlider.oninput = sizeSliderOnChange;
function sizeSliderOnChange(event) {
  generateArrayAndDraw();
  sizeSliderInfo.innerText = "Array size: " + event.target.value;
}
function speedSliderOnChange(event) {
  speedSliderInfo.innerText = "Speed: " + event.target.value;
}
window.onresize = windowsResize;
function windowsResize() {
  width = window.innerWidth * 0.8;
  height = window.innerHeight - 250;
  canvas.width = width;
  canvas.height = height;
  draw();
}
// #endregion
function startSorting() {
  if (sortType) {
    if (sortingInterval) {
      clearInterval(sortingInterval);
      sortingInterval = null;
      if (sortType.innerText != "Random sort") {
        stopOscilator();
      }
      startButton.innerText = "Start sorting";
      startButton.className = "startButton";
      generateButton.disabled = false;
      sizeSlider.disabled = false;
    } else {
      timer.start = performance.now();
      if (oscillator == undefined) {
        createOscilator();
      }
      restartVariables("ALL");
      startButton.innerText = "Stop sorting";
      startButton.className = "stopButton";
      generateButton.disabled = true;
      sizeSlider.disabled = true;
      sortingRecursion();
    }
  } else {
    alert("!CHOSE SORT METHODE!");
  }
}
function doneSorting() {
  startButton.innerText = "Start sorting";
  startButton.className = "startButton";
  generateButton.disabled = false;
  sizeSlider.disabled = false;
  sortingInterval = null;
}

function restartVariables(sortMethod) {
  resumeOscilator();
  switch (sortMethod) {
    case "Insertion Sort":
      insertionSortReset();
      break;
    case "Merge Sort":
      mergeSortReset();
      break;
    case "Tim Sort":
      timSortReset();
      break;
    case "Stalin Sort":
      stalinSortReset();
      break;
    case "Quick Sort":
      quickSortReset();
      break;
    case "Bubble Sort":
      bubbleSortReset();
      break;
    case "Random sort":
      randomSortReset();
      break;
    case "Selection sort":
      selectionSortReset();
      break;
    default:
      restartVariables("Insertion Sort");
      restartVariables("Merge Sort");
      restartVariables("Tim Sort");
      restartVariables("Stalin Sort");
      restartVariables("Quick Sort");
      restartVariables("Bubble Sort");
      restartVariables("Random sort");
      restartVariables("Selection sort");
      break;
  }
}

function sortingRecursion() {
  let isDoneSorting = false;
  switch (sortType.innerText) {
    case "Merge Sort":
      isDoneSorting = margeSort();
      drawMergeSort(cnt);
      break;
    case "Insertion Sort":
      isDoneSorting = insertSort();
      drawInsertionSort(cnt);
      break;
    case "Tim Sort":
      isDoneSorting = timSort();
      drawTimSort(cnt);
      break;
    case "Quick Sort":
      isDoneSorting = quickSort();
      drawQuickSort(cnt);
      break;
    case "Stalin Sort":
      isDoneSorting = stalinSort();
      drawStalinSort(cnt);
      break;
    case "Selection sort":
      isDoneSorting = selectionSort();
      drawSelectionSort(cnt);
      break;
    case "Bubble Sort":
      isDoneSorting = bubbleSort();
      drawBubbleSort(cnt);
      break;
    case "Random sort":
      isDoneSorting = randomSort();
      drawRandomSort(cnt);
      break;
  }
  let milis = Math.floor((performance.now() - timer.start) / 10);
  let secs = Math.floor(milis / 100);
  let minutes = Math.floor(secs / 60);
  cnt.fillStyle = "#FFF";
  while (secs > 60) {
    secs -= 60;
  }
  if (secs < 10) {
    secs = "0" + secs;
  }
  while (milis > 100) {
    milis -= 100;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  cnt.fillText(minutes + ":" + secs + "." + milis, 0, canvas.height);
  if (isDoneSorting) {
    if (sortingInterval) {
      stopOscilator();
      clearTimeout(sortingInterval);
    }
    doneSorting();
  } else {
    if (sortingInterval) {
      clearTimeout(sortingInterval);
    }
    sortingInterval = setTimeout(sortingRecursion, (speedSlider.min + (speedSlider.max - speedSlider.value)) * 5);
  }
}

function draw() {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, width, height);
  const sizeOfBlock = width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    cnt.fillStyle = "#FFF";
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element);
  });
}

generateArrayAndDraw();
