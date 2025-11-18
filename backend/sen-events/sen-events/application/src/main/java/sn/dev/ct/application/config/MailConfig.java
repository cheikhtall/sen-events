package sn.dev.ct.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MailSenderAutoConfiguration.class) // Assure que JavaMailSender est créé
public class MailConfig {
}
