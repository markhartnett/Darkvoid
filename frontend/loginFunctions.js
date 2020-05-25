var checkStudentLogin = function(username, password) {
    if (username === "" || password === "") {
        alert("Please enter a username and password");
        return;
    }

    return $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/login/student',
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
        url: 'https://localhost:8443/login/staff',
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
        type: 'POST',
        url: 'https://localhost:8443/studentsId',
        data: JSON.stringify({id: String(id)}),
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

var getStaff = function(id) {
    return $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/staffId',
        data: JSON.stringify({id: String(id)}),
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            return result;
        }
    })
};

