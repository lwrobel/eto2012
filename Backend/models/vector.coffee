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
		
	getAngle: ->
		if(0<x && 0<=y)
			return Math.atan(y/x) * 180 / Math.PI
		if(x<0 && 0<=y)
			return 180 - Math.atan(-y/x) * 180 / Math.PI
		if(x<0 && y<=0)
			return 180 + Math.atan(y/x) * 180 / Math.PI
		if(0<x && y<=0)
			return 360 - Math.atan(-y/x) * 180 / Math.PI
		if(y==0)
			return 0
		if(y>0)
			return 90
		270

module.exports = [Vector]
