const moc = require('mocha');
const chai = require('chai');
const expect = require('expect');
const dbfunc = require('./database/database');
const https = require('https');
const http = require('http');

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
                }, 20);
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
                }, 20);
            });
            var result = await testPromise;
            result = JSON.parse(result).length;
            expect(result).not.toEqual(0);
        });

    });

    moc.describe('Written API', function () {
        it("Check whether the written API returns 200", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    var buffer = '';
                    callback = function(response) {
                        response.on('data', function (chunk) {
                            buffer += chunk;
                        });
                        response.on('end', function () {
                            resolve(buffer);
                            //console.log(JSON.parse(buffer).status);
                        });
                    };
                    http.get("http://ec2-35-166-169-167.us-west-2.compute.amazonaws.com:8080/news&articles/news", callback).end();
                }, 200);
            });
            var result = await testPromise;
            result = JSON.parse(result).status;
            expect(result).toEqual(200);
        });

        it("Check whether the written API article request without query, returns 20 articles", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    var buffer = '';
                    callback = function(response) {
                        response.on('data', function (chunk) {
                            buffer += chunk;
                        });
                        response.on('end', function () {
                            resolve(buffer);
                            //console.log(JSON.parse(buffer).status);
                        });
                    };
                    http.get("http://ec2-35-166-169-167.us-west-2.compute.amazonaws.com:8080/news&articles/articles", callback).end();
                }, 20);
            });
            var result = await testPromise;
            result = JSON.parse(result).news;
            expect(result.length).toEqual(20);
        });

        it("Check whether the written API news request without query, returns 10 news", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    var buffer = '';
                    callback = function(response) {
                        response.on('data', function (chunk) {
                            buffer += chunk;
                        });
                        response.on('end', function () {
                            resolve(buffer);
                            //console.log(JSON.parse(buffer).status);
                        });
                    };
                    http.get("http://ec2-35-166-169-167.us-west-2.compute.amazonaws.com:8080/news&articles/news", callback).end();
                }, 20);
            });
            var result = await testPromise;
            result = JSON.parse(result).news;
            expect(result.length).toEqual(10);
        });

    });
});