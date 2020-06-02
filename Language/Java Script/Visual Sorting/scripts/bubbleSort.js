/* eslint-disable space-before-function-paren */
// Buble sort variables
let bubleCurrent = null;
let bubleLoop = null;

function bubbleSort() {
  if (!bubleCurrent && !bubleLoop) {
    restartVariables("ALL");
    bubleLoop = 0;
    bubleCurrent = 0;
  }
  if (bubleCurrent + bubleLoop >= arrayToSort.length - 1) {
    if (bubleCurrent === 0) {
      bubleCurrent = -1;
      bubleLoop++;
      return true;
    } else {
      bubleCurrent = 0;
      bubleLoop++;
    }
  } else {
    if (getValue(arrayToSort, bubleCurrent) > getValue(arrayToSort, bubleCurrent + 1)) {
      swap(bubleCurrent, bubleCurrent + 1, arrayToSort);
    }
    bubleCurrent++;
  }
}
function drawBubbleSort(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    if (index === bubleCurrent || index === bubleCurrent + 1) {
      cnt.fillStyle = "#5255eb";
    } else if (index >= arrayToSort.length - bubleLoop) {
      cnt.fillStyle = "#55b809";
    } else {
      cnt.fillStyle = "#ffffff";
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
}

function bubbleSortReset() {
  bubleCurrent = null;
  bubleLoop = null;
}
