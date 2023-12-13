// Adding JavaScript form validation so user is alerted when they have inputted something incorrectly.

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("contactForm");
    const submitButton = document.getElementById("submit");

    submitButton.addEventListener("click", function(event) {
        event.preventDefault();

        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const subject = document.getElementById("subject").value;
        const message = document.getElementById("message").value;

        // Perform basic validation
        if (!name || !email || !subject || !message) {
            alert("Please fill in all fields!");
            return;
        }

        // Check email format using regular expression
        const emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!email.match(emailFormat)) {
            alert("Please enter a valid email address!");
            return;
        }

        // Validation successful, you can submit the form
        form.submit();
    });
});