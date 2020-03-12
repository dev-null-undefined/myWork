/* eslint-disable space-before-function-paren */
class Part {
  constructor(a, b) {
    this.a = a
    this.b = b
  }
}
let sortingInterval = null
let arrayToSort = []

const speedSlider = document.getElementById("speed")
const sizeSlider = document.getElementById("arraySize")
const generateButton = document.getElementById("generator")
const sortMethodes = document.getElementById("sortMethods")
const startButton = document.getElementById("startButton")
const canvas = document.getElementById("canvas")

const width = window.innerWidth * 0.6
const height = window.innerHeight * 0.7
canvas.width = width
canvas.height = height

const content = canvas.getContext("2d")

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

// Stalin Sort
let stalinIndex

// RandomSort
let randomSorting = false

// Selection sort
let selectionDoneIndex
let selectionCurrentMinimumIndex

let sortType = null
// Chack box from buttons
function markChosen(element) {
  element.className = "selectedSort"
}
function unMarkChosen(element) {
  element.className = element.className.replace("selectedSort", "")
}
function choseSortType(event) {
  if (sortType == null) {
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

// Adding event Listener
Array.from(sortMethodes.getElementsByTagName("button")).forEach(element => {
  element.addEventListener("click", choseSortType)
})
generateButton.addEventListener("click", generateArrayAndDraw)
startButton.addEventListener("click", startSorting)

function startSorting() {
  restartVariables("ALL")
  if (sortType !== null) {
    if (sortingInterval !== null) {
      clearInterval(sortingInterval)
      sortingInterval = null
      startButton.innerText = "Start sorting"
      generateButton.disabled = false
      sizeSlider.disabled = false
    } else {
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
  console.log("Done")
}

function restartVariables(sortMethod) {
  switch (sortMethod) {
    case "Marge Sort":
      break
    case "Stalin Sort":
      stalinIndex = null
      break
    case "Quick Sort":
      quickParts = null
      quickCurrent = null
      quickCurrentPart = null
      quickDone = null
      quickCurrentPivot = null
      quickSmaller = null
      break
    case "Bubble Sort":
      bubleCurrent = null
      bubleLoop = null
      break
    case "Random sort":
      randomSorting = false
      break
    case "Selection sort":
      selectionDoneIndex = null
      selectionCurrentMinimumIndex = null
      break
    default:
      restartVariables("Bubble Sort")
      restartVariables("Random sort")
      restartVariables("Quick Sort")
      restartVariables("Marge Sort")
      restartVariables("Selection sort")
      restartVariables("Stalin Sort")
      break
  }
}

function sortingRecursion() {
  let isDoneSorting = false
  switch (sortType.innerText) {
    case "Marge Sort":
      break
    case "Quick Sort":
      if (quickParts === null) {
        restartVariables("ALL")
        quickParts = []
        quickDone = []
        quickParts.push(new Part(0, arrayToSort.length - 1))
        console.log(quickParts)
      }
      if (quickCurrentPart == null) {
        do {
          if (quickParts.length === 0) {
            isDoneSorting = true
            break
          }
          quickCurrentPart = quickParts.pop()
        } while (quickCurrentPart.a === quickCurrentPart.b)
        if (quickCurrentPart.a === quickCurrentPart.b) {
          isDoneSorting = true
        } else {
          quickCurrentPivot = quickCurrentPart.a + Math.floor(Math.random() * (quickCurrentPart.b - quickCurrentPart.a))
        }
      } else {
        if (quickCurrent === null) {
          quickCurrent = quickCurrentPart.a + 1
          swap(quickCurrentPivot, quickCurrentPart.a, arrayToSort)
          quickCurrentPivot = quickCurrentPart.a
          quickSmaller = quickCurrentPart.a + 1
        } else {
          if (quickCurrent === quickCurrentPart.b) {
            swap(quickSmaller, quickCurrentPart.a, arrayToSort)
            quickDone.push(quickSmaller)
            // add some code
            quickParts.push(new Part(quickCurrentPart.a, quickSmaller - 1))
            quickParts.push(new Part(quickSmaller + 1, quickCurrentPart.b))
            quickCurrentPart = null
            quickCurrent = null
          } else {
            if (arrayToSort[quickCurrent] <= arrayToSort[quickCurrentPivot]) {
              swap(quickSmaller, quickCurrent, arrayToSort)
              quickSmaller++
              quickCurrent++
            } else {
              quickCurrent++
            }
          }
        }
      }
      drawQuickSort()
      break
    case "Stalin Sort":
      // #region Stalin Sort
      if (stalinIndex == null) {
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
      if (selectionDoneIndex == null) {
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
      if (bubleCurrent == null || bubleLoop == null) {
        restartVariables("ALL")
        bubleLoop = 0
        bubleCurrent = 0
      }
      if (bubleCurrent + bubleLoop >= arrayToSort.length - 1) {
        if (bubleCurrent === 0) {
          bubleCurrent = -10
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
    if (sortingInterval !== null) {
      clearInterval(sortingInterval)
    }
    doneSorting()
  } else {
    if (sortingInterval !== null) {
      clearInterval(sortingInterval)
    }
    sortingInterval = setInterval(sortingRecursion, speedSlider.max - speedSlider.value)
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
    } else {
      if (index >= arrayToSort.length - bubleLoop) {
        content.fillStyle = "#55b809"
      } else {
        content.fillStyle = "#ffffff"
      }
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
    if (quickDone.indexOf(index) >= 0) {
      content.fillStyle = "#55b809"
    } else {
      if (index <= quickSmaller) {
        content.fillStyle = "#fcd303"
      } else {
        if (index === quickCurrent) {
          content.fillStyle = "#08c7d1"
        } else {
          content.fillStyle = "#ffffff"
        }
      }
    }
    content.fillRect(index * sizeOfBlock + sizeOfBlock * 0.025, 0, sizeOfBlock * 0.95, height * element)
  })
}
// #endregion
// #endregion
generateArrayAndDraw()
