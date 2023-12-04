let iconCart = document.querySelector('.icon-cart');
let closeCart =document.querySelector('.close');
let body = document.querySelector('body');
let listProductHTML = document.querySelector('.listProduct');
let listCartHTML = document.querySelector('.listCart');
let listCartSpan = document.querySelector('.icon-cart span');

let listProducts = [];
let carts = [];
iconCart.addEventListener('click', () => {
    body.classList.toggle('showCart')
})
closeCart.addEventListener('click', () => {
    body.classList.toggle('showCart')
})


const addDataToHTML = () => {
    listProductHTML.innerHTML = '';
    if(listProducts.length > 0){
        listProducts.forEach(product => {
            let newProduct = document.createElement('div');
            newProduct.classList.add('item');
            newProduct.dataset.id = product.id;
            newProduct.innerHTML = `
                <img src="http://localhost:63342/untitled/marketplace/src/${product.image}" alt="t-shirt design 1">
                <h2>${product.name}</h2>
                <div class="price">£${product.price}</div>
                <button class="addCart">
                    Add To Cart
                </button>`;
            listProductHTML.appendChild(newProduct); // Append newProduct to listProductHTML
        })
    }
}
listProductHTML.addEventListener('click',(event) => {
    let positionClick = event.target;
    if(positionClick.classList.contains('addCart')){
        let product_id = positionClick.parentElement.dataset.id;
        addToCart(product_id);
    }
})

const addToCart = (product_id) => {
    let positionThisProductInCart = cart.findIndex((value) => value.product_id == product_id);
    if (carts.length <= 0) {
        carts = [{
            product_id: product_id;
            quantity: 1;
        }]
    } else if(positionThisProductInCart < 0){
        carts.push({
            product_id: product_id,
            quantity: 1
        });
        
    }else{
        carts[positionThisProductInCart].quantity = carts[positionThisProductInCart].quantity + 1;
    }
    addCartToHTML();
}
const addCartToHTML = () => {
    listCartHTML.innerHTML = '';
    let totalQuantity = 0;
    if(carts.length > 0){
        carts.forEach(cart => {
            totalQuantity = totalQuantity + cart.quantity
            let newCart = document.createElement('div');
            newCart.classList.add('item');
             let positionProduct = listProducts.findIndex((value) => value.id == cart.product_id);
             let info = listProducts[positionProduct];
            newCart.innerHTML = `
                        <div class="item">
                <div class="image">
                    <img src="${info.image}" alt="">
                </div>
                    <div class="name">
                        ${info.name}
                    </div>
                    <div class="totalPrice">
                        £${info.price * cart.quantity}
                    </div>
                    <div class="quantity">
                        <span class="minus"><</span>
                        <span>${cart.quantity}</span>
                        <span class="plus">></span>
                    </div>
                `;
            listCartHTML.appendChild(newCart)

        })
    }
    iconCartSpan.innerText = totalQuantity;
}
const initApp = () => {
    //get data from json
    fetch('products.json')
        .then(response => response.json())
        .then(data => {
            listProducts = data;
            console.log(listProducts);
            addDataToHTML();
        })
}
initApp();
