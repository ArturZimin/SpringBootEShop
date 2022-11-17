// $('.img_treasure').slideUp('5000');
//
// $('.treasure').slideDown('4000');
/**=======================================================
 * WEATHER appeared
 * =======================================================*/
let count = 0;
let w = document.querySelector('.div_weather');
$('.link_weather').on('click', function (e) {

    if (count % 2 != 0) {

        w.style.display = "block";
        // $('.div_weather').css('display','block');
        count++;

    } else {
        $('.div_weather').css("display", "none");
        count++;

    }

});


/**=====================================================
 * Add to bucket without redirect
 * ====================================================*/
// $('#add_button').on('click', function (e) {
//     addToBucket(e);
// });


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
    let username=$('#username').val();
    let email=$('#email').val();
    let address=$('#address').val();
    let password=$('#password').val();
    let phone=$('#phone').val();

let user={
    "username":username,
        "email":email,
    "address":address,
    "password":password,
    "phone":phone
    };
    let jsonUser = JSON.stringify(user);

    let form = document.querySelector(".form_registration");
    let formData = new FormData(form);

    console.log(username+email+address+password+phone);
    $.ajax( {
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


    let user={
        "username":username,
        "email":email,
        "address":address,
        "password":password,
        "phone":phone
    };
    let jsonUser = JSON.stringify(user);

    let form = document.querySelector(".form_registration");
    let formData = new FormData(form);

    console.log(username+email+address+password+phone);
    $.ajax( {
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
/**SEND OBJECT
 * var dataO = {
    numberId: "1",
    companyId : "531"
};
 var data0 = {numberId: "1", companyId : "531"};
 var json = JSON2.stringify(dataO);

 $.ajax({
    type: "POST",
    url: "TelephoneNumbers.aspx/DeleteNumber",
    data: json,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function(msg) {
        alert('In Ajax');
    }
});*/





 let image=document.querySelector('#image_product');
 let image2=document.querySelector('#image_product2');
 let image3=document.querySelector('#image_product3');

 let count2=1;
 $('.button_next').on('click', function(e){

    if(count2==1){
       image.style.display="none";
       image2.style.display="block";
        image3.style.display="none";
    }if(count2==2){
        image.style.display="none";
        image2.style.display="none";
        image3.style.display="block";
    }
    if(count2==3){
        image.style.display="block";
        image2.style.display="none"
        image3.style.display="none";
    }
    count2++;

    if (count2>=4){
        count2=1;
    }
});



 let count3=0;
 const counter=document.querySelector('span');

 $('#add_button').on('click',function (e) {
   setCounterInSpan(++count3);
});

 function setCounterInSpan(number){
    counter.innerHTML=number;}
