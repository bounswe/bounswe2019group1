const moc = require('mocha');
const chai = require('chai');
const expect = require('expect');
const dbfunc = require('./database/database');

moc.describe ('TEST CASES', function() {

    moc.describe('DATABASE', function() {
        it("Adding a element to database", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    resolve(dbfunc.insertOneRecord('test_collection', {test: "test"}));
                }, 200);
            });

            var result = await testPromise;
            result = JSON.parse(result);
            expect(result.n).toEqual(1);
        });

        it("Can find a element in database", async function() {
            var testPromise = new Promise(function(resolve, reject) {
                setTimeout(function() {
                    resolve(dbfunc.findOneRecord('test_collection', {test: "test"}));
                }, 200);
            });

            var result = await testPromise;
            expect(result.test).toEqual("test");
        });

    });



});