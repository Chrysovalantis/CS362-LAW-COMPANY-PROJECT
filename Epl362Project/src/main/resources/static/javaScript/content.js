$(document).ready(function () {

  $('#pencilRec').on('click', function () {
    $("#recDescription").toggle('slow');
  });



  $('#pencilLeg').on('click', function () {
    $("#opinionDescription").toggle('slow');
  });

  $(".btn-appointment").on('click', function () {
    var appId = $(this).attr('name');
    window.location.replace("http://localhost:8080/consultation/" + appId);
  });

});

//CALENDAR

var clientToChange;

$(document).ready(function () {
  $('#editClient').change(function () {
    $('#changeClient').prop("disabled", false);
    clientToChange=this.value;
    var fullname=$("#editClient option:selected").text();
    var names=fullname.split(' ');
    var name=names[0];
    var surname=names[1];
    $('#clientNewName').val(name);
    $('#clientNewSurname').val(surname);
  });
});