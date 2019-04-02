fetchMyOrders();

function fetchMyOrders(){
    var url = "/myorders";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        showTable(jsonData)
    });
}
var container = document.querySelector("#container");
container.innerHTML = "";
var table = document.createElement("table");
table.setAttribute('class', 'table table-striped');
container.appendChild(table);

function showTableHead(jsonData){
    console.log(jsonData);

            var thead = document.createElement("thead");
            table.appendChild(thead);

            var trHead = document.createElement("tr");
            thead.appendChild(trHead);

            var thDate = document.createElement("th");
            thDate.setAttribute('scope', 'col');
            thDate.innerHTML = "Dátum";
            trHead.appendChild(thDate);

            var thName = document.createElement("th");
            thName.setAttribute('scope', 'col');
            thName.innerHTML = "Termék";
            trHead.appendChild(thName);
            
            var thPiece = document.createElement("th");
            thName.setAttribute('scope', 'col');
            thPiece.innerHTML = "Darab";
            trHead.appendChild(thPiece);

            var thDelivery = document.createElement("th");
            thName.setAttribute('scope', 'col');
            thDelivery.innerHTML = "Szállítási cím";
            trHead.appendChild(thDelivery);
        }

        function showTableBody(jsonData, i, j){
                   var tbody = document.createElement("tbody");
                   table.appendChild(tbody);

                    var tr = document.createElement("tr");
                    tbody.appendChild(tr);

                    var dateTd = document.createElement("td");
                    dateTd.innerHTML = jsonData[i].purchaseDate;
                    dateTd.setAttribute('id', 'date');
                    tr.appendChild(dateTd);

                    var nameTd = document.createElement("td");
                    nameTd.innerHTML = jsonData[i].orderedProducts[j].orderingName;
                    nameTd.setAttribute('id', 'name');
                    tr.appendChild(nameTd);

                    var pieceTd = document.createElement("td");
                    pieceTd.innerHTML = jsonData[i].orderedProducts[j].pieces;
                    tr.appendChild(pieceTd);

                    var deliveryTd = document.createElement("td");
                    deliveryTd.innerHTML = jsonData[i].delivery.deliveryAddress;
                    tr.appendChild(deliveryTd);

                    tbody.appendChild(tr);
               // }
            }




function showTable(jsonData){
    showTableHead(jsonData);
for (var i = 0; i < jsonData.length; i++){
    for(var j = 0; j < jsonData[i].orderedProducts.length ; j++){
        showTableBody(jsonData, i, j);
        }
        if (i < jsonData.length-1){
        showTableHead(jsonData);
        }
    }
}

