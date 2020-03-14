/* eslint-disable space-before-function-paren */

class Part {
  constructor(a, b) {
    this.a = a
    this.b = b
  }

  contains(c) {
    return c >= this.a && c <= this.b
  }

  size() {
    return this.b - this.a
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

let sortingInterval = null
let arrayToSort = []
let sortType = null

const speedSlider = document.getElementById("speed")
const sizeSlider = document.getElementById("arraySize")
const generateButton = document.getElementById("generator")
const sortMethodes = document.getElementById("sortMethods")
const startButton = document.getElementById("startButton")
const sizeSliderInfo = document.getElementById("arraySizeText")
const speedSliderInfo = document.getElementById("speedText")
const canvas = document.getElementById("canvas")

let width = window.innerWidth * 0.8
let height = window.innerHeight * 0.7
canvas.width = width
canvas.height = height

const content = canvas.getContext("2d")

// #region Sorts variables
// Buble sort variables
let bubleCurrent = null
let bubleLoop = null

// Quick Sort
let quickParts
let quickCurrent
let quickSmaller
let quickCurrentPart
let quickDone
let quickCurrentPivot

// Marge Sort
let margeMainPart
let margeCurrentHead
let margePartAPointer
let margePartBPointer

// Stalin Sort
let stalinIndex

// RandomSort
let randomSorting = false

// Selection sort
let selectionDoneIndex
let selectionCurrentMinimumIndex

// Tim Sort
const timPartSize = 32
let timParts
let timIndex
let timDone
let timComparing
let timCurrentPart
let timPointerA
let timPointerB
let timDoneInserting
let timNumberOfMarges
let timMargesMultiplier

// Insertion Sort
let insertionIndex
let insertionDone
let insertionComparing
// #endregion

// Chack box from buttons
function markChosen(element) {
  element.className = "selectedSort"
}
function unMarkChosen(element) {
  element.className = element.className.replace("selectedSort", "")
}
function choseSortType(event) {
  if (!sortType) {
    markChosen(event.target)
    sortType = event.target
  } else {
    unMarkChosen(sortType)
    markChosen(event.target)
    sortType = event.target
  }
}

// Array Functions
function randomizePositions(array, timesToSwap = array.length) {
  for (let i = 0; i < array.length; i++) {
    swap(Math.floor(Math.random() * array.length), Math.floor(Math.random() * array.length), array)
  }
  return array
}
function isSorted(array) {
  const sortedArray = [...array].sort()
  let sorted = true
  array.forEach((element, index) => {
    if (element !== sortedArray[index]) {
      sorted = false
    }
  })
  return sorted
}
function swap(a, b, array) {
  const itemA = array[a]
  array[a] = array[b]
  array[b] = itemA
}
function move(indexA, indexB, array) {
  return array.splice(indexB, 0, array.splice(indexA, 1)[0])
}
function generateArray(length) {
  const array = []
  for (let i = 0; i < length; i++) {
    array.push(Math.random())
  }
  return array
}
function minIndex(array) {
  let minimumIndex = 0
  array.forEach((element, index) => {
    if (element < array[minimumIndex]) {
      minimumIndex = index
    }
  })
  return minimumIndex
}

function generateArrayAndDraw(event) {
  arrayToSort = generateArray(sizeSlider.value)
  draw()
}

// #region Adding event Listener
Array.from(sortMethodes.getElementsByTagName("button")).forEach(element => {
  element.addEventListener("click", choseSortType)
})
generateButton.addEventListener("click", generateArrayAndDraw)
startButton.addEventListener("click", startSorting)
speedSlider.onchange = speedSliderOnChange
sizeSlider.onchange = sizeSliderOnChange
function sizeSliderOnChange(event) {
  sizeSliderInfo.innerText = "Array size: " + event.target.value
}
function speedSliderOnChange(event) {
  speedSliderInfo.innerText = "Speed: " + event.target.value
}
window.onresize = windowsResize
function windowsResize() {
  width = window.innerWidth * 0.8
  height = window.innerHeight * 0.7
  canvas.width = width
  canvas.height = height
  draw()
}
// #endregion

function startSorting() {
  if (sortType) {
    if (sortingInterval) {
      clearInterval(sortingInterval)
      sortingInterval = null
      startButton.innerText = "Start sorting"
      generateButton.disabled = false
      sizeSlider.disabled = false
    } else {
      restartVariables("ALL")
      startButton.innerText = "Stop sorting"
      generateButton.disabled = true
      sizeSlider.disabled = true
      sortingRecursion()
    }
  } else {
    alert("!CHOSE SORT METHODE!")
  }
}
function doneSorting() {
  startButton.innerText = "Start sorting"
  generateButton.disabled = false
  sizeSlider.disabled = false
  sortingInterval = null
}

function restartVariables(sortMethod) {
  switch (sortMethod) {
    case "Insertion Sort":
      // #region Insertion Sort
      insertionIndex = null
      insertionDone = null
      insertionComparing = null
      break
    // #endregion
    case "Marge Sort":
      // #region Marge Sort
      margeMainPart = null
      margeCurrentHead = null
      margePartAPointer = null
      margePartBPointer = null
      break
    // #endregion
    case "Tim Sort":
      // #region Tim Sort
      timParts = null
      timIndex = null
      timDone = null
      timComparing = null
      timCurrentPart = null
      timPointerA = null
      timPointerB = null
      timDoneInserting = null
      timNumberOfMarges = null
      timMargesMultiplier = null
      break
    // #endregion
    case "Stalin Sort":
      // #region Stalin Sort
      stalinIndex = null
      break
    // #endregion
    case "Quick Sort":
      // #region Quick Sort
      quickParts = null
      quickCurrent = null
      quickCurrentPart = null
      quickDone = null
      quickCurrentPivot = null
      quickSmaller = null
      break
    // #endregion
    case "Bubble Sort":
      // #region Bubble Sort
      bubleCurrent = null
      bubleLoop = null
      break
    // #endregion
    case "Random sort":
      // #region Random sort
      randomSorting = false
      break
    // #endregion
    case "Selection sort":
      // #region Selection sort
      selectionDoneIndex = null
      selectionCurrentMinimumIndex = null
      break
    // #endregion
    default:
      // #region Default
      restartVariables("Insertion Sort")
      restartVariables("Marge Sort")
      restartVariables("Tim Sort")
      restartVariables("Stalin Sort")
      restartVariables("Quick Sort")
      restartVariables("Bubble Sort")
      restartVariables("Random sort")
      restartVariables("Selection sort")
      break
    // #endregion
  }
}

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

function sortingRecursion() {
  let isDoneSorting = false
  switch (sortType.innerText) {
    case "Marge Sort":
      // #region Marge Sort
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
          isDoneSorting = true
        }
      } else if (arrayToSort[margePartAPointer] < arrayToSort[margePartBPointer]) {
        margePartAPointer++
      } else {
        move(margePartBPointer, margePartAPointer, arrayToSort)
        margePartBPointer++
      }
      drawMargeSort()
      break
    // #endregion
    case "Insertion Sort":
      // #region Insertion Sort
      if (!insertionIndex) {
        restartVariables("ALL")
        insertionIndex = 1
        insertionDone = 0
        insertionComparing = 0
      } else if (insertionDone === arrayToSort.length - 1) {
        isDoneSorting = true
      } else if (arrayToSort[insertionIndex] > arrayToSort[insertionComparing]) {
        move(insertionIndex, insertionComparing + 1, arrayToSort)
        insertionDone++
        insertionIndex = insertionDone + 1
        insertionComparing = insertionDone
      } else if (insertionComparing === 0) {
        move(insertionIndex, 0, arrayToSort)
        insertionDone++
        insertionIndex = insertionDone + 1
        insertionComparing = insertionDone
      } else {
        insertionComparing--
      }
      drawInsertionSort()
      break
    // #endregion
    case "Tim Sort":
      // #region Tim Sort
      if (!timParts) {
        restartVariables("ALL")
        timNumberOfMarges = 0
        timParts = []
        let deviderIndex = 0
        timMargesMultiplier = 2
        do {
          timParts.push(new Part(deviderIndex, deviderIndex + timPartSize))
          deviderIndex += timPartSize
        } while (deviderIndex < arrayToSort.length)
      } else if (timCurrentPart === null) {
        timCurrentPart = 0
      } else if (!timDoneInserting) {
        // Inserting part of Tim sort
        const part = timParts[timCurrentPart]
        if (!timIndex) {
          timIndex = part.a + 1
          timDone = part.a
          timComparing = part.a
        } else if (timDone === arrayToSort.length - 1 || timDone === part.b) {
          if (timCurrentPart < timParts.length - 1) {
            timCurrentPart++
          } else {
            timDoneInserting = true
          }
        } else if (arrayToSort[timIndex] > arrayToSort[timComparing]) {
          move(timIndex, timComparing + 1, arrayToSort)
          timDone++
          timIndex = timDone + 1
          timComparing = timDone
        } else if (timComparing === part.a) {
          move(timIndex, part.a, arrayToSort)
          timDone++
          timIndex = timDone + 1
          timComparing = timDone
        } else {
          timComparing--
        }
      } else if (timPointerB === null) {
        // Marge part of the Tim sort
        timPointerB = (timNumberOfMarges + timMargesMultiplier / 2) * timPartSize
        timPointerA = timNumberOfMarges * timPartSize
      } else {
        if (
          timPointerA >= (timNumberOfMarges + timMargesMultiplier) * timPartSize ||
          timPointerB >= (timNumberOfMarges + timMargesMultiplier) * timPartSize ||
          timPointerB >= arrayToSort.length ||
          timPointerA >= arrayToSort.length
        ) {
          if (timPointerB >= arrayToSort.length || timPointerA >= arrayToSort.length) {
            if (timMargesMultiplier * timPartSize < arrayToSort.length) {
              timMargesMultiplier *= 2
              timNumberOfMarges = 0
              timPointerB = null
            } else {
              isDoneSorting = true
            }
          } else {
            timNumberOfMarges += timMargesMultiplier
            timPointerB = null
          }
        } else if (arrayToSort[timPointerA] < arrayToSort[timPointerB]) {
          timPointerA++
        } else {
          move(timPointerB, timPointerA, arrayToSort)
          timPointerB++
        }
      }
      drawTimSort()
      break
    // #endregion
    case "Quick Sort":
      // #region Quick sort
      if (!quickParts) {
        restartVariables("ALL")
        quickParts = []
        quickDone = []
        quickParts.push(new Part(0, arrayToSort.length - 1))
      }
      if (!quickCurrentPart) {
        if (quickParts.length === 0) {
          isDoneSorting = true
          break
        }
        quickCurrentPart = quickParts.pop()
        if (quickCurrentPart.a === quickCurrentPart.b) {
          isDoneSorting = true
        } else {
          quickCurrentPivot = quickCurrentPart.a + Math.floor(Math.random() * (quickCurrentPart.b - quickCurrentPart.a))
        }
      } else {
        if (!quickCurrent) {
          quickCurrent = quickCurrentPart.a + 1
          swap(quickCurrentPivot, quickCurrentPart.a, arrayToSort)
          quickCurrentPivot = quickCurrentPart.a
          quickSmaller = quickCurrentPart.a
        } else {
          if (quickCurrent > quickCurrentPart.b) {
            swap(quickSmaller, quickCurrentPart.a, arrayToSort)
            quickDone.push(quickSmaller)
            // add some code
            if (quickCurrentPart.a < quickSmaller - 1) {
              quickParts.push(new Part(quickCurrentPart.a, quickSmaller - 1))
            } else if (quickCurrentPart.a === quickSmaller - 1) {
              quickDone.push(quickCurrentPart.a)
            }
            if (quickSmaller + 1 < quickCurrentPart.b) {
              quickParts.push(new Part(quickSmaller + 1, quickCurrentPart.b))
            } else if (quickSmaller + 1 === quickCurrentPart.b) {
              quickDone.push(quickCurrentPart.b)
            }
            quickCurrentPart = null
            quickCurrent = null
          } else {
            if (arrayToSort[quickCurrent] < arrayToSort[quickCurrentPivot]) {
              swap(++quickSmaller, quickCurrent++, arrayToSort)
            } else {
              quickCurrent++
            }
          }
        }
      }
      drawQuickSort()
      break
    // #endregion
    case "Stalin Sort":
      // #region Stalin Sort
      if (!stalinIndex) {
        restartVariables("ALL")
        stalinIndex = 0
      }
      if (arrayToSort[stalinIndex] <= arrayToSort[stalinIndex + 1]) {
        stalinIndex++
      } else {
        arrayToSort.splice(stalinIndex + 1, 1)
      }
      if (stalinIndex === arrayToSort.length - 1) {
        isDoneSorting = true
      }
      drawStalinSort()
      break
    // #endregion
    case "Selection sort":
      // #region Selection sort
      if (!selectionDoneIndex) {
        restartVariables("ALL")
        selectionDoneIndex = 0
      }
      selectionCurrentMinimumIndex = minIndex(arrayToSort.slice(selectionDoneIndex))
      swap(selectionDoneIndex + selectionCurrentMinimumIndex, selectionDoneIndex, arrayToSort)
      selectionDoneIndex++
      if (selectionDoneIndex === arrayToSort.length) {
        isDoneSorting = true
      }
      drawSelectionSort()
      break
    // #endregion
    case "Bubble Sort":
      // #region Bubble Sort
      if (!bubleCurrent && !bubleLoop) {
        restartVariables("ALL")
        bubleLoop = 0
        bubleCurrent = 0
      }
      if (bubleCurrent + bubleLoop >= arrayToSort.length - 1) {
        if (bubleCurrent === 0) {
          bubleCurrent = -1
          bubleLoop++
          isDoneSorting = true
        } else {
          bubleCurrent = 0
          bubleLoop++
        }
      } else {
        if (arrayToSort[bubleCurrent] > arrayToSort[bubleCurrent + 1]) {
          swap(bubleCurrent, bubleCurrent + 1, arrayToSort)
        }
        bubleCurrent++
      }
      drawBubleSort()
      break
    // #endregion
    case "Random sort":
      // #region Random sort
      if (!randomSorting) {
        restartVariables("ALL")
        randomSorting = true
      }
      arrayToSort = randomizePositions(arrayToSort)
      if (isSorted(arrayToSort)) {
        isDoneSorting = true
      }
      drawRandomSort()
      break
    // #endregion
  }
  if (isDoneSorting) {
    if (sortingInterval) {
      clearInterval(sortingInterval)
    }
    doneSorting()
  } else {
    if (sortingInterval) {
      clearInterval(sortingInterval)
    }
    sortingInterval = setInterval(sortingRecursion, speedSlider.min + (speedSlider.max - speedSlider.value))
  }
}

