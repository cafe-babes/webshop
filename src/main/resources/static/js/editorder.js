//var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

console.log(queryString);

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

        });
}
