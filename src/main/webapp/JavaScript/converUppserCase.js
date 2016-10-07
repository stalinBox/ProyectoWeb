// PASAR A MAYUSCULAS
function aMayusculas(obj, id) {
	obj = obj.toUpperCase();
	document.getElementById(id).value = obj;
}
// BLOQUEAR LA PANTALLA CUANDO ESTA ENE SPERA
$(document).ready(function() {
	$('#demo2').click(function() {
		$.blockUI({
			css : {
				border : 'none',
				padding : '15px',
				backgroundColor : '#000',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .5,
				color : '#fff'
			}
		});

		setTimeout($.unblockUI, 2000);
	});
});

function monitor(data) {
	var loading = document.getElementById("image");
	if (data.status == "begin") {
		loading.style.display = "block";
	} else if (data.status == "success") {
		loading.style.display = "none";
	}
}
