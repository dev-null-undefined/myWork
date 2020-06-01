var url = new URL(window.location.href);

var pageNumber = url.searchParams.get("i");

var dir = url.searchParams.get("DIR");

function getUrlWithoutParams() {
  return window.location.href.split("?")[0];
}
function nextPage() {
  window.location.href = getUrlWithoutParams() + "?DIR=" + dir + "&i=" + (pageNumber == null ? "16" : parseInt(pageNumber) + 16);
}
function backPage() {
  window.location.href = getUrlWithoutParams() + "?DIR=" + dir + "&i=" + (pageNumber == null ? "" : pageNumber > 0 ? pageNumber - 16 : pageNumber);
}
function load(myimg) {
  var img = document.getElementById(myimg);
  img.src = img.getAttribute("data-src");
  id = myimg.replace(/^\D+/g, "");
  location.replace("#pic-" + id);
  document.onkeydown = galleryNav;
}
function galleryNav(event) {
  var keyDown = event || window.event,
    key = keyDown.which ? keyDown.which : keyDown.keyCode;
  keyCheck(key);
}
function closeX() {
  id = null;
  location.replace("#close");
}
function keyCheck(keyCodeNum) {
  var count = 0;
  var num = document.getElementById("gallery").getElementsByTagName("img");
  for (var i = 0; i < num.length; i++) {
    if (num[i].className == "big-img") {
      count++;
    }
  }
  switch (keyCodeNum) {
    case 27:
      closeX();
      break;
    case 37:
      id--;
      id < 1 ? closeX() : load("img-" + id);
      break;
    case 39:
      id++;
      id > count ? closeX() : load("img-" + id);
      break;
    default:
      break;
  }
}
function mouseX(evt) {
  if (evt.pageX) {
    return evt.pageX;
  } else if (evt.clientX) {
    return evt.clientX + (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
  } else {
    return null;
  }
}

function mouseY(evt) {
  if (evt.pageY) {
    return evt.pageY;
  } else if (evt.clientY) {
    return evt.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
  } else {
    return null;
  }
}

let menuVisible = false;
let folderMenu = false;

const toggleMenu = (command) => {
  menu.style.display = command === "show" ? "block" : "none";
  menuVisible = command === "show" ? true : false;
};

const setPosition = ({ top, left }) => {
  menu.style.left = `${left}px`;
  menu.style.top = `${top}px`;
  //toggleMenu("show");
};
window.addEventListener("click", (e) => {
  if (menuVisible) toggleMenu("hide");
});
window.addEventListener("contextmenu", (e) => {
  e.preventDefault();
  const origin = {
    left: mouseX(e),
    top: mouseY(e),
  };
  if (folderMenu) {
    setPosition(origin);
    folderMenu = false;
    let src = e.path[0].src;
    src = src.replace("casheFiles/", "");
    src = src.replace(".small", "");
    document.getElementsByClassName("menu-option")[0].parentElement.href = src;
    document.getElementsByClassName("menu-option")[1].parentElement.href = src;
    document.getElementsByClassName("menu-option")[2].parentElement.href = src;
  } else {
    if (menuVisible) toggleMenu("hide");
  }

  return false;
});

function imageContextMenu() {
  toggleMenu("show");
  folderMenu = true;
}
