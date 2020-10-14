let x = 0,
  y = 0;
for (c of process.argv[2])
  switch (c) {
    case "D":
      x++;
  }
console.log(x + "," + y);

let x = [...process.argv[2]].reduce(
  (a, e) => {
    a[e]++;
    return a;
  },
  { R, L, U, D }
);
console.log(x.R + x.L + "," + x.U + x.D);
