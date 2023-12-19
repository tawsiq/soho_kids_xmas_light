// Triggering the Data Send
document.addEventListener('DOMContentLoaded', function() {
    let checkoutButton = document.getElementById('checkoutButton');
    if (checkoutButton) {
        checkoutButton.addEventListener('click', sendCartToCheckout);
    }
});

// Retrieve Cart Data from Local Storage

function getCartData() {
    return JSON.parse(localStorage.getItem('shoppingCart')); // Assuming 'shoppingCart' is your key
}

//Send Cart Data to the Server
function sendCartToCheckout() {
    const cartData = getCartData();
    if (cartData) {
        $.ajax({
            url: '/checkout/cart', // Endpoint to be created in the backend
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(cartData),
            success: function(response) {
                // Handle successful response
            },
            error: function(error) {
                // Handle error
            }
        });
    }
}


