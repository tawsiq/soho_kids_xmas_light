function sendRating(){
    const raterName = document.forms["submitRating"]["raterName"].value;
    const commentText = document.forms["submitRating"]["commentText"].value;
    const liked = document.forms["submitRating"]["liked"].checked;

    // This'll make up the POST message content. Notice the form is a key-value pair.
    let params = `raterName=${raterName}&commentText=${commentText}&liked=${liked}`;
    let dynamicFormAction = document.getElementById("submitRating").getAttribute("data-action");
    // Initiate a blank message, open its route, and label it with a header. Form's action goes here.
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", dynamicFormAction, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // Upon a message state change, execute the following function:
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            // Response is logged in the console
            console.log(xhttp.responseText);
            // fetchComments(); complete this later
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
