window.onload = function() {
  fetchUsers();
};

function fetchUsers() {
  fetch("/users")
    .then(function(response) {
      return response.json();
    })
    .then(function(jsonData) {
      console.log(jsonData);
      showTable(jsonData);
    });
}

function showTable(jsonData) {
  var table = document.querySelector("#users-table");

  table.innerHTML = "";
  for (var i = 0; i < jsonData.length; i++) {
    var tr = document.createElement("tr");
    tr["raw-data"] = jsonData[i];

    var idTd = document.createElement("td");
    idTd.innerHTML = jsonData[i].id;
    var idTdId = "idTd" + i;
    idTd.setAttribute("id", idTdId);
    tr.appendChild(idTd);

    var nameTd = document.createElement("td");
    nameTd.innerHTML = jsonData[i].name;
    var nameTdId = "nameTd" + i;
    nameTd.setAttribute("id", nameTdId);
    tr.appendChild(nameTd);

    var emailTd = document.createElement("td");
    emailTd.innerHTML = jsonData[i].email;
    var emailTdId = "emailTd" + i;
    emailTd.setAttribute("id", emailTdId);
    tr.appendChild(emailTd);

    var user_nameTd = document.createElement("td");
    user_nameTd.innerHTML = jsonData[i].userName;
    var user_nameTdId = "user_nameTd" + i;
    user_nameTd.setAttribute("id", user_nameTdId);
    tr.appendChild(user_nameTd);

    var passwordTd = document.createElement("td");
    passwordTd.innerHTML = jsonData[i].password;
    var passwordTdId = "passwordTd" + i;
    passwordTd.setAttribute("id", passwordTdId);
    tr.appendChild(passwordTd);

    var enabledTd = document.createElement("td");
    enabledTd.innerHTML = jsonData[i].enabled;
    var enabledTdId = "enabledTd" + i;
    enabledTd.setAttribute("id", enabledTdId);
    tr.appendChild(enabledTd);

    var roleTd = document.createElement("td");
    roleTd.innerHTML = jsonData[i].role;
    var roleTdId = "roleTd" + i;
    roleTd.setAttribute("id", roleTdId);
    tr.appendChild(roleTd);

    var user_statusTd = document.createElement("td");
    user_statusTd.innerHTML = jsonData[i].userStatus;
    var user_statusTdId = "user_statusTd" + i;
    user_statusTd.setAttribute("id", user_statusTdId);
    tr.appendChild(user_statusTd);
    table.appendChild(tr);
  }
}
