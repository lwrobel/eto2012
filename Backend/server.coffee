http = require("http")
url = require("url")

http.createServer((request, response) =>
  request.on 'end', => 
    get = url.parse(request.url, true).query
    x = parseInt get['data']
    response.writeHead 200, {'Content-Type': 'text/plain'}
    response.end "sqrt(#{x})=#{ Math.sqrt x }"
  ).listen 8080

console.log "server listen on 8080"
