def my_list_formatter(my_list, index_to_highlight=-1):
    border = " "
    center = "| "
    for i in range(len(my_list)):
        char_length = len(str(my_list[i]))
        border_char = "-"
        if (i == index_to_highlight):
            border_char = "*"
        border = border + border_char * (char_length + 2)  + " "
        center = center + str(my_list[i]) + " | "
    return border + '\n' + center + '\n' + border
