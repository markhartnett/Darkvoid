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
function getNationalities(data){
    let nationalities = {};
    for (let i = 0; i < data.length; i++){
        let national = data[i].nationality.toLowerCase();
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
function getGenders(data){
    let genders = {};
    for (let i = 0; i < data.length; i++){
        let gender = data[i].gender.toLowerCase();
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
function getGrades(data){
    let grades = {};
    for (let i = 0; i < data.length; i++){
        let g = data[i].grade.toLowerCase();
        if (g in grades){
            grades[g]++
        }
        else{
            grades[g] = 1
        }
    }
    return grades
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