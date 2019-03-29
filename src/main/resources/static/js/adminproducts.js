window.onload = function () {
    fetchProducts();
    fetchCategories();
}

var global;

//TODO: service-ben szűrés a category nevekre

function fetchProducts() {
    fetch("/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            showTable(jsonData);
            console.log(jsonData);
    });
}

function fetchCategories(){
    fetch("/categories")
    .then(function(response){
        console.log(response);
        return response.json();
    })
    .then(function(jsonData){
        global = jsonData;
    });
    return false;
}


    function showTable(jsonData) {
    var table = document.querySelector("#adminproducts-table");
    table.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];
      
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

        var statusTd = document.createElement("td");
        statusTd.innerHTML = jsonData[i].productStatus;
        var statusTdId = 'statusTd' + i;
        statusTd.setAttribute('id', statusTdId);
        tr.appendChild(statusTd);

        var categoryTd = document.createElement("td");
        categoryTd.innerHTML = jsonData[i].category.name;
        var categoryTdId = 'categoryTd' + i;
        categoryTd.setAttribute('id', categoryTdId);
        tr.appendChild(categoryTd);

        var editButtonTd = document.createElement("td");
        var editButton = document.createElement("button");
        var editButtonId = 'editbutton' + i;
        editButton.setAttribute('id', editButtonId);
        editButton.setAttribute('class', 'btn');
        editButton.setAttribute('onclick', `editTds(${i})`);
        editButtonTd.appendChild(editButton);

        var saveButton = document.createElement("button");
        var saveButtonId = 'savebutton' + i;
        saveButton.setAttribute('id', saveButtonId);
        saveButton.setAttribute('class', 'btn');
        saveButton.setAttribute('onclick', `saveTds(${i})`);
        saveButton.style.display = 'none';
        editButtonTd.appendChild(saveButton);

        var deleteButtonTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        var deleteButtonId = 'deletebutton' + i;
        deleteButton.setAttribute('id', deleteButtonId);
        deleteButton.setAttribute('class', 'btn');
        deleteButton.setAttribute('onclick', `deleteProduct(${i})`);
        deleteButton['raw-data'] = jsonData[i];
        deleteButtonTd.appendChild(deleteButton);
        

        editButton.innerHTML = `<i class="fas fa-edit"></i>Szerkesztés`;
        saveButton.innerHTML = `<i class="fa fa-save"></i>Mentés`;
        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

        tr.appendChild(editButtonTd);
        tr.appendChild(deleteButtonTd);

        table.appendChild(tr);
        
    }
    var createButton = document.getElementById('createButton');
    createButton.setAttribute('onclick', `showNewRow(${jsonData.length})`);

}

    function editTds(num){
        var code = document.getElementById(`codeTd${num}`);
        var address = document.getElementById(`addressTd${num}`);
        var name = document.getElementById(`nameTd${num}`);
        var manu = document.getElementById(`manTd${num}`);
        var price = document.getElementById(`priceTd${num}`);
        var category = document.getElementById(`categoryTd${num}`);
        selectedCategory = category.innerHTML;

        var codeData = code.innerHTML;
        var addressData = address.innerHTML;
        var nameData = name.innerHTML;
        var manuData = manu.innerHTML;
        var priceData = price.innerHTML;
        var categoryData = category.innerHTML;

        code.innerHTML = `<input id="codeInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value = '${codeData}' required>`
        address.innerHTML = `<input id="addressInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${addressData}' required>`
        name.innerHTML = `<input id="nameInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${nameData}' required>`
        manu.innerHTML = `<input id="manInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${manuData}' required>`
        price.innerHTML = `<input id="priceInput${num}" type='number' class='input-box' min='0' max='2000000' step= '1' value='${priceData}' required>`
        category.innerHTML = `<select id="selectInput${num}" class='form-control' value='${categoryData}'  required>`

        var edit = document.getElementById(`editbutton${num}`);
        edit.style.display = 'none';
        var save = document.getElementById(`savebutton${num}`);
        save.style.display = 'inline';
        showCategories(num, selectedCategory);
    }


