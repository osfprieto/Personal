CC=gcc
FLAGS= -lcrypt

linux:	
	${CC} ${FLAGS} client.c blowfish.c -o client		
solaris:	
	${CC} ${FLAGS} client.c blowfish.c -o client -lnsl -lsocket -DWORDS_BIGENDIAN

