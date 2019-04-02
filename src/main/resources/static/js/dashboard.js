window.onload = function() {
fetchDashboard();
}


var myJsonData;

function fetchDashboard(){
    fetch("/dashboard")
    .then(function(response){
        return response.json();
    })
    .then(function(jsonData){
        console.log("fetch ok");
        console.log(jsonData);
        showDashboard(jsonData);
        myJsonData = jsonData;
        console.log(myJsonData);
//        zingchart.render({jsonData});
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

        zingchart.render({
        id: 'myChart',
        data:{
     	type: "hbar",
     	backgroundColor : "#ffffff",
      tooltip:{visible:false},
     	scaleX : {
     	  lineColor : "transparent",
     	  tick : {
     	    visible : false
     	  },
     	  labels : [ "Felhasználók", "Aktív termék", "Aktív megrendelés"],
     	  item : {
     	     fontColor : "#808080",
     	     fontSize : 12
     	  }
     	},
     	scaleY :{
     	  visible : false,
     	  lineColor : "transparent",
     	  guide : {
     	    visible : false
     	  },
     	  tick : {
     	    visible : false
     	  }
     	},
     	plotarea : {
     	  marginLeft : "120",
     	  marginTop : "30",
     	  marginBottom : "30"
     	},
     	plot : {
     	  stacked : true,
     	  barsSpaceLeft : "20px",
     	  barsSpaceRight : "20px",
     	  valueBox : {
     	    visible : true,
     	    text : "%v",
     	    fontColor : "#2A2B3A",
     	    fontSize: 14
     	  },
     	  tooltip : {
     	    borderWidth : 0,
     	    borderRadius : 2
     	  },
     	  animation:{
     	    effect:3,
     	    sequence:3,
     	    method:3
     	  }
     	},
    	series : [
    		{
    			values : [jsonData[0],jsonData[2],jsonData[4]],
    			borderRadius : "50px 0px 0px 50px",
    			backgroundColor : "#E71D36",
    			rules : [
    			  {
    			    rule : "%i === 0",
    			    backgroundColor : "#E71D36"
    			  },
    			  {
    			    rule : "%i === 1",
    			    backgroundColor : "#2EC4B6"
    			  },
            {
    			    rule : "%i === 2",
    			    backgroundColor : "#FF9F1C"
    			  }
    			]
    		},
    		{
    			values : [,jsonData[1],jsonData[3]],
    			borderRadius : "0px 50px 50px 0px",
    			backgroundColor : "#E71D36",
    			alpha : 0.8,
    		  rules : [
    			  {
    			    rule : "%i === 0",
    			    backgroundColor : "#e85d6f"
    			  },
    			  {
    			    rule : "%i === 1",
    			    backgroundColor : "#90eae2"
    			  },
            {
    			    rule : "%i === 2",
    			    backgroundColor : "#f7be70"
    			  }
    			]
    		}
    	]
    	},
    });
    }

console.log("kukucs");
        console.log(zingchart.render);



