$( function() {
    
    var aim = '${Proposal.project_aim}';
    var a = aim.replace(/%&/g, '\n');
    
    var relativeWork = '${Proposal.project_relativeWork}';
    var rw = relativeWork.replace(/%&/g, '\n');
    
    var objective = '${Proposal.project_objective}'; 
    var obj = objective.replace(/%&/g, '\n');
    
    var preliminary_architecture = '${Proposal.preliminary_architecture}'; 
    var pa = preliminary_architecture.replace(/%&/g, '\n');
    
    var project_methodology = '${Proposal.project_methodology}'; 
    var pm = project_methodology.replace(/%&/g, '\n');
    
    var project_risk = '${Proposal.project_risk}'; 
    var pr = project_risk.replace(/%&/g, '\n');
    
    var project_data = '${Proposal.project_data}'; 
    var pd = project_data.replace(/%&/g, '\n');
    
    $("#aim").val(a); 
    $("#relativework").val(rw);
    $("#objective").val(obj);
    $("#pa").val(pa);
    $("#methodology").val(pm);
    $("#risk").val(pr);
    $("#data").val(pd);
    
    
} );

//Referenced from: https://stackoverflow.com/questions/573381/simple-autosave-for-spring-mvc-form
$(document).ready($('.form-control').change(function() {
	   $.ajax({
	    type : "post",
	    url : "/autoSave",
	    cache : false,
	    data : $('#edit').serialize(),
	    success : function(response) {
	     var obj = JSON.parse(response);
	     $("#alert").text(JSON.stringify(obj));
	     $("#alert").addClass("alert-success");
	    },
	    error : function() {
	     alert('Error while request..');
	    }
	   });
}));
