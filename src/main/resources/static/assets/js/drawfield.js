class Drawfield{
    constructor(){}

   drawGround(){
    // Soccer Field 그리기 코드
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

    // Penalty boxes
    ctx.strokeRect(50, 250, 75, canvas.height - 500);
    ctx.strokeRect(canvas.width - 125, 250, 75, canvas.height - 500);
}


}

export { Drawfield }