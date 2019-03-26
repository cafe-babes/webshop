var dropdown = document.querySelector("#size-select");
dropdown.onchange = function(){
    var size = dropdown[dropdown.selectedIndex].value;
    window.location.href=`/index.html?start=0&size=${size}`;
};

if((new URL(document.location)).searchParams.get("start") == undefined) {
    fetchProducts();
} else {
    fetchProductsWithStartAndSize();
    dropdown.value = (new URL(document.location)).searchParams.get("size");
}

function fetchProducts() {
    fetch("/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            listProducts(jsonData);
        });
}

function fetchProductsWithStartAndSize() {
    var start = (new URL(document.location)).searchParams.get("start") || "0";
    var size = (new URL(document.location)).searchParams.get("size");
    fetch("/products/"+start+"/"+size)
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            listProducts(jsonData);
        });
    getButtons(size);
}

function getButtons(size) {
    var buttons = document.querySelector('#page-change');
    fetch("/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            buttons.innerHTML = '';
            for(let i = 0; i < jsonData.length/size ;i++) {
                buttons.innerHTML += `<a href='index.html?start=${i*size}&size=${size}'><button type="button" class="btn btn-lm btn-outline-secondary">${i+1}</button></a>`;
            }
        });
}

function listProducts(jsonData) {
    var container = document.querySelector('#list-products');
    container.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        container.innerHTML += `<div class="col-md-4">
        <div class="card mb-4 box-shadow">
            <!--<img class="card-img-top"
                src="https://cdn10.bigcommerce.com/s-baaesh4/products/267/images/1427/blade-channels-deck__40048.1542307202.400.400.jpg?c=2"
                alt="surfboard image">-->
            <div class="card-body">
                <p class="card-text">${jsonData[i].name}</p>
                <p class="card-text">${jsonData[i].manufacture}</p>
                <div class="d-flex justify-content-between align-items-center">
                   <a href='product.html?address=${jsonData[i].address}'>
                    <div class="btn-group">
                         <button type="button" class="btn btn-lm btn-outline-secondary">Details</button>
                    </div>
                   </a>
                </div>
                <small class="text-muted">${jsonData[i].price}<span> Ft</span></small>
                <small class="text-muted">${jsonData[i].code}</small>
            </div>
        </div>
        </div>`;
    }
}