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