var _ = require("underscore");
var moment = require('moment');
// require imports module using jvm-npm.js.
// jvm-npm will be loaded by class Utils

var einheit = {
    "Packung": "Pkg.",
    "Stück": "Stk.",
    "Flaschen": "Fl."
};

//camel : takes the body from the message (json)
var b = JSON.parse(request.getBody(Java.type("java.lang.String").class))

// Filter positions where 'lieferbar' == true
var lieferbar = _.filter(b.positionen, function(p) {
    return p.lieferbar;
});

request.body = JSON.stringify({
    "id": b.nr,
    "datum": moment(b.datum, 'DD-MM-YYYY').format('YYYY-MM-DD'),
    "kunde": b.kunde,
    "adresse": b.adresse,
    "pos": _.map(lieferbar, function(p, index) {
        return {
            "nr": index + 1,
            "menge": p.menge,
            "einheit": einheit[p.einheit],
            "artikel": p.beschreibung,
            "preis": p.preis,
        }
    }),
    "gesamt-preis": _.reduce(lieferbar, function(memo, p) {
        return memo + p.preis * p.menge;
    }, 0)
})