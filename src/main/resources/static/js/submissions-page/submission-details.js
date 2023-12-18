document.addEventListener('DOMContentLoaded', function() {
    const checkbox = document.getElementById('liked');
    const likeCount = document.getElementById('likeCount');
    const linkToCopy = document.getElementById('linkToCopy');

    checkbox.addEventListener('change', fetchLikes)
        // Used to simulate likes going up / down 1 when clicked, but this would only register and be stored on the database once submitted manually.
        // I need a new system of like storage, one that updates the database with an empty comment with either 1, 0, or -1. -1
        //  will be stored if the button goes from checked to unchecked, decreasing the total count. 1 is stored if the button goes from unchecked to checked,
        //  and 0 is stored otherwise.

        // const currentValue = parseInt(likeCount.textContent);
        // if (event.target.checked) {
        //     likeCount.textContent = (currentValue + 1).toString();
        // } else {
        //     likeCount.textContent = (currentValue - 1).toString();
        // }


    linkToCopy.addEventListener('click', function() {
        // Get the value from the 'data-value' attribute
        const linkText = this.getAttribute('data-value');

        // Copy the text to the clipboard
        copyToClipboard(linkText);

        // Optionally, you can provide feedback to the user
        this.textContent = 'Link Copied';
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

    const clickableDivs = document.querySelectorAll('.clickableDiv');

    clickableDivs.forEach((div) => {
        div.addEventListener('click', () => {
            // Your click event logic here
            console.log('Element clicked!');
        });

        div.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') {
                event.preventDefault();
                div.click();
            }
        });
    });
});

