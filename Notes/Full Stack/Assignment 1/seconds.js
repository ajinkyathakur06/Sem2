const fs = require('fs');
const secondsInYear = 365 * 24 * 60 * 60;
const secondsInCentury = 100 * secondsInYear;
fs.writeFileSync('seconds.txt', `Year: ${secondsInYear}, Century: ${secondsInCentury}`);
