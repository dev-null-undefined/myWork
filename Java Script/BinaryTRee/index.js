class Node {
  constructor(data, leftNode = null, rightNode = null) {
    this.data = data;
    this.left = leftNode;
    this.right = rightNode;
  }
}

class BinaryTree {
  constructor() {
    this.root = null;
    this.count = 0;
  }

  insert(data, current = this.root) {
    if (!this.root) {
      this.root = new Node(data);
      return;
    }
    if (current.data > data) {
      if (current.left === null) {
        current.left = new Node(data);
        this.count++;
      } else {
        this.insert(data, current.left);
      }
    }
    if (current.data < data) {
      if (current.right === null) {
        current.right = new Node(data);
        this.count++;
      } else {
        this.insert(data, current.right);
      }
    }
  }
  locate(value, current = this.root) {
    //console.log("A");
    if (!this.root) {
      return;
    }
    if (current.data == value) {
      return current;
    }
    if (current.data > value) {
      if (current.left != null) {
        return this.locate(value, current.left);
      }
    }
    if (current.data < value) {
      if (current.right != null) {
        return this.locate(value, current.right);
      }
    }
  }
  locateParent(value, current = this.root) {
    //console.log("A");
    if (!this.root) {
      return;
    }
    if (current.data > value) {
      if (current.left != null) {
        if (current.left.data == value) {
          return current;
        }
        return this.locateParent(value, current.left);
      }
    }
    if (current.data < value) {
      if (current.right != null) {
        if (current.right.data == value) {
          return current;
        }
        return this.locateParent(value, current.right);
      }
    }
  }
  delete(data) {
    let deletingNodeParrent = this.locateParent(data);
    let deletingNode = this.locate(data);
    if (deletingNodeParrent == null) {
      return "Error 0x0001";
    }
    if (data == this.root.data) {
      this.root = null;
    }
    let isLeft = deletingNodeParrent.left.data == data;
    if (isLeft) {
      if (deletingNode.right == null) {
        deletingNodeParrent.left = deletingNode.left;
        return "Done left A";
      }
      if (deletingNode.left == null) {
        deletingNodeParrent.left = deletingNode.right;
        return "Done left B";
      }
      deletingNode = deletingNode.right;
      while (deletingNode.left != null) {
        deletingNode = deletingNode.left;
      }
      deletingNode.left = deletingNodeParrent.left.left;
      deletingNodeParrent.left = deletingNode;
      return "Done left C";
    } else {
      if (deletingNode.right == null) {
        deletingNodeParrent.right = deletingNode.left;
        return "Done right A";
      }
      if (deletingNode.left == null) {
        deletingNodeParrent.right = deletingNode.right;
        return "Done right B";
      }
      deletingNode = deletingNode.right;
      while (deletingNode.left != null) {
        deletingNode = deletingNode.left;
      }
      deletingNode.left = deletingNodeParrent.right.left;
      deletingNodeParrent.right = deletingNode;
      return "Done right C";
    }
  }
}

var alfa = new BinaryTree();
alfa.insert(7);
alfa.insert(4);
alfa.insert(9);
alfa.insert(8);
alfa.insert(10);
alfa.insert(2);
alfa.insert(5);
alfa.insert(1);
alfa.insert(3);
alfa.insert(6);
