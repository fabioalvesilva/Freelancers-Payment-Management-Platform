/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rui Marinho
 */
public class UtilFileWriter implements Serializable {
    private String emailFile;
    private String paymentFile;
    private static  String EMAILFILE = "Emails.txt";
    private static String  PAYMENTFILE = "Payments.txt";
    public UtilFileWriter() {
        this.emailFile = EMAILFILE;
        this.paymentFile = PAYMENTFILE;
    }

    public   void writeEmail(String email){

        writeToFile(email, emailFile);

    }

    public   void writePayment(ArrayList<String> paymentTransaction){
        for (String s : paymentTransaction) {
            writeToFile(s, paymentFile);
        }


    }
    private static void writeToFile(String data, String file) {
        //Inspired on https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(data);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        finally {
            if(out != null)
                out.close();
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
    }
}
