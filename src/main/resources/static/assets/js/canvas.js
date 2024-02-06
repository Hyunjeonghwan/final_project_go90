//window.addEventListener("load", initPlayers);
let players = [];  //회원객체 담을 배열

const voteIdValue = document.querySelector('.vote-value');
const voteId = voteIdValue.value;
const canvas = document.getElementById("soccerField");
const ctx = canvas.getContext("2d");
let selectedFormation = document.querySelector('.selected-formation');
const thumbnail = document.querySelectorAll('.thumbnail');
const circle2 = document.querySelectorAll('.circle-2');
let putBtn = document.querySelector('.put-btn');
let selectedQuarter = document.querySelector('.quarter');

/**======메모 관련 변수======*/
//기본 펜 스타일, 색상 설정
let draw_color = "black";
let draw_width = "2";
let is_drawing = false;

async function initPlayers() {
	try {
		const response = await fetch(`/myteam/management/load/${voteId}`);
		const data = await response.json();
		data.forEach(playerData => {
			const { memberImg, x, y, name, backNumber, position, goal, assist, defence, save } = playerData;

			// 이미지 객체 생성
			const memberImage = new Image();
			memberImage.src = memberImg;

			// Player 객체 생성
			const player = new Player(memberImage, x, y, name, backNumber, position, goal, assist, defence, save);

			// 이미지 로딩 완료 후 선수 추가
			memberImage.onload = () => {
				players.push(player);
				//  whichFormation();
			};
		});
	} catch (error) {
		console.error('데이터 로드 실패', error);
	}
}

/**회원 객체 */
class Player {
	constructor(memberImg, x, y, name, backNumber, position, goal, assist, defence, save) {
		this.memberImg = memberImg;
		this.x = x;
		this.y = y;
		this.name = name;
		this.backNumber = backNumber;
		this.position = position;
		this.goal = goal;
		this.assist = assist;
		this.defence = defence;
		this.save = save;
		this.width = 60;
		this.height = 60;
		this.isDragging = false;
		this.dragOffsetX = 0;
		this.dragOffsetY = 0;
	}
	drawImg(ctx) {
		ctx.font = 'normal bold 16px san-serif';
		ctx.fillStyle = 'white';
		ctx.drawImage(this.memberImg, this.x, this.y, this.width, this.height);
		ctx.fillText(this.backNumber + '.' + this.name, this.x - 7, this.y + 75);
		ctx.fillText(this.position, this.x - 10, this.y);
	}
	move(x, y) {
		this.x = x;
		this.y = y;
	}
}
/** ==========================축구필드 그리기====================================*/
function drawSoccerField() {
	ctx.fillStyle = "#53c653";
	ctx.fillRect(0, 0, canvas.width, canvas.height);

	ctx.strokeStyle = "#FFFFFF"; // Line color
	ctx.lineWidth = 2;

	// Outer boundary
	ctx.strokeRect(50, 50, canvas.width - 100, canvas.height - 100);

	// Penalty half
	ctx.beginPath();
	ctx.arc(125, 395, 30, 300, toRadian(90));
	ctx.stroke();
	ctx.beginPath();
	ctx.arc(1175, 395, 30, toRadian(90), toRadian(270));
	ctx.stroke();

	// Center circle
	ctx.beginPath();
	ctx.arc(canvas.width / 2, canvas.height / 2, 91.5, 0, toRadian(360));
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
	ctx.closePath();

	// Penalty boxes
	ctx.beginPath();
	ctx.strokeRect(50, 250, 75, canvas.height - 500);
	ctx.strokeRect(canvas.width - 125, 250, 75, canvas.height - 500);
	ctx.globalCompositeOperation = 'source';
}

selectedFormation.addEventListener('change', whichFormation);
selectedQuarter.addEventListener('change', whichQuarter);

