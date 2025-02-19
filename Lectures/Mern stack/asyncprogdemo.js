const async = require('async')

function square(x) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (typeof x !== 'number') {
                reject(new Error('Input must be a number'))
            } else {
                resolve(Math.pow(x, 2))
            }
        }, 2000)
    })
}

async function output(x) {
    try {
        const ans = await square(x)
        console.log(`Square of ${x} is ${ans}`)
    } catch (error) {
        console.error(error.message)
    }
}

output(10)
for (let i = 1; i <= 10; i++) {
    console.log(i)
}