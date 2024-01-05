
// 이메일 유효성 검사를 위한 스크립트
function validateEmail() {
	var email = document.forms["signinForm"]["email"].value;
    var emailError = document.getElementById("emailError");
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === "") {
        emailError.innerText = "를 입력해주세요.";
        emailError.className = "error-message";
        return false;
    } else if (!emailRegex.test(email)) {
        emailError.innerText = "를 올바르게 입력해주세요.";
        emailError.className = "warning-message";
        return false;
    } else {
        emailError.innerText = "";
        return true;
    }
}

// 비밀번호 유효성 검사를 위한 스크립트
function validatePassword() {
    var password = document.forms["signinForm"]["password"].value;
    var passwordError = document.getElementById("passwordError");
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
	
	if (password === "") {
        passwordError.innerText = "를 입력해주세요.";
        passwordError.className = "error-message"; // 빨간색
        return false;
    } else if (!passwordRegex.test(password)) {
        passwordError.innerText = "는 영문, 숫자, 특수문자를 조합해서 입력해주세요.(8~16자)";
        passwordError.className = "warning-message"; // 주황색
        return false;
    } else {
        passwordError.innerText = "";
        return true;
    }
}

// 모든 항목의 유효성 검사 수행
// 조건을 만족하지 않았을 경우 오류메세지 출력
function validateLoginForm() {
	var isValid = validateEmail() && validatePassword()
	if (!isValid) {
		alert("입력 정보를 확인해주세요."); // 예시: 입력 정보 오류 메시지
        return false;
    }
    return true;
}