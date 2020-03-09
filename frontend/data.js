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