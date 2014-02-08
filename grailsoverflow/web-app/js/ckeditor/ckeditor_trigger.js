CKEDITOR.replace('CKEditor').on('required', function( evt ) {
    $("#js_contentRequired").fadeIn(1000);
    evt.cancel(); // Prevent submit.
});