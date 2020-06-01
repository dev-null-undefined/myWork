/* eslint-disable space-before-function-paren */
// Insertion Sort
let insertionIndex;
let insertionDone;
let insertionComparing;
function insertSort() {
  if (!insertionIndex) {
    restartVariables("ALL");
    insertionIndex = 1;
    insertionDone = 0;
    insertionComparing = 0;
  } else if (insertionDone === arrayToSort.length - 1) {
    return true;
  } else if (getValue(arrayToSort, insertionIndex) > getValue(arrayToSort, insertionComparing)) {
    move(insertionIndex, insertionComparing + 1, arrayToSort);
    insertionDone++;
    insertionIndex = insertionDone + 1;
    insertionComparing = insertionDone;
  } else if (insertionComparing === 0) {
    move(insertionIndex, 0, arrayToSort);
    insertionDone++;
    insertionIndex = insertionDone + 1;
    insertionComparing = insertionDone;
  } else {
    insertionComparing--;
  }
}

function drawInsertionSort(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    if (index === insertionIndex) {
      cnt.fillStyle = "#0e66c9";
    } else if (index === insertionComparing) {
      cnt.fillStyle = "#c6d618";
    } else if (index <= insertionDone) {
      cnt.fillStyle = "#35d618";
    } else {
      cnt.fillStyle = "#FFF";
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
}

function insertionSortReset() {
  insertionIndex = null;
  insertionDone = null;
  insertionComparing = null;
}
