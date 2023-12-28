/**
 * 
 */

function createIndexBestProducts() {
	var bestUl = document.getElementById('best-item-ul');
	var createCount = 16;
	
	for (var i = 0; i < createCount; i++) {
		
		var bestLi = document.createElement('li');
		bestLi.className = 'item-li';
		
		var innerBestProducts = `
			<div class="best-item-wrap">
				<div class="item-img-wrap relative flex">
					<canvas class="best-item-img"></canvas>
					<div class="item-btn flex space-evenly">
						<a href="#">
							<button class="index-products-btn more-btn"></button>
						</a>
						<button class="index-products-btn shopping-cart-btn" onclick="modal()"></button>
						<button class="index-products-btn favorite-btn favorite-btn-1"></button>
					</div>
				</div>
				<div class="item-info-wrap">
					<div class="item-info-1">
						<a href="#">공간 활용 고양이 윈도우 해먹</a>
					</div>
					<div class="item-info-2 flex center-1 start">
						<span class="sale">99%</span>
						<span class="price">10000원</span>
						<span class="sale-price">100원</span>
					</div>
				</div>
			</div>
		`;
		
		bestLi.innerHTML = innerBestProducts;
		bestUl.appendChild(bestLi);
	}
}

function createIndexNewArrivalsProducts() {
	var newArrivalsUl = document.getElementById('newArrivals-item-ul');
	var createCount = 16;
	
	for (var i = 0; i < createCount; i++) {
		
		var newArrivalsLi = document.createElement('li');
		newArrivalsLi.className = 'item-li';
		
		var innerNewArrivalsProducts = `
			<li class="item-li">
				<div class="best-item-wrap">
					<div class="item-img-wrap relative flex">
						<canvas class="newArrivals-item-img"></canvas>
						<div class="item-btn flex space-evenly">
							<a href="#">
								<button class="index-products-btn more-btn"></button>
							</a>
							<button class="index-products-btn shopping-cart-btn" onclick="modal()"></button>
							<button class="index-products-btn favorite-btn favorite-btn-1"></button>
						</div>
					</div>
					<div class="item-info-wrap">
						<div class="item-info-1">
							<a href="#">노즈 워크 장난감</a>
						</div>
						<div class="item-info-2 flex center-1 start">
							<span class="sale">99%</span>
							<span class="price">10000원</span>
							<span class="sale-price">100원</span>
						</div>
					</div>
				</div>
			</li>
		`;
		
		newArrivalsLi.innerHTML = innerNewArrivalsProducts;
		newArrivalsUl.appendChild(newArrivalsLi);
	}
}

function createIndexProducts() {
	createIndexBestProducts();
	createIndexNewArrivalsProducts();
}

window.onload = createIndexProducts;