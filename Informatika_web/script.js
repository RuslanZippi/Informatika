var model = loadModel()
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext('2d');
var dragging = false;
var pos = { x: 0, y: 0 };
// определим слушателей событий для ПК и телефонов
// Движение мышкой
canvas.addEventListener('mousedown',  engage);
canvas.addEventListener('mousedown',  setPosition);
canvas.addEventListener('mousemove',  draw);
canvas.addEventListener('mouseup', disengage);

// Касание
canvas.addEventListener('touchstart', engage);
canvas.addEventListener('touchmove', setPosition);
canvas.addEventListener('touchmove', draw);
canvas.addEventListener('touchend', disengage);
// определим, является ли устройство сенсорным
function isTouchDevice() {
return (
('ontouchstart' in window) ||
(navigator.maxTouchPoints > 0) ||
(navigator.msMaxTouchPoints > 0)
);
}
// определим базовые функции для обнаружения нажатия и отпускания
function engage() {
dragging = true;
};

function disengage() {
dragging = false;
};
// получим новую позиции при событиях связанных с мышью или прикосновением к экрану
function setPosition(e) {

if (isTouchDevice()) {
var touch = e.touches[0];
pos.x = touch.clientX - ctx.canvas.offsetLeft;
pos.y = touch.clientY - ctx.canvas.offsetTop;
} else {pos.x = e.clientX - ctx.canvas.offsetLeft;
 pos.y = e.clientY - ctx.canvas.offsetTop;
 }
}
// рисуем линию на холсте при нажатой кнопке мыши
function draw(e) {
e.preventDefault();
e.stopPropagation();

 if (dragging) {

 ctx.beginPath();ctx.lineWidth = 40;
 ctx.lineCap = 'round';
 ctx.strokeStyle = 'red';

// получаем текущую позицию, переместимся в новую, создавая линию от текущей до новой
 ctx.moveTo(pos.x, pos.y);
setPosition(e);
 ctx.lineTo(pos.x, pos.y);

 // Рисуем
ctx.stroke();
 }
}

// Очищаем холст
function erase() {
ctx.clearRect(0, 0, canvas.width, canvas.height);
}
async function loadModel(){ 
// загружаем модель
model = await tf.loadLayersModel('/tensorflow/model.json');
 model.predict(tf.zeros([1, 28, 28, 1]))
 // возвращаем модель
 return model
}
function getData(){
 return ctx.getImageData(0, 0, canvas.width, canvas.height);
}
// определяем функцию вывода модели
async function predictModel() {
// получаем данные изображения
imageData = getData();

 // преобразуем объект данных canvas в тензор
image = tf.browser.fromPixels(imageData)

// предварительная обработка изображения
 image = tf.image.resizeBilinear(image, [28,28]).sum(2).expandDims(0).expandDims(-1)

 y = model.predict(image);

document.getElementById('result').innerHTML = "Prediction: " + y.argMax(1).dataSync();
}