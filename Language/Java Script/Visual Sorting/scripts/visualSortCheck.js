/* eslint-disable space-before-function-paren */
// RandomSort
let sortChecking = false;
let sortCheckIndex = 0;
function sortCheck() {
  if (sortChecking) {
    getValue(arrayToSort, sortCheckIndex);
    sortCheckIndex++;
  } else {
    sortChecking = true;
    sortCheckIndex = 0;
  }
  return sortCheckIndex === arrayToSort.length;
}

function drawSortCheck(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  const sortedArray = [...arrayToSort].sort();
  arrayToSort.forEach((element, index) => {
    if (sortCheckIndex >= index) {
      if (element === sortedArray[index]) {
        cnt.fillStyle = "#55b809";
      } else {
        cnt.fillStyle = "#e01f1f";
      }
    } else {
      cnt.fillStyle = "#FFF";
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
}

function sortCheckReset() {
  sortChecking = false;
}
