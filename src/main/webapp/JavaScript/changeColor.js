window.onload = function() {
	var div = document.getElementsByClassName("fc-content");
	var m = null;
	for (var i = 0; i < div.length; i++) {
		var spans = div[i].getElementsByTagName("span");
		for (var j = 0; j < spans.length; j++) {
			m = spans[j].innerHTML;
			if (m.indexOf('Troquelado') != -1) {
				div[i].style.background = 'red';
			} else if (m.indexOf('Aparado') != -1) {
				div[i].style.background = 'blue';
			} else {
				(m.indexOf('Montaje') != -1)
				div[i].style.background = "#FF9D00";
			}
		}
	}
}
