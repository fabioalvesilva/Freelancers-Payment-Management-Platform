package pt.ipp.isep.dei.esoft.pot.model.EmailAPI;

public interface EmailSystem {
    public void sendEmail(String email, String subject, String body);
}
