package pt.ipp.isep.dei.esoft.pot.model.EmailAPI;


import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import java.io.Serializable;

public class EmailSystemAdapter2 implements EmailSystem, Serializable {

    private EmailSystemApi1 api;

    public EmailSystemAdapter2() {

        this.api = new EmailSystemApi1();

    }



    @Override
    public void sendEmail(String email, String subject, String body) {
        api.sendEmail( email,  subject,  body);
    }


}
