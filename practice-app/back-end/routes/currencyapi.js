const express = require('express');
const router = express.Router();
var jsdom = require("jsdom");
const { JSDOM } = jsdom;
const { window } = new JSDOM();
const { document } = (new JSDOM('')).window;
global.document = document;
var $ = jQuery = require('jquery')(window);

// set endpoint and your access key
endpoint = 'latest'
access_key = 'e6bd540558679f6909d74efe2470aee6';

// get the most recent exchange rates via the "latest" endpoint:
$.ajax({
    url: 'https://data.fixer.io/api/' + endpoint + '?access_key=' + access_key,
    dataType: 'jsonp',
    success: function(json) {
        // exchange rata data is stored in json.rates
        alert(json.rates.GBP);
        // base currency is stored in json.base
        alert(json.base);
        // timestamp can be accessed in json.timestamp
        alert(json.timestamp);
    }
});

//
router.get('/currencyapi', (req, res) => {

    res.status(200).end(JSON.stringify({success: success}))
});

router.post('/currencyapi', async (req, res) => {

    //console.log(req.body);
    //res.status(200);

    var success = await login(req.body.username, req.body.password);
    console.log(success);
    res.status(200).end(JSON.stringify({success: success}));
    //res.end(JSON.stringify({a:1, b:23}));
    //...password check
    //.. bla bla

    //res.status(200).redirect("./profile")

});
