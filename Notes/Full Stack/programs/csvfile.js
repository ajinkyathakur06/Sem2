const csv = require('csv-parser');
const fs = require('fs');

fs.createReadStream('cricket.csv')
  .pipe(csv())
  .on('data', (row) => {
    console.log('CSV Row:', row);
  })
  .on('end', () => {
    console.log('CSV file processing complete');

  });

const csvWriter = require('fast-csv');
const data = [
  { name: 'Akshay', age: 28 },
  { name: 'Boby', age: 32 }
];
csvWriter.writeToPath('newData.csv', data, {headers: true})
  .on('finish', () => console.log('CSV file written successfully'));
