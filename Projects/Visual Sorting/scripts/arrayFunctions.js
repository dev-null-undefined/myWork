/* eslint-disable space-before-function-paren */
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
