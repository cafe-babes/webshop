fetchMyOrders();

function fetchMyOrders(){
    var url = "/myorders";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        var valami = [];
        for (const key in jsonData) {
            if (jsonData.hasOwnProperty(key)) {
                const element = jsonData[key];
                valami.push(getDeliveryById(element.deliveryId));
            }
        }
        return [jsonData, valami];
    })
    .then(function(response){
        showMyOrders(response[0], response[1])
    });
}

function showMyOrders(jsonData, address){
    console.log(jsonData);
    console.log(address);
    var container = document.querySelector("#container");
    container.innerHTML = "";
    var table = document.createElement("table");
    table.setAttribute('class', 'table table-striped');
    container.appendChild(table);

    for(var obj in jsonData){
        if(jsonData.hasOwnProperty(obj)){
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

            for(var i = 0; i < jsonData.length; i++){
                   var tbody = document.createElement("tbody");
                   table.appendChild(tbody);

                    var tr = document.createElement("tr");
                    tbody.appendChild(tr);

                    var dateTd = document.createElement("td");
                    dateTd.innerHTML = jsonData[i].purchaseDate;
                    dateTd.setAttribute('id', 'date');
                    tr.appendChild(dateTd);

                    var nameTd = document.createElement("td");
                    nameTd.innerHTML = jsonData[i].orderedProducts[i].orderingName;
                    nameTd.setAttribute('id', 'name');
                    tr.appendChild(nameTd);

                    var pieceTd = document.createElement("td");
                    pieceTd.innerHTML = jsonData[i].orderedProducts[i].pieces;
                    tr.appendChild(pieceTd);

                    var deliveryTd = document.createElement("td");
                    console.log(address[i]);
                    deliveryTd.innerHTML = address[i];
                    tr.appendChild(deliveryTd);

                    tbody.appendChild(tr);
                }
            }
        }
    }


function getDeliveryById(deliveryId){
    console.log(deliveryId);
    return fetch('/delivery/' + deliveryId)
        .then(res => res.json())
        
}