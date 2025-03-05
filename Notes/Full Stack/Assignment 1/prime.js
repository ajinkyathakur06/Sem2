function printPrimes(start, end) {
    for (let num = start; num <= end; num++) {
        let isPrime = true;
        if (num < 2) continue;
        for (let i = 2; i <= Math.sqrt(num); i++) {
            if (num % i === 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) console.log(num);
    }
}
printPrimes(10, 50);