/**select 선택된 값에 따른 전술 그리기 */
function whichFormation() {
	let formVal = selectedFormation.value;
	clearCanvas();
	drawPlayers(ctx);
	redoMemo();
	ctx.beginPath();
	ctx.font = 'normal bold 24px san-serif';
	ctx.fillStyle = 'white';
	if (selectedQuarter.value === '0') {
		ctx.fillText('💡쿼터를 선택하세요!', 565, 40);
		ctx.font = 'normal bold 80px san-serif';
		ctx.fillText('👉', 1200, 155);
	} else {
		ctx.fillText(`🔥 ${selectedQuarter.value}쿼터 🔥`, 585, 40);
	}
	ctx.stroke();
	ctx.closePath();
	if (formVal === '442') {
		fftwo();
	} else if (formVal === '433') {
		ffthree();
	} else if (formVal === '4231') {
		fttone();
	} else if (formVal === 'basic') {
	}
}

/**쿼터 선택시 변화하는 텍스트 */
function whichQuarter() {
	whichFormation();
	let infoText = document.querySelector('.info-text-quarter');
	switch (selectedQuarter.value) {
		case '1':
		case '2':
		case '3':
		case '4':
			infoText.textContent = `🏃 ${selectedQuarter.value}쿼터`;

			infoText.classList.remove('fade-in');
			infoText.addEventListener('animationend', function() {
				infoText.classList.remove('fade-in');
			});
			infoText.classList.add('fade-in');
			break;
		case '0':
			infoText.classList.remove('fade-in');
			infoText.textContent = "";
			break;
	}
}

/**로드 시 축구필드 그리기 */
window.onload = function() {
	drawSoccerField();
}

/**지우고 다시 그리기 */
function clearCanvas() {
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	drawSoccerField();
}
/**원 그릴때 radian을 각도로 */
function toRadian(angle) {
	return angle * Math.PI / 180;
}

/**마우스 좌표가 선수객체인지 식별하기 */
function isMouseOverPlayer(x, y, player) {
	return (
		x >= player.x &&
		x <= player.x + player.width &&
		y >= player.y &&
		y <= player.y + player.height
	);
}


/**=================메모패드 기능================= */

//메모장 버튼 클릭과 닫기 관련 
let memo = document.querySelector(".memo span");
memo.addEventListener('click', e => {

	const toolsContainer = document.querySelector(".tools");
	if (toolsContainer.classList.contains("show")) {
		toolsContainer.style.animation = "slide-down 0.3s forwards";
		memo.textContent = "메모";
		canvas.style.cursor = 'auto';
		setTimeout(() => {
			toolsContainer.classList.remove("show");
		}, 300); // 0.3초 뒤에 .show 클래스 제거
	} else {
		// .show 클래스가 적용되지 않은 경우 클릭하면 나타남
		canvas.style.cursor = 'url(/assets/img/cursor.png),auto';
		toolsContainer.classList.add("show");
		memo.textContent = "닫기";
		toolsContainer.style.animation = "slide-up 0.3s forwards";
	}
	e.stopPropagation(); // 이벤트 전파 중단
});

/**색상 변경 함수 */
function change_color(e) {
	draw_color = e.style.background;
}
// 현재 그리는 그림 정보
let currentDrawing = {
	color: null,
	width: null,
	points: []
};
// 메모 담을 배열
let memoArray = [];

/**=====그리기 함수===== */
function start(e) {
	let x = e.clientX - canvas.getBoundingClientRect().left;
	let y = e.clientY - canvas.getBoundingClientRect().top;
	currentDrawing = {
		color: draw_color,
		width: draw_width,
		points: []
	};
	if (is_drawing) {
		e.preventDefault();
		for (let i = players.length - 1; i >= 0; i--) {
			const player = players[i];
			if (e.type != 'mouseout' && isMouseOverPlayer(x, y, player)) {
				is_drawing = false;
				return;
			}

		}
	}
}

/**그리기*/
function draw(e) {
	if (is_drawing) {
		const x = e.clientX - canvas.getBoundingClientRect().left;
		const y = e.clientY - canvas.getBoundingClientRect().top;

		ctx.beginPath();
		ctx.strokeStyle = draw_color;
		ctx.lineWidth = draw_width;
		ctx.lineCap = "round";
		ctx.lineJoin = "round";
		currentDrawing.points.push({ x, y });
		ctx.moveTo(x, y);
		ctx.lineTo(x, y);
		ctx.stroke();

	} else if (!is_drawing) {
		return;
	}
}

