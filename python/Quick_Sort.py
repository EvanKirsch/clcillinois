def partition(list, left, right):
    pivot_value = list[right]
    low_index = left - 1
    print(f"Pivoting around value: {pivot_value}")
    for compare_index in range(left, right):
        if(list[compare_index] < pivot_value):
            print(f"{list[compare_index]} is less than {pivot_value}, so it will be on the left of the pivot")
            low_index = low_index + 1
            low_value = list[low_index]
            high_value = list[compare_index]
            # print(f"list[{compare_index}] = {low_value}")
            # print(f"list[{low_index}] = {high_value}")
            list[compare_index] = low_value
            list[low_index] = high_value

    low_index = low_index + 1
    pivot_value = list[right]
    lower_value = list[low_index]
    # print(f"Placing Pivot Value: {pivot_value}")
    # print(f"list[{low_index}] = {pivot_value}")
    # print(f"list[{right}] = {lower_value}")
    print(list)
    list[low_index] = pivot_value
    list[right] = lower_value
    return low_index 

def quick_sort(list, left, right):
    if(left < right):
        split_location = partition(list, left, right)
        quick_sort(list, left, split_location-1) 
        quick_sort(list, split_location+1, right)
    return list
    
def impl_quick_sort(list):
    return quick_sort(list, 0, len(list) - 1)
    
list = [9, 4, 11, 5]
print(impl_quick_sort(list))