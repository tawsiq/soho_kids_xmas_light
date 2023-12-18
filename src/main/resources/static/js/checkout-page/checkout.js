
document.querySelector('.checkout').addEventListener('click', function(event) {
    event.preventDefault(); // Prevents the default link action

    // Example: Check if the cart is empty
    if (cartIsEmpty()) {
        alert("Your cart is empty!");
    } else {
        window.location.href = this.getAttribute('href'); // Redirect to the checkout page
    }
});

function cartIsEmpty() {
    // Implement a check to see if the cart is empty
    return cart.length === 0;
}

document.addEventListener('DOMContentLoaded', () => {
    OrderSummary();
});

function OrderSummary() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let orderSummaryContainer = document.getElementById('order-summary');

    cart.forEach(item => {
        let itemElement = document.createElement('div');
        itemElement.classList.add('order-item');
        itemElement.innerHTML = `
            <h3>${productName}</h3>
            <p>Price: £${item.price}</p>
            <p>Quantity: ${item.quantity}</p>
            <p>Total: £${(item.price * item.quantity).toFixed(2)}</p>
        `;
        orderSummaryContainer.appendChild(itemElement);
    });
}
