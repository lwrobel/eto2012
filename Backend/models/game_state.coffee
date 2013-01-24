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

  updatePlayer: (player) =>
    @players[player.ID] = player

module.exports = [GameState]
