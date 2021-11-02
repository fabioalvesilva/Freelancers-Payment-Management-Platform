/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Fábio Silva
 */
public class PasswordGenerator implements AlgPasswordGenerator, Serializable {

    @Override
    public String genPassword() {
    

    int length = 6;

    final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
    final char[] numbers = "0123456789".toCharArray();
    final char[] symbols = "^$?!@#%&".toCharArray();
    final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();

    
    Random random = new SecureRandom();

//    StringBuilder password = 1234; 

//    for (int i = 0; i < length-4; i++) {
//        password.append(allAllowed[random.nextInt(allAllowed.length)]);
//    }
//
//    password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
//    password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
//    password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
//    password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);
    
    return "1234";
    }

    }
  
