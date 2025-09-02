package kr.co.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//페이지 이동작업
	@GetMapping(value="/")
	public String main(){
		return "index";
	}
	
	
}
