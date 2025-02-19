var colors=require('colors')
console.log(colors.green("color module"))
console.log(colors.red.underline("color module"))
console.log(colors.bgCyan("color module"))
console.log(colors.rainbow("color module"))
console.log(colors.blue.bgWhite("color module"))
colors.setTheme({
    custom:['red','underline','bgWhite']
})
console.log('test theme'.custom)