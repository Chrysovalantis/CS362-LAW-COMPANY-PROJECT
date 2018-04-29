$(document).ready(function() {

	$('#pencilRec').on('click', function() {
		$("#recDescription").toggle('slow');
	});

	$('#pencilLeg').on('click', function() {
		$("#opinionDescription").toggle('slow');
	});

	$(".btn-appointment").on('click', function() {
		var appId = $(this).attr('name');
		window.location.replace("http://localhost:8080/consultation/" + appId);
	});

});

function objectifyForm(formArray) { // serialize data function

	var returnArray = {};
	for (var i = 0; i < formArray.length; i++) {
		returnArray[formArray[i]['name']] = formArray[i]['value'];
	}
	return returnArray;
}

// CALENDAR

var clientToChange;

$(document).ready(function() {
	$('#editClient').change(function() {
		clientToChange = this.value;
		var fullname = $("#editClient option:selected").text();
		var names = fullname.split(' ');
		var name = names[0];
		var surname = names[1];
		$('#clientNewName').val(name);
		$('#clientNewSurname').val(surname);
	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#clientForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "clients/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#recommendationForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "recomentations/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#branchForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "branches/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#caseTypeForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "caseTypes/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#disagreementForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "desagrements/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#legaOpinionForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "legalOpinions/add")

	});
});

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#createCaseForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addRecord(formData, "clientCases/add")

	});
});

function editClient() {

	// Serialize data
	var formData = objectifyForm($('#editClientForm').serializeArray());
	formData = JSON.stringify(formData);

	// Make AJAX request
	$.ajax({
		url : "changeRequests/editClient",
		type : "put",
		data : formData,
		contentType : "application/json",
		success : function(result) {
			swal('Done!', 'Client Request Submited', 'success').then(function() {
				location.href = "addInfo";
			});

		},
		error : function(jqXHR, status, err) {
			console.log(jqXHR);
			console.log(status);
			console.log(err);
			alert('Failed!');
		}
	});

	//addRecord(formData, "changeRequests/editClient")

}

function lockClient() {

	// Serialize data
	var formData = objectifyForm($('#editClientForm').serializeArray());
	formData = JSON.stringify(formData);

	console.log(formData);

	// Make AJAX request
	$.ajax({
		url : "changeRequests/addLock",
		type : "post",
		data : formData,
		contentType : "application/json",
		success : function(result) {
			swal('Done!', 'Client Locked', 'success').then(function() {
				location.reload();
			});

		},
		error : function(jqXHR, status, err) {
			console.log(jqXHR);
			console.log(status);
			console.log(err);
			alert('Failed!');
		}
	});

}

$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#newAppointmentForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		// Make AJAX request
		$.ajax({
			url : "apointments/add",
			type : "post",
			data : formData,
			contentType : "application/json",
			success : function(result) {
				swal('Done!', 'Record Added', 'success').then(function() {
					location.reload();
				});

			},
			error : function(jqXHR, status, err) {
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
		url : path,
		type : "post",
		data : data,
		contentType : "application/json",
		success : function(result) {
			swal('Done!', 'Record Added', 'success').then(function() {
				location.href = "addInfo";
			});

		},
		error : function(jqXHR, status, err) {
			console.log(jqXHR);
			console.log(status);
			console.log(err);
			alert('Failed!');
		}
	});

}

// consultation----------------------------------------------------------
var staffId=0;
$(document).ready(function() {
	// Listen to submit event on the <form> itself!
	$('#consultationForm').submit(function(e) {

		// Prevent form submission which refreshes page
		e.preventDefault();

		// Serialize data
		var formData = objectifyForm($(this).serializeArray());
		formData = JSON.stringify(formData);

		addConsultation(formData, "/casesHistorys/addConsultation");

	});
});


function addConsultation(data, path) {

	// Make AJAX request
	$.ajax({
		url : path,
		type : "post",
		data : data,
		contentType : "application/json",
		success : function(result) {
			swal('Done!', 'Record Added', 'success').then(function() {
				location.href = "../legal_appointments/"+staffId;
			});

		},
		error : function(jqXHR, status, err) {
			console.log(jqXHR);
			console.log(status);
			console.log(err);
			alert('Failed!');
		}
	});

}
// consultation----------------------------------------------------------
//search-table------------------------------------------------------------------

$(document).ready(function(){
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });
});

//search-table------------------------------------------------------------------



// head-office -----------------------------------------------------------

$(document).ready(function() {
	$('.table-head').hide();

	$("#clientBranch").on('click', function() {
		$('.table-responsive').hide();
		console.log("#" + $(this).attr('name'));
		$("#" + $(this).attr('name')).show();
	});

	$("#legalOpinions").on('click', function() {
		$('.table-head').hide();
		console.log("#" + $(this).attr('name'));
		$("#" + $(this).attr('name')).show();
	});

	$("#clientCase").on('click', function() {
		$('.table-head').hide();
		console.log("#" + $(this).attr('name'));
		$("#" + $(this).attr('name')).show();
	});

	$("#clientDay").on('click', function() {
		$('.table-head').hide();
		console.log("#" + $(this).attr('name'));
		$("#" + $(this).attr('name')).show();
	});

	$("#recommentation").on('click', function() {
		$('.table-head').hide();
		console.log("#" + $(this).attr('name'));
		$("#" + $(this).attr('name')).show();
	});

});

// head-office -----------------------------------------------------------


// case-history -----------------------------------------------------------
function showCaseHistory(obj){
	$.ajax({url: "../case-history/"+obj.getAttribute("data-id"), success: function(result){
        $("#case-history").html(result);
    }});	
}

// case-history -----------------------------------------------------------
