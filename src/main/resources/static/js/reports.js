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
        m_incomeTd.innerHTML = jsonData[i].total + " Ft";
        tr.appendChild(m_incomeTd);

        var countTd = document.createElement("td");
        countTd.innerHTML = jsonData[i].count;
        tr.appendChild(countTd);

        incomeTable.appendChild(tr);
    }


    zingchart.THEME="classic";
    var myConfig =
            {
                "type": "bar",
                "stacked": true,
                "stack-type": "normal",
                "background-color": "#FFFFFF",
                "title": {
                    "text": "",
                    "font-family": "arial",
                    "x": "40px",
                    "y": "5px",
                    "align": "left",
                    "bold": false,
                    "font-size": "16px",
                    "font-color": "#000000",
                    "background-color": "none"
                },
                "subtitle": {
                    "text": "<i></i>",
                    "font-family": "arial",
                    "x": "175px",
                    "y": "5px",
                    "align": "left",
                    "bold": false,
                    "font-size": "16px",
                    "font-color": "#7E7E7E",
                    "background-color": "none"
                },
                "plot": {
                    "bar-width": "25px",
                    "hover-state": {
                        "visible": false
                    },
                    "animation":{
                      "delay":300,
                      "sequence":1
                    }
                },
                "labels":[
                    {
                        "text": "SHIPPED",
                        "background-color": "#90A23B",
                        "font-size": "14px",
                        "font-family": "arial",
                        "font-weight": "normal",
                        "font-color": "#FFFFFF",
                        "padding": "10%",
                        "border-radius": "3px",
                        "offset-y":-30,
                        "shadow": false,
                        "callout": true,
                        "callout-height": "10px",
                        "callout-width": "15px",
                        "hook":"node:plot=2;index=4"
                    },
                    {
                        "text": "ACTIVE",
                        "thousands-separator": ",",
                        "background-color": "#FFC700",
                        "font-size": "14px",
                        "font-family": "arial",
                        "font-weight": "normal",
                        "font-color": "#FFFFFF",
                        "padding": "10%",
                        "border-radius": "3px",
                        "shadow": false,
                        "callout": true,
                        "callout-height": "10px",
                        "callout-width": "15px",
                        "offset-y": -30,
                        "hook":"node:plot=2;index=9"
                    }
                ],
                "scale-x": {
                    "values": [
                        "JAN",
                        "FEB",
                        "MAR",
                        "APR",
                        "MAY",
                        "JUN",
                        "JUL",
                        "AUG",
                        "SEP",
                        "OCT",
                        "NOV",
                        "DEC"
                    ],
                    "line-color": "#7E7E7E",
                    "tick": {
                        "visible": false
                    },
                    "guide": {
                        "visible": false
                    },
                    "item": {
                        "font-family": "arial",
                        "font-color": "#8B8B8B"
                    }
                },
                "scale-y": {
                    "values": "0:5000000:1000000",
                    "short": true,
                    "line-color": "#7E7E7E",
                    "tick": {
                        "visible": false
                    },
                    "guide": {
                        "line-style": "solid"
                    },
                    "item": {
                        "font-family": "arial",
                        "font-color": "#8B8B8B"
                    }
                },
            "series": [
                {
                    "values" : [
                        0,
                        jsonData[0].total,
                        jsonData[1].total,
                        jsonData[2].total
//                        jsonData[2].total,
//                        jsonData[3].total,
//                        jsonData[4].total,
//                        jsonData[5].total,
//                        jsonData[6].total,
//                        jsonData[7].total,
//                        jsonData[8].total,
//                        jsonData[9].total,
//                        jsonData[10].total,
//                        jsonData[11].total,
//                        jsonData[12].total
                        ],
                    "background-color": "#90A23B"
                },
            ],
                "tooltip": {
                    "visible": false
                }
            }
    ;

    zingchart.render({
    	id : 'myChart',
    	data : myConfig,
    });
    }


