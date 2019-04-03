fetchBasket();

function fetchImage(productId) {
    var productImage = document.getElementById(`img-${productId}`);
    console.log(productImage);

    fetch('/image/' + productId)
    .then(function(response) {
          if(response.status == 200) {
            return response.blob();
          }
      productImage.src = 'https://pbs.twimg.com/profile_images/758084549821730820/_HYHtD8F_400x400.jpg';
    })
    .then(function(myBlob) {
      var objectURL = URL.createObjectURL(myBlob);
      productImage.src = objectURL;
    });
}

function fetchBasket(){
    var url = "/basket";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData) {
        var productId = jsonData;
        console.log(jsonData);
        showBasket(jsonData);
        fetchImage(productId);
    });
}

function showBasket(jsonData){
    var container = document.getElementById("list-product");
    container.innerHTML = "";

    var sum = 0;

    for(var i = 0; i < jsonData.length; i++){
        console.log(`${jsonData[i].address}`);
        container.innerHTML +=
            `<div class="container">
            <div class="col-sm-7 ad-left" id="${jsonData[i].address}">
                <h2 id="name">${jsonData[i].name}</h2>
                <h3><span id="price-${jsonData[i].address}">${jsonData[i].price}</span> Ft</h3>
                <label for = "changeQuantity">Darab</label> <br>
                <button type="button" class="btn btn-outline-primary" id="minus" onclick="minus('${jsonData[i].address}')">-</button><span id = "changeQuantity-${jsonData[i].address}">${jsonData[i].pieces}</span>
                <button type="button" class="btn btn-outline-primary" id="minus" onclick="plus('${jsonData[i].address}')">+</button>
                <br><br>
                <button id="delete-one" type="button" class="btn btn-outline-secondary" onclick="removeItemFromBasket('${jsonData[i].address}')">Töröl</button>
                <br>
            </div>
            <div>
            <img class="card-img-top basketImage" id='img-${jsonData[i].productId}' alt="surfboard image">
            </div>
            <hr>
            </div>

            `;
        sum += jsonData[i].price * jsonData[i].pieces;
        fetchImage(jsonData[i].productId);
    }
    
    document.getElementById("total-price").innerHTML = sum;
    
}

function minus(address) {
    var pieceSpan = document.querySelector(`#changeQuantity-${address}`);
    if(pieceSpan.innerHTML <= 1)
        removeItemFromBasket(address);
    pieceSpan.innerHTML = parseInt(pieceSpan.innerText) - 1;
    updatePieces(address, parseInt(pieceSpan.innerText));
    summarizer(address);
    basketRefresh();
}

function plus(address) {
    var pieceSpan = document.querySelector(`#changeQuantity-${address}`);
    pieceSpan.innerHTML = parseInt(pieceSpan.innerText) + 1;
    updatePieces(address, parseInt(pieceSpan.innerText));
    summarizer(address);
    basketRefresh();
}

function summarizer(address){
    var sum = 0;
    var productArr = document.querySelector('#list-product').children;
    for (let i = 0; i < productArr.length; i++) {
        const element = productArr[i];
        var piece = parseInt(element.querySelector("[id^='changeQuantity-']").innerText);
        console.log(piece);
        var price = parseInt(element.querySelector("[id^='price-']").innerText);
        console.log(price)
        sum += piece * price;
    }
    document.getElementById("total-price").innerHTML = sum;
}

function updatePieces(address, piece) {
    var request = {
        "pieces": piece,
        "address": address
    }

    fetch("/basket", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
            "Content-type": "application/json"
        }
    })
    .then(function (response) {
        return response;
    })
}

function removeItemFromBasket(product_address) {
      var url = "/basket/" + product_address;
      console.log(url)
      return fetch(url, {
              method: "DELETE"
          })
          .then(function (response) {
              fetchBasket();
              basketRefresh();
          })
}

function emptyBasket() {
    var url = "/basket";
    fetch(url, {
    method: "DELETE"
    })
    .then(function(){
        fetchBasket();
        basketRefresh();
    });
}

function checkIfEmpty(){
       var url = "/basket";
       fetch(url)
       .then(function(response){
           return response.json();
       })
       .then(function(jsonData) {
         //  console.log(jsonData);
           if(jsonData.length == 0){
                alert("a kosár tartalma üres");
                return;
           }
           window.location.href = "/delivery.html";
           //handleAddToOrders();
           basketRefresh();
       });
}

/*function handleAddToOrders(){
    var url = "/myorders";
    fetch(url,{
        method: "GET"
    })
    .then(function(response) {
        console.log(response);
        return response.json();
    })
    .then(function(){
        addToOrders();
    });
}

function addToOrders(){
    console.log("addToOrders ok")
    var url = "/myorders";
    fetch(url,{
        method: "POST",
    })
    .then(function(response){
        console.log(response);
        window.location.href ="/delivery.html";
        return response.json();
    })
}*/
