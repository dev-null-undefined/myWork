let ctx;
let c;
let ydone=[];
function onLoad() {
  c = document.getElementById("myCanvas");
  ctx = c.getContext("2d");
  //ctx.moveTo(0, 0);
  for (let i = 0; i < 180 * 5; i++) {
    if(!ydone.includes(parseInt(Math.cos(degree(i / 5.0)) * 100 + c.height / 2))){

      ydone.push(parseInt(Math.cos(degree(i / 5.0)) * 100 + c.height / 2));
      for(let x=Math.sin(degree(i / 5.0)) * 100 + c.width / 2;x>(-Math.sin(degree(i / 5.0)) * 100 + c.width / 2);x--){
        drawPixel(
          x,
          parseInt(Math.cos(degree(i / 5.0)) * 100 + c.height / 2),
          ctx
        );  
      }
    }
  }
  ctx.stroke();
}
function drawPixel(x, y, ctx, size = 1) {
  ctx.fillRect(x, y, size, size);
}
function degree(degrees) {
  return (degrees * Math.PI) / 180;
}
window.onload = onLoad;

document.addEventListener("click", function myOnClick(evt) {
  drawPixel(evt.clientX, evt.clientY, ctx, 2);
});
