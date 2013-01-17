net = require('net')

class GameServer
  constructor: ->
    @HOST = '127.0.0.1'
    @PORT = 6969

    @server = net.createServer (socket) =>
      remoteAddress = socket.remoteAddress
      remotePORT = socket.remotePort

      console.log("CONNECTED: #{remoteAddress}:#{remotePORT}")
      socket.write('Welcome!')

      socket.on 'data', (data) => 
        console.log("DATA #{remoteAddress}:#{data}")
        socket.write('You said "' + data + '"')

      socket.on 'close', (data) =>
        console.log("CLOSED: #{remoteAddress}:#{remotePORT}")

  listen: =>
    console.log("Server listening on #{@HOST}:#{@PORT}")
    @server.listen(@PORT, @HOST)

new GameServer().listen()


