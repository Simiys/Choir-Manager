function registration() {
    let registration = {};
    registration.email = document.getElementById("email").value;
    registration.firstName = document.getElementById("firstName").value;
    registration.lastName = document.getElementById("lastName").value;
    registration.password = document.getElementById("password").value;
    registration.matchingPassword = document.getElementById("matching password").value;
    registration.isRegent = document.getElementById("regent").checked;

    let json = JSON.stringify(registration);
    console.log(registration);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/registr', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    let str = xhr.response;
    switch(str) {
        case 'emailError':
            document.getElementById("emailerror").removeAttribute("hidden");
        break;
        case 'fillException':
            document.getElementById("fieldserror").removeAttribute("hidden");
        break;
        case 'passwordError':
            document.getElementById("matchingerror").removeAttribute("hidden");
        break;
        case 'userIsPresent':
            document.getElementById("userispresent").removeAttribute("hidden");
        break;
        case "success":
        console.log("success")
    }

}
