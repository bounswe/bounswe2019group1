const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');

// Returns login.html page to client
router.get('/', (req, res) => {
    res.sendFile("login.html",{root:__dirname+"../../../front-end"})
});

/*
    When user request logging in this will be executed.
    Receives username and password from client and checks it on database.
    According to existence of account, database result will be returned to client.
 */
router.post('/', async (req, res) => {

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

module.exports = router;

// Checks existence of user
async function login(username, password) {
    res = await dbfuncs.findAllRecords("app_user", {username: username, password: password});
    if(res.length == 0)
        return false;
    else
        return true;
}
