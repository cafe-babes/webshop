fetchBasket();

function fetchBasket(){
    var url = "/basket";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData) {
        showBasket(jsonData);
    });
}

function showBasket(jsonData){
    var container = document.getElementById("list-product");
    container.innerHTML = "";

    var sum = 0;
    if(jsonData.length === 0){
            console.log("valami");
            container.innerHTML += `<h2>A kosár tartalma üres</h2>`;
        } else {
    for(var i = 0; i < jsonData.length; i++){
     console.log("ok");
        container.innerHTML +=
            `<div class="col-sm-5">
                <img class="surf-basket-img"
                    src="https://cdn10.bigcommerce.com/s-baaesh4/products/267/images/1427/blade-channels-deck__40048.1542307202.400.400.jpg?c=2"
                    alt="surf">
            </div>
            <div class="col-sm-7">

                <h2 id="name">${jsonData[i].name}</h2>
                <h3><span id="price">${jsonData[i].price}</span> Ft X </h3>
                <h2><span id="amount">${jsonData[i].amount}</span> db</h2>
                <button id="delete-one" type="button" class="btn btn-outline-secondary" onclick="deleteOneItem()">Töröl</button>
                <br>
            </div>
            `;
        sum += jsonData[i].price;
        document.querySelector("#delete-one")["raw-data"] = jsonData[i];
    }
    console.log("más");
    container.innerHTML += `<h2 id="total">Teljes összeg: <span id="total-price"></span> Ft</h2>`;
    document.getElementById("total-price").innerHTML = sum;
    }
}

function deleteOneItem(){
    address = document.querySelector("#delete-one")["raw-data"].address;
    console.log(address);
    var url = "/basket/" + address;
    console.log(url)
    return fetch(url, {
        method: "DELETE"
    })
    .then(function(response){
        fetchBasket();
    })
}

function emptyBasket() {
    var url = "/basket";
    fetch(url, {
    method: "DELETE"
    })
    .then(function(){
        fetchBasket();
    });
}
