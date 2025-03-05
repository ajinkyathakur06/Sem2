
function displayMessage() {
    for (let i = 1; i <= 10; i++) {
        setTimeout(() => console.log("Welcome to Node.js"), i * 2000);
    }
}
displayMessage();
