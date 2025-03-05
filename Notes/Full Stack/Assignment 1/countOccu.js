function countCharacter(str, char) {
    return str.split(char).length - 1;
}
console.log(countCharacter("hello world", "o"));
