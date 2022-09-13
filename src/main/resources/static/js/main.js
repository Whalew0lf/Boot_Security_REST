const roles = [{"id":1,"name":"ROLE_USER"},{"id":2,"name":"ROLE_ADMIN"}];

$(document).ready(function(){
    alert("загрузка страницы");
    $.get("/api/users/current",function (data) {
        $("#currentUser").html(data["email"]);
        $("#currentRoles").html(data["rolesByString"]);
        fillUserTable();
        showAdminPage();
    });

});

$("#createForm").on("submit", function (e) {
    e.preventDefault();
    const json = {"role" : []};
    let array = $("#createForm").serializeArray();
    $.each(array, function () {
        if(this.name == "role") {
            json[this.name].push(roles[Number(this.value) - 1]);
        } else {
            json[this.name] = this.value || "";
        }
    });
    $.ajax({
        url: '/api/users',
        type: 'POST',
        data: JSON.stringify(json),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (data) {
            $("#usersTable").append(createUserRow(data));
            showAdminPage();}
    });
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
    $.get("/api/users/all", function (data) {
        let tbl_body = "";
        $.each(data, function() {
            tbl_body += createUserRow(this);
        });
        $("#usersTable").append(tbl_body);
    })
}

function createUserRow(user) {
    let tbl_row = '<tr class="userRow" id="user' + user["id"] +'">';
    tbl_row += "<td>"+user["id"]+"</td>";
    tbl_row += "<td>"+user["firstName"]+"</td>";
    tbl_row += "<td>"+user["lastName"]+"</td>";
    tbl_row += "<td>"+user["age"]+"</td>";
    tbl_row += "<td>"+user["email"]+"</td>";
    tbl_row += "<td>"+user["rolesByString"]+"</td>";
    tbl_row += "<td>"+user["email"]+"</td>";
    tbl_row += "<td>"+user["email"]+"</td>"+"</tr>";
    return tbl_row;
}
