/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ipp.isep.dei.esoft.pot.model.Category;
import pt.ipp.isep.dei.esoft.pot.model.Constantes;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.pot.model.TaskList;
import pt.ipp.isep.dei.esoft.pot.model.Task;

/**
 *
 * @author
 */
public class CreateTaskController {

    private POTAplication m_oApp;
    private Platform m_oPlatform;
    private TaskList taskList;
    private Task task;
    private Organization m_oOrganization;

    public CreateTaskController() {
        if (!POTAplication.getInstance().getAtualSession().isLoggedInComPapel(Constantes.ORGANIZATION_COLLABORATOR_ROLE)) {
            throw new IllegalStateException("Unregistered user");
        }
        this.m_oApp = POTAplication.getInstance();
        this.m_oPlatform = m_oApp.getPlatform();
        this.m_oOrganization = this.m_oPlatform.getOrganizationRegister().getOrganizationByUserEmail(POTAplication.getInstance().getAtualSession().getEmailUtilizador());
    }

    public boolean newTask(String id, String description, double assignDuration, double coustPerHour, Category category) {
        try {
            this.task = m_oOrganization.getM_taskList().newTask(id, description, assignDuration, coustPerHour, category);
            return m_oOrganization.getM_taskList().registTask(this.task);
        } catch (RuntimeException ex) {
            Logger.getLogger(Utils.class
                    .getName()).log(Level.SEVERE, null, ex);
            this.task = null;
            return false;
        }
    }

    public String getTaskList() {
        return taskList.toString();
    }
}
