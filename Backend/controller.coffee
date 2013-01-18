[GameServer] = require('./server')

[AmmunitionLevel] = require('./models/ammunition_level')
[Colour] = require('./models/colour')
[ColourManager] = require('./models/colour_manager')
[Element] = require('./models/element')
[GameState] = require('./models/game_state')
[Map] = require('./models/map')
[Missile] = require('./models/missile')
[MoveState] = require('./models/move_state')
[Player] = require('./models/player')
[Spaceship] = require('./models/spaceship')
[Vector] = require('./models/vector')

class Controller
  constructor: ->
    new GameServer().listen()

new Controller()
