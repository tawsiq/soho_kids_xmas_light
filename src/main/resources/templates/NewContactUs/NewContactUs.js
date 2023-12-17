// // Adding JavaScript form validation so user is alerted when they have inputted something incorrectly.
//
// document.addEventListener("DOMContentLoaded", function() {
//     const form = document.getElementById("contactForm");
//     const submitButton = document.getElementById("submit");
//     const nameField = document.getElementById("name");
//     const emailField = document.getElementById("email");
//     const subjectField = document.getElementById("subject");
//     const messageField = document.getElementById("message");
//     const charCount = document.getElementById("charCount");
//
//     submitButton.addEventListener("click", function(event) {
//         event.preventDefault();
//
//         const name = nameField.value;
//         const email = emailField.value;
//         const subject = subjectField.value;
//         const message = messageField.value;
//
//         const emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//
//         if (!name.match(/^[a-zA-Z]+$/)) {
//             alert("Name should only contain alphabetic characters!");
//             return;
//         }
//
//         if (!email.match(emailFormat)) {
//             alert("Please enter a valid email address!");
//             return;
//         }
//
//         if (subject.length < 5 || subject.length > 50) {
//             alert("Subject should be between 5 and 50 characters!");
//             return;
//         }
//
//         if (message.length > 2500) {
//             alert("Message exceeds the character limit of 2500!");
//             return;
//         }
//
//         form.submit();
//     });
//
//     messageField.addEventListener("input", function() {
//         const messageLength = messageField.value.length;
//         charCount.textContent = `${messageLength} / 2500 characters`;
//     });
// });
