run the code by typing node q2.js on the folder location.
The desired output is written to orderedBill.txt file.

What it does is that, orderJson() fills an array named orderedBill() which is an array of JSON objects in response.json except the first element since it is an overall summary element.
After calling orderedBill(), orderedBill array has in increasing order of the "y" leveles of the objects.
Then, writeAfterOrdered() is called which iterates over the elements of the orderedBill and by checking the y coordinate difference between two adjacent elements and
comparing it wrt offsetTolerance, decides whether to print on the same line or enter a newline and move on from there.