function showCategories(num, category){
        var jsonData = global;
        var myselect2 = document.querySelector('#selectInput' + num);
        myselect2.innerHTML = "";
        for (var i = 0; i < jsonData.length; i++) {
            myselect2.innerHTML +=
            `<option value="${jsonData[i].name}">${jsonData[i].name}</option>`
        }
}


    function saveTds(num){

        var id = document.getElementById(`savebutton${num}`).parentElement.parentElement['raw-data'].id;

        var code = document.getElementById(`codeInput${num}`).value;
        var address = document.getElementById(`addressInput${num}`).value;
        var name = document.getElementById(`nameInput${num}`).value;
        var manu = document.getElementById(`manInput${num}`).value;
        var price = document.getElementById(`priceInput${num}`).value;
        var category = document.getElementById(`selectInput${num}`).value;
        console.log(category);
        console.log(global);
        console.log(num);

        //TODO category mentése
        var request = {
            "id": id,
            "code": code,
            "address": address,
            "name": name,
            "manufacture": manu,
            "price": price,
            "category" : category
        }

        console.log(request);

        fetch("/products/" + id, {
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
            if (jsonData.status == 'OK') {

               document.getElementById(`codeTd${num}`).innerHTML = code;
               document.getElementById(`addressTd${num}`).innerHTML = address;
               document.getElementById(`nameTd${num}`).innerHTML = name;
               document.getElementById(`manTd${num}`).innerHTML = manu;
               document.getElementById(`priceTd${num}`).innerHTML = price;
               document.getElementById(`categoryTd${num}`).innerHTML = category;
            
                fetchProducts();
                fetchCategories();
               document.getElementById("message-div").setAttribute("class", "alert alert-success");
               document.getElementById("message-div").innerHTML = "Frissítve";
            } else {
                document.getElementById("message-div").setAttribute("class", "alert alert-danger");
                document.getElementById("message-div").innerHTML = "Frissítés nem sikerült";
            }
        });
        return false;
    }

    function showNewRow(length){
        var num = length + 1;
        var table = document.querySelector("#adminproducts-table");
        var tr = document.createElement('tr');

        var idTd = document.createElement('td');
        idTd.setAttribute('id', `idTd${num}`);
        var codeTd = document.createElement('td');
        codeTd.setAttribute('id', `codeTd${num}`);
        var addressTd = document.createElement('td');
        addressTd.setAttribute('id', `addressTd${num}`);
        var nameTd = document.createElement('td');
        nameTd.setAttribute('id', `nameTd${num}`);
        var manTd = document.createElement('td');
        manTd.setAttribute('id', `manTd${num}`);
        var priceTd = document.createElement('td');
        priceTd.setAttribute('id', `priceTd${num}`);
        var statusTd = document.createElement('td');
        var categoryTd = document.createElement('td');
        categoryTd.setAttribute('id', `categoryTd${num}`);
        var saveButtonTd = document.createElement('td');
        var saveButton = document.createElement('button');
        saveButton.setAttribute('class', 'btn');
        saveButton.setAttribute('onclick', `addNewProduct(${num})`);
        var deleteButtonTd = document.createElement('td');
        var deleteButton = document.createElement('button');
        deleteButton.setAttribute('onclick', 'deleteNewRow()');
        deleteButton.setAttribute('class', 'btn');
        saveButtonTd.appendChild(saveButton);
        deleteButtonTd.appendChild(deleteButton);

        codeTd.innerHTML = `<input id="codeInputNew${num}" type='text' minLength='1' maxLength='255' class='input-box' required>`
        addressTd.innerHTML = `<input id="addressInputNew${num}" type='text' minLength='1' maxLength='255' class='input-box' required>`
        nameTd.innerHTML = `<input id="nameInputNew${num}" type='text' minLength='1' maxLength='255' class='input-box' required>`
        manTd.innerHTML = `<input id="manInputNew${num}" type='text' minLength='1' maxLength='255' class='input-box' required>`
        priceTd.innerHTML = `<input id="priceInputNew${num}" type='number' class='input-box' min='0' max='2000000' step= '1' required>`
        statusTd.innerHTML = 'ACTIVE';
        categoryTd.innerHTML = `<select id="categoryInputNew${num}" class='form-control' required>`
        console.log(num);
        for(var i = 0; i < global.length; i++){
            document.querySelector(`#categoryInputNew${num}`).innerHTML +=
            `<option value="${global[i].name}">${global[i].name}</option>`
        }

        saveButton.innerHTML = `<i class="fa fa-save"></i>Mentés`;
        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

        tr.appendChild(idTd); tr.appendChild(codeTd); tr.appendChild(addressTd); 
        tr.appendChild(nameTd); tr.appendChild(manTd); tr.appendChild(priceTd); 
        tr.appendChild(statusTd); tr.appendChild(categoryTd); tr.appendChild(saveButtonTd); tr.appendChild(deleteButtonTd);
        table.appendChild(tr);

    }


function showNewCategories(num){
    console.log(num);
    var jsonData = global;
    console.log(document.querySelector('#categoryInputNew15'));
    var myselect2 = document.querySelector('#categoryInputNew' + num);
    console.log(myselect2);
            myselect2.innerHTML = "";
            for (var i = 0; i < jsonData.length; i++) {
                myselect2.innerHTML +=
                `<option value="${jsonData[i].name}">${jsonData[i].name}</option>`
            }
}

    function addNewProduct(num){

        var code = document.getElementById(`codeInputNew${num}`).value;
        var address = document.getElementById(`addressInputNew${num}`).value;
        var name = document.getElementById(`nameInputNew${num}`).value;
        var manu = document.getElementById(`manInputNew${num}`).value;
        var price = document.getElementById(`priceInputNew${num}`).value;
        var category = document.getElementById(`categoryInputNew${num}`).value;
        
        var request = {
            //"id": id,
            "code": code,
            "address": address,
            "name": name,
            "manufacture": manu,
            "price": price,
            "product_status": "ACTIVE",
            "category" : category
        }

        fetch("/products", {
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
            if (jsonData.status == "OK") {

                document.getElementById(`codeTd${num}`).innerHTML = code;
                document.getElementById(`addressTd${num}`).innerHTML = address;
                document.getElementById(`nameTd${num}`).innerHTML = name;
                document.getElementById(`manTd${num}`).innerHTML = manu;
                document.getElementById(`priceTd${num}`).innerHTML = price;
                document.getElementById(`categoryTd${num}`).innerHTML = category;
                fetchProducts();
                document.getElementById("message-div").setAttribute("class", "alert alert-success");
                document.getElementById("message-div").innerHTML = "Új termék hozzáadva";
            } else {
                document.getElementById("message-div").setAttribute("class", "alert alert-danger");
                document.getElementById("message-div").innerHTML = "A beszúrás sikertelen";
            }
        });
        return false; 
    }

    function deleteNewRow(){
        var table = document.querySelector("#adminproducts-table");
        table.removeChild(table.lastChild);
    }

    function deleteProduct(num){

        var id = document.getElementById(`deletebutton${num}`).parentElement.parentElement['raw-data'].id;

        if (!confirm("Biztos, hogy törli a terméket?")) {
            return;
        }

        fetch("/products/" + id, {
                method: "DELETE",
            })
            .then(function (response) {
                document.getElementById("message-div").setAttribute("class", "alert alert-success");
                document.querySelector("#message-div").innerHTML = "Törölve"
                fetchProducts();
                });
            }
            
