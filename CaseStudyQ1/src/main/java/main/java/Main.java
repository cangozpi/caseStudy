package main.java;

import main.java.Q1.CodeGeneration;

public class Main {

    public static void main(String[] args){
        //run Q1
        CodeGeneration codeGen = new CodeGeneration(8, "ACDEFGHKLMNPRTXYZ234579");
        //generate a code as a sample output
        String codeSample = codeGen.generateCode();
        System.out.println("Sample code generated: " + codeSample + "\nValidity of the Code: " + codeGen.checkCode(codeSample));

        //generate codes of sample size 1000
        int sampleSize = 1000;// # of codes generated
        codeGen.generateCodes(sampleSize);



    }


}
