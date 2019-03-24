$.getJSON("/user-role", json => {

    let role = json.role || 'VISITOR';
    let name = json.userName;

    document.querySelector('nav').innerHTML =
        `<a class="navbar-brand navbar-inverse" href="/">CafeBabes</a>
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
            <a class="nav-link" href="/">Home</a>
        </li>`;

    if (role == 'ADMIN') {
        navFill.innerHTML +=
            `<div class="btn-group">
            <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin menü<span
                    class="sr-only">Toggle Dropdown</span>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/adminproducts.html">Termékek adminisztrációja</a>
                <a class="dropdown-item" href="/users.html">Felhasználók adminisztrációja</a>
                <a class="dropdown-item" href="/orders.html">Rendelések adminisztrációja</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/dashboard.html">Dashboard</a>
            </div>
        </div>`
    }

    if (role == 'VISITOR') {
        navFill.innerHTML +=
            `<li class="nav-item active" id="sign-up">
            <a class="nav-link" href="/register.html">Sign Up</a>
        </li>
        <li class="nav-item active" id="login">
            <a class="nav-link" href="/login">Log in</a>
        </li>`
    } else {
        navFill.innerHTML +=
            `<div class="btn-group">
            <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Üdvözlünk ${name}!<span
                    class="sr-only">Toggle Dropdown</span>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/orders.html">Rendeléseim</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/logout">Log out</a>
            </div>
        </div>`
    }

    navFill.innerHTML +=
        `<li class="nav-item active" id="basket">
            <a class="nav-link" href="basket.html"><i class="fas fa-shopping-cart"></i></a>
        </li>`;
});