/**그리기 종료*/
function stop() {
	if (is_drawing) {
		ctx.closePath();
		if (currentDrawing.points.length > 1) {
			// 그림 정보를 memoArray 배열에 저장
			memoArray.push({ ...currentDrawing });
			currentDrawing = {
				color: draw_color,
				width: draw_width,
				points: null
			};
			is_drawing = false;
		}
		whichFormation();
	}
}

/**지우기*/
function clearAllMemo() {
	memoArray = [];
	drawPlayers(ctx);
	whichFormation();
}
/**하나 되돌리기*/
function undoMemo() {
	if (memoArray.length > 0) {
		memoArray.pop();
		whichFormation();
	}
}
/**다시그리기*/
function redoMemo() {
	if (memoArray.length > 0) {
		const redoData = memoArray.pop();
		memoArray.push(redoData);
		redrawMemo();
	}
}
/**다시 그리기 관련 함수 */
function redrawMemo() {
	memoArray.forEach(data => {
		ctx.strokeStyle = data.color;
		ctx.lineWidth = data.width;
		ctx.lineCap = "round";
		ctx.lineJoin = "round";
		ctx.beginPath();
		data.points.forEach(point => {
			ctx.lineTo(point.x, point.y);
		});
		ctx.stroke();
		ctx.closePath();
	});
}




/**선수 그리기 */
function drawPlayers() {
	players.forEach(player => {
		player.drawImg(ctx);
	});
}
/**선수 추가 */
function addPlayer(x, y, memberImg, name, backNumber, position) {
	const player = new Player(memberImg, x, y, name, backNumber, position);
	players.push(player);
	drawPlayers();
}

/**드래그*/
const cards = document.querySelectorAll('.container2[draggable="true"]');
cards.forEach(card => {
	//드래그 시 사진 변경
	const imgSrc = card.querySelector('.thumbnail').src;
	const cardImage = new Image();
	cardImage.src = imgSrc;
	card.addEventListener('dragstart', e => {
		e.dataTransfer.setData('text/plain', card.id);
		
		e.dataTransfer.setDragImage(cardImage, 0, 0);

		card.addEventListener('dragend', () => {
			card.draggable = false;
			thumbnail.forEach(thumb => { thumb.draggable = false; });
			circle2.forEach(circle => { circle.draggable = false; });
		});
	});
});

/**드래그 중 드롭 방지 */
canvas.addEventListener('dragover', (e) => {
	e.preventDefault();
});

/**드롭이벤트 */
canvas.addEventListener('drop', e => {
	whichFormation();
	e.preventDefault();
	const x = e.clientX - canvas.getBoundingClientRect().left;
	const y = e.clientY - canvas.getBoundingClientRect().top;
	const cardId = e.dataTransfer.getData('text/plain');
	const player = document.getElementById(cardId);
	if (player) {
		const playerImgPath = player.querySelector('.thumbnail').src;
		const playerName = player.querySelector('.name').textContent;
		const playerNumber = player.querySelector('.backNumber').textContent;
		const playerPosition = player.querySelector('.position').textContent;

		let playerImg = new Image();
		playerImg.src = playerImgPath;
		playerImg.onload = () => {
			addPlayer(x, y, playerImg, playerName, playerNumber, playerPosition);
		};
	}
});

// 캔버스 내에서 마우스 다운 이벤트 처리
let isDragging = false;
let dragTarget = null;
let offsetX = 0;
let offsetY = 0;
let selectedPlayer = null;

canvas.addEventListener("mousedown", e => {
	const x = e.clientX - canvas.getBoundingClientRect().left;
	const y = e.clientY - canvas.getBoundingClientRect().top;
	is_drawing = true;
	start(e);
	for (let i = players.length - 1; i >= 0; i--) {
		const player = players[i];
		if (isMouseOverPlayer(x, y, player)) {
			dragTarget = player;
			isDragging = true;
			offsetX = x - player.x;
			offsetY = y - player.y;
			is_drawing = false;
			break;
		}
	}
});

// 마우스 이동 이벤트 처리
canvas.addEventListener("mousemove", e => {
	const x = e.clientX - canvas.getBoundingClientRect().left;
	const y = e.clientY - canvas.getBoundingClientRect().top;
	if (is_drawing) {
		draw(e);
	}
	if (isDragging && dragTarget) {
		dragTarget.move(x - offsetX, y - offsetY);
		drawPlayers(ctx);
		whichFormation();
	}
});
canvas.addEventListener("mouseout", stop());

