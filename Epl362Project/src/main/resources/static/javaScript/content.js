


$(document).ready(function(){

    $('#pencilRec').on('click',function(){
    	$("#recDescription").toggle('slow');
    });



    $('#pencilLeg').on('click',function(){
    	$("#opinionDescription").toggle('slow');
    });

    $(".btn-appointment").on('click',function(){
      var appId=$(this).attr('name');
      window.location.replace("http://localhost:8080/consultation/"+appId);
    });
    
});

//CALENDAR
