//window.onload = function () {
//    fetchOrders();
//}
/*
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
*/



function getJsonData(url, callbackFunc) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        callbackFunc(this);
      }
    };
    xhttp.open('GET', url, true);
    xhttp.send();
  }

  function successAjax(xhttp) {
    var orders = JSON.parse(xhttp.responseText);
    console.log(orders);
    showTable(orders);
  }

  getJsonData('/json/orders.json', successAjax);

  function showTable(jsonData) {
    var table = document.querySelector("#orders-table");
    table.innerHTML = "";
    for (var i = 0; i < jsonData.length; i++) {
        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var idTd = document.createElement("td");
        idTd.innerHTML = jsonData[i].user_id;
        var idTdId = 'idTd' + i;
        idTd.setAttribute('id', idTdId);
        tr.appendChild(idTd);

        var purchaseDateTd = document.createElement("td");
        purchaseDateTd.innerHTML = jsonData[i].purchase_date;
        var purchase_dateTdId = 'purchaseDateTd' + i;
        purchaseDateTd.setAttribute('id', purchase_dateTdId);
        tr.appendChild(purchaseDateTd);

        var userId = document.createElement("td");
        userId.innerHTML = jsonData[i].total;
        var userTdId = 'userId' + i;
        userId.setAttribute('id', userTdId);
        tr.appendChild(userId);

        var total = document.createElement("td");
        total.innerHTML = jsonData[i].order_value;
        var totalTdId = 'total' + i;
        total.setAttribute('id', totalTdId);
        tr.appendChild(total);

        var orderStatusTd = document.createElement("td");
        orderStatusTd.innerHTML = jsonData[i].order_status;
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

    }
    var createButton = document.getElementById('createButton');
    createButton.setAttribute('onclick', `showNewRow(${jsonData.length})`);

}