function route(pathname) {
  console.log("About to route a request for " + pathname);
}

exports.route = route;


var http = require("http");
var url = require("url");
var querystring = require("querystring");

function start() {
  function onRequest(request, response) {
    var pathname = url.parse(request.url).query;
	a = querystring.parse(response).text;
    console.log("Request for " + a + " received.");
    response.writeHead(200, {"Content-Type": "text/plain"});
    response.write("Hello World");
    response.end();
  }

  http.createServer(onRequest).listen(8888);
  console.log("Server has started.");
}
