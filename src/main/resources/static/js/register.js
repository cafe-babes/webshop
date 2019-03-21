function handleFormSubmit() {
var request = {
"username" : document.forms["form-register"].username.value,
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
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            listProducts(jsonData);
        });
}