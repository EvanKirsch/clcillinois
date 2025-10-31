#include <stdio.h>
#include <stdlib.h>
#include "stats.h"

stats * create_stats(){
  stats *new_stats = (stats *)malloc(sizeof(stats));
  if(new_stats == NULL){
    return NULL;
  }

  new_stats->r_type = 0;
  new_stats->i_type = 0;
  new_stats->j_type = 0;

  new_stats->fwd_taken = 0;
  new_stats->bkw_taken = 0;
  new_stats->not_taken = 0;

  new_stats->loads = 0;
  new_stats->stores = 0;
  new_stats->arith = 0;

  int i;
  for(i = 0; i < 32; i++){
    new_stats->regs_reads[i] = 0;
    new_stats->regs_writes[i] = 0;
  }
  return new_stats;
}

int write_stats(stats * the_stats, FILE *out_file){
  float insts = the_stats->r_type + the_stats->i_type + the_stats->j_type;

  if(the_stats == NULL){
    return -1;
  }
  fprintf(out_file,"%s: %i\n", "insts", (int)insts);
  fprintf(out_file,"%s: %i\n", "r-type", the_stats->r_type);
  fprintf(out_file,"%s: %i\n", "i-type", the_stats->i_type);
  fprintf(out_file,"%s: %i\n", "j-type", the_stats->j_type);

  fprintf(out_file,"%s: %f\n", "fwd-taken", (the_stats->fwd_taken/insts)*100);
  fprintf(out_file,"%s: %f\n", "bkw-taken", (the_stats->bkw_taken/insts)*100);
  fprintf(out_file,"%s: %f\n", "not-taken",(the_stats->not_taken/insts)*100);

  fprintf(out_file,"%s: %f\n", "loads",(the_stats->loads/insts)*100);
  fprintf(out_file,"%s: %f\n", "stores", (the_stats->stores/insts)*100);
  fprintf(out_file,"%s: %f\n", "arith", (the_stats->arith/insts)*100);

  int i;
  for(i = 0; i < 32; i++){
    fprintf(out_file,"reg-%i: %d %d\n", i, the_stats->regs_reads[i], the_stats->regs_writes[i]);
  }
  return 0;
}
