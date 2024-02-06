class Player {
    constructor(image, x, y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
    }
    // 이미지 그리기 함수
    drawImg(ctx) {
        ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
    }
}

// radian 각도로
function toRadian(angle){
    return angle * Math.PI / 180;
}


const table = document.querySelector('table');
let draggedRow = null;
const canvas = document.getElementById("soccerField");
const ctx = canvas.getContext("2d");


// const canvasB = document.querySelector("#bottomCanvas");
// const ctx2 = canvasB.getContext("2d");

//  Soccer field
ctx.fillStyle = "#53c653"; 
ctx.fillRect(0, 0, canvas.width, canvas.height);

ctx.strokeStyle = "#FFFFFF"; // Line color
ctx.lineWidth = 2;

// Outer boundary
ctx.strokeRect(50, 50, canvas.width - 100, canvas.height - 100);

//penalty half
ctx.beginPath();
ctx.arc(125,395,30,300,toRadian(90));
ctx.stroke();
ctx.beginPath();
ctx.arc(1175,395,30,toRadian(90),toRadian(270));
ctx.stroke();

// Center circle
ctx.beginPath();
ctx.arc(canvas.width / 2, canvas.height / 2, 91.5, 0,toRadian(360));
ctx.stroke();

// Center dot
ctx.beginPath();
ctx.arc(canvas.width / 2, canvas.height / 2, 2, 0, Math.PI * 2);
ctx.fill();

// Corners
ctx.beginPath();
ctx.arc(50, 50, 10, 0, Math.PI * 0.5);
ctx.stroke();

ctx.beginPath();
ctx.arc(canvas.width - 50, 50, 10, Math.PI * 0.5, Math.PI);
ctx.stroke();

ctx.beginPath();
ctx.arc(50, canvas.height - 50, 10, 0, -Math.PI * 0.5, true);
ctx.stroke();

ctx.beginPath();
ctx.arc(canvas.width - 50, canvas.height - 50, 10, -Math.PI * 0.5, -Math.PI, true);
ctx.stroke();

// Goals
ctx.beginPath();
ctx.moveTo(50, canvas.height / 2 - 36);
ctx.lineTo(35, canvas.height / 2 - 36);
ctx.lineTo(35, canvas.height / 2 + 36);
ctx.lineTo(50, canvas.height / 2 + 36);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(canvas.width - 50, canvas.height / 2 - 36);
ctx.lineTo(canvas.width - 35, canvas.height / 2 - 36);
ctx.lineTo(canvas.width - 35, canvas.height / 2 + 36);
ctx.lineTo(canvas.width - 50, canvas.height / 2 + 36);
ctx.stroke();

// Center line
ctx.beginPath();
ctx.moveTo(canvas.width / 2, 50);
ctx.lineTo(canvas.width / 2, canvas.height - 50);
ctx.stroke();

// Penalty boxes
ctx.strokeRect(50, 250, 75, canvas.height - 500);
ctx.strokeRect(canvas.width - 125, 250, 75, canvas.height - 500);

//images

let img = new Image();
img.src = '/assets/img/user.png';


//테이블 드래그
const rows = document.querySelectorAll('tr[draggable="true"]');
rows.forEach(row => {
    row.addEventListener('dragstart', e => {
        e.dataTransfer.setData('text/plain', ''); // 드래그 데이터 설정
        e.dataTransfer.setDragImage(img, 0, 0); // 드래그 중인 요소 설정
    });
});
canvas.addEventListener('dragover', (e) => {
    e.preventDefault();
});

canvas.addEventListener('drop', (e) => {
    e.preventDefault();
    // 드롭한 위치에 이미지를 그리기
    const x = e.clientX - canvas.getBoundingClientRect().left;
    const y = e.clientY - canvas.getBoundingClientRect().top;
console.log(x,y);

const player = new Player(img,x,y);
    // 드래그된 요소 이미지 url 설정
    // const imageSrc = img;
    //그리기
    player.drawImg(ctx);
    
});





















