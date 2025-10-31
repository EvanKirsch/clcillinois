#include <stdio.h>
#include <stdlib.h>
#include "stats.h"
#include "main.h"


int main(){
  stats * prog_stats = create_stats();  // stats obj to neatly keep track of stats
  unsigned int last_pc = 0;             // val of last pc
  unsigned int pc = 0;                  // hex val for pc
  unsigned int instruction = 0;         // hex val for instruction

  FILE *in_file = NULL;                 //input file  (trace.txt)
  FILE *out_file = NULL;                //output file (statistics.txt)

  in_file = fopen("trace.txt", "r");
  out_file = fopen("statistics.txt", "w");

  while(fscanf(in_file, "%x %x\n", &pc, &instruction) == 2){
    if(last_pc != 0){
        if (last_pc < pc-4){
            prog_stats->fwd_taken++;
        }else if(last_pc > pc-4){
            prog_stats->bkw_taken++;
        }else{
          prog_stats->not_taken++;
        }
    }
    last_pc = collect_stats(pc, instruction, prog_stats);
  }
  write_stats(prog_stats, out_file);
  free(prog_stats);
  return 0;
}

unsigned int collect_stats(unsigned int pc, unsigned int instruction, stats * prog_stats){
  unsigned int opcode = range_value(instruction, 31, 26);
  unsigned int rs = range_value(instruction, 25, 21);
  unsigned int rt = range_value(instruction, 20, 16);
  unsigned int rd = range_value(instruction, 15, 11);
  unsigned int func = range_value(instruction, 5, 0);

  if(opcode == 0){                                          //R type cases
    prog_stats->r_type++;
    if(func == 0x08){                                             //jumps
      prog_stats->regs_reads[rs]++;
      return pc;
    }else if(func == 0x00 || func == 0x02 || func == 0x03){       //shifts
      prog_stats->regs_reads[rt]++;
      prog_stats->regs_writes[rd]++;
      prog_stats->arith++;
    }else{                                                        //arithmatic
      prog_stats->regs_reads[rs]++;
      prog_stats->regs_reads[rt]++;
      prog_stats->regs_writes[rd]++;
      prog_stats->arith++;
    }

  }else if(opcode >= 0x3){                                  // I type cases
    prog_stats->i_type++;

    if(opcode == 0x28 || opcode == 0x29 || opcode == 0x2b){       //stores
      prog_stats->regs_reads[rt]++;
      prog_stats->regs_reads[rs]++;
      prog_stats->stores++;
    }else if(opcode == 0x4 || opcode == 0x5){                     //jumps
      prog_stats->regs_reads[rs]++;
      prog_stats->regs_reads[rt]++;
      return pc;
    }else if(opcode == 0x23  || opcode == 0xf){                   //loads
      prog_stats->regs_reads[rs]++;
      prog_stats->regs_writes[rt]++;
      prog_stats->loads++;
    }else{                                                        //arithmatic
      prog_stats->regs_reads[rs]++;
      prog_stats->regs_writes[rt]++;
      prog_stats->arith++;
    }

  }else{                                                    //J type cases
    prog_stats->j_type++;
    if(opcode == 0x3){                                            //jal
      prog_stats->regs_writes[31]++;
    }
    return pc;
  }
  return 0;
}

int range_value(unsigned int num, int upper, int lower){
  num = num<<(31-upper);
  num = num>>(lower+(31-upper));
  return num;
}
