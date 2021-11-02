package pt.ipp.isep.dei.esoft.pot.model.EmailAPI;

import java.io.Serializable;



public class EmailSystemAdapter1 implements EmailSystem, Serializable {

    private EmailSystemApi1 api;

    public EmailSystemAdapter1() {

        this.api = new EmailSystemApi1();

    }



    @Override
    public void sendEmail(String email, String subject, String body) {
        api.sendEmail( email,  subject,  body);
    }



}
