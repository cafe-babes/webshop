window.onload() = function(){
    fetchBasket();
}

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

    var name = document.getElementById("name");
    var price = parseInt(document.getElementById("price").value, 10);
    var amount = parseInt(document.getElementById("amount").value, 10);

    name.innerHTML = jsonData.name;
    price.innerHTML = jsonData.price;
    amount.innerHTML = jsonData.amount;
    document.getElementById("total-price").value = price * amount;

}