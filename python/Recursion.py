#!/usr/bin/env python3

# Character Count
def count(char,text):
        n = 0
        while text[n] == char:
            n += 1
            if n == len(text)-1:
                return(len(text))
        if text[0] != char:
            text = text[1:]
        else:
            text = text[:n] + text[n+1:]
        count(char, text)


# List Equality
def isEqual(list1, list2):
    if list1[0] == list2[0] and len(list1) == len(list2):
        if len(list1) == 1:
            return(True)
        list1 = list1[1:]
        list2 = list2[1:]
        isEqual(list1, list2)
    else:
        return(False)


# Radix Conversion
def convertBase(n,base):
    abc='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    step = 1
    totremain = ''
    while n // base**step != 0:
        remain = abc[n - ((n//base**step)*base)]
        totremain += convertBase(n//base**step, base) + remain
        return(totremain)
    remain = abc[n - ((n//base**step)*base)]
    return(remain)
