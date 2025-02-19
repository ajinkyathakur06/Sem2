// 8. Print Multiplication table
function printMultiplicationTable(num) {
    for (let i = 1; i <= 10; i++) {
        console.log(`${num} x ${i} = ${num * i}`);
    }
}


console.log("\nMultiplication table of 5:");
printMultiplicationTable(5);
