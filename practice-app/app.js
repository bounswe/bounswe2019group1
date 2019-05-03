const express = require('express');
const cors = require('cors');

var app = express();
app.use(cors()); // for all routes

var port = process.env.PORT || 8080;

app.use(express.static(__dirname + "./front-end"));
app.use('/login',require('./back-end/routes/login'));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
