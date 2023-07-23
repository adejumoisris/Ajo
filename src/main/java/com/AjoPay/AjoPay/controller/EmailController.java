package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.request.EmailDto;
import com.AjoPay.AjoPay.dto.response.ApiResponse;
import com.AjoPay.AjoPay.dto.response.AppResponse;
import com.AjoPay.AjoPay.service.SendMailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("mail")
public class EmailController {
    private final SendMailService sendMailService;

   public ResponseEntity<AppResponse<Boolean>> sendmail(@RequestBody EmailDto emailDto){
       log.info("email controller -: send email to {}", emailDto.getRecipient());
       CompletableFuture<Void> future = CompletableFuture.runAsync(()-> sendMailService.sendMail(emailDto));
       return
               future.isDone() ?
                       ResponseEntity.ok(AppResponse.<Boolean>builder()
                               .isSuccessful(true)
                               .message("email sent successfully")
                               .result(true)
                               .build()
                       )
                       :
                       ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(AppResponse.<Boolean>builder()
                               .isSuccessful(false)
                               .message("email not successful")
                               .result(false)
                               .build()
                       );
   }

}
