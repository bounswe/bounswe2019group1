const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');

router.get('/', (req, res) => {
    res.sendFile("signup.html",{root:__dirname+"../../../front-end"})
});

router.post('/', async (req, res) => {

    console.log(req.query);

    //res.status(200);

    var success = await signup(req.query.username, req.query.password);
    console.log(success);
    res.status(200).end(JSON.stringify({success: success}));
    //res.end(JSON.stringify({a:1, b:23}));
    //...password check
    //.. bla bla

    //res.status(200).redirect("./profile")

});

module.exports = router;

async function signup(username, password) {
    res = await dbfuncs.findAllRecords("app_user", {username: username});
    if (res.length == 0){
        await dbfuncs.insertOneRecord("app_user", {username: username, password: password});
        return true;
    } else
        return false;
}