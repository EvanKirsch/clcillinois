import math as math 
TOLERANCE = 0.0001

def main():
    my_input = " "
    while(len(my_input) > 0):
        my_input = input("Number? ")
        if(len(my_input) > 0):
            my_number = int(my_input)
            print(f'My Newton Approximation: {calculate_newton_square(my_number)}')
            print(f'Python Math.sqrt(): {math.sqrt(my_number)}')

def calculate_newton_square(my_number):
    
    my_estimate = my_number

    while(True):
        my_root = .5 * (my_estimate + (my_number/my_estimate))
        print(f'Our Previous Estimate is: {my_estimate}, Our Revised Estimate is: {my_root}')
        
        if(abs(my_root - my_estimate) < TOLERANCE):
          print(f"we have identified our estimate of {my_root} as being within our tolerance")
          return my_root
        
        my_estimate = my_root

main()