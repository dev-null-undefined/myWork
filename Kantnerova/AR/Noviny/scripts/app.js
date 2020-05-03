let smallerWindow;
function openArchive(event) {
  if (smallerWindow && !smallerWindow.closed) {
    smallerWindow.close();
  }
  let url = window.location.href.split("/");
  url.pop();
  url = url.join("/") + "/archive/" + event.target.getElementsByTagName("select")[0].value;
  smallerWindow = window.open(url, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
  return false;
}
