var fs=require('fs')
const grains=['rice','wheat','oats']
var filews=fs.createWriteStream("grains.txt",'utf8','w')
filews.on('close',function(){
    console.log("closed")
})
i=0
while(i<grains.length)
{
    filews.write(grains[i])
    console.log("Data written to file: "+grains[i])
    i++
}
filews.end()
var filers=fs.createReadStream("grains.txt",'utf8','r')
filers.on('data',function(txt){
    console.log("Grains: "+txt)
    console.log("Bytes: "+txt.length)
})
