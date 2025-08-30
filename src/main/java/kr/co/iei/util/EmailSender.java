package kr.co.iei.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender sender;

	public void sendMail(String mailTitle, String emailFull, String emailContent) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			//메일 전송 시간 설정
			helper.setSentDate(new Date());
			//보내는 사람 정보
			helper.setFrom(new InternetAddress("datebases2s2@gmail.com","DateBase"));
			//받는사람 이메일 주소
			helper.setTo(emailFull);
			//제목 설정
			helper.setSubject(mailTitle);
			//내용 설정
			helper.setText(emailContent, true);
			//이메일 전송
			sender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
