function addLinkToButton(button_id, url) {
	document.getElementById(button_id).addEventListener('click', (e) => {
		location.href = url;
	});
}


addLinkToButton('list_btn', './list');


function confirmDelete(url) {
	if( window.confirm("정말 삭제하시겠습니까?") ) {
		location.href = url;
	}
}