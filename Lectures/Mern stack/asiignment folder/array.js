// 5. Create array, sort array, concatenate two arrays
function handleArrays(arr1, arr2) {
    const sortedArray1 = [...arr1].sort((a, b) => a - b);
    const sortedArray2 = [...arr2].sort((a, b) => a - b);
    const concatenatedArray = sortedArray1.concat(sortedArray2);
    
    console.log("Array 1 (Original):", arr1);
    console.log("Array 1 (Sorted):", sortedArray1);
    console.log("Array 2 (Original):", arr2);
    console.log("Array 2 (Sorted):", sortedArray2);
    console.log("Concatenated Array:", concatenatedArray);

    return { sortedArray1, sortedArray2, concatenatedArray };
}

console.log("\nArray operations:");
console.log("\nHandling Arrays:");
handleArrays([5, 1, 3], [9, 2, 8]);