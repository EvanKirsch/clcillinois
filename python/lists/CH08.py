from Scripts import my_list_formatter

my_list = [5, 10, 15, 20]

for i in range(len(my_list)):
    print(my_list_formatter(my_list, i))
    my_list[i] = my_list[i] + 1
    print(my_list_formatter(my_list, i))
