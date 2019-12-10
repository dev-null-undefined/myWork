function quickSort(args) {
  if (args.length <= 1) return args;
  var pivot = args[args.length - 1];
  let sorted = [pivot];
  var left = [];
  var right = [];
  for (let i = 0; i < args.length - 1; i++) {
    let element = args[i];
    if (element != pivot)
      element < pivot ? left.push(element) : right.push(element);
  }
  //console.log(left);
  sorted = quickSort(left).concat(sorted);

  //console.log(right);
  return sorted.concat(quickSort(right));
}
console.log(quickSort([1, 9, 7, 6, 5, 12, 3, 4]));
let arr = [];
for (let i = 0; i < 100; i++) {
  arr[i] = Math.floor(Math.random() * 100);
}
console.log(quickSort(arr));