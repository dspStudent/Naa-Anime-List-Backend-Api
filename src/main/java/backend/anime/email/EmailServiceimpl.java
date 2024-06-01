package backend.anime.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceimpl implements EmailSevice{
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public void mailSendTo(String to, String subject, String html) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("teamcoderid@gmail.com");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(html, true);
        javaMailSender.send(mimeMessage);
    }
}
