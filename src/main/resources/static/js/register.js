function handleFormSubmit() {
var request = {
"username" : document.forms["form-register"].username.value,
"email" : document.forms["form-register"].email.value,
"password" : document.forms["form-register"].password.value
}

fetch("/register",{
         method: "POST",
         body: JSON.stringify(request),
         headers:{
         "Content-type": "application/json"
         }
         })
        .then(function (response) {
            alert("Succes!");
        });
        return false;
}