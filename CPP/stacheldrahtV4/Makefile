CC=gcc
FLAGS= -lcrypt

linux:	
	${CC} ${FLAGS} mserv.c blowfish.c -O6 -o mserv
solaris:	
	${CC} ${FLAGS} mserv.c blowfish.c -o mserv -lnsl -lsocket -DWORDS_BIGENDIAN

