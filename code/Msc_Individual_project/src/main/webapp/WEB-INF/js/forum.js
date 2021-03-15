$( function() {
<c:forEach items="${AllForum}" var="forum">
  var date = new Date();
  var text ='${forum.createdDate}';
  var textArray1 = text.split(" "); 
  var d1= textArray1[0].split("-");
  var d2= textArray1[1].split(":");
  var d3= d2[2].split(".");
  console.log(d2);
  date.setFullYear(parseInt(d1[0]));
  date.setMonth(parseInt(d1[1])-1);
  date.setDate(parseInt(d1[2]));
  date.setHours(parseInt(d2[0]));
  date.setMinutes(parseInt(d2[1]));
  date.setSeconds(parseInt(d3[0]));
  date.setMilliseconds(parseInt(d3[1]));

  var seconds = Math.floor((new Date() - date) / 1000);  
  var year =60*60*24*365;
  var month =60*60*24*30;
  var day = 60*60*24;
  var hour = 60*60;
  var minute =60;
  var interval = seconds / year;
  var stop= false;
console.log(seconds);
  if (interval > 1 &&stop != true) {
	$("#ago${forum.forumID}").html(Math.floor(interval)+" years ago");
    stop = true;
  }
  
  interval = seconds / month;
  if (interval > 1 && stop != true) {
	  $("#ago${forum.forumID}").html(Math.floor(interval)+" months ago");
	  stop = true;
  }
  
  interval = seconds / day;
  if (interval > 1 && stop != true) {
	  $("#ago${forum.forumID}").html(Math.floor(interval)+" days ago");
	  stop = true;
  }
  
  interval = seconds / hour;
  if (interval > 1 && stop != true) {
	  $("#ago${forum.forumID}").html(Math.floor(interval)+" hours ago");
	  stop = true;
  }
  
  interval = seconds / minute;
  if (interval > 1 && stop != true) {
	  $("#ago${forum.forumID}").html(Math.floor(interval)+" minutes ago");
	  stop = true;
  }
  if (interval < 1 && stop != true) {
	  $("#ago${forum.forumID}").html("0 minutes ago");
	  stop = true;
  }
  </c:forEach>
  return stop;
})

$( function() {
  var date = new Date();
  var text ='${Forum.createdDate}';
  var textArray1 = text.split(" "); 
  var d1= textArray1[0].split("-");
  var d2= textArray1[1].split(":");
  var d3= d2[2].split(".");
  console.log(d2);
  date.setFullYear(parseInt(d1[0]));
  date.setMonth(parseInt(d1[1])-1);
  date.setDate(parseInt(d1[2]));
  date.setHours(parseInt(d2[0]));
  date.setMinutes(parseInt(d2[1]));
  date.setSeconds(parseInt(d3[0]));
  date.setMilliseconds(parseInt(d3[1]));

  var seconds = Math.floor((new Date() - date) / 1000);  
  var year =60*60*24*365;
  var month =60*60*24*30;
  var day = 60*60*24;
  var hour = 60*60;
  var minute =60;
  var interval = seconds / year;
  var stop= false;
console.log(seconds);
  if (interval > 1 &&stop != true) {
	$("#ago").html(Math.floor(interval)+" years ago");
    stop = true;
  }
  
  interval = seconds / month;
  if (interval > 1 && stop != true) {
	  $("#ago").html(Math.floor(interval)+" months ago");
	  stop = true;
  }
  
  interval = seconds / day;
  if (interval > 1 && stop != true) {
	  $("#ago").html(Math.floor(interval)+" days ago");
	  stop = true;
  }
  
  interval = seconds / hour;
  if (interval > 1 && stop != true) {
	  $("#ago").html(Math.floor(interval)+" hours ago");
	  stop = true;
  }
  
  interval = seconds / minute;
  if (interval > 1 && stop != true) {
	  $("#ago").html(Math.floor(interval)+" minutes ago");
	  stop = true;
  }
  if (interval < 1 && stop != true) {
	  $("#ago").html("0 minutes ago");
	  stop = true;
  }

  return stop;
})

$( function() {
<c:forEach items="${Content}" var="content">
  var date = new Date();
  var text ='${content.responseDate}';
  var textArray1 = text.split(" "); 
  var d1= textArray1[0].split("-");
  var d2= textArray1[1].split(":");
  var d3= d2[2].split(".");
  console.log(d2);
  date.setFullYear(parseInt(d1[0]));
  date.setMonth(parseInt(d1[1])-1);
  date.setDate(parseInt(d1[2]));
  date.setHours(parseInt(d2[0]));
  date.setMinutes(parseInt(d2[1]));
  date.setSeconds(parseInt(d3[0]));
  date.setMilliseconds(parseInt(d3[1]));

  var seconds = Math.floor((new Date() - date) / 1000);  
  var year =60*60*24*365;
  var month =60*60*24*30;
  var day = 60*60*24;
  var hour = 60*60;
  var minute =60;
  var interval = seconds / year;
  var stop= false;
console.log(seconds);
  if (interval > 1 &&stop != true) {
	$(".time${content.contentID}").html(Math.floor(interval)+" years ago");
    stop = true;
  }
  
  interval = seconds / month;
  if (interval > 1 && stop != true) {
	  $(".time${content.contentID}").html(Math.floor(interval)+" months ago");
	  stop = true;
  }
  
  interval = seconds / day;
  if (interval > 1 && stop != true) {
	  $(".time${content.contentID}").html(Math.floor(interval)+" days ago");
	  stop = true;
  }
  
  interval = seconds / hour;
  if (interval > 1 && stop != true) {
	  $(".time${content.contentID}").html(Math.floor(interval)+" hours ago");
	  stop = true;
  }
  
  interval = seconds / minute;
  if (interval > 1 && stop != true) {
	  $(".time${content.contentID}").html(Math.floor(interval)+" minutes ago");
	  stop = true;
  }
  if (interval < 1 && stop != true) {
	  $(".time${content.contentID}").html("0 minutes ago");
	  stop = true;
  }
  </c:forEach>
  return stop;
})