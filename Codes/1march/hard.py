# Function to generate a string where each letter is followed by its position in the alphabet
def letter_positions(word):
    result = ""
    for char in word.upper():
        if char.isalpha():
            result += f"{char}{ord(char) - ord('A') + 1}"
    return result


word = "IMCC"
letter_positions_result = letter_positions(word)
print(f"Input Word: {word}")
print(f"Output: {letter_positions_result}")