$(document).ready(function(){
    $.post("/api/get-current",function (data) {
        $("#currentUser").html(data["email"]);
        $("#currentRoles").html(data["rolesByString"]);
    })
    $.post("/api/get-all", function (data) {
       let tbl_body = "";
        $.each(data, function() {
            let tbl_row = '<tr class="user' + this["id"] +'">';
            tbl_row += "<td>"+this["id"]+"</td>";
            tbl_row += "<td>"+this["firstName"]+"</td>";
            tbl_row += "<td>"+this["lastName"]+"</td>";
            tbl_row += "<td>"+this["age"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_row += "<td>"+this["rolesByString"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_body += tbl_row+"</tr>";
            alert(tbl_row)
        })
        $("#usersTable").append(tbl_body);
    })
});
