function handleFormSubmit() {
var form = document.forms["form-register"];
var request = {
"name" : form.name.value,
"userName" : form.username.value,
"password" : form.password.value
}

$(document).ready(function() {
    $('#password-progress-bar').strengthMeter('progressBar', {
        container: $('#password-progress-bar-container')
    });
});

fetch("/users",{
         method: "POST",
         body: JSON.stringify(request),
         headers:{
         "Content-type": "application/json"
         }
         })
         .then(function(response){
            return response.json();
         })
         .then(function(json) {
         console.log(json);
         var div = document.querySelector('#response-box');
         if(json.resultStatusEnum=="OK") {
            div.setAttribute("class", "alert alert-success")
            div.innerHTML = "Sikeres regisztráció! Átirányítunk...";
            setTimeout(function(){
            window.location.href = "/login";
            }, 1000);
         } else {
            div.innerHTML = json.message;
            div.setAttribute("class", "alert alert-danger");
            form.name.focus();
         }});

return false;
}