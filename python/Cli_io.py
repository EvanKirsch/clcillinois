#!/usr/bin/env python3

def computeWC ():
	temp = input('Enter temperature (F): ')
	temp = int(temp)
	wind = input('Enter wind velocity (MPH): ')
	wind = int(wind)
	wcill = 35.74 + 0.6215*(temp) - 35.75*pow(wind, 0.16) + 0.4275*temp*pow(wind, 0.16)
	wcill = str(wcill)
	print ("The windchill is: ", wcill)

computeWC()
