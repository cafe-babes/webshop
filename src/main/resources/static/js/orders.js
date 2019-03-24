window.onload = function () {
    fetchOrders();
}

function fetchOrders() {
    fetch("/orders")
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            showTable(jsonData);
        });
}


  function showTable(jsonData) {
    var table = document.querySelector("#orders-table");
    table.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var idTd = document.createElement("td");
        idTd.innerHTML = jsonData[i].userId;
        var idTdId = 'idTd' + i;
        idTd.setAttribute('id', idTdId);
        tr.appendChild(idTd);

        var purchaseDateTd = document.createElement("td");
        purchaseDateTd.innerHTML = jsonData[i].purchaseDate;
        var purchaseDateTdId = 'purchaseDateTd' + i;
        purchaseDateTd.setAttribute('id', purchaseDateTdId);
        tr.appendChild(purchaseDateTd);

        var userId = document.createElement("td");
        userId.innerHTML = jsonData[i].total;
        var userTdId = 'userId' + i;
        userId.setAttribute('id', userTdId);
        tr.appendChild(userId);

        var total = document.createElement("td");
        total.innerHTML = jsonData[i].sumQuantity;
        var totalTdId = 'total' + i;
        total.setAttribute('id', totalTdId);
        tr.appendChild(total);

        var orderStatusTd = document.createElement("td");
        orderStatusTd.innerHTML = jsonData[i].orderStatus;
        var orderStatusTdId = 'manTd' + i;
        orderStatusTd.setAttribute('id', orderStatusTdId);
        tr.appendChild(orderStatusTd);

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
        deleteButton.setAttribute('onclick', `deleteOrder(${i})`);
        deleteButton['raw-data'] = jsonData[i];
        deleteButtonTd.appendChild(deleteButton);


        editButton.innerHTML = `<i class="fas fa-edit"></i>Szerkesztés`;
        saveButton.innerHTML = `<i class="fa fa-save"></i>Mentés`;
        deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

        tr.appendChild(editButtonTd);
        tr.appendChild(deleteButtonTd);

        table.appendChild(tr);

//        var orderDetailsTable = document.createElement("table");
//        orderDetailsTable.innerHTML = "<tr><td>loremipsum</tr></td><tr><td>loremipsum</tr></td><tr><td>loremipsum</tr></td>";
////        var orderDetailsTrId = 'orderDetailsTr' + i;
////         orderDetailsTr.setAttribute('id', orderDetailsTrId);
//        tr.appendChild(orderDetailsTable);

    }

}

    function editTds(num){

        window.location.href = "editorder.html";

        var address = (new URL(document.location)).searchParams.get("address");
            var url = "/product/"+address;


//        function fetchOrder() {
//            fetch("/orders/`${num}`")
//                .then(function (response) {
//                    return response.json();
//                })
//                .then(function (jsonData) {
//                    console.log(jsonData);
//                    showTable(jsonData);
//                });
//        }
//        fetchOrder();
    }


    function deleteOrder(num){
        var id = document.getElementById(`deletebutton${num}`).parentElement.parentElement['raw-data'].id;
        if (!confirm("Biztos, hogy törli a megrendelést?")) {
            return;
        }
        fetch("/orders/" + id, {
                method: "POST",
            })
            .then(function (response) {
                document.getElementById("message-div").setAttribute("class", "alert alert-success");
                document.querySelector("#message-div").innerHTML = "Törölve"
                fetchOrders();
                });
            }