// 유효성 검사 메서드
    function checkAll() {
		
		window.onload = function() {
		var join = document.join; //form데이터를 모두 join변수에 저장
		
		// 오류 문구 //errorId : span의 id들(각 요소마다 나타낼 오류를 표시하기 위함)
		// error : class list의 하위 span을 모두 불러냄(일괄 처리를 위함 - 반복문)
		var errorId = [ "emailError", "passwordError", "c_passwordError", "nameError", "tel_numError" ];
		
		
        //변수에 저장
        var email = document.getElementById("email")
        var password = document.getElementById("password")
        var c_password = document.getElementById("c_password")
        var name = document.getElementById("name")
		var tel_num = document.getElementById("tel_num")
		
		var emailError = document.getElementById("emailError")
		var passwordError = document.getElementById("passwordError")
		var c_passwordError = document.getElementById("c_passwordError")
		var nameError = document.getElementById("nameError")
		var tel_numError = document.getElementById("tel_numError")
		var error = document.querySelectorAll('.list > span');
		
        // 정규식
        // password
        var regIdpassword = /^[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}$/;
        // 이름
        var regName = /^[가-힣a-zA-Z]{2,15}$/;
        // 이메일
        var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
        // 휴대폰 번호
        var regTel_num = /^[0-9]{10,11}$/;

        //메일주소 확인
        if(email.value.length == 0){
            alert("이메일을 입력해주세요.")
            email.focus();
            return false;
        }

        else if(!regEmail.test(email.value)){
            alert("잘못된 이메일 형식입니다.")
            email.focus();
            return false;
        }

        //비밀번호 확인
        if(password.value == ""){
            alert("비밀번호를 입력하세요.")
            password.focus();
            return false;
        }
        //비밀번호 영어 대소문자 확인
        else if(!regIdpassword.test(password.value)){
            alert("4~12자 영문 대소문자, 숫자만 입력하세요.")
            password.focus();
            return false;
        }

        //비밀번호 확인
        if(c_password.value !== password.value){
            alert("비밀번호와 동일하지 않습니다.")
            c_password.focus();
            return false;
        }

        //이름 확인 = 한글과 영어만 가능하도록
        if(name.value == ""){
            alert("이름을 입력하세요.")
            name.focus();
            return false;
        }

        else if(!regName.test(name.value)){
            alert("최소 2글자 이상, 한글과 영어만 입력하세요.")
            name.focus();
            return false;
        }
        
        if(tel_num.value == ""){
            alert("휴대폰 번호를 입력하세요.")
            tel_num.focus();
            return false;
        }
        //휴대폰 번호 숫자만 입력했는지 확인
        else if(!regTel_num.test(tel_num.value)){
            alert("-없이 숫자만 입력하세요.")
            tel_num.focus();
            return false;
        }
       