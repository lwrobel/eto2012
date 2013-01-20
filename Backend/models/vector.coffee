class Vector
	constructor: (@x, @y) ->

	add: (x,y) ->
		@x = @x + x
		@y = @y + y
		this
	
	sub: (x,y) ->
		@x = @x - x
		@y = @y - y
		this

	set: (x,y) ->
		@x = x
		@y = y
		this
		
module.exports = [Vector]
