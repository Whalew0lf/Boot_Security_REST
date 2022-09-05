$(document).ready(function(){
    $.post("/api/get-current",function (data) {
        $("#currentUser").html(data["email"]);
        $("#currentRoles").html(data["rolesByString"]);
    })
});
