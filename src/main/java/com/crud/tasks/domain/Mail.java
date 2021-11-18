package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCCs;

    public static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;
        private String toCCs;

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MailBuilder toCC(String cc) {
            this.toCCs = Optional.ofNullable(cc).orElse(null);
            return this;
        }

        public Mail create() {
            return new Mail(mailTo, subject, message, toCCs);
        }
    }




}
