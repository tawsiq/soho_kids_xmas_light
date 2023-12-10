document.addEventListener('DOMContentLoaded', function() {
    const checkbox = document.getElementById('liked');
    const likeCount = document.getElementById('likeCount');
    const linkToCopy = document.getElementById('linkToCopy');

    checkbox.addEventListener('change', function(event) {
        // Your code here to handle the change event
        const currentValue = parseInt(likeCount.textContent);
        if (event.target.checked) {
            likeCount.textContent = (currentValue + 1).toString();
        } else {
            likeCount.textContent = (currentValue - 1).toString();
        }
    });

    linkToCopy.addEventListener('click', function() {
        // Get the value from the 'data-value' attribute
        const linkText = this.getAttribute('data-value');

        // Copy the text to the clipboard
        copyToClipboard(linkText);

        // Optionally, you can provide feedback to the user
        this.textContent = 'Link Copied!';
        setTimeout(() => {
            this.textContent = 'Copy Link';
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
