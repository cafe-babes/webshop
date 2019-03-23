function handleFormSubmit() {
var form = document.forms["form-register"];
var request = {
"name" : form.name.value,
"userName" : form.username.value,
"password" : form.password.value
}

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
         var div = document.querySelector('#response-box');
         if(json.resultStatusEnum=="OK") {
            div.setAttribute("class", "alert alert-success")
            div.innerHTML = "Sikeres regisztráció! Átirányítunk...";
            setTimeout(function(){
            window.location.href = "/login";
            }, 1000);
         } else {
            div.innerHTML = "Ez a felhasználónév már foglalt!";
            div.setAttribute("class", "alert alert-danger");
            form.name.focus();
         }});

return false;
}