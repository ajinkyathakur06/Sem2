var myfs=require('fs')
myfs.writeFile('newfile.txt',"Hello friends.",function(err){
    console.log('saved')
})
//read file in sync mode
var data1=myfs.readFileSync('sample.txt')
console.log("sync reading..."+data1.toString())
var i=1
while(i<=5){
    console.log("number:"+i)
    i++
}
//read file in async mode
myfs.readFile('newfile.txt',function(err,data){
    console.log("async reading..."+data.toString())
})

//end
console.log("reading is complete")
