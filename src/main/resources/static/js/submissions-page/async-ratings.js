
function sendRating(){
    const raterName = document.forms["submitRating"]["raterName"].value;
    const commentText = document.forms["submitRating"]["commentText"].value;
    // const liked = document.forms["submitRating"]["liked"].checked; // Commented this out because rating no longer contains this field. Also removed from params below

    // This'll make up the POST message content for the form's input name & comment text.
    let params = `raterName=${raterName}&commentText=${commentText}`;
    // Form action retrieved via data attribute so that th: can still be used. To dynamically render the id. Alternatively I could have just extracted the url.
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
            // And only fetch comments. Likes will now be fetched separately.
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
    // Asynchronous request to get updated comments. The fetch method was a lot easier to implement that the GET / POST method above for this.
    // The controllers had to be changed to get this to work, but it should still be readable.
    const commentsContainer = document.querySelector('.comment-section');
    // Obtaining endpoint defined by Thymeleaf in the container. See HTML & Controller endpoint pair.:
    const commentsEndpoint = commentsContainer.getAttribute("data-commentsEndpoint")
    fetch(commentsEndpoint)
        .then(response => response.text())
        .then(data => {
            // Replaces the existing comments section with the updated comments HTML defined in the controller with this endpoint.
            commentsContainer.innerHTML = data;
        })
        .catch(error => {
            console.error('Error fetching comments:', error);
        });
}
function fetchLikes() {
    // Asynchronous request to get updated likes. Mirrors the one above for comments. This is kept separate for clarity and ease of endpoint routing. Not very dry tho.
    const likesContainer = document.getElementById("likeCount")
    // Again obtaining endpoint defined by Thymeleaf in the container:
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
// This one is called within the html itself, where the onchange attribute is used with 1 or -1.
function sendLikeUpdate(increment) {
    // Again very similar, except this one's a fetch post request. I tried to follow the blueprint of lectures like I did for sendRating,
    //  but it didn't end up working since we aren't specifically sending in a form tied to an object.
    let sendLikeEP_WithoutIncrement = document.getElementById("liked").getAttribute("data-sendLikeEndpoint_withoutIncrement");
    let sendLikeEndPoint = sendLikeEP_WithoutIncrement + increment;

    fetch(sendLikeEndPoint, {
        method: 'POST',
        // Didn't really need content headers here, although not including them is probably bad practise.
        // I included them on the sendRating function because that's how the lectures did it, but I didn't end up using those either.
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
