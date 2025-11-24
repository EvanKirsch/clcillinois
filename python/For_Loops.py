from Scripts import my_list_formatter # importing a custom formator to print the list and highlight the currently worked index

# declare and instaniate a list. This is the list we are going to iterate over.
my_list = [5, 10, 15, 20]

# i - sort for "index", will start at 0 and get incremented by 1
# range(len(my_list)) - this is going to create a range of numbers from 0 to the length of "my list"
for i in range(len(my_list)):                # for each number in the range of 0 to the length of my list - execute the loop with i equal to the index 
    print(my_list_formatter(my_list, i))         # print my list, highlight element at i
    my_list[i] = my_list[i] + 1                  # set the value at index i to the current value at index i plus 1
    print(my_list_formatter(my_list, i))         # print my list, highlight element at i

# after the loop, every element in the list will be incremented by 1 