/* eslint-disable space-before-function-paren */
// Array Functions
function randomizePositions(array, timesToSwap = array.length) {
  for (let i = 0; i < array.length; i++) {
    swap(Math.floor(Math.random() * array.length), Math.floor(Math.random() * array.length), array);
  }
  return array;
}
function isSorted(array) {
  const sortedArray = [...array].sort();
  let sorted = true;
  array.forEach((element, index) => {
    if (element !== sortedArray[index]) {
      sorted = false;
    }
  });
  return sorted;
}
function swap(a, b, array) {
  const itemA = array[a];
  array[a] = array[b];
  array[b] = itemA;
}
function move(indexA, indexB, array) {
  return array.splice(indexB, 0, array.splice(indexA, 1)[0]);
}
function generateArray(length) {
  const array = [];
  for (let i = 0; i < length; i++) {
    array.push(Math.random());
  }
  return array;
}
function minIndex(array) {
  let minimumIndex = 0;
  array.forEach((element, index) => {
    if (element < array[minimumIndex]) {
      minimumIndex = index;
    }
  });
  return minimumIndex;
}

function map_range(value, low1, high1, low2, high2) {
  return low2 + ((high2 - low2) * (value - low1)) / (high1 - low1);
}

let maxF = 700;
let minF = 100;
function getValue(array, index) {
  oscillator.frequency.value = map_range(array[index], 0, 1, minF, maxF);
  return array[index];
}
function stopOscilator() {
  gainOscillator.disconnect(oscillatorContext.destination);
}
function resumeOscilator() {
  gainOscillator.connect(oscillatorContext.destination);
}
var oscillatorContext;
let oscillator;
var gainOscillator;
function startOscilator() {
  oscillatorContext = new AudioContext();
  oscillatorContext.resume();
  oscillator = oscillatorContext.createOscillator();
  oscillator.type = "sine";
  gainOscillator = oscillatorContext.createGain();
  gainOscillator.gain.value = 0.15;
  oscillator.connect(gainOscillator);
  gainOscillator.connect(oscillatorContext.destination);
  oscillator.start();
  stopOscilator();
}
