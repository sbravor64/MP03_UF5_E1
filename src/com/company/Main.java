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
//        buscarConExpresionRegular();
        buscarSinExpresionRegular();
    }

    private static void buscarSinExpresionRegular() throws IOException {

        int longitudPareNoel = 8;
        int longitudRens = 4;
        int longitudFollets = 5;

        char[] pareNoelChar = {'*','<',']',':','-','D','O', 'o'};
        char[] rensChar = {'>',':','o',')'};
        char[] folletsChar = {'<',']',':','-','D'};

        int countPareNoel=0, countRens=0, countFollets=0;

        BufferedReader br = new BufferedReader(new FileReader("santako.txt"));
        String line = br.readLine();
        char[] caracLine;

        while (line!=null){
            caracLine = line.toCharArray();

            int p=0, r=0, f=0, contadorCarac=0;
            for (char c: caracLine) {
                if(c==pareNoelChar[p]){
                    p++;
                    contadorCarac++;
                    if(contadorCarac==longitudPareNoel){
                        countPareNoel++;
                        contadorCarac=0;
                        p=0;
                    }
                    if(p>=pareNoelChar.length) p=0;
                } else if(c==rensChar[r]){
                    r++;
                    contadorCarac++;
                    if(contadorCarac==longitudRens){
                        countRens++;
                        contadorCarac=0;
                        r=0;
                    }
                    if(r>=rensChar.length) r=0;
                } else if(c==folletsChar[f]){
                    f++;
                    contadorCarac++;
                    if(contadorCarac==longitudFollets){
                        countFollets++;
                        contadorCarac=0;
                        f=0;
                    }
                    if(f>=folletsChar.length) f=0;
                }
            }

            if(countPareNoel>0){
                System.out.print("Pare Noel (" + countPareNoel + ")" + " ");
            }
            if(countRens>0){
                System.out.print("Ren (" + countRens + ")"  + " ");
            }
            if(countFollets>0){
                System.out.print("Follet (" + countFollets + ")");
            }

            System.out.print("\n");
            countPareNoel=0;
            countRens=0;
            countFollets=0;

            line = br.readLine();
        }
    }

    static void buscarConExpresionRegular() throws IOException {
        int countPareNoel=0;
        int countRens=0;
        int countFollets=0;

        BufferedReader br = new BufferedReader(new FileReader("santako.txt"));
        String line = br.readLine();

        while (line!=null){
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

            if(countPareNoel>0){
                System.out.print("Pare Noel (" + countPareNoel + ")" + " ");
            }
            if(countRens>0){
                System.out.print("Ren (" + countRens + ")"  + " ");
            }
            if(countFollets>0){
                System.out.print("Follet (" + countFollets + ")");
            }
            System.out.print("\n");
            countFollets=0; countPareNoel=0; countRens=0;
            line=br.readLine();

        }
    }
}
