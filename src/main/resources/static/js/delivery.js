fetchUserDeliveries()

function fetchUserDeliveries(){
     var url = "/delivery";
     fetch(url)
         .then(function (response) {
             return response.json();
         })
         .then(function (jsonData) {
             console.log(jsonData);
            showdeliveries(jsonData);
         });
}

function showdeliveries(jsonData){
    var deliveryDiv = document.querySelector('#deliveryDiv');
    for (var i = 0; i < jsonData.length; i ++){
        var div = document.createElement('div');
        div["raw-data"] = jsonData[i]
        div.setAttribute('class', 'delivery-box');
        div.setAttribute('id', `div${i}`);
        div.innerHTML = `<p>${jsonData[i].deliveryAddress}</p><br><input type='radio' id="input${i}" class='radio-input' name="delivery" >`

        deliveryDiv.appendChild(div);
    }
    var toStore = document.createElement('div');
    toStore.setAttribute('class', 'delivery-box');
    toStore.innerHTML = `<p>Kiszállítás az üzletbe</p><br><input type='radio' name="delivery">`
    deliveryDiv.appendChild(toStore);
}

function addNewAddress(){
    var div = document.querySelector('#new-address');
    div.innerHTML = `<label for="inputNewAddress">Szállítási cím megadása (ország, város, utca, házszám, emelet, irányítószám)</label>
     <input type="text" id="inputNewAddress" onfocus=checkoutRadios() max-length=255 style="width:600px;">`;
}

function addToOrders(address){
   // var newAddress = document.querySelector('#inputNewAddress').value;
    var request = {
        "deliveryAddress":address
    }

     var url = "/myorders";
     fetch(url, {
             method: "POST",
             body: JSON.stringify(request),
             headers: {
               "Content-type": "application/json"
                 }
         })
         .then(function (response) {
             console.log(response);
            // window.location.href = "/succesfulorder.html";
             return response.json();
         }).then(function (jsonData) {
             console.log(jsonData.message);
         })
}

function checkAddress(){
    var deliveryDiv = document.querySelector('#deliveryDiv');
    var newAddress = document.querySelector('#inputNewAddress');
    for (var i = 0; i < deliveryDiv.children.length; i++){
        if (deliveryDiv.children[i].children[2].checked == true){
            console.log(deliveryDiv.children[i].children[0].innerHTML)
            addToOrders(deliveryDiv.children[i].children[0].innerHTML);
        } else if (newAddress.value != null){
            addToOrders(newAddress);
        }
    }
}

function checkoutRadios(){
    var deliveryDiv = document.querySelector('#deliveryDiv');
     for (var i = 0; i < deliveryDiv.children.length; i++) {
         deliveryDiv.children[i].children[2].checked = false;
         }
}