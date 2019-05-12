const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');

// Returns signup.html page to client
router.get('/', (req, res) => {
    res.sendFile("signup.html",{root:__dirname+"../../../front-end"})
});

/*
    When user tries to sign up this will be executed.
    Receives username and password from client and checks it on database.
 */
router.post('/', async (req, res) => {

    console.log(req.body);

    //res.status(200);

    var success = await signup(req.body.username, req.body.password);
    console.log(success);
    res.status(200).end(JSON.stringify({success: success}));
    //res.end(JSON.stringify({a:1, b:23}));
    //...password check
    //.. bla bla

    //res.status(200).redirect("./profile")

});

module.exports = router;

// Checks If such username exists, returns false. If not adds this user to database and returns true.
async function signup(username, password) {
    res = await dbfuncs.findAllRecords("app_user", {username: username});
    if (res.length == 0){
        await dbfuncs.insertOneRecord("app_user", {username: username, password: password});
        return true;
    } else
        return false;
}