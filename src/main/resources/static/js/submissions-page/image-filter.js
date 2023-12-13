
document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.button');

    buttons.forEach(button => {
        button.addEventListener('click', function () {
            const filterCriteria = this.dataset.filter;

            filterImages(filterCriteria);

            handleButtonState(buttons, this);
        });
    });

    function filterImages(criteria) {
        const images = document.querySelectorAll('.winner-submissions-flex-container .button.full-border, .submissions-flex-container .button.full-border');

        // Elements are tagged within the html to show which flex container they fall under, just incase it's needed.
        images.forEach(image => {
            const imageYearGroup = image.dataset.year_group;
            const imageSubmissionYear = image.dataset.submission_year;

            if ((criteria === imageYearGroup) || (criteria === imageSubmissionYear)) {
                image.classList.remove('filtered-out');

            } else {
                image.classList.add('filtered-out');
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
