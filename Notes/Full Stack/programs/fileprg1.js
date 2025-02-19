const fs = require('fs');
function countChars(filename)
{
    const vowels = 'aeiouAEIOU';
    let vowelCount = 0;
    let consonantCount = 0;
    let digitCount = 0;
    let specialCharCount = 0;
    const stream = fs.createReadStream(filename, { encoding: 'utf8' });
    stream.on('data', (data) => {
        for (const char of data) 
        {
            if (char.match(/[a-zA-Z]/)) 
            {
                if (vowels.includes(char))
                {
                vowelCount++;
                } 
                else 
                {
                    consonantCount++;
                }
            } 
            else if (char.match(/\d/))
            {
                digitCount++;
            } 
            else if (char.match(/\S/))
            {
                specialCharCount++;
            }
        }
    });
stream.on('end', () => {
console.log(`Vowels: ${vowelCount}`);
console.log(`Consonants: ${consonantCount}`);
console.log(`Digits: ${digitCount}`);
console.log(`Special Characters: ${specialCharCount}`);
});
stream.on('error', (err) => {
    console.error(`Error reading file: ${err}`);
    });
    }
    //reading file from user
    const file = process.argv[2];
    if (file) {
    countChars(file);
    } else {
    console.error('Please provide a file name as an argument.');
    }

