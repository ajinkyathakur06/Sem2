var fs = require('fs');

// Writing to a file
fs.writeFile('newfile.txt', "Hello friends.... Juice Pilado..... Mosambi Kaa.....", (err) => {
    if (err) {
        console.error("Error writing to file:", err);
    } else {
        console.log("File has been saved successfully.");
    }
});

// Reading the 'newfile.txt' file
fs.readFile('newfile.txt', (err, data) => {
    if (err) {
        console.error("Error reading file:", err);
    } else {
        console.log("Content of 'newfile.txt':", data.toString());
    }
});

// Reading the 'sample.txt' file
try {
    var data1 = fs.readFileSync('sample.txt');
    console.log("Content of 'sample.txt':", data1.toString());
} catch (err) {
    console.error("Error reading file:", err);
}

console.log("Initiated file reading.");
