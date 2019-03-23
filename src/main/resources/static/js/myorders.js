fetchMyOrders();

function fetchMyOrders(){
    var url = "/myorders";
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        console.log("fetch ok");
        showMyOrders(jsonData);
    });
}

function showMyOrders(jsonData){
    console.log("showMyOrders ok")
    var container = document.querySelector("#container");
    container.innerHTML = "";

    for(var i = 0; i < jsonData.length; i++){
        console.log("ciklus ok");
        container.innerHTML +=
        `
            <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Dátum</th>
                        <th scope="col">Termék</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row" id="counter">${i}</th>
                        <td id="date">${jsonData[i].purchase_date}</td>
                        <td id="product">${jsonData[i].ordering_name}</td>
                    </tr>
                    </tbody>
                </table>
        `
    }
}