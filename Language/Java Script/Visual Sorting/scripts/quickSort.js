/* eslint-disable space-before-function-paren */
// Quick Sort
let quickParts;
let quickCurrent;
let quickSmaller;
let quickCurrentPart;
let quickDone;
let quickCurrentPivot;

function quickSort(params) {
  // #region Quick sort
  if (!quickParts) {
    restartVariables("ALL");
    quickParts = [];
    quickDone = [];
    quickParts.push(new Part(0, arrayToSort.length - 1));
  }
  if (!quickCurrentPart) {
    if (quickParts.length === 0) {
      return true;
    }
    quickCurrentPart = quickParts.pop();
    if (quickCurrentPart.a === quickCurrentPart.b) {
      return true;
    } else {
      quickCurrentPivot = quickCurrentPart.a + Math.floor(Math.random() * (quickCurrentPart.b - quickCurrentPart.a));
    }
  } else {
    if (!quickCurrent) {
      quickCurrent = quickCurrentPart.a + 1;
      swap(quickCurrentPivot, quickCurrentPart.a, arrayToSort);
      quickCurrentPivot = quickCurrentPart.a;
      quickSmaller = quickCurrentPart.a;
    } else {
      if (quickCurrent > quickCurrentPart.b) {
        swap(quickSmaller, quickCurrentPart.a, arrayToSort);
        quickDone.push(quickSmaller);
        // add some code
        if (quickCurrentPart.a < quickSmaller - 1) {
          quickParts.push(new Part(quickCurrentPart.a, quickSmaller - 1));
        } else if (quickCurrentPart.a === quickSmaller - 1) {
          quickDone.push(quickCurrentPart.a);
        }
        if (quickSmaller + 1 < quickCurrentPart.b) {
          quickParts.push(new Part(quickSmaller + 1, quickCurrentPart.b));
        } else if (quickSmaller + 1 === quickCurrentPart.b) {
          quickDone.push(quickCurrentPart.b);
        }
        quickCurrentPart = null;
        quickCurrent = null;
      } else {
        if (getValue(arrayToSort, quickCurrent) < arrayToSort[quickCurrentPivot]) {
          swap(++quickSmaller, quickCurrent++, arrayToSort);
        } else {
          quickCurrent++;
        }
      }
    }
  }
  return false;
}

function drawQuickSort(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    if (quickCurrentPivot === index) {
      cnt.fillStyle = "#ff0000";
    } else if (quickDone.indexOf(index) >= 0) {
      cnt.fillStyle = "#55b809";
    } else if (index === quickCurrent) {
      cnt.fillStyle = "#08c7d1";
    } else if (quickCurrentPart && quickCurrentPart.contains(index) && index <= quickSmaller) {
      cnt.fillStyle = "#fcd303";
    } else {
      cnt.fillStyle = "#ffffff";
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
  cnt.fillStyle = "#ff0000";
  cnt.strokeStyle = "#FF0000";
  cnt.setLineDash([5, 3]);
  cnt.beginPath();
  cnt.moveTo(0, cnt.canvas.height * arrayToSort[quickCurrentPivot]);
  cnt.lineTo(cnt.canvas.width, cnt.canvas.height * arrayToSort[quickCurrentPivot]);
  cnt.stroke();
  if (quickCurrentPart) {
    cnt.fillRect(quickCurrentPart.a * sizeOfBlock, 0, 1, cnt.canvas.height);
    cnt.fillRect((quickCurrentPart.b + 1) * sizeOfBlock - 1, 0, 1, cnt.canvas.height);
  }
}

function quickSortReset() {
  quickParts = null;
  quickCurrent = null;
  quickCurrentPart = null;
  quickDone = null;
  quickCurrentPivot = null;
  quickSmaller = null;
}
