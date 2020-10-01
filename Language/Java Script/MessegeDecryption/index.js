let text = document.getElementById("text").innerText;
function decrypt(t) {
  let l=t.length/2,x="",y="",i=0;while(i<l){x+=t[(l+i+1)]+t[(l+i)];y+=t[(i+1)]+t[(i)];i+=2;}return x+y
}
console.log(decrypt(text));
// 12345ABCDE
// ABCDE12345
// BADC1E2345
