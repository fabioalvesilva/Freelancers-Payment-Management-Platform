/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import javafx.stage.FileChooser;

/**
 *
 * @author jpjes
 */
public class LoadTransactionController {

    
    private FileChooser fileChooser;
    
    private LoadTransactionController() {
        fileChooser = new FileChooser();
        associateFilter("File with Transactions (txt)", "*.txt");
        associateFilter("File with Transactions (csv)", "*.csv");
    }
    
    public static FileChooser createFileChooserTransaction() {
        LoadTransactionController fcLoadTransaction = new LoadTransactionController();
        return fcLoadTransaction.fileChooser;
    }
    
    private void associateFilter(String desc, String ext) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(desc, ext);
        fileChooser.getExtensionFilters().add(filter);
    }
}
