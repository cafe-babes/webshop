window.onload = function() {
    fetchProduct();
}
console.log((new URL(document.location)).searchParams.get("address"));

$.getJSON("/user-role", json => {
if(json.role != 'VISITOR')
    document.querySelector('#addToBasketButton').hidden = false;
});

function fetchProduct(){
var address = (new URL(document.location)).searchParams.get("address");
var url = "/product/"+address;
    fetch(url)
    .then(function(response){
    return response.json();
    })
    .then(function(jsonData) {
        if (jsonData.status == 'NOT_OK') {
           showProductNotFound(jsonData);
        }else{
           showProduct(jsonData);
        }
});
    return false;
}

function handleAddToBasketButton(){
    var address = (new URL(document.location)).searchParams.get("address");
    var url = "/basket";
    fetch(url,{
       method: "GET"
    })
    .then(function(response){
    console.log(response);
        if(response.ok==false)
            window.location.href = "/login";
        else
            return response.json();
    })
    .then(function(jsonData) {
        for(var i = 0; i < jsonData.length; i++) {
            if(jsonData[i].address==address) {
                alert("A termék már a kosárban van!");
                return;
            }
        }
        addToBasket();
    });
}

function addToBasket(){
    var quantity = document.querySelector("#inputQuantity").value;
    var address = (new URL(document.location)).searchParams.get("address");
    var url = "/basket/"+address;

    var request = {
        "pieces": quantity
    }

    fetch(url,{
      method: "POST",
      body: JSON.stringify(request),
       headers: {
           "Content-type": "application/json"
       }
      })
      .then(function(response){
        return response.json();
      })
      .then(function(jsonData){
        if(jsonData!=-1) {
            addGoToBasketButton();
            basketRefresh();
        } else
            alert("This product has already been added!")
      })
}

function addGoToBasketButton(){
    var container = document.querySelector("#basketButton");
    container.innerHTML = "";
    container.innerHTML +=
    `<a href="basket.html"><button type="button" class="btn btn-primary btn-lg">Irány a kosár</button></a>`;
}


function showProduct(jsonData) {

    var code = document.getElementById("code");
    var name = document.getElementById("name");
    var manufacture = document.getElementById("manufacturer");
    var price = document.getElementById("price");

    code.innerHTML = jsonData.code;
    name.innerHTML = jsonData.name;
    manufacture.innerHTML = jsonData.manufacture;
    price.innerHTML = jsonData.price;

}

function showProductNotFound(jsonData){
    var productText = document.getElementById("product-text");
    var picture = document.getElementById("picture");
    productText.innerHTML = "Sajnálom, ilyen termékkel nem rendelkezünk !";
    picture.innerHTML = "";
}