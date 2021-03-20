const fs = require('fs');

let rawdata = fs.readFileSync('response.json');
let bill = JSON.parse(rawdata);
//console.log(bill[1].boundingPoly.vertices[0])

let output = "";

//iterate over the list and append description to output txt in a formatted way
//use vertices to guide you

let offsetTolerance = 10;//max offset btw same lines
let lastYTop = bill[0].boundingPoly.vertices[0].y; 

for(var i = 1; i < bill.length; i++){
    //parse the boundingPoly coordinates
    coordinates = bill[i].boundingPoly.vertices;
    xy1 = coordinates[0]
    xy2 = coordinates[1]
    xy3 = coordinates[2]
    xy4 = coordinates[3]

    if(Math.abs(lastYTop - xy1.y) < offsetTolerance){//if on the same line append to the same line
        output += " " + bill[i].description;

    }else{ //not on the same line
        //break a line then, append and update lastYTop
        output += "\n" + bill[i].description
        lastYTop = xy1.y
    }
    
}

console.log(bill[0].description)

//write to file
fs.writeFile('bill.txt', output, function (err) {
    if (err) return console.log(err);
    console.log('JSON parsed and outputted written at bill.txt');
  });