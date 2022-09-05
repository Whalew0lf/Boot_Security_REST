$(document).ready(function(){
    $("#id").html("This is Hello World by JQuery");

});
$("#add").click( function( evnt ) {
    $.post("/api/get-all",function (data) {
        $("#id").html(data[1]["email"]);
    })

});
