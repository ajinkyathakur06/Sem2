//const async = require("async");

function square(x) {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(Math.pow(x, 2));
        }, 500);
    });
}

async function output(x) {
    const ans = await square(x);
    console.log(ans);
}

output(10);
for(i=1;i<10;i++)
{
    console.log(i)
}
