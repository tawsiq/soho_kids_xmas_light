

function sendRating(){
    const raterName = document.forms["submitRating"]["raterName"].value;
    const commentText = document.forms["submitRating"]["commentText"].value;
    // const liked = document.forms["submitRating"]["liked"].checked; // Commented this out because rating no longer contains this field. Also removed from params below

    // This'll make up the POST message content. Notice the form is a key-value pair.
    let params = `raterName=${raterName}&commentText=${commentText}`;
    let dynamicFormAction = document.getElementById("submitRating").getAttribute("data-action");
    // Initiate a blank message, open its route, and label it with a header. Form's action goes here.
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", dynamicFormAction, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // Upon a message state change, execute the following function:
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            // Response is logged in the console
            console.log(xhttp.responseText);
            fetchComments();
            // fetchLikes(); // on form submission to update its number too.
        } else {
            // If response failed in any way, log the error into the console.
            console.error(xhttp.statusText);
        }
    };
    // Send the form data to the server
    // + return false to intercept standard processing & prevent page reload.
    xhttp.send(params);
    return false;
}
function fetchComments() {
    // Asynchronous request to get updated comments
    const commentsContainer = document.querySelector('.comment-section');
    //Obtaining endpoint defined by Thymeleaf in the container:
    const commentsEndpoint = commentsContainer.getAttribute("data-commentsEndpoint")
    fetch(commentsEndpoint)
        .then(response => response.text())
        .then(data => {
            // Replaces the existing comments section with the updated comments HTML, defined in the controller with this endpoint.
            commentsContainer.innerHTML = data;
        })
        .catch(error => {
            console.error('Error fetching comments:', error);
        });
}
function fetchLikes() {
    // Asynchronous request to get updated comments
    const likesContainer = document.getElementById("likeCount")
    //Obtaining endpoint defined by Thymeleaf in the container:
    const likesEndpoint = likesContainer.getAttribute("data-likesEndpoint")

    fetch(likesEndpoint)
        .then(response => response.text())
        .then(data => {
            // Replaces the existing comments section with the updated comments HTML, defined in the controller with this endpoint.
            likesContainer.innerHTML = data;
        })
        .catch(error => {
            console.error('Error fetching likes:', error);
        });
}

function sendLikeUpdate(increment) {

    let sendLikeEP_WithoutIncrement = document.getElementById("liked").getAttribute("data-sendLikeEndpoint_withoutIncrement");
    let sendLikeEndPoint = sendLikeEP_WithoutIncrement + increment;

    fetch(sendLikeEndPoint, {
        method: 'POST',
        // Add any necessary headers or body if needed
    })
        .then(response => {
            if (response.ok) {
                fetchLikes(); // Fetch and update the likes count after liking the submission
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .catch(error => {
            console.error('Error liking submission:', error);
        });
}

// So that comments don't need to be directly passed by the controller the first time the URL is accessed.
// Filtered rating code & fetching like counter in GetMapping controller no longer needs to be repeated (see where it was commented out)


