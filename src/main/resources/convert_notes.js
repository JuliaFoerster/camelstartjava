var _ = require("underscore");
// require imports module using jvm-npm.js.
// jvm-npm will be loaded by class Util

//camel aufruf: takes the body from the message (json)
//die eingelesen json datei die die route entlang wandert.
//die json datei wird geparst nach json
// also b ist ein geparsted json object.
var b = JSON.parse(request.getBody(Java.type("java.lang.String").class))


request.body = JSON.stringify({
    "postcodes": b.postcodes
})