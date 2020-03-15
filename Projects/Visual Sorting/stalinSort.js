/* eslint-disable space-before-function-paren */
// Stalin Sort
let stalinIndex

function stalinSort() {
  if (!stalinIndex) {
    restartVariables("ALL")
    stalinIndex = 0
  }
  if (arrayToSort[stalinIndex] <= arrayToSort[stalinIndex + 1]) {
    stalinIndex++
  } else {
    arrayToSort.splice(stalinIndex + 1, 1)
  }
  return stalinIndex === arrayToSort.length - 1
}

function drawStalinSort(cnt) {
  cnt.fillStyle = "#000"
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height)
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index <= stalinIndex) {
      cnt.fillStyle = "#55b809"
    } else {
      cnt.fillStyle = "#ffffff"
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element)
  })
}
function stalinSortReset() {
  stalinIndex = null
}
