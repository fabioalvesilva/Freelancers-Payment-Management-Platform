/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author jpjes
 */
public class Utils implements Serializable {
    public static Scanner getFile(File file) {
        //File file = new File(fileName);
        Scanner scFile = null;
        try {
            scFile = new Scanner(file,"UTF-8");
            return scFile;
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
