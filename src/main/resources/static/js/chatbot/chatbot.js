/**
 * 
 */
var stompClient=null;

 document.getElementById("chatbot-toggle").addEventListener("click",chatbotClick);
 document.getElementById("chatbotInputbutton").addEventListener("click",chatbotContentInput);
 document.getElementById("closebox").addEventListener("click",function(){
	 document.getElementById("conversation").style.display = "none";
 })
  $("#chatbotInput").keypress(function(event) {
        if (event.which === 13) {
            event.preventDefault(); // 기본 엔터 동작 방지

            // 엔터 키가 눌렸을 때 할 작업 추가
            chatbotContentInput();
        }
    });
 function chatbotContentInput() {
	 let content = document.getElementById("chatbotInput").value.trim();
	 if(content!=="") {
		 showUserMessage(content);
		 document.getElementById("chatbotInput").value='';
		 sendMessage(content);
	 }
 }
 function showUserMessage(content){
	 let tag = `
		 	<div class="user-question flex">
				<span>${content}</span>
			</div>
	 `;
	 $(".c-container").append(tag);
	 // 스크롤을 가장 아래로 이동
     $(".c-container").scrollTop($(".c-container")[0].scrollHeight);
	 
 }
 function sendMessage(question){
	 
	 let key = new Date().getTime();
	 console.log(key);
	 
	 stompClient.subscribe(`/topic/question/${key}`, function(msgData) {
		 //메세지 수신
		 let msg = JSON.parse(msgData.body);
		 showBotMessage(msg.content);
	 });
	 
	 let data= {
		 key: key,
		 content: question
	 }
	 
	 //3.발행 접속되었다 메세지
	 //사용자->브로커 메세지 전송
	 stompClient.send("/message/question",{},JSON.stringify(data));
	 
 }
 
 function chatbotClick() {
	 //1.채팅창 보여주기
     // let element = document.getElementById("conversation");
     //element.style.display = (element.style.display === "none" || element.style.display === "") ? "block" : "none";
      
      //2.소켓접속
      if(stompClient==null) {
      	connect();
      } else {
		  $("#conversation").show();
	  }
 }
 function connect(){
    stompClient =  Stomp.over(new SockJS("/green-bot"));
    stompClient.connect({},connectWellcome);
 }
 function connectWellcome(){
	 //1. 대화창 화면에 보여주기
	 $("#conversation").show();
	 
	 //2.구독
	 stompClient.subscribe("/topic/greetings", function(msgData) {
		 //메세지 수신
		 let msg=JSON.parse(msgData.body);
		 let tag=`
		 	<div class="user-answer">
				<span>${msg.content}</span>
			</div>
		 `;
		 showMessage(tag);
	 });
	 
	 let data= {
		 name: "guest"
	 }
	 
	 //3.발행 접속되었다 메세지
	 //사용자->브로커 메세지 전송
	 stompClient.send("/message/hello",{},JSON.stringify(data));
 }
 //메세지 추가
 function showMessage(tag) {
	 $(".c-container").append(tag);
	 // 스크롤을 가장 아래로 이동
     $(".c-container").scrollTop($(".c-container")[0].scrollHeight);
 }
 function showBotMessage(content) {
	 let tag=`
		 	<div class="user-answer">
				<span>${content}</span>
			</div>
		 `;
	 $(".c-container").append(tag);
	 // 스크롤을 가장 아래로 이동
        $(".c-container").scrollTop($(".c-container")[0].scrollHeight);
 }