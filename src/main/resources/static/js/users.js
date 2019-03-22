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


    var editTd = document.createElement("td");
    var editButton = document.createElement("button");
    editButton.setAttribute('class', 'btn');
    editButton.setAttribute('onclick', `editTds(${i})`);
    editButton.innerHTML = `<i class="fas fa-edit"></i>Szerkesztés`;
    tr.appendChild(editTd);
    editTd.appendChild(editButton);

     var saveButton = document.createElement("button");
                var saveButtonId = 'savebutton' + i;
                saveButton.setAttribute('id', saveButtonId);
                saveButton.setAttribute('class', 'btn');
                saveButton.setAttribute('onclick', `saveTds(${i})`);
                saveButton.style.display = 'none';
                editButtonTd.appendChild(saveButton);


    var deleteButtonTd = document.createElement("td");
            var deleteButton = document.createElement("button");
            var deleteButtonId = 'deletebutton' + i;
            deleteButton.setAttribute('id', deleteButtonId);
            deleteButton.setAttribute('class', 'btn');
            deleteButton.setAttribute('onclick', `deleteUser(${i})`);
            deleteButton['raw-data'] = jsonData[i];

            deleteButton.innerHTML = `<i class="fas fa-trash-alt"></i>Törlés`;

            deleteButtonTd.appendChild(deleteButton);
            tr.appendChild(deleteButtonTd);

            table.appendChild(tr);

  }
  }
  function deleteUser(num){

          var id = document.getElementById(`deletebutton${num}`)['raw-data'].id;
          var name = document.getElementById(`deletebutton${num}`)['raw-data'].name;

          if (!confirm("Biztos, hogy törli a felhasználót?")) {
              return;
          }

           fetch("/users/" + id, {
                          method: "DELETE",
                      })
                      .then(function (response) {
                          document.getElementById("message-div").setAttribute("class", "alert alert-success");
                          document.querySelector("#message-div").innerHTML = name + " sikeresen törölve!"
                          fetchUsers();
                          });
}
function editTds(num){

        var name = document.getElementById(`nameTd${num}`);
        var password = document.getElementById(`passwordTd${num}`);

        var nameData = code.innerHTML;
        var passwordData = address.innerHTML;

        name.innerHTML = `<input id="nameInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value = '${codeData}' required>`
        password.innerHTML = `<input id="passwordInput${num}" type='text' minLength='1' maxLength='255' class='input-box'  value='${passwordData}' required>`

        var edit = document.getElementById(`editbutton${num}`);
        edit.style.display = 'none';
        var save = document.getElementById(`savebutton${num}`);
        save.style.display = 'inline';
    }

    function saveTds(num){

            var id = document.getElementById(`savebutton${num}`).parentElement.parentElement['raw-data'].id;

            var code = document.getElementById(`codeInput${num}`).value;
            var address = document.getElementById(`addressInput${num}`).value;
            var name = document.getElementById(`nameInput${num}`).value;
            var manu = document.getElementById(`manInput${num}`).value;
            var price = document.getElementById(`priceInput${num}`).value;

            var request = {
                "id": id,
                "code": code,
                "address": address,
                "name": name,
                "manufacture": manu,
                "price": price,
                "product_status": "ACTIVE"
            }

            fetch("/users/" + id, {
                    method: "POST",
                    body: JSON.stringify(request),
                    headers: {
                        "Content-type": "application/json"
                    }
                })
                .then(function (response) {
                    return response.json();
                }).
            then(function (jsonData) {
                if (jsonData.status == 'OK') {

                   document.getElementById(`codeTd${num}`).innerHTML = code;
                   document.getElementById(`addressTd${num}`).innerHTML = address;
                   document.getElementById(`nameTd${num}`).innerHTML = name;
                   document.getElementById(`manTd${num}`).innerHTML = manu;
                   document.getElementById(`priceTd${num}`).innerHTML = price;

                    fetchProducts();
                   document.getElementById("message-div").setAttribute("class", "alert alert-success");
                   document.getElementById("message-div").innerHTML = "Frissítve";
                } else {
                    document.getElementById("message-div").setAttribute("class", "alert alert-danger");
                    document.getElementById("message-div").innerHTML = "Frissítés nem sikerült";
                }
            });
            return false;
        }