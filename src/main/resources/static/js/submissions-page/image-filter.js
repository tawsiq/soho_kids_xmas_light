
document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.button');

    buttons.forEach(button => {
        button.addEventListener('click', function () {
            const filterCriteria = this.dataset.filter;

            // Filter the images based on the criteria
            filterImages(filterCriteria);

            // Handle button active state
            handleButtonState(buttons, this);
        });
    });

    function filterImages(criteria) {
        const images = document.querySelectorAll('.winner-submissions-flex-container div, .submissions-flex-container div');

        images.forEach(image => {
            const imageYearGroup = image.dataset.yearGroup;
            const imageSubmissionYear = image.dataset.submissionYear;

            if ((criteria === imageYearGroup) || (criteria === imageSubmissionYear)) {
                image.classList.remove('hidden');
            } else {
                image.classList.add('hidden');
                console.log(`Filtered out: ${imageYearGroup} - ${imageSubmissionYear}`);
            }
        });
    }

    function handleButtonState(allButtons, activeButton) {
        allButtons.forEach(button => {
            button.classList.remove('active'); // Remove active state from all buttons
        });

        activeButton.classList.add('active'); // Add active state to the clicked button
    }
});
