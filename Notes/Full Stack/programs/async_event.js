// Importing events
const EventEmitter = require('events');

// Initializing event emitter instances 
var eventEmitter = new EventEmitter();

// Async function listening to myEvent
eventEmitter.on('myEvent', (msg) => {
    setImmediate( () => {
        console.log("Message from async1: " + msg);
    });
});
// Async function listening to myEvent
eventEmitter.on('myEvent', (msg) => {
    setImmediate( () => {
        console.log("Message from async2: " + msg);
    });
});

// Declaring listener fun to myEvent
var fun1 = (msg) => {
    console.log("Message from fun1: " + msg);
};
// Declaring listener fun to myEvent
var fun2 = (msg) => {
    console.log("Message from fun2: " + msg);
};

// Listening to myEvent with fun
eventEmitter.on('myEvent', fun1);
eventEmitter.on('myEvent', fun2);

// Triggering myEvent
eventEmitter.emit('myEvent', "Event occurred");
