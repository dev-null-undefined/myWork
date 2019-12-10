var newWindow=null;
var url = new URL(window.location.href);
var pageNumber = url.searchParams.get("page");
var input = url.searchParams.get("filterPoint");
function onLoad(){
  if((input==null||input=="")&&(pageNumber==null||pageNumber=="1"||pageNumber=="")){
    if (confirm("Chcete zobrazit posledn\u00ED zpr\u00E1vy jednotek?")) 
    {
      if(newWindow==null){
        newWindow=window.open("lastMessage.php", "_blank");
      }else if(newWindow.closed){
        newWindow=window.open("lastMessage.php", "_blank");
      }
    } else {
      console.log("Cancel");
    }
  }else{
    console.log("Input is full 123")
  }
}
if(pageNumber==null||pageNumber==""){
  pageNumber = 1;
}else{
  pageNumber = parseInt(pageNumber,10);
}
function getUrlWithoutParams(){
  return window.location.href.split('?')[0];
}



function nextPage(){
  window.location.href=getUrlWithoutParams()+"?filterPoint="+(input==null?"":input)+"&page="+(pageNumber+1);
}

function backPage(){

  window.location.href=getUrlWithoutParams()+"?filterPoint="+(input==null?"":input)+"&page="+(pageNumber!=1?pageNumber-1:pageNumber);
}
window.onload=onLoad;
