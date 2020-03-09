var getFromCookie = function(name, cookie) {
    var cookies = cookie.split(';');

    for (var i = 0; i < cookies.length; i++) {
        var str = cookies[i];

        console.log("i: " + i + " str: " + str);
        console.log("Index: " + str.indexOf(name));
        if (str.indexOf(name) === 0) {
            return str.substring(name.length, str.length);
        }
    }

    console.log("Could not load cookie: " + name);
    return "";
};