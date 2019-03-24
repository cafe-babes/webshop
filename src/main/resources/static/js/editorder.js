
var arr = window.location.href.split('=');
var id = parseInt(arr[1]);


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
        idTd.innerHTML = jsonData[i].productId;
        var idTdId = 'idTd' + i;
        idTd.setAttribute('id', idTdId);
        tr.appendChild(idTd);

        var purchaseDateTd = document.createElement("td");
        purchaseDateTd.innerHTML = jsonData[i].orderingPrice;
        var purchaseDateTdId = 'purchaseDateTd' + i;
        purchaseDateTd.setAttribute('id', purchaseDateTdId);
        tr.appendChild(purchaseDateTd);


        var deleteButtonTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        var deleteButtonId = 'deletebutton' + i;
        deleteButton.setAttribute('id', deleteButtonId);
        deleteButton.setAttribute('class', 'btn');
        deleteButton.setAttribute('onclick', `deleteOrder(${i})`);
        deleteButton['raw-data'] = jsonData[i];
        deleteButtonTd.appendChild(deleteButton);


        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;


        tr.appendChild(deleteButtonTd);

        table.appendChild(tr);

    }

}