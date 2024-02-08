let listProductHTML = document.querySelector('.listProduct');
let listCartHTML = document.querySelector('.listCart');
let iconCart = document.querySelector('.icon-cart');
let iconCartSpan = document.querySelector('.icon-cart span');
let body = document.querySelector('body');
let closeCart = document.querySelector('.close');
let products = /*[[${products}]]*/ []; 
let cart = [];


// Event listeners for showing/hiding the cart
iconCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
});
closeCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
});


// Event listeners for 'Add to Cart' buttons, attach to server-rendered buttons
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.addCart').forEach(button => {
        button.addEventListener('click', function() {
            let productElement = this.closest('.item');
            let productId = productElement.dataset.id;
            addToCart(productId);
        });
    });

    // Load cart from local storage and update HTML
    if (localStorage.getItem('cart')) {
        cart = JSON.parse(localStorage.getItem('cart'));
        addCartToHTML();
    }
});


// Function to add items to the cart
const addToCart = (product_id) => {
    // Find the product element and extract necessary data
    let productElement = document.querySelector(`.item[data-id="${product_id}"]`);
    let productName = productElement.querySelector('h2').innerText;
    let productPrice = productElement.querySelector('.price').innerText.replace('£', '');

    // Check if product is already in the cart and update quantity or add new product
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
    // Update HTML and local storage with new cart data
    addCartToHTML();
    addCartToMemory();
};


// Function to save cart data to local storage
const addCartToMemory = () => {
    localStorage.setItem('cart', JSON.stringify(cart));
};


// Function to update the cart display in HTML
const addCartToHTML = () => {
    // Clear existing cart items and initialize variables for total quantity and cost
    listCartHTML.innerHTML = '';
    let totalQuantity = 0;
    let total_cost = 0;

    // Iterate over cart items to create and append HTML elements for each item
    if (cart.length > 0) {
        cart.forEach(item => {
            totalQuantity += item.quantity;
            total_cost += item.price * item.quantity; // Calculate total cost

            // Create new cart item element and append it to the cart HTML
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

        // Display total cost in the cart
        let total_costElement = document.createElement('div');
        total_costElement.classList.add('total-cost');
        total_costElement.innerHTML = `<strong>Total Cost:</strong> £${total_cost.toFixed(2)}`;
        listCartHTML.appendChild(total_costElement);
    }
    // Update cart icon with total quantity of items
    iconCartSpan.innerText = totalQuantity;
};


// Event listener for changing item quantity in the cart
listCartHTML.addEventListener('click', (event) => {
    let positionClick = event.target;
    if(positionClick.classList.contains('minus') || positionClick.classList.contains('plus')){
        // Determine product ID and type of action (plus or minus)
        let product_id = positionClick.parentElement.parentElement.dataset.id;
        let type = 'minus';
        if(positionClick.classList.contains('plus')){
            type = 'plus';
        }
        changeQuantityCart(product_id, type);
    }
});


// Function to change the quantity of a cart item
const changeQuantityCart = (product_id, type) => {
    // Find the item in the cart and update its quantity based on type
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
                    // Remove item from cart if quantity falls below 1
                    cart.splice(positionItemInCart, 1);
                }
                break;
        }
    }
    // Update cart display and local storage
    addCartToHTML();
    addCartToMemory();
};

// Initialize the app
const initApp = () => {

    // get data cart from local storage
    if(localStorage.getItem('cart')){
        cart = JSON.parse(localStorage.getItem('cart'));
        addCartToHTML();
    }
};
initApp();
