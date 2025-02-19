// 6. Create array, sum of all elements, remove specific element
function arrayOperations(arr, elementToRemove) {
    const sum = arr.reduce((acc, num) => acc + num, 0);
    const filteredArray = arr.filter(num => num !== elementToRemove);
    
    console.log("Original Array:", arr);
    console.log("Sum of All Elements:", sum);
    console.log(`Array After Removing ${elementToRemove}:`, filteredArray);

    return { sum, filteredArray };
}


console.log("\nArray Operations:");
arrayOperations([10, 20, 30, 40], 20);