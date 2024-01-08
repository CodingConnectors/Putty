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
	let amount = document.getElementById('totalPrice').getAttribute('data-total-price');
	//결제id생성
	let payuid = generateUUID();
	// 예제 사용
	/*console.log(uuid);
	console.log(amount)*/
	
	/*
	let PaymentProductsDTO = {
		productNo: [1],
		volume: [1]
	}
	
	$.ajax({
		url: "/payment/pay",
		method: "post",
        contentType: 'application/json',
		data: JSON.stringify(PaymentProductsDTO),
		success: function(response) {
	        // 성공적으로 응답을 받았을 때 수행할 코드
	        console.log("Payment successful:", response);
	    },
	    error: function(xhr, status, error) {
	        // 에러가 발생했을 때 수행할 코드
	        console.error("Payment error:", status, error);
	    }
	})
	*/
	let data={
	    pg: pg,
	    pay_method: "card",
	    merchant_uid: payuid,
	    name: "테스트 결제",
	    amount: amount,
	    buyer_tel: buyer_tel,
	}
	IMP.request_pay(data, rsp => {
	    if (rsp.success) {
	      // axios로 HTTP 요청
	      axios({
	        url: "/payment/pay",
	        method: "post",
	        headers: { "Content-Type": "application/json" },
	        data: {
	          imp_uid: rsp.imp_uid,
	          merchant_uid: rsp.merchant_uid
	        }
	      }).then((data) => {
	        // 서버 결제 API 성공시 로직
	        window.location.href = "/payment/suc";
	      })
	    } else {
	      alert(`결제에 실패하였습니다. 에러 내용: ${rsp.error_msg}`);
	    }
	});
}

function generateUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random() * 16 | 0,
      v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}
