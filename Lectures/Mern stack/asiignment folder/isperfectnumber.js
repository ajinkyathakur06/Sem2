// 3. Check if a number is Perfect number
function isPerfectNumber(num) {
    let sum = 0;
    for (let i = 1; i <= num / 2; i++) {
        if (num % i === 0) sum += i;
    }
    return sum === num;
}

console.log("\nIs 25 a Perfect number?");
console.log(isPerfectNumber(25));