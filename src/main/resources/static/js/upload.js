window.addEventListener('load', init);

function init() {
    let uploadButton = document.getElementById('uploadButton');
    uploadButton.addEventListener('click', startUpload);
}

function startUpload() {
    var s = window.location.href.split("=")[1];
    console.log(s);
    let product_id = parseInt(window.location.href.split("=")[1]);
    console.log(product_id);

    let file = document.getElementById('fileToUpload').files[0];
    const formData = new FormData();

    formData.append('file', file);
    formData.append('productId', product_id);


    console.log(formData);
    fetch('/image', {
        method: 'POST',
        body: formData
    }).then(response => {
        console.log(response);
        return response.text();
    }).then(message => {
        console.log(message);
        document.getElementById('uploadForm').reset();
        let messageNode = document.getElementById('messageP');
//        messageNode.innerText = message;
    });
}


function bs_input_file() {
	$(".input-file").before(
		function() {
			if ( ! $(this).prev().hasClass('input-ghost') ) {
				var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
				element.attr("name",$(this).attr("name"));
				element.change(function(){
					element.next(element).find('input').val((element.val()).split('\\').pop());
				});
				$(this).find("button.btn-choose").click(function(){
					element.click();
				});
				$(this).find("button.btn-reset").click(function(){
					element.val(null);
					$(this).parents(".input-file").find('input').val('');
				});
				$(this).find('input').css("cursor","pointer");
				$(this).find('input').mousedown(function() {
					$(this).parents('.input-file').prev().click();
					return false;
				});
				return element;
			}
		}
	);
}
$(function() {
	bs_input_file();
});