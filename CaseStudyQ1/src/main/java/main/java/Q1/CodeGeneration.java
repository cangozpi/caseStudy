package main.java.Q1;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CodeGeneration {

    private int codeLength;//length of the code that is generated
    private char[] characterSet;//valid characters that can be used in code generation
    private HashMap<String, Boolean> sampleMap;//holds generated unique codes

    //default constructor
    public CodeGeneration(int codeLength, String validString){
        this.codeLength = codeLength;
        this.characterSet = validString.toCharArray();
        this.sampleMap = new HashMap<>();
    }

    //generates many unique codes
    public void generateCodes(int sampleSize){
        //create sampleSize many codes and write it on GeneratedCodes.txt file in the resources folder
        while(sampleMap.size() < sampleSize){//fill with unique codes
            String newCode = generateCode();
            if(sampleMap.containsKey(newCode) == false){//if code generated is unique then add to sampleMap
                sampleMap.put(newCode, true);
            }else{//if code generated is not unique then do not add it to the sampleMap
                continue;
            }
        }

        //write sampled codes to a file
        writeGeneratedCodesToFile();
    }

    //writes generated codes in the sampleMap to a File named GeneratedCodes.txt
    public void  writeGeneratedCodesToFile(){
        try {
            //write codes to the file
            FileWriter myWriter = new FileWriter("GeneratedCodes.txt");
            sampleMap.keySet().stream().forEach((x) -> {
                //write to the file
                try {
                    myWriter.write(x + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            myWriter.close();
            System.out.println("Successfully wrote generated odes to GeneratedCodes.txt.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //generates a single code that conforms to the standards
    public String generateCode(){
        Random rand = new Random();

        String code = "";
        for(int i = 0; i < codeLength ; i++){
            //choose random char from available set
            int randNum = rand.nextInt(characterSet.length); // any index value corresponding to characterSet's every index
            char randChar = characterSet[randNum]; //randomly chosen char
            //check for adjacent repetitions
            if(code.length() == 0){//append anyway to the first index
                code += randChar;//append to code
            }else if(code.charAt(i - 1) == randChar){//if adjacent chars then don't append to code and don't increment i
                i--;
                continue;
            }else{//no repetitions
                code += randChar;//append to code
            }

        }

        return code;
    }

    //checks the validity of the code(i.e whether it conforms to the standards)
    public boolean checkCode(String code){
        char[] codeCharArray = code.toCharArray();

        //check the length of the code
        if(codeCharArray.length != codeLength){
            return false;//different length of code, invalid code
        }

        //iterate over each element
        for(int i = 0; i < codeCharArray.length; i++){
            //check if the elements are from the characterSet
            if(new String(characterSet).indexOf(codeCharArray[i]) == -1){//if doesn't exist
                return false;//invalid code
            }
            //check if two adjacent values are the same(i.e check for repetition)
            if(i > 0){//can't check index 0 for repetition
                if(codeCharArray[i-1] == codeCharArray[i]){
                    return false; //repetition occurred invalid code
                }
            }
        }



        return true;
    }


}
