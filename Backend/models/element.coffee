[Vecotor] = require('./vector');

class Element
  constructor: (position) ->
		@position = new Vector(position.x, position.y)
		@velocity = new Vector(0,0) #to trzeba zmienić raczej ;P
		@acceleration = 0.2
		@maxVelocityValue = 15.0

	getPosition: ->
		@position

	getVelocity: ->
		@velocity
	
	getAcceleration: ->
		@acceleration

	getMaxVelocity: ->
		@maxVelocityValue

	setPosition: (vector) ->
		@position.set(vecotr.x, vector.y)

	setVelocity: (vector) ->
		@velocity.set(vector.x, vector.y)

	setAcceleration: (acceleration) ->
		@acceleration = acceleration

	setMaxVelocity: (value) ->
		@maxVelocityValue = value

	getRotation: ->
		@velocity.getAngle()

module.exports = [Element]
