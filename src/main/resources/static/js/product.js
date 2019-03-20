window.onload = function() {
    fetchProduct();

// Kliens oldalról begépelődnek az adatok.
// create-form gombra kattintva elküldi a szervernek (JAVA-kapja meg előbb)
//    var createForm = document.getElementById("create-form");
//       //Ha a felhasználó submitolja a formot akkor ez a függvény hívódjon meg (olyan mint egy lambda kifejezés)
//       // a függvény nem kerül meghívásra csak akkor ha meg lesz nyomva a gomb.
//       createForm.onsubmit = handleCreateFormSubmit;
}

 function fetchProduct(){
            var address = (new URL(document.location)).searchParams.get("address");
            var url = "/product/"+address;
                fetch(url)
                .then (function(response){
                return response.json();
                })
                .then(function(jsonData) {
               console.log(jsonData);
               // a kapott adatokból újrakreáljuk a táblát
               showProduct(jsonData);
            });
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