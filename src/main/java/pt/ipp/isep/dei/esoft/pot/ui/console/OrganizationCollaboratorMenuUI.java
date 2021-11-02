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
 * @author Fábio Silva
 */
public class OrganizationCollaboratorMenuUI {

    public OrganizationCollaboratorMenuUI() {

    }

    public void run() {
        List<String> options = new ArrayList<String>();
        options.add("Load Transaction file");
        options.add("Create task");
        options.add("Create transaction");
        options.add("Add Freelancer");
        options.add("Performance Indicators");

        int opcao = 0;
        do {
            opcao = Utils.apresentaESelecionaIndex(options, "\n\nCollaborator Menu");

            switch (opcao) {
                case 0:
                    EspecificarAreaAtividadeUI uiCat = new EspecificarAreaAtividadeUI();
                    uiCat.run();
                    break;
                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;
            }

            // Incluir as restantes opções aqui
        } while (opcao != -1);
    }
}
