net = require('net')

class GameServer
  constructor: ->
    @HOST = '192.168.1.100' #use your local server ip
    @PORT = 6969

    @gameState = {'aaa':'bbb', 'ccc':'ddd'} #TODO

    @server = net.createServer (socket) =>
      remoteAddress = socket.remoteAddress
      remotePORT = socket.remotePort

      console.log("CONNECTED: #{remoteAddress}:#{remotePORT}")

      socket.on 'data', (data) => 
        console.log("DATA #{remoteAddress}:#{data}")
        json = JSON.parse(data.toString())
        console.log(json)
        if json.type == 'get' and json.object == 'gameState'
          socket.write JSON.stringify {status: 'ok', object: 'gameState', data: @gameState}
        else
          socket.write JSON.stringify {status: 'error', error: 'badRequest'}

      socket.on 'close', (data) =>
        console.log("CLOSED: #{remoteAddress}:#{remotePORT}")

  listen: =>
    console.log("Server listening on #{@HOST}:#{@PORT}")
    @server.listen(@PORT, @HOST)

module.exports = [GameServer]
