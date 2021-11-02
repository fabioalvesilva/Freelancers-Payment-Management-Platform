/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joaquim
 */
public class StatisticsTest {

    public StatisticsTest() {
    }

    /**
     * Test of delayMean method, of class Statistics.
     */
    @Test
    public void testDelayMean() {

        List<Transaction> tempList = new ArrayList<>();
        Date date = new Date(2020, 01, 25);
        Address address = new Address("Rua Sao Tomas", "54", "Porto", "Portugal");
        Category category = new Category("Software Development");
        Freelancer freelancer = new Freelancer("MS3", "Maria Sousa", "Senior", "ms10@isep.ipp.pt", 213456789, "PT120010001234567891", address);
        Task task1 = new Task("4", "Mobile Application Development", 60.0, 50.0, category);
        Task task2 = new Task("5", "Mobile Application Development", 60.0, 50.0, category);
        Transaction transaction1 = new Transaction(104, date, -2, "High Quality Work", freelancer, task1);
        Transaction transaction2 = new Transaction(105, date, 0, "High Quality Work", freelancer, task2);
        tempList.add(transaction1);
        tempList.add(transaction2);

        System.out.println("delayMean");
        Statistics instance = new Statistics();
        double expResult = -1.0;
        double result = instance.delayMean(tempList);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of delayStandardDeviation method, of class Statistics.
     */
    @Test
    public void testDelayStandardDeviation() {

        List<Transaction> tempList = new ArrayList<>();
        Date date = new Date(2020, 01, 25);
        Address address = new Address("Rua Sao Tomas", "54", "Porto", "Portugal");
        Category category = new Category("Software Development");
        Freelancer freelancer = new Freelancer("MS3", "Maria Sousa", "Senior", "ms10@isep.ipp.pt", 213456789, "PT120010001234567891", address);
        Task task1 = new Task("4", "Mobile Application Development", 60.0, 50.0, category);
        Task task2 = new Task("5", "Mobile Application Development", 60.0, 50.0, category);
        Transaction transaction1 = new Transaction(104, date, -2, "High Quality Work", freelancer, task1);
        Transaction transaction2 = new Transaction(105, date, 0, "High Quality Work", freelancer, task2);
        tempList.add(transaction1);
        tempList.add(transaction2);

        System.out.println("delayStandardDeviation");
        Statistics instance = new Statistics();
        double expResult = 1.0;
        double result = instance.delayStandardDeviation(tempList);
        assertEquals(expResult, result, 0.005);
    }

    /**
     * Test of paymentMean method, of class Statistics.
     */
    @Test
    public void testPaymentMean() {

        List<Transaction> tempList = new ArrayList<>();
        Category category = new Category("Software Development");
        Task task1 = new Task("4", "Mobile Application Development", 60.0, 50.0, category);
        Task task2 = new Task("5", "Mobile Application Development", 60.0, 50.0, category);
        Currency currency = new Currency(1.0);
        Payment payment = new Payment(1, 1, 3000.0, currency);
        Transaction transaction1 = new Transaction(104, payment, task1);
        Transaction transaction2 = new Transaction(104, payment, task2);
        tempList.add(transaction1);
        tempList.add(transaction2);

        System.out.println("paymentMean");
        Statistics instance = new Statistics();
        double expResult = 3000.0;
        double result = instance.paymentMean(tempList);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of paymentStandardDeviation method, of class Statistics.
     */
    @Test
    public void testPaymentStandardDeviation() {

        List<Transaction> tempList = new ArrayList<>();
        Category category = new Category("Software Development");
        Task task1 = new Task("4", "Mobile Application Development", 60.0, 50.0, category);
        Task task2 = new Task("5", "Mobile Application Development", 60.0, 50.0, category);
        Currency currency = new Currency(1.0);
        Payment payment = new Payment(1, 1, 3000.0, currency);
        Transaction transaction1 = new Transaction(104, payment, task1);
        Transaction transaction2 = new Transaction(104, payment, task2);
        tempList.add(transaction1);
        tempList.add(transaction2);

        System.out.println("paymentStandardDeviation");
        Statistics instance = new Statistics();
        double expResult = 0.0;
        double result = instance.paymentStandardDeviation(tempList);
        assertEquals(expResult, result, 0.05);
    }

}
