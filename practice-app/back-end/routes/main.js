const express = require('express');
const router = express.Router();
const http = require('https');
const dbfuncs = require('../../database/database');

router.get('/',  (req, res) => {
    res.sendFile("main.html",{root:__dirname+"../../../front-end"})
});


// Returns latest 3 articles
router.get('/articles',  async (req, res) => {
    const articles = JSON.parse(await get_articles()).slice(0,3);
    res.status(200).end(JSON.stringify(articles));
});

// Returns 3 latest news.
router.get('/news',  async (req, res) => {
    const news = JSON.parse(await get_news()).slice(0,3);
    res.status(200).end(JSON.stringify(news));
});

module.exports = router;

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
