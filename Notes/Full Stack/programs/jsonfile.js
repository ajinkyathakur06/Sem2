//write to file
const fs = require('fs');
const data ={ key: 'value1', number: 41}
fs.writeFile('newData.json', JSON.stringify(data), 'utf8', (err) => {
  if (err) {
    console.error(err);
    return;
  }
  console.log('JSON file written successfully');
});

//Read from file
const fs1 = require('fs');
fs1.readFile('newData.json', 'utf8', (err, data) => {
  if (err) {
    console.error(err);
    return;
  }
  const jsonData = JSON.parse(data);
  console.log('JSON content:', jsonData);
});