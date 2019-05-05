module.exports = router

const express = require('express');
const router = express.Router();
const dbfuncs = require('../../database/database');

router.get('/', (req, res) => {
    res.sendFile("events.html",{root:__dirname+"../../../front-end"})
});

module.exports = router;

async function getAllEvents() {
    res = await dbfuncs.findAllRecords("app_events");
    if(res.length == 0)
        return false; // There is no event.
    else
        return res; // All events are fetched from database.
}