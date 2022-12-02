package com.hongmai.clonfer.util;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */

import com.hongmai.clonfer.enums.ResultCodeEnum;
import com.hongmai.clonfer.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;

import static cn.hutool.core.date.DateTime.now;

@Component
public class SendEmailUtil {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendSimpleMail(String email, String title, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(title);
            helper.setFrom("contact@shiyirep.com");
            helper.setTo(email);
            helper.setSentDate(now());
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new ApiException(ResultCodeEnum.SEND_AUTH_EMAIL_FAILED);
        }
    }
}
