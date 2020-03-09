var getFromCookie = function(name) {
    var cookie = decodeURIComponent(document.cookie);
    var cookies = cookie.split(';');

    for (var i = 0; i < cookies.length; i++) {

    }

    console.log("Could not load cookie: " + name);
    return "";
};