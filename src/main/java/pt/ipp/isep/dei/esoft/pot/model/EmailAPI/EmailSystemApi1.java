package pt.ipp.isep.dei.esoft.pot.model.EmailAPI;

import java.io.Serializable;
import pt.ipp.isep.dei.esoft.utils.UtilFileWriter;



    public class EmailSystemApi1 implements Serializable {
        //Todo use Platform writer
        //Utils.Scanner . Write to file
        //private id;
        UtilFileWriter writer =new UtilFileWriter();

        public EmailSystemApi1() {
            super();
        }



        public void sendEmail(String email, String subject, String body){
            String string = String.format("Email: %s; \t Subject: %s; \t Body: %s;",email,subject, body);
            writer.writeEmail(string);

        }


    }