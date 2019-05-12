const moc = require('mocha');
const chai = require('chai');
const expect = require('expect');
const dbfunc = require('./database/database');
const https = require('https');

const IP_HOST = "https:localhost:8080";

moc.describe ('TEST CASES', function() {

    moc.describe('DATABASE', function() {
        it("Adding a element to database", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    resolve(dbfunc.insertOneRecord('test_collection', {test: "test"}));
                }, 20);
            });

            var result = await testPromise;
            result = JSON.parse(result);
            expect(result.n).toEqual(1);
        });

        it("Finding a element in database", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    resolve(dbfunc.findOneRecord('test_collection', {test: "test"}));
                }, 20);
            });

            var result = await testPromise;
            expect(result.test).toEqual("test");
        });
    });

    moc.describe('API', function () {
        it("Check whether the news response is empty or not", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
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
                }, 200);
            });
            var result = await testPromise;
            result = JSON.parse(result).length;
            expect(result).not.toEqual(0);
        });

        it("Check whether the articles response is empty or not", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
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
                }, 200);
            });
            var result = await testPromise;
            result = JSON.parse(result).length;
            expect(result).not.toEqual(0);
        });

    });
});