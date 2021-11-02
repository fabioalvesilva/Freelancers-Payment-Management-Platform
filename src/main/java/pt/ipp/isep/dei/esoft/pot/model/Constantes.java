/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;


public class Constantes implements Serializable
{
    public static final String ADMINISTRATOR_ROLE = "ADMINISTRATOR";
    public static final String ORGANIZATION_COLLABORATOR_ROLE = "ORGANIZATION_COLLABORATOR";
    public static final String ORGANIZATION_MANAGER_ROLE = "ORGANIZATION_MANAGER";
    public static final String FREELANCER_ROLE = "FREELANCER";
    
    public static final String PARAMS_FILE = "src/main/resources/config.properties";
    public static final String PARAMS_PLATFORM_DESCRIPTION = "Platform.Description";
    public static final String PARAMS_PLATFORM_EmailSystem = "Plataforma.EmailSystem.Class";
    
    public static final String CARACTER_SPLIT = ";";
}
