let wins=0;
let loses=0;
let doors=[];

for(let gameNum=0;gameNum<999;gameNum++){
function initGame() {
    doors=[0,0,0];
    doors[Math.floor(Math.random()*3)]=1;
}
initGame();
// for(let i=0;i<3;i++){
//      console.log("door["+i+"]:"+doors[i]);
// }
let chosen=Math.floor(Math.random()*3);
let open;
// console.log("chosen:"+chosen);
for(let i=0;i<3;i++){
    if(i!=chosen&&doors[i]!=1){
        open=i;
    }
}
//CHANGE DOORS comment out if u dont want to change doors
console.log("open:"+open);
for(let i=0;i<3;i++){
    if(open!=i&&chosen!=i){
        chosen=i;
        break;
    }
}
console.log("chosen:"+chosen);
//END of changing doors
if(doors[chosen]==1){
    wins++;
}else{
    loses++;
}   
}

console.log("WINs:"+wins);
console.log("LOSES:"+loses);
console.log("WINS/LOSES:"+wins/loses);