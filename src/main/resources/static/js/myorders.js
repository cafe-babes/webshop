fetchMyOrders();

function fetchMyOrders(){
    var url = "/myorders";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        getNames(jsonData);
//        console.log("fetch ok");
//        console.log(jsonData);
//        showMyOrders(jsonData);
    });
}

function getNames(jsonData){
    var url = "/myorders";
    fetch(url, {
        method: "GET"
    })
    .then(function(response){
        console.log(response);
        return response.json();
    })
    .then(function(jsonData){
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
            console.log(jsonData[i].name)
            container.innerHTML +=
            `
                        <tr>
                            <th scope="row" id="counter">${i+1}</th>
                            <td id="date">${jsonData[i].purchaseDate}</td>
                            <td id="product">${jsonData[i].name}</td>
                        </tr>
            `
    }
}