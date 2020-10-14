let a=require("./a.json")
let fs=require("fs")
let b=JSON.parse(JSON.stringify(a));
b["Ahoj"]="reee";
console.log(a);
console.log(b);
fs.writeFile("./a.json", JSON.stringify(b, null, 4), err => {
    if (err) throw err;
});
console.log(a);
console.log(b);