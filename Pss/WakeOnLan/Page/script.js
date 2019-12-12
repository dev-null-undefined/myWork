let mac;
let x = 0;
let macAddres = "";
function loaded() {
  mac = document.getElementById("macAddress");
  // console.log(mac);
  mac.addEventListener("keydown", keyDown);
}
function keyDown(params) {
  console.log(params);
  params.preventDefault();
  if (params.key == "Backspace") {
    x--;
    if (x % 2) {
      macAddres = macAddres.substring(0, macAddres.length - 2);
    } else {
      macAddres = macAddres.substring(0, macAddres.length - 1);
    }
  } else {
    if (x >= 12) {
      return;
    }
    switch (params.key) {
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
      case "8":
      case "9":
      case "A":
      case "B":
      case "C":
      case "D":
      case "E":
      case "F":
      case "a":
      case "b":
      case "c":
      case "d":
      case "e":
      case "f":
        x++;
        break;
      default:
        return;
    }
  }
  if (params.key != "Backspace") {
    if (x % 2 == 0&&x!=12) {
      macAddres += params.key.toUpperCase() + ":";
    } else {
      macAddres += params.key.toUpperCase();
    }
  }
  mac.value = macAddres;
  for (let a = x+1; a <= 12; a++) {
    if (a % 2 == 0&&a!=12) {
      mac.value += "0:";
    } else {
      mac.value += "0";
    }
  }
}
