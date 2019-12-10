let canvas;
function toggleDropDown() {
    document.getElementById("myDropdown").classList.toggle("show");
}
let graphView=false;
function togleGraphView(){
    if(graphView){
        document.getElementsByClassName("datagrid")[0].style.display="none";
    }else{
        document.getElementsByClassName("datagrid")[0].style.display="inherit";
        document.getElementsByTagName
        if(canvas==null){
            initGraph();
        }
    }
    graphView=!graphView;
}
let values=new Array();
function initGraph(){
  let rowsNames=document.getElementsByTagName("tr")[0].getElementsByTagName("th");
  let id=0;
    rowsNames.array.forEach(element => {
        values[id++]=new Array(element);
    });
    let rows=document.getElementsByTagName("tr");
    for(id=1;id<rows.length;id++){
        let currentRow=document.getElementsByTagName("tr")[id].getElementsByTagName("th");
        for(let x=0;x<currentRow.length;x++){
            values[x].push(currentRow[x]);
        }
    }
}
function setup() {
    canvas = createCanvas(1000, 500);
}

function draw() {
  ellipse(50, 50, 80, 80);
}