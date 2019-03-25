fetchDashboard();

function fetchDashboard(){
    fetch("/dashboard")
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        console.log("fetch ok");
        console.log(jsonData);
        showDashboard(jsonData);
    });
}

function showDashboard(jsonData){
    var tbody = document.querySelector("#tbody");
    tbody.innerHTML = "";
    tbody.innerHTML += `
        <tr>
            <td style="text-align: center">${jsonData[0]}</td>
            <td style="text-align: center">${jsonData[1]}</td>
            <td style="text-align: center">${jsonData[2]}</td>
            <td style="text-align: center">${jsonData[3]}</td>
            <td style="text-align: center">${jsonData[4]}</td>
        </tr>
    `;

}