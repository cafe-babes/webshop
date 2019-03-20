window.onload = function () {
    fetchLocations();
}

function fetchLocations() {
    fetch("/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            showTable(jsonData);
        });
}

function showTable(jsonData) {
    var container = document.querySelector('#list-products');
    container.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var item = `<div class="col-md-4">
        <div class="card mb-4 box-shadow">
            <img class="card-img-top"
                src="https://cdn10.bigcommerce.com/s-baaesh4/products/267/images/1427/blade-channels-deck__40048.1542307202.400.400.jpg?c=2"
                alt="surfboard image">
            <div class="card-body">
                <p class="card-text">${jsonData[i].name}</p>
                <p class="card-text">${jsonData[i].manufacture}</p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button type="button" class="btn btn-lm btn-outline-secondary"><a href='product.html?address=${jsonData[i].address}'></a>Details</button>
                    </div>
                </div>
                <small class="text-muted">${jsonData[i].price}<span> Ft</span></small>
                <small class="text-muted">${jsonData[i].code}<span> Ft</span></small>
            </div>
        </div>
    </div>`;
        container.appendChild(item);
    }
}