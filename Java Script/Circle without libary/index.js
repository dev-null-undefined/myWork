let ctx;
let c;
function onLoad() {
  c = document.getElementById("myCanvas");
  ctx = c.getContext("2d");
  //ctx.moveTo(0, 0);
  for (let i = 0; i < 360 * 2; i++) {
    drawPixel(
      Math.sin(degree(i / 2.0)) * 50 + c.width / 2,
      Math.cos(degree(i / 2.0)) * 50 + c.height / 2,
      ctx
    );
  }
  ctx.beginPath();
  ctx.arc(c.width / 2, c.height / 2, 55, 0, 2 * Math.PI);
  for (let i = 0; i < 360 * 2; i++) {
    drawPixel(
      (i / c.width) * 360,
      Math.sin(degree(i / 2.0)) * 50 + c.height / 2,
      ctx
    );
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
