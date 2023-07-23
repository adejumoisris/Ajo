package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.dto.request.EmailDto;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.AjoPay.AjoPay.service.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender mailSender; // autowiring mail sender

    @Value("ajopay554@gmail.com")
    private String senderEmail;

    @Async
    public void sendMail(EmailDto emailDto) {
        log.info("sending mail, building mail ");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setFrom(emailDto.getSender());
            mimeMessageHelper.setTo(emailDto.getRecipient());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody());

        };

        try {
            CompletableFuture.runAsync(()->
                    mailSender.send(mimeMessagePreparator)).exceptionally(exp ->{
                        throw new CustomException("Exception occure while sending mail [message]" + exp.getLocalizedMessage());
            });
            log.info("mail as sent");
        }catch (MailException exception){
            log.error("exception occure when sending mail {}", exception.getMessage());
            throw new CustomException("Exception occur when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }

    }


    //  <<<<<<---------------------SENDING MAIL WITH AN ATTACHMENT------------------------------------>>>>>

    @Async
    public void sendEmailWithAttachment(EmailDto emailDto) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("ajopay554@gmail.com");
        mimeMessageHelper.setTo(emailDto.getRecipient());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(emailDto.getBody());


        try {
            FileSystemResource fileSystemResource =
                    new FileSystemResource(new File(emailDto.getAttachment()));

            mimeMessageHelper
                    .addAttachment(Objects
                            .requireNonNull(fileSystemResource.getFilename()),fileSystemResource);

            mailSender.send(mimeMessage);
            log.info("email has sent!!");
        } catch (MailException exception) {
            log.error("Exception occurred when sending mail {}",exception.getMessage());
            throw new CustomException("Exception occurred when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }


    }









//    public void sendSimpleEmail(String toEmail, String body, String subject){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("idrisadejumo@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//
//        System.out.println("mail sent ");
//
//    }


}
