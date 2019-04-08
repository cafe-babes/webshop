window.addEventListener('load', init);

var productId = parseInt(window.location.href.split("=")[1]);
var imageContainer = document.querySelector("#image-container");

fetchImage(productId, 0);

function init() {
    let uploadButton = document.getElementById('uploadButton');
    uploadButton.addEventListener('click', startUpload);
    document.querySelector("#fileToUpload").addEventListener("change", function(){
        document.querySelector("[for='"+ this.id +"']").innerText = this.files[0].name;
    })
//    let resetButton = document.getElementById('resetButton');
//    resetButton.addEventListener('click', function(){
//        document.querySelector("#fileToUpload").addEventListener("change", function(){
//        document.querySelector("[for='" + this.id + "']").innerText = "";
//        document.querySelector("[for='" + this + "']").value = null;
//    });
}

function startUpload() {
    let file = document.getElementById('fileToUpload').files[0];
    const formData = new FormData();

    formData.append('file', file);
    formData.append('productId', productId);

    fetch('/image', {
        method: 'POST',
        body: formData
    }).then(response => {
        console.log(response);
        return response.text();
    }).then(message => {
        console.log(message);
        alert(message);
        document.getElementById('uploadForm').reset();
        let messageNode = document.getElementById('messageP');
        fetchImage(productId, 0);
//        messageNode.innerText = message;
    });
}

function fetchImage(productId, offset) {
    if(offset == 0)
        imageContainer.innerHTML = "";
    fetch('/image/' + productId + '/' + offset)
    .then(function(response) {
      if(response.status == 200) {
        imageContainer.innerHTML += `<div class="col-md-4">
                                          <div class="card mb-4 box-shadow">
                                              <img class="card-img-top" id='img-${offset}'
                                                   alt="surfboard image">
                                              <div class="card-body">
                                                  <p class="card-text">${response.headers.get("Content-Disposition").split("=")[1]}</p>
                                                  <small class="text-muted" id="file-type-${offset}">${response.headers.get("Content-Type")}</small>
                                                  <p><small class="text-muted" id="file-size-${offset}"></small></p>
                                                  <div class="d-flex justify-content-between align-items-center">
                                                      <div class="btn-group">
                                                          <button type="button" class="btn btn-lm btn-outline-secondary" onclick="deleteImage(${productId}, ${offset})">Törlés</button>
                                                      </div>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>`;

        return response.blob();
      }
    })
    .then(function(blob) {
        if(blob) {
            document.querySelector(`#file-size-${offset}`).innerHTML = Math.round(blob.size/1024 * 100) / 100 +" kB";
            document.querySelector(`#img-${offset}`).src = URL.createObjectURL(blob);
            fetchImage(productId, offset+1);
        }
    });
}

function deleteImage(productId, offset) {
    fetch('/image/' + productId + '/' + offset, {
      method: "DELETE"
    })
    .then(function(response){
      fetchImage(productId, 0);
      return response.text();
    })
    .then(function(message){
      console.log(message);
    });
}

//function bs_input_file() {
//	$(".input-file").before(
//		function() {
//			if ( ! $(this).prev().hasClass('input-ghost') ) {
//				var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
//				element.attr("name",$(this).attr("name"));
//				element.change(function(){
//					element.next(element).find('input').val((element.val()).split('\\').pop());
//				});
//				$(this).find("button.btn-choose").click(function(){
//					element.click();
//				});
//				$(this).find("button.btn-reset").click(function(){
//					element.val(null);
//					$(this).parents(".input-file").find('input').val('');
//				});
//				$(this).find('input').css("cursor","pointer");
//				$(this).find('input').mousedown(function() {
//					$(this).parents('.input-file').prev().click();
//					return false;
//				});
//				return element;
//			}
//		}
//	);
//}
//$(function() {
//	bs_input_file();
//});