#include <stdio.h>
#include <stdlib.h>

struct stats{
  //instruction counters
  int r_type;               //r type instructions
  int i_type;               //i type instructions
  int j_type;               //j type instructions

  //branch counters
  int fwd_taken;            //branches that go forward
  int bkw_taken;            //branches that go backward
  int not_taken;            //branches not taken

  //read/write
  int loads;                //loads used
  int stores;               //stores used
  int arith;                //arithmatic opperations?

  int regs_reads[32];
  int regs_writes[32];
};
typedef struct stats stats;

stats * create_stats();

int write_stats(stats *, FILE *);
