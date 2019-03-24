$.getJSON("/user-role", json => {

let role = json.role;
let name = json.userName;

document.querySelector('nav').innerHTML =
    `<a class="navbar-brand" href="/">CafeBabes</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
           aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
       <ul class="navbar-nav mr-auto" id="variable-navbar">
       </ul>
    </div>`;

let navFill = document.querySelector('#variable-navbar');

navFill.innerHTML +=
           `<li class="nav-item active" id="home">
               <a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
           </li>`;

if(role == 'ADMIN'){
navFill.innerHTML +=
        `<li class="nav-item active" id="admin">
            <a class="nav-link" href="/adminproducts.html">Termékek adminisztrációja<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active" id="users">
            <a class="nav-link" href="/users.html">Felhasználók adminisztrációja<span class="sr-only">(current)</span></a>
        </li>`
}

if(role == 'VISITOR') {
navFill.innerHTML +=
        `<li class="nav-item active" id="sign-up">
            <a class="nav-link" href="/register.html">Sign Up<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active" id="login">
            <a class="nav-link" href="/login">Log in<span class="sr-only">(current)</span></a>
        </li>`
} else {
navFill.innerHTML +=
        `<li class="nav-item active" id="logout">
            <a class="nav-link" href="/logout">Log out<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item" id="full-name">
            <a class="nav-link disabled">Üdvözlünk ${name}!<span class="sr-only">(current)</span></a>
        </li>`
}

navFill.innerHTML +=
      `<li class="nav-item active" id="basket">
           <a class="nav-link" href="basket.html"><i class="fas fa-shopping-cart"></i></a>
       </li>`;
});

