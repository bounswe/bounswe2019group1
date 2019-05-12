const express = require('express');
const router = express.Router();
const https = require('https');


// Returns the news about country and category
router.get('/news', async (req, res) => {
    const articles = JSON.parse(await get_news()).slice(0,10);
    var q_country = req.query.country;
    var q_category = req.query.category;
    if(req.query.country === undefined)
        q_country = "NP";
    if(req.query.category === undefined)
        q_category = "NP";

    var response_object = {};
    var news = [];
    for (var i = 0; i<10;i++){
        if((q_category === articles[i].category || q_category === "NP") && (q_country === articles[i].country || q_country === "NP")) {
            var temp = {};
            temp.title =  articles[i].title;
            temp.description = articles[i].description;
            news.push(temp);
        }
    }
    response_object.status = 200;
    response_object.news = news;
    res.status(200).end(JSON.stringify(response_object));
});

// Returns the articles about country and category
router.get('/articles', async (req, res) => {
    const articles = JSON.parse(await get_articles());
    var q_country = req.query.country;
    var q_category = req.query.category;
    if(req.query.country === undefined)
        q_country = "NP";
    if(req.query.category === undefined)
        q_category = "NP";

    var response_object = {};
    var news = [];
    for (var i = 0; i<20;i++){
        if((q_category === articles[i].category || q_category === "NP") && (q_country === articles[i].country || q_country === "NP")) {
            var temp = {};
            temp.title =  articles[i].title;
            temp.description = articles[i].description;
            news.push(temp);
        }
    }
    response_object.status = 200;
    response_object.news = news;
    res.status(200).end(JSON.stringify(response_object));
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
            https.get(options, callback).end();
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
            https.get(options, callback).end();
        });
}