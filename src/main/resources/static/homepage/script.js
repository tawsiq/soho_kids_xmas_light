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


let currentSlide = 0;

// Fetch images from the server (replace with your API endpoint)
function fetchImages() {
    //Simulating AJAX call
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve([
                'image1.jpg',
                'image2.jpg',
                'image3.jpg',
                // Add more image URLs as needed
            ]);
        }, 1000);
    });
}

// Display the slides
async function showSlides() {
    const slides = await fetchImages();

    // Create HTML for slides
    const slideshowContainer = document.querySelector('.slideshow-container');
    slides.forEach((imageUrl, index) => {
        const slide = document.createElement('div');
        slide.className = 'mySlides';
        slide.innerHTML = 'img src"${imageUrl}" style="width:100%">';
        slideshowContainer.appendChild(slide);

        // Create indicator dots
        const dotContainer = document.querySelector('.dot-container');
        const dot = document.createElement('span');
        dot.className = 'dot';
        dot.onclick = () => currentSlide = index;
        dotContainer.appendChild(dot);
    });

    // Show the first slide
    showCurrentSlide(currentSlide);
}

    // Display the current slide
    function showCurrentSlide(index) {
    const slides = document.querySelectorAll('.mySlides');
    const dots = document.querySelectorAll('.dot');

    if (index >= slides.length) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = slides.length - 1;
    }

    slides.forEach((slide) => (slide.style.display = 'none'));
    dots.forEach((dot) => (dot.className = 'dot'));

    slides[currentSlide].style.display = 'block';
    dots[currentSlide].className += ' active';
    }

    // Change slide on button click
    function changeSlide(n) {
    showCurrentSlide((currentSlide += n));
    }

    // Initialize the slideshow
showSlides();