//file module 'fs'

var myfs = require('fs')
myfs.writeFile('newfile.txt', "hello friends.... juice pilado....mosambi kaa.....", function (err) {
    console.log('Saved')
})

myfs.appendFile('newfile.txt', "Its intersting", function (err) {
    console.log("Appended")
})

myfs.rename('newfile.txt', 'renamedfile.txt', function (err) {
    console.log("Renamed file")
})

