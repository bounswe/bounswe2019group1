const express = require('express');
const cors = require('cors');
const session = require('express-session');

var app = express();
app.use(cors()); // for all routes

var port = process.env.PORT || 8080;

app.use(express.static(__dirname + "./front-end"));
app.use('/login',require('./back-end/routes/login'));
app.use('/signup',require('./back-end/routes/signup'));
app.use(session({ secret: 'my secret', resave: false, saveUninitialized: false }));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
