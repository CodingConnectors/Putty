/**
 * 
 */
let checkboxes = document.querySelectorAll('input[name="selectOrder"]');
checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', calculateTotal);
});

function calculateTotal() {
  // 체크된 체크박스만 가져옴
  let checkedCheckboxes = document.querySelectorAll('input[name="selectOrder"]:checked');
  // 선택한 상품의수(체크박스) 출력
  updateSelectedItemCount(checkedCheckboxes);
  
  let totalPrice = 0;

  checkedCheckboxes.forEach(function(checkbox) {
    // 각 체크된 상품의 가격을 가져와서 더합니다.
    let price = parseInt(checkbox.getAttribute("data-price"));
    totalPrice += price;
  });

  // 결과를 화면에 출력합니다.
  let totalOutput = document.getElementById("totalPrice");
  totalOutput.textContent = totalPrice+"원";
  
}

function updateSelectedItemCount(checkedCheckboxes) {
  let selectedItemCount = checkedCheckboxes.length;

  let selectedItemCountOutput = document.getElementById("selectedItemCount");
  selectedItemCountOutput.textContent = selectedItemCount;
}