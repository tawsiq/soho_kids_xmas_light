let listProductHTML = document.querySelector('.listProduct');
let listCartHTML = document.querySelector('.listCart');
let iconCart = document.querySelector('.icon-cart');
let iconCartSpan = document.querySelector('.icon-cart span');
let body = document.querySelector('body');
let closeCart = document.querySelector('.close');
let products = /*[[${products}]]*/ []; // Thymeleaf expression to get products
let cart = [];


iconCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
});


closeCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
});


const addDataToHTML = () => {
    // remove datas default from HTML


    // add new datas
    if(products.length > 0) // if has data
    {
        products.forEach(product => {
            let newProduct = document.createElement('div');
            newProduct.dataset.id = product.id;
            newProduct.classList.add('item');
            newProduct.innerHTML =
                `<img src="${product.image}" alt="">
               <h2>${product.name}</h2>
               <div class="price">$${product.price}</div>
               <button class="addCart">Add To Cart</button>`;
            listProductHTML.appendChild(newProduct);
        });
    }
};


document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.addCart').forEach(button => {
        button.addEventListener('click', function() {
            let productElement = this.closest('.item');
            let productId = productElement.dataset.id;
            addToCart(productId);
        });
    });

    // Load cart from local storage
    if (localStorage.getItem('cart')) {
        cart = JSON.parse(localStorage.getItem('cart'));
        addCartToHTML();
    }
});

const addToCart = (product_id) => {
    // Simplified logic for adding to cart
    let productElement = document.querySelector(`.item[data-id="${product_id}"]`);
    let productName = productElement.querySelector('h2').innerText;
    let productPrice = productElement.querySelector('.price').innerText.replace('£', '');

    let positionThisProductInCart = cart.findIndex((item) => item.product_id == product_id);
    if (positionThisProductInCart < 0) {
        cart.push({
            product_id: product_id,
            name: productName,
            price: productPrice,
            quantity: 1
        });
    } else {
        cart[positionThisProductInCart].quantity++;
    }
    addCartToHTML();
    addCartToMemory();
};


const addCartToMemory = () => {
    localStorage.setItem('cart', JSON.stringify(cart));
};


const addCartToHTML = () => {
    listCartHTML.innerHTML = '';
    let totalQuantity = 0;

    if (cart.length > 0) {
        cart.forEach(item => {
            totalQuantity += item.quantity;

            // Find the corresponding product element in the HTML
            let productElement = document.querySelector(`.item[data-id="${item.product_id}"]`);

            if (productElement) {
                let newItem = document.createElement('div');
                newItem.classList.add('item');
                newItem.dataset.id = item.product_id;

                let productName = productElement.querySelector('h2').innerText;
                let productPrice = productElement.querySelector('.price').innerText.replace('£', '');

                newItem.innerHTML = `
                    <div class="image">
                        <img src="${productElement.querySelector('img').src}">
                    </div>
                    <div class="name">${productName}</div>
                    <div class="totalPrice">£${productPrice * item.quantity}</div>
                    <div class="quantity">
                        <span class="minus"><</span>
                        <span>${item.quantity}</span>
                        <span class="plus">></span>
                    </div>
                `;
                listCartHTML.appendChild(newItem);
            }
        });
    }
    iconCartSpan.innerText = totalQuantity;
};



listCartHTML.addEventListener('click', (event) => {
    let positionClick = event.target;
    if(positionClick.classList.contains('minus') || positionClick.classList.contains('plus')){
        let product_id = positionClick.parentElement.parentElement.dataset.id;
        let type = 'minus';
        if(positionClick.classList.contains('plus')){
            type = 'plus';
        }
        changeQuantityCart(product_id, type);
    }
});


const changeQuantityCart = (product_id, type) => {
    let positionItemInCart = cart.findIndex((value) => value.product_id == product_id);
    if(positionItemInCart >= 0){
        let info = cart[positionItemInCart];
        switch (type) {
            case 'plus':
                cart[positionItemInCart].quantity = cart[positionItemInCart].quantity + 1;
                break;


            default:
                let changeQuantity = cart[positionItemInCart].quantity - 1;
                if (changeQuantity > 0) {
                    cart[positionItemInCart].quantity = changeQuantity;
                }else{
                    cart.splice(positionItemInCart, 1);
                }
                break;
        }
    }
    addCartToHTML();
    addCartToMemory();
};


const initApp = () => {
    // No need to fetch data here; it's directly embedded in the HTML via Thymeleaf


    // get data cart from memory
    if(localStorage.getItem('cart')){
        cart = JSON.parse(localStorage.getItem('cart'));
        addCartToHTML();
    }
};


initApp();

