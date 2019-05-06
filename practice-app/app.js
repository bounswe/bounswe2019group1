const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const session = require('express-session');
const path = require("path");

var app = express();
app.use(cors()); // for all routes
app.use(express.static("frontend"));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

var port = process.env.PORT || 8080;

app.use(express.static(path.join(__dirname, "front-end")));
app.use('/login',require('./back-end/routes/login'));
app.use('/signup',require('./back-end/routes/signup'));
app.use('/main',require('./back-end/routes/main'));
app.use('/news',require('./back-end/routes/news'));
app.use('/article',require('./back-end/routes/article'));
app.use(session({ secret: '23jfDer94b3re6ibk3d1mw8ac', resave: false, saveUninitialized: false }));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
