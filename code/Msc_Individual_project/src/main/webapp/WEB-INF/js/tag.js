var tags = [];
function addTag(tag) {
    tags.push(tag);
}

function removeTag(tag) {
    tags = tags.filter(el => el !== tag);
}
$('#tags').val(tags);

//Reference: https://gist.github.com/Sudheendra123/e43683253f82b199eab95efbd5e63c62
$(document).ready(function() {		
	//column checkbox select all or cancel
	$("input.select-all").click(function () {
	    var checked = this.checked;
	    $("input.select-item").each(function (index,item) {
	        item.checked = checked;
	    });
	});
});