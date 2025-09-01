package kr.co.iei.match.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.match.model.service.MatchService;
import kr.co.iei.match.model.vo.Grade;

@Controller
@RequestMapping(value="/match")
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	@GetMapping(value="/view")
	public String view() {
		return "/match/view";
	}
	
	@GetMapping(value="/membershipFrm")
	public String membershipFrm(){
		return "/match/membershipFrm";
	}
	
	@GetMapping(value="/calcPrice")
	@ResponseBody
	public Grade calcPrice(Grade g) {
		/*
		int match = Integer.parseInt(matchCount);
		int age = Integer.parseInt(memberAge);
		Grade g = new Grade();
		g.setMatchCount(match);
		g.setMemberAge(age);
		g.setMemberGender(memberGender);
		g.setMemberGrade(memberGrade);
		System.out.println(g);
		*/
		Grade grade = matchService.calcPrice(g);
		return grade;
	}
}
