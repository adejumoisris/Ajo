package com.AjoPay.AjoPay.service;

import com.AjoPay.AjoPay.dto.request.EmailDto;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.RequestBody;

public interface SendMailService {

  public void sendMail(EmailDto emailDto);
  public void sendEmailWithAttachment(EmailDto emailDto) throws MessagingException;
}
