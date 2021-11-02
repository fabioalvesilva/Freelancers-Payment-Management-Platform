/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import java.util.Scanner;
import pt.ipp.isep.dei.esoft.pot.controller.OrganizationRegisterController;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;


public class OrganizationRegisterUI
{
    private OrganizationRegisterController m_controller;
    
    
    public OrganizationRegisterUI()
    {
        m_controller = new OrganizationRegisterController();
    }

    public void run()
    {
        System.out.println("\nRegistar Organizacao:");

        if(introduzDados())
        {
            apresentaDados();

            if (Utils.confirm("Confirma os dados introduzidos? (S/N)")) {
                if (m_controller.organizationRegister()) {
                    System.out.println("Registo efetuado com sucesso.");
                } else {
                    System.out.println("Não foi possivel concluir o registo com sucesso.");
                }
            }
        }
        else
        {
            System.out.println("Ocorreu um erro. Operação cancelada.");
        }
    }
    
    private boolean introduzDados() {
        String strOrgName = Utils.readLineFromConsole("Nome da Organização: ");
        String strVAT = Utils.readLineFromConsole("NIF: ");
        System.out.println("\nInformação do Gestor (i.e. de quem procede ao registo):");  
        String strManagerName = Utils.readLineFromConsole("Nome da Gestor: ");
        String strManagerEmail = Utils.readLineFromConsole("EMail: ");
        String strColabName = Utils.readLineFromConsole("Nome do Colaborador: ");
        String strColabEmail = Utils.readLineFromConsole("EMail: ");
        
        return m_controller.newOrganization(strOrgName, strVAT, strManagerName, strManagerEmail, strColabName, strColabEmail);
    }
    
//    private void readOrganizationsFile(){
//        
//        Scanner readFile = new Scanner("organizations_list.txt");
//        
//        readFile.nextLine();
//        
//        String linha;
//        
//        while(readFile.hasNext()){
//            linha = readFile.nextLine();
//            String [] itensLinha = linha.split(";");
//             String strOrgName = itensLinha[0];
//             String strVAT = itensLinha[1];
//             String strManagerName = itensLinha[2];
//             String strManagerEmail = itensLinha[3];
//             String strColabName = itensLinha[4];
//             String strColabEmail = itensLinha[5];
//             
//             m_controller.newOrganization(strOrgName, strVAT, strManagerName, strManagerEmail, strColabName, strColabEmail);
//        }
//        
//    }
    
    private void apresentaDados() 
    {
        System.out.println("\n Informação a Registar:\n" + m_controller.getOrganizationString());
    }
}
