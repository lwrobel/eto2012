#to test server, requests etc.

net = require('net')

class GameClient
  constructor: ->
    @HOST = '127.0.0.1'
    @PORT = 6969

    @client = new net.Socket()

    @client.connect @PORT, @HOST, =>
      console.log("CONNECTED")

    @client.on 'data', (data) =>
      console.log("DATA: #{data}")

    @client.on 'close', =>
      console.log('CLOSED')

  send: (data) =>
    @client.write(data)

  close: =>
    @client.destroy()

client = new GameClient()
setTimeout ( => client.send('Chuck Norris')), 10
setTimeout ( => client.close()), 1000
