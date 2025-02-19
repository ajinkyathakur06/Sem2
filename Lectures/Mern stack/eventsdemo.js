const EventEmitter = require('events');
const emitter = new EventEmitter();

// 1. on(event, listener): Adds a listener for the specified event
emitter.on('greet', (name) => {
    console.log(`Hello, ${name}!`);
});

// Triggering the 'greet' event multiple times
emitter.emit('greet', 'Ajinkya'); // Output: Hello, Ajinkya!
emitter.emit('greet', 'Alice');   // Output: Hello, Alice!

// 2. once(event, listener): Adds a one-time listener for the specified event
emitter.once('connect', () => {
    console.log('Connected to the server.');
});

// Triggering the 'connect' event multiple times
emitter.emit('connect'); // Output: Connected to the server.
emitter.emit('connect'); // No output since it's a one-time listener.

// 3. emit(event, args...): Triggers the event, passing arguments to listeners
emitter.on('status', (code, message) => {
    console.log(`Status Code: ${code}, Message: ${message}`);
});

emitter.emit('status', 200, 'OK'); // Output: Status Code: 200, Message: OK

// 4. removeListener(event, listener): Removes a specific listener for an event
function sayHello(name) {
    console.log(`Hello, ${name}!`);
}

emitter.on('greetOnce', sayHello);

emitter.emit('greetOnce', 'Ajinkya'); // Output: Hello, Ajinkya!

// Removing the specific listener
emitter.removeListener('greetOnce', sayHello);

emitter.emit('greetOnce', 'Ajinkya'); // No output as the listener was removed

// 5. removeAllListeners([event]): Removes all listeners for a specified event or all events
emitter.on('greetAll', (name) => {
    console.log(`Hi, ${name}!`);
});

emitter.on('greetAll', () => {
    console.log('Another greet listener!');
});

emitter.emit('greetAll', 'Ajinkya');
// Output:
// Hi, Ajinkya!
// Another greet listener!

// Removing all listeners for 'greetAll'
emitter.removeAllListeners('greetAll');

emitter.emit('greetAll', 'Ajinkya'); // No output as all listeners were removed

// Removing all listeners for all events
emitter.on('event1', () => console.log('Event1 triggered'));
emitter.on('event2', () => console.log('Event2 triggered'));

emitter.emit('event1'); // Output: Event1 triggered
emitter.emit('event2'); // Output: Event2 triggered

// Remove all listeners for all events
emitter.removeAllListeners();

emitter.emit('event1'); // No output
emitter.emit('event2'); // No output
