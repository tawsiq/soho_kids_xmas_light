// Retrieve Cart Data from Local Storage
function getCartData() {
    return JSON.parse(localStorage.getItem('cart'));
}

// Populate Checkout Table with Cart Data
function populateCheckoutTable() {
    const cartData = getCartData();
    const checkoutTable = document.getElementById('checkoutTable');
    const totalCostElement = document.getElementById('totalCost');

    if (cartData && checkoutTable) {
        let totalCost = 0;
        // Clear existing rows and set up headers
        checkoutTable.innerHTML = '<tr><th>Product Name</th><th>Quantity</th><th>Price</th></tr>';

        // Populate table with cart items
        cartData.forEach(item => {
            const row = checkoutTable.insertRow();
            row.innerHTML = `
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>£${item.price}</td>
                <td>£${(item.price * item.quantity).toFixed(2)}</td>
            `;
            totalCost += item.price * item.quantity;
        });

        // Update the total cost display
        if (totalCostElement) {
            totalCostElement.innerText = `£${totalCost.toFixed(2)}`;
        }
    }
}

// Event Listener for Page Load
document.addEventListener('DOMContentLoaded', function() {
    populateCheckoutTable();
});
