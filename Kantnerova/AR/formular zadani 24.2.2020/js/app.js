document.getElementsByTagName("h1")[0].textContent = "My Activities List";
document.getElementsByTagName("h1")[0].style.fontFamily = "Monospace";
document.getElementsByTagName("h1")[0].style.color = "#0000f0";
document.getElementsByClassName("desc")[0].innerHTML =
  "A list of <i>fun</i> things I want to do today";
let newLiElement = document.createElement("li");
newLiElement.innerHTML = "<input> Eat ice cream";
document.getElementsByTagName("ul")[0].appendChild(newLiElement);
for (x of document.getElementsByTagName("input")) {
  x.type = "checkbox";
}
let deleteBtn = document.createElement("button");
deleteBtn.innerText = "Delete";
deleteBtn.addEventListener("click", x =>
  document.getElementsByClassName("extra")[0].remove()
);
document.getElementsByClassName("extra")[0].appendChild(deleteBtn);
