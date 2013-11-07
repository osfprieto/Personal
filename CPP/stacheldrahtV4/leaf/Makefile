# Tribe Flood Network Makefile
# 1999 by Mixter - PRIVATE

CC=gcc
FLAGS=-O6 -fomit-frame-pointer -s

linux:	
	${CC} ${FLAGS} td.c blowfish.c -D__LITTLE_ENDIAN_BITFIELD -static -DLINUX -o td -O6

solaris:	
	${CC} ${FLAGS} td.c blowfish.c -DSOLARIS -o td -O6 -lnsl -lsocket	

