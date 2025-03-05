import chalk from 'chalk';
import chalkAnimation from 'chalk-animation';
console.log(chalk.red('Hello'));
const rainbow = chalkAnimation.rainbow('Welcome to Node JS');

setTimeout(() => {
    rainbow.stop();
}, 3000);