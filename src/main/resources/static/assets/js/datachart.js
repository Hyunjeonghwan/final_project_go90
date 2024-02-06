const matchHeader = document.querySelectorAll('.match-header');




const matchId = document.querySelectorAll('.match-header input[type=hidden]');
const matchIdVal = matchId.value;





matchHeader.forEach(headers => {
  headers.addEventListener('click',() => {
	  matchId.forEach(ids => {
		  
    console.log(ids.value);
	  });
  });
  
});






   // 초기 데이터
  let initialMatchDate = "";
  let initialScores = [];
   
   // Chart.js를 사용하여 초기 그래프 생성
   let ctx = document.getElementById("scoreChart").getContext("2d");
   let myChart = new Chart(ctx, {
     type: "bar",
     data: {
       labels: ["득점", "도움", "태클", "세이브"],
       datasets: [
         {
           label: initialMatchDate,
           data: initialScores,
           backgroundColor: "rgba(75, 192, 192, 0.5)",
           borderColor: "rgba(75, 192, 192, 1)",
           borderWidth: 2,
         },
       ],
     },
     options: {
       responsive: false,
       scales: {
         y: {
           beginAtZero: true,
         },
       },
     },
   });
   
   // HTML에서 테이블 요소들을 선택
   let tableRows = document.querySelectorAll(".stats-table tbody tr");
   
   
   // 클릭 이벤트 핸들러 등록
   tableRows.forEach(function (row, index) {
     row.addEventListener("click", function () {
       // 클릭한 행에서 데이터 가져오기
       var cells = row.getElementsByTagName("td");
       var matchDate = cells[0].textContent;
       var scores = [
         parseInt(cells[1].textContent),
         parseInt(cells[2].textContent),
         parseInt(cells[3].textContent),
         parseInt(cells[4].textContent),
       ];
   
       // 그래프 업데이트
       updateChart(matchDate, scores);
     });
   });
   
   function updateChart(matchDate, scores) {
     // 기존 그래프 제거
     myChart.destroy();
   
     // 새로운 그래프 생성 및 데이터 설정
     myChart = new Chart(ctx, {
       type: "bar",
       data: {
         labels: ["득점", "도움", "태클", "세이브"],
         datasets: [
           {
             label: matchDate,
             data: scores,
             backgroundColor: "rgba(75, 192, 192, 0.5)",
             borderColor: "rgba(75, 192, 192, 1)",
             borderWidth: 2,
           },
         ],
       },
       options: {
         responsive: true, 
       maintainAspectRatio: false,
         scales: {
           y: {
             beginAtZero: true,
           },
         },
       },
     });
   }
   
   // 페이지 로드 시 초기 그래프를 보여주도록 호출
   updateChart(initialMatchDate, initialScores);
   
   
   
   
   
   
   
   
   
   // 데이터 준비
   let matchInfo = [
     {
       matchDate: "2023/09/08",
       teams: "이젠 FC VS 아카데미 FC",
       totalScore: 14, // 해당 경기의 총 득점
       quarters: [
         { quarter: "1쿼터", score: 2 },
         { quarter: "2쿼터", score: 3 },
         { quarter: "3쿼터", score: 4 },
         { quarter: "4쿼터", score: 5 },
       ],
     },
     // 다른 경기에 대한 정보
   ];
   
   // 각 경기의 총 득점과 각 쿼터별 점수 데이터 추출
   let matchLabels = matchInfo.map((match) => match.matchDate);
   let totalScores = matchInfo.map((match) => match.totalScore);
   let quarterScores = matchInfo.map((match) =>
     match.quarters.map((quarter) => quarter.score)
   );
   
   // 두번째 그래프 생성
   let ctxMatchBar = document.getElementById("matchScoreChart").getContext("2d");
   let matchChart = new Chart(ctxMatchBar, {
     type: "bar",
     data: {
       labels: matchLabels,
       datasets: [
         {
           label: "팀 득점",
           data: totalScores,
           backgroundColor: "rgba(75, 192, 192, 0.5)",
           borderColor: "rgba(75, 192, 192, 1)",
           borderWidth: 2,
         },
         {
           label: "1쿼터",
           data: quarterScores.map((scores) => scores[0]),
           backgroundColor: "rgba(255, 99, 132, 0.5)",
           borderColor: "rgba(255, 99, 132, 1)",
           borderWidth: 2,
         },
         {
           label: "2쿼터",
           data: quarterScores.map((scores) => scores[1]),
           backgroundColor: "rgba(54, 162, 235, 0.5)",
           borderColor: "rgba(54, 162, 235, 1)",
           borderWidth: 2,
         },
         {
           label: "3쿼터",
           data: quarterScores.map((scores) => scores[2]),
           backgroundColor: "rgba(255, 206, 86, 0.5)",
           borderColor: "rgba(255, 206, 86, 1)",
           borderWidth: 2,
         },
         {
           label: "4쿼터",
           data: quarterScores.map((scores) => scores[3]),
           backgroundColor: "rgba(153, 102, 255, 0.5)",
           borderColor: "rgba(153, 102, 255, 1)",
           borderWidth: 2,
         },
       ],
     },
     options: {
       responsive: true,
       maintainAspectRatio: false,
       scales: {
         y: {
           beginAtZero: true,
         },
       },
     },
   });
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   // 총 경기수와 출전 경기수 데이터 
   let totalGames = 20; //20경기라고 가정
   let participatedGames = 10; //10경기라고 가정
   
   // 3번째:파이 차트
   let ctxPie = document.getElementById("pieChart").getContext("2d");
   let myPieChart = new Chart(ctxPie, {
     type: "pie", 
     data: {
       labels: ["총 경기수", "출전 경기수"], // 각 부분의 라벨
       datasets: [
         {
           data: [totalGames, participatedGames], // 데이터 값 설정
           backgroundColor: ["rgba(75, 192, 192, 0.5)", "rgba(255, 99, 132, 0.5)"], // 파이 차트 부분의 색상 설정
         },
       ],
     },
     options: {
       responsive: true,
       maintainAspectRatio: false,
       tooltips: {
         callbacks: {
           label: function (tooltipItem, data) {
             return data.labels[tooltipItem.index] + ": " + data.datasets[0].data[tooltipItem.index];
           },
         },
       },
     },
   });
   