/* eslint-disable space-before-function-paren */
class PathFind {
  constructor(board) {
    this.board = board
    this.interval = null
  }

  update() {}
  draw(cnt) {}
  updateAndDraw(cnt, that) {}
  intervalStart(cnt, speed) {
    if (this.interval === null) {
      this.interval = setInterval(this.updateAndDraw, speed, cnt)
    }
  }

  intervalStop() {
    if (this.interval !== null) {
      clearInterval(this.interval)
      this.interval = null
    }
  }
}
