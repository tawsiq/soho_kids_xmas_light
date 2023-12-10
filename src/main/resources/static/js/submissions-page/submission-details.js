document.addEventListener('DOMContentLoaded', function() {

    const checkbox = document.getElementById('liked');
    let likeCount = document.getElementById('likeCount');

    checkbox.addEventListener('change', function (event) {
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

    const copyButton = document.getElementById('linkToCopy');
    const labelForButton = document.querySelector('.copyLink-button');

    copyButton.addEventListener('click', function () {
        // Get the value from the 'data-value' attribute
        const linkText = this.getAttribute('data-value');

        // Copy the text to the clipboard
        copyToClipboard(linkText);

        // Optionally, you can provide feedback to the user
        labelForButton.textContent = 'Link Copied!';
        setTimeout(() => {
            labelForButton.textContent = 'Copy Link';
        }, 2000); // Reset button text after 2 seconds
    });

    function copyToClipboard(text) {
        navigator.clipboard.writeText(text)
            .then(() => {
                console.log('Text copied to clipboard');
                // You can add additional logic here after successful copy
            })
            .catch(err => {
                console.error('Failed to copy: ', err);
                // Handle errors here
            });
    }
});
