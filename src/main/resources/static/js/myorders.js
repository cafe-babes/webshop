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
    console.log("showMyOrders ok");
    var container = document.querySelector("#table");
    container.innerHTML = "";

    for(var obj in jsonData){
        if(jsonData.hasOwnProperty(obj)){
            var thead = document.createElement("thead");
            container.appendChild(thead);

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

            for(var prop in jsonData[obj]){
                if(jsonData[obj].hasOwnProperty(prop)){
                   var tbody = document.createElement("tbody");
                   container.appendChild(tbody);

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

                    container.appendChild(tr);
                }
            }
        }
    }
}