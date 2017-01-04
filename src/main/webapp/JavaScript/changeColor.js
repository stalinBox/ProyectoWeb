jQuery(document).ready(function($) {
	var div = document.getElementsByClassName('montajeMTN');
	for (var i = 0; i < div.length; i++) {
		div[i].style.background = 'red';
	}

	var div = document.getElementsByClassName('aparadoAPA');
	for (var i = 0; i < div.length; i++) {
		div[i].style.background = 'blue';
	}

	var div = document.getElementsByClassName('troqueladoTRQ');
	for (var i = 0; i < div.length; i++) {
		div[i].style.background = 'black';
	}
});
