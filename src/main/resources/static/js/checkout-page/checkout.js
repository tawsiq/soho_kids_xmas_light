
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
// Assuming 'cart' is your cart array and each item has 'price' and 'quantity' properties

// Function to calculate total price
function calculateTotalPrice() {
    return cart.reduce((total, item) => total + item.price * item.quantity, 0);
}

// Function to update total price in the hidden input field
function updateTotalPriceInCheckout() {
    const totalPrice = calculateTotalPrice();
    document.getElementById('totalPriceInput').value = totalPrice.toFixed(2);
}

// Bind this function to the form's submit event or any event that changes the cart
const checkoutForm = document.querySelector('form');
checkoutForm.addEventListener('submit', updateTotalPriceInCheckout);

function displayOrderSummary() {
    let summaryHtml = '';
    cart.forEach(item => {
        summaryHtml += `<div>${item.name} - Quantity: ${item.quantity} - Price: £${item.price.toFixed(2)}</div>`;
    });
    summaryHtml += `<div>Total Cost: £${calculateTotalCost().toFixed(2)}</div>`;
    document.getElementById('orderSummary').innerHTML = summaryHtml;
}

function calculateTotalCost() {
    return cart.reduce((total, item) => total + item.price * item.quantity, 0);
}

// Call this function when the page loads
displayOrderSummary();
document.addEventListener('DOMContentLoaded', function() {
    displayOrderSummary();
});

