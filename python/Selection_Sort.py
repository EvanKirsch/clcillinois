
def selection_sort(my_list):
    i = 0
    while i < len(my_list):
        j = i
        current_selected_index = i

        while j < len(my_list):
            if(my_list[j] < my_list[current_selected_index]):
                current_selected_index = j

            j += 1

        my_selected_value = my_list[current_selected_index]
        my_current_value = my_list[i]
        my_list[i] = my_selected_value
        my_list[current_selected_index] = my_current_value

        i += 1

    return my_list

print(selection_sort([1, 8 , 9, 2, 3, 7]))