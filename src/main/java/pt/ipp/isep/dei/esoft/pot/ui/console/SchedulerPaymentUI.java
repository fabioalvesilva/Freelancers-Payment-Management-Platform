/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import pt.ipp.isep.dei.esoft.pot.controller.SchedulerPaymentController;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author Fábio Silva
 */

public class SchedulerPaymentUI {
    
    private SchedulerPaymentController m_controller;
    
    public SchedulerPaymentUI(){
        m_controller = new SchedulerPaymentController();
    }
    
    public void run()
    {
        System.out.println("\nScheduling Payment:");

        if(introduzDados())
        {
            apresentaDados();

//            if (Utils.confirm("Do you confirm? (Y/N)")) {
//                if (m_controller.organizationRegister()) {
//                    System.out.println("Payments scheduling with success.");
//                } else {
//                    System.out.println("It was not possible. Sorry! Go fuck your self");
//                }
//            }
        }
        else
        {
            System.out.println("An error had occurred.");
        }
    }
    
    private boolean introduzDados() {
        
        
        String strDate = Utils.readLineFromConsole("Date (dd-mm-yyyy hh:mm):");
        
        
        return m_controller.setScheduler(strDate);
    }
    
    private void apresentaDados() 
    {
        System.out.println("\n Informação a Registar:\n");
    }
    
}
