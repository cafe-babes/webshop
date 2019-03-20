window.onload = function() {
    fetchProduct();
}

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
               // a kapott adatokból újrakreáljuk a táblát
            });
                return false;
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