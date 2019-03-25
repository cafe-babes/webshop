$.getJSON("/reports/orders", json => {
console.log(json)
var sum = 0;
for(var i = 0; i<json.length; i++) {
    if(json[i].amount)
        sum += json[i].amount;
}
document.querySelector('#cartCount').innerHTML = sum;
});