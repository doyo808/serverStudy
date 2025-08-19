function confirmUrl(url) {
	if (window.confirm('진짜로?')) {
		location.href = url;
	}
}