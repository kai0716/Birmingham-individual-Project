function loginAjax() {
    var email = $('#email').val();
    var password = $('#password').val();
    console.log(email);
	var send = false;
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	  $.ajax({
		  async:false,
    	  type: "POST",
    	  url: "/loginValidate",
    	  data: "&email="+ email +"&password=" + password,
    	  success: function(response){
    		  			if(response.status == "SUCCESS"){
    		  					send = true;
    		  					
    		  			}
    		  			else{console.log(response.result);
    		  				 errorInfo = "";
    		  				 for(i =0 ; i < response.result.length ; i++){
    		  				 errorInfo += "<br>" + (i + 1) +". " + response.result[i].code;
    		  				 }
    		  				 
    		  				$('#error').html(errorInfo);
    		  				$('#info').hide('slow');
    		  				$('#error').show('slow');
    		  			}
    			   },
	  })
	  return send;
}