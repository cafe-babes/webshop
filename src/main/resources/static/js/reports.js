            window.onload = function() {
              fetchOrderReport();
              fetchProductReport();
            };

            function fetchOrderReport() {
              fetch("/reports/orders")
                .then(function(response) {
                  return response.json();
                })
                .then(function(jsonData) {
                  showIncomeTable(jsonData);
                });
            }

             function fetchProductReport() {
                          fetch("/reports/products")
                            .then(function(response) {
                              return response.json();
                            })
                            .then(function(jsonData) {
                              showProductTable(jsonData);
                            });
                        }

            function showIncomeTable(jsonData){
                              console.log(jsonData);
            var incomeTable = document.getElementById("income-table");

        for(var i = 0; i<jsonData.length; i++) {

        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var dateYearTd = document.createElement("td");
            dateYearTd.innerHTML = jsonData[i].year;
            tr.appendChild(dateYearTd);

            var dateMonthTd = document.createElement("td");
                        dateMonthTd.innerHTML = jsonData[i].month;
                        tr.appendChild(dateMonthTd);

            var o_statusTd = document.createElement("td");
                o_statusTd.innerHTML = jsonData[i].orderStatus;
                tr.appendChild(o_statusTd);

                 var m_incomeTd = document.createElement("td");
                         m_incomeTd.innerHTML = jsonData[i].total;
                         tr.appendChild(m_incomeTd);

                         var countTd = document.createElement("td");
                              countTd.innerHTML = jsonData[i].count;
                              tr.appendChild(countTd);

                              incomeTable.appendChild(tr);

                    }
}


  function showProductTable(jsonData){
                              console.log(jsonData);
            var incomeTable = document.getElementById("income-table");

        for(var i = 0; i<jsonData.length; i++) {

        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var dateYearTd = document.createElement("td");
            dateYearTd.innerHTML = jsonData[i].year;
            tr.appendChild(dateYearTd);

            var dateMonthTd = document.createElement("td");
                        dateMonthTd.innerHTML = jsonData[i].month;
                        tr.appendChild(dateMonthTd);

            var o_statusTd = document.createElement("td");
                o_statusTd.innerHTML = jsonData[i].orderStatus;
                tr.appendChild(o_statusTd);

                 var m_incomeTd = document.createElement("td");
                         m_incomeTd.innerHTML = jsonData[i].total;
                         tr.appendChild(m_incomeTd);

                         var countTd = document.createElement("td");
                              countTd.innerHTML = jsonData[i].count;
                              tr.appendChild(countTd);

                              incomeTable.appendChild(tr);

                    }
}