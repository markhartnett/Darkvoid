// Returns array of objects
function getData(url){
    let data = [];
    $.ajax({
        type: 'GET',
        url: url,
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function(d) {
            $.each(d, function(key, value) {
                data.push(value);
            });
        }
    });
    return data;
}

function getSingleData(url){
    let data = null;
    $.ajax({
        type: 'GET',
        url: url,
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function(d) {
            data = d;
        }
    });
    return data;
}

// Accepts list of students/staff
// Returns dictionary
// key: nationality
// value: int
function getNationalities(url){
    data = getData(url)
    let nationalities = {};
    for (let i = 0; i < data.length; i++){
        let national = data[i].toLowerCase();
        if (national in nationalities){
            nationalities[national]++
        }
        else{
            nationalities[national] = 1
        }
    }
    return nationalities
}

// Accepts list of students/staff
// Returns dictionary
// key: gender
// value: int
function getGenders(url){
    data = getData(url)
    let genders = {};
    for (let i = 0; i < data.length; i++){
        let gender = data[i].toLowerCase();
        if (gender in genders){
            genders[gender]++
        }
        else{
            genders[gender] = 1
        }
    }
    return genders
}

// Accepts list of module enrolments
// Returns dictionary
// key: grade
// value: int
function getGrades(url){
    data = getData(url)
    let grades = {};
    for (let i = 0; i < data.length; i++){
        let g = data[i].toLowerCase();
        if (g in grades){
            grades[g]++
        }
        else{
            grades[g] = 1
        }
    }
    return grades
}

function myModules(id) {
    let data = [];
    $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/myModules',
        data: JSON.stringify({id: String(id)}),
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function(d) {
            $.each(d, function(key, value) {
                data.push(value);
            });
        }
    });
    return data;
}

// Accepts list of students/staff
// Returns array of usernames
function getUsernames(data){
    let usernames = [];
    for (let i = 0; i < data.length; i++){
        usernames[i]=data[i].username;
    }
    return usernames
}

const isNewUsername = function (username) {
    let data = null;
    $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/isNewUsername',
        data: JSON.stringify({username: String(username)}),
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function (result) {
            data = result;
        }
    });
    return data;
};