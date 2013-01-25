class GameState
  constructor: -> 
    @players = {}

  json: =>
    buf=[]
    for k,v of @players
      buf.push v
    data = {
      players: buf
    }
    data

  removePlayer: (ID) =>
    delete @players[ID]

  updatePlayer: (player) =>
    @players[player.ID] = player

module.exports = [GameState]
