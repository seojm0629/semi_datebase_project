package kr.co.iei.match.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.match.model.service.MatchService;
import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;

@Controller
@RequestMapping(value="/match")
public class MatchController {
	
	@Autowired
	MatchService matchService;
	@Autowired
	MemberService memberService;
	
	@GetMapping(value="/view")
	public String view() {
		return "/match/view";
	}
	
	@GetMapping(value="/membershipFrm")
	public String membershipFrm(){
		return "/match/membershipFrm";
	}
	
	@PostMapping(value="/calcPrice")
	@ResponseBody
	public Grade calcPrice(String memberGrade , String memberAge , String matchCount , String memberGender ,String memberId, Model model) {
		
		int match = Integer.parseInt(matchCount);
		int age = Integer.parseInt(memberAge);
		Grade g = new Grade();
		g.setMatchCount(match);
		g.setMemberAge(age);
		g.setMemberGender(memberGender);
		g.setMemberGrade(memberGrade);
		g.setMemberGender(memberGender.toUpperCase());
		
		
		g.setMemberGender(g.getMemberGender().toUpperCase());
		Grade grade = matchService.calcPrice(g);
		grade.setMemberList(memberService.selectOneMember(memberId));
		
		
		return grade;
	}
}
