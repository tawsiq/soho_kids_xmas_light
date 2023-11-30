document.addEventListener("DOMContentLoaded", function() {
    const expandBtns = document.querySelectorAll(".expand-btn");

    expandBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            const yearContent = this.parentElement.nextElementSibling;
            if (yearContent.style.display === "none" || yearContent.style.display === "") {
                yearContent.style.display = "block";
            } else {
                yearContent.style.display = "none";
            }
        });
    });

    const likeBtns = document.querySelectorAll(".like-btn");
    const likeCounters = document.querySelectorAll(".like-counter");
    const postCommentBtns = document.querySelectorAll(".post-comment-btn");

    likeBtns.forEach((btn, index) => {
        btn.addEventListener("click", function() {
            let currentLikes = parseInt(likeCounters[index].innerText);
            currentLikes++;
            likeCounters[index].innerText = `${currentLikes} ${currentLikes === 1 ? 'Like' : 'Likes'}`;
        });
    });

    postCommentBtns.forEach((btn) => {
        btn.addEventListener("click", function() {
            const commentSection = this.parentElement;
            const commentInput = commentSection.querySelector('.comment-input');
            const commentsList = commentSection.querySelector('.comments-list');

            const commentText = commentInput.value.trim();
            if (commentText !== '') {
                const newComment = document.createElement('div');
                newComment.classList.add('comment');
                newComment.innerText = commentText;
                commentsList.appendChild(newComment);
                commentInput.value = '';
            }
        });
    });
});
