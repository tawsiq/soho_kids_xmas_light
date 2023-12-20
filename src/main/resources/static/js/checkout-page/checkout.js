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
