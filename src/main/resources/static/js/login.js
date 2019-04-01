window.onload = function () {
    checkLoginError();
}

function checkLoginError(){
var url = window.location.href;
var inputErrorMessage = "Hibás név vagy jelszó!";
 if (url.split("?")[1] == "error"){
    document.querySelector("#main-message").innerHTML = inputErrorMessage;
 }
}

window.onkeydown = e => {
    if (!e) { var e = window.event; }
    e.preventDefault();
    if (e.keyCode == 13) { document.forms[0].submit(); }
}