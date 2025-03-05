const fs = require('fs');
const content = fs.readFileSync('file.txt', 'utf-8');
console.log("Lines:", content.split('\n').length);
console.log("Words:", content.split(/\s+/).length);
console.log("Characters:", content.length);
