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
    console.log(id);
    if (id != null) {
        $.ajax({
            url: "http://localhost:8080/ajax/product/add/to/bucket/"+id,
            method: "POST",
            data: {"id": id},
            contentType: 'application/json',
            success: function (data) {
                alert(data);

            },
            error: function (data) {
                alert(data);
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


// $.ajax({
//     type: "POST",
//
//     url: "@{/products/add/to/bucket}",
//
//     context: document.body,
//
//     success: function(){
//
//        echo("")
//
//     }
//
// });


/**
 let image=document.querySelector('#image_product');
 let image2=document.querySelector('#image_product2');
 let image3=document.querySelector('#image_product3');

 let count2=0;
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
        image2.style.display="none";
        image3.style.display="none";
        count2=0;

    }
    count2++;

});



 let count3=0;
 const counter=document.querySelector('span');

 $('#add_button').on('click',function (e) {
   setCounterInSpan(++count3);
});

 function setCounterInSpan(number){
    counter.innerHTML=number;}
 */