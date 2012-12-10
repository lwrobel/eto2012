var net = require('net');

net.createServer(function(socket) {
   
    socket.on('data', function(data) {
        
    	socket.write((2*parseInt(data)).toString());
        
    });
    
}).listen(8888, 'localhost');