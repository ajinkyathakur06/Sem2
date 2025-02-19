// 2. Check if a number is Armstrong number
function isArmstrong(num) {
    const digits = num.toString().split('');
    const sum = digits.reduce((acc, digit) => acc + Math.pow(Number(digit), digits.length), 0);
    return sum === num;
}

console.log("\nIs 153 an Armstrong number?");
console.log(isArmstrong(153));