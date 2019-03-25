$.getJSON("/reports/orders", json => {
console.log(json)
var sum = 0;
for(var i = 0; i<json.length; i++) {
     var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var dateTd = document.createElement("td");
            dateTd.innerHTML = jsonData[i].purchase_date;
            tr.appendChild(dateTd);

            var o_statusTd = document.createElement("td");
                dateTd.innerHTML = jsonData[i].order_status;
                tr.appendChild(o_statusTd);

                 var m_incomeTd = document.createElement("td");
                         dateTd.innerHTML = jsonData[i].total;
                         tr.appendChild(m_incomeTd);

}
document.querySelector('#cartCount').innerHTML = sum;
});

$.getJSON("/reports/products", json => {
console.log(json)
var sum = 0;
for(var i = 0; i<json.length; i++) {
     var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var dateTd = document.createElement("td");
        dateTd.innerHTML = jsonData[i].purchase_date;
        tr.appendChild(dateTd);

}
document.querySelector('#cartCount').innerHTML = sum;
});