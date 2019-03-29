window.onload = function () {
  fetchProduct();
};
console.log((new URL(document.location)).searchParams.get('address'));

var product;
var user;

$.getJSON('/user-role', json => {
user = {json};
  if (json.role != 'VISITOR') {
    document.querySelector('#addToBasketButton').hidden = false;
  }
});



function fetchProduct() {
  var address = (new URL(document.location)).searchParams.get('address');
  var url = '/product/' + address;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
    console.log(jsonData);
      if (jsonData.status == 'NOT_OK') {
        showProductNotFound(jsonData);
      } else {
        product = {jsonData};
        var productId = jsonData.id;
        fetchFeedback(productId);
        showProduct(jsonData);
      }
    });
  return false;
}


function fetchFeedback(productId) {
  var url = '/feedback/' + productId;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
      console.log('fetch ok');
      console.log(jsonData);
      showReviews(jsonData);
    });
}


function showReviews(jsonData) {
  var reviews = document.getElementById('reviews');
  reviews.innerHTML = '';

  for (var i = 0; i < jsonData.length; i++) {
    reviews.innerHTML += `
                <div class="container">
                <div>
                    <div id="carousel-reviews" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="item active">
                                <div class="col-md-4 col-sm-6">
                                    <div class="block-text rel zmin">
                                    <small class="text-muted">Dátum: ${jsonData[i].feedbackDate.replace('T', ' ')}</small>
                                        <div class="mark">My rating: <span class="rating-input"><span data-value="0"
                                         class="glyphicon glyphicon-star"></span><span
                                         data-value="1" class="glyphicon glyphicon-star"></span><span data-value="2"
                                         class="glyphicon glyphicon-star"></span><span
                                         data-value="3" class="glyphicon glyphicon-star"></span><span data-value="4"
                                         class="glyphicon glyphicon-star-empty"></span><span
                                         data-value="5" class="glyphicon glyphicon-star-empty"></span>  </span></div>
                                        <p>${jsonData[i].feedback}</p>
                                        <ins class="ab zmin sprite sprite-i-triangle block"></ins>
                                    </div>
                                    <br>
                                    <div class="person-text rel">
                                    <a title="" href="#">${jsonData[i].user.name}</a>
                                </div>
                            </div>
                         </div>
                     </div>
                </div>
           </div>
      </div>


                `;
  }
}


function handleAddToBasketButton() {
  var address = (new URL(document.location)).searchParams.get('address');
  var url = '/basket';
  fetch(url, {
    method: 'GET'
  })
    .then(function (response) {
      console.log(response);
      if (response.ok == false) {
        window.location.href = '/login';
      } else {
        return response.json();
      }
    })
    .then(function (jsonData) {
      for (var i = 0; i < jsonData.length; i++) {
        if (jsonData[i].address == address) {
          alert('A termék már a kosárban van!');
          return;
        }
      }
      addToBasket();
    });
}

function addToBasket() {
  var quantity = document.querySelector('#inputQuantity').value;
  var address = (new URL(document.location)).searchParams.get('address');
  var url = '/basket/' + address;

  var request = {
    'pieces': quantity
  };

  fetch(url, {
    method: 'POST',
    body: JSON.stringify(request),
    headers: {
      'Content-type': 'application/json'
    }
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
      if (jsonData != -1) {
        addGoToBasketButton();
        basketRefresh();
      }
    });
}

function addGoToBasketButton() {
  var container = document.querySelector('#basketButton');
  container.innerHTML = '';
  container.innerHTML +=
        '<a href="basket.html"><button type="button" class="btn btn-primary btn-lg">Irány a kosár</button></a>';
}


function showProduct(jsonData) {
  var code = document.getElementById('code');
  var name = document.getElementById('name');
  var manufacture = document.getElementById('manufacturer');
  var price = document.getElementById('price');

  code.innerHTML = jsonData.code;
  name.innerHTML = jsonData.name;
  manufacture.innerHTML = jsonData.manufacture;
  price.innerHTML = jsonData.price;
}

function newReview() {
console.log("velemeny");
var dateNow = new Date(Date.now()).toISOString().substring(0,19);
var reviewButton = document.getElementById('review-button');
var reviewText = document.getElementById('review-text');
console.log(user);
console.log(product);
var request =
                    {
                            "feedbackDate": dateNow,
                            "feedback": reviewText.value,
                            "rating": 5,
                            "user": {user},
                            "product": {product}
                        }
            fetch("/feedback" , {
                    method: "POST",
                    body: JSON.stringify(request),
                    headers: {
                        "Content-type": "application/json"
                    }
                }).then(function (response) {
                                      return response.json();
                                  }).
                              then(function (jsonData) {
                              console.log(jsonData);
                              });
                              return false;

}

function showProductNotFound(jsonData) {
    var productText = document.getElementById("product-text");
    var pageNotFound = document.getElementById("page-not-found");
    var picture = document.getElementById("picture");
    var reviews = document.getElementById("reviews");
    pageNotFound.innerHTML = ` <br>
                                <div>
                                <h2>Sajnos ilyen termékkel nem rendelkezünk...</h2>
                                </div><br>
                                <div class="d-flex justify-content-center"><img src="https://vignette.wikia.nocookie.net/kenny-the-shark/images/2/24/KTS_Gallery_570x402_08.jpg/revision/latest/scale-to-width-down/310?cb=20130523023812"></div>
                               <br>`
    productText.innerHTML = "";
    picture.innerHTML = "";
    reviews.innerHTML = "";
}

