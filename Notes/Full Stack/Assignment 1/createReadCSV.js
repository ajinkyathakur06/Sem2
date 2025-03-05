const fs = require('fs');
const data = "Name,Age,Weight\nAjinkya,25,80\nKeshav,22,75";
fs.writeFileSync('data.csv', data);
console.log(fs.readFileSync('data.csv', 'utf-8'));
