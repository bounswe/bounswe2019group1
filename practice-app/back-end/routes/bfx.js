const request = require('request');
exports.ticker= async function(req,res) {
    const word=req.query.word;
    //const word=res.query.curr1+res.query.curr2;
    const options1 = {
        url: "https://api-pub.bitfinex.com/v2/"+"tickers?symbols=t"+word,
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Accept-Charset': 'utf-8',
            'User-Agent': 'my-reddit-client'
        }
    };
    const options2 = {
        url: "https://api-pub.bitfinex.com/v2/"+"tickers?symbols=ALL",
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Accept-Charset': 'utf-8',
            'User-Agent': 'my-reddit-client'
        }
    };
    console.log(word);
    if(word === undefined){
        //getting info about all tickers
        request(options2, function(err, response, body) {
            let json = JSON.parse(body);
            res.send(json);
            console.log(json);
        });
    }
    else{
        //getting info about one ticker
        request(options1, function(err, response, body) {
            let json = JSON.parse(body);
            res.send(json);
            console.log(json);
        });
    }
};
exports.stats= async function(req,res) {
    const word=req.query.word;
    //const word=res.query.curr1+res.query.curr2;
    const options1 = {
        //url: "https://api.bitfinex.com/v2/"+"stats1/pos.size:1m:t"+word+":long/hist", //version 2
        url:"https://api.bitfinex.com/v1/stats/"+word,    //version 1
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Accept-Charset': 'utf-8',
            'User-Agent': 'my-reddit-client'
        }
    };
    //getting info about statistics
    request(options1, function(err, response, body) {
            let json = JSON.parse(body);
            res.send(json);
            console.log(json);
    });
};
