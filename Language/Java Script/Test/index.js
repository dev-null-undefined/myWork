let things = [
  { x: 12, y: 22 },
  { x: 14, y: 22 },
  { x: 22, y: 22 },
  { x: 14, y: 22 },
  { x: 24, y: 43 },
  { x: 15, y: 12 },
];
let duplicite = [];
let set = new Set();
things.forEach((thing) => {
  const thingString = JSON.stringify(thing);
  if (set.has(thingString)) {
    duplicite.push(thing);
  } else {
    set.add(thingString);
  }
});
