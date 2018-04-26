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

    addRecord(formData, "clients/add")

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

    addRecord(formData, "recomentations/add")

  });
});

$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#createCaseForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "createCase/add")

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

    addRecord(formData, "branches/add")

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

    addRecord(formData, "caseTypes/add")

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

    addRecord(formData, "desagrements/add")

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

    addRecord(formData, "legalOpinions/add")

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

    addRecord(formData, "changeRequests/add")

  });
});

$(document).ready(function () {

  $('#lockClient').click(function (e) {

    e.preventDefault();

    // Prevent form submission which refreshes page

    // Serialize data
    var formData = objectifyForm($('#editClientForm').serializeArray());
    formData = JSON.stringify(formData);

    console.log(formData);

    // Make AJAX request
    $.ajax({
      url: "lockClient",
      type: "post",
      data: formData,
      contentType: "application/json",
      success: function (result) {
        swal(
          'Done!',
          'Client Locked',
          'success'
        ).then(function () {
          location.reload();
        });

      },
      error: function (jqXHR, status, err) {
        console.log(jqXHR);
        console.log(status);
        console.log(err);
        alert('Failed!');
      }
    });

  });

});


$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#newAppointmentForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    // Make AJAX request
    $.ajax({
      url: "newAppointment",
      type: "post",
      data: formData,
      contentType: "application/json",
      success: function (result) {
        swal(
          'Done!',
          'Record Added',
          'success'
        ).then(function () {
          location.reload();
        });

      },
      error: function (jqXHR, status, err) {
        console.log(jqXHR);
        console.log(status);
        console.log(err);
        alert('Failed!');
      }
    });

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


//consultation----------------------------------------------------------
$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#consultationForm').submit(function (e) {

    // Prevent form submission which refreshes page
    e.preventDefault();

    // Serialize data
    var formData = objectifyForm($(this).serializeArray());
    formData = JSON.stringify(formData);

    addRecord(formData, "addConsultation");


  });
});


// consultation----------------------------------------------------------