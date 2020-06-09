window.onload = function () {
  var file = document.getElementById("thefile");
  var audio = document.getElementById("audio");

  file.onchange = function () {
    var files = this.files;
    audio.src = URL.createObjectURL(files[0]);
    document.title = files[0].name;
    audio.load();
    audio.play();
    var context = new AudioContext();
    var src = context.createMediaElementSource(audio);
    var analyser = context.createAnalyser();

    var canvas = document.getElementById("canvas");
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    var ctx = canvas.getContext("2d");

    src.connect(analyser);
    analyser.connect(context.destination);

    // analyser.fftSize = 256;
    analyser.fftSize = 512;

    var bufferLength = analyser.frequencyBinCount;
    console.log(bufferLength);

    var dataArray = new Uint8Array(bufferLength);

    var WIDTH = canvas.width;
    var HEIGHT = canvas.height;

    var barWidth = (WIDTH / bufferLength) * 2.5;
    var barHeight;
    var x = 0;

    let timeOutId = 0;

    timeOutId = setTimeout(hideControl, 5000);
    window.onmousemove = () => {
      showControl();
      clearTimeout(timeOutId);
      timeOutId = setTimeout(hideControl, 5000);
    };
    function hideControl() {
      document.getElementById("audio").style.bottom = "-65px";
      document.getElementById("thefile").style.top = "-25px";
    }
    function showControl() {
      document.getElementById("audio").style.bottom = "10px";
      document.getElementById("thefile").style.top = "10px";
    }

    function renderFrame() {
      requestAnimationFrame(renderFrame);

      x = 0;

      analyser.getByteFrequencyData(dataArray);

      ctx.fillStyle = "#000";
      ctx.fillRect(0, 0, WIDTH, HEIGHT);

      // for (var i = 0; i < bufferLength; i++) {
      //   barHeight = (dataArray[i] / 255) * HEIGHT;

      //   var r = dataArray[i] + 25 * (i / bufferLength);
      //   var g = 250 * (i / bufferLength);
      //   var b = 50;

      //   ctx.fillStyle = "rgb(" + r + "," + g + "," + b + ")";
      //   ctx.fillRect(x, HEIGHT - barHeight, barWidth, barHeight);

      //   x += barWidth + 1;
      // }
      // x = 0;
      let points = [];
      dataArray.forEach((element) => {
        points.push({ y: HEIGHT - (element / 255) * HEIGHT, x: x });
        x += barWidth + 1;
      });
      let pointsToDraw = chaikinsAlgo(points, 4);
      ctx.strokeStyle = "#FFF";
      lastPoint = pointsToDraw[0];
      for (let i = 1, lenght = pointsToDraw.length; i < lenght; i++) {
        ctx.beginPath();
        ctx.moveTo(lastPoint.x, lastPoint.y);
        lastPoint = pointsToDraw[i];
        ctx.lineWidth = ((lastPoint.y - HEIGHT) / HEIGHT) * -3 + 1;
        var r = (lastPoint.y - HEIGHT / HEIGHT) * 255;
        var g = (lastPoint.y / HEIGHT) * 255;
        var b = 135;
        ctx.strokeStyle = "rgb(" + r + "," + g + "," + b + ")";
        ctx.lineTo(lastPoint.x, lastPoint.y);
        ctx.stroke();
      }
    }

    function chaikinsAlgo(points, quality) {
      const size = 0.25;
      if (quality > 0) {
        let newPoints = [];
        let lastPoint = points[0];
        for (let i = 1, lenght = points.length; i < lenght; i++) {
          newPoints.push({ x: (1 - size) * lastPoint.x + size * points[i].x, y: (1 - size) * lastPoint.y + size * points[i].y });
          newPoints.push({ x: size * lastPoint.x + (1 - size) * points[i].x, y: size * lastPoint.y + (1 - size) * points[i].y });
          lastPoint = points[i];
        }
        return chaikinsAlgo(newPoints, quality - 1);
      } else {
        return points;
      }
    }
    audio.play();
    renderFrame();
  };
};
