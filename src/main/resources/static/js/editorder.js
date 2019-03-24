
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
        deleteButton.setAttribute('onclick', `deleteItem(${jsonData[i].id}, ${jsonData[i].orderingName})`);
        deleteButton['raw-data'] = jsonData[i];
        deleteButtonTd.appendChild(deleteButton);


        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

        tr.appendChild(deleteButtonTd);
        table.appendChild(tr);
    }
}

    function deleteItem(id, orderingName){
        var id = document.getElementById(`deletebutton${num}`).parentElement.parentElement['raw-data'].id;
        if (!confirm("Biztos, hogy törli a tételt?")) {
            return;
        }
        fetch("/orders/" + id +"/" +  orderingName, {
                method: "DELETE",
            })
            .then(function (response) {
                document.getElementById("message-div").setAttribute("class", "alert alert-success");
                document.querySelector("#message-div").innerHTML = "Törölve az " + orderingName + "termék"
                fetchOrders();
                });
            }