// signup.js

// 이메일 유효성 검사를 위한 스크립트
function validateEmail() {
	var email = document.forms["signupForm"]["email"].value;
    var emailError = document.getElementById("emailError");
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	
	if (email === "") {
        emailError.innerText = "를 입력해주세요.";
        return false;
    } else if (!emailRegex.test(email)) {
        emailError.innerText = "를 올바르게 입력해주세요.";
        return false;
    } else {
        emailError.innerText = "가 멋지네요.";
        return true;
    }
}

// 비밀번호 유효성 검사를 위한 스크립트
function validatePassword() {
    var password = document.forms["signupForm"]["password"].value;
    var passwordError = document.getElementById("passwordError");
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
	
	if (password === "") {
        passwordError.innerText = "를 입력해주세요.";
        return false;
    } else if (!passwordRegex.test(password)) {
        passwordError.innerText = "는 영문, 숫자, 특수문자를 조합해서 입력해주세요.(8~16자)";
        return false;
    } else {
        passwordError.innerText = "가 완벽합니다.";
        return true;
    }
}

// 비밀번호 확인 유효성 검사를 위한 스크립트
function validateConfirmPassword() {
    var confirmPassword = document.forms["signupForm"]["c_password"].value;
    var password = document.forms["signupForm"]["password"].value;
    var cPasswordError = document.getElementById("c_passwordError");
	
	if (confirmPassword === "") {
    	cPasswordError.innerText = "를 재입력해주세요.";
    	return false;
    } else if (confirmPassword !== password) {
        cPasswordError.innerText = "가 일치하지 않습니다.";
        return false;
    } else {
        cPasswordError.innerText = "가 일치합니다.";
        return true;
    }
}

// 이름 유효성 검사를 위한 스크립트
function validateName() {
    var name = document.forms["signupForm"]["name"].value;
    var nameError = document.getElementById("nameError");
    var nameRegex = /^[a-zA-Z가-힣]{2,20}$/;

    if (name === "") {
        nameError.innerText = "을 입력해주세요.";
        return false;
    } else if (!nameRegex.test(name)) {
        nameError.innerText = "이 올바르지 않습니다.";
        return false;
    } else {
        nameError.innerText = "이 예쁘네요.";
        return true;
    }
}

// 전화번호 유효성 검사를 위한 스크립트
function validateTelNum() {
    var telNum = document.forms["signupForm"]["tel_num"].value;
    var telNumError = document.getElementById("tel_numError");
    var telNumRegex = /^[0-9]{10,11}$/;
	
	if (telNum === "") {
        telNumError.innerText = "를 입력해주세요.";
        return false;
    } else if (!telNumRegex.test(telNum)) {
        telNumError.innerText = "는 -없이 입력해주세요.(10~11자)";
        return false;
    } else {
        telNumError.innerText = "가 입력되었습니다.";
        return true;
    }
}

// 모든 유효성 검사와 약관 동의를 확인하는 스크립트
function checkAll() {
    // 모든 항목의 유효성 검사 수행
    var isValid = validateEmail() && validatePassword() && validateConfirmPassword() && validateName() && validateTelNum();
	
    // 모든 약관 동의 체크 확인
    var allCheck = document.getElementById("all_switch").checked;

    if (isValid && allCheck) {
        // 유효성 검사 및 약관 동의 모두 충족 시 리다이렉트 또는 DB 저장 수행
        // 여기에 필요한 처리를 추가하세요
        alert("회원가입이 완료되었습니다."); // 예시: 회원가입 완료 메시지
        return true;
    } else {
        // 하나라도 조건을 만족하지 않을 경우 에러 메시지 출력
        if (!isValid) {
            alert("입력 정보를 확인해주세요."); // 예시: 입력 정보 오류 메시지
        }
        if (!allCheck) {
            alert("약관에 동의해주세요."); // 예시: 약관 동의 오류 메시지
        }
        return false;
    }
}

// 아코디언 기능을 위한 스크립트
$(function () {
    $('.drop_area').click(clickedBtnMenu);
});

function clickedBtnMenu() {
    $(this).find('.terms_txt').slideToggle(112);
}

// 개별 체크박스에 기반한 checkAll 업데이트를 위한 스크립트
document.addEventListener("DOMContentLoaded", function () {
    const checkAll = document.getElementById("all_switch");
    const checkBoxesNormal = document.querySelectorAll('.terms_agree .normal');

    function updateCheckAll() {
        checkAll.checked = Array.from(checkBoxesNormal).every((checkbox) => checkbox.checked);
    }

    checkAll.addEventListener('click', function () {
        checkBoxesNormal.forEach((checkbox) => {
            checkbox.checked = checkAll.checked;
        });
    });

    checkBoxesNormal.forEach((checkbox) => {
        checkbox.addEventListener('click', updateCheckAll);
    });
});

// 체크 박스 누르면 색변환
document.addEventListener("DOMContentLoaded", function () {
    const checkAll = document.getElementById("all_switch");
    const checkBoxesNormal = document.querySelectorAll('.terms_agree .normal');
    const agreeAllContainer = document.querySelector('.agree_all');
    const agreeCheckContainers = document.querySelectorAll('.agree_check');

    function updateCheckAll() {
        if (checkAll.checked) {
            setCheckedStyle();
        } else {
            setUncheckedStyle();
        }
    }

    function setCheckedStyle() {
        agreeAllContainer.style.backgroundColor = "#BF9270";
        agreeAllContainer.style.color = "white";
        agreeCheckContainers.forEach(container => {
            container.style.backgroundColor = "#BF9270";
            container.style.color = "white";
        });
    }

    function setUncheckedStyle() {
        if (Array.from(checkBoxesNormal).every(checkbox => checkbox.checked)) {
            agreeAllContainer.style.backgroundColor = "#BF9270";
            agreeAllContainer.style.color = "white";
        } else {
            agreeAllContainer.style.backgroundColor = "";
            agreeAllContainer.style.color = "";
        }

        agreeCheckContainers.forEach(container => {
            const checkbox = container.querySelector('.normal');
            if (checkbox.checked) {
                container.style.backgroundColor = "#BF9270";
                container.style.color = "white";
            } else {
                container.style.backgroundColor = "";
                container.style.color = "";
            }
        });
    }

    checkAll.addEventListener('click', function () {
        checkBoxesNormal.forEach((checkbox) => {
            checkbox.checked = checkAll.checked;
        });
        updateCheckAll();
    });

    checkBoxesNormal.forEach((checkbox) => {
        checkbox.addEventListener('click', function () {
        updateCheckAll();
        });
    });
});
