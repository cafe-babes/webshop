window.onload = function() {
    fetchProduct();
}
console.log((new URL(document.location)).searchParams.get("address"));

 function fetchProduct(){
            var address = (new URL(document.location)).searchParams.get("address");
            var url = "/product/"+address;
                fetch(url)
                .then (function(response){
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

function addToBasket(){
            var address = (new URL(document.location)).searchParams.get("address");
            var url = "/basket/"+address;
            var request = {
            "userId" : 1
            }
            fetch(url,{
              method: "POST",
              body: JSON.stringify(request),
              headers:{
              "Content-type": "application/json"
              }
              })
              .then(function() {
              window.location.href = "basket.html";
              })
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