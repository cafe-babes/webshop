fetchMyOrders();

function fetchMyOrders(){
    var url = "/myorders";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        console.log("fetch ok");
        console.log(jsonData);
        showMyOrders(jsonData);
    });
}

function showMyOrders(jsonData){
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

            for(var prop in jsonData[obj]){
                if(jsonData[obj].hasOwnProperty(prop)){
                   var tbody = document.createElement("tbody");
                   table.appendChild(tbody);

                    var tr = document.createElement("tr");
                    tbody.appendChild(tr);

                    var dateTd = document.createElement("td");
                    dateTd.innerHTML = obj;
                    dateTd.setAttribute('id', 'date');
                    tr.appendChild(dateTd);


                    var names = jsonData[obj][prop];
                    console.log(names);
                    var nameTd = document.createElement("td");
                    nameTd.innerHTML = jsonData[obj][prop].orderingName;
                    nameTd.setAttribute('id', 'name');
                    tr.appendChild(nameTd);

                    var pieceTd = document.createElement("td");
                    pieceTd.innerHTML = jsonData[obj][prop].pieces;
                    tr.appendChild(pieceTd);

                    tbody.appendChild(tr);
                }
            }
        }
    }
}