var net = require('net');

var HOST = '127.0.0.1';
var PORT = 1234;

net.createServer(function(sock) {
   
    sock.on('data', function(data) {
        
    	sock.write(factorial(data));
        
    });
    
}).listen(PORT, HOST);

function factorial(number) {

	if( !parseInt(number) )
		return "";
		
	var fact = 1;
	
	for(var i=2; i<=number; ++i)
		fact*=i;
	
	return fact.toString()+'\n\r';
}