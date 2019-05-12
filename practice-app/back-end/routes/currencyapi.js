const express = require('express');
const router = express.Router();
const http = require("http")
var jsdom = require("jsdom");
const { JSDOM } = jsdom;
const { window } = new JSDOM();
const { document } = (new JSDOM('')).window;

global.document = document;
var $ = jQuery = require('jquery')(window);

// set access key
const access_key = 'aabb98fbc1d445dd4fb18dfd6bc136df';

//
router.get('/', (req, res) => {
    res.sendFile("currencies.html",{root:__dirname+"../../../front-end"})
});

router.post('/', async (req, res) => {

    var currencies_json = JSON.parse(await get_currencies());
    console.log(currencies_json.rates);
    res.status(200).end(JSON.stringify(currencies_json));
});

async function get_currencies() {
    return new Promise(
        function (resolve, reject) {
            var buffer = '';

            callback = function(response) {
                response.on('data', function (chunk) {
                    buffer += chunk;
                });
                response.on('end', function () {
                    resolve(buffer);
                    //console.log(JSON.parse(buffer));
                });
            };
            http.get("http://data.fixer.io/api/latest?access_key="+ access_key, callback).end();
        });
}

module.exports = router;
