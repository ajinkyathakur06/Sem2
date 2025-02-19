const myfs=require('fs') //importing file handling module
myfs.readFile('myfile.txt','utf8', function(err, data){
    console.log(data)
})
//writing to file
myfs.writeFile('newfile.txt',"Its difficult but interesting",function(err){
    console.log("data saved")
})
myfs.appendFile('newfile.txt',"Need to understand few more constructs",function(err){
    console.log("data saved")
})
//renaming file
myfs.rename('newfile.txt','renamed_file.txt',function(err){
    console.log("file renamed")
})
//delete file
myfs.unlink('myfile.txt',function(err){
    console.log('deleted file')
})
