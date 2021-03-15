function weekValidate(){
	var a=document.getElementById("numWeek").value
	var b=document.getElementById("date").value
	var send = true;
	console.log(b);
	if(a=== undefined || a=== null ||isEmpty(a)){
		alert("Please enter week number");
		console.log(b)
		send= false;
	}
	
	if(b=== undefined || b=== null ||isEmpty(b)){
		alert("Please select a date");
		send= false;
	}

	if(a==0){
		alert("Week number cannot choose 0");
		send= false;
	}
	return send;
}
// Validation
function planValidate(){
	var a=document.getElementById("title").value
	var send = true;

	if(a=== undefined || a=== null ||isEmpty(a)){
		alert("Please enter a title");
		send= false;
	}
	
	return send;
}

function isEmpty(obj) {
    return !obj || Object.keys(obj).length === 0;
}
//Date
$(document).ready(function() {		
	// Restrict past date
	var today = new Date().toISOString().split('T')[0];
	document.getElementById("date").setAttribute('min', today);
	
	
    <c:forEach var="week" items="${Weeks}">
    	<c:if test="${not empty week.plan}">
    		document.getElementById("collapse${week.weekID}").className = "panel-collapse collapse in";
    	</c:if>
    </c:forEach>
	})	

// Text format	
$( function() {    
    <c:forEach var="week" items="${Weeks}">
    	<c:forEach var="plan" items="${week.plan}">
        	var description = '${plan.description}'; 
        	var d = description.replace(/%&/g, '\n');
        	var note = '${plan.notes}';
        	var n= 	note.replace(/%&/g, '\n');
        	$("#description${plan.planID}").val(d);
        	$("#note${plan.planID}").val(n);
    	</c:forEach>
	</c:forEach>
    
    <c:forEach var="iplan" items="${icebox.plan}">
    	var description1 = '${iplan.description}'; 
    	var d1 = description1.replace(/%&/g, '\n');
    	var note1 = '${plan.notes}';
        var n1= note1.replace(/%&/g, '\n');
        $("#description${iplan.planID}").val(d1);
    	$("#note${iplan.planID}").val(n1);
	</c:forEach>
} );
// Completion bar
$( function() {   
    <c:forEach var="week" items="${Weeks}">
		var completed =0;
		var total=0;
		var percentage=0;
    	<c:if test="${not empty week.plan}">
	    	<c:forEach var="plan" items="${week.plan}">
	        	if ('${plan.status}' == "Finished"){
	        		completed++;
	        	}
	        	total++;
	    	</c:forEach>

	    	percentage = (completed/total)*100;
	    	console.log(percentage);
	    	$("#progress${week.weekID}").css("width", percentage+"%");
	    	$("#progressValue${week.weekID}").text(percentage+"% ("+completed+"/"+total+")");
	    	if(percentage==0){
	    		document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-warning"
	    		$("#progress${week.weekID}").css("width", "100%");
	    	}
	    	if(percentage<100 && percentage>0){
	    		document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-info"
	    	}
    	</c:if>
    	

    	<c:if test="${empty week.plan}">
    		$("#progress${week.weekID}").css("width", "100%");
	    	$("#progressValue${week.weekID}").text("0% ("+completed+"/"+total+")");
	    	document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-warning"
    	</c:if>
	</c:forEach>
} );
//Reference: https://gist.github.com/Sudheendra123/e43683253f82b199eab95efbd5e63c62
$(document).ready(function() {		
	//column checkbox select all or cancel
	$("input.select-all-ice").click(function () {
	    var checked = this.checked;
	    $("input.select-item-ice").each(function (index,item) {
	        item.checked = checked;
	    });
	});
	
	<c:forEach var="week" items="${Weeks}">
	$("input.select-all-${week.weekID}").click(function () {
	    var checked = this.checked;
	    $("input.select-item-${week.weekID}").each(function (index,item) {
	        item.checked = checked;
	    });
	});
	</c:forEach>
});
// Finished validation
function getStatus(selectObject){
	var value = selectObject.value;	
	var a =document.getElementById("weeklink").value;
	if(value=="Finished"){
		if(a==""||a===null || a==undefined){
			console.log("1");
			  $("#weeklink").attr("required", true);
		}
	}
	else{
		$("#weeklink").attr("required", false);
	}
	
}