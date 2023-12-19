/**
 * 
 */
let checkboxes = document.querySelectorAll('input[name="selectOrder"]');
checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', calculateTotal);
});
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
/*$(".product_volume").on("change", function() {
    updateProductPerTotal($(this));
});
function updateProductPerTotal(volume) {
    let price = volume.closest('tr').find('.selectOrder').data("price");
    let perTotal = volume.val() * price; // Use volume.val() to get the input value
    volume.closest('tr').find('.productPerTotal')
        .text(perTotal.toLocaleString('ko-KR') + "원");
}// 제이쿼리 */ 

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

function updateSelectedItemCount(checkedCheckboxes) {
  let selectedItemCount = checkedCheckboxes.length;

  let selectedItemCountOutput = document.getElementById("selectedItemCount");
  selectedItemCountOutput.textContent = selectedItemCount;
}