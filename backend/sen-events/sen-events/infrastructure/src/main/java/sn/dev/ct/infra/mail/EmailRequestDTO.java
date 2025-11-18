package sn.dev.ct.infra.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmailRequestDTO {
    public String to;
    public String subject;
    public String content;
}
