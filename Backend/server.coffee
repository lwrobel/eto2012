net = require('net')

class GameServer
  constructor: ->
    @HOST = '192.168.0.11' #use your local server ip
    @PORT = 6969

    @gameState = {
      players: [
          ID : 123
          missiles : []
          spaceship : 
            ID : 100
            position :
              y : 100
              x : 100
            height : 300
            width : 335
            rotation : 320
            acceleration : 0.5
            maxVelocityValue : 20
            velocity : 0
          colour: 
            value: 111
      ]
    }

    @server = net.createServer (socket) =>
      remoteAddress = socket.remoteAddress
      remotePORT = socket.remotePort

      console.log("CONNECTED: #{remoteAddress}:#{remotePORT}")

      socket.on 'data', (data) => 
        console.log("DATA #{remoteAddress}:#{data}")
        json = JSON.parse(data.toString())
        console.log(json)
        @gameState.players[0].spaceship.position.x=Math.floor(Math.random()*500)
        @gameState.players[0].spaceship.position.y=Math.floor(Math.random()*500)
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
