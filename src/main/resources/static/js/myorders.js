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
    console.log("showMyOrders ok")
    var container = document.querySelector("#tbody");
    container.innerHTML = "";
    console.log(jsonData.length);

    for(var i = 0; i < jsonData.length; i++){
            console.log("ciklus ok");
            container.innerHTML +=
            `
                        <tr>
                            <th scope="row" id="counter">${i+1}</th>
                            <td id="date">${jsonData[i].purchaseDate}</td>
                            <td id="product">${jsonData[i].orderingName}</td>
                        </tr>
            `
    }
}