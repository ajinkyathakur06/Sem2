fs=require('fs')
lines=chars=words=0
const stream=fs.createReadStream('sample.txt','utf8')
stream.on('data',(mydata)=>{
    lines+=mydata.split('\n').length
    chars+=mydata.length
    words+=mydata.split(/\s+/).length
})
stream.on('end',()=>{
    console.log("No of characters in file: "+chars)
    console.log("No of words in file: "+words)
    console.log("No of lines in file: "+lines)
})
