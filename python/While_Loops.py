#!/usr/bin/env python3

# Compound Intrest
def compound(startingAmount, targetAmount, interestRate) :
	totalAmount = startingAmount
	years = 0
	while totalAmount < targetAmount:
		annualInterest = totalAmount * interestRate
		totalAmount += annualInterest
		years += 1
	return(years)

# Perfect Numbers (pure)
def perfect(integer) :
	divider = 1
	sum = 0
	while divider < integer:
		if integer % divider == 0 :
			sum += divider
			divider += 1
		else :
			divider += 1
	if sum == integer and integer != 0:
		perfect = True
	else :
		perfect = False
	return(perfect)

# Perfect Numbers
def perfectLits(upperlimit) :
	count = 1
	while upperlimit >= count:
		integer = count
		tf = perfect(integer)
		if tf == True :
			print (integer)
			count += 1
		else :
			count += 1
