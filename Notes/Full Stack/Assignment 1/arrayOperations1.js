function arrayOperations() {
    const arr = [1, 2, 3, 4, 5];
    const sum = arr.reduce((a, b) => a + b, 0);
    const removeElement = (arr, element) => arr.filter(item => item !== element);
    console.log("Sum:", sum);
    console.log("After Removal:", removeElement(arr, 3));
}
arrayOperations();
