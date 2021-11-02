/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author paulomaio
 */
public class OrganizationManagerMenuUI
{
    
    public OrganizationManagerMenuUI()
    {
    }

    public void run()
    {
        List<String> options = new ArrayList<String>();
        options.add("Schedule Payments");
        // Adicionar Aqui Outras Opções
        
        int opcao = 0;
        do
        {            
            opcao = Utils.apresentaESelecionaIndex(options, "\n\nManager Menu");

            switch(opcao)
            {
                case 0:
                    SchedulerPaymentUI uiPayment = new SchedulerPaymentUI();
                    uiPayment.run();
                    break;
                case 1:
                    break;
                    
            }

            // Incluir as restantes opções aqui
            
        }
        while (opcao != -1 );
    }
}
