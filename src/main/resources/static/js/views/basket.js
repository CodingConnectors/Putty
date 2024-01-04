/**
 * 
 */
let checkboxes = document.querySelectorAll('input[name="selectOrder"]');
checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', calculateTotal);
});
$('#selectOrderButton').click(selectProductPayment)
//삭제버튼시 장바구니에서 물품 삭제
$(".delBasket").on("click", function() {
    // 선택된 행에서 data-productId 속성 값을 가져옴
    var productId = $(this).closest("tr").find(".selectOrder").data("productid");

    // 서버로 AJAX 요청을 보냄
    $.ajax({
        url: "/basket/"+productId,  // 델리트 매핑 URL로 변경
        type: "DELETE",
        success: function(response) {
            // 삭제 성공 시 처리
            // 페이지를 리다이렉트하고 새로고침
            window.location.href = "/basket";
        },
        error: function(error) {
            // 삭제 실패 시 처리
            console.error("상품 삭제 실패");
            // 원하는 동작 수행
        }
    });
});
//바로주문 버튼 클릭시 주문
$('.directOrder').on('click',function() {
    var productNo = $(this).closest("tr").find(".selectOrder").data("productid");

    // 동적으로 form 생성
    var form = document.createElement('form');
    form.action = '/payment';
    form.method = 'post';
    form.style.display = 'none'; // 화면에 표시되지 않도록 설정

    // 폼에 데이터 추가
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'productNo';
    input.value = productNo;
    form.appendChild(input);

    // body에 form 추가
    document.body.appendChild(form);

    // form 제출 (새로고침)
    form.submit();

    // 폼이 더 이상 필요하지 않으면 제거
    document.body.removeChild(form);
});

//선택한 물품들을 결제페이지로 이동
function selectProductPayment() {
  // 선택된 체크박스들을 가져와서 처리
    let selectedProducts = $('.selectOrder:checked');
    let form = document.createElement('form');
    
    form.action='/payment';
    form.method='post';
    
    // 선택된 상품들의 productNo를 배열에 담기
    selectedProducts.each(function() {
		let input = document.createElement('input');
        let productNo = $(this).data('productid');
	    input.name = 'productNo';
	    input.value = productNo;
        form.appendChild(input);
    });
    
    // form을 body에 추가하고 자동으로 submit
    document.body.appendChild(form);
    form.submit();
    
    //console.log(JSON.stringify(paymentProductsDTO))
	/*
    // 서버로 데이터 전송
    $.ajax({
        url: '/payment',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(paymentProductsDTO),
        success: function(response) {
            // 서버 응답을 처리
            window.location=response;
        }
	});
	*/
}

//수량이 바뀌면 금액을 계산해주는 코드
document.querySelectorAll('.product_volume').forEach(function (input) {
    updateProductPerTotal(input);
    input.addEventListener('change', function () {
        updateProductPerTotal(input);
        calculateTotal();
    });
});


function updateProductPerTotal(volume) {
    let price = volume.closest('tr').querySelector('.selectOrder').getAttribute("data-price");
    let discount = volume.value * price / volume.closest('tr').querySelector('.selectOrder').getAttribute("data-discount");
    let perTotal =  volume.value * price - discount;
    volume.closest('tr').querySelector('.productPerTotal')
        .textContent = perTotal.toLocaleString('ko-KR') + "원";
    volume.closest('tr').querySelector('.discount')
        .textContent = discount.toLocaleString('ko-KR');
    volume.closest('tr').querySelector('.discountPer')
        .textContent = volume.closest('tr').querySelector('.selectOrder').getAttribute("data-discount")+"%";
}

//체크된 모든 상품의 가격을 계산해서 출력하는 코드
function calculateTotal() {
  // 체크된 체크박스만 가져옴
  let checkedCheckboxes = document.querySelectorAll('input[name="selectOrder"]:checked');
  // 선택한 상품의수(체크박스) 출력
  updateSelectedItemCount(checkedCheckboxes);
  
  let totalPrice = 0;
  let totalDisCount = 0;

  checkedCheckboxes.forEach(function(checkbox) {
    // 각 체크된 상품의 가격을 가져와서 더합니다.
    let price = parseInt(checkbox.getAttribute("data-price"));
    let volume = checkbox.closest('tr').querySelector('.product_volume').value;;
    
    let perTotal = price*volume;
    let disCount = perTotal / 10;
    
    
    totalDisCount += disCount;
    totalPrice += (perTotal);
  });

  // 결과를 화면에 출력합니다.
  let totalOutput = document.getElementById("totalPrice");
  totalOutput.textContent = totalPrice.toLocaleString('ko-KR') +"원";
  let totalOutputDC = document.getElementById("totalDiscount");
  totalOutputDC.textContent = "-" + totalDisCount.toLocaleString('ko-KR') +"원";
  let totalOrderPrice = document.getElementById("totalOrderPrice");
  totalOrderPrice.textContent = (totalPrice-totalDisCount).toLocaleString('ko-KR') +"원";
  
}

//선택된 상품 갯수 출력
function updateSelectedItemCount(checkedCheckboxes) {
  let selectedItemCount = checkedCheckboxes.length;

  let selectedItemCountOutput = document.getElementById("selectedItemCount");
  selectedItemCountOutput.textContent = selectedItemCount;
}