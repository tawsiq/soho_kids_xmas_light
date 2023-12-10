const checkbox = document.getElementById('liked');
let likeCount = document.getElementById('likeCount');

checkbox.addEventListener('change', function(event) {
    // Your code here to handle the change event
    if (event.target.checked) {
        let currentValue = parseInt(likeCount.textContent);
        currentValue += 1;
        // Update the displayed number
        likeCount.textContent = currentValue.toString();
    } else {
        let currentValue = parseInt(likeCount.textContent);
        currentValue -= 1;
        likeCount.textContent = currentValue.toString();
    }

});