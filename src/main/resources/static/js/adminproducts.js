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
        var idTdId = 'idTd' + i;
        idTd.setAttribute('id', idTdId);
        tr.appendChild(idTd);
      
        var codeTd = document.createElement("td");
        codeTd.innerHTML = jsonData[i].code;
        var codeTdId = 'codeTd' + i;
        codeTd.setAttribute('id', codeTdId);
        tr.appendChild(codeTd);
       
        var addressTd = document.createElement("td");
        addressTd.innerHTML = jsonData[i].address;
        var addressTdId = 'addressTd' + i;
        addressTd.setAttribute('id', addressTdId);
        tr.appendChild(addressTd);
       
        var nameTd = document.createElement("td");
        nameTd.innerHTML = jsonData[i].name;
        var nameTdId = 'nameTd' + i;
        nameTd.setAttribute('id', nameTdId);
        tr.appendChild(nameTd);

        var manufactureTd = document.createElement("td");
        manufactureTd.innerHTML = jsonData[i].manufacture;
        var manTdId = 'manTd' + i;
        manufactureTd.setAttribute('id', manTdId);
        tr.appendChild(manufactureTd);

        var priceTd = document.createElement("td");
        priceTd.innerHTML = jsonData[i].price;
        var priceTdId = 'priceTd' + i;
        priceTd.setAttribute('id', priceTdId);
        tr.appendChild(priceTd);

        var editButtonTd = document.createElement("td");
        var editButton = document.createElement("button");
        var editButtonId = 'editbutton' + i;
        editButton.setAttribute('id', editButtonId);
        editButton.setAttribute('onclick', `editTds(${i})`);
        editButtonTd.appendChild(editButton);

        var saveButton = document.createElement("button");
        var saveButtonId = 'savebutton' + i;
        saveButton.setAttribute('id', saveButtonId);
        saveButton.setAttribute('onclick', `saveTds(${i})`);
        saveButton.style.display = 'none';
        editButtonTd.appendChild(saveButton);

        var deleteButtonTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        var deleteButtonId = 'deletebutton' + i;
        deleteButton.setAttribute('id', deleteButtonId);
        deleteButtonTd.appendChild(deleteButton);
        
        editButton.innerHTML = `<button class="btn"><i class="fas fa-edit"></i>Szerkesztés</button>`;
        saveButton.innerHTML = `<button class="btn"><i class="fa fa-save"></i>Mentés</button>`;
        deleteButton.innerHTML = `<button class="btn"><i class="fas fa-trash-alt"></i>Törlés</button>`;

       /* deleteButton.onclick = deleteLocation;
        deleteButton["raw-data"] = jsonData[i];*/

        tr.appendChild(editButtonTd);
        tr.appendChild(deleteButtonTd);

        table.appendChild(tr);
    }
}

    function editTds(num){

        var code = document.getElementById(`codeTd${num}`);
        var address = document.getElementById(`addressTd${num}`);
        var name = document.getElementById(`nameTd${num}`);
        var manu = document.getElementById(`manTd${num}`);
        var price = document.getElementById(`priceTd${num}`);

        var codeData = code.innerHTML;
        var addressData = address.innerHTML;
        var nameData = name.innerHTML;
        var manuData = manu.innerHTML;
        var priceData = price.innerHTML;

        code.innerHTML = `<input id="codeTd${num}" type='text' minLength='1' maxLength='255' class='input-box'  value = '${codeData}'>`
        address.innerHTML = `<input id="addressTd${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${addressData}'>`
        name.innerHTML = `<input id="nameTd${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${nameData}'>`
        manu.innerHTML = `<input id="manTd${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${manuData}'>`
        price.innerHTML = `<input id="priceTd${num}" type='number' class='input-box' min='0' max='2000000' step= '1' value='${priceData}'>`
    

        var edit = document.getElementById(`editbutton${num}`);
        edit.style.display = 'none';
        var save = document.getElementById(`savebutton${num}`);
        save.style.display = 'inline';
    }

    function saveTds(num){

         var id = (new URL(document.adminproducts)).searchParams.get("id");

        var codeTd = document.getElementById(`codeTd${num}`);
        var addressTd = document.getElementById(`addressTd${num}`);
        var nameTd = document.getElementById(`nameTd${num}`);
        var manTd = document.getElementById(`manTd${num}`);
        var priceTd = document.getElementById(`priceTd${num}`);

        var code = codeTd.value;
        var address = addressTd.value;
        var name = nameTd.value;
        var manu = manTd.value;
        var price = priceTd.value;

        var request = {

            "code": code,
            "address": address,
            "name": name,
            "manufacture": manu,
            "price": price
        }

        fetch("/api/adminproducts/" + id, {
                method: "POST",
                body: JSON.stringify(request),
                headers: {
                    "Content-type": "application/json"
                }
            })
            .then(function (response) {
                return response.json();
            }).
        then(function (jsonData) {
            if (jsonData.resultStatusE = "OK") {

               codeTd.innerHTML = `<td id='idTd${num}'>${code}</td>`
               addressTd.innerHTML = `<td id='addressTd${num}'>${address}</td>`
               nameTd.innerHTML = `<td id='nameTd${num}'>${name}</td>`
               manTd.innerHTML = `<td id='idTd${num}'>${manu}</td>`
               priceTd.innerHTML = `<td id='idTd${num}'>${price}</td>`
                fetchProducts();
                document.getElementById("message-div").setAttribute("class", "alert alert-success");
            } else {
                document.getElementById("message-div").setAttribute("class", "alert alert-danger");
            }
            document.getElementById("message-div").innerHTML = "Updated";
        });
        return false;
    }

    function addNewProduct(){
        
    }
