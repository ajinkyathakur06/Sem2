const http = require('http');
http.createServer((req, res) => {
    res.write("Hello, HTTP!");
    res.end();
}).listen(8080);
