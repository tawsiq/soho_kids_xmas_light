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
