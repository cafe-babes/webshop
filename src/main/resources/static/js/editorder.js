var id = (new URL(document.location)).searchParams.get('id');

window.onload = function () {
    fetchOrders();
}

function fetchOrders() {
    fetch("/orders/" + id)
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            showTable(jsonData);
        });
}

function showTable(jsonData) {
    var table = document.querySelector("#order-table");
    table.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var idTd = document.createElement("td");
        idTd.innerHTML = jsonData[i].orderingName;
        var idTdId = 'idTd' + i;
        idTd.setAttribute('id', idTdId);
        tr.appendChild(idTd);

        var orderingPriceTd = document.createElement("td");
        orderingPriceTd.innerHTML = jsonData[i].orderingPrice;
        var orderingPriceTdId = 'purchaseDateTd' + i;
        orderingPriceTd.setAttribute('id', orderingPriceTdId);
        tr.appendChild(orderingPriceTd);

        var deleteButtonTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        var deleteButtonId = 'deletebutton' + i;
        deleteButton.setAttribute('id', deleteButtonId);
        deleteButton.setAttribute('class', 'btn');
        deleteButton.setAttribute('onclick', `deleteItem(${i})`);
        deleteButton['raw-data'] = jsonData[i];
        deleteButtonTd.appendChild(deleteButton);

        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

        tr.appendChild(deleteButtonTd);
        table.appendChild(tr);
    }
}

function deleteItem(i, orderingAddress){
    var id = document.getElementById(`deletebutton${i}`).parentElement.parentElement['raw-data'].orderId;
    var orderingAddress = document.getElementById(`deletebutton${i}`).parentElement.parentElement['raw-data'].orderingAddress;
    console.log(id);
    console.log(orderingAddress);
    if (!confirm("Biztos, hogy törli a tételt?")) {
        return;
    }
    fetch("/orders/" + id +"/" +  orderingAddress, {
        method: "DELETE"
    })
    .then(response => response.json())
    .then(jsonData => {
        if(jsonData.ok==true) {
            document.getElementById("message-div").setAttribute("class", "alert alert-success");
            document.querySelector("#message-div").innerHTML = "Törölve a " + orderingAddress + " termék";
            fetchOrders();
        } else {
            document.getElementById("message-div").setAttribute("class", "alert alert-danger");
            document.querySelector("#message-div").innerHTML = "Nem sikerült a törlés";
        }
    });
}