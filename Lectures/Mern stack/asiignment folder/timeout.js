function displayMessageWithDelay(message, times, delay) {
    let count = 0;

    const interval = setInterval(() => {
        console.log(message);
        count++;

        if (count >= times) {
            clearInterval(interval); // Stop after the specified number of times
        }
    }, delay);
}

// Example usage:
displayMessageWithDelay("Welcome to Node.js", 10, 2000);
