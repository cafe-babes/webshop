$.getJSON("/user-role", json => {

    let role = json.role || 'VISITOR';
    let name = json.userName;

    var nav = document.querySelector('#basket');
    var parent = nav.parentNode;
    var helper = document.createElement('div');

    helper.innerHTML = `<li class="nav-item active" id="home">
                            <a class="nav-link" href="/">
                                <i class="fas fa-home fa-lg"> </i>
                            </a>
                        </li>`;

    if (role == 'ROLE_ADMIN') {
        helper.innerHTML +=
            `<div class="btn-group">
                <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin menü<span
                        class="sr-only">Toggle Dropdown</span>
                </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/adminproducts.html">Termékek adminisztrációja</a>
                <a class="dropdown-item" href="/categories.html">Kategóriák adminisztrációja</a>
                <a class="dropdown-item" href="/users.html">Felhasználók adminisztrációja</a>
                <a class="dropdown-item" href="/orders.html">Rendelések adminisztrációja</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/dashboard.html">Dashboard</a>
                <a class="dropdown-item" href="/reports.html">Riportok</a>
            </div>
        </div>`
    }

    if (role == 'VISITOR') {
        helper.innerHTML +=
            `<li class="nav-item active" id="sign-up">
            <a class="nav-link" href="/register.html">Regisztráció</a>
        </li>
        <li class="nav-item active" id="login">
            <a class="nav-link" href="/login.html">Bejelentkezés</a>
        </li>`
    } else {
        helper.innerHTML +=
            `<div class="btn-group">
            <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Üdvözlünk ${name}!<span
                    class="sr-only">Toggle Dropdown</span>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/myorders.html">Rendeléseim</a>
                <a class="dropdown-item" href="/myprofile.html">Saját adatlap</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/logout">Kijelentkezés</a>
            </div>
        </div>`
    }

    while (helper.firstChild) {
        parent.insertBefore(helper.firstChild, nav);
    }
});

basketRefresh();

function basketRefresh() {
$.getJSON("/basket", json => {
var sum = 0;
for(var i = 0; i<json.length; i++) {
    if(json[i].amount)
        sum += json[i].amount;
}
document.querySelector('#cartCount').innerHTML = sum;
});
}
