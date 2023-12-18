                                                 // Sign-up Form
function submitForm() {
    const schoolName = document.getElementById('schoolName').value;
    const contactNumber = document.getElementById('contactNumber').value;
    const email = document.getElementById('email').value;

    if (validateEmail(email)) {
        console.log('Form submitted successfully!');
        console.log('School Name:', schoolName);
        console.log('Contact Number:', contactNumber);
        console.log('Email:', email);
    } else {
        alert('Invalid email address. Please enter a valid email.');
    }
}