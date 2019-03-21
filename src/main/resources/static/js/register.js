function handleFormSubmit() {
var request = {
"name" : document.forms["form-register"].name.value,
"username" : document.forms["form-register"].username.value,
"password" : document.forms["form-register"].password.value
}

fetch("/users",{
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