/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;

/**
 *
 * @author Fábio Silva
 */
public class Currency implements Serializable {

    double valor;

    Currency(double valor) {
        this.valor = valor;
    }

}
