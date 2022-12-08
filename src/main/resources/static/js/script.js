
/**=======================================================
 * WEATHER appeared
 * =======================================================*/

let w = document.querySelector('.div_weather');
$('.link_weather').on('click', function (e) {

    if (w.style.display==="block") {
        w.style.display = "none";

    } else {
        w.style.display = "block";
    }

});


/**=====================================================
 * Add to bucket without redirect
 * ====================================================*/



function addToBucket(id) {
    // e.preventDefault();
    // let idProduct = $('#id').val();
    if (id != null) {
        $.ajax({
            url: "http://localhost:8080/ajax/product/add/to/bucket/" + id,
            method: "POST",
            data: {"id": id},
            contentType: 'application/json',
            success: function (data) {

                alert("Code: " + data.responseCode + " , " + data.message);
                counterPlus(data.amount);
            },
            error: function (data) {
                alert("Error code: " + data.responseCode + " , " + data.message);
            }
            // cache:false,
            // processData: false,
            // contentType:false
        });
    } else {
        alert("Id is null!");
    }
    return false;

}

const txtCounterInBucket = document.querySelector('#counter_in_bucket');

//let counterInBucket = +$('#counter_in_bucket');

function counterPlus(number) {

    txtCounterInBucket.innerHTML = number;
}


/**==================================================================
 * CHECK FORM AND CONFIRMATION BY EMAIL
 * ================================================================*/

function checkFormAndConfirmationByEmail(event) {
    let username = $('#username').val();
    let email = $('#email').val();
    let address = $('#address').val();
    let password = $('#password').val();
    let phone = $('#phone').val();

    let user = {
        "username": username,
        "email": email,
        "address": address,
        "password": password,
        "phone": phone
    };
    let jsonUser = JSON.stringify(user);

    let form = document.querySelector(".form_registration");
    let formData = new FormData(form);

    console.log(username + email + address + password + phone);
    $.ajax({
        url: "http://localhost:8080/email/confirm",
        method: "POST",
        data: jsonUser,
        contentType: 'application/json',
        dataType: "json",
        success: function (data) {

            alert("Code: " + data.responseCode + " , " + data.message);
        },
        error: function (data) {
            alert("Error code: " + data.responseCode + " , " + data.message);
        }


    });

}


/**=========================================================
 * REGISTRATION
 * ========================================================*/
function registration(e) {
    let username = $('#username').val();
    let email = $('#email').val();
    let address = $('#address').val();
    let password = $('#password').val();
    let phone = $('#phone').val();


    let user = {
        "username": username,
        "email": email,
        "address": address,
        "password": password,
        "phone": phone
    };
    let jsonUser = JSON.stringify(user);

    let form = document.querySelector(".form_registration");
    let formData = new FormData(form);

    console.log(username + email + address + password + phone);
    $.ajax({
        url: "http://localhost:8080/users/registration",
        method: "POST",
        data: jsonUser,
        contentType: 'application/json',
        dataType: "json",
        success: function (data) {

            alert("Code: " + data.responseCode + " , " + data.message);
        },
        error: function (data) {
            alert("Error code: " + data.responseCode + " , " + data.message);
        }


    });

}

/**============================================================
 * SHOW PHONE
 ==============================================================*/
// $('#win').hide('5000');
// $('.img2').fadeOut('5000');
// $('.treasure').slideUp('5000');


$('#get_phone').on("click", function () {
    let isNone = $('.phone_number').css('display');
    if (isNone == 'none') {

        $('.phone_number').css("display", "block").slideUp(5000);
    } else {
        $('.phone_number').css("display", "none");
    }

});

/**============================================================
 * SHOW EMAIL
 ==============================================================*/

$('#get_email').on("click", function () {
    let isNone = $('.email_address').css('display');
    if (isNone == 'none') {

        $('.email_address').css("display", "block").slideUp(5000);
    } else {
        $('.email_address').css("display", "none");
    }

});

/**============================================================
 * SHOW ADDRESS
 ==============================================================*/

$('#get_address').on("click", function () {
    let isNone = $('.address_shop').css('display');
    if (isNone == 'none') {

        $('.address_shop').css("display", "block").slideToggle(5000);
    } else {
        $('.address_shop').css("display", "none");
    }

});

/**=========================================================================
 * NEXT IMAGE
 * ========================================================================*/

let image = document.querySelector('#image_product');
let image2 = document.querySelector('#image_product2');
let image3 = document.querySelector('#image_product3');

