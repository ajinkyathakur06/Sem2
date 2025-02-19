function isPrime(num) {
    if (num <= 1) return false; // Numbers less than or equal to 1 are not prime
    if (num === 2) return true; // 2 is a prime number
    if (num % 2 === 0) return false; // Eliminate even numbers greater than 2

    const sqrtNum = Math.sqrt(num);
    for (let i = 3; i <= sqrtNum; i += 2) {
        if (num % i === 0) return false; // If divisible, it's not prime
    }
    return true; // Otherwise, it's prime
}

// Example usage:
const number = 17; // Replace with any number to test
console.log(`${number} is prime: ${isPrime(number)}`);
