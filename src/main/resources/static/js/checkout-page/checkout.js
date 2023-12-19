// Triggering the Data Send
document.addEventListener('DOMContentLoaded', function() {
    let checkoutButton = document.getElementById('checkoutButton');
    if (checkoutButton) {
        checkoutButton.addEventListener('click', sendCartToCheckout);
    }
});



function sendCartToCheckout() {
    const cartData = getCartData();
    if (cartData) {
        // Example AJAX call to send data to the server
        $.ajax({
            url: '/path/to/your/endpoint', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(cartData),
            success: function(response) {
                // Handle successful response
                console.log("Checkout data sent successfully:", response);
            },
            error: function(error) {
                // Handle error
                console.error("Error sending checkout data:", error);
            }
        });
    }
}


document.addEventListener('DOMContentLoaded', function() {
    populateCheckoutTable();

    let checkoutButton = document.getElementById('checkoutButton');
    if (checkoutButton) {
        checkoutButton.addEventListener('click', sendCartToCheckout);
    }
});

// Retrieve Cart Data from Local Storage
function getCartData() {
    return JSON.parse(localStorage.getItem('cart')); // Changed key to 'cart'
}

// Populate Checkout Table with Cart Data
function populateCheckoutTable() {
    const cartData = getCartData();
    const checkoutTable = document.getElementById('checkoutTable'); // Assuming you have a table with this ID

    if (cartData && checkoutTable) {
        cartData.forEach(item => {
            const row = checkoutTable.insertRow();
            row.innerHTML = `
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>£${item.price}</td>
                <td>£${(item.price * item.quantity).toFixed(2)}</td>
            `;
        });
    }
}

// Triggering the Data Send and Populate Table
document.addEventListener('DOMContentLoaded', function() {
    populateCheckoutTable(); // Populate table on page load

    let checkoutButton = document.getElementById('checkoutButton');
    if (checkoutButton) {
        checkoutButton.addEventListener('click', sendCartToCheckout);
    }
});


