document.addEventListener('DOMContentLoaded', function () {

    <!-- List of Image URLs -->

    var imageUrls = [
        '../static/homepage content/lights_pic_1.png',
        '../static/homepage content/lights_pic_2.png',
        '../static/homepage content/lights_pic_3.png',
    ];

    var currentIndex = 0;

    function showCurrentSlide() {
        var slideshowContainer = document.querySelector('.slideshow-container');
        slideshowContainer.innerHTML = '<div class="mySlides">' +
            '<img src="' + imageUrls[currentIndex] + '" alt="Slide ' + (currentIndex + 1) + '">' +
            '</div>';
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % imageUrls.length;
        showCurrentSlide();
    }

    showCurrentSlide();

    setInterval(nextSlide, 5000);

});

