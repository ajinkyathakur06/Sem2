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

console.log("\nStack operations:");
const stack = new Stack();
stack.push(10);
stack.push(20);
console.log("Stack after pushes:", stack.display());
console.log("Top element:", stack.peek());
console.log("Popped element:", stack.pop());
console.log("Stack after pop:", stack.display());