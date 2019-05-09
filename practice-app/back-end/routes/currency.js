const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');
const http = require('https');

router.get('/', (req, res) => {
    res.sendFile("currency.html",{root:__dirname+"../../../front-end"})
});

router.post('/',  async (req, res) => {
    const currency = JSON.parse(await get_currency());
    res.status(200).end(JSON.stringify(currency));
});


async function get_currency() {
    return new Promise(
        function (resolve, reject) {
            
var headers = {
    'Accept': 'Application/xml',
    'Authorization': 'Client guest:guest'
};
var buffer = '';
var options = {
    host: 'api.tradingeconomics.com',
    port: 80,
    path: '/markets/currency',
    headers: headers
};
callback = function(response) {
    response.on('data', function (chunk) {
    buffer += chunk;
});
response.on('end', function () {
    resolve(buffer);
    //
});
}
  
var req = http.get(options, callback).end();

});

}

module.exports = router;
