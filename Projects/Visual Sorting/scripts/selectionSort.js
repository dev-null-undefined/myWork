/* eslint-disable space-before-function-paren */
// Selection sort
let selectionDoneIndex;
let selectionCurrentMinimumIndex;

function selectionSort() {
  if (!selectionDoneIndex) {
    restartVariables("ALL");
    selectionDoneIndex = 0;
  }
  selectionCurrentMinimumIndex = minIndex(arrayToSort.slice(selectionDoneIndex));
  swap(selectionDoneIndex + selectionCurrentMinimumIndex, selectionDoneIndex, arrayToSort);
  getValue(arrayToSort, selectionDoneIndex++);
  return selectionDoneIndex === arrayToSort.length;
}

function drawSelectionSort(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    if (index === selectionCurrentMinimumIndex + selectionDoneIndex) {
      cnt.fillStyle = "#5255eb";
    } else {
      if (index < selectionDoneIndex) {
        cnt.fillStyle = "#55b809";
      } else {
        cnt.fillStyle = "#ffffff";
      }
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
}

function selectionSortReset() {
  selectionDoneIndex = null;
  selectionCurrentMinimumIndex = null;
}
