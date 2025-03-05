# Function to find the second largest element in an array
def second_largest(nums):
    if len(nums) < 2:
        return -1

    largest = second = float('-inf')
    for num in nums:
        if num > largest:
            second = largest
            largest = num
        elif num > second and num != largest:
            second = num

    return second if second != float('-inf') else -1


nums = [10, 5, 8, 20, 15]
second_largest_element = second_largest(nums)
print(f"Second Largest Element: {second_largest_element}")