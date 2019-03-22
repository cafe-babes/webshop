fetchBasket();

function fetchBasket(){
    var user_id = 1; //TODO
    var url = "/basket/" + user_id;
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

    for(var i = 0; i < jsonData.length; i++){
     console.log("ok");
        container.innerHTML +=
            `<div class="col-sm-5">
                <img class="surf-basket-img"
                    src="https://cdn10.bigcommerce.com/s-baaesh4/products/267/images/1427/blade-channels-deck__40048.1542307202.400.400.jpg?c=2"
                    alt="surf">
            </div>
            <div class="col-sm-7">
                <hr>
                <h2 id="name">${jsonData[i].name}</h2>
                <h3><span id="price">${jsonData[i].price}</span> Ft X </h3>
                <h2><span id="amount">${jsonData[i].amount}</span> db</h2>
                <br>
            </div>
            `;
        sum += jsonData[i].price;
    }
    document.getElementById("total-price").innerHTML = sum;
}

function emptyBasket() {
    var user_id = 1; //TODO
    var url = "/basket/" + user_id;
    fetch(url, {
    method: "DELETE"
    })
    .then(function(){
        fetchBasket();
    });
}

//TODO : megjeleníteni egy gombot: irány a kosár