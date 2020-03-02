if (localStorage["teams"] == undefined) {
  localStorage["teams"] = "";
}
let teams = localStorage["teams"].split(";");
teams.forEach(name => {
  if (name != "") {
    element = document.createElement("li");
    element.innerText = name;
    element.className = "teamCard";
    document.getElementById("invitedList").appendChild(element);
  }
});
function f(e) {
  e.preventDefault();
  element = document.createElement("li");
  element.innerText = e.target.name.value;
  element.className = "teamCard";
  document.getElementById("invitedList").appendChild(element);
  localStorage["teams"] += e.target.name.value.replace(";", ":") + ";";
  document.getElementById("registrar").getElementsByTagName("input")[0].value =
    "";
}
document.getElementById("registrar").addEventListener("submit", f);
