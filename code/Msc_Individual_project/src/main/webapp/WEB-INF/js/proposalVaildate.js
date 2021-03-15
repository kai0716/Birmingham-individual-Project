function isEmpty(obj) {
    return !obj || Object.keys(obj).length === 0;
}
 function proposalValidate(){
	var a=document.getElementById("title").value
	var b=document.getElementById("aim").value
	var c=document.getElementById("relativework").value
	var d=document.getElementById("objective").value
	var e=document.getElementById("pa").value
	var f=document.getElementById("methodology").value
	var g=document.getElementById("risk").value
	var send = true;
	console.log(b);
	if(a=== undefined || a=== null ||isEmpty(a)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(b=== undefined || b=== null ||isEmpty(b)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(c=== undefined || c=== null ||isEmpty(c)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(d=== undefined || d=== null ||isEmpty(d)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(e=== undefined || e=== null ||isEmpty(e)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(f=== undefined || f=== null ||isEmpty(f)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	if(g=== undefined || g=== null ||isEmpty(g)){
		alert("Please Fill out all the column");
		send= false;
		return send;
	}
	

	return send;
}