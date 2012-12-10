var http = require("http");
var url = require("url");
var querystring = require("querystring");

http.createServer(function(request, response) {
  response.writeHead(200, {"Content-Type": "text/plain"});
  var query = url.parse(request.url).query;
  // querystring = querystring(string)["foo"];
  // querystring = url.parse(request.url).querystring["foo"];
  // querystring = url.parse(request.url).query["foo"].text;
  // querystring = querystring.parse(response).text;
  querystring = querystring.parse(response);
  // querystring = url.parse(request.url).querystring(string)["foo"];
  response.write("Hello World " + querystring);
  response.end();
}).listen(8888);