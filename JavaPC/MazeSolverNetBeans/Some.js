function getString(array) {
  var string = "";
  for (let i = 1; i < array.length; i++) {
    string +=
      "jde " +
      getBefore(array, i - 1) +
      " a potka " +
      array[i] +
      ".\nA " +
      array[i] +
      ' povida:"' +
      getBefore(array, i - 1) +
      ' mohu jit s vami?"\nPøièemž ' +
      getBefore(array, i - 1) +
      ' odpovi: "Jo mùžeš."\nA tak ';
  }
  return string;
}
function getStringSMaslem(array) {
  array = getChlebaSMaslem(array);
  var string = "";
  for (let i = 1; i < array.length; i++) {
    string +=
      "jde " +
      getBefore(array, i - 1) +
      " a potka " +
      array[i] +
      ".\nA " +
      array[i] +
      ' povida:"' +
      getBefore(array, i - 1) +
      ' mohu jit s vami?"\nPøièemž ' +
      getBefore(array, i - 1) +
      ' odpovi: "Jo mùžeš."\nA tak ';
  }
  return string;
}
function getBefore(array, index) {
  let string = "";
  for (let i = 0; i < index + 1; i++) {
    string += array[i] + ",";
  }
  return string;
}

function getChlebaSMaslem(array) {
  var newArray = [];
  for (let i = 0; i < array.length; i++) {
    newArray.push(array[i]);
    newArray.push(array[i] + " s maslem");
    newArray.push(array[i] + " s maslem " + " se salamem");
  }
  return newArray;
}
