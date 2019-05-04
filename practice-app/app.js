const bodyParser = require('body-parser');
const express = require('express');
const cors = require('cors');

var app = express();
app.use(cors()); // for all routes

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var port = process.env.PORT || 8080;

app.use(express.static(__dirname + "./front-end"));
app.use('/login',require('./back-end/routes/login'));
app.use('/signup',require('./back-end/routes/signup'));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
