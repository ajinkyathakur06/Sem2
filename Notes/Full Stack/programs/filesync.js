/*var fs = require("fs");
var data = fs.readFileSync('sample.txt');

console.log(data.toString());

let i = 1;
while (i <=5) {
  console.log("The number is " + i);
  i++;
}*/
var fs = require("fs");
fs.readFile('sample.txt', function (err, data) {
   if (err) return console.error(err);
   console.log(data.toString());
});

let i = 1;
while (i <=5) {
  console.log("The number is " + i);
  i++;
}