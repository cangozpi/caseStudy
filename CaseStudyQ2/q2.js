const fs = require('fs');

let rawdata = fs.readFileSync('response.json');
//console.log(orderedBill[1].boundingPoly.vertices[0])
let unorderedBill= JSON.parse(rawdata);
var orderedBill = [];

//orders the unorderedBill and writes it on orderedBill
let orderJson = () => {

    unorderedBill.splice(0,1); //remove the first element since its irrelevant

    while(unorderedBill.length > 1){
        
        
        let currentminY = 10000000000; //represnet float(inf)
        let currentMin;
        let currentMinIndex = -1; // index of the min element
        
        for(var i = 1; i < unorderedBill.length; i++){
            coordinates = unorderedBill[i].boundingPoly.vertices;
            xy1 = coordinates[0]
            xy2 = coordinates[1]
            xy3 = coordinates[2]
            xy4 = coordinates[3]

            if(currentminY > xy1.y){//if its above the currentMin
                currentminY = xy1.y;
                currentMinIndex = i;
                currentMin = unorderedBill[i]; // set the json object to currentMin
            }

        }

        //add the currentMin obj to orderedBill and remove it from the unorderedBill array
        orderedBill.push(currentMin);
        unorderedBill.splice(currentMinIndex,1);
    
    }
    

}


//requires ordered List with respect to xy1.y coordinate in increasing order
let writeAfterOrdered = () => {

    let output = "";
    //iterate over the list and append description to output txt in a formatted way
    //use vertices to guide you

    let offsetTolerance = 10;//max offset btw same lines
    let lastYTop = orderedBill[0].boundingPoly.vertices[0].y; 


    for(var i = 0; i < orderedBill.length; i++){
        //parse the boundingPoly coordinates
        coordinates = orderedBill[i].boundingPoly.vertices;
        xy1 = coordinates[0]
        xy2 = coordinates[1]
        xy3 = coordinates[2]
        xy4 = coordinates[3]
    
        if(Math.abs(lastYTop - xy1.y) < offsetTolerance){//if on the same line append to the same line
            output += " " + orderedBill[i].description;
    
        }else{ //not on the same line
            //break a line then, append and update lastYTop
            output += "\n" + orderedBill[i].description
            lastYTop = xy1.y
        }
        
    }
    
    
    //write to file
    fs.writeFile('orderedBill.txt', output, function (err) {
        if (err) return console.log(err);
        console.log('JSON parsed and outputted written at orderedBill.txt');
      });
}




orderJson()
writeAfterOrdered()
