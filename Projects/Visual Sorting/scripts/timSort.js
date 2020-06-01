/* eslint-disable space-before-function-paren */
// Tim Sort
const timPartSize = 32;
let timParts;
let timIndex;
let timDone;
let timComparing;
let timCurrentPart;
let timPointerA;
let timPointerB;
let timDoneInserting;
let timNumberOfMarges;
let timMargesMultiplier;

function timSort() {
  if (!timParts) {
    restartVariables("ALL");
    timNumberOfMarges = 0;
    timParts = [];
    let deviderIndex = 0;
    timMargesMultiplier = 2;
    do {
      timParts.push(new Part(deviderIndex, deviderIndex + timPartSize));
      deviderIndex += timPartSize;
    } while (deviderIndex < arrayToSort.length);
  } else if (timCurrentPart === null) {
    timCurrentPart = 0;
  } else if (!timDoneInserting) {
    // Inserting part of Tim sort
    const part = timParts[timCurrentPart];
    if (!timIndex) {
      timIndex = part.a + 1;
      timDone = part.a;
      timComparing = part.a;
    } else if (timDone === arrayToSort.length - 1 || timDone === part.b) {
      if (timCurrentPart < timParts.length - 1) {
        timCurrentPart++;
      } else {
        timDoneInserting = true;
      }
    } else if (arrayToSort[timIndex] > getValue(arrayToSort, timComparing)) {
      move(timIndex, timComparing + 1, arrayToSort);
      timDone++;
      timIndex = timDone + 1;
      timComparing = timDone;
    } else if (timComparing === part.a) {
      move(timIndex, part.a, arrayToSort);
      timDone++;
      timIndex = timDone + 1;
      timComparing = timDone;
    } else {
      timComparing--;
    }
  } else if (timPointerB === null) {
    // Marge part of the Tim sort
    timPointerB = (timNumberOfMarges + timMargesMultiplier / 2) * timPartSize;
    timPointerA = timNumberOfMarges * timPartSize;
  } else {
    if (
      timPointerA >= (timNumberOfMarges + timMargesMultiplier) * timPartSize ||
      timPointerB >= (timNumberOfMarges + timMargesMultiplier) * timPartSize ||
      timPointerB >= arrayToSort.length ||
      timPointerA >= arrayToSort.length
    ) {
      if (timPointerB >= arrayToSort.length || timPointerA >= arrayToSort.length) {
        if (timMargesMultiplier * timPartSize < arrayToSort.length) {
          timMargesMultiplier *= 2;
          timNumberOfMarges = 0;
          timPointerB = null;
        } else {
          return true;
        }
      } else {
        timNumberOfMarges += timMargesMultiplier;
        timPointerB = null;
      }
    } else if (arrayToSort[timPointerA] < getValue(arrayToSort, timPointerB)) {
      timPointerA++;
    } else {
      move(timPointerB, timPointerA, arrayToSort);
      timPointerB++;
    }
  }
}

function drawTimSort(cnt) {
  cnt.fillStyle = "#000";
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height);
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length;
  arrayToSort.forEach((element, index) => {
    if (timDoneInserting) {
      if (index === timPointerA) {
        cnt.fillStyle = "#ff0000";
      } else if (index === timPointerB) {
        cnt.fillStyle = "#c6d618";
      } else {
        cnt.fillStyle = "#FFF";
      }
    } else if (index === timIndex) {
      cnt.fillStyle = "#0e66c9";
    } else if (index === timComparing) {
      cnt.fillStyle = "#c6d618";
    } else if (index <= timDone && index >= timCurrentPart * timPartSize) {
      cnt.fillStyle = "#35d618";
    } else {
      cnt.fillStyle = "#FFF";
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element);
  });
  cnt.fillStyle = "#ff0000";
  if (!timDoneInserting && timCurrentPart !== null) {
    cnt.fillRect(timParts[timCurrentPart].a * sizeOfBlock, 0, 2, cnt.canvas.height);
    cnt.fillRect((timParts[timCurrentPart].b + 1) * sizeOfBlock - 1, 0, 2, cnt.canvas.height);
  } else if (timDoneInserting) {
    cnt.fillRect(timNumberOfMarges * timPartSize * sizeOfBlock, 0, 2, cnt.canvas.height);
    cnt.fillRect((timNumberOfMarges + timMargesMultiplier) * timPartSize * sizeOfBlock - 1, 0, 2, cnt.canvas.height);
  }
}

function timSortReset() {
  timParts = null;
  timIndex = null;
  timDone = null;
  timComparing = null;
  timCurrentPart = null;
  timPointerA = null;
  timPointerB = null;
  timDoneInserting = null;
  timNumberOfMarges = null;
  timMargesMultiplier = null;
}
