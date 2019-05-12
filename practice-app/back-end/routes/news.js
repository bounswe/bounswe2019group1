const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');
const http = require('https');

// Returns news.html page to client
router.get('/', (req, res) => {
    res.sendFile("news.html",{root:__dirname+"../../../front-end"})
});

// Returns 10 latest news.
router.post('/',  async (req, res) => {
    const news = JSON.parse(await get_news());
    res.status(200).end(JSON.stringify(news));
});

// Requests news from Tradingeconomics API
async function get_news() {
    return new Promise(
        function (resolve, reject) {
            var headers = {
                'Accept': 'Application/xml',
                'Authorization': 'Client guest:guest'
            };
            var buffer = '';
            var options = {
                host: 'api.tradingeconomics.com',
                port: 443,
                path: '/news',
                headers: headers
            };
            options.path = options.path.replace (' ','%20');
            callback = function(response) {
                response.on('data', function (chunk) {
                    buffer += chunk;
                });
                response.on('end', function () {
                    resolve(buffer);
                    //console.log(JSON.parse(buffer));
                });
            };
            http.get(options, callback).end();
        });
}

module.exports = router;
