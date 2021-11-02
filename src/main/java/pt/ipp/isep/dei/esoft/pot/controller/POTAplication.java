/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import pt.ipp.isep.dei.esoft.autorizacao.AutorizationFacade;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.model.Constantes;
import pt.ipp.isep.dei.esoft.pot.model.CurrencySystem.CurrencyConverter;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import static pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister.FILE_NAME;
import pt.ipp.isep.dei.esoft.pot.model.Platform;

/**
 *
 * @author paulomaio
 */
public class POTAplication
{
       
    private final Platform m_thePlatform;
    private final AutorizationFacade m_oAutorization;
    private final Properties props;

    //todo RM - Changes for loading from porperties
    private EmailSystem email;
    private CurrencyConverter currency;

    public static final String FILE_NAME = "Platform.ltf";
    
    private POTAplication()
    {
        props = getProperties();
        this.m_thePlatform = read();
        this.m_oAutorization = this.m_thePlatform.getAutorizationFacade();
        EmailSystem testemail = this.m_thePlatform.getEmailSytem();
        CurrencyConverter testcurrency = this.m_thePlatform.getM_currency();
        bootstrap();
    }
    
    public Platform read() {
        return read(FILE_NAME);
    }
    
    public Platform read(String fileName) {
        return read(new File(fileName));
    }
    
    public Platform read(File file) {
        Platform platform;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                platform = (Platform) in.readObject();
            } finally {
                in.close();
            }
            return platform;
        } catch (IOException | ClassNotFoundException ex) {
            return new Platform(props.getProperty(Constantes.PARAMS_PLATFORM_DESCRIPTION), email, currency);
        }
    }
    
    public boolean save(Platform platform) {
        return save(FILE_NAME, platform);
    }
    
    public boolean save(String fileName, Platform platform) {
        return save(new File(fileName), platform);
    }

    public boolean save(File file, Platform platform) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(platform);
            } catch(Exception e) {
                System.out.println(e);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    
    public Platform getPlatform()
    {
        return this.m_thePlatform;
    }
    

    public UserSession getAtualSession()
    {
        return this.m_oAutorization.getAtualSession();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
       return this.m_oAutorization.doLogin(strId,strPwd) != null;
    }
    
    public void doLogout()
    {
        this.m_oAutorization.doLogout();
    }
    
    private Properties getProperties()
    {
        Properties props = new Properties();
        
        // Adiciona propriedades e valores por omissão
        props.setProperty(Constantes.PARAMS_PLATFORM_DESCRIPTION, "Task for Joe");

        
        // Lê as propriedades e valores definidas 
        try
        {
            InputStream in = new FileInputStream(Constantes.PARAMS_FILE);
            props.load(in);
            in.close();
            //Protected variation for EmailAdapter
            Class<?> eClass = Class.forName(props.getProperty("Plataforma.EmailSystem.Class"));
            email = (EmailSystem) eClass.newInstance();
            //Protected variation for CurrencyConverter
            Class<?> cClass = Class.forName(props.getProperty("Plataforma.CurrencyConverter.Class"));
            currency = (CurrencyConverter) cClass.newInstance();



        }
        catch(IOException | InstantiationException | IllegalAccessException | ClassNotFoundException ex)
        {
            System.out.println("exception handling getting properties");
        }
        return props;
    }

    
    private void bootstrap()
    {
        this.m_oAutorization.userRoleRegister(Constantes.ADMINISTRATOR_ROLE);
        this.m_oAutorization.userRoleRegister(Constantes.FREELANCER_ROLE);
        this.m_oAutorization.userRoleRegister(Constantes.ORGANIZATION_MANAGER_ROLE);
        this.m_oAutorization.userRoleRegister(Constantes.ORGANIZATION_COLLABORATOR_ROLE);
        
        this.m_oAutorization.userRegisterWithRole("Administrator", "admin@t4j.pt", "123456",Constantes.ADMINISTRATOR_ROLE);
       
        this.m_oAutorization.userRegisterWithRole("Colaborador", "colaborador", "123456",Constantes.ORGANIZATION_COLLABORATOR_ROLE);
        this.m_oAutorization.userRegisterWithRole("Gestor", "gestor", "123456",Constantes.ORGANIZATION_MANAGER_ROLE);
        
        
        this.m_oAutorization.userRegisterWithRole("Freelancer 1", "free1@esoft.pt", "123456",Constantes.FREELANCER_ROLE);
        this.m_oAutorization.userRegisterWithRole("Freelancer 2", "free2@esoft.pt", "123456",Constantes.FREELANCER_ROLE);
        
        this.m_oAutorization.userRegisterWithRoles("Martim", "martim@esoft.pt", "123456",new String[] {Constantes.FREELANCER_ROLE, Constantes.ADMINISTRATOR_ROLE});
    }
    
    // Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static POTAplication singleton = null;
    public static POTAplication getInstance()
    {
        if(singleton == null) 
        {
            synchronized(POTAplication.class) 
            {
                singleton = new POTAplication();
            }
        }
        return singleton;
    }
    public EmailSystem getEmailSystem() {


        //todo only for test
        //oAlg.sendEmail("jhbgvfcvh", "gvhjbkl", "njkbhknlçjhjg");

        return null;
    }
    
}
