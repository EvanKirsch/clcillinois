#!/usr/bin/env python3

def merge_sort(my_list):

    if len(my_list) == 1:
        return

    split_index = round(len(my_list) / 2)

    left_list = my_list[0:split_index]
    right_list = my_list[split_index:len(my_list)]

    merge_sort(left_list)
    merge_sort(right_list)

    right_index = 0
    left_index = 0
    sort_index = 0
    # add lowest items for each list back into the original list
    while(right_index < len(right_list) and left_index < len(left_list)):
        if(left_list[left_index] < right_list[right_index]):
            my_list[sort_index] = left_list[left_index]
            left_index += 1
        else:
            my_list[sort_index] = right_list[right_index]
            right_index += 1
        sort_index += 1

    # add remaining left list
    while(left_index < len(left_list)):
        my_list[sort_index] = left_list[left_index]
        left_index += 1
        sort_index += 1

    # add remaining right list
    while(right_index < len(right_list)):
        my_list[sort_index] = right_list[right_index]
        right_index += 1
        sort_index += 1


my_list = [1, 2, 3, 9, 4]
merge_sort(my_list)
print(my_list)
