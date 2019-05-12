const express = require('express');
const router = express.Router();
const bfx = require('./bfx');
/*router.get('/ticker', (req, res) => {
    res.sendFile("ticker.html",{root:__dirname+"../../frontend"})
});*/

router.use('/ticker',function(req,res){
    bfx.ticker(req,res);
});

router.use('/stats',function(req,res){
    bfx.stats(req,res);
});
module.exports = router;