// Adding JavaScript form validation so user is alerted when they have inputted something incorrectly.

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("contactForm");

    // ... (existing code remains unchanged)

    form.addEventListener("submit", function(event) {
        const name = nameField.value;
        const email = emailField.value;
        const subject = subjectField.value;
        const message = messageField.value;

        const emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if (!name.match(/^[a-zA-Z]+$/)) {
            alert("Name should only contain alphabetic characters!");
            event.preventDefault();
            return;
        }

        if (!email.match(emailFormat)) {
            alert("Please enter a valid email address!");
            event.preventDefault();
            return;
        }

        if (subject.length < 5 || subject.length > 50) {
            alert("Subject should be between 5 and 50 characters!");
            event.preventDefault();
            return;
        }

        if (message.length > 2500) {
            alert("Message exceeds the character limit of 2500!");
            event.preventDefault();
            return;
        }

        // Form submission will occur if all validations pass
    });

    // Character counter functionality remains unchanged
});



/*
//JAVASCRIPT CHARACTER COUNTER NOT WORKING FOR SOME REASON, TRYING TO DEBUG
messageField.addEventListener("input", function() {
    const messageLength = messageField.value.length;
    charCount.textContent = `${messageLength} / 2500 characters`;
    console.log(`Message length: ${messageLength}`);
});
*/

