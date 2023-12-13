// Listener for input events to adjust the textarea height
document.addEventListener('input', function (e) {
    if (e.target.tagName.toLowerCase() === 'textarea') {
        autoExpand(e.target);
    }
});

// Function to automatically expand the textarea based on its content
function autoExpand(textarea) {
    // Resets the textarea height to the minimum in case it has shrunk
    textarea.style.height = '1.5em';
    // Set height to scrollHeight (the full height of the content, even if it's not visible)
    textarea.style.height = textarea.scrollHeight + 'px';
}

