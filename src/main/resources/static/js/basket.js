fetchBasket();

function fetchBasket(){
    var url = "/basket";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData) {
        console.log(jsonData);
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
            `<div class="col-sm-7">
                <h2 id="name">${jsonData[i].name}</h2>
                <h3><span id="price">${jsonData[i].price}</span> Ft X </h3>
                <label for = "changeQuantity">Darab</label> <br>
                <input onclick = "changeSum(${jsonData[i].productId}, ${jsonData[i].pieces}, ${jsonData[i].price})"
                id = "changeQuantity${jsonData[i].productId}"
                type = "number" step = "1" value = ${jsonData[i].pieces} min="0" style = "width:40px;" > 
                <br><br>
                <button id="delete-one" type="button" class="btn btn-outline-secondary" onclick="getProductById('${jsonData[i].productId}')">Töröl</button>
                <br>
            </div>
            `;
            var quantity = document.querySelector(`#changeQuantity${jsonData[i].productId}`).value;
            
            sum += jsonData[i].price * quantity;
            
    }
    
    document.getElementById("total-price").innerHTML = sum;
    
}

function changeSum(productId, pieces, price){
    console.log("szeva")
   var previous_value = pieces;
    var sum = parseInt(document.getElementById("total-price").innerHTML);
    console.log(sum);
       var value = document.getElementById(`changeQuantity${productId}`).value;
       console.log('value',value);
    sum += (pieces * -price) + (value * price);
      /* if (previous_value > value) {
           sum -= parseInt(price);
           previous_value = value;
        } else if (previous_value < value) {
            sum += parseInt(price);
            previous_value = value;
       }*/
      // document.getElementById("total-price").innerHTML = sum;
       summarizer();

}

function summarizer(){
    var sum = 0;
    var productArr = document.querySelector('#list-product').children;
    for (const key in productArr) {
        if (productArr.hasOwnProperty(key)) {
            const element = productArr[key].children;
            var piece = parseInt(element[4].value);
            console.log(piece);
            var price = parseInt(element[1].firstElementChild.innerHTML);
            console.log(price)
            sum += piece * price;
        }
    }
    document.getElementById("total-price").innerHTML = sum;
}


function getProductById(productId){
fetch('/products/' + productId)
    .then(res => res.json())
    .then(data => { removeItemFromBasket(data) })
}

function removeItemFromBasket(product) {
      var url = "/basket/" + product.address;
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
           handleAddToOrders();
           basketRefresh();
       });
}

function handleAddToOrders(){
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
        window.location.href ="/succesfulorder.html";
        return response.json();
    })
}
