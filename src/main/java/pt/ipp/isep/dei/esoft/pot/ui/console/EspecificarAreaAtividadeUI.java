/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import pt.ipp.isep.dei.esoft.pot.controller.EspecificarAreaAtividadeController;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author paulomaio
 */
public class EspecificarAreaAtividadeUI
{
    private EspecificarAreaAtividadeController m_controller;
    public EspecificarAreaAtividadeUI()
    {
        m_controller = new EspecificarAreaAtividadeController();
    }

    public void run()
    {
        System.out.println("\nEspecificar Área de Atividade:");

        if(introduzDados())
        {
            apresentaDados();

            if (Utils.confirm("Confirma os dados introduzidos? (S/N)")) {
                if (m_controller.registaAreaAtividade()) {
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
        String strCodigo = Utils.readLineFromConsole("Código: ");
        String strDescricaoBreve = Utils.readLineFromConsole("Descrição Breve: ");
        String strDescricaoDetalhada = Utils.readLineFromConsole("Descrição Detalhada: ");
        
        return m_controller.novaAreaAtividade(strCodigo, strDescricaoBreve,strDescricaoDetalhada);
    }
    
    private void apresentaDados() 
    {
        System.out.println("\nÁrea de Atividade:\n" + m_controller.getAreaAtividadeString());
    }
}
