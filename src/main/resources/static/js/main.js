const roles = [{"id":1,"name":"ROLE_USER"},{"id":2,"name":"ROLE_ADMIN"}];
let deleteModal = new bootstrap.Modal(document.getElementById("deleteModal"), {});
let editModal = new bootstrap.Modal(document.getElementById("editModal"), {});

$(document).ready(function(){
    $.get("/api/users/current",function (data) {
        $("#currentUser").html(data["email"]);
        $("#currentRoles").html(data["rolesByString"]);
        fillUserTable();
        showAdminPage();
    });

});

$("#createForm").on("submit", function (el) {
    el.preventDefault();
    let json = {"role" : []};
    let array = $("#createForm").serializeArray();
    $.each(array, function () {
        if(this.name == "role") {
            json[this.name].push(roles[this.value - 1]);
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

$("#confirmDeleteButton").on("click", function () {
    let id = $(this).attr('userid');
    $.ajax({
        url: "/api/users/"+id,
        type: 'DELETE',
        async: false,
        success: function (data) {
            $("#user"+id).remove();
            deleteModal.hide();
        }
    });
});

$("#editForm").on("submit", function (el) {
    alert("функция");
    el.preventDefault();
    let json = {"role" : []};
    let array = $("#editForm").serializeArray();
    console.log(array);
    $.each(array, function () {
        if(this.name == "role") {
            json[this.name].push(roles[this.value - 1]);
        } else {
            json[this.name] = this.value || "";
        }
    });
    $.ajax({
        url: '/api/users',
        type: 'PUT',
        data: JSON.stringify(json),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (data) {
            $("#user"+data["id"]).replaceWith(createUserRow(data));
            editModal.hide();
        }
    });
});

$("#userLink").on("click",function () { showUserPage();});



$("#newUserLink").on("click",function () { showUserCreatePage();});

$("#adminLink").on("click",function () {
    fillUserTable();
    showAdminPage();
});

$("#userTableLink").on("click",function () {showAdminPage();});

$(document).on("click", ".removeUserButton", function () {
    $.get("/api/users/"+$(this).attr("delUserId"),function (data) {
        $("#deleteFormId").attr("value", data["id"]);
        $("#deleteFormFirstName").attr("value", data["firstName"]);
        $("#deleteFormLastName").attr("value", data["lastName"]);
        $("#deleteFormAge").attr("value", data["age"]);
        $("#deleteFormEmail").attr("value", data["email"]);
        $("#confirmDeleteButton").attr('userid', data["id"]);
        deleteModal.show();
    });
});

$(document).on("click", ".editUserButton", function () {
    $.get("/api/users/"+$(this).attr("editUserId"),function (data) {
        $("#editFormId").attr("value", data["id"]);
        $("#editFormFirstName").attr("value", data["firstName"]);
        $("#editFormLastName").attr("value", data["lastName"]);
        $("#editFormAge").attr("value", data["age"]);
        $("#editFormEmail").attr("value", data["email"]);
        editModal.show();
    });
});

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
    tbl_row += "<td><button class='btn btn-info text-light editUserButton'edituserid="+user['id']+">Edit</button></td>";
    tbl_row += "<td><button class='btn btn-danger removeUserButton' deluserid="+user['id']+">Delete</button></tr>";
    return tbl_row;
}
