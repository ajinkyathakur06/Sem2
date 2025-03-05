const fs = require('fs');
const data = [{ name: "John", email: "john@example.com", job: "Engineer" }];
fs.writeFileSync('data.json', JSON.stringify(data, null, 2));
console.log(JSON.parse(fs.readFileSync('data.json')));
