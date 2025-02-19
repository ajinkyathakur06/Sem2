const EventEmitter = require('events');
const emitter = new EventEmitter();

// Step 1: Register listeners (using `on` and `once` methods)
emitter.on('start', () => {
  console.log('The process has started.');
});

emitter.once('step', (stepNumber) => {
  console.log(`Step ${stepNumber}: Executed once.`);
});

emitter.on('progress', (percentage) => {
  console.log(`Progress: ${percentage}% completed.`);
});

function endHandler(status) {
  console.log(`The process has ended with status: ${status}`);
}

emitter.on('end', endHandler);

// Step 2: Emit events in sequence (using `emit` method)
console.log('--- Event Lifecycle Begins ---');
emitter.emit('start');              // Triggers the 'start' event
emitter.emit('step', 1);            // Triggers the 'step' event (one-time listener)
emitter.emit('progress', 50);       // Triggers the 'progress' event
emitter.emit('progress', 75);       // Triggers the 'progress' event again
emitter.emit('step', 2);            // No output because 'step' listener was one-time
emitter.emit('end', 'Success');     // Triggers the 'end' event
console.log('--- Event Lifecycle Ends ---');

// Step 3: Remove specific listeners
emitter.removeListener('end', endHandler);

// Try triggering the 'end' event again
emitter.emit('end', 'Failure');     // No output as the listener was removed

// Step 4: Remove all listeners for an event
emitter.on('cleanup', () => console.log('Cleaning up...'));
emitter.on('cleanup', () => console.log('Finalizing cleanup...'));

emitter.emit('cleanup'); 
// Output:
// Cleaning up...
// Finalizing cleanup...

emitter.removeAllListeners('cleanup');

// Try triggering the 'cleanup' event again
emitter.emit('cleanup');            // No output as all listeners were removed

// Step 5: Remove all listeners for all events
emitter.on('final', () => console.log('Final task completed.'));
emitter.emit('final');              // Output: Final task completed.

emitter.removeAllListeners();       // Removes all listeners

emitter.emit('final');              // No output as all listeners were removed
