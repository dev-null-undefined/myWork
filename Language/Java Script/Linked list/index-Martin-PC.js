class Node {
  constructor(val) {
    this.val = val;
    this.head = null;
  }
  push(val) {
    if (this.tail != undefined) {
      this.tail.push(val);
    } else {
      this.tail = new Node(val);
      this.tail.head = this;
    }
  }
  peek(index) {
    if (index > 0) {
      if (this.tail == undefined) {
        return "Out of Bounds";
      }
      return this.tail.peek(index - 1);
    } else {
      return this.val;
    }
  }
  pop(index) {
    if (index > 0) {
      if (this.tail == undefined) {
        return "Out of Bounds";
      }
      return this.tail.pop(index - 1);
    } else {
      if (this.tail != undefined) {
        this.val = this.tail.val;
        this.tail = this.tail.tail;
        return "Done";
      }
      if(this.head!=undefined){
          this.head.tail=undefined;
      }
      return "Done";
    }

  }
  toString() {
    if (this.tail != undefined) {
      return this.tail.toString() + "," + this.val;
    } else {
      return this.val;
    }
  }
}

var node = new Node("Ahoj");
node.push("Ahoj1");
node.push("Ahoj2");
node.push("Ahoj3");
node.push("Ahoj4");
