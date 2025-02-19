const fs = require('fs');
function countLinesWordsChars(file){
    let lines = 0;
    let words = 0;
    let chars = 0;
    // Create a readable stream from the file
    const stream = fs.createReadStream(file, { encoding: 'utf8' });
    // Listen for 'data' event, which is emitted whenever data is read from the stream
    stream.on('data', (data) => {
    // Count lines by counting the number of newline characters
    lines += data.split('\n').length;
    // Count words by splitting the data by whitespace characters and filtering out empty strings
    words += data.split(/\s+/).length;
    // Count characters by adding the length of the data
    chars += data.length;
    });
    // Listen for 'end' event, which is emitted when the end of the stream is reached
    stream.on('end', () => {
    console.log(`Number of lines: ${lines}`);
    console.log(`Number of words: ${words}`);
    console.log(`Number of characters: ${chars}`);
    });
    // Listen for 'error' event, which is emitted when an error occurs
    stream.on('error', (err) => {
    console.error(`Error reading file: ${err}`);
    });
};
// Call the function with the file name as argument
countLinesWordsChars('sample.txt');
