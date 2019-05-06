const express = require('express');
const router = express.Router();
const http = require('https');
const dbfuncs = require('../../database/database');

router.get('/',  (req, res) => {
    res.sendFile("main.html",{root:__dirname+"../../../front-end"})
});

module.exports = router;
