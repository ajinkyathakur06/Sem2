const fs = require('fs');
const content = fs.readFileSync('file.txt', 'utf-8');
console.log("Characters:", content.length);
console.log("Digits:", (content.match(/\d/g) || []).length);
console.log("Vowels:", (content.match(/[aeiou]/gi) || []).length);
console.log("Consonants:", (content.match(/[^aeiou\s\d\W]/gi) || []).length);
console.log("Special Characters:", (content.match(/\W/g) || []).length);
