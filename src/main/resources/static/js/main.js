window.onload = function(){
fetchLocations();
}

function fetchLocations(){
    fetch("/products")
        .then(function(response){
        return response.json();
        })
        .then(function(jsonData){
        console.log(jsonData);
        showTable(jsonData);
        });
}

function showTable(jsonData){
var table = document.getElementById("products-table");
    table.innerHTML = "";
for (var i = 0; i <jsonData.length; i++){
    var td = document.createElement("td");
    var codeTr = document.createElement("tr");
    codeTd.innerHTML = jsonData[i].code;
    td.appendChild(codeTr);
    var nameTr = document.createElement("tr");
    nameTr.innerHTML = "<a href='product.html?address=" + jsonData[i].address + "'>" + jsonData[i].name + "</a>";
    td.appendChild(nameTr);
    var manufactureTr = document.createElement("tr");
      manufactureTr.innerHTML = jsonData[i].manufacture;
      td.appendChild(manufactureTr);
    var priceTr = document.createElement("tr");
        priceTr.innerHTML = jsonData[i].price;
        td.appendChild(priceTr);
        
    table.appendChild(td);
    }
}


    /* var createFrom = document.querySelector('#products-form');
       createFrom.onsubmit = handleCreateFormSubmit; */