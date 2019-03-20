window.onload = function () {
    fetchProducts();

  //  var createFrom = document.querySelector('#locations-form');
  //  createFrom.onsubmit = handleCreateFormSubmit;
}


function fetchProducts() {
    fetch("/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            showTable(jsonData);
        });
}

/*function handleCreateFormSubmit() {
    var name = document.querySelector("#name-input").value;
    var lat = parseFloat(document.querySelector('#lat-input').value);
    var lon = parseFloat(document.querySelector('#lon-input').value);
    var request = {
        "name": name,
        "lat": lat,
        "lon": lon
    };

    fetch("/api/locations", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                "Content-type": "application/json"
            }
        })
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData.status);
            if (jsonData.status == "OK") {
                document.querySelector("#name-input").value = "";
                document.querySelector("#lat-input").value = "";
                document.querySelector("#lon-input").value = "";
                fetchProducts();
                document.querySelector("#message-div").setAttribute("class", "alert alert-success")
            } else {
                document.querySelector("#message-div").setAttribute("class", "alert alert-danger")
            }
            document.querySelector("#message-div").innerHTML = jsonData.message;
        });
    return false;
}*/

function showTable(jsonData) {
    var table = document.querySelector("#adminproducts-table");
    table.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var tr = document.createElement("tr");
      
        var idTd = document.createElement("td");
        idTd.innerHTML = jsonData[i].id;
        tr.appendChild(idTd);
      
        var codeTd = document.createElement("td");
        codeTd.innerHTML = jsonData[i].code;
        tr.appendChild(codeTd);
       
        var addressTd = document.createElement("td");
        addressTd.innerHTML = jsonData[i].address;
        tr.appendChild(addressTd);
       
        var nameTd = document.createElement("td");
        nameTd.innerHTML = jsonData[i].name;
        tr.appendChild(nameTd);

        var manufactureTd = document.createElement("td");
        manufactureTd.innerHTML = jsonData[i].manufacture;
        tr.appendChild(manufactureTd);

        var priceTd = document.createElement("td");
        priceTd.innerHTML = jsonData[i].price;
        tr.appendChild(priceTd);

        var editButtonTd = document.createElement("td");
        var editButton = document.createElement("button");
        editButtonTd.appendChild(editButton);

        var deleteButtondTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        buttondTd.appendChild(deleteButton);

        editButton.innerHTML = "Edit";
        deleteButton.innerHTML = "Delete";
       /* deleteButton.onclick = deleteLocation;
        deleteButton["raw-data"] = jsonData[i];*/

        tr.appendChild(buttondTd);

        table.appendChild(tr);
    }
}