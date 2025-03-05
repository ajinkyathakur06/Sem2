def sum_even_odd(arr):
    # Initialize sums
    sum_even = 0
    sum_odd = 0

    # Iterate through the array
    for num in arr:
        if num % 2 == 0:  # Check if the number is even
            sum_even += num
        else:  # Otherwise odd
            sum_odd += num

    return sum_even, sum_odd


array = [11, 20, 35, 40, 53, 60]

# Get the sums
even_sum, odd_sum = sum_even_odd(array)

# Display the results
print(f"Array: {array}")
print(f"Sum of Even Numbers: {even_sum}")
print(f"Sum of Odd Numbers: {odd_sum}")
