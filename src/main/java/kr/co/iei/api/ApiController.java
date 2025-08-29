package kr.co.iei.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
	
	@Autowired
	private EmailSender emailSender;
	
	
	
}
