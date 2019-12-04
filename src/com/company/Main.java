package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static String pareNoel = "\\*<]:-DOo";
    static String rens = "\\>:o\\)";
    static String follets = "\\<]:-D";


    public static void main(String[] args) throws IOException {
        System.out.println("Nombre: Andrés Bravo Ruiz");
        System.out.println("----Con expresión Regular----");
        buscarConExpresionRegular();
        System.out.println("");
        System.out.println("----Sin expresión regular----");
        buscarSinExpresionRegular();
    }

    private static void buscarSinExpresionRegular() throws IOException {

        char[] pareNoelChar = {'*','<',']',':','-','D','O', 'o'};
        char[] rensChar = {'>',':','o',')'};
        char[] folletsChar = {'<',']',':','-','D'};

        BufferedReader br = new BufferedReader(new FileReader("santako.txt"));
        String line = br.readLine();
        String caracs;

        while (line!=null){
            int p=0, r=0, f=0;
            int countPareNoel=0, countRens=0, countFollets=0;

            for (int j = 0; j <= line.length()-pareNoelChar.length ; j++) {
                caracs = line.substring(p,j+pareNoelChar.length);
                if(caracs.equals(String.valueOf(pareNoelChar))){
                    countPareNoel++;
                    countFollets--;
                }
                p++;
            }

            for (int j = 0; j <= line.length()-rensChar.length ; j++) {
                caracs = line.substring(r,j+rensChar.length);
                if(caracs.equals(String.valueOf(rensChar))){
                    countRens++;
                }
                r++;
            }

            for (int i = 0; i <=line.length()-folletsChar.length ; i++) {
                caracs = line.substring(f,i+folletsChar.length);
                if(caracs.equals(String.valueOf(folletsChar))){
                    countFollets++;
                }
                f++;
            }

            if(countPareNoel>0) System.out.print("Pare Noel (" + countPareNoel + ")" + " ");
            if(countRens>0) System.out.print("Ren (" + countRens + ")" + " ");
            if(countFollets>0) System.out.print("Follet (" + countFollets + ")");

            System.out.print("\n");
            line = br.readLine();
        }
    }

    static void buscarConExpresionRegular() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("santako.txt"));
        String line = br.readLine();

        while (line!=null){
            int countPareNoel=0;
            int countRens=0;
            int countFollets=0;

            Pattern patron_pareNoel = Pattern.compile(pareNoel);
            Pattern patron_rens = Pattern.compile(rens);
            Pattern patron_follets = Pattern.compile(follets);
            Matcher m_pareNoel = patron_pareNoel.matcher(line);
            Matcher m_rens = patron_rens.matcher(line);
            Matcher m_follets = patron_follets.matcher(line);

            while (m_pareNoel.find()){ countPareNoel++;
                countFollets--;}
            while (m_rens.find()) countRens++;
            while (m_follets.find()) countFollets++;

            if(countPareNoel>0) System.out.print("Pare Noel (" + countPareNoel + ")" + " ");
            if(countRens>0) System.out.print("Ren (" + countRens + ")" + " ");
            if(countFollets>0) System.out.print("Follet (" + countFollets + ")");

            System.out.print("\n");
            line=br.readLine();

        }
    }
}