// #region Draw
function draw() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    content.fillStyle = "#FFF"
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
// #region Draw Sorts
function drawRandomSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  const sortedArray = [...arrayToSort].sort()
  arrayToSort.forEach((element, index) => {
    if (element === sortedArray[index]) {
      content.fillStyle = "#55b809"
    } else {
      content.fillStyle = "#e01f1f"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
function drawBubleSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index === bubleCurrent || index === bubleCurrent + 1) {
      content.fillStyle = "#5255eb"
    } else if (index >= arrayToSort.length - bubleLoop) {
      content.fillStyle = "#55b809"
    } else {
      content.fillStyle = "#ffffff"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
function drawSelectionSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index === selectionCurrentMinimumIndex + selectionDoneIndex) {
      content.fillStyle = "#5255eb"
    } else {
      if (index < selectionDoneIndex) {
        content.fillStyle = "#55b809"
      } else {
        content.fillStyle = "#ffffff"
      }
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
function drawStalinSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index <= stalinIndex) {
      content.fillStyle = "#55b809"
    } else {
      content.fillStyle = "#ffffff"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
function drawQuickSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (quickCurrentPivot === index) {
      content.fillStyle = "#ff0000"
    } else if (quickDone.indexOf(index) >= 0) {
      content.fillStyle = "#55b809"
    } else if (index === quickCurrent) {
      content.fillStyle = "#08c7d1"
    } else if (quickCurrentPart && quickCurrentPart.contains(index) && index < quickSmaller) {
      content.fillStyle = "#fcd303"
    } else {
      content.fillStyle = "#ffffff"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
  content.fillStyle = "#ff0000"
  content.strokeStyle = "#FF0000"
  content.setLineDash([5, 3])
  content.beginPath()
  content.moveTo(0, height * arrayToSort[quickCurrentPivot])
  content.lineTo(width, height * arrayToSort[quickCurrentPivot])
  content.stroke()
  if (quickCurrentPart) {
    content.fillRect(quickCurrentPart.a * sizeOfBlock, 0, 1, height)
    content.fillRect((quickCurrentPart.b + 1) * sizeOfBlock - 1, 0, 1, height)
  }
}
function drawTimSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (timDoneInserting) {
      if (index === timPointerA) {
        content.fillStyle = "#ff0000"
      } else if (index === timPointerB) {
        content.fillStyle = "#c6d618"
      } else {
        content.fillStyle = "#FFF"
      }
    } else if (index === timIndex) {
      content.fillStyle = "#0e66c9"
    } else if (index === timComparing) {
      content.fillStyle = "#c6d618"
    } else if (index <= timDone && index >= timCurrentPart * timPartSize) {
      content.fillStyle = "#35d618"
    } else {
      content.fillStyle = "#FFF"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
  content.fillStyle = "#ff0000"
  if (!timDoneInserting && timCurrentPart !== null) {
    content.fillRect(timParts[timCurrentPart].a * sizeOfBlock, 0, 2, height)
    content.fillRect((timParts[timCurrentPart].b + 1) * sizeOfBlock - 1, 0, 2, height)
  } else if (timDoneInserting) {
    content.fillRect(timNumberOfMarges * timPartSize * sizeOfBlock, 0, 2, height)
    content.fillRect((timNumberOfMarges + timMargesMultiplier) * timPartSize * sizeOfBlock - 1, 0, 2, height)
  }
}
function drawMargeSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index === margePartAPointer) {
      content.fillStyle = "#ff0000"
    } else if (index === margePartBPointer) {
      content.fillStyle = "#c6d618"
    } else {
      content.fillStyle = "#FFF"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
  content.fillStyle = "#ff0000"
  if (margeCurrentHead) {
    content.fillRect(margeCurrentHead.a * sizeOfBlock, 0, 1, height)
    content.fillRect((margeCurrentHead.b + 1) * sizeOfBlock - 1, 0, 1, height)
  }
}
function drawInsertionSort() {
  content.fillStyle = "#000"
  content.fillRect(0, 0, width, height)
  const sizeOfBlock = width / arrayToSort.length
  arrayToSort.forEach((element, index) => {
    if (index === insertionIndex) {
      content.fillStyle = "#0e66c9"
    } else if (index === insertionComparing) {
      content.fillStyle = "#c6d618"
    } else if (index <= insertionDone) {
      content.fillStyle = "#35d618"
    } else {
      content.fillStyle = "#FFF"
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
// #endregion
// #endregion
generateArrayAndDraw()
