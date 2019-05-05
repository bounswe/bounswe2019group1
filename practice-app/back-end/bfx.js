const express = require('express');
const api = express();
const request = require('request')

api.get('/', function(req,res) {

    const url = "https://api-pub.bitfinex.com/v2/";
    const word=req.query.word;
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



});


module.exports = api;