let count2 = 1;
$('.button_next').on('click', function (e) {

    if (count2 == 1) {
        image.style.display = "none";
        image2.style.display = "block";
        image3.style.display = "none";
    }
    if (count2 == 2) {
        image.style.display = "none";
        image2.style.display = "none";
        image3.style.display = "block";
    }
    if (count2 == 3) {
        image.style.display = "block";
        image2.style.display = "none"
        image3.style.display = "none";
    }
    count2++;

    if (count2 >= 4) {
        count2 = 1;
    }
});


let count3 = 0;
const counter = document.querySelector('span');

$('#add_button').on('click', function (e) {
    setCounterInSpan(++count3);
});

function setCounterInSpan(number) {
    counter.innerHTML = number;
};



/**============================================================
 * CREDIT CART BUCKET.html
 * ============================================================*/
var CreditCard = (function() {

    var init = function() {
        $('.credit-card.front input, .credit-card.front  select').on('focus', front_focus);
        $('.credit-card.back input').on('focus', back_focus);
    };

    var front_focus = function() {
        $('.credit-card.back').addClass('unfocused');
        $(this).closest('.credit-card').removeClass('unfocused');
    };

    var back_focus = function() {
        $('.credit-card.front').addClass('unfocused');
        $(this).closest('.credit-card').removeClass('unfocused');
    };

    return {
        init: init
    }

}());

$(CreditCard.init);

$('#btn_pay').on("click", function (e){
    if ($('.credit_cart_general').is(':hidden')) {
        $('.credit_cart_general').css({'display': 'block','animation': 'showBlock 1s linear forwards'});
        $('.order_bucket_form').css('padding-top', '380px').slideDown('slow');
        $('#block_confirm').css('display','block');
    }else {
        $('.credit_cart_general').css("display",'none');
        $('.order_bucket_form').css('padding-top', '0').slideDown('slow');
        $('#block_confirm').css('display','none');
    }
});

/***==========================================================
 *APPEAR icon card
 *==========================================================*/
$('.tbx-number-0').on('input',function (event){
    let n1=$('.tbx-number-0').val();
    const icon = $('.visa_icon');
    if (+(n1)>4000&&+(n1)<5000) {
        icon.text('Visa');
    } else if(+(n1)>5000&&+(n1)<6000){
        icon.text('Mastercard');
    }
    else {
        icon.text('');
    }
});


/**===============================================================
 * VALIDATE MASTERCARD
 * ==============================================================*/
function isValid_MasterCard_Number(MasterCard_Number) {
    // Regex to check valid
    // MasterCard_Number
    let regex = new RegExp(/^5[1-5][0-9]{14}|^(222[1-9]|22[3-9]\\d|2[3-6]\\d{2}|27[0-1]\\d|2720)[0-9]{12}$/);

    // if MasterCard_Number
    // is empty return false
    if (MasterCard_Number == null) {
        return "false";
    }

    // Return true if the MasterCard_Number
    // matched the ReGex
    if (regex.test(MasterCard_Number) == true) {
        return "true";
    }
    else {
        return "false";
    }
}


$('#form_credit_cart').submit( function (e){
    e.preventDefault();
   const n1= $('.tbx-number-0').val();
   const n2= $('.tbx-number-1').val();
   const n3= $('.tbx-number-2').val();
   const n4= $('.tbx-number-3').val();
   const cardNumber=n1+n2+n3+n4;
if (+(n1)>5000&&+(n1)<6000){
 if(isValid_MasterCard_Number(cardNumber)===true){
     alert("Your card has been successfully validated!");
 }else {
     alert("Еhe entered card details are not correct. Try again!");
 }

    if (+(n1)>4000&&+(n1)<5000){
        let p='/^4[0-9]{12}(?:[0-9]{3}){0,2}$/';
        if(cardNumber.test(p)){
            alert("Your card has been successfully validated!");
        }else {
            alert("Еhe entered card details are not correct. Try again!");
        }
    }
}
   const month=$('.drp-expiration-month').val();
   const year=$('.drp-expiration-year').val();
   const ccv=$('.tbx-ccv').val();
    console.log(cardNumber+' month:'+month+'  year: 20'+year+' ccv:'+ccv);


});

/**VISA("^4[0-9]{12}(?:[0-9]{3}){0,2}$"),
 MASTERCARD("^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$"),
 AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
 DINERS_CLUB("^3(?:0[0-5]\\d|095|6\\d{0,2}|[89]\\d{2})\\d{12,15}$"),
 DISCOVER("^6(?:011|[45][0-9]{2})[0-9]{12}$"),
 JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
 CHINA_UNION_PAY("^62[0-9]{14,17}$");*/