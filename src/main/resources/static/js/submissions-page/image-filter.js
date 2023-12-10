
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
        const images = document.querySelectorAll('.winner-submissions-flex-container .button.full-border, .submissions-flex-container .button.full-border');

        // This tags element before their classes affecting styling are removed in order to keep track of them. Ones that satisfy the filter will have their classes reinstated
        // logic is wrong here. I'll need to tag images in the html. I'll need to also obtain BOTH a list of images (done) AND a list of their containers - will just use another query selector.  
        images.forEach(image => {
                if (image.classList.contains('winner-submissions-flex-container')) {
                    image.classList.add('winnerTag');
                    image.classList.remove('winner-submissions-flex-container');
                } else {
                    // I have (hopefully) never assigned two elements with both classes queried
                    // image.classList.add('submissionTag'); // technically don't need this
                    image.classList.remove('submissions-flex-container');
                }
            }
        )

        images.forEach(image => {
            const imageYearGroup = image.dataset.year_group;
            const imageSubmissionYear = image.dataset.submission_year;


            if ((criteria === imageYearGroup) || (criteria === imageSubmissionYear)) {
                image.classList.remove('hidden');

                if (image.classList.contains('winnerTag')) {
                    image.classList.add('winner-submissions-flex-container');
                } else {
                    image.classList.add('submissions-flex-container');
                }

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
