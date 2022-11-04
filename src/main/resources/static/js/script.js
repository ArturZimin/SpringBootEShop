
/**=======================================================
 * WEATHER
 * =======================================================*/
let count=0;
let w= document.querySelector('.div_weather');
$('.link_weather').on('click', function(e){

    if(count%2!=0){

        w.style.display="block";
        // $('.div_weather').css('display','block');
        count++;

    }else{
        $('.div_weather').css("display","none");
        count++;

    }

});