var http = require('http');
var fs = require('fs');
var express = require('express');

http.createServer(function (req, res) {
    fs.readFile('../front-end/login.html', function(err, data) {
        res.writeHead(200, {'Content-Type': 'text/html'});
        res.write(data);
        res.end();
    });
}).listen(8080);