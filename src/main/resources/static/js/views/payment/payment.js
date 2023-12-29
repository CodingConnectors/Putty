/**
 * 
 */

IMP.init("imp55174178");

document.getElementById('payment-button').addEventListener('click',requestPay)

// 선택된 라디오 버튼의 부모 레이블에 배경색을 빨간색으로 설정
$('input[name="payment-gateway"]:checked').parent('label').css('outline', '3px solid burlywood');

// 라디오 버튼이 변경될 때 이벤트를 처리하여 배경색을 업데이트
$('input[name="payment-gateway"]').change(function() {
    // 모든 레이블의 배경색을 초기화 (옵션)
    $('label').css('outline', '');

    // 선택된 라디오 버튼의 부모 레이블에 배경색을 빨간색으로 설정
    $(this).parent('label').css('outline', '3px solid burlywood');
});

function requestPay() {
	let pg = document.querySelector('input[name="payment-gateway"]:checked').value;
	let buyer_tel = document.querySelector('input[name="phone"]').value;
	console.log(buyer_tel)
	let data={
	    pg: pg,
	    pay_method: "card",
	    merchant_uid: "test_lqohvy6f",
	    name: "테스트 결제",
	    amount: 100,
	    buyer_tel: buyer_tel,
	}
	IMP.request_pay(data);
}


