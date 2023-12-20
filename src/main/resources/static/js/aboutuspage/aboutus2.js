
//Function to validate email address the user is entering to ensure it follows the correct format
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
    // Toggle visibility of the media section
    var toggleMediaButton = document.getElementById('toggleMedia');
    var mediaSection = document.getElementById('media');

    if (toggleMediaButton && mediaSection) {
        toggleMediaButton.addEventListener('click', function () {
            mediaSection.classList.toggle('hidden');
        });
    }

});