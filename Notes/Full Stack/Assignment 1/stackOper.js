class Stack {
    constructor() {
        this.stack = [];
    }
    push(item) { this.stack.push(item); }
    pop() { return this.stack.pop(); }
    peek() { return this.stack[this.stack.length - 1]; }
    isEmpty() { return this.stack.length === 0; }
}
const stack = new Stack();
stack.push(10);
stack.push(20);
console.log(stack.pop());
