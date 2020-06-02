function stopOscilator() {
  gainOscillator.disconnect(oscillatorContext.destination);
}
function resumeOscilator() {
  gainOscillator.connect(oscillatorContext.destination);
}
var oscillatorContext;
let oscillator;
var gainOscillator;
function startOscilator() {
  oscillatorContext = new AudioContext();
  oscillatorContext.resume();
  oscillator = oscillatorContext.createOscillator();
  oscillator.type = "sine";
  gainOscillator = oscillatorContext.createGain();
  gainOscillator.gain.value = 0.15;
  oscillator.connect(gainOscillator);
  gainOscillator.connect(oscillatorContext.destination);
  oscillator.start();
  stopOscilator();
}
function changeFrequency(value) {
  oscillator.frequency.value = map_range(value, 0, 1, minF, maxF);
}
let maxF = 700;
let minF = 100;
