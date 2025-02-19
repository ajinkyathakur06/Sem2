array1=["pune","mumbai","delhi","nagpur"]

/*for(i=0;i<array1.length;i++)
{
    console.log(array1[i])
}
array1.forEach(city => {
    console.log(city)
});*/
num=[100,205,9,40,25]
new_array=array1.concat(num)
new_array.forEach(no => {
    console.log(no)
});
a=array1.includes("mumbaii")
if(a)
    console.log("it is present in array")
else
    console.log("not presesnt")

