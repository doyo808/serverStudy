console.log("연결됨3");

function moveTo(url) {
	location.href = url;
}

document.addEventListener('DOMContentLoaded', () => {
	const affectedRowsDiv = document.getElementById('affectedRowsDiv');
	console.log("affectedRows =", affectedRows);

	if (affectedRows === 0) {
		affectedRowsDiv.classList.add('zero');
	} else {
		affectedRowsDiv.classList.remove('zero');
	}
});