function showProductTable(jsonData){
    console.log(jsonData);
    var tradeTable = document.getElementById("trade-table");

    for(var i = 0; i<jsonData.length; i++) {

        var tr = document.createElement("tr");
        tr["raw-data"] = jsonData[i];

        var dateYearTd = document.createElement("td");
        dateYearTd.innerHTML = jsonData[i].year;
        tr.appendChild(dateYearTd);

        var dateMonthTd = document.createElement("td");
        dateMonthTd.innerHTML = jsonData[i].month;
        tr.appendChild(dateMonthTd);

        var productnameTd = document.createElement("td");
        productnameTd.innerHTML = jsonData[i].productname;
        tr.appendChild(productnameTd);

        var priceTd = document.createElement("td");
        priceTd.innerHTML = jsonData[i].price + " Ft";
        tr.appendChild(priceTd);

        var countTd = document.createElement("td");
        countTd.innerHTML = jsonData[i].count;
        tr.appendChild(countTd);

        var totalTd = document.createElement("td");
        totalTd.innerHTML = jsonData[i].total + " Ft";
        tr.appendChild(totalTd);

        tradeTable.appendChild(tr);

    }

        zingchart.THEME="classic";
        var myConfig =
                {
                    "type": "bar",
                    "stacked": true,
                    "stack-type": "normal",
                    "background-color": "#FFFFFF",
                    "title": {
                        "text": "",
                        "font-family": "arial",
                        "x": "40px",
                        "y": "5px",
                        "align": "left",
                        "bold": false,
                        "font-size": "16px",
                        "font-color": "#000000",
                        "background-color": "none"
                    },
                    "subtitle": {
                        "text": "<i></i>",
                        "font-family": "arial",
                        "x": "175px",
                        "y": "5px",
                        "align": "left",
                        "bold": false,
                        "font-size": "16px",
                        "font-color": "#7E7E7E",
                        "background-color": "none"
                    },
                    "plot": {
                        "bar-width": "25px",
                        "hover-state": {
                            "visible": false
                        },
                        "animation":{
                          "delay":300,
                          "sequence":1
                        }
                    },
                    "labels":[
                        {
                            "text": "SHIPPED",
                            "background-color": "#90A23B",
                            "font-size": "14px",
                            "font-family": "arial",
                            "font-weight": "normal",
                            "font-color": "#FFFFFF",
                            "padding": "10%",
                            "border-radius": "3px",
                            "offset-y":-30,
                            "shadow": false,
                            "callout": true,
                            "callout-height": "10px",
                            "callout-width": "15px",
                            "hook":"node:plot=2;index=4"
                        },
                        {
                            "text": "ACTIVE",
                            "thousands-separator": ",",
                            "background-color": "#FFC700",
                            "font-size": "14px",
                            "font-family": "arial",
                            "font-weight": "normal",
                            "font-color": "#FFFFFF",
                            "padding": "10%",
                            "border-radius": "3px",
                            "shadow": false,
                            "callout": true,
                            "callout-height": "10px",
                            "callout-width": "15px",
                            "offset-y": -30,
                            "hook":"node:plot=2;index=9"
                        }
                    ],
                    "scale-x": {
                        "values": [
                            "JAN",
                            "FEB",
                            "MAR",
                            "APR",
                            "MAY",
                            "JUN",
                            "JUL",
                            "AUG",
                            "SEP",
                            "OCT",
                            "NOV",
                            "DEC"
                        ],
                        "line-color": "#7E7E7E",
                        "tick": {
                            "visible": false
                        },
                        "guide": {
                            "visible": false
                        },
                        "item": {
                            "font-family": "arial",
                            "font-color": "#8B8B8B"
                        }
                    },
                    "scale-y": {
                        "values": "0:1000000:200000",
                        "short": true,
                        "line-color": "#7E7E7E",
                        "tick": {
                            "visible": false
                        },
                        "guide": {
                            "line-style": "solid"
                        },
                        "item": {
                            "font-family": "arial",
                            "font-color": "#8B8B8B"
                        }
                    },
                "series": [
                    {
                        "values" : [
                            0,
                            jsonData[0].total,
                            jsonData[1].total,
    //                        jsonData[2].total
    //                        jsonData[2].total,
    //                        jsonData[3].total,
    //                        jsonData[4].total,
    //                        jsonData[5].total,
    //                        jsonData[6].total,
    //                        jsonData[7].total,
    //                        jsonData[8].total,
    //                        jsonData[9].total,
    //                        jsonData[10].total,
    //                        jsonData[11].total,
    //                        jsonData[12].total
                            ],
                        "background-color": "#90A23B"
                    },
                ],
                    "tooltip": {
                        "visible": false
                    }
                }
        ;

        zingchart.render({
        	id : 'myChart2',
        	data : myConfig,
        });

}