//read from csv file
const mycsv=require('csv-parser')
const fs=require('fs')
const results=[]
fs.createReadStream('cricket.csv') 
.pipe(mycsv()) //begins to parse data into our stream
.on('data',(row)=>{
    results.push(row)
    console.log(`${row['name']} played ${row['matches']}`)
})
.on('end',()=>{
    console.log("First row", results[0])
    for(i=1;i<results.length;i++)
        console.log('csv row:',results[i])
})

//Write to csv file
const csvwriter=require('fast-csv')
const data=[{name:'Akshay', age:28},
    {name:'Anvita', age:27}, 
]
csvwriter.writeToPath('newData.csv',data,{headers:true})

