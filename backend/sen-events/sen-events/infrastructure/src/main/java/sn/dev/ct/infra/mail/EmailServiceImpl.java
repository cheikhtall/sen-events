package sn.dev.ct.infra.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import org.thymeleaf.context.Context;

@Service @AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlEmail(EmailRequestDTO emailRequestDTO){
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(emailRequestDTO.getTo());
            helper.setSubject(emailRequestDTO.getSubject());
            helper.setText(emailRequestDTO.getContent(), true);
            mailSender.send(mimeMessage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendWelcomeEmail(AccountDTO account, String rawPassword) {

        Context context = new Context();
        context.setVariable("username", account.getEmail());
        context.setVariable("email", account.getEmail());
        context.setVariable("password", rawPassword);

        String html = templateEngine.process("user-created-template", context);

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setTo(account.getEmail());
        emailRequestDTO.setSubject("Bienvenue sur SEN-EVENTS");
        emailRequestDTO.setContent(html);

        sendHtmlEmail(emailRequestDTO);
    }
}