// 마우스 업 이벤트 처리
canvas.addEventListener("mouseup", () => {
	stop();
	isDragging = false;
	dragTarget = null;
});

/**선수 삭제 우클릭 */
canvas.addEventListener("contextmenu", e => {
	e.preventDefault();
	const x = e.clientX - canvas.getBoundingClientRect().left;
	const y = e.clientY - canvas.getBoundingClientRect().top;

	for (let i = players.length - 1; i >= 0; i--) {
		const player = players[i];
		if (
			x >= player.x &&
			x <= player.x + player.width &&
			y >= player.y &&
			y <= player.y + player.height
		) {
			// 클릭한 선수를 삭제
			players.splice(i, 1);
			whichFormation();
			cards.forEach(card => { card.draggable = true; });
			break;
		}
	}
});


/**4-4-2 포메이션 배열 */
const fftwoCircles = [
	{ x: 90, y: 390 },
	{ x: 340, y: 150 },
	{ x: 310, y: 310 },
	{ x: 310, y: 480 },
	{ x: 340, y: 640 },
	{ x: 750, y: 140 },
	{ x: 750, y: 330 },
	{ x: 750, y: 480 },
	{ x: 750, y: 660 },
	{ x: 1050, y: 330 },
	{ x: 1100, y: 480 },
];
/**4-3-3 포메이션 배열 */
const ffthreeCircles = [
	{ x: 90, y: 390 },

	{ x: 340, y: 150 },
	{ x: 310, y: 310 },
	{ x: 310, y: 480 },
	{ x: 340, y: 640 },

	{ x: 750, y: 200 },
	{ x: 750, y: 395 },
	{ x: 750, y: 570 },

	{ x: 1100, y: 300 },
	{ x: 1000, y: 390 },
	{ x: 1100, y: 480 },
];
/**4-2-3-1 포메이션 배열 */
const fttoneCircles = [
	{ x: 90, y: 390 },
	{ x: 340, y: 150 },
	{ x: 310, y: 310 },
	{ x: 310, y: 480 },
	{ x: 340, y: 640 },
	{ x: 560, y: 290 },
	{ x: 600, y: 500 },
	{ x: 860, y: 200 },
	{ x: 860, y: 395 },
	{ x: 860, y: 570 },
	{ x: 1130, y: 390 },
];


let angle = 0;
/**점선 원 그리기 함수*/
function dashedArc(x, y, angle) {
	ctx.beginPath();
	ctx.setLineDash([5, 5]);
	ctx.strokeStyle = 'white';
	ctx.lineWidth = 2;
	ctx.arc(x, y, 30, toRadian(angle + 0), toRadian(angle + 360), false);
	ctx.stroke();
	ctx.setLineDash([]);
	ctx.closePath();
}
/**4-4-2 포메이션  */
function fftwo() {
	fftwoCircles.forEach(fftwoCircle => {
		dashedArc(fftwoCircle.x, fftwoCircle.y, angle);
	});
}
/**4-3-3 포메이션 */
function ffthree() {
	ffthreeCircles.forEach(ffthreeCircle => {
		dashedArc(ffthreeCircle.x, ffthreeCircle.y, angle);
	});
}

/**4-2-3-1 포메이션 */
function fttone() {
	fttoneCircles.forEach(fftoneCircle => {
		dashedArc(fftoneCircle.x, fftoneCircle.y, angle);
	});
}

/**  포메이션 사진으로 저장하기*/
let formationName = document.querySelector('.formation-text');
const saveBtn = document.querySelector('.saveBtn').addEventListener('click', saveImg);
function saveImg() {
	canvas.toBlob((blob) => {
		const a = document.createElement('a');
		document.body.append(a);
		a.download = `${date}/${selectedQuarter.value}쿼터.png`;
		a.href = URL.createObjectURL(blob);
		a.click();
		a.remove();
	});
}


/**날짜계산*/
let today = new Date();
let year = today.getFullYear();
let month = ( '0' + (today.getMonth() +1)).slice(-2);
let day = ('0' + today.getDate()).slice(-2);

const date = year + '-' + month + '-' +day;

