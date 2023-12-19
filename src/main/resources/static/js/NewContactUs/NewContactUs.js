// Adding JavaScript form validation so user is alerted when they have inputted something incorrectly.


/*This code should retain the character counter functionality while ensuring proper form submission validation.
By binding the submit event to the form and validating the inputs accordingly, the submit button will work as expected,
and the character count should update dynamically as the user types in the message field.*/

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("contactForm");
    const nameField = document.getElementById("name");
    const emailField = document.getElementById("email");
    const subjectField = document.getElementById("subject");
    const messageField = document.getElementById("message");
    const charCount = document.getElementById("charCount");

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
    });

    // Character counter functionality
    messageField.addEventListener("input", function() {
        const messageLength = messageField.value.length;
        charCount.textContent = `${messageLength} / 2500 characters`;
    });
});







/*document.addEventListener("DOMContentLoaded", function() {
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
});*/



/*
//JAVASCRIPT CHARACTER COUNTER NOT WORKING FOR SOME REASON, TRYING TO DEBUG
messageField.addEventListener("input", function() {
    const messageLength = messageField.value.length;
    charCount.textContent = `${messageLength} / 2500 characters`;
    console.log(`Message length: ${messageLength}`);
});
*/

