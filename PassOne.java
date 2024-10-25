package com.company;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class PassOne {


    static ArrayList<String> Ref2 = new ArrayList<>();
    static ArrayList<String> Label2 = new ArrayList<>();
    static ArrayList<String> Label = new ArrayList<>();
    static ArrayList<String> ObjectCode = new ArrayList<>();
    static ArrayList<String> Ref = new ArrayList<>();
    static ArrayList<Integer> LocCounter = new ArrayList<Integer>();
    static ArrayList<String> LocCounter1 = new ArrayList<>();
    static ArrayList<String> Inst = new ArrayList<>();
    private static final String[][] OPTAB = new String[59][3];
    public static final String[][] REG = new String[9][2];

    public static void Registers () {
        REG[0] = new String[]{"A", "0"};
        REG[1] = new String[]{"X", "1"};
        REG[2] = new String[]{"L", "2"};
        REG[3] = new String[]{"B", "3"};
        REG[4] = new String[]{"S", "4"};
        REG[5] = new String[]{"T", "5"};
        REG[6] = new String[]{"F", "6"};
        REG[7] = new String[]{"PC", "8"};
        REG[8] = new String[]{"SW", "9"};
    }

    public static void initialize () {
        OPTAB[0] = new String[]{"FIX", "1", "C4"};
        OPTAB[1] = new String[]{"FLOAT", "1", "C0"};
        OPTAB[2] = new String[]{"HIO", "1", "F4"};
        OPTAB[3] = new String[]{"NORM", "1", "C8"};
        OPTAB[4] = new String[]{"SIO", "1", "F0"};
        OPTAB[5] = new String[]{"TIO", "1", "F8"};
        OPTAB[6] = new String[]{"ADDR", "2", "90"};
        OPTAB[7] = new String[]{"CLEAR", "2", "B4"};
        OPTAB[8] = new String[]{"COMPR", "2", "A0"};
        OPTAB[9] = new String[]{"DIVR", "2", "9C"};
        OPTAB[10] = new String[]{"MULR", "2", "98"};
        OPTAB[11] = new String[]{"RMO", "2", "AC"};
        OPTAB[12] = new String[]{"SHIFTL", "2", "A4"};
        OPTAB[13] = new String[]{"SHIFTR", "2", "A8"};
        OPTAB[14] = new String[]{"SUBR", "2", "94"};
        OPTAB[15] = new String[]{"SVC", "2", "B0"};
        OPTAB[16] = new String[]{"TIXR", "2", "B8"};
        OPTAB[17] = new String[] {"ADD", "3", "18"};
        OPTAB[18] = new String[] {"ADDF", "3", "58"};
        OPTAB[19] = new String[] {"AND", "3", "40"};
        OPTAB[20] = new String[] {"COMP", "3", "28"};
        OPTAB[21] = new String[] {"COMPF", "3", "88"};
        OPTAB[22] = new String[] {"DIV", "3", "24"};
        OPTAB[23] = new String[] {"DIVF", "3", "64"};
        OPTAB[24] = new String[] {"J", "3", "3C"};
        OPTAB[25] = new String[] {"JEQ", "3", "30"};
        OPTAB[26] = new String[] {"JGT", "3", "34"};
        OPTAB[27] = new String[] {"JLT", "3", "38"};
        OPTAB[28] = new String[] {"JSUB", "3", "48"};
        OPTAB[29] = new String[] {"LDA", "3", "00"};
        OPTAB[30] = new String[] {"LDB", "3", "68"};
        OPTAB[31] = new String[] {"LDCH", "3", "50"};
        OPTAB[32] = new String[] {"LDF", "3", "70"};
        OPTAB[33] = new String[] {"LDL", "3", "08"};
        OPTAB[34] = new String[] {"LDS", "3", "6C"};
        OPTAB[35] = new String[] {"LDT", "3", "74"};
        OPTAB[36] = new String[] {"LDX", "3", "04"};
        OPTAB[37] = new String[] {"LPS", "3", "D0"};
        OPTAB[38] = new String[] {"MUL", "3", "20"};
        OPTAB[39] = new String[] {"MULF", "3", "60"};
        OPTAB[40] = new String[] {"OR", "3", "44"};
        OPTAB[41] = new String[] {"RD", "3", "D8"};
        OPTAB[42] = new String[] {"RSUB", "3", "4C"};
        OPTAB[43] = new String[] {"SSK", "3", "EC"};
        OPTAB[44] = new String[] {"STA", "3", "0C"};
        OPTAB[45] = new String[] {"STB", "3", "78"};
        OPTAB[46] = new String[] {"STCH", "3", "54"};
        OPTAB[47] = new String[] {"STF", "3", "80"};
        OPTAB[48] = new String[] {"STI", "3", "D4"};
        OPTAB[49] = new String[] {"STL", "3", "14"};
        OPTAB[50] = new String[] {"STS", "3", "7C"};
        OPTAB[51] = new String[] {"STSW", "3", "E8"};
        OPTAB[52] = new String[] {"STT", "3", "84"};
        OPTAB[53] = new String[] {"STX", "3", "10"};
        OPTAB[54] = new String[] {"SUB", "3", "1C"};
        OPTAB[55] = new String[] {"SUBF", "3", "5C"};
        OPTAB[56] = new String[] {"TD", "3", "E0"};
        OPTAB[57] = new String[] {"TIX", "3", "2C"};
        OPTAB[58] = new String[] {"WD", "3", "DC"};


    }


    public static int checkByte(String  x){
        int len = 0;
        if(x.substring(0, 1).equals("C"))
            len =  x.length() - 3 ;
        else
            len =  (x.length() - 3)/2;
        return len;
    }


    public static int string2Int(String s) {

        int integer = Integer.parseInt(s);

        return integer;
    }


    public static int ConvertHexaDecimal(String s) {

        int decimal = Integer.parseInt(s,16);

        return decimal;
    }

    public static String ConvertHexaToBinary(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);

            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }

        return bin;



    }

    public static void main(String[] args) throws IOException {
        //Read the file
        File f = new File("/Users/zeyadhassan/Desktop/inSICXE.txt");
        Scanner read = new Scanner(f);

        //Splitting the lines
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String s = line;
            String[] splitedline = s.split(" ");

            // if length == 3
            if (splitedline.length == 3) {
                Label.add(splitedline[0]);
                Inst.add(splitedline[1]);
                Ref.add(splitedline[2]);

            }
            // if length == 2
            if (splitedline.length == 2) {
                Label.add("$");
                Inst.add(splitedline[0]);
                Ref.add(splitedline[1]);
            }
            // if length == 1
            if (splitedline.length == 1) {
                Label.add("$");
                Inst.add(splitedline[0]);
                Ref.add("$");
            }

        }
        //System.out.println(Label);
        //System.out.println(Inst);
        //System.out.println(Ref);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Pass One
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String s = line;
            String[] splitedline = s.split(" ");

            Label.add(splitedline[0]);
            Inst.add(splitedline[1]);
            Ref.add(splitedline[2]);

        }

        LocCounter.add(0);
        LocCounter.add(ConvertHexaDecimal(Ref.get(0)));
        //System.out.println(LocCounter);
        int i = 1;
        int increase = 0;
        while(i < Inst.size()){

            if(Inst.get(i).matches("WORD")){
                increase = 3;
            }
            else if(Inst.get(i).matches("BYTE")) {
                increase = checkByte(Ref.get(i));
            }

            else if(Inst.get(i).matches("RESW")){
                increase = 3 *  string2Int(Ref.get(i));
            }

            else if(Inst.get(i).matches("RESB")){
                increase = 1 * string2Int(Ref.get(i));
            }

            else if(Inst.get(i).startsWith("+")){
                increase = 4 ;
            }

            else if(Inst.get(i).matches("CLEAR")){
                increase = 2 ;
            }

            else if(Inst.get(i).matches("ADDR")) {
                increase = 2;
            }

            else if(Inst.get(i).matches("COMPR")){
                increase = 2 ;
            }

            else if(Inst.get(i).matches("DIVR")){
                increase = 2 ;
            }

            else if(Inst.get(i).matches("MULR")){
                increase = 2 ;
            }

            else if(Inst.get(i).matches("RMO")){
                increase = 2 ;
            }
            else if(Inst.get(i).matches("SHIFTL")){
                increase = 2 ;
            }
            else if(Inst.get(i).matches("SHIFTR")){
                increase = 2 ;
            }
            else if(Inst.get(i).matches("SUBR")){
                increase = 2 ;
            }
            else if(Inst.get(i).matches("SVC")){
                increase = 2 ;
            }
            else if(Inst.get(i).matches("TIXR")){
                increase = 2 ;
            }

            else if(Inst.get(i).matches("FIX")){
                increase = 1 ;
            }
            else if(Inst.get(i).matches("FLOAT")){
                increase = 1 ;
            }
            else if(Inst.get(i).matches("HIO")){
                increase = 1 ;
            }
            else if(Inst.get(i).matches("NORM")){
                increase = 1 ;
            }
            else if(Inst.get(i).matches("SIO")){
                increase = 1 ;
            }

            else if(Inst.get(i).matches("TIO")){
                increase = 1 ;
            }

            else if(Inst.get(i).matches("BASE")){
                LocCounter1.add("$");
            }

            else {

                increase = 3;
            }

            LocCounter.add(increase + LocCounter.get(i));

            i++;

        }




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Pass two


 /*       int N,I,X,B,P;
        initialize();
        ObjectCode.add("");
        for (int j = 0; j < Inst.size(); j++) {
                for (int k = 0; k < Label.size(); k++) {
                }

        }


        for(int j=1 ; j<Inst.size() -1 ; j++) {
            String tString = "";
            String TempInst = Inst.get(j);
            String TempRef = Ref.get(j);

            boolean plusFlag = false, XFlag = false, hashFlag = false, atFlag = false, baseFlag = false, formatTwoFlag = false;



////////////////////////////////////////////////////////////////////////////
            //Format 2
            for(int k=0 ; k<59 ; k++){
                if(TempInst.equals(OPTAB[k][0])){
                    if(OPTAB[k][1].equals("2")){
                        tString = tString + OPTAB[k][2];

                        formatTwoFlag = true;
                        break;
                    }
                }
            }
            if(formatTwoFlag){
                String[] reg = TempRef.split(",");
                for(int k=0; k<reg.length;k++){
                    for( int z=0;z<9;z++){
                        if (reg[k].equals(REG[z][0])){
                            tString=tString+REG[z][1];
                        }
                    }
                }

                if(tString.length()<4){
                    tString = tString + "0";
                }
                ObjectCode.add(tString);
                continue;
            }


            if(Inst.get(j).charAt(0) == '+'){
                TempInst = Inst.get(j).substring(1);
                plusFlag = true;

            }else{
                TempInst = Inst.get(j);
            }

            if(TempInst.equals("WORD")){
                TempInst = Ref.get(j);








        }

////////////////////////////////////////////////////////////////////////////////////////

        //Format 3
        int E = 0;
        if(Ref.get(i).startsWith("#")){
            I = 1;
            N = 0;
            X = 0;
        }
        else if(Ref.get(i).startsWith("@")){
            I = 0;
            N = 1;
            X = 0;
        }
        else if(Ref.get(i).contains(",X")){
            X = 1;
            N = 0;
            I = 0;
            if(!Ref.get(i).startsWith("#")  &&  !Ref.get(i).startsWith("@")){
                N = 1;
                I = 1;
                X = 0;
            }
        }




        //getting target address

        Integer TA = null;
        for(int j = 1; j < Label.size() ; j++){
            if(!(Label.get(j).equals("$"))){
                TA = LocCounter.get(j);
                // System.out.print(String.format("%04x", LocCounter.get(j)) + "\n");
                System.out.println("TArget address"+ String.format("%04x",TA));
            }
        }


*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Printing the valuess
        System.out.print("Location | Labels | Instructions | References | Object Code | \n" );

        System.out.print("------------------------------------------------------- \n" );



        for(int k = 0; k < Label.size() ; k++){
            Label2.add(k, Label.get(k));

            Ref2.add(k, Ref.get(k));
            if(Label2.get(k).matches("$")){

                Label2.set(k, " ");
            }
            if(Ref2.get(k).matches("$")){

                Ref2.set(k, " ");

            }
            System.out.print(String.format("%04x", LocCounter.get(k))+
                    "\t | \t"+Label2.get(k)+"\t | \t"+ Inst.get(k)+
                    "\t | \t"+Ref2.get(k)  +"\t | \t"+  "\n" );
        }


        System.out.println("---------------------------"
                + "----------------------------------------------------");
        System.out.println("Code Length = " +
                String.format("%04x", (LocCounter.get(LocCounter.size()-1)
                        - LocCounter.get(1))));
        System.out.println("-------------------"
                + "------------------------------------------------------------");
        System.out.println("    SYMBOL TABLE    ");
        System.out.println("-----------------------");
        System.out.println("Labels\t |\tAddress");
        System.out.println("-----------------------");
        for(int j = 1; j < Label.size() ; j++){
            if(!(Label.get(j).equals("$"))){
                System.out.print(  Label.get(j)+ "\t|\t" +
                        String.format("%04x", LocCounter.get(j)) + "\n");
            }
        }
        System.out.println("-----------------------");

    }




}

