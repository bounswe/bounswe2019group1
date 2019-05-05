// JavaScript contact form Document
$(document).ready(function() {
	$('form#contact-form').submit(function() {
	$('form#contact-form .error').remove();
	var hasError = false;
	$('.requiredField').each(function() {
	if(jQuery.trim($(this).val()) == '') {
    var labelText = $(this).prev('label').text();
    $(this).parent().append('<span class="error">You forgot to enter your '+labelText+'</span>');
    $(this).addClass('inputError');
    hasError = true;
    } else if($(this).hasClass('email')) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(!emailReg.test(jQuery.trim($(this).val()))) {
    var labelText = $(this).prev('label').text();
    $(this).parent().append('<span class="error">You entered an invalid '+labelText+'</span>');
    $(this).addClass('inputError');
    hasError = true;
    }
    }
    });
    if(!hasError) {
    $('form#contact-form input.submit').fadeOut('normal', function() {
    $(this).parent().append('');
    });

     $("#loader").show();
        $.ajax({
            url: "form-process.php",
            type: "POST",
            data:  new FormData(this),
            contentType: false,
            cache: false,
            processData:false,
            success: function(data){
			  $('form#contact-form').slideUp("fast", function() { $(this).before('<div class="col-lg-8 mx-auto success text-center"><div class="success-box-top"><i class="icofont icofont-teacher"></i><h4>Email Successfully Sent</h4><p>Thank You! We will back to you soon</p></div><div class="success-box-bottom"><a href="#" class="btn-style btn-border btn-border-2">Go To Home</a></div></div>');
			  $("#loader").hide();
			  })
            }           
       });
	   
	   return false;
    }
 
   });
});