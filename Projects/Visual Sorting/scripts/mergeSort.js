/* eslint-disable space-before-function-paren */
// Marge Sort
let margeMainPart
let margeCurrentHead
let margePartAPointer
let margePartBPointer

function makePartsMargeSort(part) {
  if (part.size() >= 1) {
    let subPart = new MargeSortPart(part.a, part.a + Math.floor(part.size() / 2), part)
    part.subParts.push(subPart)
    makePartsMargeSort(subPart)
    // console.log(subPart)
    subPart = new MargeSortPart(part.a + Math.floor(part.size() / 2) + 1, part.b, part)
    part.subParts.push(subPart)
    makePartsMargeSort(subPart)
    // console.log(subPart)
  } else {
    part.sorted = true
  }
}

class MargeSortPart {
  constructor(a, b, head) {
    this.a = a
    this.b = b
    this.head = head
    this.subParts = []
    if (a > b) {
      console.log(this.size())
    }
    this.sorted = false
  }

  size() {
    return this.b - this.a
  }
}

function margeSort() {
  if (!margeMainPart) {
    restartVariables("ALL")
    margeMainPart = new MargeSortPart(0, arrayToSort.length - 1, null)
    makePartsMargeSort(margeMainPart)
  } else if (!margeCurrentHead) {
    margeCurrentHead = margeMainPart
    while (margeCurrentHead.subParts.length >= 2) {
      margeCurrentHead = margeCurrentHead.subParts[0]
    }
    margeCurrentHead = margeCurrentHead.head
  } else if (margePartBPointer === null) {
    margePartAPointer = margeCurrentHead.subParts[0].a
    margePartBPointer = margeCurrentHead.subParts[1].a
  } else if (
    margePartBPointer > margeCurrentHead.subParts[1].b ||
    margePartAPointer > margeCurrentHead.subParts[1].b ||
    margePartAPointer >= arrayToSort.length ||
    margePartBPointer >= arrayToSort.length
  ) {
    margePartBPointer = null
    margeCurrentHead.sorted = true
    // margeCurrentHead.subParts[0].sorted = true
    // margeCurrentHead.subParts[1].sorted = true
    if (margeCurrentHead.head) {
      margeCurrentHead = margeCurrentHead.head
      while (margeCurrentHead.subParts[1] || margeCurrentHead.subParts[0]) {
        if (margeCurrentHead.subParts[0] && !margeCurrentHead.subParts[0].sorted) {
          margeCurrentHead = margeCurrentHead.subParts[0]
        } else if (margeCurrentHead.subParts[1] && !margeCurrentHead.subParts[1].sorted) {
          margeCurrentHead = margeCurrentHead.subParts[1]
        } else {
          break
        }
      }
    } else {
      return true
    }
  } else if (arrayToSort[margePartAPointer] < arrayToSort[margePartBPointer]) {
    margePartAPointer++
  } else {
    move(margePartBPointer, margePartAPointer, arrayToSort)
    margePartBPointer++
  }
}

function drawMergeSort(cnt) {
  cnt.fillStyle = "#000"
  cnt.fillRect(0, 0, cnt.canvas.width, cnt.canvas.height)
  const sizeOfBlock = cnt.canvas.width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index === margePartAPointer) {
      cnt.fillStyle = "#ff0000"
    } else if (index === margePartBPointer) {
      cnt.fillStyle = "#c6d618"
    } else {
      cnt.fillStyle = "#FFF"
    }
    cnt.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, cnt.canvas.height * element)
  })
  cnt.fillStyle = "#ff0000"
  if (margeCurrentHead) {
    cnt.fillRect(margeCurrentHead.a * sizeOfBlock, 0, 1, cnt.canvas.height)
    cnt.fillRect((margeCurrentHead.b + 1) * sizeOfBlock - 1, 0, 1, cnt.canvas.height)
  }
}

function mergeSortReset() {
  margeMainPart = null
  margeCurrentHead = null
  margePartAPointer = null
  margePartBPointer = null
}
