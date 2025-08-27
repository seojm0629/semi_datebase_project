package kr.co.iei.match.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/match")
public class MatchController {
	
	@GetMapping(value="/view")
	public String view() {
		return "/match/view";
	}
	
}
