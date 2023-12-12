function validateEmail() {
    var email = document.getElementById("email").value;
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    return true;
}

document.addEventListener('DOMContentLoaded', function () {
    var mediaLinks = document.querySelectorAll('.media-links a');

    mediaLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            link.style.color = 'red';
        });
    });
});