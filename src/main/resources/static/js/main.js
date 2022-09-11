const user = {id: 1, name: "ROLE_USER"};

const admin = {id: 2, name: "ROLE_ADMIN"};

$(document).ready(function(){
    $.post("/api/get-current",function (data) {
        $("#currentUser").html(data["email"]);
        $("#currentRoles").html(data["rolesByString"]);
        fillUserTable();
        showAdminPage();
    });

});

$("#createUserButton").on("click", function () {
    $.post("/api/add", $("#createForm").serialize());
});

$("#userLink").on("click",function () { showUserPage();});

$("#newUserLink").on("click",function () { showUserCreatePage();});

$("#adminLink").on("click",function () {
    fillUserTable();
    showAdminPage();
});

$("#userTableLink").on("click",function () {showAdminPage();});

function showUserCreatePage() {
    $("#usersTablePage").attr("hidden", "hidden");
    $("#userPage").attr("hidden", "hidden");
    $("#adminPage").removeAttr("hidden");
    $("#createPage").removeAttr("hidden");
    $("#userTableLink").removeClass("active");
    $("#newUserLink").addClass("active");
    $("#userLink").removeClass("active");
    $("#adminLink").addClass("active");
}
function showAdminPage() {
    $("#createPage").attr("hidden", "hidden");
    $("#userPage").attr("hidden", "hidden");
    $("#adminPage").removeAttr("hidden");
    $("#usersTablePage").removeAttr("hidden");
    $("#userTableLink").addClass("active");
    $("#newUserLink").removeClass("active");
    $("#userLink").removeClass("active");
    $("#adminLink").addClass("active");
}
function showUserPage() {
    $("#createPage").attr("hidden", "hidden");
    $("#usersTablePage").attr("hidden", "hidden");
    $("#adminPage").attr("hidden", "hidden");
    $("#userPage").removeAttr("hidden");
    $("#userTableLink").removeClass("active");
    $("#newUserLink").removeClass("active");
    $("#userLink").addClass("active");
    $("#adminLink").removeClass("active");
}

function fillUserTable() {
    $('.userRow').remove();
    $.post("/api/get-all", function (data) {
        let tbl_body = "";
        $.each(data, function() {
            let tbl_row = '<tr class="userRow" id="user' + this["id"] +'">';
            tbl_row += "<td>"+this["id"]+"</td>";
            tbl_row += "<td>"+this["firstName"]+"</td>";
            tbl_row += "<td>"+this["lastName"]+"</td>";
            tbl_row += "<td>"+this["age"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_row += "<td>"+this["rolesByString"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_row += "<td>"+this["email"]+"</td>";
            tbl_body += tbl_row+"</tr>";
        });
        $("#usersTable").append(tbl_body);
    })
}
