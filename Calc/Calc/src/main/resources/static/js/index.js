function addOutput(num) {
  var display = document.getElementById("display");
  display.value = display.value + num;
}

function calculate() {
  var display = document.getElementById("display");
  var result = eval(display.value);
  var displayResult = document.getElementById("result");
  displayResult.value = result;

////////////////추가
    var httpRequest;
  /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.calcString = display.value;
    reqJson.calcResult = result;

    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    httpRequest = new XMLHttpRequest();

/* httpRequest의 readyState가 변화했을때 함수 실행 */
     httpRequest.onreadystatechange = () => {
        /* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 name과 age를 그려줌 */
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
              if (httpRequest.status === 200) {
                var result = httpRequest.response;
                document.getElementById("calcString").innerText = result.calcString;
                document.getElementById("calcResult").innerText = result.calcResult;
              } else {
                alert('request에 뭔가 문제가 있어요.');
              }
        }
    };

    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/postcalc', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}



function reset() {
  var display = document.getElementById("display");
  display.value = "";
  var displayResult = document.getElementById("result");
  displayResult.value = "";
}

function del() {
  var display = document.getElementById("display");
  display.value = display.value.substring(0, display.value.length - 1);
}

