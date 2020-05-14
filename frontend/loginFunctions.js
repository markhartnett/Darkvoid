var checkStudentLogin = function(username, password) {
    if (username === "" || password === "") {
        alert("Please enter a username and password");
        return;
    }

    return $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/login/student',
        data: JSON.stringify({username:String(username), password:String(password)}),
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
        type: 'POST',
        url: 'http://localhost:8080/login/staff',
        data: JSON.stringify({username:String(username), password:String(password)}),
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
        url: 'http://localhost:8080/students/' + id,
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

var getStaff = function(id) {
    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/staff/' + id,
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

