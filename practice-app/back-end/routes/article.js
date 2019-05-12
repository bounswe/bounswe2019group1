const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');
const http = require('https');

// Returns article.html page to client
router.get('/', (req, res) => {
    res.sendFile("article.html",{root:__dirname+"../../../front-end"})
});

// Returns latest 20 articles
router.post('/',  async (req, res) => {
    const articles = JSON.parse(await get_articles()).slice(0,10);
    res.status(200).end(JSON.stringify(articles));
});

// Requests articles from Tradingeconomics API
async function get_articles() {
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
                path: '/articles',
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
