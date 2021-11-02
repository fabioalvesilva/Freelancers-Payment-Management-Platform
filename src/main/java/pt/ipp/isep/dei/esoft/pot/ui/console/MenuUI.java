/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserRole;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

public class MenuUI {

    public MenuUI() {
    }

    public void run() throws IOException {

        List<String> options = new ArrayList<String>();
        options.add("Login");
        options.add("Register Organization");
        options.add("Create Freelancer");

        int opcao = 0;
        do {
            opcao = Utils.apresentaESelecionaIndex(options, "\n\nMain Menu");

            switch (opcao) {
                case 0:
                    AutenticacaoUI uiLogin = new AutenticacaoUI();
                    boolean sucesso = uiLogin.run();
                    if (sucesso) {
                        redirecionaParaUI(uiLogin.getPapeisUtilizador());
                    }
                    uiLogin.logout();
                    break;
                case 1:
                    OrganizationRegisterUI ui = new OrganizationRegisterUI();
                    ui.run();
                    break;
                case 2:
                    CreateFreelancerUI freelancerUI = new CreateFreelancerUI();
                    freelancerUI.run();
                    break;
            }
        } while (opcao != -1);
    }

    private void redirecionaParaUI(UserRole uRole) {
        if (uRole == null) {
            System.out.println("The user does not have any role associated.");
            return;
        }
        
//        UserRole role = selecionaPapel(uRole);

        if (uRole.hasId("ADMINISTRATOR_ROLE")) {
            AdministratorMenuUI ui = new AdministratorMenuUI();
            ui.run();
        }
        if (uRole.hasId("ORGANIZATION_MANAGER")) {
            OrganizationManagerMenuUI ui = new OrganizationManagerMenuUI();
            ui.run();
        }
        if (uRole.hasId("ORGANIZATION_COLLABORATOR")) {
            OrganizationCollaboratorMenuUI ui = new OrganizationCollaboratorMenuUI();
            ui.run();
        }
    }

    private UserRole selecionaPapel(List<UserRole> papeis) {
        if (papeis.size() == 1) {
            return papeis.get(0);
        } else {
            return (UserRole) Utils.apresentaESeleciona(papeis, "Escolha o papel que pretende assumir:");
        }
    }
}
