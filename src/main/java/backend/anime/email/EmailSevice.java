package backend.anime.email;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailSevice{
    public void mailSendTo(String to, String subject, String html) throws MessagingException;
}
