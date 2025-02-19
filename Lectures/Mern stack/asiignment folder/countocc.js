// 4. Count occurrence of character in a string
function countCharacterOccurrence(str, char) {
    return [...str].reduce((count, current) => current === char ? count + 1 : count, 0);
}


console.log("\nOccurrences of 'l' in 'hello world':");
console.log(countCharacterOccurrence("hello world", "l"));