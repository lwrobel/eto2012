net = require('net')
[GameState] = require('./models/game_state')

class GameServer
  constructor: ->
    @HOST = '192.168.0.11' #use your local server ip
    @PORT = 6969
    @gameState = new GameState()

    @server = net.createServer (socket) =>
      remoteAddress = socket.remoteAddress
      remotePORT = socket.remotePort

      console.log("CONNECTED: #{remoteAddress}:#{remotePORT}")

      socket.on 'data', (data) => 
        try
          console.log("DATA #{remoteAddress}")
          json = JSON.parse(data.toString())
          if json.type == 'get' and json.object == 'gameState'
            console.log 'get gameState'
            socket.write JSON.stringify {status: 'ok', object: 'gameState', data: @gameState.json()}
          else if json.type == 'put' and json.object == 'currentInstancePlayer'
            console.log 'put currentInstancePlayer'
            @gameState.updatePlayer(json.data)
          else
            console.log 'error'
            socket.write JSON.stringify {status: 'error', error: 'badRequest'}
        catch error
          console.log error
          socket.write JSON.stringify {status: 'error', error: 'badRequest'}

      socket.on 'close', (data) =>
        console.log("CLOSED: #{remoteAddress}:#{remotePORT}")

  listen: =>
    console.log("Server listening on #{@HOST}:#{@PORT}")
    @server.listen(@PORT, @HOST)

module.exports = [GameServer]
