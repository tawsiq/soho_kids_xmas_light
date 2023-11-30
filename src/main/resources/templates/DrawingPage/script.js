document.addEventListener("DOMContentLoaded", function() {
    const expandBtns = document.querySelectorAll(".expand-btn");

    expandBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            const yearContent = this.parentElement.nextElementSibling;
            yearContent.style.display = yearContent.style.display === "none" ? "block" : "none";
        });
    });

    const likeBtns = document.querySelectorAll(".like-btn");
    const commentBtns = document.querySelectorAll(".comment-btn");

    likeBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            const likeCounter = this.nextElementSibling;
            let currentLikes = parseInt(likeCounter.innerText);
            currentLikes++;
            likeCounter.innerText = `${currentLikes} ${currentLikes === 1 ? 'Like' : 'Likes'}`;
        });
    });

    commentBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            const commentsSection = this.parentElement.querySelector('.comments-section');
            const commentInput = document.createElement('input');
            commentInput.setAttribute('type', 'text');
            commentInput.setAttribute('placeholder', 'Write a comment...');
            commentInput.classList.add('comment-input');
            commentsSection.appendChild(commentInput);

            commentInput.addEventListener('keypress', function(e) {
                if (e.key === 'Enter') {
                    const commentText = this.value.trim();
                    if (commentText !== '') {
                        const newComment = document.createElement('div');
                        newComment.classList.add('comment');
                        newComment.innerText = commentText;
                        commentsSection.appendChild(newComment);
                        this.value = '';
                    }
                }
            });
        });
    });
});
