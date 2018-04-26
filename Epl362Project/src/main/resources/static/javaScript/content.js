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

function objectifyForm(formArray) { //serialize data function

  var returnArray = {};
  for (var i = 0; i < formArray.length; i++) {
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

//CALENDAR

var clientToChange;

$(document).ready(function () {
  $('#editClient').change(function () {
    clientToChange = this.value;
    var fullname = $("#editClient option:selected").text();
    var names = fullname.split(' ');
    var name = names[0];
    var surname = names[1];
    $('#clientNewName').val(name);
    $('#clientNewSurname').val(surname);
  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#clientForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addClient")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#recommendationForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addRecommendation")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#branchForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addBranch")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#caseTypeForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addCaseType")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#disagreementForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addDisagreement")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#legaOpinionForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addLegalOpinion")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#editClientForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "editClient")

  });
});

function addRecord(data, path) {

  // Make AJAX request
  $.ajax({
    url: path,
    type: "post",
    data: data,
    contentType: "application/json",
    success: function (result) {
      swal(
        'Done!',
        'Record Added',
        'success'
      ).then(function () {
        location.href = "addInfo";
      });

    },
    error: function (jqXHR, status, err) {
      console.log(jqXHR);
      console.log(status);
      console.log(err);
      alert('Failed!');
    }
  });

}