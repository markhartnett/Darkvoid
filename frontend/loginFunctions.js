var checkStudentLogin = function(username, password) {
    if (username === "" || password === "") {
        alert("Please enter a username and password");
        return;
    }

    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/login/student/' + username + '/' + password,
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

var checkStaffLogin = function(username, password) {
    if (username === "" || password === "") {
        alert("Please enter a username and password");
        return;
    }

    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/login/staff/' + username + '/' + password,
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

var getStudent = function(id) {
    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/student/' + id,
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

