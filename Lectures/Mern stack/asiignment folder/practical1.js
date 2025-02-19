// 1. Print prime numbers in an interval of 10 to 50
function printPrimesInRange(start, end) {
    for (let num = start; num <= end; num++) {
        if (isPrime(num)) {
            console.log(num);
        }
    }
}
function isPrime(num) {
    if (num < 2) return false;
    for (let i = 2; i <= Math.sqrt(num); i++) {
        if (num % i === 0) return false;
    }
    return true;
}

// 2. Check if a number is Armstrong number
function isArmstrong(num) {
    const digits = num.toString().split('');
    const sum = digits.reduce((acc, digit) => acc + Math.pow(Number(digit), digits.length), 0);
    return sum === num;
}

// 3. Check if a number is Perfect number
function isPerfectNumber(num) {
    let sum = 0;
    for (let i = 1; i <= num / 2; i++) {
        if (num % i === 0) sum += i;
    }
    return sum === num;
}

// 4. Count occurrence of character in a string
function countCharacterOccurrence(str, char) {
    return [...str].reduce((count, current) => current === char ? count + 1 : count, 0);
}

// 5. Create array, sort array, concatenate two arrays
function handleArrays(arr1, arr2) {
    const sortedArray1 = [...arr1].sort((a, b) => a - b);
    const sortedArray2 = [...arr2].sort((a, b) => a - b);
    const concatenatedArray = sortedArray1.concat(sortedArray2);
    return { sortedArray1, sortedArray2, concatenatedArray };
}

// 6. Create array, sum of all elements, remove specific element
function arrayOperations(arr, elementToRemove) {
    const sum = arr.reduce((acc, num) => acc + num, 0);
    const filteredArray = arr.filter(num => num !== elementToRemove);
    return { sum, filteredArray };
}

// 7. Implement stack operations
class Stack {
    constructor() {
        this.stack = [];
    }
    push(element) {
        this.stack.push(element);
    }
    pop() {
        if (this.isEmpty()) return "Stack is empty!";
        return this.stack.pop();
    }
    peek() {
        return this.isEmpty() ? "Stack is empty!" : this.stack[this.stack.length - 1];
    }
    isEmpty() {
        return this.stack.length === 0;
    }
    display() {
        return this.stack;
    }
}

// 8. Print Multiplication table
function printMultiplicationTable(num) {
    for (let i = 1; i <= 10; i++) {
        console.log(`${num} x ${i} = ${num * i}`);
    }
}

// 9. Convert decimal number to binary
function decimalToBinary(decimal) {
    return decimal.toString(2);
}

// Example usage:
console.log("Prime numbers between 10 and 50:");
printPrimesInRange(10, 50);

console.log("\nIs 153 an Armstrong number?");
console.log(isArmstrong(153));

console.log("\nIs 28 a Perfect number?");
console.log(isPerfectNumber(28));

console.log("\nOccurrences of 'l' in 'hello world':");
console.log(countCharacterOccurrence("hello world", "l"));

console.log("\nArray operations:");
const arrays = handleArrays([5, 1, 3], [9, 2, 8]);
console.log(arrays);

console.log("\nSum and filtered array:");
const arrOps = arrayOperations([10, 20, 30, 40], 20);
console.log(arrOps);

console.log("\nStack operations:");
const stack = new Stack();
stack.push(10);
stack.push(20);
console.log("Stack after pushes:", stack.display());
console.log("Top element:", stack.peek());
console.log("Popped element:", stack.pop());
console.log("Stack after pop:", stack.display());

console.log("\nMultiplication table of 5:");
printMultiplicationTable(5);

console.log("\nBinary of 25:");
console.log(decimalToBinary(25));
