/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import pt.ipp.isep.dei.esoft.pot.model.Constantes;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.TaskList;

/**
 *
 * @author Fábio Silva
 */
public class SchedulerPaymentController {
    
    private Platform m_oPlatform;
    private Organization m_Organization;
    private SimpleDateFormat schedulingDate;
    
    public SchedulerPaymentController(){
        
      
        if(!POTAplication.getInstance().getAtualSession().isLoggedInComPapel(Constantes.ORGANIZATION_MANAGER_ROLE))
            throw new IllegalStateException("Unauthorized User!");
        this.m_oPlatform = POTAplication.getInstance().getPlatform();
        this.m_Organization = m_oPlatform.getOrganizationRegister().getOrganizationByUserEmail(POTAplication.getInstance().getAtualSession().getEmailUtilizador());
        
        
    }
    
    public boolean setScheduler (String date){
        
        if(date == null || date.isEmpty() ){
            return false;
        }else{
            this.schedulingDate = new SimpleDateFormat (date);
            return true;
        }     
    }
    
    public TaskList getUnpaidTaskList (Organization organization){
        
        return organization.getUnpaidTaskList();
    } 
    
}
