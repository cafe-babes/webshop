window.onload = function () {
  fetchProduct();
};
console.log((new URL(document.location)).searchParams.get('address'));

var product;
var user;
var feedbacks;

$.getJSON('/user', json => {
if(json.id != 0){
var userId = json.id;
fetchUser(userId);
}
  if (json.role != 'VISITOR') {
    document.querySelector('#addToBasketButton').hidden = false;
  }
});

function fetchUser(userId) {
  var url = '/users/' + userId;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
     user = jsonData;
    });
  return false;
}


function fetchProduct() {
  var address = (new URL(document.location)).searchParams.get('address');
  var url = '/product/' + address;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
      if (jsonData.status == 'NOT_OK') {
        showProductNotFound(jsonData);
      } else {
        product = jsonData;
        var productId = jsonData.id;
        fetchFeedbacks(productId);
        showProduct(jsonData);
      }
    });
  return false;
}


function fetchFeedbacks(productId) {
  var url = '/feedback/' + productId;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (jsonData) {
    feedbacks = jsonData;
      showFeedbacks(jsonData);
    });
}


function showFeedbacks(jsonData) {
  var feedbacks = document.getElementById('feedbacks');
  feedbacks.innerHTML = '';

  for (var i = 0; i < jsonData.length; i++) {
    feedbacks.innerHTML += `
      <div class="container">
          <div>
              <div id="carousel-feedbacks" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner">
                      <div class="item active">
                          <div class="col-md-4 col-sm-6">
                              <div class="block-text rel zmin">
                              <div class="ad-right" id="feedback-and-delete-buttons">
                              <button onclick="editFeedback(${jsonData[i].id})" id="editFeedback#"+${jsonData[i].id} class="btn btn-success"><i class='fas fa-pen-alt'></i></button>
                              <button onclick="deleteFeedback(${jsonData[i].id})" id="deleteFeedback#"+${jsonData[i].id} class="btn btn-danger"><i class='fas fa-trash-alt'></i></button></div>
                                  <small class="text-muted">Dátum: ${jsonData[i].feedbackDate.replace('T', ' ')}</small>
                                  <div class="mark">Értékelés: ${jsonData[i].rating}<span class="rating-input"><span
                                          data-value="0"
                                          class="glyphicon glyphicon-star"></span><span
                                          data-value="1" class="glyphicon glyphicon-star"></span><span data-value="2"
                                                                                                       class="glyphicon glyphicon-star"></span><span
                                          data-value="3" class="glyphicon glyphicon-star"></span><span data-value="4"
                                                                                                       class="glyphicon glyphicon-star-empty"></span><span
                                          data-value="5" class="glyphicon glyphicon-star-empty"></span>  </span>
                                  </div>
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

function newFeedback() {

            if(typeof user === "undefined"){
                        alert("Be kell jelentkeznie az értékeléshez");
                        return;
                    }

            var feedbackButton = document.getElementById('feedback-button');
            var date = new Date(Date.now());
            date.setHours(date.getHours()+1)
            var dateNow = date.toISOString().substring(0,19);
            var feedbackText = document.getElementById('feedback-text');
            var rating = parseInt(document.querySelector('.stars').getAttribute('data-rating'));

            console.log(dateNow);
            console.log(feedbackText.value);
            console.log(rating);
            console.log(user);
            console.log(product);

                    var request =
                                   {
                                   	"feedbackDate": dateNow,
                                       "feedback": feedbackText.value,
                                       "rating": rating,
                                       "user": {
                                       "id": user.id,
                                       "name": user.name,
                                       "email": null,
                                       "userName": user.userName,
                                       "password": user.password,
                                       "enabled": user.enabled,
                                       "role": user.role,
                                       "userStatus": user.userStatus
                                   },
                                       "product": {
                                       "id": product.id,
                                       "code": product.code,
                                       "address": product.address,
                                       "name": product.name,
                                       "manufacture":product.manufacture,
                                       "price": product.price,
                                       "productStatus": product.productStatus,
                                       "category": {
                                           "id": product.category.id,
                                           "name": product.category.name,
                                           "ordinal": 1
                                       }
                                   }
                                   }

            fetch("/feedback" , {
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
                                    if (jsonData.resultStatusEnum == "OK") {
                                      fetchProduct();
                                      alert(jsonData.message);
                                    } else {
                                        alert(jsonData.message);
                                    }
                                });
                                return false;
            }

function deleteFeedback(feedbackId){

        if(typeof user === "undefined"){
            alert("Be kell jelentkeznie az értékelés törléséhez");
            return;
        }

        var feedback;
        for(var i = 0; i<feedbacks.length; i++){
                if(feedbacks[i].id == feedbackId){
                        feedback = feedbacks[i];
                        break;
                }
        }

        console.log("feedback-hez tartozó user id-ja = " +feedback.user.id);
        console.log("a bejelentkezett user id-ja = "+ user.id);

            if(user.id == feedback.user.id){

                     fetch("/feedback/" + feedback.id, {
                                               method: "DELETE",
                                           })
                                           .then(function (response) {
                     //                          document.getElementById("message-div").setAttribute("class", "alert alert-success");
                     //                          document.querySelector("#message-div").innerHTML = name + " sikeresen törölve!"
                                               fetchProduct();
                                               });
            }else{
                alert("Ez nem az Ön értékelése, ezért nem törölheti");
            }

}

function editFeedback(feedbackId){
        if(typeof user === "undefined"){
                    alert("Be kell jelentkeznie az értékelés módosításához");
                    return;
                }
                alert("Fejlesztés alatt...");
}

function showProductNotFound(jsonData) {
    var productText = document.getElementById("product-text");
    var pageNotFound = document.getElementById("page-not-found");
    var picture = document.getElementById("picture");
    var feedbacks = document.getElementById("feedbacks");
    pageNotFound.innerHTML = ` <br>
                                <div>
                                <h2>Sajnos ilyen termékkel nem rendelkezünk...</h2>
                                </div><br>
                                <div class="d-flex justify-content-center"><img src="https://vignette.wikia.nocookie.net/kenny-the-shark/images/2/24/KTS_Gallery_570x402_08.jpg/revision/latest/scale-to-width-down/310?cb=20130523023812"></div>
                               <br>`
    productText.innerHTML = "";
    picture.innerHTML = "";
    feedbacks.innerHTML = "";
}

//Init Star Rating System
        document.addEventListener('DOMContentLoaded', function(){
            let stars = document.querySelectorAll('.star');
            stars.forEach(function(star){
                star.addEventListener('click', setRating);
            });

            let rating = parseInt(document.querySelector('.stars').getAttribute('data-rating'));
            let target = stars[rating - 1];
            target.dispatchEvent(new MouseEvent('click'));
        });
        function setRating(ev){
            let span = ev.currentTarget;
            let stars = document.querySelectorAll('.star');
            let match = false;
            let num = 0;
            stars.forEach(function(star, index){
                if(match){
                    star.classList.remove('rated');
                }else{
                    star.classList.add('rated');
                }
                //are we currently looking at the span that was clicked
                if(star === span){
                    match = true;
                    num = index + 1;
                }
            });
            document.querySelector('.stars').setAttribute('data-rating', num);
        }