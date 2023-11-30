$(document).ready(function () {
    // Fetch drawing submissions via AJAX
    $.ajax({
        url: 'getDrawings.php' , // Replace with your server-side script
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                // Populate slides with drawing submissions
                for (var i = 0; i < data.length; i++) {
                    $('slideshow-container').append('<div class="mySlides">' +
                      '<img src="' + data[i].imageURL + '" alt="Drawing ' + (i + 1) + '">' +
                      '<div class="text">' + data[i].caption + '</div>' +
                      '</div>');
                }

                // Start the slideshow
                showSlides(1);
            } else {
                console.error('No drawing submissions found.');
            }
        },
        error: function (xhr, status, error) {
            console.error('AJAX error:', status, error);
        }
    });
});

var slideIndex = 1;

function showSlides(n) {
    var slides = $('.mySlides');
    if (n > slides.length) {
        slideIndex = 1;
    }
    if (n < 1) {
        slideIndex = slides.length;
    }
    slides.hide();
    $(slides[slideIndex - 1]).show();
}

setInterval(function () {
    slideIndex++;
    showSlides(slideIndex);
}, 5000); // Change slide every 5 seconds